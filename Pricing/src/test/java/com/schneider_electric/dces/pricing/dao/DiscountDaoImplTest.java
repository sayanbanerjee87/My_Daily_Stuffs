package com.schneider_electric.dces.pricing.dao;

import com.schneider_electric.dces.pricing.model.Discount;
import com.schneider_electric.dces.pricing.model.DiscountFamily;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
        "classpath:/applicationContext-test.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback =  true)
@Transactional
@TestExecutionListeners( {
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class })
public class DiscountDaoImplTest {

    @Autowired
    private DiscountDaoImpl discountDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void shouldLoadDiscountFamilies_fromPriceList_atGivenDate_forAuthorizedUser() {
        Collection<DiscountFamily> discountFamilies = discountDao.loadDiscountFamilies("FR", new LocalDate(2014, 10, 1).toDate());
        assertThat(discountFamilies).hasSize(3);
        assertThat(discountFamilies).extracting("code").containsOnly("F", "F1", "F2");

        discountFamilies = discountDao.loadDiscountFamilies("FR", new LocalDate(2015, 2, 1).toDate());
        assertThat(discountFamilies).hasSize(4);
        assertThat(discountFamilies).extracting("code").containsOnly("F", "F1", "F2", "F3");

        discountFamilies = discountDao.loadDiscountFamilies("FR", new LocalDate(2013, 1, 1).toDate());
        assertThat(discountFamilies).isEmpty();
    }


    @Test
    public void shouldLoadEveryDiscounts_forAGivenUserId_andPriceList() {
        Collection<Discount> discounts = discountDao.loadDiscounts("1234", "FR", new LocalDate(2014, 8, 15).toDate());
        assertThat(discounts).usingElementComparatorIgnoringFields("id", "validityStart", "validityStop").containsOnly(
                new Discount("FR", "1234", "F", 0.2d, new LocalDateTime(2014, 8, 1, 0, 0).toDate(), new LocalDateTime(2014, 10, 1, 0, 0).toDate()),
                new Discount("FR", "1234", "F1", 0.1d, new LocalDateTime(2014, 8, 1, 0, 0).toDate(), new LocalDateTime(2014, 9, 1, 0, 0).toDate()),
                new Discount("FR", "1234", "F2", 0.35d, new LocalDateTime(2014, 8, 1, 0, 0).toDate())
        );

        discounts = discountDao.loadDiscounts("1234", "FR", new LocalDate(2014, 9, 15).toDate());
        assertThat(discounts).usingElementComparatorIgnoringFields("id", "validityStart", "validityStop").containsOnly(
                new Discount("FR", "1234", "F", 0.2d, new LocalDateTime(2014, 8, 1, 0, 0).toDate(), new LocalDateTime(2014, 10, 1, 0, 0).toDate()),
                new Discount("FR", "1234", "F2", 0.35d, new LocalDateTime(2014, 8, 1, 0, 0).toDate())
        );

        discounts = discountDao.loadDiscounts("UNKNOWN", "FR");
        assertThat(discounts).isEmpty();

        discounts = discountDao.loadDiscounts("1234", "UNKNOWN");
        assertThat(discounts).isEmpty();
    }

    @Test
    public void shouldInsertNewDiscount_andUpdateExistingOnes() {
        Collection<Discount> discountsToSave = newArrayList(
                // Update with new value
                new Discount("FR", "1234", "F", 0.1d, new LocalDate(2014, 8, 1).toDate()),
                // Insert and set validityStop to previous discount
                new Discount("FR", "1234", "F2", 0.1d, new LocalDate(2014, 12, 1).toDate()),
                // Insert new discount
                new Discount("FR", "1234", "F1", 0.1d, new LocalDate(2014, 10, 1).toDate())
        );
        discountDao.saveDiscounts(discountsToSave);

        Date discountDate = new LocalDate(2014, 12, 15).toDate();
        Collection<Discount> discounts = discountDao.loadDiscounts("1234", "FR", discountDate);
        assertThat(discounts).hasSize(3);
        assertThat(discounts).usingElementComparatorIgnoringFields("id", "validityStart", "validityStop").containsAll(newArrayList(
                new Discount("FR", "1234", "F", 0.25d, new LocalDate(2014, 10, 1).toDate(), new LocalDate(2015, 2, 1).toDate()),
                new Discount("FR", "1234", "F2", 0.1d, new LocalDate(2014, 12, 1).toDate()),
                new Discount("FR", "1234", "F1", 0.1d, new LocalDate(2014, 10, 1).toDate())
        ));

        discounts = discountDao.loadDiscounts("1234", "FR", new LocalDate(2014, 9, 30).toDate());
        assertThat(discounts).hasSize(2);
        assertThat(discounts).usingElementComparatorIgnoringFields("id", "validityStart", "validityStop").containsAll(newArrayList(
                new Discount("FR", "1234", "F", 0.1d, new LocalDate(2014, 8, 1).toDate(), new LocalDate(2014, 10, 1).toDate()),
                new Discount("FR", "1234", "F2", 0.35d, new LocalDate(2014, 8, 1).toDate(), new LocalDate(2014, 12, 1).toDate())
        ));
    }

    @Test
    public void shouldDeleteDiscountWithNullValue() {
        Date discountDate = new LocalDate().plusMonths(6).toDate();
        Collection<Discount> discountsToSave = newArrayList(
                new Discount("FR", "003", "F2", 0.2d, discountDate)
        );
        discountDao.saveDiscounts(discountsToSave);

        Collection<Discount> discounts = discountDao.loadDiscounts("003", "FR", discountDate);
        assertThat(discounts).hasSize(1);

        discountsToSave = newArrayList(
                new Discount("FR", "003", "F2", null, discountDate)
        );
        discountDao.saveDiscounts(discountsToSave);

        discounts = discountDao.loadDiscounts("003", "FR", discountDate);
        assertThat(discounts).hasSize(0);
    }

    @Test
    public void shouldUpdateDiscountValidityStopToNow_IfValueIsNull_AndNoDiscountInTheFuture() {
        Date discountDate = new LocalDate().minusMonths(1).toDate();
        Collection<Discount> discountsToSave = newArrayList(
                new Discount("FR", "004", "F2", 0.2d, discountDate)
        );
        discountDao.saveDiscounts(discountsToSave);
        this.entityManager.flush();

        Collection<Discount> discounts = discountDao.loadDiscounts("004", "FR", discountDate);
        assertThat(discounts).hasSize(1);

        discountsToSave = newArrayList(
                new Discount("FR", "004", "F2", null, discountDate)
        );
        discountDao.saveDiscounts(discountsToSave);

        discounts = discountDao.loadDiscounts("004", "FR", new LocalDate().minusDays(1).toDate());
        assertThat(discounts).hasSize(1);
        assertThat(discounts).containsExactly(new Discount("FR", "004", "F2", 0.2d, discountDate, new LocalDate().toDate()));
    }

    @Test
    public void shouldUpdateDiscountValidityStopToNow_IfValueIsNull_AndDiscountExistInTheFuture() {
        Date discountDate = new LocalDate().minusMonths(1).toDate();
        Collection<Discount> discountsToSave = newArrayList(
                new Discount("FR", "005", "F", 0.2d, discountDate, new LocalDate().plusMonths(1).toDate()),
                new Discount("FR", "005", "F", 0.3d, new LocalDate().plusMonths(1).toDate())
        );
        discountDao.saveDiscounts(discountsToSave);

        Collection<Discount> discounts = discountDao.loadDiscounts("005", "FR", discountDate);
        assertThat(discounts).hasSize(1);

        discountsToSave = newArrayList(
                new Discount("FR", "005", "F", null, discountDate)
        );
        discountDao.saveDiscounts(discountsToSave);

        discounts = discountDao.loadDiscounts("005", "FR", new LocalDate().minusDays(1).toDate());
        assertThat(discounts).hasSize(1);
        assertThat(discounts).containsExactly(new Discount("FR", "005", "F", 0.2d, discountDate, new LocalDate().toDate()));

        discounts = discountDao.loadDiscounts("005", "FR", new LocalDate().plusDays(1).toDate());
        assertThat(discounts).hasSize(0);
    }

    @Test
    public void shouldUpdateDiscountValidityStopToTheDiscountValidityStart_IfValueIsNull_AndDiscountExistAtValidityStart() {
        Date discountDate = new LocalDate().minusMonths(3).toDate();
        Collection<Discount> discountsToSave = newArrayList(
                new Discount("FR", "006", "F", 0.2d, discountDate, new LocalDate().plusMonths(1).toDate()),
                new Discount("FR", "006", "F", 0.3d, new LocalDate().plusMonths(1).toDate())
        );
        discountDao.saveDiscounts(discountsToSave);

        Collection<Discount> discounts = discountDao.loadDiscounts("006", "FR", discountDate);
        assertThat(discounts).hasSize(1);

        LocalDate nullDiscountDate = new LocalDate().minusMonths(1);
        discountsToSave = newArrayList(
                new Discount("FR", "006", "F", null, nullDiscountDate.toDate())
        );
        discountDao.saveDiscounts(discountsToSave);

        discounts = discountDao.loadDiscounts("006", "FR", new LocalDate().toDate());
        assertThat(discounts).hasSize(0);

        discounts = discountDao.loadDiscounts("006", "FR", nullDiscountDate.minusDays(1).toDate());
        assertThat(discounts).hasSize(1);
        assertThat(discounts).containsExactly(new Discount("FR", "006", "F", 0.2d, discountDate, nullDiscountDate.toDate()));
    }
}
package com.schneider_electric.dces.pricing.dao;

import com.google.common.collect.Sets;
import com.schneider_electric.dces.pricing.exception.RelationConstraintException;
import com.schneider_electric.dces.pricing.exception.RevisionInThePastException;
import com.schneider_electric.dces.pricing.i18n.I18nProvider;
import com.schneider_electric.dces.pricing.model.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.xnap.commons.i18n.I18nFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;

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
public class PriceDaoImplTest {

    @Autowired
	private PriceDaoImpl priceDao;

    @Autowired
	private DiscountDaoImpl discountDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Before
    public void setUp() {
        I18nProvider.set(I18nFactory.getI18n(getClass(), Locale.ENGLISH));
    }

    @Test
    @Ignore
    public void generateSql() {
        generateSql("org.hibernate.dialect.H2Dialect", "src/test/resources/db/", "createSchema-H2.sql");
        generateSql("org.hibernate.dialect.MySQLDialect", "src/test/resources/db/", "createSchema-Mysql.sql");
    }

    private void generateSql(String dialectClass, String filePath, String fileName) {
        Configuration cfg = new Configuration();
        cfg.setProperty("hibernate.dialect", dialectClass);
        cfg.setProperty("hibernate.hbm2ddl.auto", "create");
        cfg.addAnnotatedClass(Price.class);
        cfg.addAnnotatedClass(PriceList.class);
        cfg.addAnnotatedClass(PriceRevision.class);
        cfg.addAnnotatedClass(UserAccess.class);
        cfg.addAnnotatedClass(DiscountFamily.class);
        cfg.addAnnotatedClass(Discount.class);
        SchemaExport export = new SchemaExport(cfg);
        export.setDelimiter(";");
        export.setOutputFile(filePath + fileName);
        export.setFormat(true);
        export.execute(true, false, false, true);
    }

    @Test
	public void shouldnt_get_price_none_exists_for_criteria() {
		Set<Price> prices = priceDao.getPrices(Sets.newHashSet("123456"), "FR");
		assertThat(prices).isEmpty();
	}

	@Test
	public void should_only_get_prices_that_matches_every_criteria() {
		Set<Price> prices = priceDao.getPrices(Sets.newHashSet("28267","16774","26970","UNKNOWN"), "FR");
		assertThat(prices).hasSize(3);
	}

    @Test
    public void should_get_prices_in_the_past() {
        Set<Price> prices = priceDao.getPrices(Sets.newHashSet("28267", "16774", "26970", "36774"), "FR", new LocalDate(2014, 10, 1).toDate());
        assertThat(prices).extracting("value").containsOnly(50d, 117.96d, 12.7d, 16.4d);
    }

	@Test
	public void should_get_all_prices_if_no_reference_id_is_specified() {
        Set<Price> prices = priceDao.getPrices(new HashSet<String>(), "FR");
        assertThat(prices).hasSize(4);
	}

    @Test
    public void shouldLoadAPublicPriceList_byItsID() {
        PriceList priceList = priceDao.getPriceList("1234", "FR");
        assertThat(priceList).isNotNull();
        assertThat(priceList).isEqualToIgnoringGivenFields(new PriceList("FR", "FR", "Prix France", PriceListType.PUBLIC, "EUR"), "revisions");
    }

    @Test
    public void shouldLoadAPrivatePriceList_byItsID() {
        PriceList priceList = priceDao.getPriceList("1234", "3333");
        assertThat(priceList).isNotNull();
        assertThat(priceList).isEqualToIgnoringGivenFields(new PriceList("3333", "My prices", "User1 Price 1", PriceListType.USER, "EUR"), "revisions");
    }

    @Test(expected = AccessDeniedException.class)
    public void shouldNotLoadPriceList_byItsID_ifUserHasNoAccess() {
        priceDao.getPriceList("unknown", "3333");
    }

    @Test
    public void shouldFindPriceList_returnNothing_ifUserHasNoPrivateList() {
        List<PriceList> priceLists = priceDao.findPriceList("unknown", null, null, null);
        assertThat(priceLists).isEmpty();
    }

    @Test
    public void shouldFindPriceList_returnUserPriceLists_withoutFilter() {
        List<PriceList> priceLists = priceDao.findPriceList("1234", null, null, null);
        assertThat(priceLists)
                .hasSize(2)
                .extracting("id").containsOnly("3333", "4444");
    }

    @Test
    public void shouldFindPriceList_returnUserPriceLists_withFilterOnName() {
        List<PriceList> priceLists = priceDao.findPriceList("1234", "My prices", null, null);
        assertThat(priceLists)
                .hasSize(1)
                .extracting("name").containsOnly("My prices");
    }

    @Test
    public void shouldFindPriceList_returnUserPriceLists_withFilterOnDate_andRevisionExists_forGivenDate() {
        List<PriceList> priceLists = priceDao.findPriceList("1234", null, new LocalDate(2015, 1, 1).toDate(), null);
        assertThat(priceLists)
                .hasSize(1)
                .extracting("id").containsOnly("3333");
    }

    @Test
    public void shouldFindPriceList_returnAnEmptyList_withFilterOnDate_andRevisionDoesNotExists_forGivenDate() {
        List<PriceList> priceLists = priceDao.findPriceList("1234", null, new LocalDate(2013, 1, 1).toDate(), null);
        assertThat(priceLists).isEmpty();
    }

    @Test(expected = AccessDeniedException.class)
    public void shouldFindPriceList_throwAnAccessDeniedException_whenFederatedId_isNull() {
        priceDao.findPriceList(null, "FR", new LocalDate(2013, 1, 1).toDate(), null);
    }

    @Test(expected = AccessDeniedException.class)
    public void shouldSavePriceList_throwAnAccessDeniedException_ifNoFederatedId_isGiven() {
        priceDao.save(null, new PriceList(null, "Test", "Test price list", PriceListType.USER, "EUR"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldSavePriceList_throwAnIllegalArgumentException_ifNoId_isGiven_forPublicList() {
        priceDao.save("1234-test", new PriceList(null, "Test", "Test price list", PriceListType.PUBLIC, "EUR"));
    }

    @Test
    public void shouldSavePriceList_persistPriceList() {
        PriceList priceList = new PriceList(null, "Test", "Test price list", PriceListType.USER, "EUR");
        priceDao.save("1234-test", priceList);

        assertThat(priceList.getId()).isNotEmpty();

        this.entityManager.flush();

        PriceList priceListInserted = priceDao.getPriceList("1234-test", priceList.getId());
        assertThat(priceListInserted).isEqualTo(priceList);
    }

    @Test
    public void shouldUpdatePriceList_persistPriceList() {
        PriceList priceList = new PriceList("4444", "Test Update", "User 1 prices 2", PriceListType.USER, "EUR");
        priceDao.save("1234", priceList);

        this.entityManager.flush();

        PriceList priceListInserted = priceDao.getPriceList("1234", priceList.getId());
        assertThat(priceListInserted).isEqualTo(priceList);
    }

    @Test
    public void shouldSavePriceRevision() {
        PriceRevision revision = new PriceRevision(new PriceList("4444"), new LocalDate(2014, 10, 1).toDate(), new LocalDate(2015, 1, 1).toDate());
        Collection<Price> prices = newArrayList(
                new Price(revision, "REF1", null, 150d),
                new Price(revision, "REF2", null, 75.5d)
        );
        revision.setPrices(prices);

        Collection<DiscountFamily> discountFamilies = newArrayList(
                new DiscountFamily("F1", "Family 1", revision),
                new DiscountFamily("F2", "Family 2", revision)
        );
        revision.setDiscountFamilies(discountFamilies);

        priceDao.save("1234", revision);
        entityManager.flush();

        Set<Price> pricesRetrieved = priceDao.getPrices("4444", new LocalDate(2014, 11, 1).toDate());
        assertThat(pricesRetrieved).hasSize(2);
        assertThat(pricesRetrieved).containsAll(prices);

        Collection<DiscountFamily> discountFamiliesInserted = discountDao.loadDiscountFamilies("4444", new LocalDate(2014, 11, 1).toDate());
        assertThat(discountFamiliesInserted).hasSize(2);
        assertThat(discountFamiliesInserted).containsAll(discountFamilies);
    }

    @Test
    public void shoudDeleteAPriceList_withNoRevision() {
        priceDao.deletePriceList("4321", "5555");
        PriceList priceList = priceDao.getPriceList("4321", "5555");
        assertThat(priceList.isArchived()).isTrue();
    }

    @Test(expected = AccessDeniedException.class)
    public void shoudNotDeleteAPriceList_ifUserHasNoAccess() {
        priceDao.deletePriceList("4321", "4444");
    }

    @Test
    public void shoudDeleteAPriceRevision_inTheFuture() {
        PriceRevision revision = new PriceRevision(new PriceList("4444"), new LocalDate().plusMonths(6).toDate(), null);
        ArrayList<Price> prices = newArrayList(
                new Price(revision, "REF1", null, 150d),
                new Price(revision, "REF2", null, 75.5d)
        );
        revision.setPrices(prices);

        priceDao.save("1234", revision);
        entityManager.flush();

        priceDao.deletePriceRevision("1234", revision.getId());

        PriceRevision priceRevision = entityManager.find(PriceRevision.class, revision.getId());
        assertThat(priceRevision).isNull();
    }

    @Test
    public void shoudCreateAPriceRevision_usingTimestampForTheCurrentDay() {
        PriceRevision revision = new PriceRevision(new PriceList("4444"), new LocalDateTime().minusMinutes(1).toDate(), null);
        ArrayList<Price> prices = newArrayList(
                new Price(revision, "REF1", null, 150d),
                new Price(revision, "REF2", null, 75.5d)
        );
        revision.setPrices(prices);

        priceDao.save("1234", revision);
        entityManager.flush();

        PriceRevision revision2 = new PriceRevision(new PriceList("4444"), new LocalDateTime().toDate(), null);
        prices = newArrayList(
                new Price(revision2, "REF1", null, 152d),
                new Price(revision2, "REF2", null, 76d)
        );
        revision2.setPrices(prices);

        priceDao.save("1234", revision2);
        entityManager.flush();

        PriceRevision revisionNow = priceDao.loadRevision("4444", new LocalDateTime().toDate());
        assertThat(revisionNow).isEqualTo(revision2);

        PriceRevision revisionJustBefore = priceDao.loadRevision("4444", new LocalDateTime().minusMinutes(1).toDate());
        assertThat(revisionJustBefore).isEqualTo(revision);
    }

    @Test(expected = RevisionInThePastException.class)
    public void shoudNotDeleteAPriceRevision_inThePast() {
        priceDao.deletePriceRevision("1234", 3L);
    }

    @Test(expected = AccessDeniedException.class)
    public void shoudNotDeleteAPriceRevision_ifUserHasNoAccess() {
        priceDao.deletePriceRevision("4321", 3L);
    }

    @Test
    public void shouldLoadPriceRevision_withThousandPrices_inTime() {
        PriceRevision revision = new PriceRevision(new PriceList("4444"), new LocalDateTime().plusMonths(6).toDate(), null);
        ArrayList<Price> prices = newArrayList();
        for (int i = 0; i < 5000; i++) {
            prices.add(new Price(revision, "REF" + i, "F", 10d));
        }
        revision.setPrices(prices);

        long startTime = System.currentTimeMillis();
        priceDao.save("1234", revision);
        entityManager.flush();
        long stopTime = System.currentTimeMillis();
        System.out.println(stopTime - startTime);
        assertThat(stopTime - startTime).isLessThan(4000);
    }

    @Test
    public void shouldLoadPrices_inTheFuture_withoutToDate() {
        PriceRevision revision = new PriceRevision(new PriceList("5555"), new LocalDateTime().minusDays(1).toDate(), null);
        ArrayList<Price> prices = newArrayList(
                new Price(revision, "REF1", null, 160d),
                new Price(revision, "REF2", null, 76.6d)
        );
        revision.setPrices(prices);

        priceDao.save("4321", revision);
        entityManager.flush();

        PriceRevision revision2 = new PriceRevision(new PriceList("5555"), new LocalDateTime().plusDays(5).toDate(), null);
        prices = newArrayList(
                new Price(revision2, "REF1", null, 152d),
                new Price(revision2, "REF2", null, 76d)
        );
        revision2.setPrices(prices);

        priceDao.save("4321", revision2);
        entityManager.flush();

        PriceRevision revisionNow = priceDao.loadRevision("5555", new LocalDateTime().plusDays(5).toDate());
        assertThat(revisionNow).isEqualTo(revision2);

        PriceRevision revisionJustBefore = priceDao.loadRevision("5555", new LocalDateTime().plusDays(1).toDate());
        assertThat(revisionJustBefore).isEqualTo(revision);
    }

}

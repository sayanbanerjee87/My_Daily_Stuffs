package com.schneider_electric.dces.pricing.dao;

import com.schneider_electric.dces.pricing.model.Discount;
import com.schneider_electric.dces.pricing.model.DiscountFamily;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Date;

/**
 * User: FDU3285
 * Date: 14/11/2014
 * Time: 08:39
 */
@Singleton
@Repository
public class DiscountDaoImpl implements DiscountDao {

    protected final Log logger = LogFactory.getLog(getClass());

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Collection<Discount> loadDiscounts(String federatedId, String priceList) {
        return loadDiscounts(federatedId, priceList, new Date());
    }

    @Override
    public Collection<Discount> loadDiscounts(String federatedId, String priceList, Date discountDate) {
        return this.entityManager.createQuery(
                "Select d from Discount d, DiscountFamily df " +
                        "where d.federatedId=:federatedId " +
                        "and df.revision.priceList.id=:priceListId " +
                        "and df.revision.priceList.type='PUBLIC' " +
                        "and df.code=d.familyCode " +
                        "and d.validityStart <= :discountDate " +
                        "and (d.validityStop IS NULL or d.validityStop > :discountDate) " +
                        "and df.revision.from <= :discountDate " +
                        "and (df.revision.to IS NULL or df.revision.to > :discountDate)", Discount.class)
                .setParameter("federatedId", federatedId)
                .setParameter("priceListId", priceList)
                .setParameter("discountDate", discountDate)
                .getResultList();
    }

    private Discount getDiscountMatching(Discount discount) {
        try {
            return this.entityManager.createQuery(
                    "from Discount where federatedId=:federatedId " +
                            "and priceList=:priceList " +
                            "and familyCode=:familyCode " +
                            "and validityStart=:validityStart", Discount.class)
                    .setParameter("federatedId", discount.getFederatedId())
                    .setParameter("priceList", discount.getPriceList())
                    .setParameter("familyCode", discount.getFamilyCode())
                    .setParameter("validityStart", discount.getValidityStart())
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    private Discount loadDiscount(String federatedId, String priceList, String familyCode, Date validityDate) {
        try {
            return this.entityManager.createQuery(
                    "from Discount where federatedId=:federatedId " +
                            "and priceList=:priceList " +
                            "and familyCode=:familyCode " +
                            "and validityStart <=:validityDate and (validityStop IS NULL or validityStop > :validityDate)", Discount.class)
                    .setParameter("federatedId", federatedId)
                    .setParameter("priceList", priceList)
                    .setParameter("familyCode", familyCode)
                    .setParameter("validityDate", validityDate)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void saveDiscounts(Collection<Discount> discounts) {
        Session session = entityManager.unwrap(Session.class);

        for (Discount discount : discounts) {
            Discount discountForUpdate = discount;
            Discount existingDiscount = getDiscountMatching(discountForUpdate);
            Discount nextDiscount = getNextDiscount(discountForUpdate);

            if (discountForUpdate.hasNoValue()) {
                // Discount without value
                if (existingDiscount == null) {
                    // No discount exist with this validity start
                    // Set validity stop for discount living at the given validity start
                    // Shorting living time of this discount
                    updateValidityStop(discountForUpdate);
                    continue;
                }
                Date today = new LocalDate().toDate();
                if (today.equals(discountForUpdate.getValidityStart())) {
                    // A discount exist at the start date which is also today
                    // The discount has not been used
                    // We can delete it
                    session.delete(existingDiscount);
                } else {
                    // Another discount exist for this validity start
                    // We update it making it ending today
                    discountForUpdate = existingDiscount;
                    discountForUpdate.setValidityStop(today);
                    session.saveOrUpdate(discountForUpdate);
                }
            } else {
                if (nextDiscount != null) {
                    discountForUpdate.setValidityStop(nextDiscount.getValidityStart());
                }

                if (existingDiscount != null) {
                    existingDiscount.setValidityStop(discountForUpdate.getValidityStop());
                    existingDiscount.setValue(discountForUpdate.getValue());
                    session.update(existingDiscount);
                } else {
                    updateValidityStop(discountForUpdate);
                    session.save(discountForUpdate);
                }
            }
        }
    }

    private void updateValidityStop(Discount discount) {
        Discount discountToUpdate = loadDiscount(discount.getFederatedId(), discount.getPriceList(), discount.getFamilyCode(), discount.getValidityStart());
        if (discountToUpdate != null) {
            discountToUpdate.setValidityStop(discount.getValidityStart());
        }
    }

    @Override
    public Collection<DiscountFamily> loadDiscountFamilies(String priceList, Date validityDate) {
        return this.entityManager.createQuery("from DiscountFamily df where df.revision.priceList.id=:priceListId and (:validityDate IS NULL or (df.revision.from <= :validityDate and (df.revision.to IS NULL or df.revision.to > :validityDate)))", DiscountFamily.class)
                .setParameter("priceListId", priceList)
                .setParameter("validityDate", validityDate)
                .getResultList();
    }

    private Discount getNextDiscount(Discount discount) {
        try {
            return this.entityManager.createQuery(
                    "from Discount where federatedId=:federatedId " +
                    "AND priceList=:priceList AND familyCode=:familyCode AND validityStart > :discountDate order by validityStart", Discount.class)
                    .setParameter("federatedId", discount.getFederatedId())
                    .setParameter("priceList", discount.getPriceList())
                    .setParameter("familyCode", discount.getFamilyCode())
                    .setParameter("discountDate", discount.getValidityStart())
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}

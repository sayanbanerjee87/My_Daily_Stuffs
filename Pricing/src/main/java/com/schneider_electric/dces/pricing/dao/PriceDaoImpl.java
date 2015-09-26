package com.schneider_electric.dces.pricing.dao;

import com.schneider_electric.dces.pricing.exception.RelationConstraintException;
import com.schneider_electric.dces.pricing.exception.RevisionInThePastException;
import com.schneider_electric.dces.pricing.i18n.I18nProvider;
import com.schneider_electric.dces.pricing.model.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Repository;
import org.xnap.commons.i18n.I18n;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Sets.newHashSet;
import static org.hibernate.criterion.Property.forName;
import static org.hibernate.criterion.Restrictions.*;

@Singleton
@Repository
public class PriceDaoImpl implements PriceDao {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String REVISION_QUERY = "Select r from PriceRevision r, PriceList l where r.priceList.id=:priceListId and r.from <= :priceDate AND (r.to IS NULL OR r.to > :priceDate)";

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Set<Price> getPrices(String priceList) {
        return getPrices(priceList, new Date());
    }

    @Override
    public Set<Price> getPrices(String priceList, Date priceDate) {
        I18n i18n = I18nProvider.get();
        checkArgument(priceList != null && !priceList.isEmpty(), i18n.tr("Please provide the price list"));
        Session session = entityManager.unwrap(Session.class);
        session.disableFilter("referenceFilter");
        PriceRevision revision = loadRevision(priceList, priceDate);
        if (revision != null) {
            return newHashSet(revision.getPrices());
        }
        return newHashSet();
    }

    @Override
    public Set<Price> getPrices(Set<String> referenceIds, String priceList) {
        return getPrices(referenceIds, priceList, new Date());
    }

    @Override
    public Set<Price> getPrices(Set<String> referenceIds, String priceList, Date priceDate) {
        I18n i18n = I18nProvider.get();
        checkArgument(priceList != null && !priceList.isEmpty(), i18n.tr("Please provide the price list"));

        if (referenceIds != null && !referenceIds.isEmpty()) {
            Session session = entityManager.unwrap(Session.class);
            session.enableFilter("referenceFilter").setParameterList("refIds", referenceIds);
        }

        PriceRevision revision = loadRevision(priceList, priceDate);
        if (revision != null) {
            return newHashSet(revision.getPrices());
        }
        return newHashSet();
    }

    @Override
    public PriceRevision loadRevision(String federatedId, String priceListId, Set<String> referenceIds, Date priceDate) {
        I18n i18n = I18nProvider.get();
        if (federatedId == null || federatedId.isEmpty()) {
            throw new AccessDeniedException(i18n.tr("User federatedId should be given"));
        }
        checkArgument(priceListId != null && !priceListId.isEmpty(), i18n.tr("Please provide the price list"));

        if (!canAccess(federatedId, priceListId)) {
            logger.info("User has no access to price list <{}>", priceListId);
            throw new AccessDeniedException(i18n.tr("User has no access to the given price list"));
        }

        if (referenceIds != null && !referenceIds.isEmpty()) {
            Session session = entityManager.unwrap(Session.class);
            session.enableFilter("referenceFilter").setParameterList("refIds", referenceIds);
        }

        return loadRevision(priceListId, priceDate);
    }

    @Override
    public PriceRevision loadRevision(String priceList, Date priceDate) {
        I18n i18n = I18nProvider.get();
        checkArgument(priceList != null && !priceList.isEmpty(), i18n.tr("Please provide the price list"));
        try {
            PriceRevision revision = this.entityManager.createQuery(REVISION_QUERY, PriceRevision.class)
                    .setParameter("priceListId", priceList)
                    .setParameter("priceDate", priceDate != null ? priceDate : new Date())
                    .getSingleResult();
            revision.getPrices().size();
            revision.getDiscountFamilies().size();

            return revision;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public PriceRevision loadRevision(String federatedId, long revisionId, Collection<String> referenceIds) {
        I18n i18n = I18nProvider.get();
        checkArgument(referenceIds != null && !referenceIds.isEmpty(), i18n.tr("Please provide reference Ids to load"));
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("referenceFilter").setParameterList("refIds", referenceIds);
        return loadRevision(federatedId, revisionId);
    }

    @Override
    public PriceRevision loadRevision(String federatedId, long revisionId) {
        I18n i18n = I18nProvider.get();
        if (federatedId == null || federatedId.isEmpty()) {
            throw new AccessDeniedException(i18n.tr("User federatedId should be given"));
        }

        try {
            PriceRevision priceRevision = this.entityManager.find(PriceRevision.class, revisionId);

            if (!canAccess(federatedId, priceRevision.getPriceList().getId())) {
                logger.info("User has no access to revision <{}>", revisionId);
                throw new AccessDeniedException(i18n.tr("User has no access to the given revision"));
            }
            priceRevision.getPrices();
            priceRevision.getDiscountFamilies();

            return priceRevision;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public PriceList getPriceList(String federatedId, String priceListId) {
        I18n i18n = I18nProvider.get();
        PriceList priceList = entityManager.find(PriceList.class, priceListId);
        if (priceList != null && priceList.getType() != PriceListType.PUBLIC && !canAccess(federatedId, priceListId)) {
            logger.info("User has no access to price list <{}>", priceListId);
            throw new AccessDeniedException(i18n.tr("User has no access to the given price list"));
        }
        if (priceList != null) {
            priceList.getRevisions().size();
        }
        return priceList;
    }

    private UserAccess getUserAccess(String federatedId, String priceListId) throws NoResultException {
        PriceList priceList = this.entityManager.find(PriceList.class, priceListId);
        if (PriceListType.PUBLIC == priceList.getType()) {
            return null;
        }
        return entityManager.createQuery("from UserAccess where federatedId=:federatedId and priceList.id=:priceList", UserAccess.class)
                .setParameter("federatedId", federatedId)
                .setParameter("priceList", priceListId)
                .getSingleResult();
    }

    private boolean canAccess(String federatedId, String priceListId) {
        try {
            getUserAccess(federatedId, priceListId);
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    public List<PriceList> findPriceList(String federatedId, String name, Date validityDate, Boolean archived) {
        I18n i18n = I18nProvider.get();
        if (federatedId == null || federatedId.isEmpty()) {
            throw new AccessDeniedException(i18n.tr("User federatedId should be given"));
        }

        Session session = entityManager.unwrap(Session.class);
        Criteria cr = session.createCriteria(PriceList.class);
        if (name != null) {
            cr.add(eq("name", name));
        }
        if (archived != null) {
            cr.add(eq("archived", archived));
        }
        if (validityDate != null) {
            Criteria revisions = cr.createCriteria("revisions");
            revisions.add(and(
                            le("from", validityDate),
                            or(isNull("to"), gt("to", validityDate)))
            );
        }

        DetachedCriteria userAccess = DetachedCriteria.forClass(UserAccess.class, "userAccess")
                .setProjection(forName("priceList.id"))
                .add(eq("federatedId", federatedId));

        cr.add(eq("type", PriceListType.USER));
        cr.add(forName("id").in(userAccess));

        return cr.list();
    }

    @Override
    public void save(String federatedId, PriceList priceList) {
        I18n i18n = I18nProvider.get();
        if (federatedId == null || federatedId.isEmpty()) {
            throw new AccessDeniedException(i18n.tr("User federatedId should be given"));
        }

        if (PriceListType.PUBLIC == priceList.getType() && (priceList.getId() == null || priceList.getId().isEmpty())) {
            throw new IllegalArgumentException(i18n.tr("Price list ID should be given to save public lists"));
        }

        Session session = entityManager.unwrap(Session.class);

        if (PriceListType.USER == priceList.getType() && (priceList.getId() == null || priceList.getId().isEmpty())) {
            priceList.setId(UUID.randomUUID().toString());
        }

        session.saveOrUpdate(priceList);
        entityManager.flush();

        if (PriceListType.USER == priceList.getType()) {
            UserAccess userAccess = new UserAccess(federatedId, priceList);
            if (!canAccess(federatedId, priceList.getId())) {
                entityManager.persist(userAccess);
            }
        }
    }

    @Override
    public void deletePriceList(String federatedId, String priceListId) {
        I18n i18n = I18nProvider.get();
        PriceList priceList = entityManager.find(PriceList.class, priceListId);

        if (priceList == null) {
            return;
        }

        if (priceList.getType() == PriceListType.USER && !canAccess(federatedId, priceListId)) {
            logger.info("User has no access to price list <{}>", priceListId);
            throw new AccessDeniedException(i18n.tr("User has no access to the given price list"));
        }

        priceList.setArchived(true);
        this.entityManager.merge(priceList);
    }

    @Override
    public void save(String federatedId, PriceRevision revision) {
        I18n i18n = I18nProvider.get();
        if (federatedId == null || federatedId.isEmpty()) {
            throw new AccessDeniedException(i18n.tr("User federatedId should be given"));
        }
        if (isUserPriceList(revision.getPriceList()) && !canAccess(federatedId, revision.getPriceList().getId())) {
            logger.info("User has no access to price list <{}>", revision.getPriceList().getId());
            throw new AccessDeniedException(i18n.tr("User has no access to the given price list"));
        }

        Session session = entityManager.unwrap(Session.class);

        PriceRevision existingRevisionAtFromDate = loadRevision(revision.getPriceList().getId(), revision.getFrom());
        if (existingRevisionAtFromDate != null) {
            existingRevisionAtFromDate.setTo(revision.getFrom());
            session.saveOrUpdate(existingRevisionAtFromDate);
            this.entityManager.flush();
        }

        if (revision.getTo() != null) {
            PriceRevision existingRevisionAtToDate = loadRevision(revision.getPriceList().getId(), revision.getTo());
            if (existingRevisionAtToDate != null && !existingRevisionAtToDate.equals(existingRevisionAtFromDate)) {
                existingRevisionAtToDate.setFrom(revision.getTo());
                session.saveOrUpdate(existingRevisionAtToDate);
            }
        }

        session.saveOrUpdate(revision);
    }

    private boolean isUserPriceList(PriceList priceList) {
        if (priceList.getType() != null) {
            return PriceListType.USER == priceList.getType();
        }
        PriceList persistedPriceList = this.entityManager.find(PriceList.class, priceList.getId());
        if (persistedPriceList == null) {
            throw new IllegalArgumentException("Price list not found");
        }
        return PriceListType.USER == persistedPriceList.getType();
    }

    @Override
    public void deletePriceRevision(String federatedId, Long revisionId) {
        I18n i18n = I18nProvider.get();
        if (federatedId == null || federatedId.isEmpty()) {
            throw new AccessDeniedException(i18n.tr("User federatedId should be given"));
        }
        PriceRevision revision = entityManager.find(PriceRevision.class, revisionId);
        if (revision == null) {
            return;
        }

        if (isUserPriceList(revision.getPriceList()) && !canAccess(federatedId, revision.getPriceList().getId())) {
            logger.info("User has no access to price list <{}>", revision.getPriceList().getId());
            throw new AccessDeniedException(i18n.tr("User has no access to price list"));
        }

        if (revision.getFrom().compareTo(new Date()) < 0) {
            logger.info("Cannot delete an already started price revision <{}>", revision.toString());
            throw new RevisionInThePastException(i18n.tr("Cannot delete an already started price revision"));
        }

        entityManager.remove(revision);
    }
}
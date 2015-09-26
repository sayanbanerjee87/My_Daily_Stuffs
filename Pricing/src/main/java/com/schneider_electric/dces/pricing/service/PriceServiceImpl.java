package com.schneider_electric.dces.pricing.service;

import com.schneider_electric.dces.pricing.dao.DiscountDao;
import com.schneider_electric.dces.pricing.dao.PriceDao;
import com.schneider_electric.dces.pricing.i18n.I18nProvider;
import com.schneider_electric.dces.pricing.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xnap.commons.i18n.I18n;

import javax.inject.Singleton;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.NotFoundException;
import java.util.*;


@Singleton
@Service
@Transactional
public class PriceServiceImpl implements PriceService {

    private static final Logger LOG = LoggerFactory.getLogger(PriceServiceImpl.class);

	@Autowired
	private PriceDao priceDao;

	@Autowired
	private DiscountDao discountDao;

    @Override
    public Set<Price> getPrices(String priceList) {
        return priceDao.getPrices(priceList);
    }

    @Override
    public Set<Price> getPrices(String priceList, Date pricingDate) {
        return priceDao.getPrices(priceList, pricingDate);
    }

    @Override
	public Set<Price> getPrices(Set<String> referenceIds, String priceList, Date priceDate) {
		return  priceDao.getPrices(referenceIds, priceList, priceDate);
	}

    @Override
    public void setPrices(PriceRevision revision) {
        priceDao.save("1234", revision);
    }

	@Override
	public Set<Price> getPricesWithDiscount(Set<String> referenceIds, String priceList, String federatedId, Date validityDate) {

		Set<Price> prices;
		Collection<Discount> discounts;

		if (validityDate == null) {
			prices = getPrices(referenceIds, priceList, new Date());
			discounts = discountDao.loadDiscounts(federatedId, priceList);
		} else {
			prices = priceDao.getPrices(referenceIds, priceList, validityDate);
			discounts = discountDao.loadDiscounts(federatedId, priceList, validityDate);
		}


		HashMap<String, Double> discountMap = createDiscountmap(discounts);

        for (Price price : prices) {
            price.setDiscount(discountMap.get(price.getFamilyCode()));
        }
		return prices;
	}

	private HashMap<String, Double> createDiscountmap (Collection<Discount> discounts) {
		HashMap<String, Double> map = new HashMap<>();

        for (Discount discount : discounts) {
            map.put(discount.getFamilyCode(), discount.getValue());
        }

		return map;
	}

    @Override
    public void saveDiscounts(String federatedId, Collection<Discount> discounts) {
        for (Discount discount : discounts) {
            discount.setFederatedId(federatedId);
        }
        discountDao.saveDiscounts(discounts);
    }

    @Override
    public void saveDiscounts(String federatedId, String priceListId, Collection<Discount> discounts) {
        for (Discount discount : discounts) {
            discount.setPriceList(priceListId);
        }
        saveDiscounts(federatedId, discounts);
    }

    @Override
    public Collection<DiscountFamily> loadFamilies(String priceList, Date validityDate) {
        return discountDao.loadDiscountFamilies(priceList, validityDate);
    }

	@Override
	public Collection<Discount> loadDiscounts(String federatedId, String countryCode) {
		return discountDao.loadDiscounts(federatedId, countryCode);
	}

    @Override
    public List<PriceList> findPriceLists(String federatedId, String name, Date validityDate, Boolean archived) {
        return priceDao.findPriceList(federatedId, name, validityDate, archived);
    }

    @Override
    public PriceList loadPriceList(String federatedId, String priceListId) {
        return priceDao.getPriceList(federatedId, priceListId);
    }

    @Override
    public PriceList createPriceList(String federatedId, PriceList priceList) {
        priceDao.save(federatedId, priceList);
        return priceList;
    }

    @Override
    public void updatePriceList(String federatedId, PriceList priceList, String priceListId) throws NotFoundException {
        checkPriceListExistence(federatedId, priceListId);
        priceList.setId(priceListId);

        // Check that type is not changed
        PriceList oldPriceList = this.loadPriceList(federatedId,priceListId); // TODO : do this in dao
        if (oldPriceList.getType() != (priceList.getType())){
            I18n i18n = I18nProvider.get();
            throw new NotAcceptableException(i18n.tr("Changing the type of a price list is forbidden"));
        }
        oldPriceList.setName(priceList.getName());
        oldPriceList.setCurrencyCode(priceList.getCurrencyCode());
        oldPriceList.setDescription(priceList.getDescription());
        oldPriceList.setRevisions(priceList.getRevisions());

        priceDao.save(federatedId, oldPriceList);
    }

    @Override
    public void deletePriceList(String federatedId, String priceListId) {
        checkPriceListExistence(federatedId, priceListId);
        priceDao.deletePriceList(federatedId, priceListId);
    }

    @Override
    public PriceRevision loadPrices(String federatedId, long revisionId, Collection<String> refs) {
        return priceDao.loadRevision(federatedId, revisionId, refs);
    }

    @Override
    public PriceRevision loadPrices(String federatedId, String priceListId, Set<String> refs, Date validityDate) {
        return priceDao.loadRevision(federatedId, priceListId, refs, validityDate);
    }

    @Override
    public PriceRevision createPriceListRevision(String federatedId, String priceListId, PriceRevision revision) {
        revision.setPriceList(new PriceList(priceListId));
        priceDao.save(federatedId, revision);
        return revision;
    }

    @Override
    public void updatePriceListRevision(String federatedId, PriceRevision revision) {
        priceDao.save(federatedId, revision);
    }

    @Override
    public void deletePriceListRevision(String federatedId, long revisionId) {
        priceDao.deletePriceRevision(federatedId, revisionId);
    }

    @Override
    public PriceRevision loadPriceListRevision(String federatedId, Long revisionId) {
        return priceDao.loadRevision(federatedId, revisionId);
    }

    private void checkPriceListExistence(String federatedId, String priceListId){
        if (priceDao.findPriceList(federatedId, priceListId, null, null) == null){
            I18n i18n = I18nProvider.get();
            LOG.info("Price list {} doesn't exist", priceListId);
            throw new NotFoundException(i18n.tr("Price list doesn't exist"));
        }
    }
}

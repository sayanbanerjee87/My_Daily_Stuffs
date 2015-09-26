package com.schneider_electric.dces.pricing.config;

import com.schneider_electric.dces.pricing.model.*;
import com.schneider_electric.dces.pricing.service.PriceService;
import org.springframework.stereotype.Service;

import javax.inject.Singleton;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.NotFoundException;
import java.util.*;

import static com.google.common.collect.Lists.newArrayList;

@Singleton
@Service
public class PriceServiceImplMock implements PriceService {

    public static final String priceListId = "id" ;
    public static final String wrongPriceListId = "wrong_id" ;
    public static final Long priceRevisionId = 1L ;
    public static final Long wrongPriceRevisionId = -1L ;

    @Override
	public Set<Price> getPrices(Set<String> referenceIds, String priceList, Date priceDate) {
    	Set<Price> prices = Collections.synchronizedSet(new HashSet<Price>());
        prices.add(new Price("ref1", "F", 50d));
    	prices.add(new Price("ref2", "F1", 30d));
    	prices.add(new Price("ref3", "F2", null));
        return prices;
    }

    @Override
    public Set<Price> getPrices(String priceList, Date priceDate) {
        return getPrices(priceList);
    }

    @Override
    public Set<Price> getPrices(String priceList) {
        Set<Price> prices = Collections.synchronizedSet(new HashSet<Price>());
        prices.add(new Price("ref1", "F", 50d));
        prices.add(new Price("ref2", "F1", 30d));
        prices.add(new Price("ref3", "F2", 5d));
        prices.add(new Price("ref4", "F2", 45d));
        return prices;


    }

	@Override
	public void setPrices(PriceRevision revision) {
		// TODO Auto-generated method stub
	}

	@Override
	public Set<Price> getPricesWithDiscount(Set<String> referenceIds,
			String priceList, String federatedId, Date validityDate) {

		Set<Price> prices = new HashSet<Price>();

		if(referenceIds.contains("Ref1")) {
			Price price1 = new Price("Ref1", "F1", 13d);
			price1.setDiscount(0.1d);
			prices.add(price1);
		}


		if(referenceIds.contains("Ref2")) {
			Price price2 = new Price("Ref2", "F1", 12.5d);
			price2.setDiscount(0.2d);
			prices.add(price2);
		}

		if(referenceIds.contains("Ref3") && !federatedId.equals("001")) {
			Price price3 = new Price("Ref3", "F1", 13.5d);
			price3.setDiscount(0.25d);
			prices.add(price3);
		}

		return prices;
	}

    @Override
    public void saveDiscounts(String federatedId, Collection<Discount> discounts) {
    }

    @Override
    public void saveDiscounts(String federatedId, String priceListId, Collection<Discount> discounts) {

    }

    @Override
    public Collection<DiscountFamily> loadFamilies(String priceList, Date validityDate) {
        return newArrayList(
                new DiscountFamily("F1", "Family F1"),
                new DiscountFamily("F2", "Family F2")
        );
    }

	@Override
	public Collection<Discount> loadDiscounts(String federatedId,
			String countryCode) {
		return newArrayList(
                new Discount(federatedId, "F1", 10d, new Date()),
                new Discount(federatedId, "F2", 13.5d, new Date()),
                new Discount(federatedId, "F3", 13.5d, new Date())
        );
	}

    @Override
    public List<PriceList> findPriceLists(String federatedId, String name, Date validityDate, Boolean archived) {
        return null;
    }

    @Override
    public PriceList loadPriceList(String federatedId, String priceListId) {
        if (priceListId.equals(this.priceListId)){
            return new PriceList();
        }

        throw new NotFoundException(priceListId);
    }

    @Override
    public PriceList createPriceList(String federatedId, PriceList priceList) {
        return new PriceList("0","name","desc",PriceListType.USER,"currencyCode");
    }

    @Override
    public void updatePriceList(String federatedId, PriceList priceList, String priceListId) throws NotFoundException {
        if (priceListId.equals(PriceServiceImplMock.priceListId)){
            if (priceList.getType() == PriceListType.USER){
                throw new NotAcceptableException();
            }
            return ;
        }

        throw new NotFoundException(priceList.getId());
    }

    @Override
    public void deletePriceList(String federatedId, String priceListId) {
        if (!priceListId.equals(PriceServiceImplMock.priceListId)){
            throw new NotFoundException(priceListId);
        }
    }

    @Override
    public PriceRevision loadPrices(String federatedId, long revisionId, Collection<String> refs) {
        return null;
    }

    @Override
    public PriceRevision loadPrices(String federatedId, String priceListId, Set<String> refs, Date validityDate) {
        if (!priceListId.equals(PriceServiceImplMock.priceListId)){
            throw new NotFoundException(priceListId);
        }

        return new PriceRevision(priceRevisionId, new PriceList(priceListId), new Date(), new Date(), new HashSet<Price>());
    }

    @Override
    public PriceRevision createPriceListRevision(String federatedId, String priceListId, PriceRevision revision) {
        if (!priceListId.equals(PriceServiceImplMock.priceListId)){
            throw new NotFoundException(priceListId);
        }

        return new PriceRevision(priceRevisionId, new PriceList(priceListId), new Date(), new Date(), new HashSet<Price>());
    }

    @Override
    public void updatePriceListRevision(String federatedId, PriceRevision revision) {

    }

    @Override
    public void deletePriceListRevision(String federatedId, long revisionId) {
        if (revisionId != priceRevisionId){
            throw new NotFoundException(priceListId);
        }
    }

    @Override
    public PriceRevision loadPriceListRevision(String federatedId, Long revisionId) {
        return null;
    }
}
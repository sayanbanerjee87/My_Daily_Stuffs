package com.schneider_electric.dces.pricing.rest.dto;

import com.schneider_electric.dces.pricing.model.Price;
import com.schneider_electric.dces.pricing.model.PriceList;
import com.schneider_electric.dces.pricing.model.PriceRevision;

import java.util.Collection;
import java.util.Date;

public class PriceRevisionWithCurrency extends PriceRevision {

    private String currencyCode ;

    public PriceRevisionWithCurrency() {
        super();
    }

    public PriceRevisionWithCurrency(long id, PriceList priceList, Date from, Date to, Collection<Price> prices, String currencyCode){
        super(id,priceList,from,to,prices);

        this.currencyCode = currencyCode ;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}

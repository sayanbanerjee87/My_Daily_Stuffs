package com.schneider_electric.dces.pricing.rest;

import com.schneider_electric.dces.pricing.model.Price;
import com.schneider_electric.dces.pricing.rest.dto.StringList;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import java.util.Date;
import java.util.Set;

public interface PriceWithDiscountResource {
    Set<Price> getPrices(@QueryParam("referenceIds") StringList referenceIds,
                         @NotNull @NotEmpty @PathParam("priceList") String countryCode,
                         @QueryParam("priceDate") Date priceDate, @HeaderParam("Authorization") String authorizationParam);
}

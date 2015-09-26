package com.schneider_electric.dces.pricing.rest;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.ws.rs.QueryParam;

import com.schneider_electric.dces.pricing.rest.dto.StringList;
import org.hibernate.validator.constraints.NotEmpty;

import com.schneider_electric.dces.pricing.model.Price;

public interface PriceResource {
	public abstract Set<Price> getPrices(@QueryParam("referenceIds") StringList referenceIds,
										  @NotNull @NotEmpty @QueryParam("countryCode") String countryCode,
										  @QueryParam("priceDate") Date priceDate);
}
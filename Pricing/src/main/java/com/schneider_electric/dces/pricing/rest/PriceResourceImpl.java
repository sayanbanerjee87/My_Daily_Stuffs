package com.schneider_electric.dces.pricing.rest;

import com.schneider_electric.dces.pricing.i18n.I18nProvider;
import com.schneider_electric.dces.pricing.model.Price;
import com.schneider_electric.dces.pricing.rest.dto.StringList;
import com.schneider_electric.dces.pricing.service.PriceService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.Authorization;
import com.wordnik.swagger.annotations.AuthorizationScope;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xnap.commons.i18n.I18n;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

@Component
@Path("/")
@Api(value = "prices", description = "Prices API")
public class PriceResourceImpl implements PriceResource {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private PriceService priceService;

	public PriceResourceImpl() {
	}

	@Override
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/prices/{countryCode}")
	@ApiOperation(value = "Get prices for a country and a list of references",
            notes = "Returns prices for the given country and the list of references.<br/><br/>" +
                    "Sample:<br/>" +
                    "/prices/FR?referenceIds=A123456,B456789&priceDate=2014-11-20",
            authorizations = @Authorization(value = "access token", scopes = @AuthorizationScope(scope = "getPrices", description = "Retrieve prices")))
	public Set<Price> getPrices(@QueryParam("referenceIds") StringList referenceIds,
			@NotNull @NotEmpty @PathParam("countryCode") String countryCode, @QueryParam("priceDate") Date priceDate) {

		if (logger.isInfoEnabled()) {
			logger.info("New request for reference(s): " + referenceIds + ", country " + countryCode);
		}
		
		Set<String> referenceIdSet = referenceIds != null ? newHashSet(referenceIds) : new HashSet<String>();

		return priceService.getPrices(referenceIdSet, countryCode, priceDate);
	}

}

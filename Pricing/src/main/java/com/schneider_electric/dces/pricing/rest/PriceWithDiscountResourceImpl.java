package com.schneider_electric.dces.pricing.rest;

import com.schneider_electric.dces.pricing.exception.UserNotFoundException;
import com.schneider_electric.dces.pricing.i18n.I18nProvider;
import com.schneider_electric.dces.pricing.model.FederatedIdWrapper;
import com.schneider_electric.dces.pricing.model.Price;
import com.schneider_electric.dces.pricing.rest.dto.StringList;
import com.schneider_electric.dces.pricing.service.PriceService;
import com.schneider_electric.dces.pricing.service.UserService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.Authorization;
import com.wordnik.swagger.annotations.AuthorizationScope;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xnap.commons.i18n.I18n;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

@Component
@Path("/")
@Api(value = "pricesWithDiscount", description = "Prices with Discount API")
public class PriceWithDiscountResourceImpl implements PriceWithDiscountResource {


    protected final static Logger LOG = LoggerFactory.getLogger(PriceWithDiscountResourceImpl.class);

    private PriceService priceService;

    private UserService userService;

    @Autowired
    public PriceWithDiscountResourceImpl(PriceService priceService, UserService userService) {
        this.priceService = priceService;
        this.userService = userService;
    }

    @Override
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/pricesWithDiscount/{priceList}")
    @ApiOperation(value = "Get prices with user discount",
            notes = "Returns prices with user discount for the given references<br/><br/>" +
                    "<b>Authorization header is populated with the token retreived by the user during authentication.</b><br/>" +
                    "Query parameters are used to specify reference IDs and country (ISO code):<br/>" +
                    "<b>Sample:</b><br/>" +
                    "<pre>/pricesWithDiscount/FR?referenceIds=A12345,B456789&priceDate=2014-11-20</pre><br/>",
            authorizations = @Authorization(value = "token", scopes = @AuthorizationScope(scope = "getPricesWithDiscount", description = "Retrieve prices with user discount")),
            response = Price.class, responseContainer = "Set")
    public Set<Price> getPrices(@QueryParam("referenceIds") StringList referenceIds,
                                @NotNull @NotEmpty @PathParam("priceList") String priceList,
                                @QueryParam("priceDate") Date priceDate, @HeaderParam("Authorization") String authorizationParam) {

        String federatedId = userService.getFederatedId(authorizationParam);

        LOG.debug("Asking prices for federatedID {}", federatedId);

        Set<String> referenceIdSet = newHashSet(referenceIds);

        return priceService.getPricesWithDiscount(referenceIdSet, priceList, federatedId, priceDate);
    }

}

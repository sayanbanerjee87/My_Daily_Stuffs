package com.schneider_electric.dces.pricing.rest;

import com.schneider_electric.dces.pricing.model.Discount;
import com.schneider_electric.dces.pricing.model.DiscountFamily;
import com.schneider_electric.dces.pricing.rest.dto.DiscountWithDescription;
import com.schneider_electric.dces.pricing.service.PriceService;
import com.schneider_electric.dces.pricing.service.UserService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.Authorization;
import com.wordnik.swagger.annotations.AuthorizationScope;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.*;

import static com.google.common.collect.Maps.newHashMap;

@Component
@Path("/")
@Api(value = "discounts", description = "Discounts API")
public class DiscountResourceImpl implements DicountResource {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PriceService priceService;
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @PUT
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/discount")
    @ApiOperation(value = "Set user discounts",
            notes = "Set user discounts<br/><br/>" +
                    "<b>Sample :</b><br/>" +
                    "<b>Request:</b><br/>" +
                    "<pre>PUT /api/v1/discount HTTP/1.1\n" +
                    "Host: pricing-sqe.pace.schneider-electric.com\n" +
                    "Cache-Control: no-cache\n" +
                    "Content-Type: application/json\n" +
                    "authorization: TOKEN_GENERATED_BY_LOGIN_APP\n" +
                    "\n" +
                    "[\n" +
                    "  {\"familyCode\":\"F1\", \"priceList\": \"FR\", \"value\": 0.2, \"validityStart\": \"2014-07-14\"},\n" +
                    "  {\"familyCode\":\"F2\", \"priceList\": \"FR\", \"value\": 0.15, \"validityStart\": \"2014-09-1\"}\n" +
                    "]</pre>" +
                    "<br/>",
            authorizations = @Authorization(value = "token", scopes = @AuthorizationScope(scope = "setDiscounts", description = "Save discounts per family for authenticated user")))
    public void setDiscounts(@Valid Collection<Discount> discounts, @HeaderParam("Authorization") String authorizationParam) {
        String federatedId = userService.getFederatedId(authorizationParam);
        logger.info("UPDATING DISCOUNTS: user={}", federatedId);
        priceService.saveDiscounts(federatedId, discounts);
        logger.info("DISCOUNTS UPDATED: user={}", federatedId);
    }
    
    @Override
	@GET  
	@Path("/discount")
	@Produces({MediaType.APPLICATION_JSON})
	@ApiOperation(value = "Get user's discounts",
    notes = "Returns a json with the discounts for the user<br/><br/>" +
            "<b>Authorization header is populated with the token retreived by the user during authentication.</b><br/>" +
            "Query parameter is used to specify the country (ISO code):<br/>" +
            "<b>Sample:</b><br/>" +
            "<pre>?priceList=FR</pre><br/>",
    authorizations = @Authorization(value = "token", scopes = @AuthorizationScope(scope = "getDiscountsInJson", description = "Get the user's discounts in json format")),
    response = Response.class)
	public Collection<DiscountWithDescription> getDiscountsInJson(@QueryParam("priceList") String priceList, @HeaderParam("Authorization") String authorizationParam) throws IOException {
		
        String federatedId = userService.getFederatedId(authorizationParam);
        
        ArrayList<DiscountWithDescription> discountsWithDescription = new ArrayList<DiscountWithDescription>();

        Collection<Discount> discounts = priceService.loadDiscounts(federatedId, priceList);
        Collection<DiscountFamily> families = priceService.loadFamilies(priceList, new Date());
        Map<String, Discount> discountForFamilyMap = mapToFamily(discounts);

        Date today = new LocalDate().toDate();
        for (DiscountFamily family : families) {
            DiscountWithDescription discountWithDescription;
            Discount discount = discountForFamilyMap.get(family.getCode());
            if (discount != null) {
                discountWithDescription = new DiscountWithDescription(discount.getFamilyCode(), discount.getValue(), new LocalDate(discount.getValidityStart()).toDate(), family.getDescription());
            } else {
                discountWithDescription = new DiscountWithDescription(family.getCode(), null, today, family.getDescription());
            }
            discountsWithDescription.add(discountWithDescription);
        }
        
        return  discountsWithDescription;
	}

    private Map<String, Discount> mapToFamily(Collection<Discount> discounts) {
        Map<String, Discount> discountsByFamily = newHashMap();
        for (Discount discount : discounts) {
            discountsByFamily.put(discount.getFamilyCode(), discount);
        }
        return discountsByFamily;
    }

    @Override
    @PUT
    @Consumes({ MediaType.APPLICATION_JSON })
    @Path("/discount/{priceList}")
    @ApiOperation(value = "Set user discounts",
            notes = "Set user discounts<br/><br/>" +
                    "<b>Sample :</b><br/>" +
                    "<b>Request:</b><br/>" +
                    "<pre>POST /api/v1/discount HTTP/1.1\n" +
                    "Host: pricing-sqe.pace.schneider-electric.com\n" +
                    "Cache-Control: no-cache\n" +
                    "Content-Type: application/json\n" +
                    "authorization: TOKEN_GENERATED_BY_LOGIN_APP\n" +
                    "\n" +
                    "[\n" +
                    "  {\"familyCode\":\"F1\", \"priceList\": \"FR\", \"value\": 0.2, \"validityStart\": \"2014-07-14\"},\n" +
                    "  {\"familyCode\":\"F2\", \"priceList\": \"FR\", \"value\": 0.15, \"validityStart\": 1417104942}\n" +
                    "]</pre>" +
                    "<br/>",
            authorizations = @Authorization(value = "token", scopes = @AuthorizationScope(scope = "setDiscounts", description = "Save discounts per family for authenticated user")))
	public void updateDiscounts(@PathParam("priceList") String priceListId, List<Discount> discounts, @HeaderParam("Authorization") String authorizationParam) {
        String federatedId = userService.getFederatedId(authorizationParam);
        logger.info("UPDATING DISCOUNTS: user={}", federatedId);
        priceService.saveDiscounts(federatedId, priceListId, discounts);
        logger.info("DISCOUNTS UPDATED: user={}", federatedId);
	}

    @Override
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/families/{priceList}")
	public Collection<DiscountFamily> getFamilies(@PathParam("priceList") String priceList) {
		return priceService.loadFamilies(priceList, new Date());
	}

}

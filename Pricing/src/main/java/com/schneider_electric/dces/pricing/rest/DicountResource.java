package com.schneider_electric.dces.pricing.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.schneider_electric.dces.pricing.model.Discount;
import com.schneider_electric.dces.pricing.model.DiscountFamily;
import com.schneider_electric.dces.pricing.rest.dto.DiscountWithDescription;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

public interface DicountResource {
    void setDiscounts(@Valid Collection<Discount> discounts, @HeaderParam("Authorization") String authorizationParam);
    Collection<DiscountWithDescription> getDiscountsInJson(@QueryParam("countryCode") String countryCode, @HeaderParam("Authorization") String authorizationParam) throws IOException;
    void updateDiscounts(@PathParam("countryCode") String countryCode, List<Discount> discounts, @HeaderParam("Authorization") String authorizationParam);
    Collection<DiscountFamily> getFamilies(@PathParam("countryCode") String countryCode);
}

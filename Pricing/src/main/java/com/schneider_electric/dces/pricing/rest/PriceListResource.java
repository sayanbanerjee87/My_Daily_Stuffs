package com.schneider_electric.dces.pricing.rest;

import com.schneider_electric.dces.pricing.model.PriceList;
import com.schneider_electric.dces.pricing.model.PriceListType;
import com.schneider_electric.dces.pricing.model.PriceRevision;
import com.schneider_electric.dces.pricing.rest.dto.StringList;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.Authorization;
import com.wordnik.swagger.annotations.AuthorizationScope;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.jvnet.hk2.annotations.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

public interface PriceListResource {

    Response getPriceLists(@QueryParam("name") @Optional String name,
                           @QueryParam("validityDate") @Optional Date date,
                           @QueryParam("archived") @Optional Boolean archived,
                           @HeaderParam("Authorization") @Optional String authorization);

    Response getPriceList(@PathParam("id") @NotNull String priceListId,
                                          @QueryParam("validityDate") @Optional String validityDate,
                                          @HeaderParam("Authorization") @Optional String authorization);

    Response addPriceList(@NotNull @Valid PriceList priceList,
                                          @HeaderParam("Authorization") @NotNull String authorization);

    Response updatePriceList(@PathParam("id") @NotNull String priceListId,
                                             @NotNull @Valid PriceList priceList,
                                             @HeaderParam("Authorization") @NotNull String authorization);

    Response deletePriceList(@PathParam("id") @NotNull String priceListId,
                                             @HeaderParam("Authorization") @NotNull String authorization);

    @POST
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Path("/pricelists/{id}")
    @ApiOperation(value = "Add revision",
                  authorizations = @Authorization(value = "token",scopes = @AuthorizationScope(scope = "addRevision", description = "Add revision")))
    Response addRevision(@PathParam("id") @NotNull String priceListId,
                         FormDataMultiPart formDataMultiPart,
                         @HeaderParam("Authorization") @NotNull String authorization);

    Response addRevision(@PathParam("id") @NotNull String priceListId,
                                         @NotNull @Valid PriceRevision revision,
                                         @HeaderParam("Authorization") @NotNull String authorization);

    Response deleteRevision(@PathParam("id") @NotNull String priceListId,
                                            @PathParam("revId") @NotNull Long revId,
                                            @HeaderParam("Authorization") @NotNull String authorization);

    Response getRevision(@PathParam("id") @NotNull String priceListId,
                                         @PathParam("revId") @NotNull Long revId,
                                         @HeaderParam("Authorization") String authorization);

    Response getPrices(@QueryParam("priceListId") @NotNull @NotBlank String priceListId,
                                       @QueryParam("refIds") StringList refIds,
                                       @QueryParam("validityDate") Date validityDate,
                                       @HeaderParam("Authorization") String authorization);
}


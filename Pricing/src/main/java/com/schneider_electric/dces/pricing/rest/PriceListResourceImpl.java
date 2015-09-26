package com.schneider_electric.dces.pricing.rest;

import com.google.common.collect.ImmutableMap;
import com.schneider_electric.dces.pricing.exception.ErrorWrapper;
import com.schneider_electric.dces.pricing.i18n.I18nProvider;
import com.schneider_electric.dces.pricing.model.PriceList;
import com.schneider_electric.dces.pricing.model.PriceRevision;
import com.schneider_electric.dces.pricing.rest.dto.PriceRevisionWithCurrency;
import com.schneider_electric.dces.pricing.rest.dto.StringList;
import com.schneider_electric.dces.pricing.service.ExcelParserImpl;
import com.schneider_electric.dces.pricing.service.PriceService;
import com.schneider_electric.dces.pricing.service.UserService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.Authorization;
import com.wordnik.swagger.annotations.AuthorizationScope;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.hibernate.validator.constraints.NotBlank;
import org.jvnet.hk2.annotations.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.xnap.commons.i18n.I18n;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.InputStream;
import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Component
@Path("/")
@Api(value = "pricelists", description = "Price lists API")
public class PriceListResourceImpl implements PriceListResource {

    //region private fields

    private static final Logger LOG = LoggerFactory.getLogger(PriceListResourceImpl.class);

    private final String priceListsApiBasePath = "/api/pricelists/" ;

    @Autowired
    private UserService userService;

    @Autowired
    private PriceService priceService;

    //endregion

    //region public api

    @Override
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/pricelists")
    @ApiOperation(  value = "Get user price lists",
                    notes = "Return user price lists. It doesn't include public price lists<br/><br/>" +
                            "<b>Request:</b><br/>" +
                            "<pre>GET /api/pricelists HTTP/1.1\n" +
                            "Host: pricing-sqe.pace.schneider-electric.com\n" +
                            "Accept: application/json\n" +
                            "Cache-Control: no-cache\n" +
                            "authorization: TOKEN_GENERATED_BY_LOGIN_APP\n" +
                            "<br/>" +
                            "<b>Response:</b><br/>" +
                            "HTTP/1.1 200, OK<br/>" +
                            "<b>JSON raw data:</b><br/>" +
                            "[\n" +
                            "        {\n" +
                            "          \"id\", \"FR\",\n" +
                            "          \"name\" : \"FR\",\n" +
                            "          \"description\" : \"Prices FR\",\n" +
                            "          \"type\" : \"PUBLIC\",\n" +
                            "          \"currencyCode\" : \"EUR\"\n" +
                            "        }\n" +
                            "    ]<br/>",
                            authorizations = @Authorization(value = "token",scopes = @AuthorizationScope(scope = "getPriceLists", description = "Get price lists")),
                    responseContainer = "Collection",
                    response = PriceList.class)
    public Response getPriceLists(@QueryParam("name") @Optional String name,
                                  @QueryParam("date") @Optional Date date,
                                  @QueryParam("archived") @Optional Boolean archived,
                                  @HeaderParam("Authorization") @Optional String authorization) {

        String federatedId = null ;

        if (authorization != null) {
            federatedId = userService.getFederatedId(authorization);
        }

        List<PriceList> priceLists = this.priceService.findPriceLists(federatedId, name, date, archived);

        return  Response.ok(priceLists)
                .build();
    }

    @Override
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/pricelists/{id}")
    @ApiOperation(  value = "Get price list",
                    notes = "Return price list corresponding to the specified id. Only authenticated user can access user pricelists<br/><br/>" +
                            "<b>Request:</b><br/>" +
                            "<pre>GET /api/pricelists/{id} HTTP/1.1\n" +
                            "Host: pricing-sqe.pace.schneider-electric.com\n" +
                            "Cache-Control: no-cache\n" +
                            "authorization: TOKEN_GENERATED_BY_LOGIN_APP\n" +
                            "</pre>" +
                            "<br/>" +
                            "<b>Response:</b><br/>" +
                            "HTTP/1.1 200, OK<br/>" +
                            "<b>JSON raw data:</b><br/>" +
                            "{\n" +
                            "      \"id\", \"FR\",\n" +
                            "      \"name\" : \"FR\",\n" +
                            "      \"description\" : \"Prices FR\",\n" +
                            "      \"type\" : \"PUBLIC\",\n" +
                            "      \"currencyCode\" : \"EUR\",\n" +
                            "      \"revisions\": [\n" +
                            "        {\"id\": 1234, \"from\": timestamp, \"to\": timestamp}\n" +
                            "      ]\n" +
                            "    }",
                             authorizations = @Authorization(value = "token",scopes = @AuthorizationScope(scope = "getPriceList", description = "Get price list")),
            response = PriceList.class)
    public Response getPriceList(@PathParam("id") @NotNull String priceListId,
                                 @QueryParam("validityDate") @Optional String validityDate,
                                 @Optional @HeaderParam("Authorization") String authorization) {

        String federatedId = authorization == null ? null : userService.getFederatedId(authorization);

        PriceList priceList = this.priceService.loadPriceList(federatedId, priceListId); // TODO : handle errors (wrong id, authorizations ...)

        if (priceList == null){
            I18n i18n = I18nProvider.get();
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorWrapper(i18n.tr("Price list {0} does not exist", priceListId)))
                    .build();
        }

        return  Response.ok(priceList)
                .build();
    }

    @Override
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/pricelists")
    @ApiOperation(  value = "Add price list",
                    notes = "Create a new price list. User can not create two lists with the same name.<br/><br/>" +
                            "<b>Request:</b><br/>" +
                            "<pre>POST /api/pricelists HTTP/1.1\n" +
                            "Host: pricing-sqe.pace.schneider-electric.com\n" +
                            "Cache-Control: no-cache\n" +
                            "Content-Type: application/json\n" +
                            "authorization: TOKEN_GENERATED_BY_LOGIN_APP\n" +
                            "\n" +
                            "{\n" +
                            "   \"id\" : \"Price List ID: ONLY BUT MANDATORY for PUBLIC prices\", \n" +
                            "   \"name\" : \"Price List\", \n" +
                            "   \"description\" : \"New Price list\", \n" +
                            "   \"type\" : \"USER\", \n" +
                            "   \"currencyCode\" : \"EUR\" \n" +
                            "}</pre>" +
                            "<br/>",
                    authorizations = @Authorization(value = "token",scopes = @AuthorizationScope(scope = "addPriceList", description = "Add price list")))
    public Response addPriceList(@NotNull @Valid PriceList priceList,
                                 @HeaderParam("Authorization") @NotNull String authorization) {

        String federatedId = userService.getFederatedId(authorization);

        LOG.info("CREATE PRICE LIST: user=<{}>, priceList={}", federatedId, priceList);

        PriceList createdPriceList = this.priceService.createPriceList(federatedId, priceList);

        URI uri = UriBuilder.fromPath(priceListsApiBasePath)
                            .segment(createdPriceList.getId())
                            .build();

        LOG.info("PRICE LIST CREATED: user=<{}>, priceList={}", federatedId, createdPriceList);

        return Response.ok()
                .status(HttpStatus.CREATED.value())
                .location(uri)
                .build();
    }

    @Override
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/pricelists/{id}")
    @ApiOperation(  value = "Update price list",
                    notes = "Update the price list with id given in path. User can not rename a list if a list with the same same already exists.<br/><br/>" +
                            "<b>Request:</b><br/>" +
                            "<pre>PUT /api/pricelists/{id} HTTP/1.1\n" +
                            "Host: pricing-sqe.pace.schneider-electric.com\n" +
                            "Cache-Control: no-cache\n" +
                            "Content-Type: application/json\n" +
                            "authorization: TOKEN_GENERATED_BY_LOGIN_APP\n" +
                            "\n" +
                            "{\n" +
                            "   \"id\" : \"Price List ID\", \n" +
                            "   \"name\" : \"Price List\", \n" +
                            "   \"description\" : \"New description\", \n" +
                            "   \"type\" : \"USER\", \n" +
                            "   \"currencyCode\" : \"EUR\" \n" +
                            "}</pre>" +
                            "<br/>",
                    authorizations = @Authorization(value = "token",scopes = @AuthorizationScope(scope = "updatePriceList", description = "Update price list")))
    public Response updatePriceList(@PathParam("id") @NotNull String priceListId,
                                    @NotNull @Valid PriceList priceList,
                                    @HeaderParam("Authorization") @NotNull String authorization) {

        String federatedId = userService.getFederatedId(authorization);

        LOG.info("UPDATE PRICE LIST {}: user=<{}>, priceList={}", priceListId, federatedId, priceList);

        this.priceService.updatePriceList(federatedId, priceList, priceListId);

        LOG.info("PRICE LIST UPDATED {}: user=<{}>, priceList={}", priceListId, federatedId, priceList);

        return Response.ok()
                .status(HttpStatus.NO_CONTENT.value())
                .build();
    }

    @Override
    @DELETE
    @Path("/pricelists/{id}")
    @ApiOperation(  value = "Delete price list",
                    notes = "Delete the price list with id given in path. Price list cannot be deleted if prices exists in the price list.<br/><br/>" +
                            "<b>Request:</b><br/>" +
                            "<pre>DELETE /api/pricelists/{id} HTTP/1.1\n" +
                            "Host: pricing-sqe.pace.schneider-electric.com\n" +
                            "Cache-Control: no-cache\n" +
                            "authorization: TOKEN_GENERATED_BY_LOGIN_APP\n" +
                            "</pre>" +
                            "<br/>",
                    authorizations = @Authorization(value = "token",scopes = @AuthorizationScope(scope = "deletePriceList", description = "Delete price list")))
    public Response deletePriceList(@PathParam("id") @NotNull String priceListId,
                                    @HeaderParam("Authorization") @NotNull String authorization) {

        String federatedId = userService.getFederatedId(authorization);

        LOG.info("DELETE PRICE LIST {}: user=<{}>", priceListId, federatedId);

        this.priceService.deletePriceList(federatedId, priceListId);

        LOG.info("PRICE LIST DELETED {}: user=<{}>", priceListId, federatedId);

        return Response.ok()
                .status(HttpStatus.NO_CONTENT.value())
                .build();
    }

    @Override
    @POST
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/pricelists/{priceListId}")
    @ApiOperation(  value = "Upload prices revision to price list using XLS file",
                    notes = "Add a new revision to the price list with id given in path<br/><br/>" +
                            "<b>Request:</b><br/>" +
                            "<pre>POST /api/pricelists/{id} HTTP/1.1\n" +
                            "Host: pricing-sqe.pace.schneider-electric.com\n" +
                            "Cache-Control: no-cache\n" +
                            "Content-Type: multipart/form-data\n" +
                            "authorization: TOKEN_GENERATED_BY_LOGIN_APP\n" +
                            "data: \n" +
                            " - file: the XLS file.\n" +
                            " - from: the revision starting date.\n" +
                            " - to: (Optional) the revision ending date.\n" +
                            " - sheetIdx: the 0-based index of the sheet where prices can be found.\n" +
                            " - contentFirstRowIdx: the 0-based index of the first row where prices can be found.\n" +
                            " - referenceColIdx: the 0-based index of the column where product references can be found.\n" +
                            " - priceColIdx: the 0-based index of the column where price can be found.\n" +
                            " - familyCodeColIdx: (Optional) the 0-based index of the discount family code associated to the reference.\n" +
                            " to upload discount families (PUBLIC price list ONLY)\n" +
                            " - sheetIdxFamily: (Mandatory if familyCodeColIdx is set) the 0-based index of the sheet where discount families can be found.\n" +
                            " - familyCodeColIdxFamily: (Optional) the 0-based index of the discount family code. Default 0.\n" +
                            " - descriptionColIdxFamily: (Optional) the 0-based index of the discount family description. Default 1. \n" +
                            "</pre>" +
                            "<br/>",
                    authorizations = @Authorization(value = "token",scopes = @AuthorizationScope(scope = "addRevision", description = "Add revision")))
    public Response addRevision(@PathParam("priceListId") @NotNull String priceListId,
                                FormDataMultiPart formDataMultiPart,
                                @HeaderParam("Authorization") @NotNull String authorization) {

        String federatedId = userService.getFederatedId(authorization);

        LOG.info("UPLOAD REVISION for price list <{}>: user=<{}>", priceListId, federatedId);

        checkRevisionFormDataMultipartValid(formDataMultiPart);

        InputStream inputStream = loadPartAs(formDataMultiPart, "file", InputStream.class);
        Date from = loadPartAsDate(formDataMultiPart, "from");
        Date to = loadPartAsDate(formDataMultiPart, "to");

        PriceRevision priceRevision = new PriceRevision(new PriceList(priceListId), from, to);

        ExcelParserImpl excelParser = new ExcelParserImpl(priceListId, inputStream);
        excelParser.loadRevision(formDataMultiPart, priceRevision);

        return this.addRevision(priceListId, priceRevision, authorization);
    }

    private void checkRevisionFormDataMultipartValid(FormDataMultiPart formDataMultiPart) {
        Collection<String> mandatoryParams = newArrayList();
        if (loadPartAs(formDataMultiPart, "file", InputStream.class) == null) {
            mandatoryParams.add("file");
        }
        if (loadPartAs(formDataMultiPart, "sheetIdx", Integer.class) == null) {
            mandatoryParams.add("sheetIdx");
        }
        if (loadPartAs(formDataMultiPart, "contentFirstRowIdx", Integer.class) == null) {
            mandatoryParams.add("contentFirstRowIdx");
        }
        if (loadPartAs(formDataMultiPart, "referenceColIdx", Integer.class) == null) {
            mandatoryParams.add("referenceColIdx");
        }
        if (loadPartAs(formDataMultiPart, "priceColIdx", Integer.class) == null) {
            mandatoryParams.add("priceColIdx");
        }
        if (!mandatoryParams.isEmpty()) {
            I18n i18n = I18nProvider.get();
            throw new WebApplicationException(i18n.tr("Following data parameters are mandatory {0}", mandatoryParams), Response.Status.BAD_REQUEST);
        }
    }

    private <T> T loadPartAs(FormDataMultiPart formDataMultiPart, String fieldName, Class<T> clazz) {
        FormDataBodyPart field = formDataMultiPart.getField(fieldName);
        if (field == null) {
            return null;
        }
        return field.getValueAs(clazz);
    }

    private Date loadPartAsDate(FormDataMultiPart formDataMultiPart, String fieldName) {
        FormDataBodyPart field = formDataMultiPart.getField(fieldName);
        if (field == null) {
            return null;
        }
        final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(field.getValue());
        } catch (ParseException e) {
            I18n i18n = I18nProvider.get();
            throw new WebApplicationException(i18n.tr("Unable to parse date {0}", field.getValue()), Response.Status.BAD_REQUEST);
        }
    }

    @Override
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/pricelists/{priceListId}")
    @ApiOperation(  value = "Upload prices revision to price list as JSON data",
                    notes = "Add a new revision to the price list with id given in path<br/><br/>" +
                            "<b>Request:</b><br/>" +
                            "<pre>POST /api/pricelists/{id} HTTP/1.1\n" +
                            "Host: pricing-sqe.pace.schneider-electric.com\n" +
                            "Cache-Control: no-cache\n" +
                            "Content-Type: application/json\n" +
                            "authorization: TOKEN_GENERATED_BY_LOGIN_APP\n" +
                            "\n" +
                            "{\n" +
                            "   \"from\" : \"2015-01-01\", \n" +
                            "   \"to\" : \"2015-03-01\", \n" +
                            "   \"prices\" : [ \n" +
                            "       { \n" +
                            "           \"reference\" : \"A9B1654\", \n" +
                            "           \"value\" : 199, \n" +
                            "       }, \n" +
                            "       { \n" +
                            "           \"reference\" : \"A9B1655\", \n" +
                            "           \"value\" : 299, \n" +
                            "       } \n" +
                            "   ] \n"+
                            "}</pre>" +
                            "<br/>",
                     authorizations = @Authorization(value = "token",scopes = @AuthorizationScope(scope = "addRevision", description = "Add revision")))
    public Response addRevision(@PathParam("priceListId") @NotNull String priceListId,
                                @NotNull @Valid PriceRevision revision,
                                @HeaderParam("Authorization") @NotNull String authorization) {

        String federatedId = userService.getFederatedId(authorization);

        LOG.info("CREATE REVISION for price list <{}>: user=<{}>", priceListId, federatedId);

        PriceRevision createdPriceRevision;

        revision.setRevisionRelationshipToInternalObjects();
        createdPriceRevision = this.priceService.createPriceListRevision(federatedId, priceListId, revision);

        URI uri = UriBuilder.fromPath(priceListsApiBasePath)
                        .segment(priceListId)
                        .segment(createdPriceRevision.getId().toString())
                        .build();

        LOG.info("REVISION CREATED for price list <{}>: user=<{}>, newRevision=<{}>, from=<{}>, to=<{}>", priceListId, federatedId, revision.getId(), revision.getFrom(), revision.getTo());

        return Response.ok()
                .location(uri)
                .status(HttpStatus.CREATED.value())
                .entity(ImmutableMap.builder()
                        .put("created", revision.getPrices().size())
                        .build())
                .build();
    }

    @Override
    @DELETE
    @Path("/pricelists/{priceListId}/{revId}")
    @ApiOperation(  value = "Delete revision",
                    notes = "Delete the revision with id given in path attached to the price list given with id given in path. Only future revision can be deleted.<br/><br/>" +
                            "<b>Request:</b><br/>" +
                            "<pre>DELETE /api/pricelists/{priceListId}/{revisionId} HTTP/1.1\n" +
                            "Host: pricing-sqe.pace.schneider-electric.com\n" +
                            "Cache-Control: no-cache\n" +
                            "authorization: TOKEN_GENERATED_BY_LOGIN_APP\n" +
                            "</pre>" +
                            "<br/>",
                    authorizations = @Authorization(value = "token",scopes = @AuthorizationScope(scope = "deleteRevision", description = "Delete revision")))
    public Response deleteRevision(@PathParam("priceListId") @NotNull String priceListId,
                                   @PathParam("revId") @NotNull Long revId,
                                   @HeaderParam("Authorization") @NotNull String authorization) {

        String federatedId = userService.getFederatedId(authorization);

        LOG.info("DELETE REVISION for price list <{}>: user=<{}>", priceListId, federatedId);

        this.priceService.deletePriceListRevision(federatedId, revId);

        LOG.info("REVISION DELETED for price list <{}>: user=<{}>", priceListId, federatedId);

        return Response.ok()
                .status(HttpStatus.NO_CONTENT.value())
                .build();
    }

    @Override
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/pricelists/{priceListId}/{revId}")
    @ApiOperation(  value = "Get prices revision",
                    notes = "Return revision with id given in path attached to price list with id given in path<br/><br/>" +
                            "<b>Request:</b><br/>" +
                            "<pre>GET /api/pricelists/{priceListId}/{revisionId} HTTP/1.1\n" +
                            "Host: pricing-sqe.pace.schneider-electric.com\n" +
                            "Cache-Control: no-cache\n" +
                            "authorization: TOKEN_GENERATED_BY_LOGIN_APP\n" +
                            "</pre>" +
                            "<br/>",
                    authorizations = @Authorization(value = "token",scopes = @AuthorizationScope(scope = "getRevision", description = "Get revision")),
                    response = PriceRevision.class)
    public Response getRevision(@PathParam("priceListId") @NotNull String priceListId,
                                @PathParam("revId") @NotNull Long revId,
                                @HeaderParam("Authorization") String authorization) {

        String federatedId = userService.getFederatedId(authorization);

        PriceRevision priceRevision = this.priceService.loadPriceListRevision(federatedId, revId);

        return Response.ok(priceRevision)
                .build();
    }

    @Override
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/prices")
    @ApiOperation(  value = "Get prices",
                    notes = "Return prices of the references given as query parameter using price list with id give in query parameter. If no validity date is specified, last prices will be returned.<br/><br/>" +
                            "<b>Request:</b><br/>" +
                            "<pre>GET /api/prices HTTP/1.1\n" +
                            "Host: pricing-sqe.pace.schneider-electric.com\n" +
                            "Cache-Control: no-cache\n" +
                            "authorization: TOKEN_GENERATED_BY_LOGIN_APP\n" +
                            "</pre>" +
                            "<br/>",
                    authorizations = @Authorization(value = "token",scopes = @AuthorizationScope(scope = "getPrices", description = "Get prices")),
    response = PriceRevisionWithCurrency.class)
    public Response getPrices(@QueryParam("priceListId") @NotNull @NotBlank String priceListId,
                              @QueryParam("refIds") StringList refIds,
                              @QueryParam("validityDate") Date validityDate,
                              @HeaderParam("Authorization") String authorization) {

        String federatedId = userService.getFederatedId(authorization);

        PriceRevision priceRevision = this.priceService.loadPrices(federatedId, priceListId.trim(), refIds, validityDate); // TODO : check user access

        if (priceRevision == null) {
            I18n i18n = I18nProvider.get();
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorWrapper(i18n.tr("No price revision exist for price list {0} at the given date {1}", priceListId, validityDate != null ? validityDate : new Date())))
                    .build();
        }

        PriceRevisionWithCurrency priceRevisionWithCurrency = new PriceRevisionWithCurrency(priceRevision.getId(),
                priceRevision.getPriceList(),
                priceRevision.getFrom(),
                priceRevision.getTo(),
                priceRevision.getPrices(),
                priceRevision.getPriceList().getCurrencyCode());

        return Response.ok(priceRevisionWithCurrency)
                .build();
    }

    //endregion
}

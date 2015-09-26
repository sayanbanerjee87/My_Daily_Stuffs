package com.schneider_electric.dces.pricing.rest;

import com.schneider_electric.dces.pricing.config.JerseyConfig;
import com.schneider_electric.dces.pricing.i18n.I18nProvider;
import com.schneider_electric.dces.pricing.model.Discount;
import com.schneider_electric.dces.pricing.model.Price;
import com.schneider_electric.dces.pricing.model.PriceList;
import com.schneider_electric.dces.pricing.model.PriceListType;
import com.schneider_electric.dces.pricing.rest.dto.PriceRevisionWithCurrency;
import org.custommonkey.xmlunit.XMLUnit;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.test.JerseyTest;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.xnap.commons.i18n.I18nFactory;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * User: FDU3285
 * Date: 14/01/2015
 * Time: 08:55
 */
public class PriceListResourceIT extends JerseyTest {

    private static final boolean NOT_ARCHIVED = false;
    private static final boolean ARCHIVED = true;

    @Override
    protected Application configure() {
        XMLUnit.setIgnoreWhitespace(true);

        GenericXmlApplicationContext context = new GenericXmlApplicationContext("applicationContext-integration.xml");
        JerseyConfig rc = new JerseyConfig();
        // Now you can expect validation errors to be sent to the client.
        rc.property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        return rc.property("contextConfig", context);
    }

    @Override
    protected void configureClient(ClientConfig config) {
        config.register(MultiPartFeature.class);
    }

    @Before
    public void initI18n() {
        I18nProvider.set(I18nFactory.getI18n(getClass(), Locale.ENGLISH));
    }

    @Test
    public void functionalTest() throws Exception {
        createPublicPriceList("FR", "Prix France", "EUR");
        loadAndMatchPriceList("FR", "FR", "Prix France", "EUR", PriceListType.PUBLIC);

        createPublicPriceList("EN", "Prices UK", "GBP");
        loadAndMatchPriceList("EN", "EN", "Prices UK", "GBP", PriceListType.PUBLIC);
        editPublicPriceList("EN", "ENG", "Prices English in EUR", "EUR", PriceListType.PUBLIC);
        loadAndMatchPriceList("EN", "ENG", "Prices English in EUR", "EUR", PriceListType.PUBLIC);
        deletePriceList("EN", 204);

        uploadPrices("FR", "sample_integration.xls", "2015-01-01", "2015-03-01");
        loadPrices("FR", "11937,38100,38500,38600", "2014-12-31", Response.Status.NOT_FOUND);
        loadPrices("FR", "11937,38100,38500,38600", "2015-01-01", 4);
        loadPrices("FR", "11937,38100,38500,38600", "2015-03-01", Response.Status.NOT_FOUND);

        deletePriceList("EN", 204);

        uploadPrices("FR", "sample_integration.xls", "2015-03-01", "2015-06-01");
        loadPrices("FR", "11937,38100,38500,38600", "2015-03-01", 4);

        saveDiscounts(newArrayList(
                new Discount("FR", "1234", "F", 0.1d, new LocalDate(2014, 1, 1).toDate()),
                new Discount("FR", "1234", "F1", 0.2d, new LocalDate(2014, 12, 1).toDate(), new LocalDate(2015, 6, 1).toDate()),
                new Discount("FR", "1234", "F2", 0.3d, new LocalDate(2015, 12, 1).toDate())
        ), "1234");
        loadPricesWithDiscount("1234", "2014-11-20", "11937,38100,38500,38600", 0);
        loadPricesWithDiscount("1234", "2015-01-01", "11937,38100,38500,38600", 4, 0.1d, 0.2d, null);
        loadPricesWithDiscount("1234", "2015-12-01", "11937,38100,38500,38600", 0);

        createUserPriceList("USER1", "PL1", "price list One", "EUR");
        String userPriceList2Id = createUserPriceList("USER1", "PL2", "price list Two", "EUR");
        createUserPriceList("USER2", "PL1 user 2", "price list Two", "EUR");
        findPriceLists("USER1", null, 2);
        findPriceLists("USER1", NOT_ARCHIVED, 2);
        findPriceLists("USER1", ARCHIVED, 0);
        findPriceLists("USER2", null, 1);
        deletePriceList("USER1", userPriceList2Id, 204);
        findPriceLists("USER1", null, 2);
        findPriceLists("USER1", NOT_ARCHIVED, 1);
        findPriceLists("USER1", ARCHIVED, 1);
    }

    private void loadPricesWithDiscount(String federatedId, String date, String refIds, int size, Double... discounts) {
        Collection<Price> prices = target("/pricesWithDiscount/FR")
                .queryParam("referenceIds", refIds)
                .queryParam("priceDate", date)
                .request().accept(MediaType.APPLICATION_JSON)
                .header("Authorization", federatedId)
                .get(new GenericType<Collection<Price>>(){});
        assertThat(prices).hasSize(size);
        if (size > 0) {
            assertThat(prices).extracting("discount", Double.class).containsOnly(discounts);
        }
    }

    private void saveDiscounts(ArrayList<Discount> discounts, String federatedId) {
        Response response = target("discount")
                .request().accept(MediaType.APPLICATION_JSON)
                .header("Authorization", federatedId)
                .put(Entity.json(discounts), Response.class);

        assertThat(response.getStatus()).isEqualTo(204);
    }

    private void loadPrices(String priceListId, Object refIds, String date, int nbPrices) {
        final PriceRevisionWithCurrency response = target("prices")
                .queryParam("priceListId", priceListId)
                .queryParam("refIds", refIds)
                .queryParam("validityDate", date)
                .request().get(PriceRevisionWithCurrency.class);
        assertThat(response.getPrices()).hasSize(nbPrices);
    }

    private void loadPrices(String priceListId, Object refIds, String date, Response.Status status) {
        final Response response = target("prices")
                .queryParam("priceListId", priceListId)
                .queryParam("refIds", refIds)
                .queryParam("validityDate", date)
                .request().get(Response.class);
        assertThat(response.getStatusInfo()).isEqualTo(status);
    }

    private void uploadPrices(String priceListId, String filePath, String from, String to) {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(filePath);
        FormDataMultiPart formDataMultiPart = new FormDataMultiPart()
                .field("file", stream, MediaType.APPLICATION_OCTET_STREAM_TYPE)
                .field("from", from)
                .field("to", to)
                .field("sheetIdx", "0")
                .field("contentFirstRowIdx", "1")
                .field("referenceColIdx","0")
                .field("priceColIdx", "3")
                .field("familyColIdx", "5")
                .field("sheetIdxFamily", "1");


        final Response response = target("pricelists/" + priceListId).request()
                .header("Authorization", "Authorization")
                .post(Entity.entity(formDataMultiPart, MediaType.MULTIPART_FORM_DATA));

        assertThat(response.getStatus()).isEqualTo(201);
    }

    private void editPublicPriceList(String id, String name, String description, String currencyCode, PriceListType type) {
        final Response response = target("pricelists/" + id).request()
                .header("Authorization", "Authorization")
                .put(Entity.json(new PriceList(id, name, description, type, currencyCode)));
        assertThat(response.getStatus()).isEqualTo(204);
    }

    private void deletePriceList(String id, int expectedStatus) {
        this.deletePriceList("Authorization", id, expectedStatus);
    }

    private void deletePriceList(String userAuth, String id, int expectedStatus) {
        final Response response = target("pricelists/"+ id).request()
                .header("Authorization",userAuth)
                .delete();

        assertThat(response.getStatus()).isEqualTo(expectedStatus);

    }

    private void createPublicPriceList(String name, String description, String currencyCode) {
        final Response response = target("pricelists").request()
                .header("Authorization", "Authorization")
                .post(Entity.json(new PriceList(name, name, description, PriceListType.PUBLIC, currencyCode)));

        assertThat(response.getStatus()).isEqualTo(201);
        assertThat(response.getLocation().getPath()).isEqualTo("/api/pricelists/" + name);
    }

    private String createUserPriceList(String userAuth, String name, String description, String currencyCode) {
        final Response response = target("pricelists").request()
                .header("Authorization", userAuth)
                .post(Entity.json(new PriceList(name, name, description, PriceListType.USER, currencyCode)));

        assertThat(response.getStatus()).isEqualTo(201);
        String path = response.getLocation().getPath();
        assertThat(path).startsWith("/api/pricelists/");
        return path.substring(path.lastIndexOf('/') + 1);
    }

    private void loadAndMatchPriceList(String id, String name, String description, String currencyCode, PriceListType type) {
        final PriceList response = target("pricelists/" + id).request().get(PriceList.class);
        assertThat(response).isEqualTo(new PriceList(id, name, description, type, currencyCode));
    }

    private void findPriceLists(String userAuth, Boolean archived, int size) {
        final Collection<PriceList> priceLists = target("pricelists")
                .queryParam("archived", archived)
                .request()
                .header("Authorization", userAuth)
                .get(new GenericType<Collection<PriceList>>() {});
        assertThat(priceLists).hasSize(size);
    }
}

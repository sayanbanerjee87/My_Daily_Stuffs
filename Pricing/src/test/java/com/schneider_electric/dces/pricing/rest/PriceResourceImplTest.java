package com.schneider_electric.dces.pricing.rest;

import com.schneider_electric.dces.pricing.config.JerseyConfig;
import com.schneider_electric.dces.pricing.config.JerseyTestConfig;
import com.schneider_electric.dces.pricing.i18n.I18nProvider;
import org.glassfish.jersey.test.JerseyTest;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.xml.sax.SAXException;
import org.xnap.commons.i18n.I18nFactory;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class PriceResourceImplTest extends JerseyTest {

    @Override
    protected Application configure() {
        ApplicationContext context = new AnnotationConfigApplicationContext(JerseyTestConfig.class);
        return new JerseyConfig().property("contextConfig", context);
    }

    @Before
    public void initI18n() {
        I18nProvider.set(I18nFactory.getI18n(getClass(), Locale.ENGLISH));
    }

    @Test
    public void prices_should_be_retrievable_as_json() throws JSONException {
        final String prices = target("prices/FR")
                .queryParam("currencyCode", "EUR")
                .queryParam("referenceIds", Arrays.asList("ref1", "ref2", "ref3"))
                .request().accept(MediaType.APPLICATION_JSON).get(String.class);
        String expected = "[" +
                "{" +
                "\"reference\": \"ref1\"," +
                "\"value\": 50," +
                "\"familyCode\": \"F\"" +
                "}," +
                "{" +
                "\"reference\": \"ref2\"," +
                "\"value\": 30," +
                "\"familyCode\": \"F1\"" +
                "}," +
                "{" +
                "\"reference\": \"ref3\"," +
                "\"familyCode\": \"F2\"" +
                "}" +
                "]";
        JSONAssert.assertEquals(expected, prices, JSONCompareMode.LENIENT);
    }

    @Test
    public void prices_should_be_retrievable_as_xml() throws JSONException, SAXException, IOException {
        final String prices = target("prices/FR")
                .queryParam("currencyCode", "EUR")
                .queryParam("referenceIds", Arrays.asList("ref1", "ref2", "ref3"))
                .request().accept(MediaType.APPLICATION_XML).get(String.class);
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                "<prices>" +
                "<price><familyCode>F1</familyCode><reference>ref2</reference><value>30.0</value></price>" +
                "<price><familyCode>F2</familyCode><reference>ref3</reference></price>" +
                "<price><familyCode>F</familyCode><reference>ref1</reference><value>50.0</value></price>" +
                "</prices>";

        assertThat(prices).isEqualTo(expected);
    }

    @Test
    public void prices_should_be_retrievable_as_json_withPriceDate() throws JSONException {
        final String prices = target("prices/FR")
                .queryParam("currencyCode", "EUR")
                .queryParam("referenceIds", Arrays.asList("ref1", "ref2", "ref3"))
                .queryParam("priceDate", "2014-11-01")
                .request().accept(MediaType.APPLICATION_JSON).get(String.class);
        String expected = "[" +
                "{" +
                "\"reference\": \"ref1\"," +
                "\"value\": 50," +
                "\"familyCode\": \"F\"" +
                "}," +
                "{" +
                "\"reference\": \"ref2\"," +
                "\"value\": 30," +
                "\"familyCode\": \"F1\"" +
                "}," +
                "{" +
                "\"reference\": \"ref3\"," +
                "\"familyCode\": \"F2\"" +
                "}" +
                "]";
        JSONAssert.assertEquals(expected, prices, JSONCompareMode.LENIENT);
    }

    @Test
    public void prices_should_return_400_error_if_no_price_list() throws JSONException {
        final Response prices = target("prices")
                .queryParam("referenceIds", Arrays.asList("ref1", "ref2", "ref3"))
                .request().accept(MediaType.APPLICATION_JSON).get();

        assertThat(prices.getStatus()).isEqualTo(400);
    }

    @Test
    public void prices_should_accept_requests_without_currency() throws JSONException {
        final Response prices = target("prices/FR")
                .queryParam("referenceIds", Arrays.asList("ref1", "ref2", "ref3"))
                .request().accept(MediaType.APPLICATION_JSON).get();

        assertThat(prices.getStatus()).isEqualTo(200);
    }

    @Test
    public void prices_should_support_no_references() throws JSONException {
        final Response prices = target("prices/FR")
                .queryParam("currencyCode", "EUR")
                .request().accept(MediaType.APPLICATION_JSON).get();

        assertThat(prices.getStatus()).isEqualTo(200);
    }

    @Test
    public void prices_should_support_references_list_is_empty() throws JSONException {
        final Response prices = target("prices/FR")
                .queryParam("currencyCode", "EUR")
                .queryParam("referenceIds", (String) null)
                .request().accept(MediaType.APPLICATION_JSON).get();

        assertThat(prices.getStatus()).isEqualTo(200);
    }

}
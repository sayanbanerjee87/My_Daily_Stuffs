package com.schneider_electric.dces.pricing.rest;

import com.schneider_electric.dces.pricing.config.JerseyConfig;
import com.schneider_electric.dces.pricing.config.JerseyTestConfig;
import com.schneider_electric.dces.pricing.config.PriceServiceImplMock;
import com.schneider_electric.dces.pricing.i18n.I18nProvider;
import com.schneider_electric.dces.pricing.model.PriceList;
import com.schneider_electric.dces.pricing.model.PriceListType;
import com.schneider_electric.dces.pricing.model.PriceRevision;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.test.JerseyTest;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.xnap.commons.i18n.I18nFactory;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class PriceListResourceImplTest extends JerseyTest {

    @Override
    protected Application configure() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JerseyTestConfig.class);
        context.register(org.springframework.validation.beanvalidation.LocalValidatorFactoryBean.class);
        JerseyConfig config = new JerseyConfig();
        config.property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        return config.property("contextConfig", context);
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
    public void should_get_price_lists(){
        final Response response = target("pricelists").request().get();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    public void should_get_price_list(){
        final Response response = target("pricelists/"+ PriceServiceImplMock.priceListId).request().get();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    public void get_price_list_should_return_404_if_wrong_price_list_id(){
        final Response response = target("pricelists/"+PriceServiceImplMock.wrongPriceListId).request().get();

        assertThat(response.getStatus()).isEqualTo(404);
    }

    @Test
    public void should_create_price_list_fail_if_name_is_blank(){
        final Response response = target("pricelists").request()
                                    .header("Authorization", "Authorization")
                                    .post(Entity.json(new PriceList("FR", "", "Prix France", PriceListType.PUBLIC, "EUR")));

        assertThat(response.getStatus()).isEqualTo(400);
    }

    @Test
    public void should_create_price_list_fail_if_desc_is_blank(){
        final Response response = target("pricelists").request()
                                    .header("Authorization", "Authorization")
                                    .post(Entity.json(new PriceList("FR", "FR", "", PriceListType.PUBLIC, "EUR")));

        assertThat(response.getStatus()).isEqualTo(400);
    }

    @Test
    public void should_create_price_list_fail_if_type_is_null(){
        final Response response = target("pricelists").request()
                                    .header("Authorization", "Authorization")
                                    .post(Entity.json(new PriceList("FR", "FR", "Price France", null, "EUR")));

        assertThat(response.getStatus()).isEqualTo(400);
    }

    @Test
    public void should_create_price_list_fail_if_currency_is_blank(){
        final Response response = target("pricelists").request()
                                    .header("Authorization","Authorization")
                                    .post(Entity.json( new PriceList("FR", "FR", "Price France", PriceListType.PUBLIC, "")) );

        assertThat(response.getStatus()).isEqualTo(400);
    }

    @Test
    public void should_create_price_list(){
        final Response response = target("pricelists").request()
                                    .header("Authorization","Authorization")
                                    .post(Entity.json( new PriceList("0", "PriceList 1", "Price list first", PriceListType.USER, "EUR")) );

        assertThat(response.getStatus()).isEqualTo(201);
        assertThat(response.getLocation().getPath()).isEqualTo("/api/pricelists/0");
    }

    @Test
    public void should_update_price_list(){
        final Response response = target("pricelists/"+PriceServiceImplMock.priceListId).request()
                .header("Authorization", "Authorization")
                .put(Entity.json(new PriceList("FR", "FR", "Prix France", PriceListType.PUBLIC, "EUR")));

        assertThat(response.getStatus()).isEqualTo(204);
    }

    @Test
    public void should_update_price_list_return_409_if_not_acceptable_exception_thrown(){
        final Response response = target("pricelists/"+PriceServiceImplMock.priceListId).request()
                .header("Authorization", "Authorization")
                .put(Entity.json(new PriceList("FR", "FR", "Prix France", PriceListType.USER, "EUR")));

        assertThat(response.getStatus()).isEqualTo(409);
    }

    @Test
    public void should_update_price_list_fail_if_name_is_blank(){
        final Response response = target("pricelists/"+PriceServiceImplMock.priceListId).request()
                .header("Authorization", "Authorization")
                .post(Entity.json(new PriceList("FR", "", "Prix France", PriceListType.PUBLIC, "EUR")));

        assertThat(response.getStatus()).isEqualTo(400);
    }

    @Test
    public void should_update_price_list_fail_if_desc_is_blank(){
        final Response response = target("pricelists/"+PriceServiceImplMock.priceListId).request()
                .header("Authorization","Authorization")
                .post(Entity.json(new PriceList("FR", "FR", "", PriceListType.PUBLIC, "EUR")));

        assertThat(response.getStatus()).isEqualTo(400);
    }

    @Test
    public void should_update_price_list_fail_if_type_is_null(){
        final Response response = target("pricelists/"+PriceServiceImplMock.priceListId).request()
                .header("Authorization","Authorization")
                .post(Entity.json(new PriceList("FR", "FR", "Price France", null, "EUR")));

        assertThat(response.getStatus()).isEqualTo(400);
    }

    @Test
    public void should_update_price_list_fail_if_currency_is_blank(){
        final Response response = target("pricelists/"+PriceServiceImplMock.priceListId).request()
                .header("Authorization","Authorization")
                .post(Entity.json( new PriceList("FR", "FR", "Price France", PriceListType.PUBLIC, "")) );

        assertThat(response.getStatus()).isEqualTo(400);
    }

    @Test
    public void update_price_list_should_return_404_if_wrong_price_list_id(){
        final Response response = target("pricelists/"+PriceServiceImplMock.wrongPriceListId).request()
                .header("Authorization","Authorization")
                .put(Entity.json(new PriceList("FR", "FR", "Prix France", PriceListType.PUBLIC, "EUR")));

        assertThat(response.getStatus()).isEqualTo(404);
    }

    @Test
    public void update_price_list_should_return_404_with_wrong_price_list_id(){
        final Response response = target("pricelists/"+PriceServiceImplMock.wrongPriceListId).request()
                .header("Authorization","Authorization")
                .put(Entity.json(new PriceList("FR", "FR", "Prix France", PriceListType.PUBLIC, "EUR")));

        assertThat(response.getStatus()).isEqualTo(404); // TODO : allow PUT in CORS
    }

    @Test
    public void should_delete_price_list(){
        final Response response = target("pricelists/"+PriceServiceImplMock.priceListId).request()
                .header("Authorization","Authorization")
                .delete();

        assertThat(response.getStatus()).isEqualTo(204);
    }

    @Test
    public void delete_price_list_should_return_404_if_wrong_price_list_id(){
        final Response response = target("pricelists/"+PriceServiceImplMock.wrongPriceListId).request()
                .header("Authorization","Authorization")
                .delete();

        assertThat(response.getStatus()).isEqualTo(404);
    }

    @Test
    public void should_create_price_list_revision_from_file(){

        InputStream stream = getClass().getClassLoader().getResourceAsStream("sample.xls");
        FormDataMultiPart formDataMultiPart = new FormDataMultiPart().field("file", stream, MediaType.TEXT_PLAIN_TYPE)
                                                        .field("from", "2015-01-01")
                                                        .field("to", "2015-03-01")
                                                        .field("sheetIdx", "0")
                                                        .field("contentFirstRowIdx", "1")
                                                        .field("referenceColIdx","0")
                                                        .field("priceColIdx", "3")
                                                        .field("familyColIdx", "5");


        final Response response = target("pricelists/"+PriceServiceImplMock.priceListId).request()
                .header("Authorization", "Authorization")
                    .post(Entity.entity(formDataMultiPart, MediaType.MULTIPART_FORM_DATA));

        assertThat(response.getStatus()).isEqualTo(201);
    }

   @Test
    public void should_create_price_list_revision_from_file__XLSX() throws URISyntaxException {

        URL stream = getClass().getClassLoader().getResource("sample.xlsx");

        FileDataBodyPart fileDataBodyPart = new FileDataBodyPart("file", new File(stream.toURI()), new MediaType("application", "vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        FormDataMultiPart formDataMultiPart = new FormDataMultiPart()
                                                        .field("from", "2015-01-01")
                                                        .field("to", "2015-03-01")
                                                        .field("sheetIdx", "0")
                                                        .field("contentFirstRowIdx", "1")
                                                        .field("referenceColIdx","0")
                                                        .field("priceColIdx", "3")
                                                        .field("familyColIdx", "5");
       formDataMultiPart.bodyPart(fileDataBodyPart);

        final Response response = target("pricelists/"+PriceServiceImplMock.priceListId).request()
                .header("Authorization", "Authorization")
                    .post(Entity.entity(formDataMultiPart, MediaType.MULTIPART_FORM_DATA));

        assertThat(response.getStatus()).isEqualTo(201);
    }

    @Test
    public void should_create_price_list_revision_from_file_notfail_if_differentformat_file(){

        InputStream stream = getClass().getClassLoader().getResourceAsStream("bomformat-pricelist.xls");
        FormDataMultiPart formDataMultiPart = new FormDataMultiPart().field("file", stream, MediaType.TEXT_PLAIN_TYPE)
                                                        .field("from", "2015-01-01")
                                                        .field("to", "2015-03-01")
                                                        .field("sheetIdx", "0")
                                                        .field("contentFirstRowIdx", "4")
                                                        .field("referenceColIdx","3")
                                                        .field("priceColIdx", "8");


        final String response = target("pricelists/"+PriceServiceImplMock.priceListId).request()
                .header("Authorization", "Authorization")
                .post(Entity.entity(formDataMultiPart, MediaType.MULTIPART_FORM_DATA), String.class);

        assertThat(response).contains("\"created\":3");
    }

    @Test
    public void should_create_price_list_revision_from_file_fail_if_contentindex_is_wrong(){

        InputStream stream = getClass().getClassLoader().getResourceAsStream("bomformat-pricelist.xls");
        FormDataMultiPart formDataMultiPart = new FormDataMultiPart().field("file", stream, MediaType.TEXT_PLAIN_TYPE)
                                                        .field("from", "2015-01-01")
                                                        .field("to", "2015-03-01")
                                                        .field("sheetIdx", "0")
                                                        .field("contentFirstRowIdx", "3")
                                                        .field("referenceColIdx","3")
                                                        .field("priceColIdx", "8");


        final Response response = target("pricelists/"+PriceServiceImplMock.priceListId).request()
                .header("Authorization", "Authorization")
                    .post(Entity.entity(formDataMultiPart, MediaType.MULTIPART_FORM_DATA));

        assertThat(response.getStatus()).isEqualTo(400);
    }

    @Test
    public void should_create_price_list_revision_from_file_fail_if_ref_without_value(){

        InputStream stream = getClass().getClassLoader().getResourceAsStream("reference_without_value.xls");
        FormDataMultiPart formDataMultiPart = new FormDataMultiPart().field("file", stream, MediaType.TEXT_PLAIN_TYPE)
                                                        .field("from", "2015-01-01")
                                                        .field("to", "2015-03-01")
                                                        .field("sheetIdx", "0")
                                                        .field("contentFirstRowIdx", "1")
                                                        .field("referenceColIdx","0")
                                                        .field("priceColIdx", "1");


        final Response response = target("pricelists/"+PriceServiceImplMock.priceListId).request()
                .header("Authorization", "Authorization")
                    .post(Entity.entity(formDataMultiPart, MediaType.MULTIPART_FORM_DATA));

        assertThat(response.getStatus()).isEqualTo(400);
    }

    @Test
    public void should_create_price_list_revision_from_file_return_0_if_refindex_is_wrong() throws JSONException {

        InputStream stream = getClass().getClassLoader().getResourceAsStream("bomformat-pricelist.xls");
        FormDataMultiPart formDataMultiPart = new FormDataMultiPart().field("file", stream, MediaType.TEXT_PLAIN_TYPE)
                                                        .field("from", "2015-01-01")
                                                        .field("to", "2015-03-01")
                                                        .field("sheetIdx", "0")
                                                        .field("contentFirstRowIdx", "4")
                                                        .field("referenceColIdx","2")
                                                        .field("priceColIdx", "8");


        final String response = target("pricelists/"+PriceServiceImplMock.priceListId).request()
                .header("Authorization", "Authorization")
                    .post(Entity.entity(formDataMultiPart, MediaType.MULTIPART_FORM_DATA), String.class);

        assertThat(response).contains("\"created\":0");
    }

    @Test
    public void should_create_price_list_revision_from_file_fail_if_sheetIdx_is_null(){

        InputStream stream = getClass().getClassLoader().getResourceAsStream("sample.xls");
        FormDataMultiPart formDataMultiPart = new FormDataMultiPart().field("file", stream, MediaType.TEXT_PLAIN_TYPE)
                .field("from", "2015-01-01")
                .field("to", "2015-03-01")
                .field("contentFirstRowIdx", "1")
                .field("referenceColIdx","0")
                .field("priceColIdx", "3")
                .field("familyColIdx", "5");


        final Response response = target("pricelists/"+PriceServiceImplMock.priceListId).request()
                .header("Authorization", "Authorization")
                .post(Entity.entity(formDataMultiPart, MediaType.MULTIPART_FORM_DATA));

        assertThat(response.getStatus()).isEqualTo(400);
    }

    @Test
    public void should_create_price_list_revision_from_file_fail_if_contentFirstRowIdx_is_null(){

        InputStream stream = getClass().getClassLoader().getResourceAsStream("sample.xls");
        FormDataMultiPart formDataMultiPart = new FormDataMultiPart().field("file", stream, MediaType.TEXT_PLAIN_TYPE)
                .field("from", "2015-01-01")
                .field("to", "2015-03-01")
                .field("sheetIdx", "0")
                .field("referenceColIdx","0")
                .field("priceColIdx", "3")
                .field("familyColIdx", "5");


        final Response response = target("pricelists/"+PriceServiceImplMock.priceListId).request()
                .header("Authorization", "Authorization")
                .post(Entity.entity(formDataMultiPart, MediaType.MULTIPART_FORM_DATA));

        assertThat(response.getStatus()).isEqualTo(400);
    }

    @Test
    public void should_create_price_list_revision_from_file_fail_if_referenceColIdx_is_null(){

        InputStream stream = getClass().getClassLoader().getResourceAsStream("sample.xls");
        FormDataMultiPart formDataMultiPart = new FormDataMultiPart().field("file", stream, MediaType.TEXT_PLAIN_TYPE)
                .field("from", "2015-01-01")
                .field("to", "2015-03-01")
                .field("sheetIdx", "0")
                .field("contentFirstRowIdx", "1")
                .field("priceColIdx", "3")
                .field("familyColIdx", "5");


        final Response response = target("pricelists/"+PriceServiceImplMock.priceListId).request()
                .header("Authorization", "Authorization")
                .post(Entity.entity(formDataMultiPart, MediaType.MULTIPART_FORM_DATA));

        assertThat(response.getStatus()).isEqualTo(400);
    }

    @Test
    public void should_create_price_list_revision_from_file_fail_if_priceColIdx_is_null(){

        InputStream stream = getClass().getClassLoader().getResourceAsStream("sample.xls");
        FormDataMultiPart formDataMultiPart = new FormDataMultiPart().field("file", stream, MediaType.TEXT_PLAIN_TYPE)
                .field("from", "2015-01-01")
                .field("to", "2015-03-01")
                .field("sheetIdx", "0")
                .field("contentFirstRowIdx", "1")
                .field("referenceColIdx","0")
                .field("familyColIdx", "5");


        final Response response = target("pricelists/"+PriceServiceImplMock.priceListId).request()
                .header("Authorization", "Authorization")
                .post(Entity.entity(formDataMultiPart, MediaType.MULTIPART_FORM_DATA));

        assertThat(response.getStatus()).isEqualTo(400);
    }

    @Test
    public void should_create_price_list_revision_from_file_fail_if_file_is_null(){

        FormDataMultiPart formDataMultiPart = new FormDataMultiPart()
                .field("from", "2015-01-01")
                .field("to", "2015-03-01")
                .field("sheetIdx", "0")
                .field("contentFirstRowIdx", "1")
                .field("referenceColIdx","0")
                .field("priceColIdx", "3")
                .field("familyColIdx", "5");


        final Response response = target("pricelists/"+PriceServiceImplMock.priceListId).request()
                .header("Authorization", "Authorization")
                .post(Entity.entity(formDataMultiPart, MediaType.MULTIPART_FORM_DATA));

        assertThat(response.getStatus()).isEqualTo(400);
    }

    @Test
    public void create_price_list_revision_should_return_404_if_wrong_price_list_id(){

        InputStream stream = getClass().getClassLoader().getResourceAsStream("sample.xls");
        FormDataMultiPart formDataMultiPart = new FormDataMultiPart().field("file", stream, MediaType.TEXT_PLAIN_TYPE)
                .field("from", "2015-01-01")
                .field("to", "2015-03-01")
                .field("sheetIdx", "0")
                .field("contentFirstRowIdx", "1")
                .field("referenceColIdx","0")
                .field("priceColIdx", "3")
                .field("familyColIdx", "5");


        final Response response = target("pricelists/"+PriceServiceImplMock.wrongPriceListId).request()
                .header("Authorization", "Authorization")
                .post(Entity.entity(formDataMultiPart, MediaType.MULTIPART_FORM_DATA));

        assertThat(response.getStatus()).isEqualTo(404);
    }

    @Test
    public void should_create_price_list_revision_from_json(){

        final Response response = target("pricelists/"+PriceServiceImplMock.priceListId).request()
                .header("Authorization", "Authorization")
                .post(Entity.json(new PriceRevision(PriceServiceImplMock.priceRevisionId,new Date(),new Date(), Collections.EMPTY_LIST)));

        assertThat(response.getStatus()).isEqualTo(201);
        assertThat(response.getLocation().getPath()).isEqualTo("/api/pricelists/"+PriceServiceImplMock.priceListId+"/"+PriceServiceImplMock.priceRevisionId);
    }

    @Test
    public void create_price_list_revision_from_json_should_return_404_if_wrong_price_list_id(){

        final Response response = target("pricelists/"+PriceServiceImplMock.wrongPriceListId).request()
                .header("Authorization", "Authorization")
                .post(Entity.json(new PriceRevision(0,new Date(),new Date(), Collections.EMPTY_LIST)));

        assertThat(response.getStatus()).isEqualTo(404);
    }

    @Test
    public void should_delete_price_list_revision(){
        final Response response = target("pricelists/"+PriceServiceImplMock.priceListId+"/"+PriceServiceImplMock.priceRevisionId).request()
                .header("Authorization","Authorization")
                .delete();

        assertThat(response.getStatus()).isEqualTo(204);
    }

    @Test
    public void should_get_price_list_revision(){
        final Response response = target("pricelists/"+PriceServiceImplMock.priceListId+"/"+PriceServiceImplMock.priceRevisionId).request().get();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
     public void get_price_list_revision_should_return_404_if_wrong_price_list_id(){
        final Response response = target("pricelists/"+PriceServiceImplMock.wrongPriceListId+"/"+PriceServiceImplMock.priceListId).request().get();

        assertThat(response.getStatus()).isEqualTo(404);
    }

    @Test
    public void get_price_list_revision_should_return_404_if_wrong_price_list_revision_id(){
        final Response response = target("pricelists/"+PriceServiceImplMock.priceListId+"/"+PriceServiceImplMock.wrongPriceListId).request().get();

        assertThat(response.getStatus()).isEqualTo(404);
    }

    @Test
    public void should_get_prices(){
        final Response response = target("prices")
                .queryParam("refIds", Arrays.asList("ref1", "ref2", "ref3"))
                .queryParam("priceListId", PriceServiceImplMock.priceListId)
                .request()
                .header("Authorization", "Authorization")
                .get();

        assertThat(response.getStatus()).isEqualTo(200);
    }

}

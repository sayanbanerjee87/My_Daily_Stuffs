package com.schneider_electric.dces.pricing.rest;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.schneider_electric.dces.pricing.model.Discount;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.test.JerseyTest;
import org.joda.time.LocalDate;
import org.json.JSONException;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.schneider_electric.dces.pricing.config.JerseyConfig;
import com.schneider_electric.dces.pricing.config.JerseyTestConfig;

public class DiscountResourceImplTest extends JerseyTest {
	
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

    @Test
    public void shouldSaveDiscounts_forUser() {
        Collection<Discount> discounts = newArrayList(
                new Discount("FR", "F1", 0.2d, new LocalDate(2014, 1, 1).toDate()),
                new Discount("EN", "F2", 0.3d, new LocalDate(2014, 6, 1).toDate())
        );
        Response response = target("discount")
                .request().accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "123")
                .put(Entity.json(discounts), Response.class);

        assertThat(response.getStatus()).isEqualTo(204);
    }

    @Test
    public void shouldSaveDiscounts_forUser_withNullOrEmptyDiscountValues() {
        String discountsAsJson = "[" +
                "{\"familyCode\":\"F1\",\"countryCode\":\"FR\",\"validityStart\":\"2014-01-01\"}," +
                "{\"familyCode\":\"F1\",\"countryCode\":\"FR\",\"value\":null,\"validityStart\":\"2014-01-01\"}," +
                "{\"familyCode\":\"F1\",\"countryCode\":\"FR\",\"value\":\"\",\"validityStart\":\"2014-01-01\"}" +
            "]";
        Response response = target("discount")
                .request().accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "123")
                .put(Entity.json(discountsAsJson), Response.class);

        assertThat(response.getStatus()).isEqualTo(204);
    }

    @Test
    public void shouldNotsaveDiscounts_forUnvalidatedDiscounts() throws IOException {
        Collection<Discount> discounts = newArrayList(
                new Discount("FR", "1234", null, 0.2d, new LocalDate(2014, 1, 1).toDate()),
                new Discount(null, "1234", "F", 0.2d, new LocalDate(2014, 1, 1).toDate()),
                new Discount("FR", "1234", "F", 10d, new LocalDate(2014, 1, 1).toDate()),
                new Discount("FR", "1234", "F", -0.2d, new LocalDate(2014, 1, 1).toDate()),
                new Discount("FR", "1234", "F", 0.2d, null)
        );
        Response response = target("discount")
                .request().accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "123")
                .put(Entity.json(discounts), Response.class);

        assertThat(response.getStatus()).isEqualTo(400);

        String validationErrorsJson = CharStreams.toString(new InputStreamReader((ByteArrayInputStream) response.getEntity(), Charsets.UTF_8));
        assertThat(validationErrorsJson).contains(
                "\"messageTemplate\":\"{org.hibernate.validator.constraints.NotBlank.message}\",\"path\":\"DiscountResourceImpl.setDiscounts.arg0[0].familyCode\"",
                "\"messageTemplate\":\"{com.schneider_eletric.constraints.Percentage.message}\",\"path\":\"DiscountResourceImpl.setDiscounts.arg0[2].value\"",
                "\"messageTemplate\":\"{com.schneider_eletric.constraints.Percentage.message}\",\"path\":\"DiscountResourceImpl.setDiscounts.arg0[3].value\"",
                "\"messageTemplate\":\"{javax.validation.constraints.NotNull.message}\",\"path\":\"DiscountResourceImpl.setDiscounts.arg0[4].validityStart\""
        );

    }
}

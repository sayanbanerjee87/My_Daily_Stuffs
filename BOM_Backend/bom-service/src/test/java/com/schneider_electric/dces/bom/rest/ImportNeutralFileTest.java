package com.schneider_electric.dces.bom.rest;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.Resources;
import org.glassfish.jersey.message.internal.FileProvider;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.FileCopyUtils;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.Response;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

/**
 * User: FDU3285
 * Date: 07/05/2015
 * Time: 09:15
 */
public class ImportNeutralFileTest extends JerseyTest {

    @Override
    protected Application configure() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JerseyConfigTest.class);
        context.scan("com.schneider_electric.dces.bom");
        context.register(org.springframework.validation.beanvalidation.LocalValidatorFactoryBean.class);
        context.register(ResourceBundleMessageSource.class);
        ResourceBundleMessageSource bean = context.getBean(ResourceBundleMessageSource.class);
        bean.setBasenames("i18n/messages");
        bean.setDefaultEncoding("UTF-8");
        JerseyConfigTest rc = new JerseyConfigTest();
        // Now you can expect validation errors to be sent to the client.
        rc.property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        return rc.property("contextConfig", context);
    }

    /**
     * PACE-1108 : NPE if neutral file does not reference part
     * @throws IOException
     */
    @Test
    public void import_shouldNotFailWithA_NPE_ifPartAssembly_ContainsAnInvalidReference() throws IOException {
        URL url = Resources.getResource("NotWorking-bom_input.xml");
        String xml = Resources.toString(url, Charsets.UTF_8);
        Response response = target("import")
                .queryParam("addDescriptions", true)
                .queryParam("addPrices", true)
                .queryParam("priceCountryCode", "ES")
                .queryParam("language", "fr-FR")
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.xml(xml), Response.class);

        assertThat(response.getStatus()).isEqualTo(400);

        ByteArrayInputStream in = (ByteArrayInputStream) response.getEntity();
        String result = CharStreams.toString(new InputStreamReader(in, "UTF-8"));
        assertThat(result).isEqualTo("Unable to import neutral file: 'Part' element is missing or invalid for PartAssembly.");
    }

    /**
     * PACE-1108 : NPE if neutral file does not reference part
     * @throws IOException
     */
    @Test
    public void import_shouldFilterOnSwitchboard() throws IOException {
        URL url = Resources.getResource("multiDashboardNeutralFile.xml");
        String xml = Resources.toString(url, Charsets.UTF_8);
        Response response = target("import")
                .queryParam("equipments", "EQ001")
                .queryParam("addDescriptions", false)
                .queryParam("addPrices", false)
                .queryParam("priceCountryCode", "ES")
                .queryParam("language", "fr-FR")
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.xml(xml), Response.class);

        assertThat(response.getStatus()).isEqualTo(200);

        ByteArrayInputStream in = (ByteArrayInputStream) response.getEntity();
        String result = CharStreams.toString(new InputStreamReader(in, "UTF-8"));
        assertThat(result).contains("\"content\":[{\"product\":{\"reference\":\"PRA10202\",\"quantity\":1,\"products\":[]},\"tags\":{\"equipment\":\"Tableau\",\"type\":\"enclosure\"}}]");
    }

}
package com.schneider_electric.dces.bom.rest;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.Resources;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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

public class ExportResourceTest extends JerseyTest {

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

    @Test
    public void export_shouldDeliver_xlsFile_for_givenBom() throws IOException {
        URL url = Resources.getResource("bom-sample.json");
        String json = Resources.toString(url, Charsets.UTF_8);
        Response response = target("export")
                .request()
                .accept(MediaType.APPLICATION_OCTET_STREAM)
                .post(Entity.json(json), Response.class);

        ByteArrayInputStream in = (ByteArrayInputStream) response.getEntity();
        if (in != null) {
            FileProvider fileProvider = new FileProvider();
            File f = fileProvider.readFrom(File.class, null, null, MediaType.APPLICATION_OCTET_STREAM_TYPE, new MultivaluedHashMap<String, String>(), in);
            FileCopyUtils.copy(f, new File("bom.xls"));
        }

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getHeaderString("Content-Disposition")).isEqualTo("attachment; filename=\"bom.xls\"");
    }

    @Test
    public void export_shouldDeliver_xlsFile_for_givenBom_with_ranges_in_total() throws IOException {
        URL url = Resources.getResource("bom-sample-range.json");
        String json = Resources.toString(url, Charsets.UTF_8);
        Response response = target("export")
                .request()
                .accept(MediaType.APPLICATION_OCTET_STREAM)
                .post(Entity.json(json), Response.class);

        ByteArrayInputStream in = (ByteArrayInputStream) response.getEntity();
        if (in != null) {
            FileProvider fileProvider = new FileProvider();
            File f = fileProvider.readFrom(File.class, null, null, MediaType.APPLICATION_OCTET_STREAM_TYPE, new MultivaluedHashMap<String, String>(), in);
            FileCopyUtils.copy(f, new File("bom.xls"));
        }

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getHeaderString("Content-Disposition")).isEqualTo("attachment; filename=\"bom.xls\"");
    }

    @Test
    public void export_shouldDeliver_xlsFile_for_givenBom_with_sub_products() throws IOException {
        URL url = Resources.getResource("bom-sample-range-sub-products.json");
        String json = Resources.toString(url, Charsets.UTF_8);
        Response response = target("export")
                .request()
                .accept(MediaType.APPLICATION_OCTET_STREAM)
                .post(Entity.json(json), Response.class);

        ByteArrayInputStream in = (ByteArrayInputStream) response.getEntity();
        if (in != null) {
            FileProvider fileProvider = new FileProvider();
            File f = fileProvider.readFrom(File.class, null, null, MediaType.APPLICATION_OCTET_STREAM_TYPE, new MultivaluedHashMap<String, String>(), in);
            FileCopyUtils.copy(f, new File("bom.xls"));
        }

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getHeaderString("Content-Disposition")).isEqualTo("attachment; filename=\"bom.xls\"");
    }

    @Test
    public void export_shouldFail_ifJsonData_doesNotSatisfyHeaderConstraints() throws IOException {
        URL url = Resources.getResource("bom-validation-fail-header-wrong.json");
        String json = Resources.toString(url, Charsets.UTF_8);
        Response response = target("export")
                .request()
                .accept(MediaType.APPLICATION_OCTET_STREAM)
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json(json), Response.class);

        assertThat(response.getStatus()).isEqualTo(400);
        String validationErrorsJson = CharStreams.toString(new InputStreamReader((ByteArrayInputStream) response.getEntity(), Charsets.UTF_8));
        assertThat(validationErrorsJson).contains(
                "\"messageTemplate\":\"{javax.validation.constraints.NotNull.message}\",\"path\":\"ExportResource.export.arg0.header.columns[1].label\"",
                "\"messageTemplate\":\"{javax.validation.constraints.NotNull.message}\",\"path\":\"ExportResource.export.arg0.header.currency\"",
                "\"messageTemplate\":\"{javax.validation.constraints.NotNull.message}\",\"path\":\"ExportResource.export.arg0.header.groups[0].tag\"",
                "\"messageTemplate\":\"{javax.validation.constraints.NotNull.message}\",\"path\":\"ExportResource.export.arg0.header.language\"",
                "\"messageTemplate\":\"{javax.validation.constraints.NotNull.message}\",\"path\":\"ExportResource.export.arg0.header.columns[0].id\""
        );
    }

    @Test
    public void export_shouldFail_ifJsonData_doesNotSatisfyContentConstraints() throws IOException {
        URL url = Resources.getResource("bom-validation-fail-content-wrong.json");
        String json = Resources.toString(url, Charsets.UTF_8);
        Response response = target("export")
                .request()
                .accept(MediaType.APPLICATION_OCTET_STREAM)
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json(json), Response.class);

        assertThat(response.getStatus()).isEqualTo(400);
        String validationErrorsJson = CharStreams.toString(new InputStreamReader((ByteArrayInputStream) response.getEntity(), Charsets.UTF_8));
        assertThat(validationErrorsJson).contains(
                "\"messageTemplate\":\"{javax.validation.constraints.NotNull.message}\",\"path\":\"ExportResource.export.arg0.bom.currencyCode\""
        );
    }

    @Test
    public void shouldExport_haveThesameOrder_thanTheGivenJson() throws Exception {
        URL url = Resources.getResource("bom-order-sample.json");
        String json = Resources.toString(url, Charsets.UTF_8);
        Response response = target("export")
                .request()
                .accept(MediaType.APPLICATION_OCTET_STREAM)
                .post(Entity.json(json), Response.class);

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getHeaderString("Content-Disposition")).isEqualTo("attachment; filename=\"bom.xls\"");

        ByteArrayInputStream in = (ByteArrayInputStream) response.getEntity();
        if (in != null) {
            Workbook workbook = new HSSFWorkbook(in);
            Sheet sheet = workbook.getSheetAt(0);
            assertThat(sheet.getRow(3).getCell(2).getStringCellValue()).isEqualTo("QA-1");
            assertThat(sheet.getRow(4).getCell(2).getStringCellValue()).isEqualTo("QA-2");
        }
    }

    @Test
    public void shouldExportAsCsv_exportBomAsCsvFile() throws Exception {
        URL url = Resources.getResource("bom-order-sample.json");
        String json = Resources.toString(url, Charsets.UTF_8);
        Response response = target("export")
                .request()
                .accept("text/csv")
                .post(Entity.json(json), Response.class);

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getHeaderString("Content-Disposition")).isEqualTo("attachment; filename=\"bom.csv\"");
    }

    @Test
    public void shouldExportAsXls_acceptBomWithMessages_inHeader() throws Exception {
        URL url = Resources.getResource("bom-sample-with-messages.json");
        String json = Resources.toString(url, Charsets.UTF_8);
        Response response = target("export")
                .request()
                .accept(MediaType.APPLICATION_OCTET_STREAM)
                .post(Entity.json(json), Response.class);

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getHeaderString("Content-Disposition")).isEqualTo("attachment; filename=\"bom.xls\"");
    }

    @Test
    public void shouldExportAsXls_acceptBomWithQuantities() throws Exception {
        URL url = Resources.getResource("bom-sample-quantity.json");
        String json = Resources.toString(url, Charsets.UTF_8);
        Response response = target("export")
                .request()
                .accept(MediaType.APPLICATION_OCTET_STREAM)
                .post(Entity.json(json), Response.class);

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getHeaderString("Content-Disposition")).isEqualTo("attachment; filename=\"bom.xls\"");
    }

    @Test
    public void shouldExportAsXls_acceptBom_WithProducts_withoutTag() throws Exception {
        URL url = Resources.getResource("bom-sample-others.json");
        String json = Resources.toString(url, Charsets.UTF_8);
        Response response = target("export")
                .request()
                .accept(MediaType.APPLICATION_OCTET_STREAM)
                .post(Entity.json(json), Response.class);

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getHeaderString("Content-Disposition")).isEqualTo("attachment; filename=\"bom.xls\"");
    }


    @Test
    public void export_shouldDeliver_xlsFile_for_accept_json_requested() throws IOException {
        URL url = Resources.getResource("bom-sample.json");
        String json = Resources.toString(url, Charsets.UTF_8);
        Response response = target("export")
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json(json), Response.class);

        ByteArrayInputStream in = (ByteArrayInputStream) response.getEntity();
        if (in != null) {
            FileProvider fileProvider = new FileProvider();
            File f = fileProvider.readFrom(File.class, null, null, MediaType.APPLICATION_OCTET_STREAM_TYPE, new MultivaluedHashMap<String, String>(), in);
            FileCopyUtils.copy(f, new File("bom.xls"));
        }

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getHeaderString("Content-Disposition")).isEqualTo("attachment; filename=\"bom.xls\"");
    }

    @Test
    public void export_shouldFail_if_accept_not_recognized() throws IOException {
        URL url = Resources.getResource("bom-sample.json");
        String json = Resources.toString(url, Charsets.UTF_8);
        Response response = target("export")
                .request()
                .accept(MediaType.APPLICATION_XML)
                .post(Entity.json(json), Response.class);

        assertThat(response.getStatus()).isEqualTo(Response.Status.NOT_ACCEPTABLE.getStatusCode());
    }

    @Test
    public void exportForExcel_canTake_filenameAsParameter() throws IOException {
        URL url = Resources.getResource("bom-sample.json");
        String json = Resources.toString(url, Charsets.UTF_8);
        Response response = target("export")
                .queryParam("filename", "mynameIlike.xls")
                .request()
                .accept("application/vnd.ms-excel")
                .post(Entity.json(json), Response.class);

        assertThat(response.getHeaderString("Content-Disposition")).isEqualTo("attachment; filename=\"mynameIlike.xls\"");
    }

    @Test
    public void exportForCsv_canTake_filenameAsParameter() throws IOException {
        URL url = Resources.getResource("bom-sample.json");
        String json = Resources.toString(url, Charsets.UTF_8);
        Response response = target("export")
                .queryParam("filename", "mynameIlike.csv")
                .request()
                .accept("text/csv")
                .post(Entity.json(json), Response.class);

        assertThat(response.getHeaderString("Content-Disposition")).isEqualTo("attachment; filename=\"mynameIlike.csv\"");
    }
}
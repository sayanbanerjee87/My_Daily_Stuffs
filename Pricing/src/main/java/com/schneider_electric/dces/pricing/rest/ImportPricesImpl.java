package com.schneider_electric.dces.pricing.rest;

import com.google.common.io.Resources;
import com.schneider_electric.dces.pricing.model.DiscountFamily;
import com.schneider_electric.dces.pricing.model.Price;
import com.schneider_electric.dces.pricing.service.PriceService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.Authorization;
import com.wordnik.swagger.annotations.AuthorizationScope;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Component
@Path("/")
@Api(value = "pricesImport", description = "Prices import/export API")
public class ImportPricesImpl {

    protected final Log logger = LogFactory.getLog(getClass());
	
	private PriceService priceService;

    @Autowired
	public ImportPricesImpl(PriceService priceService) {
        this.priceService = priceService;
    }

    @GET
    @Produces({MediaType.APPLICATION_OCTET_STREAM, MediaType.TEXT_HTML})
    @Path("/prices/{priceList}")
    @ApiOperation(value = "Get prices excel file for a country",
    notes = "Returns an excel file of the prices for a given country<br/><br/>" +
            "Query path is used to specify the country (ISO code):<br/>" +
            "<b>Sample:</b><br/>" +
            "<pre>/prices/FR</pre><br/>",
    authorizations = @Authorization(value = "token", scopes = @AuthorizationScope(scope = "getPrices", description = "Get prices excel file for a country")),
    response = Response.class)
    public Response getPrices(@PathParam("priceList") String priceList) throws IOException {
        final HSSFWorkbook workbook = new HSSFWorkbook(Resources.getResource("prices.xls").openStream());
        writePrices(workbook, this.priceService.getPrices(priceList));
        writeFamilies(workbook, this.priceService.loadFamilies(priceList, new Date()));
        StreamingOutput file = new StreamingOutput() {
            @Override
            public void write(OutputStream outputStream) throws IOException, WebApplicationException {
                workbook.write(outputStream);
            }
        };
        return  Response.ok(file, MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=\"prices.xls\"")
                .build();
    }

    private void writeFamilies(HSSFWorkbook workbook, Collection<DiscountFamily> families) {
        HSSFSheet sheet = workbook.getSheet("Families");
        HSSFCreationHelper createHelper = workbook.getCreationHelper();
        HSSFCellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(
                createHelper.createDataFormat().getFormat("yyyy-MM-dd")
        );
        int rownum = 1;
        for (DiscountFamily family : families) {
            HSSFRow row = sheet.createRow(rownum++);
            writeCell(row, family.getCode(), 0);
            writeCell(row, family.getDescription(), 1);
        }
    }

    private void writePrices(HSSFWorkbook workbook, Set<Price> prices) {
        HSSFSheet sheet = workbook.getSheet("Prices");
        int rownum = 1;
        for (Price price : prices) {
            HSSFRow row = sheet.createRow(rownum++);
            writeCell(row, price.getReference(), 0);
            writeCell(row, price.getValue(), 1);
            writeCell(row, price.getFamilyCode(), 2);
        }
    }

    private void writeCell(Row row, String value, int column) {
        Cell cell = row.createCell(column);
        cell.setCellType(Cell.CELL_TYPE_STRING);
        cell.setCellValue(value);
    }

    private void writeCell(Row row, Double value, int column) {
        Cell cell = row.createCell(column);
        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
        cell.setCellValue(value);
    }

}

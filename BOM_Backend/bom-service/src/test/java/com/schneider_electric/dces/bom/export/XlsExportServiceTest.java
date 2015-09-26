package com.schneider_electric.dces.bom.export;

import com.schneider_electric.dces.bom.domain.*;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

public class XlsExportServiceTest {

    @Test
    public void shouldGenerateSimpleXls_fromBom() throws IOException {
        BomViewHeader header = new BomViewHeaderBuilder("€", "en")
                .withColumn("reference", "Reference", ColumnType.String)
                .withColumn("description", "Description", ColumnType.String)
                .withColumn("quantity", "Qty", ColumnType.Number)
                .withColumn("discount", "Discount", ColumnType.Percentage)
                .withColumn("unitPrice", "Unit Price", ColumnType.Price)
                .withColumn("UNIT_NET_PRICE", "Unit Net Price", ColumnType.Price)
                .withColumn("NET_PRICE", "Net Price", ColumnType.Price, true)
                .withGroup("switchboard", true, true)
                .withGroup("type", true, false)
                .build();

        Product packProduct = new Product("PRA13814", "PRA13814 extra ...");
        packProduct.addProduct(new Product("PRA13811", "PRA13811 Desc", 2, 225d));
        List<TaggedProduct> products = new TaggedProductBuilder()
                .withProduct("PRA15424", 1, 0.20, 45d, "PRA15424 Desc").withTag("type", "door").withTag("switchboard", "Switchboard 1")
                .withProduct("PRA15423", 2, 0.20, 60d, "PRA15423 Desc").withTag("type", "door").withTag("switchboard", "Switchboard 1")
                .withProduct("A9R60225", 4, 0.10, 25d, "A9R60225 Desc").withTag("type", "multi9").withTag("switchboard", "Switchboard 2")
                .withProduct(
                        new ProductBuilder("A9R60225B", "A9R10000 Desc")
                                .withProduct("A9R60220", 2, 0.10, 45d, "A9R60225 Desc")
                                .withProduct("A9R60221", 4, 0.10, 75d, "A9R60225 Desc").build()
                )
                .withTag("type", "enclosure").withTag("switchboard", "Switchboard 2")
                .withProduct("A9R60225", 4, 0.10, 25d, "A9R60225 Desc").withTag("type", "multi9").withTag("switchboard", "Switchboard 1")
                .build();

        XlsExportService bomExportService = new XlsExportService(header, new XlsBomStyleFactory());
        Workbook workbook = bomExportService.exportAsXls(new BomExportView(header, new BomModel("EUR", "en", products)));

        assertThat(workbook).isNotNull();
        Sheet sheet = workbook.getSheetAt(0);
        assertThat(sheet.getSheetName()).isEqualTo("Bill of Materials");

        Row row = sheet.getRow(1);
        assertThat(row.getCell(1).getStringCellValue()).isEqualTo("Bill of Materials");

        checkRow(sheet.getRow(3), "PRA15424", "PRA15424 Desc", 1, 0.2, 45, 36, 36);
        checkRow(sheet.getRow(4), "PRA15423", "PRA15423 Desc", 2, 0.2, 60, 48, 96);

        Cell additionalInformationCell = sheet.getRow(25).getCell(CellReference.convertColStringToIndex("B"));
        assertThat(additionalInformationCell.getStringCellValue()).isEqualTo("All prices and discounts are only indicative information");
        assertThat(sheet.getMergedRegion(0)).isNotNull();
        assertThat(sheet.getMergedRegion(0).getFirstRow()).isEqualTo(25);
        assertThat(sheet.getMergedRegion(0).getLastRow()).isEqualTo(25);
        assertThat(sheet.getMergedRegion(0).getFirstColumn()).isEqualTo(1);
        assertThat(sheet.getMergedRegion(0).getLastColumn()).isEqualTo(10);

//        For visual testing
//        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
//        workbook.write(fileOut);
//        fileOut.close();
    }

    @Test
    public void shouldGenerateSimpleXls_fromBom_with2DigitForDiscounts() throws IOException {
        BomViewHeader header = new BomViewHeaderBuilder("€", "en")
                .withColumn("reference", "Reference", ColumnType.String)
                .withColumn("description", "Description", ColumnType.String)
                .withColumn("quantity", "Qty", ColumnType.Number)
                .withColumn("discount", "Discount", ColumnType.Percentage)
                .withColumn("unitPrice", "Unit Price", ColumnType.Price)
                .withColumn("UNIT_NET_PRICE", "Unit Net Price", ColumnType.Price)
                .withColumn("NET_PRICE", "Net Price", ColumnType.Price, true)
                .withGroup("switchboard", true, true)
                .withGroup("type", true, false)
                .build();

        List<TaggedProduct> products = new TaggedProductBuilder()
                .withProduct("A9R60225", 4, 0.10568, 25d, "A9R60225 Desc").withTag("type", "multi9").withTag("switchboard", "Switchboard 1")
                .build();

        XlsExportService bomExportService = new XlsExportService(header, new XlsBomStyleFactory());
        Workbook workbook = bomExportService.exportAsXls(new BomExportView(header, new BomModel("EUR", "en", products)));

        assertThat(workbook).isNotNull();
        Sheet sheet = workbook.getSheetAt(0);
        assertThat(sheet.getSheetName()).isEqualTo("Bill of Materials");

        Row row = sheet.getRow(3);
        Cell cell = row.getCell(CellReference.convertColStringToIndex("G"));
        assertThat(cell.getCellStyle().getDataFormatString()).isEqualTo("0.## %");

//        For visual testing
//        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
//        workbook.write(fileOut);
//        fileOut.close();
    }

    private void checkRow(Row row, String ref, String desc, int qty, double discount, int unitPrice, int unitNetPrice, int netPrice) {
        assertThat(row.getCell(CellReference.convertColStringToIndex("D")).getStringCellValue()).isEqualTo(ref);
        assertThat(row.getCell(CellReference.convertColStringToIndex("E")).getStringCellValue()).isEqualTo(desc);
        assertThat(row.getCell(CellReference.convertColStringToIndex("F")).getNumericCellValue()).isEqualTo(qty);
        assertThat(row.getCell(CellReference.convertColStringToIndex("G")).getNumericCellValue()).isEqualTo(discount);
        assertThat(row.getCell(CellReference.convertColStringToIndex("H")).getNumericCellValue()).isEqualTo(unitPrice);
        assertThat(row.getCell(CellReference.convertColStringToIndex("I")).getNumericCellValue()).isEqualTo(unitNetPrice);
        assertThat(row.getCell(CellReference.convertColStringToIndex("J")).getNumericCellValue()).isEqualTo(netPrice);
    }

    @Test
    public void shouldGenerateSimpleXls_fromBom_withI18nSupport() throws IOException {
        BomViewHeader header = new BomViewHeaderBuilder("€", "es")
                .withColumn("reference", "Reference", ColumnType.String)
                .withColumn("description", "Description", ColumnType.String)
                .withColumn("quantity", "Qty", ColumnType.Number)
                .withColumn("discount", "Discount", ColumnType.Percentage)
                .withColumn("unitPrice", "Unit Price", ColumnType.Price)
                .withColumn("NET_PRICE", "Net Price", ColumnType.Price, true)
                .withGroup("switchboard", true, true)
                .withGroup("type", true, false)
                .build();

        List<TaggedProduct> products = new TaggedProductBuilder()
                .withProduct("PRA15424", 1, 0.20, 45d, "PRA15424 Desc").withTag("type", "door").withTag("switchboard", "Switchboard 1")
                .build();

        XlsExportService bomExportService = new XlsExportService(header, new XlsBomStyleFactory());
        Workbook workbook = bomExportService.exportAsXls(new BomExportView(header, new BomModel("EUR", null, products)));

        assertThat(workbook).isNotNull();
        Sheet sheet = workbook.getSheetAt(0);
        assertThat(sheet.getSheetName()).isEqualTo("Listado materiales");

        Row row = sheet.getRow(1);
        assertThat(row.getCell(1).getStringCellValue()).isEqualTo("Listado materiales");
    }

    @Test
    public void shouldGenerateSimpleXls_fromBom_withoutGroups() throws IOException {
        BomViewHeader header = new BomViewHeaderBuilder("€", "en")
                .withColumn("reference", "Reference", ColumnType.String)
                .withColumn("description", "Description", ColumnType.String)
                .withColumn("quantity", "Qty", ColumnType.Number)
                .withColumn("discount", "Discount", ColumnType.Percentage)
                .withColumn("unitPrice", "Unit Price", ColumnType.Price)
                .withColumn("NET_PRICE", "Net Price", ColumnType.Price, true)
                .build();

        Product packProduct = new Product("PRA13814", "PRA13814 extra ...");
        packProduct.addProduct(new Product("PRA13811", "PRA13811 Desc", 2, 225d));
        List<TaggedProduct> products = new TaggedProductBuilder()
                .withProduct("PRA15424", 1, 0.20, 45d, "PRA15424 Desc").withTag("type", "door").withTag("switchboard", "Switchboard 1")
                .withProduct("PRA15423", 2, 0.20, 60d, "PRA15423 Desc").withTag("type", "door").withTag("switchboard", "Switchboard 1")
                .withProduct("A9R60225", 4, 0.10, 25d, "A9R60225 Desc").withTag("type", "multi9").withTag("switchboard", "Switchboard 2")
                .withProduct(
                        new ProductBuilder("A9R60225B", "A9R10000 Desc")
                                .withProduct("A9R60220", 2, 0.10, 45d, "A9R60225 Desc")
                                .withProduct("A9R60221", 4, 0.10, 75d, "A9R60225 Desc").build()
                )
                .withTag("type", "enclosure").withTag("switchboard", "Switchboard 2")
                .withProduct("A9R60225", 4, 0.10, 25d, "A9R60225 Desc").withTag("type", "multi9").withTag("switchboard", "Switchboard 1")
                .build();

        XlsExportService bomExportService = new XlsExportService(header, new XlsBomStyleFactory());
        Workbook workbook = bomExportService.exportAsXls(new BomExportView(header, new BomModel("EUR", "en", products)));

        assertThat(workbook).isNotNull();
        Sheet sheet = workbook.getSheetAt(0);
        assertThat(sheet.getSheetName()).isEqualTo("Bill of Materials");

//        For visual testing
//        FileOutputStream fileOut = new FileOutputStream("workbook-no-groups.xls");
//        workbook.write(fileOut);
//        fileOut.close();

        Row row = sheet.getRow(1);
        assertThat(row.getCell(1).getStringCellValue()).isEqualTo("Bill of Materials");
    }

    @Test
    public void shouldGenerateXls_fromBom_withGroups_withOthersSupport() throws IOException {
        BomViewHeader header = new BomViewHeaderBuilder("€", "en")
                .withColumn("reference", "Reference", ColumnType.String)
                .withColumn("description", "Description", ColumnType.String)
                .withColumn("quantity", "Qty", ColumnType.Number)
                .withColumn("discount", "Discount", ColumnType.Percentage)
                .withColumn("unitPrice", "Unit Price", ColumnType.Price)
                .withColumn("NET_PRICE", "Net Price", ColumnType.Price, true)
                .withGroup("switchboard", true, true)
                .withGroup("type", true, false)
                .build();

        List<TaggedProduct> products = new TaggedProductBuilder()
                .withProduct("PRA15424", 1, 0.20, 45d, "PRA15424 Desc").withTag("type", "door").withTag("switchboard", "Switchboard 1")
                .withProduct("PRA15423", 2, 0.20, 60d, "PRA15423 Desc").withTag("switchboard", "Switchboard 1")
                .withProduct("A9R60225", 4, 0.10, 25d, "A9R60225 Desc").withTag("type", "multi9").withTag("switchboard", "Switchboard 2")
                .build();

        XlsExportService bomExportService = new XlsExportService(header, new XlsBomStyleFactory());
        Workbook workbook = bomExportService.exportAsXls(new BomExportView(header, new BomModel("EUR", "en", products)));

        assertThat(workbook).isNotNull();
        Sheet sheet = workbook.getSheetAt(0);
        assertThat(sheet.getSheetName()).isEqualTo("Bill of Materials");

//        For visual testing
//        FileOutputStream fileOut = new FileOutputStream("workbook-others.xls");
//        workbook.write(fileOut);
//        fileOut.close();

        Row row = sheet.getRow(6);
        assertThat(row.getCell(2).getStringCellValue()).isEqualTo("Others");
    }

    @Test
    public void shouldGenerateXls_useHeaderMessages_ifExist() {
        BomViewHeader header = new BomViewHeaderBuilder("€", "en")
                .withColumn("reference", "Reference", ColumnType.String)
                .withColumn("description", "Description", ColumnType.String)
                .withColumn("quantity", "Qty", ColumnType.Number)
                .withColumn("discount", "Discount", ColumnType.Percentage)
                .withColumn("unitPrice", "Unit Price", ColumnType.Price)
                .withColumn("NET_PRICE", "Net Price", ColumnType.Price, true)
                .withMessage("Bill of Materials", "Liste de matériels")
                .build();

        List<TaggedProduct> products = new TaggedProductBuilder()
                .withProduct("PRA15424", 1, 0.20, 45d, "PRA15424 Desc").withTag("type", "door").withTag("switchboard", "Switchboard 1")
                .build();

        XlsExportService bomExportService = new XlsExportService(header, new XlsBomStyleFactory());
        Workbook workbook = bomExportService.exportAsXls(new BomExportView(header, new BomModel("EUR", "en", products)));

        assertThat(workbook).isNotNull();
        Sheet sheet = workbook.getSheetAt(0);
        assertThat(sheet.getSheetName()).isEqualTo("Liste de matériels");
    }

    @Test
    public void shouldGenerateXls_addQuantity_toEveryProduct_ifExist_inFlatBom() throws IOException {
        BomViewHeader header = new BomViewHeaderBuilder("€", "en")
                .withColumn("reference", "Reference", ColumnType.String)
                .withColumn("description", "Description", ColumnType.String)
                .withColumn("quantity", "Qty", ColumnType.Number)
                .withColumn("discount", "Discount", ColumnType.Percentage)
                .withColumn("unitPrice", "Unit Price", ColumnType.Price)
                .withColumn("NET_PRICE", "Net Price", ColumnType.Price, true)
                .build();

        List<TaggedProduct> products = new TaggedProductBuilder()
                .withProduct("PRA15424", 1, 0.20, 45d, "PRA15424 Desc").withTag("type", "door").withTag("switchboard", "Switchboard 1")
                .withProduct("PRA15424B", 5, 0.20, 45d, "PRA15424B Desc").withTag("type", "multi9").withTag("switchboard", "Switchboard 1")
                .build();

        XlsExportService bomExportService = new XlsExportService(header, new XlsBomStyleFactory());

        TagQuantity doorQuantity = new TagQuantity("multi9", 2);
        Map<String, TagQuantity> sbQuantities = newHashMap();
        sbQuantities.put("type", doorQuantity);

        TagQuantity tagQuantity = new TagQuantity("Switchboard 1", 3);
        tagQuantity.setQuantities(sbQuantities);

        Map<String, TagQuantity> quantities = newHashMap();
        quantities.put("switchboard", tagQuantity);

        Workbook workbook = bomExportService.exportAsXls(new BomExportView(header, new BomModel("EUR", "en", products, quantities)));

        assertThat(workbook).isNotNull();
        Sheet sheet = workbook.getSheetAt(0);

//        For visual testing
//        FileOutputStream fileOut = new FileOutputStream("workbook-quantities.xls");
//        workbook.write(fileOut);
//        fileOut.close();

        Row row = sheet.getRow(3);
        assertThat(row.getCell(3).getNumericCellValue()).isEqualTo(3);
        assertThat(row.getCell(6).getNumericCellValue()).isEqualTo(108);

        row = sheet.getRow(4);
        assertThat(row.getCell(3).getNumericCellValue()).isEqualTo(30);

    }

    @Test
    public void shouldGenerateXls_addQuantity_toEveryGroup_ifExist_inGroupBom() throws IOException {
        BomViewHeader header = new BomViewHeaderBuilder("€", "en")
                .withColumn("reference", "Reference", ColumnType.String)
                .withColumn("description", "Description", ColumnType.String)
                .withColumn("quantity", "Qty", ColumnType.Number)
                .withColumn("discount", "Discount", ColumnType.Percentage)
                .withColumn("unitPrice", "Unit Price", ColumnType.Price)
                .withColumn("NET_PRICE", "Net Price", ColumnType.Price, true)
                .withGroup("switchboard", true)
                .withGroup("type", true)
                .build();

        List<TaggedProduct> products = new TaggedProductBuilder()
                .withProduct("PRA15424", 1, 0.20, 45d, "PRA15424 Desc").withTag("type", "door").withTag("switchboard", "Switchboard 1")
                .withProduct("PRA15424B", 5, 0.20, 45d, "PRA15424B Desc").withTag("type", "multi9").withTag("switchboard", "Switchboard 1")
                .withProduct("PRA15424C", 5, 0.20, 45d, "PRA15424C Desc").withTag("type", "multi9").withTag("switchboard", "Switchboard 2")
                .build();

        XlsExportService bomExportService = new XlsExportService(header, new XlsBomStyleFactory());

        TagQuantity multi9Quantity = new TagQuantity("multi9", 2);
        Map<String, TagQuantity> sbQuantities = newHashMap();
        sbQuantities.put("type", multi9Quantity);

        TagQuantity tagQuantity = new TagQuantity("Switchboard 1", 3);
        tagQuantity.setQuantities(sbQuantities);

        Map<String, TagQuantity> quantities = newHashMap();
        quantities.put("switchboard", tagQuantity);

        Workbook workbook = bomExportService.exportAsXls(new BomExportView(header, new BomModel("EUR", "en", products, quantities)));

        assertThat(workbook).isNotNull();
        Sheet sheet = workbook.getSheetAt(0);

//        For visual testing
//        FileOutputStream fileOut = new FileOutputStream("workbook-quantities.xls");
//        workbook.write(fileOut);
//        fileOut.close();

        Row row = sheet.getRow(2);
        assertThat(row.getCell(1).getStringCellValue()).isEqualTo("Switchboard 1 (Quantity: 3)");

        row = sheet.getRow(3);
        assertThat(row.getCell(5).getNumericCellValue()).isEqualTo(1);
        assertThat(row.getCell(8).getNumericCellValue()).isEqualTo(36);

        row = sheet.getRow(6);
        assertThat(row.getCell(5).getNumericCellValue()).isEqualTo(5);

        row = sheet.getRow(7);
        assertThat(row.getCell(2).getStringCellValue()).isEqualTo("Unittotal");
        assertThat(row.getCell(8).getNumericCellValue()).isEqualTo(180);

        row = sheet.getRow(8);
        assertThat(row.getCell(2).getStringCellValue()).isEqualTo("Subtotal (Quantity: 2)");
        assertThat(row.getCell(8).getNumericCellValue()).isEqualTo(360);

    }

    @Test
    public void shouldGenerateXls_withQuantity_onMainProducts_applyToEverySubProducts() throws IOException {
        BomViewHeader header = new BomViewHeaderBuilder("€", "en")
                .withColumn("reference", "Reference", ColumnType.String)
                .withColumn("description", "Description", ColumnType.String)
                .withColumn("quantity", "Qty", ColumnType.Number)
                .withColumn("discount", "Discount", ColumnType.Percentage)
                .withColumn("unitPrice", "Unit Price", ColumnType.Price)
                .withColumn("NET_PRICE", "Net Price", ColumnType.Price, true)
                .withGroup("switchboard", true)
                .withGroup("type", true)
                .build();

        List<TaggedProduct> products = new TaggedProductBuilder()
                .withProduct(
                        new ProductBuilder("A9R60225B", "A9R10000 Desc", 3, null)
                                .withProduct("A9R60220", 2, 0.10, 45d, "A9R60225 Desc")
                                .withProduct("A9R60221", 4, 0.10, 75d, "A9R60225 Desc").build()
                )
                .withTag("type", "enclosure").withTag("switchboard", "Switchboard 2")
                .build();

        XlsExportService bomExportService = new XlsExportService(header, new XlsBomStyleFactory());

        Workbook workbook = bomExportService.exportAsXls(new BomExportView(header, new BomModel("EUR", "en", products)));

        assertThat(workbook).isNotNull();
        Sheet sheet = workbook.getSheetAt(0);

//        For visual testing
//        FileOutputStream fileOut = new FileOutputStream("workbook-product-quantities.xls");
//        workbook.write(fileOut);
//        fileOut.close();

        Row row = sheet.getRow(3);
        assertThat(row.getCell(3).getStringCellValue()).isEqualTo("A9R60220");
        assertThat(row.getCell(5).getNumericCellValue()).isEqualTo(6);

        row = sheet.getRow(4);
        assertThat(row.getCell(3).getStringCellValue()).isEqualTo("A9R60221");
        assertThat(row.getCell(5).getNumericCellValue()).isEqualTo(12);
    }

    @Test
    public void shouldGenerateXls_withColumnHeader_onTopByDefault() throws IOException {
        BomViewHeader header = new BomViewHeaderBuilder("€", "en")
                .withColumn("reference", "Reference", ColumnType.String)
                .withColumn("description", "Description", ColumnType.String)
                .withColumn("quantity", "Qty", ColumnType.Number)
                .withColumn("discount", "Discount", ColumnType.Percentage)
                .withColumn("unitPrice", "Unit Price", ColumnType.Price)
                .withColumn("NET_PRICE", "Net Price", ColumnType.Price, true)
                .withGroup("switchboard", true)
                .withGroup("type", true)
                .build();

        List<TaggedProduct> products = new TaggedProductBuilder()
                .withProduct("A9R60220", 2, 0.10, 45d, "A9R60225 Desc")
                .withTag("type", "enclosure").withTag("switchboard", "Switchboard 2")
                .build();

        XlsExportService bomExportService = new XlsExportService(header, new XlsBomStyleFactory());

        Workbook workbook = bomExportService.exportAsXls(new BomExportView(header, new BomModel("EUR", "en", products)));

        assertThat(workbook).isNotNull();
        Sheet sheet = workbook.getSheetAt(0);

        Row row = sheet.getRow(1);
        assertThat(row.getCell(3).getStringCellValue()).isEqualTo("Reference");
        assertThat(row.getCell(4).getStringCellValue()).isEqualTo("Description");
        assertThat(row.getCell(5).getStringCellValue()).isEqualTo("Qty");
        assertThat(row.getCell(6).getStringCellValue()).isEqualTo("Discount");
        assertThat(row.getCell(7).getStringCellValue()).isEqualTo("Unit Price");
        assertThat(row.getCell(8).getStringCellValue()).isEqualTo("Net Price");
    }

    @Test
    public void shouldGenerateXls_forProductsWithSubSubSubProducts() throws IOException {
        BomViewHeader header = new BomViewHeaderBuilder("€", "en")
                .withColumn("reference", "Reference", ColumnType.String)
                .withColumn("description", "Description", ColumnType.String)
                .withColumn("quantity", "Qty", ColumnType.Number)
                .withColumn("discount", "Discount", ColumnType.Percentage)
                .withColumn("unitPrice", "Unit Price", ColumnType.Price)
                .withColumn("NET_PRICE", "Net Price", ColumnType.Price, true)
                .withGroup("switchboard", true)
                .withGroup("type", true)
                .build();

        List<TaggedProduct> products = new TaggedProductBuilder()
                .withProduct(
                        new ProductBuilder("A9R60225B", "A9R10000 Desc", 3, null)
                                .withProduct(
                                        new ProductBuilder("A9R60225B", "A9R10000 Desc", 2, null)
                                                .withProduct("A9R60220", 2, 0.10, 45d, "A9R60220 Desc")
                                                .withProduct("A9R60221", 4, 0.10, 75d, "A9R60221 Desc").build()
                                ).build()
                )
                .withTag("type", "enclosure").withTag("switchboard", "Switchboard 2")
                .withProduct("A9R60220", 2, 0.10, 45d, "A9R60225 Desc")
                .withTag("type", "enclosure").withTag("switchboard", "Switchboard 2")
                .build();

        XlsExportService bomExportService = new XlsExportService(header, new XlsBomStyleFactory());

        Workbook workbook = bomExportService.exportAsXls(new BomExportView(header, new BomModel("EUR", "en", products)));

        assertThat(workbook).isNotNull();
        Sheet sheet = workbook.getSheetAt(0);

        FileOutputStream fileOut = new FileOutputStream("workbook-product-subsubsub.xls");
        workbook.write(fileOut);
        fileOut.close();

        Row row = sheet.getRow(3);
        assertThat(row.getCell(3).getStringCellValue()).isEqualTo("A9R60220");
        assertThat(row.getCell(4).getStringCellValue()).isEqualTo("A9R60220 Desc");
        assertThat(row.getCell(5).getNumericCellValue()).isEqualTo(12);
        assertThat(row.getCell(6).getNumericCellValue()).isEqualTo(0.1);
        assertThat(row.getCell(7).getNumericCellValue()).isEqualTo(45d);
        assertThat(row.getCell(8).getNumericCellValue()).isCloseTo(12 * 0.9 * 45d, offset(0.1));

        row = sheet.getRow(4);
        assertThat(row.getCell(3).getStringCellValue()).isEqualTo("A9R60221");
        assertThat(row.getCell(4).getStringCellValue()).isEqualTo("A9R60221 Desc");
        assertThat(row.getCell(5).getNumericCellValue()).isEqualTo(24);
        assertThat(row.getCell(6).getNumericCellValue()).isEqualTo(0.1);
        assertThat(row.getCell(7).getNumericCellValue()).isEqualTo(75d);
        assertThat(row.getCell(8).getNumericCellValue()).isCloseTo(24 * 0.9 * 75d, offset(0.1));
    }

    @Test
    public void shouldGenerateXls_forProductsWithSubSubSubProducts_andDisplayPackEnabled() throws IOException {
        BomViewHeader header = new BomViewHeaderBuilder("€", "en")
                .withDisplayPackInformations()
                .withColumn("reference", "Reference", ColumnType.String)
                .withColumn("description", "Description", ColumnType.String)
                .withColumn("quantity", "Qty", ColumnType.Number)
                .withColumn("discount", "Discount", ColumnType.Percentage)
                .withColumn("unitPrice", "Unit Price", ColumnType.Price)
                .withColumn("NET_PRICE", "Net Price", ColumnType.Price, true)
                .withGroup("switchboard", true)
                .withGroup("type", true)
                .build();

        List<TaggedProduct> products = new TaggedProductBuilder()
                .withProduct(
                        new ProductBuilder("A9R60225A", "A9R60225A Desc", 3, 0.1d, 700d)
                                .withProduct(
                                        new ProductBuilder("A9R60225B", "A9R60225B Desc", 2, 390d)
                                                .withProduct("A9R60220", 2, null, 45d, "A9R60220 Desc")
                                                .withProduct("A9R60221", 4, null, 75d, "A9R60221 Desc").build()
                                ).build()
                )
                .withTag("type", "enclosure").withTag("switchboard", "Switchboard 2")
                .withProduct("A9R60220", 2, 0.10, 45d, "A9R60225 Desc")
                .withTag("type", "enclosure").withTag("switchboard", "Switchboard 2")
                .build();

        XlsExportService bomExportService = new XlsExportService(header, new XlsBomStyleFactory());

        Workbook workbook = bomExportService.exportAsXls(new BomExportView(header, new BomModel("EUR", "en", products)));

        assertThat(workbook).isNotNull();
        Sheet sheet = workbook.getSheetAt(0);

//        FileOutputStream fileOut = new FileOutputStream("workbook-product-subsubsub.xls");
//        workbook.write(fileOut);
//        fileOut.close();

        Row row = sheet.getRow(3);
        assertThat(row.getCell(3).getStringCellValue()).isEqualTo("A9R60225A");
        assertThat(row.getCell(4).getStringCellValue()).isEqualTo("A9R60225A Desc");
        assertThat(row.getCell(5).getNumericCellValue()).isEqualTo(3);
        assertThat(row.getCell(6).getNumericCellValue()).isEqualTo(0.1);
        assertThat(row.getCell(7).getNumericCellValue()).isEqualTo(700);
        assertThat(row.getCell(8).getNumericCellValue()).isCloseTo(3 * 0.9 * 700, offset(0.1));

        row = sheet.getRow(4);
        assertThat(row.getCell(3).getStringCellValue()).isEqualTo("    A9R60225B");
        assertThat(row.getCell(4).getStringCellValue()).isEqualTo("A9R60225B Desc");
        assertThat(row.getCell(5).getNumericCellValue()).isEqualTo(2);
        assertThat(row.getCell(6).getNumericCellValue()).isEqualTo(0);
        assertThat(row.getCell(7).getNumericCellValue()).isEqualTo(390d);
        assertThat(row.getCell(8).getStringCellValue()).isEmpty();

        row = sheet.getRow(5);
        assertThat(row.getCell(3).getStringCellValue()).isEqualTo("        A9R60220");
        assertThat(row.getCell(4).getStringCellValue()).isEqualTo("A9R60220 Desc");
        assertThat(row.getCell(5).getNumericCellValue()).isEqualTo(2);
        assertThat(row.getCell(6).getStringCellValue()).isEmpty();
        assertThat(row.getCell(7).getNumericCellValue()).isEqualTo(45d);
        assertThat(row.getCell(8).getStringCellValue()).isEmpty();

        row = sheet.getRow(6);
        assertThat(row.getCell(3).getStringCellValue()).isEqualTo("        A9R60221");
        assertThat(row.getCell(4).getStringCellValue()).isEqualTo("A9R60221 Desc");
        assertThat(row.getCell(5).getNumericCellValue()).isEqualTo(4);
        assertThat(row.getCell(6).getStringCellValue()).isEmpty();
        assertThat(row.getCell(7).getNumericCellValue()).isEqualTo(75d);
        assertThat(row.getCell(8).getStringCellValue()).isEmpty();
    }
}
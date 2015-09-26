package com.schneider_electric.dces.bom.export;

import com.google.common.base.Charsets;
import com.schneider_electric.dces.bom.domain.*;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static org.assertj.core.api.Assertions.assertThat;

public class CsvExportServiceTest {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    @Test
    public void shouldGenerateCsv_fromBom_withGroups_withOthersSupport() throws IOException {
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
                .withProduct("PRA15424", 1, 0.20, 45d, "PRA15424 Desc").withTag("type", "door").withTag("switchboard", "Switchboard 1")
                .withProduct("PRA15423", 2, 0.20, 60d, "PRA15423 Desc").withTag("switchboard", "Switchboard 1")
                .withProduct("A9R60225", 4, 0.123456, 25.1234d, "A9R60225 Desc").withTag("type", "multi9").withTag("switchboard", "Switchboard 2")
                .build();

        CsvExportService bomExportService = new CsvExportService(header, ';');
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bomExportService.exportAsCsv(new BomExportView(header, new BomModel("EUR", "en", products)), outputStream);

        String csv = new String(outputStream.toByteArray(), Charsets.UTF_8);
        assertThat(csv).isNotNull();

        assertThat(csv).contains(
                "switchboard;type;Reference;Description;Qty;Discount;Unit Price;Unit Net Price;Net Price;" + LINE_SEPARATOR +
                        "Switchboard 1;door;PRA15424;PRA15424 Desc;1;0.2;45.0;36.0;36.0;" + LINE_SEPARATOR +
                        "Switchboard 1;Others;PRA15423;PRA15423 Desc;2;0.2;60.0;48.0;96.0;" + LINE_SEPARATOR +
                        "Switchboard 2;multi9;A9R60225;A9R60225 Desc;4;0.1235;25.12;22.02;88.09;"
        );
    }

    @Test
    public void shouldGenerateCsv_fromBom_withGroups_withOthersSupport_inFrench() throws IOException {
        BomViewHeader header = new BomViewHeaderBuilder("€", "fr")
                .withColumn("reference", "Réference", ColumnType.String)
                .withColumn("description", "Description", ColumnType.String)
                .withColumn("quantity", "Qté", ColumnType.Number)
                .withColumn("discount", "Discount", ColumnType.Percentage)
                .withColumn("unitPrice", "Prix unitaire", ColumnType.Price)
                .withColumn("NET_PRICE", "Prix", ColumnType.Price, true)
                .withGroup("switchboard", true, true)
                .withGroup("type", true, false)
                .build();

        List<TaggedProduct> products = new TaggedProductBuilder()
                .withProduct("PRA15424", 1, 0.20, 45d, "PRA15424 Desc").withTag("type", "door").withTag("switchboard", "Switchboard 1")
                .withProduct("PRA15423", 2, 0.20, 60d, "PRA15423 Desc").withTag("switchboard", "Switchboard 1")
                .withProduct("A9R60225", 4, 0.10, 25d, "A9R60225 Desc").withTag("type", "multi9").withTag("switchboard", "Switchboard 2")
                .build();

        CsvExportService bomExportService = new CsvExportService(header, ';');
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bomExportService.exportAsCsv(new BomExportView(header, new BomModel("EUR", "en", products)), outputStream);

        String csv = new String(outputStream.toByteArray(), Charsets.UTF_8);
        assertThat(csv).isNotNull();

        assertThat(csv).startsWith(
                "\uFEFFswitchboard;type;Réference;Description;Qté;Discount;Prix unitaire;Prix;" + LINE_SEPARATOR +
                        "Switchboard 1;door;PRA15424;PRA15424 Desc;1;0,2;45,0;36,0;" + LINE_SEPARATOR +
                        "Switchboard 1;Autres;PRA15423;PRA15423 Desc;2;0,2;60,0;96,0;" + LINE_SEPARATOR +
                        "Switchboard 2;multi9;A9R60225;A9R60225 Desc;4;0,1;25,0;90,0;"
        );
    }

    @Test
    public void shouldGenerateCsv_fromBom_withSubProducts() throws IOException {
        BomViewHeader header = new BomViewHeaderBuilder("€", "fr")
                .withColumn("reference", "Réference", ColumnType.String)
                .withColumn("description", "Description", ColumnType.String)
                .withColumn("quantity", "Qté", ColumnType.Number)
                .withColumn("discount", "Discount", ColumnType.Percentage)
                .withColumn("unitPrice", "Prix unitaire", ColumnType.Price)
                .withColumn("NET_PRICE", "Prix", ColumnType.Price, true)
                .withGroup("switchboard", true, true)
                .withGroup("type", true, false)
                .build();

        List<TaggedProduct> products = new TaggedProductBuilder()
                .withProduct("PRA15424", 1, 0.20, 45d, "PRA15424 Desc").withTag("type", "door").withTag("switchboard", "Switchboard 1")
                .withProduct("PRA15423", 2, 0.20, 60d, "PRA15423 Desc").withTag("type", "door").withTag("switchboard", "Switchboard 1")
                .withProduct("A9R60225", 4, 0.10, 25d, "A9R60225 Desc").withTag("type", "multi9").withTag("switchboard", "Switchboard 2")
                .withProduct(
                        new ProductBuilder("A9R60225B", "A9R10000 Desc", 3, null)
                                .withProduct("A9R60220", 2, 0.10, 45d, "A9R60220 Desc")
                                .withProduct("A9R60221", 4, 0.10, 25d, "A9R60221 Desc").build()
                )
                .withTag("type", "enclosure").withTag("switchboard", "Switchboard 2")
                .withProduct("A9R60225", 4, 0.10, 25d, "A9R60225 Desc").withTag("type", "multi9").withTag("switchboard", "Switchboard 1")
                .build();

        CsvExportService bomExportService = new CsvExportService(header, ';');
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bomExportService.exportAsCsv(new BomExportView(header, new BomModel("EUR", "en", products)), outputStream);

        String csv = new String(outputStream.toByteArray(), Charsets.UTF_8);
        assertThat(csv).isNotNull();

        assertThat(csv).startsWith(
                "\uFEFFswitchboard;type;Réference;Description;Qté;Discount;Prix unitaire;Prix;" + LINE_SEPARATOR +
                        "Switchboard 1;door;PRA15424;PRA15424 Desc;1;0,2;45,0;36,0;" + LINE_SEPARATOR +
                        "Switchboard 1;door;PRA15423;PRA15423 Desc;2;0,2;60,0;96,0;" + LINE_SEPARATOR +
                        "Switchboard 1;multi9;A9R60225;A9R60225 Desc;4;0,1;25,0;90,0;" + LINE_SEPARATOR +
                        "Switchboard 2;multi9;A9R60225;A9R60225 Desc;4;0,1;25,0;90,0;" + LINE_SEPARATOR +
                        "Switchboard 2;enclosure;A9R60220;A9R60220 Desc;6;0,1;45,0;243,0;" + LINE_SEPARATOR +
                        "Switchboard 2;enclosure;A9R60221;A9R60221 Desc;12;0,1;25,0;270,0;" + LINE_SEPARATOR
        );
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

        CsvExportService bomExportService = new CsvExportService(header, ';');

        TagQuantity doorQuantity = new TagQuantity("multi9", 2);
        Map<String, TagQuantity> sbQuantities = newHashMap();
        sbQuantities.put("type", doorQuantity);

        TagQuantity tagQuantity = new TagQuantity("Switchboard 1", 3);
        tagQuantity.setQuantities(sbQuantities);

        Map<String, TagQuantity> quantities = newHashMap();
        quantities.put("switchboard", tagQuantity);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bomExportService.exportAsCsv(new BomExportView(header, new BomModel("EUR", "en", products, quantities)), outputStream);

        String csv = new String(outputStream.toByteArray(), Charsets.UTF_8);
        assertThat(csv).isNotNull();

        assertThat(csv).startsWith(
                "\uFEFFReference;Description;Qty;Discount;Unit Price;Net Price;" + LINE_SEPARATOR +
                "PRA15424;PRA15424 Desc;3;0.2;45.0;108.0;" + LINE_SEPARATOR +
                "PRA15424B;PRA15424B Desc;30;0.2;45.0;1080.0;" + LINE_SEPARATOR
        );
    }

    @Test
    public void shouldGenerateXls_addQuantity_toEveryProduct_ifExist_displayingGroups() throws IOException {
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
                .withProduct("PRA15424B", 5, 0.20, 45d, "PRA15424B Desc").withTag("type", "multi9").withTag("switchboard", "Switchboard 1")
                .build();

        CsvExportService bomExportService = new CsvExportService(header, ';');

        TagQuantity doorQuantity = new TagQuantity("multi9", 2);
        Map<String, TagQuantity> sbQuantities = newHashMap();
        sbQuantities.put("type", doorQuantity);

        TagQuantity tagQuantity = new TagQuantity("Switchboard 1", 3);
        tagQuantity.setQuantities(sbQuantities);

        Map<String, TagQuantity> quantities = newHashMap();
        quantities.put("switchboard", tagQuantity);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bomExportService.exportAsCsv(new BomExportView(header, new BomModel("EUR", "en", products, quantities)), outputStream);

        String csv = new String(outputStream.toByteArray(), Charsets.UTF_8);
        assertThat(csv).isNotNull();

        assertThat(csv).startsWith(
                "\uFEFFswitchboard;type;Reference;Description;Qty;Discount;Unit Price;Net Price;" + LINE_SEPARATOR +
                "Switchboard 1;door;PRA15424;PRA15424 Desc;3;0.2;45.0;108.0;" + LINE_SEPARATOR +
                "Switchboard 1;multi9;PRA15424B;PRA15424B Desc;30;0.2;45.0;1080.0;" + LINE_SEPARATOR
        );
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

        CsvExportService bomExportService = new CsvExportService(header, ';');
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bomExportService.exportAsCsv(new BomExportView(header, new BomModel("EUR", "en", products)), outputStream);

        String csv = new String(outputStream.toByteArray(), Charsets.UTF_8);
        assertThat(csv).isNotNull();

        assertThat(csv).startsWith(
                "\uFEFFswitchboard;type;Reference;Description;Qty;Discount;Unit Price;Net Price;" + LINE_SEPARATOR +
                        "Switchboard 2;enclosure;A9R60220;A9R60220 Desc;12;0.1;45.0;486.0;" + LINE_SEPARATOR +
                        "Switchboard 2;enclosure;A9R60221;A9R60221 Desc;24;0.1;75.0;1620.0;" + LINE_SEPARATOR
        );
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
                .withProduct("A9R60225", 2, 0.10, 45d, "A9R60225 Desc")
                .withTag("type", "enclosure").withTag("switchboard", "Switchboard 2")
                .build();

        CsvExportService bomExportService = new CsvExportService(header, ';');
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bomExportService.exportAsCsv(new BomExportView(header, new BomModel("EUR", "en", products)), outputStream);

        String csv = new String(outputStream.toByteArray(), Charsets.UTF_8);
        assertThat(csv).isNotNull();

        assertThat(csv).startsWith(
                "\uFEFFswitchboard;type;Reference;Description;Qty;Discount;Unit Price;Net Price;" + LINE_SEPARATOR +
                        "Switchboard 2;enclosure;A9R60225A;A9R60225A Desc;3;0.1;700.0;1890.0;" + LINE_SEPARATOR +
                        "Switchboard 2;enclosure;A9R60225;A9R60225 Desc;2;0.1;45.0;81.0;" + LINE_SEPARATOR
        );
    }

    @Test
    public void shouldGenerateXls_forProductsWithSubSubSubProducts_andDisplayPackEnabled_withNoPrice() throws IOException {
        BomViewHeader header = new BomViewHeaderBuilder("€", "en")
                .withDisplayPackInformations()
                .withColumn("reference", "Reference", ColumnType.String)
                .withColumn("description", "Description", ColumnType.String)
                .withColumn("quantity", "Qty", ColumnType.Number)
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
                .withProduct("A9R60225", 2, 0.10, 45d, "A9R60225 Desc")
                .withTag("type", "enclosure").withTag("switchboard", "Switchboard 2")
                .build();

        CsvExportService bomExportService = new CsvExportService(header, ';');
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bomExportService.exportAsCsv(new BomExportView(header, new BomModel("EUR", "en", products)), outputStream);

        String csv = new String(outputStream.toByteArray(), Charsets.UTF_8);
        assertThat(csv).isNotNull();

        assertThat(csv).startsWith(
                "\uFEFFswitchboard;type;Level1;Level1 Qty;Level2;Level2 Qty;Reference;Description;Qty;" + LINE_SEPARATOR +
                        "Switchboard 2;enclosure;A9R60225A;3;A9R60225B;2;A9R60220;A9R60220 Desc;12;" + LINE_SEPARATOR +
                        "Switchboard 2;enclosure;A9R60225A;3;A9R60225B;2;A9R60221;A9R60221 Desc;24;" + LINE_SEPARATOR +
                        "Switchboard 2;enclosure;;;;;A9R60225;A9R60225 Desc;2;" + LINE_SEPARATOR
        );
    }
}
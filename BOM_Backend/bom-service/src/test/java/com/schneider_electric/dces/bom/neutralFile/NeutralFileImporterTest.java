package com.schneider_electric.dces.bom.neutralFile;

import com.schneider_electric.dces.bom.domain.*;
import com.schneider_electric.dces.bom.export.XlsBomStyleFactory;
import com.schneider_electric.dces.bom.export.XlsExportService;
import com.schneider_electric.dces.bom.neutralFile.extension.BomTagAttribute;
import com.schneider_electric.dces.neutralFile.NeutralFileBuilder;
import com.schneider_electric.dces.neutralFile.NeutralFileXmlReader;
import com.schneider_electric.dces.neutralFile.exceptions.XMLUnmarshallingException;
import com.schneider_electric.electrical_distribution.exchange_format.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import static com.google.common.collect.Sets.newHashSet;
import static org.assertj.core.api.Assertions.assertThat;

public class NeutralFileImporterTest {

    NeutralFileImporter neutralFileImporter = new NeutralFileImporter();

    @Test
    public void testImportNeutralFile() {
        ElectricalProject electricalProject = new NeutralFileBuilder().addProduct("PRA10202", 2).build();

        BomModel bomModel = neutralFileImporter.importNeutralFile(electricalProject, null);

        Assert.assertNotNull(bomModel);
        Assert.assertEquals(1, bomModel.content.size());
        TaggedProduct taggedProduct = bomModel.content.get(0);
        Assert.assertTrue(taggedProduct.tags.isEmpty());
        Assert.assertNotNull(taggedProduct.product);
        Assert.assertEquals("PRA10202", taggedProduct.product.getReference());
        Assert.assertEquals(2, taggedProduct.product.getQuantity().intValue());
        Assert.assertNull(taggedProduct.product.getDataSheetUrl());
        Assert.assertNull(taggedProduct.product.getDescription());
    }


    @Test
    public void testImportNeutralFileFromFile() throws XMLUnmarshallingException, IOException {
        final ElectricalProject electricalProject;
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("testNeutralFile.xml")) {
            electricalProject = new NeutralFileXmlReader().readFromXML(inputStream);
        }

        BomModel bomModel = neutralFileImporter.importNeutralFile(electricalProject, null);

        Assert.assertNotNull(bomModel);
        Assert.assertEquals(2, bomModel.content.size());
        TaggedProduct taggedProduct = bomModel.content.get(0);
        Assert.assertTrue(taggedProduct.tags.isEmpty());
        Assert.assertNotNull(taggedProduct.product);
        Assert.assertEquals("PRA10202", taggedProduct.product.getReference());
        Assert.assertEquals(1, taggedProduct.product.getQuantity().intValue());
        Assert.assertNull(taggedProduct.product.getDataSheetUrl());
        Assert.assertNull(taggedProduct.product.getDescription());

        taggedProduct = bomModel.content.get(1);
        Assert.assertTrue(taggedProduct.tags.isEmpty());
        Assert.assertNotNull(taggedProduct.product);
        Assert.assertEquals("A9F74170", taggedProduct.product.getReference());
        Assert.assertEquals(4, taggedProduct.product.getQuantity().intValue());
        Assert.assertNull(taggedProduct.product.getDataSheetUrl());
        Assert.assertNull(taggedProduct.product.getDescription());
    }

    @Test
    public void testExportBomFromNeutralFile() {
        ElectricalProject electricalProject = new NeutralFileBuilder().addProduct("PRA10202", "Pragma surface enclosure - IP30 - 2 x 13 modules - without door", 2).build();

        BomModel bomModel = neutralFileImporter.importNeutralFile(electricalProject, null);

        BomViewHeader header = new BomViewHeaderBuilder("€", "en", false, false)
                .withColumn("reference", "Reference", ColumnType.String)
                .withColumn("quantity", "Qty", ColumnType.Number)
                .build();


        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setFallbackToSystemLocale(false); // don't load messages_fr.properties if en is asked and computer default locale is fr, fallback to messages.properties
        messageSource.setBasenames("i18n/messages");

        XlsExportService bomExportService = new XlsExportService(header, new XlsBomStyleFactory());
        Workbook workbook = bomExportService.exportAsXls(new BomExportView(header, bomModel));

        assertThat(workbook).isNotNull();
        Sheet sheet = workbook.getSheetAt(0);
        assertThat(sheet.getSheetName()).isEqualTo("Bill of Materials");

        Row row = sheet.getRow(1);
        assertThat(row.getCell(1).getStringCellValue()).isEqualTo("Bill of Materials");

        row = sheet.getRow(2);
        assertThat(row.getCell(1).getStringCellValue()).isEqualTo("Reference");
        assertThat(row.getCell(2).getStringCellValue()).isEqualTo("Qty");

        row = sheet.getRow(3);
        assertThat(row.getCell(1).getStringCellValue()).isEqualTo("PRA10202");
        assertThat(row.getCell(2).getNumericCellValue()).isEqualTo(2);

    }

    @Test
    public void shouldFilterProducts_returnAllProducList_ifNoEquipmentId_isProvided() throws IOException, XMLUnmarshallingException {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("testNeutralFile.xml")) {
            final ElectricalProject electricalProject = new NeutralFileXmlReader().readFromXML(inputStream);
            Collection<ProductPack> packList = neutralFileImporter.productPacksForEquipment(electricalProject, null);
            assertThat(packList).hasSize(2);
            packList = neutralFileImporter.productPacksForEquipment(electricalProject, new HashSet<String>());
            assertThat(packList).hasSize(2);
        }
    }

    @Test(expected = NeutralFileImportException.class)
    public void shouldFilterProducts_throwAnException_ifNoEquipment_exist() throws IOException, XMLUnmarshallingException {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("testNeutralFile.xml")) {
            final ElectricalProject electricalProject = new NeutralFileXmlReader().readFromXML(inputStream);
            neutralFileImporter.productPacksForEquipment(electricalProject, newHashSet("1"));
        }
    }

    @Test(expected = NeutralFileImportException.class)
    public void shouldFilterProducts_throwAnException_ifNoEquipment_matchTheGivenId() throws IOException, XMLUnmarshallingException {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("testNeutralFile.xml")) {
            final ElectricalProject electricalProject = new NeutralFileXmlReader().readFromXML(inputStream);
            ElectricalProject.Equipments equipments = new ElectricalProject.Equipments();
            Equipment equipment = new Equipment();
            equipment.setId("0");
            equipments.getEquipment().add(equipment);
            electricalProject.setEquipments(equipments);
            neutralFileImporter.productPacksForEquipment(electricalProject, newHashSet("1"));
        }
    }

    @Test
    public void shouldFilterProducts_filtersOnEquipmentId() throws IOException, XMLUnmarshallingException {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("multiDashboardNeutralFile.xml")) {
            final ElectricalProject electricalProject = new NeutralFileXmlReader().readFromXML(inputStream);
            Collection<ProductPack> packList = neutralFileImporter.productPacksForEquipment(electricalProject, newHashSet("EQ001"));
            assertThat(packList).isNotEmpty().hasSize(1);
            assertThat(packList).extracting("id").containsOnly("PK001");
        }
    }

    @Test
    public void shouldFilterProducts_filtersOnEquipmentId_withManySwitchboard() throws IOException, XMLUnmarshallingException {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("multiDashboardNeutralFile.xml")) {
            final ElectricalProject electricalProject = new NeutralFileXmlReader().readFromXML(inputStream);
            Collection<ProductPack> packList = neutralFileImporter.productPacksForEquipment(electricalProject, newHashSet("EQ001", "EQ002"));
            assertThat(packList).isNotEmpty().hasSize(3);
            assertThat(packList).extracting("id").containsOnly("PK001", "PK002", "PK003");
        }
    }

    @Test
    public void shouldLoadTags_loadNothing_IfPackHasNoBomTagExtension() {
        ProductPack pack = new ProductPack();
        Map<String, String> tags = neutralFileImporter.loadTags(pack, null);
        assertThat(tags).isEmpty();

        pack.setExtensions(new EntityExtensionList());
        tags = neutralFileImporter.loadTags(pack, null);
        assertThat(tags).isEmpty();
    }

    @Test
    public void shouldLoadTags_extractTags_fromBomTagExtension() {
        ProductPack pack = new ProductPack();
        EntityExtensionList extensions = new EntityExtensionList();
        extensions.getExtension().add(buildBomTagExtension("type", "enclosure"));
        extensions.getExtension().add(buildBomTagExtension("other", "Other One"));
        pack.setExtensions(extensions);

        Map<String, String> tags = neutralFileImporter.loadTags(pack, null);
        assertThat(tags).isNotEmpty();
        assertThat(tags)
                .containsEntry("type", "enclosure")
                .containsEntry("other", "Other One");
    }

    @Test
    public void shouldLoadTags_extractTags_fromBomTagExtension_andFromEquipmentWithName() {
        String packId = "PACK_ID";
        ProductPack pack = new ProductPack();
        pack.setId(packId);
        EntityExtensionList extensions = new EntityExtensionList();
        extensions.getExtension().add(buildBomTagExtension("type", "enclosure"));
        extensions.getExtension().add(buildBomTagExtension("other", "Other One"));
        pack.setExtensions(extensions);

        Map<String, String> tags = neutralFileImporter.loadTags(pack, buildEquipment("EQ001", "Switchboard 1", packId));
        assertThat(tags).isNotEmpty();
        assertThat(tags)
                .containsEntry("type", "enclosure")
                .containsEntry("other", "Other One")
                .containsEntry("equipment", "Switchboard 1");
    }

    @Test
    public void shouldLoadTags_extractTags_fromBomTagExtension_andFromEquipmentWithoutName() {
        String packId = "PACK_ID";
        ProductPack pack = new ProductPack();
        pack.setId(packId);
        EntityExtensionList extensions = new EntityExtensionList();
        extensions.getExtension().add(buildBomTagExtension("type", "enclosure"));
        extensions.getExtension().add(buildBomTagExtension("other", "Other One"));
        pack.setExtensions(extensions);

        Map<String, String> tags = neutralFileImporter.loadTags(pack, buildEquipment("EQ001", null, packId));
        assertThat(tags).isNotEmpty();
        assertThat(tags)
                .containsEntry("type", "enclosure")
                .containsEntry("other", "Other One")
                .containsEntry("equipment", "EQ001");
    }

    private ElectricalProject.Equipments buildEquipment(String id, String name, String packId) {
        ElectricalProject.Equipments equipments = new ElectricalProject.Equipments();
        Equipment equipment = new Equipment();
        equipment.setId(id);
        if (name != null) {
            Equipment.Properties properties = new Equipment.Properties();
            properties.setName(name);
            equipment.setProperties(properties);
        }
        equipments.getEquipment().add(equipment);

        EquipmentCommercial commercial = new EquipmentCommercial();
        ProductPack productPack = new ProductPack();
        productPack.setId(packId);
        commercial.getProductPacks().add(productPack);
        equipment.setCommercial(commercial);
        return equipments;
    }

    private EntityExtensionList.Extension buildBomTagExtension(String name, String value) {
        EntityExtensionList.Extension extension = new EntityExtensionList.Extension();
        BomTagAttribute tagAttribute = buildBomTagAttribute(name, value);
        extension.setAny(tagAttribute);
        return extension;
    }

    private BomTagAttribute buildBomTagAttribute(String name, String value) {
        BomTagAttribute tagAttribute = new BomTagAttribute();
        tagAttribute.setName(name);
        tagAttribute.setValue(value);
        return tagAttribute;
    }
}

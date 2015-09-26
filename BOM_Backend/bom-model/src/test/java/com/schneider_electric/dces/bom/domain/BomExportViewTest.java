package com.schneider_electric.dces.bom.domain;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static org.assertj.core.api.Assertions.assertThat;


public class BomExportViewTest {

    @Test
    public void shouldGroupProducts_accordingToTheHeader() {
        BomViewHeader header = new BomViewHeaderBuilder("€", "fr")
                .withColumn("reference", "Reference", ColumnType.String)
                .withColumn("quantity", "Qty", ColumnType.Number)
                .withColumn("unitPrice", "Unit Price", ColumnType.Price)
                .withColumn("NET_PRICE", "Net Price", ColumnType.Price, true)
                .withGroup("switchboard")
                .withGroup("type")
                .build();

        List<TaggedProduct> products = new TaggedProductBuilder()
                .withProduct("PRA15424", 1, 0.20, 45d, "PRA15424 Desc").withTag("type", "door").withTag("switchboard", "1")
                .withProduct("A9R60225", 4, 0.10, 25d, "A9R60225 Desc").withTag("type", "multi9").withTag("switchboard", "2")
                .withProduct("A9R60225", 4, 0.10, 25d, "A9R60225 Desc").withTag("type", "multi9").withTag("switchboard", "1")
                .build();

        Map<String, TagQuantity> typeQuantities = newHashMap();
        typeQuantities.put("type", new TagQuantity("multi9", 2));

        Map<String, TagQuantity> sbQuantities = newHashMap();
        TagQuantity sb1Qty = new TagQuantity("1", 3);
        sb1Qty.setQuantities(typeQuantities);
        sbQuantities.put("switchboard", sb1Qty);

        BomExportView bomExportView = new BomExportView(header, new BomModel("EUR", "en", products, sbQuantities));
        BomGroup group = bomExportView.group();
        assertThat(group.getContent()).isNotEmpty().hasSize(2);
        Summable firstContent = group.getContent().get(0);
        assertThat(firstContent).isExactlyInstanceOf(BomGroup.class);

        BomGroup switchboard1 = (BomGroup) firstContent;
        assertThat(switchboard1.getLabel()).isEqualTo("switchboard");
        assertThat(switchboard1.getValue()).isEqualTo("1");
        assertThat(switchboard1.getQuantity()).isEqualTo(3);

        assertThat(switchboard1.getContent()).isNotEmpty().hasSize(2);
        BomGroup door = (BomGroup) switchboard1.getContent().get(0);
        assertThat(door).isExactlyInstanceOf(BomGroup.class);
        assertThat(door.getLabel()).isEqualTo("type");
        assertThat(door.getValue()).isEqualTo("door");
        assertThat(door.getQuantity()).isNull();

        BomGroup multi9 = (BomGroup) switchboard1.getContent().get(1);
        assertThat(multi9).isExactlyInstanceOf(BomGroup.class);
        assertThat(multi9.getLabel()).isEqualTo("type");
        assertThat(multi9.getValue()).isEqualTo("multi9");
        assertThat(multi9.getQuantity()).isEqualTo(2);

        assertThat(door.getContent()).hasSize(1);
        assertThat(door.getContent().get(0)).isExactlyInstanceOf(Product.class);

        assertThat(group.getContent().get(1)).isExactlyInstanceOf(BomGroup.class);
    }

    @Test
    public void shouldGroupProducts_accordingToTheHeader_andApplyQuantityForFlatBOM() {
        BomViewHeader header = new BomViewHeaderBuilder("€", "fr")
                .withColumn("reference", "Reference", ColumnType.String)
                .withColumn("quantity", "Qty", ColumnType.Number)
                .withColumn("unitPrice", "Unit Price", ColumnType.Price)
                .withColumn("NET_PRICE", "Net Price", ColumnType.Price, true)
                .build();

        List<TaggedProduct> products = new TaggedProductBuilder()
                .withProduct("PRA15424", 2, 0.20, 45d, "PRA15424 Desc").withTag("type", "door").withTag("switchboard", "1")
                .withProduct("A9R60225", 4, 0.10, 25d, "A9R60225 Desc").withTag("type", "multi9").withTag("switchboard", "2")
                .withProduct("A9R60225", 4, 0.10, 25d, "A9R60225 Desc").withTag("type", "multi9").withTag("switchboard", "1")
                .build();

        TagQuantity doorQuantity = new TagQuantity("door", 10);
        Map<String, TagQuantity> sbQuantities = newHashMap();
        sbQuantities.put("type", doorQuantity);

        TagQuantity tagQuantity = new TagQuantity("1", 10);
        tagQuantity.setQuantities(sbQuantities);

        Map<String, TagQuantity> quantities = newHashMap();
        quantities.put("switchboard", tagQuantity);

        BomExportView bomExportView = new BomExportView(header, new BomModel("EUR", "en", products, quantities));
        BomGroup group = bomExportView.group();
        assertThat(group.getContent()).isNotEmpty().hasSize(3);
        Summable firstContent = group.getContent().get(0);
        assertThat(firstContent).isExactlyInstanceOf(Product.class);
        assertThat(((Product)firstContent).getQuantity()).isEqualTo(200);
    }

    @Test
    public void shouldGroupProducts_usingOther_forProductThatDoesNotMatch_Tag() {
        BomViewHeader header = new BomViewHeaderBuilder("€", "fr")
                .withColumn("reference", "Reference", ColumnType.String)
                .withColumn("quantity", "Qty", ColumnType.Number)
                .withColumn("unitPrice", "Unit Price", ColumnType.Price)
                .withColumn("NET_PRICE", "Net Price", ColumnType.Price, true)
                .withGroup("switchboard")
                .build();

        List<TaggedProduct> products = new TaggedProductBuilder()
                .withProduct("PRA15424", 2, 0.20, 45d, "PRA15424 Desc").withTag("switchboard", "1")
                .withProduct("A9R60225", 4, 0.10, 25d, "A9R60225 Desc")
                .withProduct("A9R60226", 4, 0.10, 25d, "A9R60225 Desc")
                .build();

        BomExportView bomExportView = new BomExportView(header, new BomModel("EUR", "en", products));
        BomGroup group = bomExportView.group();
        assertThat(group.getContent()).isNotEmpty().hasSize(2);
        Summable firstContent = group.getContent().get(1);
        assertThat(firstContent).isExactlyInstanceOf(BomGroup.class);
        assertThat(((BomGroup)firstContent).getValue()).isEqualTo(BomGroup.BOM_EXPORT_OTHERS);
        assertThat(((BomGroup)firstContent).getContent()).hasSize(2);
    }

}
package com.schneider_electric.dces.bom.export;

import com.schneider_electric.dces.bom.domain.BomViewHeader;
import com.schneider_electric.dces.bom.domain.HeaderColumn;
import com.schneider_electric.dces.bom.domain.Product;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class BomExportGeneratorTest {

    @Test
    public void shouldLookInto_anyAttributesIfNoDirectAttribute_matchColumnId() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Product product = new Product("REF");
        product.set("customAttribute", "custom value");
        BomExportGenerator bomExportGenerator = new BomExportGenerator(new BomViewHeader(null, null, "EUR", "EN", null, null, null));
        assertThat(bomExportGenerator.getValueForColumn(product, new HeaderColumn("customAttribute", "Custom attribute"))).isEqualTo("custom value");
    }

}
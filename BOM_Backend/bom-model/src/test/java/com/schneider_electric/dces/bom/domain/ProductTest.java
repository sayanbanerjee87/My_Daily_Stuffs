package com.schneider_electric.dces.bom.domain;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {

    private static Validator validator;

    @BeforeClass
    public static void beforeClass() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    private BomExportView generateBomExportView(Product product) {
        BomViewHeader header = new BomViewHeaderBuilder("€", "en")
                .withColumn("reference", "Reference", ColumnType.String)
                .withColumn("description", "Description", ColumnType.String)
                .withColumn("quantity", "Qty", ColumnType.Number)
                .withColumn("discount", "Discount", ColumnType.Percentage)
                .withColumn("unitPrice", "Unit Price", ColumnType.Price)
                .withColumn("NET_PRICE", "Net Price", ColumnType.Price, true)
                .build();

        List<TaggedProduct> products = new TaggedProductBuilder()
                .withProduct(product)
                .build();

        return new BomExportView(header, new BomModel("EUR", "en", products));
    }

    @Test
    public void shouldValidationSucceed_forSimpleProductWithQuantityAndUnitPrice() {
        Product product = new Product("Ref", "Desc", 10, 120d);
        Set<ConstraintViolation<BomExportView>> constraintViolations = validator.validate(generateBomExportView(product));
        assertThat(constraintViolations).isEmpty();
    }

    @Test
    public void shouldValidationSucceed_forComposedProductWithQuantityAndUnitPrice() {
        Product product = new Product("Ref");
        product.addProduct(new Product("Subref", "SubDesc", 10, 120d));
        Set<ConstraintViolation<BomExportView>> constraintViolations = validator.validate(generateBomExportView(product));
        assertThat(constraintViolations).isEmpty();
    }

    @Test
    public void shouldRoundNetPrice_with2digits() {
        Product product = new Product("ref", "desc", 3, 0.03d, 5.7d);
        assertThat(product.getNetPrice()).isEqualTo(16.59d);
        product = new Product("ref", "desc", 1, 0.0d, 5.555d);
        assertThat(product.getNetPrice()).isEqualTo(5.56d);
        product = new Product("ref", "desc", 1, 0.0d, 5.554d);
        assertThat(product.getNetPrice()).isEqualTo(5.55d);
    }

}
package com.schneider_electric.dces.bom.domain;

/**
 * User: FDU3285
 * Date: 03/11/2014
 * Time: 13:52
 */
public class ProductBuilder {

    private Product product;

    public ProductBuilder(String reference) {
        this.product = new Product(reference);
    }

    public ProductBuilder(String reference, String description) {
        this.product = new Product(reference, description);
    }

    public ProductBuilder(String reference, String description, int quantity, Double discount, Double unitPrice) {
        this.product = new Product(reference, description, quantity, discount, unitPrice);
    }

    public ProductBuilder(String reference, String description, int quantity, Double unitPrice) {
        this.product = new Product(reference, description, quantity, unitPrice);
    }

    public ProductBuilder withProduct(String reference, int quantity, Double discount, Double unitPrice, String description) {
        Product product = new Product(reference, description, quantity, discount, unitPrice);
        this.product.addProduct(product);
        return this;
    }

    public ProductBuilder withProduct(Product product) {
        this.product.addProduct(product);
        return this;
    }

    public Product build() {
        return this.product;
    }
}

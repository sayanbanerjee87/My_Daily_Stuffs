package com.schneider_electric.dces.bom.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;

/**
 * User: FDU3285
 * Date: 30/10/2014
 * Time: 17:23
 */
public class TaggedProductBuilder {

    private List<TaggedProduct> products = newArrayList();
    private TaggedProduct currentProduct;

    public TaggedProductBuilder withProduct(Product product) {
        TaggedProduct taggedProduct = new TaggedProduct(product, new HashMap<String, String>());
        products.add(taggedProduct);
        this.currentProduct = taggedProduct;
        return this;
    }

    public TaggedProductBuilder withProduct(String reference, int quantity, Double discount, Double unitPrice, String description) {
        return withProduct(reference, quantity, discount, unitPrice, description, new HashMap<String, String>());
    }

    public TaggedProductBuilder withProduct(String reference, int quantity, Double discount, Double unitPrice, String description, Map<String, String> tags) {
        Product product = new Product(reference);
        product.setQuantity(quantity);
        product.setDiscount(discount);
        product.setUnitPrice(unitPrice);
        product.setDescription(description);
        TaggedProduct taggedProduct = new TaggedProduct(product, tags);
        products.add(taggedProduct);
        this.currentProduct = taggedProduct;
        return this;
    }

    public TaggedProductBuilder withTag(String key, String value) {
        if (this.currentProduct == null) {
            throw new IllegalArgumentException("Cannot set tag without product");
        }
        this.currentProduct.addTag(key, value);
        return this;
    }

    public List<TaggedProduct> build() {
        return this.products;
    }

}

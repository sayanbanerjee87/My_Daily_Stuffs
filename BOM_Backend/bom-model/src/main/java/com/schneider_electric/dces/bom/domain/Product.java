package com.schneider_electric.dces.bom.domain;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

/**
 * User: FDU3285
 * Date: 30/10/2014
 * Time: 15:52
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Product extends Summable {

    public final String reference;
    private String description;
    private Integer quantity;
    private Double discount;
    private Double unitPrice;
    private String dataSheetUrl;
    private String pictureUrl;

    @Valid
    private List<Product> products = newArrayList();

    private Map<String, String> other = newHashMap();

    @JsonCreator
    public Product(@JsonProperty("reference") String reference) {
        super();
        this.reference = reference;
    }


    public Product(String reference, String description) {
        super();
        this.reference = reference;
        this.description = description;
    }

    public Product(String reference, String description, int quantity, Double discount, Double unitPrice) {
        super();
        this.reference = reference;
        this.quantity = quantity;
        this.discount = discount;
        this.unitPrice = unitPrice;
        this.description = description;
    }

    public Product(String reference, String description, int quantity, Double unitPrice) {
        this(reference, description, quantity, null, unitPrice);
    }

    public String getReference() {
        return reference;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDataSheetUrl() {
        return dataSheetUrl;
    }

    public void setDataSheetUrl(String dataSheetUrl) {
        this.dataSheetUrl = dataSheetUrl;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    public void addProduct(Product product) {
        this.products.add(product);
    }

    @JsonAnyGetter
    public Map<String, String> any() {
        return other;
    }

    @JsonAnySetter
    public void set(String name, String value) {
        other.put(name, value);
    }

    @Override
    public int total() {
        return 0;
    }

    public Double getNetPrice() {
        if (!hasPriceData()) {
            return null;
        }
        double netprice = this.quantity * this.unitPrice;
        if (this.discount != null) {
            netprice = netprice * (1 - this.discount);
        }
        return new BigDecimal("" + netprice).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public Double getUnitNetPrice() {
        if (this.unitPrice == null) {
            return null;
        }
        double unitNetprice = this.unitPrice;
        if (this.discount != null) {
            unitNetprice = unitNetprice * (1 - this.discount);
        }
        return new BigDecimal("" + unitNetprice).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public boolean hasSubProducts() {
        return this.products != null && !this.products.isEmpty();
    }

    public boolean hasPriceData() {
        return this.quantity != null && this.unitPrice != null;
    }

    @Override
    public int evalMaxProductPackDeep() {
        if (hasSubProducts()) {
            return 1 + evalMaxProductPackDeep(this.products);
        }
        return 0;
    }
}

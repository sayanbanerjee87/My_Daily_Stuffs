package com.schneider_electric.dces.bom.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

/**
 * User: FDU3285
 * Date: 31/10/2014
 * Time: 09:49
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class BomModel {

    private String  equipments;
    private   String  reference;
    private Integer quantity;
    @NotNull
    private String currencyCode;

    @JsonProperty("language")
    private String language;
    @JsonProperty("gridProd")
    public List<TaggedProductForGrid> gridProd;
    @NotNull @Valid
    public List<TaggedProduct> content;
    private Map<String, TagQuantity> quantities = newHashMap();

    public BomModel(@JsonProperty("content") List<TaggedProduct> content) {
        this.content = content;
        this.language = null;
    }
    public BomModel(String currencyCode, String language, List<TaggedProduct> content) {
        this.currencyCode = currencyCode;
        this.content = content;
        this.language = language;
    }

    public BomModel(String currencyCode, String language,ArrayList<TaggedProductForGrid> gridProd) {
        this.currencyCode = currencyCode;
        this.gridProd = gridProd;
        this.language = language;
    }

    public BomModel(String currencyCode, String language, List<TaggedProduct> content, Map<String, TagQuantity> quantities) {
        this.content = content;
        this.currencyCode = currencyCode;
        this.language = language;
        this.quantities = quantities;
    }

    public BomModel() {

    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Map<String, TagQuantity> getQuantities() {
        return quantities;
    }

    public void setQuantities(Map<String, TagQuantity> quantities) {
        this.quantities = quantities;
    }

    public String getReference() {return reference;}
    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getEquipments() {
        return equipments;
    }
    public void setEquipments(String equipments) {
        this.equipments = equipments;
    }

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

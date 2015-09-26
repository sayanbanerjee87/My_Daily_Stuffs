package com.schneider_electric.dces.bom.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

/**
 * User: SESA379886
 * Date: 31/10/2014
 * Time: 09:49
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class GridModel {

    @NotNull
    private String currencyCode;

    @JsonProperty("language")
    private String language;
    @NotNull @Valid
    public final List gridProd;
    private Map<String, TagQuantity> quantities = newHashMap();

    public GridModel(@JsonProperty("gridProd") List gridProd) {
        this.gridProd = gridProd;
        this.language = null;
    }

    public GridModel(String currencyCode, String language, List<TaggedProductForGrid> gridProd) {
        this.currencyCode = currencyCode;
        this.gridProd = gridProd;
        this.language = language;
    }

    public GridModel(String currencyCode, String language, List<TaggedProductForGrid> gridProd, Map<String, TagQuantity> quantities) {
        this.gridProd = gridProd;
        this.currencyCode = currencyCode;
        this.language = language;
        this.quantities = quantities;
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
}

package com.schneider_electric.dces.bom.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by SESA379886 on 7/1/2015.
 */

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class UiGridModel {
    public  String  reference;
    private String  description;
    private Integer quantity;
    private Double  discount;
    private Double  unitPrice;
    private String  dataSheetUrl;
    private String  pictureUrl;
    private String  equipments;

  /*  public UiGridModel(@JsonProperty("reference") String reference){
        this.reference = reference;
    }
*/
    public String getReference() {return reference;}

    public void setReference(String reference) {
        this.reference = reference;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    public String getEquipments() {
        return equipments;
    }

    public void setEquipments(String equipments) {
        this.equipments = equipments;
    }
}

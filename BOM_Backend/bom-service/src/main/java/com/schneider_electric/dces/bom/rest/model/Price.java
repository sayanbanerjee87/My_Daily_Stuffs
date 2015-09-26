package com.schneider_electric.dces.bom.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.schneider_electric.dces.bom.rest.ExtendBOM;

import javax.xml.bind.annotation.XmlRootElement;

/**
* Created by porcher-g on 09/12/2014.
*/
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Price {
    private String reference;
    private Double value;
    private Double discount;
    private String familyCode;


    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getFamilyCode() {
        return familyCode;
    }

    public void setFamilyCode(String familyCode) {
        this.familyCode = familyCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price price = (Price) o;

        if (discount != null ? !discount.equals(price.discount) : price.discount != null) return false;
        if (familyCode != null ? !familyCode.equals(price.familyCode) : price.familyCode != null) return false;
        if (!reference.equals(price.reference)) return false;
        if (value != null ? !value.equals(price.value) : price.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reference.hashCode();
        result = 31 * result + (familyCode != null ? familyCode.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);

        return result;
    }
}

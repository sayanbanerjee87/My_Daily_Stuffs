package com.schneider_electric.dces.pricing.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import com.schneider_electric.dces.pricing.service.Strings;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

import static com.schneider_electric.dces.pricing.service.Strings.normalize;

/**
 * User: FDU3285
 * Date: 14/11/2014
 * Time: 08:35
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement
@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(name = "discount_unique_for_federatedid_pricelist_family_validityStart", columnNames = {"federatedId", "priceList", "familyCode", "validityStart"}),
        indexes=@Index(name="discount_idx", columnList="federatedId,priceList,familyCode,validityStart, validityStop")
)
public class Discount {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String federatedId;

    @NotBlank
    private String familyCode;
    private String priceList;
    @PercentageValidation
    private Double value;
    @NotNull
    private Date validityStart;
    private Date validityStop;

    public Discount() {
    }

    @JsonCreator
    public Discount(@JsonProperty("familyCode") String familyCode, @JsonProperty("value") Double value, @JsonProperty("validityStart") Date validityStart) {
        setFamilyCode(familyCode);
        this.value = value;
        this.validityStart = validityStart;
    }

    public Discount(String federatedId, String familyCode, Double value, Date validityStart) {
        this.federatedId = federatedId;
        setFamilyCode(familyCode);
        this.value = value;
        this.validityStart = validityStart;
    }

    public Discount(String priceList, String federatedId, String familyCode, Double value, Date validityStart) {
        this.priceList = priceList;
        this.federatedId = federatedId;
        setFamilyCode(familyCode);
        this.value = value;
        this.validityStart = validityStart;
    }

    public Discount(String federatedId, String familyCode, Double value, Date validityStart, Date validityStop) {
        this.federatedId = federatedId;
        setFamilyCode(familyCode);
        this.value = value;
        this.validityStart = validityStart;
        this.validityStop = validityStop;
    }

    public Discount(String priceList, String federatedId, String familyCode, Double value, Date validityStart, Date validityStop) {
        this.priceList = priceList;
        this.federatedId = federatedId;
        setFamilyCode(familyCode);
        this.value = value;
        this.validityStart = validityStart;
        this.validityStop = validityStop;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFederatedId() {
        return federatedId;
    }

    public void setFederatedId(String federatedId) {
        this.federatedId = federatedId;
    }

    public Date getValidityStop() {
        return validityStop;
    }

    public void setValidityStop(Date validityStop) {
        this.validityStop = validityStop;
    }

    public boolean hasNoValue() {
        return this.value == null || this.value == 0;
    }

    public String getFamilyCode() {
        return familyCode;
    }

    public void setFamilyCode(String familyCode) {
        this.familyCode = normalize(familyCode);
    }

    public String getPriceList() {
        return priceList;
    }

    public void setPriceList(String priceList) {
        this.priceList = priceList;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getValidityStart() {
        return validityStart;
    }

    public void setValidityStart(Date validityStart) {
        this.validityStart = validityStart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discount that = (Discount) o;

        return Objects.equal(this.federatedId, that.federatedId) &&
                Objects.equal(this.familyCode, that.familyCode) &&
                Objects.equal(this.priceList, that.priceList) &&
                Objects.equal(this.value, that.value) &&
                Objects.equal(this.validityStart, that.validityStart) &&
                Objects.equal(this.validityStop, that.validityStop);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(federatedId, familyCode, priceList, value, validityStart, validityStop);
    }


    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("federatedId", federatedId)
                .add("familyCode", familyCode)
                .add("priceList", priceList)
                .add("value", value)
                .add("validityStart", validityStart)
                .add("validityStop", validityStop)
                .toString();
    }
}

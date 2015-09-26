package com.schneider_electric.dces.pricing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.Objects;
import com.schneider_electric.dces.pricing.service.Strings;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import static com.schneider_electric.dces.pricing.service.Strings.normalize;

@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@FilterDef(name="referenceFilter", parameters=@ParamDef( name="refIds", type="string"))
@Table(
        uniqueConstraints = @UniqueConstraint(name = "price_unique_for_revision_ref", columnNames = {"priceRevision_id", "reference"})
)
public class Price {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String reference;

    @JsonIgnore
    @ManyToOne(optional = false, cascade = {})
    private PriceRevision priceRevision;

    private String familyCode;

    @Column(nullable = false)
    private Double value;

    @Transient
    private Double discount;

    public Price() {
    }

    public Price(PriceRevision priceRevision, String reference, String familyCode, Double value) {
        this.priceRevision = priceRevision;
        this.setReference(reference);
        this.setFamilyCode(familyCode);
        this.setValue(value);
    }

    public Price(String reference, String familyCode, Double value) {
        this.setReference(reference);
        this.setFamilyCode(familyCode);
        this.setValue(value);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
        this.reference = normalize(reference);
	}

    public PriceRevision getPriceRevision() {
        return priceRevision;
    }

    public void setPriceRevision(PriceRevision priceRevision) {
        this.priceRevision = priceRevision;
    }

    public String getFamilyCode() {
        return familyCode;
    }

    public void setFamilyCode(String familyCode) {
        this.familyCode = normalize(familyCode);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price that = (Price) o;

        return Objects.equal(this.id, that.id) &&
                Objects.equal(this.reference, that.reference) &&
                (
                        (this.priceRevision != null && that.priceRevision != null && Objects.equal(this.priceRevision.getId(), that.priceRevision.getId()))
                        || (this.priceRevision == null && that.priceRevision == null)
                ) &&
                Objects.equal(this.familyCode, that.familyCode) &&
                Objects.equal(this.value, that.value) &&
                Objects.equal(this.discount, that.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, reference, priceRevision != null ? priceRevision.getId() : null, familyCode, value, discount);
    }


    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("reference", reference)
                .add("priceRevision", priceRevision)
                .add("familyCode", familyCode)
                .add("value", value)
                .add("discount", discount)
                .toString();
    }
}
package com.schneider_electric.dces.pricing.model;

import com.google.common.base.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * User: FDU3285
 * Date: 09/01/2015
 * Time: 09:54
 */
@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(name = "user_access_unique_for_pricelist_userid", columnNames = {"priceList_id", "federatedId"})
)
public class UserAccess {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String federatedId;

    @ManyToOne(optional = false)
    private PriceList priceList;

    public UserAccess() {
    }

    public UserAccess(String federatedId, PriceList priceList) {
        this.federatedId = federatedId;
        this.priceList = priceList;
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

    public PriceList getPriceList() {
        return priceList;
    }

    public void setPriceList(PriceList priceList) {
        this.priceList = priceList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAccess that = (UserAccess) o;

        return Objects.equal(this.id, that.id) &&
                Objects.equal(this.federatedId, that.federatedId) &&
                Objects.equal(this.priceList, that.priceList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, federatedId, priceList);
    }


    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("federatedId", federatedId)
                .add("priceList", priceList)
                .toString();
    }
}

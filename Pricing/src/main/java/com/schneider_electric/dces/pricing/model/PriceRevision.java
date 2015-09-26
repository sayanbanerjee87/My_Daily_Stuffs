package com.schneider_electric.dces.pricing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.Objects;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

import static com.google.common.collect.Lists.newArrayList;

/**
 * User: FDU3285
 * Date: 08/01/2015
 * Time: 09:42
 */
@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(name = "revision_unique_for_pricelist_fromDate", columnNames = {"priceListId", "fromDate"}),
        indexes=@Index(name="revision_date_idx", columnList="priceListId,fromDate,toDate")
)
public class PriceRevision {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name="priceListId")
    private PriceList priceList;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fromDate", nullable = false)
    private Date from;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "toDate")
    private Date to;

    @JsonInclude(value= JsonInclude.Include.NON_EMPTY)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "priceRevision", orphanRemoval = true)
    @Filter(name="referenceFilter", condition="reference IN (:refIds)")
    private Collection<Price> prices = newArrayList();

    @JsonInclude(value= JsonInclude.Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "revision", orphanRemoval = true)
    private Collection<DiscountFamily> discountFamilies = newArrayList();

    public PriceRevision() {
    }

    public PriceRevision(PriceList priceList) {
        this.priceList = priceList;
        this.from = new Date();
    }

    public PriceRevision(PriceList priceList, Date from, Date to) {
        this.priceList = priceList;
        this.from = from;
        if (from == null) {
            this.from = new Date();
        }
        this.to = to;
    }

    public PriceRevision(long id, PriceList priceList, Date from, Date to, Collection<Price> prices) {
        this(priceList, from, to);
        this.id = id;
        this.prices = prices;
    }

    public PriceRevision(long id, Date from, Date to, Collection<Price> prices) {
        this.id = id;
        this.from = from;
        if (from == null) {
            this.from = new Date();
        }
        this.to = to;
        this.prices = prices;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public Collection<Price> getPrices() {
        return prices;
    }

    public void setPrices(Collection<Price> prices) {
        if (this.prices != null) {
            this.prices.clear();
            this.prices.addAll(prices);
        } else {
            this.prices = prices;
        }
    }

    public PriceList getPriceList() {
        return priceList;
    }

    public void setPriceList(PriceList priceList) {
        this.priceList = priceList;
    }

    public Collection<DiscountFamily> getDiscountFamilies() {
        return discountFamilies;
    }

    public void setDiscountFamilies(Collection<DiscountFamily> discountFamilies) {
        if (this.discountFamilies != null) {
            this.discountFamilies.clear();
            this.discountFamilies.addAll(discountFamilies);
        } else {
            this.discountFamilies = discountFamilies;
        }
    }

    public void setRevisionRelationshipToInternalObjects() {
        for (Price price : prices) {
            price.setPriceRevision(this);
        }
        for (DiscountFamily discountFamily : discountFamilies) {
            discountFamily.setRevision(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PriceRevision that = (PriceRevision) o;

        return Objects.equal(this.id, that.id) &&
                Objects.equal(this.from, that.from) &&
                Objects.equal(this.to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, from, to);
    }


    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("from", from)
                .add("to", to)
                .toString();
    }
}

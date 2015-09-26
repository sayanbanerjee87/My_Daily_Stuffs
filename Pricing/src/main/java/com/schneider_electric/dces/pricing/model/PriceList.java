package com.schneider_electric.dces.pricing.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.Objects;
import com.schneider_electric.dces.pricing.service.Strings;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.schneider_electric.dces.pricing.service.Strings.normalize;

/**
 * User: FDU3285
 * Date: 08/01/2015
 * Time: 09:34
 */
@Entity
@Table(
        indexes=@Index(name="pricelist_idx", columnList="id,type")
)
public class PriceList {

    @Id
    private String id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PriceListType type;

    @NotBlank
    @Column(nullable = false)
    private String currencyCode;

    private boolean archived = false;

    @JsonInclude(value= JsonInclude.Include.NON_EMPTY)
    @OneToMany(mappedBy = "priceList", fetch = FetchType.LAZY)
    private List<PriceRevision> revisions = newArrayList();

    public PriceList() {
    }

    /**
     * Constructor used to set a relationship with the price list.
     * @param id the price list ID.
     */
    public PriceList(String id) {
        this.id = id;
    }

    public PriceList(String id, String name, String description, PriceListType type, String currencyCode) {
        this.id = id;
        setName(name);
        this.description = description;
        this.type = type;
        setCurrencyCode(currencyCode);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = normalize(name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PriceListType getType() {
        return type;
    }

    public void setType(PriceListType type) {
        this.type = type;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = normalize(currencyCode);
    }

    public List<PriceRevision> getRevisions() {
        return revisions;
    }

    public void setRevisions(List<PriceRevision> revisions) {
        this.revisions = revisions;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PriceList that = (PriceList) o;

        return Objects.equal(this.id, that.id) &&
                Objects.equal(this.name, that.name) &&
                Objects.equal(this.description, that.description) &&
                Objects.equal(this.type, that.type) &&
                Objects.equal(this.currencyCode, that.currencyCode) &&
                Objects.equal(this.revisions, that.revisions) &&
                Objects.equal(this.archived, that.archived);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, description, type, currencyCode, revisions, archived);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("description", description)
                .add("type", type)
                .add("currencyCode", currencyCode)
                .add("revisions", revisions)
                .add("archived", archived)
                .toString();
    }
}

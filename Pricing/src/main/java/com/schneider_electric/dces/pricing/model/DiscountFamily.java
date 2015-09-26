package com.schneider_electric.dces.pricing.model;

import com.google.common.base.Objects;
import com.schneider_electric.dces.pricing.service.Strings;

import javax.persistence.*;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.schneider_electric.dces.pricing.service.Strings.normalize;

/**
 * User: FDU3285
 * Date: 14/11/2014
 * Time: 14:45
 */
@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(name = "discount_family_unique_for_revision_code", columnNames = {"revision_id", "code"})
)
public class DiscountFamily {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String description;

    @ManyToOne
    private PriceRevision revision;

    public DiscountFamily() {
    }

    public DiscountFamily(String code, String description) {
        setCode(code);
        this.description = description;
    }

    public DiscountFamily(String code, String description, PriceRevision revision) {
        setCode(code);
        this.description = description;
        this.revision = revision;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = normalize(code);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PriceRevision getRevision() {
        return revision;
    }

    public void setRevision(PriceRevision revision) {
        this.revision = revision;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiscountFamily that = (DiscountFamily) o;

        return Objects.equal(this.code, that.code) &&
                Objects.equal(this.description, that.description) &&
                (
                        (this.revision != null && that.revision != null && Objects.equal(this.revision.getId(), that.revision.getId()))
                        || (this.revision == null && that.revision == null)
                );
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code, description, revision != null ? revision.getId() : null);
    }


    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("code", code)
                .add("description", description)
                .add("revision", revision)
                .toString();
    }
}

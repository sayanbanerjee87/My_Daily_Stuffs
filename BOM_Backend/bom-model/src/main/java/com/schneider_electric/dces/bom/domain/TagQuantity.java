package com.schneider_electric.dces.bom.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

/**
 * User: FDU3285
 * Date: 06/03/2015
 * Time: 13:19
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class TagQuantity {

    @NotBlank
    public final String tagValue;

    public final int quantity;

    private Map<String, TagQuantity> quantities = newHashMap();

    public TagQuantity(@JsonProperty("tagValue") String tagValue, @JsonProperty("quantity") int quantity) {
        this.tagValue = tagValue;
        this.quantity = quantity;
    }

    public Map<String, TagQuantity> getQuantities() {
        return quantities;
    }

    public void setQuantities(Map<String, TagQuantity> quantities) {
        this.quantities = quantities;
    }

    public int evalMultiplier(Map<String, String> tags, String tag) {
        if (tags == null) {
            return 1;
        }

        String matchingTagValue = tags.get(tag);
        if (this.tagValue.equals(matchingTagValue)) {
            int multiplier = this.quantity;
            for (Map.Entry<String, TagQuantity> quantityEntry : quantities.entrySet()) {
                multiplier *= quantityEntry.getValue().evalMultiplier(tags, quantityEntry.getKey());
            }
            return multiplier;
        }
        return 1;
    }
}

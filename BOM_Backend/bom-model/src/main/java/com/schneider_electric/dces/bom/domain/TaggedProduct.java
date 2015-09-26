package com.schneider_electric.dces.bom.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

/**
 * User: FDU3285
 * Date: 30/10/2014
 * Time: 16:37
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class TaggedProduct {

    @NotNull @Valid
    public Product product;
    public Map<String, String> tags = newHashMap();

    @JsonCreator
    public TaggedProduct(@JsonProperty("product") Product product, @JsonProperty("tags") Map<String, String> tags) {
        this.product = product;
        if (tags != null) {
            this.tags = tags;
        }
    }

    @JsonCreator
    public void addTag(String key, String value) {
        this.tags.put(key, value);
    }

    public boolean matchTag(String tag) {
        return tags.containsKey(tag);
    }

    public boolean matchTags(Collection<String> tags) {
        if (this.tags == null) {
            return tags == null || tags.isEmpty();
        }
        return this.tags.keySet().containsAll(tags);
    }

    public String getTagValue(String tag) {
        return tags.get(tag);
    }
}

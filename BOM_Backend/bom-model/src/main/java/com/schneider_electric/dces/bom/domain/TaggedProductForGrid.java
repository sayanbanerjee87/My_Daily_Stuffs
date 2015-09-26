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
 * User: SESA379886
 * Date: 13/07/2015
 * Time: 16:37
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class TaggedProductForGrid {

    @NotNull @Valid
    public UiGridProducts gridProduct;
    @JsonCreator
    public TaggedProductForGrid(@JsonProperty("childProducts") UiGridProducts finalProduct) {
        this.gridProduct =finalProduct;
    }
}

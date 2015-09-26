package com.schneider_electric.dces.bom.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * User: FDU3285
 * Date: 30/10/2014
 * Time: 16:20
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class HeaderGroup {

    @NotNull
    public final String tag;
    @NotNull
    public final Boolean displayTotal;
    @NotNull
    public final Boolean displayColumnHeaders;

    @JsonCreator
    public HeaderGroup(@JsonProperty("tag") String tag, @JsonProperty("displayTotal") Boolean displayTotal, @JsonProperty("displayColumnHeaders") Boolean displayColumnHeaders) {
        this.tag = tag;
        if (displayTotal == null) {
            this.displayTotal = true;
        } else {
            this.displayTotal = displayTotal;
        }
        if (displayColumnHeaders == null) {
            this.displayColumnHeaders = false;
        } else {
            this.displayColumnHeaders = displayColumnHeaders;
        }
    }
}

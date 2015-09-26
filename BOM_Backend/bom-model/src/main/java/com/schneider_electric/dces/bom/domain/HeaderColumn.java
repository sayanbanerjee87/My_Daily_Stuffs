package com.schneider_electric.dces.bom.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.validation.constraints.NotNull;

/**
 * User: FDU3285
 * Date: 30/10/2014
 * Time: 16:15
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class HeaderColumn {

    @NotNull
    public final String id;
    @NotNull
    public final String label;
    @NotNull
    public ColumnType type = ColumnType.String;
    public boolean hasTotal = false;

    @JsonCreator
    public HeaderColumn(@JsonProperty("id") String id, @JsonProperty("label") String label) {
        this.id = id;
        this.label = label;
    }

    public boolean isHasTotal() {
        return hasTotal;
    }

    public void setHasTotal(boolean hasTotal) {
        this.hasTotal = hasTotal;
    }

    public ColumnType getType() {
        return type;
    }

    public void setType(ColumnType type) {
        if (type != null) {
            this.type = type;
        } else {
            this.type = ColumnType.String;
        }
    }

    /*@JsonSetter(value = "type")
    public void setColumnType(String columnType) {
        if (columnType != null) {
            this.type = ColumnType.valueOf(columnType);
        } else {
            this.type = ColumnType.String;
        }
    }*/
}

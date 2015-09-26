package com.schneider_electric.dces.bom.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Predicate;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.Iterables.isEmpty;
import static com.google.common.collect.Maps.newHashMap;

/**
 * User: FDU3285
 * Date: 30/10/2014
 * Time: 15:51
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BomViewHeader {

    @NotNull
    @NotEmpty
    @Valid
    public final List<HeaderColumn> columns;
    @NotNull
    @Valid
    public final List<HeaderGroup> groups;
    @NotNull
    public final String currency;
    @NotNull
    public final Boolean displayTotal;
    public final Boolean displayColumnHeaders;
    @NotNull
    private String language;

    private boolean displayPackInformations = false;

    public final Map<String, String> messages;

    @JsonCreator
    public BomViewHeader(
            @JsonProperty("columns") List<HeaderColumn> columns,
            @JsonProperty("groups") List<HeaderGroup> groups,
            @JsonProperty("currency") String currency,
            @JsonProperty("language") String language,
            @JsonProperty("displayTotal") Boolean displayTotal,
            @JsonProperty("displayColumnHeaders") Boolean displayColumnHeaders,
            @JsonProperty("messages") Map<String, String> messages
    ) {
        this.columns = columns;
        this.groups = groups;
        this.currency = currency;
        this.language = language;
        if (displayTotal == null) {
            this.displayTotal = true;
        } else {
            this.displayTotal = displayTotal;
        }
        if (displayColumnHeaders == null) {
            this.displayColumnHeaders = true;
        } else {
            this.displayColumnHeaders = displayColumnHeaders;
        }
        if (messages != null) {
            this.messages = messages;
        } else {
            this.messages = newHashMap();
        }
    }

    public boolean hasNetPriceCalculationColumns() {
        Collection<HeaderColumn> columnIds = filter(columns, new Predicate<HeaderColumn>() {
            @Override
            public boolean apply(HeaderColumn headerColumn) {
                return headerColumn.id.equals("quantity") || headerColumn.id.equals("unitPrice");
            }
        });
        return columnIds.size() == 2;
    }

    public Integer getColumnIdx(String columnId) {
        int idx = 0;
        for (HeaderColumn column : columns) {
            if (column.id.equals(columnId)) {
                return idx;
            }
            idx++;
        }
        return null;
    }

    public boolean hasNoGroup() {
        return isEmpty(this.groups);
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isDisplayPackInformations() {
        return displayPackInformations;
    }

    public void setDisplayPackInformations(boolean displayPackInformations) {
        this.displayPackInformations = displayPackInformations;
    }

    public boolean isDisplaySubProducts() {
        return isDisplayPackInformations() && !hasNetPriceCalculationColumns();
    }

}

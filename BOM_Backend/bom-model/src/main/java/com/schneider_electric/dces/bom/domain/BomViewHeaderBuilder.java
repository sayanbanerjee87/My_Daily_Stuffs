package com.schneider_electric.dces.bom.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

/**
 * User: FDU3285
 * Date: 30/10/2014
 * Time: 17:08
 */
public class BomViewHeaderBuilder {

    private List<HeaderColumn> columns = newArrayList();
    private List<HeaderGroup> groups = newArrayList();
    private String currency;
    private String language;
    private Boolean displayTotal = true;
    private Boolean displayColumnHeaders = true;
    private Map<String, String> messages = newHashMap();
    private boolean displayPackInformations = false;

    public BomViewHeaderBuilder(String currency, String language) {
        this(currency, language, true, true);
    }

    public BomViewHeaderBuilder(String currency, String language, Boolean displayTotal, Boolean displayColumnHeaders) {
        this.currency = currency;
        this.language = language;
        this.displayTotal = displayTotal;
        this.displayColumnHeaders = displayColumnHeaders;
    }

    public BomViewHeaderBuilder withColumn(HeaderColumn column) {
        this.columns.add(column);
        return this;
    }

    public BomViewHeaderBuilder withColumn(String id, String label) {
        this.columns.add(new HeaderColumn(id, label));
        return this;
    }

    public BomViewHeaderBuilder withColumn(String id, String label, ColumnType columnType) {
        HeaderColumn column = new HeaderColumn(id, label);
        column.setType(columnType);
        this.columns.add(column);
        return this;
    }

    public BomViewHeaderBuilder withColumn(String id, String label, ColumnType columnType, boolean hasTotal) {
        HeaderColumn column = new HeaderColumn(id, label);
        column.setType(columnType);
        column.setHasTotal(hasTotal);
        this.columns.add(column);
        return this;
    }

    public BomViewHeaderBuilder withColumns(HeaderColumn... columns) {
        this.columns.addAll(Arrays.asList(columns));
        return this;
    }

    public BomViewHeaderBuilder withGroup(HeaderGroup group) {
        this.groups.add(group);
        return this;
    }

    public BomViewHeaderBuilder withGroup(String tag) {
        this.groups.add(new HeaderGroup(tag, true, false));
        return this;
    }

    public BomViewHeaderBuilder withGroup(String tag, boolean displayTotal) {
        this.groups.add(new HeaderGroup(tag, displayTotal, false));
        return this;
    }

    public BomViewHeaderBuilder withGroup(String tag, boolean displayTotal, boolean displayColumnHeaders) {
        this.groups.add(new HeaderGroup(tag, displayTotal, displayColumnHeaders));
        return this;
    }

    public BomViewHeaderBuilder withGroups(HeaderGroup... groups) {
        this.groups.addAll(Arrays.asList(groups));
        return this;
    }

    public BomViewHeaderBuilder withMessage(String key, String value) {
        this.messages.put(key, value);
        return this;
    }

    public BomViewHeaderBuilder withDisplayPackInformations() {
        this.displayPackInformations = true;
        return this;
    }

    public BomViewHeader build() {
        BomViewHeader bomViewHeader = new BomViewHeader(this.columns, this.groups, this.currency, this.language, this.displayTotal, this.displayColumnHeaders, this.messages);
        bomViewHeader.setDisplayPackInformations(this.displayPackInformations);
        return bomViewHeader;
    }
}

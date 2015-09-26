package com.schneider_electric.dces.bom.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * User: FDU3285
 * Date: 30/10/2014
 * Time: 15:47
 */
public class BomExportView {

    @NotNull @Valid
    public final BomViewHeader header;
    @NotNull @Valid
    public final BomModel bom;

    @JsonCreator
    public BomExportView(@JsonProperty("header") BomViewHeader header, @JsonProperty("bom") BomModel bom) {
        this.header = header;
        this.bom = bom;
    }

    public BomGroup group() {
        BomGroup mainGroup = new BomGroup(header.groups, header.displayTotal, false);
        mainGroup.dispatch(bom.content, bom.getQuantities());
        return mainGroup;
    }
}

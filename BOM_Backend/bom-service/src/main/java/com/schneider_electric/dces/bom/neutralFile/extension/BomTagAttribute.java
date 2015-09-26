package com.schneider_electric.dces.bom.neutralFile.extension;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

// <pace:BomTag xmlns:pace="http://bom.pace.schneider-electric.com" name="productType" value="enclosure" />
@XmlRootElement(name = "BomTag", namespace = "http://bom.pace.schneider-electric.com")
public class BomTagAttribute {

    private String name;
    private String value;

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

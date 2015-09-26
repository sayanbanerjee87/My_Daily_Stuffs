package com.schneider_electric.dces.bom.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

/**
* Created by porcher-g on 09/12/2014.
*/
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Description {
    // {"catalogue_product":
    // {"id":"ZENL1121",
    // "short_description":"single contact block for head Ø22 1NC screw clamp terminal",
    // "long_description":"",
    // "translation":
    // {"en-GB":"single contact block for head Ø22 1NC screw clamp terminal",
    // "fr-FR":"Bloc contact à vis étriers pour boutons de boite XAL - 1O"}
    // }
    // }
    @JsonProperty("catalogue_product")
    private CatalogueProduct catalogueProduct;

    public CatalogueProduct getCatalogueProduct() {
        return catalogueProduct;
    }

    public static class CatalogueProduct {
        @JsonProperty("id")
        private String id;
        @JsonProperty("short_description")
        private String shortDescription;
        @JsonProperty("long_description")
        private String longDescription;
        @JsonProperty("translation")
        private Map<String, String> translations;

        public String getId() {
            return id;
        }

        public String getShortDescription() {
            return shortDescription;
        }

        public String getLongDescription() {
            return longDescription;
        }

        public Map<String, String> getTranslations() {
            return translations;
        }
    }
}

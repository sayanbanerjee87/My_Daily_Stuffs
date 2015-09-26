package com.schneider_electric.dces.bom.domain;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Iterables.isEmpty;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Maps.newLinkedHashMap;
import static com.google.common.collect.Maps.newTreeMap;

/**
 * User: FDU3285
 * Date: 30/10/2014
 * Time: 16:49
 */
public class BomGroup extends Summable {

    public static final String BOM_EXPORT_OTHERS = "BOM_EXPORT_OTHERS";
    private List<Summable> content = newArrayList();
    private List<HeaderGroup> groups = newArrayList();
    private String label;
    private String value;
    private boolean displayTotal = true;
    private boolean displayColumnHeaders = false;
    private Integer quantity;

    public BomGroup() {
        super();
    }

    public BomGroup(List<HeaderGroup> groups, Boolean displayTotal, Boolean displayColumnHeaders) {
        super();
        this.groups = groups;
        if (displayTotal != null) {
            this.displayTotal = displayTotal;
        }
        if (displayColumnHeaders != null) {
            this.displayColumnHeaders = displayColumnHeaders;
        }
    }

    public List<Summable> getContent() {
        return content;
    }

    public void setContent(List<Summable> content) {
        this.content = content;
    }

    public boolean isDisplayTotal() {
        return displayTotal;
    }

    public void setDisplayTotal(boolean displayTotal) {
        this.displayTotal = displayTotal;
    }

    public boolean isDisplayColumnHeaders() {
        return displayColumnHeaders;
    }

    public void setDisplayColumnHeaders(boolean displayColumnHeaders) {
        this.displayColumnHeaders = displayColumnHeaders;
    }

    @Override
    public int total() {
        return 0;
    }

    public void addProduct(Product product) {
        this.content.add(product);
    }

    public void dispatch(List<TaggedProduct> content, final Map<String, TagQuantity> quantities) {
        if (isEmpty(groups)) {
            this.content = newArrayList(Lists.transform(content, toProduct(quantities)));
            return;
        }
        HeaderGroup topGroup = groups.get(0);
        List<HeaderGroup> nextGroups = groups.subList(1, groups.size());

        Map<String, List<TaggedProduct>> bomGroupsMap = newLinkedHashMap();
        for (TaggedProduct taggedProduct : content) {
            if (taggedProduct.matchTag(topGroup.tag)) {
                String dispatchKey = taggedProduct.getTagValue(topGroup.tag);
                List<TaggedProduct> taggedProductList = bomGroupsMap.get(dispatchKey);
                if (taggedProductList == null) {
                    taggedProductList = newArrayList();
                    bomGroupsMap.put(dispatchKey, taggedProductList);
                }
                taggedProductList.add(taggedProduct);
            } else {
                List<TaggedProduct> taggedProductList = bomGroupsMap.get(BOM_EXPORT_OTHERS);
                if (taggedProductList == null) {
                    taggedProductList = newArrayList();
                    bomGroupsMap.put(BOM_EXPORT_OTHERS, taggedProductList);
                }
                taggedProductList.add(taggedProduct);
            }
        }

        for (Map.Entry<String, List<TaggedProduct>> groupValue : bomGroupsMap.entrySet()) {
            BomGroup bomGroup = new BomGroup(nextGroups, topGroup.displayTotal, topGroup.displayColumnHeaders);
            bomGroup.setLabel(topGroup.tag);
            bomGroup.setValue(groupValue.getKey());
            TagQuantity tagQuantity = quantities.get(topGroup.tag);
            if (tagQuantity != null && groupValue.getKey().equals(tagQuantity.tagValue)) {
                bomGroup.setQuantity(tagQuantity.quantity);
            } else {
                tagQuantity = null;
            }
            bomGroup.dispatch(groupValue.getValue(), tagQuantity != null ? tagQuantity.getQuantities() : new HashMap<String, TagQuantity>());
            this.content.add(bomGroup);
        }
    }

    private Function<TaggedProduct, Summable> toProduct(final Map<String, TagQuantity> quantities) {
        return new Function<TaggedProduct, Summable>() {
            @Override
            public Summable apply(TaggedProduct taggedProduct) {
                for (Map.Entry<String, TagQuantity> quantityEntry : quantities.entrySet()) {
                    TagQuantity tagQuantity = quantityEntry.getValue();
                    taggedProduct.product.setQuantity(taggedProduct.product.getQuantity() * tagQuantity.evalMultiplier(taggedProduct.tags, quantityEntry.getKey()));
                }
                return taggedProduct.product;
            }
        };
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }

    public int getDeep() {
        List<Summable> elements = this.getContent();
        if (elements.isEmpty()) {
            return 0;
        }
        if (elements.get(0) instanceof Product) {
            return 1;
        }
        return 1 + ((BomGroup)elements.get(0)).getDeep();
    }

    public boolean isLastGroup() {
        List<Summable> elements = this.getContent();
        return elements.isEmpty() || elements.get(0) instanceof Product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public int getQuantityAsMultiplier() {
        return this.quantity != null ? this.quantity : 1;
    }

    public boolean hasQuantity() {
        return this.quantity != null && this.quantity > 1;
    }

    public int evalMaxProductPackDeep() {
        List<Summable> elements = this.getContent();
        if (elements.isEmpty()) {
            return 0;
        }
        return evalMaxProductPackDeep(elements);
    }
}

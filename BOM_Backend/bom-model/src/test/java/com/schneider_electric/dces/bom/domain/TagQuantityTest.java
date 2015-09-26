package com.schneider_electric.dces.bom.domain;

import org.junit.Test;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static org.assertj.core.api.Assertions.assertThat;

public class TagQuantityTest {

    @Test
    public void shouldEvalMultiplier_return1_withNoTag() {
        TagQuantity tagQuantity = new TagQuantity("1", 10);
        Map<String, String> tags = newHashMap();
        assertThat(tagQuantity.evalMultiplier(tags, "switchboard")).isEqualTo(1);
    }

    @Test
    public void shouldEvalMultiplier_return1_withNullTagMap() {
        TagQuantity tagQuantity = new TagQuantity("1", 10);
        assertThat(tagQuantity.evalMultiplier(null, "switchboard")).isEqualTo(1);
    }

    @Test
    public void shouldEvalMultiplier_returnQuantity_IfNoTagMatch() {
        TagQuantity tagQuantity = new TagQuantity("1", 10);
        Map<String, String> tags = newHashMap();
        tags.put("unknown", "unknown value");
        assertThat(tagQuantity.evalMultiplier(tags, "switchboard")).isEqualTo(1);
    }

    @Test
    public void shouldEvalMultiplier_returnQuantity_IfOneTagMatch_butNoInHierarchy() {
        TagQuantity doorQuantity = new TagQuantity("door", 10);
        Map<String, TagQuantity> sbQuantities = newHashMap();
        sbQuantities.put("type", doorQuantity);
        TagQuantity tagQuantity = new TagQuantity("1", 10);
        tagQuantity.setQuantities(sbQuantities);

        Map<String, String> tags = newHashMap();
        tags.put("switchboard", "2");
        tags.put("type", "door");
        assertThat(tagQuantity.evalMultiplier(tags, "switchboard")).isEqualTo(1);
    }

    @Test
    public void shouldEvalMultiplier_returnQuantity_IfOneTagMatch() {
        TagQuantity tagQuantity = new TagQuantity("1", 10);
        Map<String, String> tags = newHashMap();
        tags.put("switchboard", "1");
        assertThat(tagQuantity.evalMultiplier(tags, "switchboard")).isEqualTo(10);
    }

    @Test
    public void shouldEvalMultiplier_returnQuantityMultiplier_IfManyTagMatch() {
        TagQuantity doorQuantity = new TagQuantity("door", 10);
        Map<String, TagQuantity> sbQuantities = newHashMap();
        sbQuantities.put("type", doorQuantity);
        TagQuantity tagQuantity = new TagQuantity("1", 10);
        tagQuantity.setQuantities(sbQuantities);

        Map<String, String> tags = newHashMap();
        tags.put("switchboard", "1");
        tags.put("type", "door");
        assertThat(tagQuantity.evalMultiplier(tags, "switchboard")).isEqualTo(100);
    }

}
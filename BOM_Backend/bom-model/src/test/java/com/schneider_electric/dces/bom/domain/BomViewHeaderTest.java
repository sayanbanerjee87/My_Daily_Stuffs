package com.schneider_electric.dces.bom.domain;


import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * User: FDU3285
 * Date: 02/04/2015
 * Time: 16:33
 */
public class BomViewHeaderTest {

    @Test
    public void shouldSetDefaultValues() {
        BomViewHeader header = new BomViewHeader(new ArrayList<HeaderColumn>(), new ArrayList<HeaderGroup>(), "€", "fr", null, null, new HashMap<String, String>());
        assertThat(header.displayTotal).isTrue();
        assertThat(header.displayColumnHeaders).isTrue();
    }

}
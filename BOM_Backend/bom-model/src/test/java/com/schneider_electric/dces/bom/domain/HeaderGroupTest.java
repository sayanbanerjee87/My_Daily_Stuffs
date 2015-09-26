package com.schneider_electric.dces.bom.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * User: FDU3285
 * Date: 02/04/2015
 * Time: 16:30
 */
public class HeaderGroupTest {

    @Test
    public void shouldSetDefaultValue() {
        HeaderGroup group = new HeaderGroup("tag", null, null);
        assertThat(group.displayTotal).isTrue();
        assertThat(group.displayColumnHeaders).isFalse();
    }
}
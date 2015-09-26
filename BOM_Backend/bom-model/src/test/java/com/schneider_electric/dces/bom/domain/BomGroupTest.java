package com.schneider_electric.dces.bom.domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * User: FDU3285
 * Date: 23/04/2015
 * Time: 11:17
 */
public class BomGroupTest {

    @Test
    public void shouldEvalMaxProductPackDeep_returnZero_ifNoPack() {
        BomGroup bomGroup = new BomGroup();
        Product product = new ProductBuilder("A9R60225A", "A9R60225A Desc", 3, 0.1d, 700d)
                .build();
        List<Summable> products = newArrayList();
        products.add(product);
        bomGroup.setContent(products);
        assertThat(bomGroup.evalMaxProductPackDeep()).isEqualTo(0);
    }

    @Test
    public void shouldCalculate_theMaximumSubProductDeep_forTheBomGroup() {
        BomGroup bomGroup = new BomGroup();
        Product product = new ProductBuilder("A9R60225A", "A9R60225A Desc", 3, 0.1d, 700d)
                .withProduct(
                        new ProductBuilder("A9R60225B", "A9R60225B Desc", 2, 390d)
                                .withProduct("A9R60220", 2, null, 45d, "A9R60220 Desc")
                                .withProduct("A9R60221", 4, null, 75d, "A9R60221 Desc").build()
                )
                .withProduct("A9R60220", 2, null, 45d, "A9R60220 Desc")
                .build();
        List<Summable> products = newArrayList();
        products.add(product);
        bomGroup.setContent(products);
        assertThat(bomGroup.evalMaxProductPackDeep()).isEqualTo(2);
    }

}
package com.schneider_electric.dces.bom.domain;

import java.util.List;

/**
 * User: FDU3285
 * Date: 30/10/2014
 * Time: 16:55
 */
public abstract class Summable {
    public abstract int total();
    public abstract int evalMaxProductPackDeep();

    protected int evalMaxProductPackDeep(List<? extends Summable> content) {
        int maxDeep = 0;
        for (Summable summable : content) {
            maxDeep = Math.max(maxDeep, summable.evalMaxProductPackDeep());
        }
        return maxDeep;
    }
}

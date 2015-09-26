package com.schneider_electric.dces.bom.rest.model;

import java.util.HashSet;
import java.util.Set;

import static com.google.common.collect.Sets.newLinkedHashSet;

/**
 * User: FDU3285
 * Date: 25/11/2014
 * Time: 15:05
 */
public class StringList extends HashSet<String> {

    public StringList(String list) {
        if (list != null && !list.trim().isEmpty()) {
            Set<String> set = newLinkedHashSet();
            String[] elements = list.split(",");
            for (String element : elements) {
                set.add(element.trim());
            }
            addAll(set);
        }
    }
}

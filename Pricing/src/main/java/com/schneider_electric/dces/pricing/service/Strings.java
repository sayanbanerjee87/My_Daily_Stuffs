package com.schneider_electric.dces.pricing.service;

/**
 * User: FDU3285
 * Date: 16/01/2015
 * Time: 10:42
 */
public class Strings {

    public static String normalize(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        return value.trim();
    }

}

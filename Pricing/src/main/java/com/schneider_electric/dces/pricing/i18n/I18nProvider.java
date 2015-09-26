package com.schneider_electric.dces.pricing.i18n;

import org.xnap.commons.i18n.I18n;

/**
 * User: FDU3285
 * Date: 27/08/14
 * Time: 11:37
 */
public class I18nProvider {
    public static final ThreadLocal<I18n> userThreadLocal = new ThreadLocal<I18n>();

    public static void set(I18n user) {
        userThreadLocal.set(user);
    }

    public static void unset() {
        userThreadLocal.remove();
    }

    public static I18n get() {
        return userThreadLocal.get();
    }
}

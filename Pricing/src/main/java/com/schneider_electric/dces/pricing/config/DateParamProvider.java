package com.schneider_electric.dces.pricing.config;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: FDU3285
 * Date: 21/11/2014
 * Time: 16:43
 */
public class DateParamProvider implements ParamConverterProvider {

    @Override
    public <T> ParamConverter<T> getConverter(final Class<T> rawType, final Type genericType,
                                              final Annotation[] annotations) {
        if (rawType != Date.class) {
            return null;
        }

        final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        //noinspection unchecked
        return (ParamConverter<T>) new ParamConverter<Date>() {
            @Override
            public Date fromString(final String value) {
                try {
                    return format.parse(value);
                } catch (final ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }

            @Override
            public String toString(final Date value) {
                return format.format(value);
            }
        };
    }
}
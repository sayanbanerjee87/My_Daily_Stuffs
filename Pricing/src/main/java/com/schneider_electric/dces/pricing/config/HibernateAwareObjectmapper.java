package com.schneider_electric.dces.pricing.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * User: FDU3285
 * Date: 13/01/2015
 * Time: 16:27
 */
@Provider
public class HibernateAwareObjectmapper implements ContextResolver<ObjectMapper> {

    final ObjectMapper defaultObjectMapper;

    public HibernateAwareObjectmapper() {
        this.defaultObjectMapper = createDefaultMapper();
    }

    private static ObjectMapper createDefaultMapper() {
        final ObjectMapper result = new ObjectMapper();
        result.registerModule(new Hibernate4Module());
        return result;
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return defaultObjectMapper;
    }
}

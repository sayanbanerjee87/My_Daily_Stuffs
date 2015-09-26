package com.schneider_electric.dces.pricing.config;

import com.schneider_electric.dces.pricing.dao.DiscountDaoImpl;
import com.schneider_electric.dces.pricing.dao.PriceDaoImpl;
import com.schneider_electric.dces.pricing.rest.DicountResource;
import com.schneider_electric.dces.pricing.rest.PriceListResourceImpl;
import com.schneider_electric.dces.pricing.rest.PriceResource;
import com.schneider_electric.dces.pricing.service.PriceServiceImpl;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.SpringLifecycleListener;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@Configuration
//@ComponentScan(basePackageClasses = { PriceResource.class, PriceListResourceImpl.class, DicountResource.class, PriceServiceImpl.class, PriceDaoImpl.class, DiscountDaoImpl.class, UserServiceImplMock.class })
public class JerseyIntegrationTestConfig extends ResourceConfig {

    public JerseyIntegrationTestConfig() {
        register(SpringLifecycleListener.class);
        register(RequestContextFilter.class);
        register(MultiPartFeature.class);
        packages("com.schneider_electric.dces.pricing.rest");
        packages("com.wordnik.swagger.jersey.listing");
        register(JacksonFeature.class);
        register(LoggingFilter.class);
    }
}

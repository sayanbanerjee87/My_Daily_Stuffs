package com.schneider_electric.dces.pricing.config;

import com.schneider_electric.dces.pricing.exception.RevisionInThePastException;
import com.schneider_electric.dces.pricing.rest.DicountResource;
import com.schneider_electric.dces.pricing.rest.PriceResource;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.SpringLifecycleListener;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = { PriceResource.class, DicountResource.class, PriceServiceImplMock.class, UserServiceImplMock.class })
public class JerseyTestConfig extends ResourceConfig {

    public JerseyTestConfig() {
        register(SpringLifecycleListener.class);
        register(RequestContextFilter.class);
        register(MultiPartFeature.class);
        packages("com.schneider_electric.dces.pricing.rest");
        packages("com.wordnik.swagger.jersey.listing");
        register(JacksonFeature.class);
        register(UnhandledExceptionMapper.class);
        register(NotAcceptableExceptionMapper.class);
        register(NotFoundExceptionMapper.class);
        register(RelationConstraintExceptionMapper.class);
        register(RevisionInThePastException.class);
        register(I18nFilter.class);
        register(LoggingFilter.class);
    }
}

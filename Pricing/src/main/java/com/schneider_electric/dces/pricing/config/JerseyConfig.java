package com.schneider_electric.dces.pricing.config;

import com.schneider_electric.dces.pricing.exception.RevisionInThePastException;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.SpringLifecycleListener;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
        register(SpringLifecycleListener.class);
        register(RequestContextFilter.class);
		register(MultiPartFeature.class);
		packages("com.schneider_electric.dces.pricing.rest");
        register(HibernateAwareObjectmapper.class);
        register(JacksonFeature.class);
        register(DateParamProvider.class);
        register(UnhandledExceptionMapper.class);
        register(NotAcceptableExceptionMapper.class);
        register(NotFoundExceptionMapper.class);
        register(RelationConstraintExceptionMapper.class);
        register(RevisionInThePastException.class);
		register(I18nFilter.class);
		register(LoggingFilter.class);
	}
}
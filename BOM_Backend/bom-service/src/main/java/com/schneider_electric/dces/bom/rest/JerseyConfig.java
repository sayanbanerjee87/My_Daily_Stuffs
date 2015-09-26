package com.schneider_electric.dces.bom.rest;

import com.schneider_electric.dces.bom.neutralFile.extension.ElectricalNetworkJAXBContextProvider;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(RequestContextFilter.class);
		register(ElectricalNetworkJAXBContextProvider.class);
		packages("com.schneider_electric.dces.bom.rest");
		packages("com.wordnik.swagger.jersey.listing");
        register(JacksonFeature.class);
		register(LoggingFilter.class);
	}
}
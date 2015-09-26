package com.schneider_electric.dces.pricing.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@EnableWebSecurity
@Order(Ordered.LOWEST_PRECEDENCE - 6)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Autowired
    private ApplicationContext ctx;

    @Value(value="${pricing.conf.path}")
    private String htpasswdFilePath;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

    	Properties userProperties = loadUsersProperties();

        logger.info("User registered: " + userProperties);

    	http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/error").permitAll()
                .antMatchers("/swagger-api-docs").permitAll()
                .antMatchers("/swagger-api-docs/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/api/*").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/api/*").permitAll()
                .antMatchers("/api/pricesWithDiscount/**").permitAll()
                .antMatchers("/api/pricelists").permitAll()
                .antMatchers("/api/pricelists/**").permitAll()
                .antMatchers("/api/discount").permitAll()
                .antMatchers("/api/discount/**").permitAll()
                .antMatchers("/api/prices").permitAll()
                .antMatchers("/api/**").hasRole("PRICE_CONSUMER")
                .anyRequest().authenticated();
        http.httpBasic().realmName("Price API");

        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager(userProperties);
        http.authenticationProvider(new HtPasswdUserAuthenticationProvider(userDetailsService, ctx.getResource(getHtpasswdFilePath() + "htpassword-pricing")));
        http.userDetailsService(userDetailsService);
    }

	private Properties loadUsersProperties() throws IOException {
		Properties prop = new Properties();
        Resource resource = ctx.getResource(getHtpasswdFilePath() + "/users.properties");
        InputStream in = resource.getInputStream();
        prop.load(in);
    	in.close();
		return prop;
	}

    private String getHtpasswdFilePath() {
        // Hack for tests. Did not figured out how to inject it properly.
        return "${pricing.conf.path}".equals(htpasswdFilePath) ? "classpath:" : htpasswdFilePath;
    }
}

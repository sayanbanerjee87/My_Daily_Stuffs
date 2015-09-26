package com.schneider_electric.dces.pricing.rest;

import com.schneider_electric.dces.pricing.config.JerseyConfig;
import com.schneider_electric.dces.pricing.config.JerseyTestConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.test.JerseyTest;
import org.json.JSONException;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

public class PricesWithDiscountResourceImplTest extends JerseyTest {

	@Override
    protected Application configure() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JerseyTestConfig.class);
        context.scan("com.schneider_electric");
        context.register(org.springframework.validation.beanvalidation.LocalValidatorFactoryBean.class);
        JerseyConfig config = new JerseyConfig();
        config.property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        return config.property("contextConfig", context);
    }
	
	@Test
    public void should_return_empty_array_for_inexistant_ref() throws JSONException {
		String prices = target("pricesWithDiscount/FR")
				  .queryParam("currencyCode", "EUR")
				  .queryParam("referenceIds", "noRef")
				  .request().accept(MediaType.APPLICATION_JSON)
				  .header("Authorization", "123")
				  .get(String.class);
		
		assertThat(prices).isEqualTo("[]");
    }
	
	@Test
    public void should_return_empty_array_for_this_ref_and_this_user() throws JSONException {
		String prices = target("pricesWithDiscount/FR")
				  .queryParam("currencyCode", "EUR")
				  .queryParam("referenceIds", "Ref3")
				  .request().accept(MediaType.APPLICATION_JSON)
				  .header("Authorization", "123")
				  .get(String.class);
		
		assertThat(prices).isEqualTo("[]");
    }
	
	@Test
    public void should_return_2_results_for_these_refs_and_this_user() throws JSONException {
		String prices = target("pricesWithDiscount/FR")
				  .queryParam("currencyCode", "EUR")
				  .queryParam("referenceIds", "Ref1,Ref2,Ref3")
				  .request().accept(MediaType.APPLICATION_JSON)
				  .header("Authorization", "123")
				  .get(String.class);

		JSONArray jsonResult = new JSONArray(prices);
		int resultsLength = jsonResult.length();
		
		assertThat(resultsLength).isEqualTo(2);
    }
	
	@Test
    public void should_get_the_good_discount_for_this_ref_and_this_user() throws JSONException {
		String prices = target("pricesWithDiscount/FR")
				  .queryParam("currencyCode", "EUR")
				  .queryParam("referenceIds", "Ref1")
				  .request().accept(MediaType.APPLICATION_JSON)
				  .header("Authorization", "123")
				  .get(String.class);

		JSONArray jsonResult = new JSONArray(prices);
		String reference = "Ref1";
		Double discount = jsonResult.getJSONObject(0).getDouble("discount");
		String referenceInResult = jsonResult.getJSONObject(0).getString("reference");
		
		assertThat(discount).isEqualTo(0.1d);
		assertThat(reference).isEqualTo(referenceInResult);
    }

}

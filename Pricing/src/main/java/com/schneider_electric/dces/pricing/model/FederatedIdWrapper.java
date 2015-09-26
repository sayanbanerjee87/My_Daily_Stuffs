package com.schneider_electric.dces.pricing.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FederatedIdWrapper {
	
	private String id;
	
	public FederatedIdWrapper() {
		
	}

	public FederatedIdWrapper(String id) {
		this.setId(id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Override
   public String toString()
   {
      return "FederatedIdWrapper [federatedId=" + id + "]";
   }
}

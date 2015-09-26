package com.schneider_electric.dces.pricing.service;

import com.schneider_electric.dces.pricing.model.FederatedIdWrapper;

public interface UserService {
	public String getFederatedId(String authorization);
}

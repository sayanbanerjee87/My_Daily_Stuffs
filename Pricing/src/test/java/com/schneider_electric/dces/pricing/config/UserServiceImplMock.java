package com.schneider_electric.dces.pricing.config;

import javax.inject.Singleton;

import org.springframework.stereotype.Service;

import com.schneider_electric.dces.pricing.model.FederatedIdWrapper;
import com.schneider_electric.dces.pricing.service.UserService;

@Singleton
@Service
public class UserServiceImplMock implements UserService {

	@Override
	public String getFederatedId(String authorization) {
        if (authorization != null && authorization.startsWith("USER")) {
            return authorization;
        }
		return "001";
	}

}

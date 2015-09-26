package com.schneider_electric.dces.pricing.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class UserNotFoundException extends WebApplicationException  {
	public UserNotFoundException(String message) {
		super(Response.status(Response.Status.BAD_REQUEST)
                .entity(message).build());
	}
}

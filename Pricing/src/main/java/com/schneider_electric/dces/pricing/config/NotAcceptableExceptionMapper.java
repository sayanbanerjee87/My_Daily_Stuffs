package com.schneider_electric.dces.pricing.config;

import com.schneider_electric.dces.pricing.exception.ErrorWrapper;
import org.springframework.http.HttpStatus;

import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class NotAcceptableExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<NotAcceptableException> {
    @Override
    public Response toResponse(NotAcceptableException e) {
        return Response.status(HttpStatus.CONFLICT.value())
                .entity(new ErrorWrapper(e.getMessage()))
                .build();
    }
}

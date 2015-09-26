package com.schneider_electric.dces.pricing.config;

import com.schneider_electric.dces.pricing.exception.ErrorWrapper;
import com.schneider_electric.dces.pricing.exception.RevisionInThePastException;
import org.springframework.http.HttpStatus;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RevisionInThePastExceptionMapper implements ExceptionMapper<RevisionInThePastException> {
    @Override
    public Response toResponse(RevisionInThePastException e) {
        return Response.status(HttpStatus.CONFLICT.value())
                .entity(new ErrorWrapper(e.getMessage()))
                .build();
    }
}

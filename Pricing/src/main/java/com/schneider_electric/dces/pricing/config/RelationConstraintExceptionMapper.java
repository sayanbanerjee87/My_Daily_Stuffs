package com.schneider_electric.dces.pricing.config;

import com.schneider_electric.dces.pricing.exception.ErrorWrapper;
import com.schneider_electric.dces.pricing.exception.RelationConstraintException;
import org.springframework.http.HttpStatus;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RelationConstraintExceptionMapper implements ExceptionMapper<RelationConstraintException> {
    @Override
    public Response toResponse(RelationConstraintException e) {
        return Response.status(HttpStatus.CONFLICT.value())
                .entity(new ErrorWrapper(e.getMessage()))
                .build();
    }
}

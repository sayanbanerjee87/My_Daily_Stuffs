package com.schneider_electric.dces.pricing.config;

import com.schneider_electric.dces.pricing.exception.ErrorWrapper;
import org.springframework.http.HttpStatus;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
    @Override
    public Response toResponse(NotFoundException e) {
        return Response.status(HttpStatus.NOT_FOUND.value())
                .entity(new ErrorWrapper(e.getMessage()))
                .build();
    }
}

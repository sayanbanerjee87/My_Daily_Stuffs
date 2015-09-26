package com.schneider_electric.dces.bom.rest;

import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.validation.ValidationError;
import org.glassfish.jersey.server.validation.internal.LocalizationMessages;
import org.glassfish.jersey.server.validation.internal.ValidationHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import java.util.List;
import java.util.logging.Level;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

@Provider
public class ConstraintValidationExceptionMapper implements ExceptionMapper<ValidationException> {

	protected static final Logger LOG = LoggerFactory.getLogger(ConstraintValidationExceptionMapper.class);
	
	@Override
	public Response toResponse(ValidationException exception) {
        if (exception instanceof ConstraintViolationException) {
            LOG.warn(LocalizationMessages.CONSTRAINT_VIOLATIONS_ENCOUNTERED(), exception.getMessage());

            final ConstraintViolationException cve = (ConstraintViolationException) exception;
            final Response.ResponseBuilder response = Response.status(ValidationHelper.getResponseStatus(cve));

            response.type(MediaType.APPLICATION_JSON)
                    .entity(
                            new GenericEntity<>(
                                    ValidationHelper.constraintViolationToValidationErrors(cve),
                                    new GenericType<List<ValidationError>>() {
                                    }.getType()
                            )
                    );

            return response.build();
        } else {
            LOG.warn(LocalizationMessages.VALIDATION_EXCEPTION_RAISED(), exception.getMessage());
            return Response.serverError().entity(
                    new GenericEntity<>(exception.getMessage(), new GenericType<String>(){}.getType())).build();
        }
	}
}

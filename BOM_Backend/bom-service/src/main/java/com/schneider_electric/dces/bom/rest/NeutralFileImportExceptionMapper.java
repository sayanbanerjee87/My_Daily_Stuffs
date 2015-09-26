package com.schneider_electric.dces.bom.rest;

import com.schneider_electric.dces.bom.neutralFile.NeutralFileImportException;
import org.glassfish.jersey.server.validation.ValidationError;
import org.glassfish.jersey.server.validation.internal.LocalizationMessages;
import org.glassfish.jersey.server.validation.internal.ValidationHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.List;

@Provider
public class NeutralFileImportExceptionMapper implements ExceptionMapper<NeutralFileImportException> {

	protected static final Logger LOG = LoggerFactory.getLogger(NeutralFileImportExceptionMapper.class);
	
	@Override
	public Response toResponse(NeutralFileImportException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(exception.getMessage())
                .build();
	}
}

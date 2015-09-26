package com.schneider_electric.dces.pricing.config;

import com.schneider_electric.dces.pricing.exception.ErrorWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

@Provider
public class UnhandledExceptionMapper implements ExceptionMapper<RuntimeException> {

    protected static final Logger LOG = LoggerFactory.getLogger(UnhandledExceptionMapper.class);

    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(RuntimeException exception) {

        if (exception instanceof WebApplicationException) {
            // WORKAROUND: Attempt to mirror Jersey's built-in behavior.
            // @see http://java.net/jira/browse/JERSEY-1607
            WebApplicationException webApplicationException = (WebApplicationException) exception;
            return webApplicationException.getResponse();
        }

        LOG.error("UnhandledException", exception);
        MediaType responseType = MediaType.APPLICATION_JSON_TYPE;
        if (request != null) {
            String acceptHeader = request.getHeader("Accept");
            if (acceptHeader != null && acceptHeader.equals(MediaType.APPLICATION_XML)) {
                responseType = MediaType.APPLICATION_XML_TYPE;
            }
        }
        return Response.status(INTERNAL_SERVER_ERROR).type(responseType).entity(new ErrorWrapper(exception.getCause() != null ? exception.getCause().getMessage() : exception.getMessage())).build();
    }
}

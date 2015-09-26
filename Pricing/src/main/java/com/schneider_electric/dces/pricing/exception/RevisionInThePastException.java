package com.schneider_electric.dces.pricing.exception;

import javax.ws.rs.WebApplicationException;

/**
 * User: FDU3285
 * Date: 12/01/2015
 * Time: 10:01
 */
public class RevisionInThePastException extends WebApplicationException {
    public RevisionInThePastException(String message) {
        super(message);
    }
}

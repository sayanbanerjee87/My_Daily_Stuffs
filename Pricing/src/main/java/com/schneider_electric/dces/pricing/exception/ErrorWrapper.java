package com.schneider_electric.dces.pricing.exception;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * User: FDU3285
 * Date: 29/10/2014
 * Time: 13:51
 */
@XmlRootElement
public class ErrorWrapper {
    private String error;

    public ErrorWrapper() {
    }

    public ErrorWrapper(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String message) {
        this.error = message;
    }

    @Override
    public String toString() {
        return "{" +
                "error='" + error + '\'' +
                '}';
    }
}

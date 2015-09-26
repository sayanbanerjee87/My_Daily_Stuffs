package com.schneider_electric.dces.bom.neutralFile;

/**
 * Created by porcher-g on 21/11/2014.
 */
public class NeutralFileImportException extends RuntimeException {

    public NeutralFileImportException() {
    }

    public NeutralFileImportException(String message) {
        super(message);
    }

    public NeutralFileImportException(String message, Throwable cause) {
        super(message, cause);
    }

    public NeutralFileImportException(Throwable cause) {
        super(cause);
    }

    public NeutralFileImportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

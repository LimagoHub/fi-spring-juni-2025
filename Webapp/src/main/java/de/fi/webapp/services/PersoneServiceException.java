package de.fi.webapp.services;

public class PersoneServiceException extends Exception {

    public PersoneServiceException() {
    }

    public PersoneServiceException(final String message) {
        super(message);
    }

    public PersoneServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public PersoneServiceException(final Throwable cause) {
        super(cause);
    }

    public PersoneServiceException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

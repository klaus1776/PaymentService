package edu.innotech.exception;

public class IntegrationException extends RuntimeException {
    private final String externalSystemCode;

    public IntegrationException(String message, String externalSystemCode) {
        super(message);
        this.externalSystemCode = externalSystemCode;
    }

    public String getExternalSystemCode() {
        return externalSystemCode;
    }
}

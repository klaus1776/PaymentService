package edu.innotech.dto;

public class ErrorExternalResponseDto {
    private String message;
    private String externalSystemCode;


    public String getExternalSystemCode() {
        return externalSystemCode;
    }

    public String getMessage() {
        return message;
    }

    public void setExternalSystemCode(String externalSystemCode) {
        this.externalSystemCode = externalSystemCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

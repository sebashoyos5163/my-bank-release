package com.accenture.customers.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

public class ResponseDTO {
    private String statusCode;
    private String statusMessage;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public ResponseDTO(String statusMessage, String statusCode) {
        this.statusMessage = statusMessage;
        this.statusCode = statusCode;
    }
}

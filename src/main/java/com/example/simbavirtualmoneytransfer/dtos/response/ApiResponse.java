package com.example.simbavirtualmoneytransfer.dtos.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {

    private boolean isSuccessful;

    private String message;

    public ApiResponse(boolean isSuccessful, String message) {
        this.isSuccessful = isSuccessful;
        this.message = message;
    }
}

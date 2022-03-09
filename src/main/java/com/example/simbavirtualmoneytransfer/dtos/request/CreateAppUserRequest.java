package com.example.simbavirtualmoneytransfer.dtos.request;


import lombok.Data;

@Data
public class CreateAppUserRequest {

    private String name;

    private String email;

    private String password;

}

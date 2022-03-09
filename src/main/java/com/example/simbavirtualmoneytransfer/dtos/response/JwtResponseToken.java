package com.example.simbavirtualmoneytransfer.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponseToken {

    private String JwtToken;

    private String email;

}

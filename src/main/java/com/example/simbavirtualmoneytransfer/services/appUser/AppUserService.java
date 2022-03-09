package com.example.simbavirtualmoneytransfer.services.appUser;

import com.example.simbavirtualmoneytransfer.dtos.request.CreateAppUserRequest;
import com.example.simbavirtualmoneytransfer.dtos.request.LoginRequest;
import com.example.simbavirtualmoneytransfer.dtos.response.CreateAppUserResponse;
import com.example.simbavirtualmoneytransfer.dtos.response.JwtResponseToken;

public interface AppUserService {

    CreateAppUserResponse register(CreateAppUserRequest createAppUserRequest);

    JwtResponseToken login (LoginRequest loginRequest);

}

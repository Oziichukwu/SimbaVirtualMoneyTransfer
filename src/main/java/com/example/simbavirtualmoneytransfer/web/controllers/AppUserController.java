package com.example.simbavirtualmoneytransfer.web.controllers;


import com.example.simbavirtualmoneytransfer.dtos.request.CreateAppUserRequest;
import com.example.simbavirtualmoneytransfer.dtos.request.LoginRequest;
import com.example.simbavirtualmoneytransfer.dtos.response.CreateAppUserResponse;
import com.example.simbavirtualmoneytransfer.dtos.response.JwtResponseToken;
import com.example.simbavirtualmoneytransfer.services.appUser.AppUserService;
import com.example.simbavirtualmoneytransfer.web.exceptions.EmailAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/appUser")
public class AppUserController {


    @Autowired
    private AppUserService appUserService;


    @PostMapping("/register")
    public ResponseEntity<?> register (@Valid @RequestBody CreateAppUserRequest createAppUserRequest){

        try {
            CreateAppUserResponse userResponse = appUserService.register(createAppUserRequest);
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        }catch (EmailAlreadyExistException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@Valid @RequestBody LoginRequest loginRequest){

        try {
            JwtResponseToken jwtResponseToken = appUserService.login(loginRequest);
            return new ResponseEntity<>(jwtResponseToken, HttpStatus.OK);
        }catch (EmailAlreadyExistException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

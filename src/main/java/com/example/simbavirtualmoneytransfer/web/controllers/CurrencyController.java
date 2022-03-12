package com.example.simbavirtualmoneytransfer.web.controllers;

import com.example.simbavirtualmoneytransfer.services.currency.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;


@RestController
@RequestMapping("/api/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping()
    public ResponseEntity<?>exchangeRate(){
        try {
            return new ResponseEntity<>(currencyService.getCurrencies(), HttpStatus.OK);
        }catch (RestClientException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

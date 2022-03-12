package com.example.simbavirtualmoneytransfer.web.controllers;

import com.example.simbavirtualmoneytransfer.data.models.Transaction;
import com.example.simbavirtualmoneytransfer.dtos.request.TransactionRequestDto;
import com.example.simbavirtualmoneytransfer.dtos.response.TransactionResponse;
import com.example.simbavirtualmoneytransfer.services.transaction.TransactionService;
import com.example.simbavirtualmoneytransfer.web.exceptions.SimbaVirtaulMoneyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping()
    public ResponseEntity<?>send(@RequestBody TransactionRequestDto requestDto){

        try{
            Transaction transaction = transactionService.send(requestDto);
            return new ResponseEntity<>(transaction, HttpStatus.OK);
        }catch (SimbaVirtaulMoneyException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/receive")
    public ResponseEntity<?>receive(@RequestBody TransactionRequestDto requestDto){

        try{
            TransactionResponse response = transactionService.receive(requestDto);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (SimbaVirtaulMoneyException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{appUserId}")
    public ResponseEntity<?>getAllTransactions(@PathVariable Long appUserId){
        List<Transaction> transactionList = transactionService.getAllTransaction(appUserId);
        return ResponseEntity.status(HttpStatus.OK).body(transactionList);
    }
}

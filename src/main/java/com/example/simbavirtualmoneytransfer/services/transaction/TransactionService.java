package com.example.simbavirtualmoneytransfer.services.transaction;


import com.example.simbavirtualmoneytransfer.data.models.Transaction;
import com.example.simbavirtualmoneytransfer.dtos.request.TransactionRequestDto;
import com.example.simbavirtualmoneytransfer.dtos.response.TransactionResponse;

public interface TransactionService {

    Transaction send (TransactionRequestDto requestDto);

    TransactionResponse receive(TransactionRequestDto requestDto);
}

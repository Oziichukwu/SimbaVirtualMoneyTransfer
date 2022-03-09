package com.example.simbavirtualmoneytransfer.dtos.request;

import com.example.simbavirtualmoneytransfer.data.models.Currency;
import lombok.Data;


@Data
public class TransactionRequestDto {

    private String sender;

    private String receiver;

    private Currency SourceCurrency;

    private Currency targetCurrency;

    //private double exchangeRate;

    private double amount;
}

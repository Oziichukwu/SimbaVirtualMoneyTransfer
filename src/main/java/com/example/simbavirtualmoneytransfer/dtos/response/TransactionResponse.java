package com.example.simbavirtualmoneytransfer.dtos.response;

import com.example.simbavirtualmoneytransfer.data.models.Currency;
import lombok.Builder;
import lombok.Data;

@Data
public class TransactionResponse {

   private boolean isSuccess;

   private String message;
}

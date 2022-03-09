package com.example.simbavirtualmoneytransfer.web.exceptions;

public class SimbaVirtaulMoneyException extends RuntimeException{

    public SimbaVirtaulMoneyException() {
    }

    public SimbaVirtaulMoneyException(String message) {
        super(message);
    }

    public SimbaVirtaulMoneyException(String message, Throwable cause) {
        super(message, cause);
    }

    public SimbaVirtaulMoneyException(Throwable cause) {
        super(cause);
    }
}

package com.example.simbavirtualmoneytransfer.data.models;



public enum Currency {

    USD(0),
    EUR(0),
    NGN(0);

    private int balanceAmount;


    Currency(int balanceAmount){
        this.balanceAmount = balanceAmount;
    }

    public int getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(int balanceAmount) {
        this.balanceAmount = balanceAmount;
    }
}

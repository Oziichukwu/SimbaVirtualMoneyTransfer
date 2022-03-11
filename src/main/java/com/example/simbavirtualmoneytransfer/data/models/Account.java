package com.example.simbavirtualmoneytransfer.data.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long appUserId;

    @OneToMany
    private List<Transaction> transactionList;

    private double accountBalance;

    @CreationTimestamp
    @JsonFormat(pattern = "MMMM-dddd-yyyy  HH:mm:ss")
    private LocalDateTime CreationDate;

    @UpdateTimestamp
    @JsonFormat(pattern = "MMMM-dddd-yyyy  HH:mm:ss")
    private LocalDateTime updatedDate;

    private Currency baseCurrency;

    public void addTransaction(Transaction transaction){
        if(transactionList == null){
            transactionList = new ArrayList<>();
        }
        transactionList.add(transaction);
    }


}

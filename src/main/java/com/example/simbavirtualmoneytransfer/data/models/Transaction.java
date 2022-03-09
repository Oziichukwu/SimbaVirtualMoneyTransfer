package com.example.simbavirtualmoneytransfer.data.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String sender;

    private String receiver;

    private Currency SourceCurrency;

    private Currency targetCurrency;

    private double exchangeRate;

    private double amount;

    @CreationTimestamp
    @JsonFormat(pattern = "MMMM-dddd-yyyy  HH:mm:ss")
    private LocalDateTime creationDate;

    @UpdateTimestamp
    @JsonFormat(pattern = "MMMM-dddd-yyyy  HH:mm:ss")
    private LocalDateTime updatedDate;


}

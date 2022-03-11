package com.example.simbavirtualmoneytransfer.data.repositories;

import com.example.simbavirtualmoneytransfer.data.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {


}

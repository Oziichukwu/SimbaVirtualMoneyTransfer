package com.example.simbavirtualmoneytransfer.data.repositories;

import com.example.simbavirtualmoneytransfer.data.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findAccountByAppUserId(Long appUserId);

}

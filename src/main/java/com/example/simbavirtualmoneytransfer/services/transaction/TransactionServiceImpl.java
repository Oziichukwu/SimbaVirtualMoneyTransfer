package com.example.simbavirtualmoneytransfer.services.transaction;

import com.example.simbavirtualmoneytransfer.data.models.Account;
import com.example.simbavirtualmoneytransfer.data.models.AppUser;
import com.example.simbavirtualmoneytransfer.data.models.Currency;
import com.example.simbavirtualmoneytransfer.data.models.Transaction;
import com.example.simbavirtualmoneytransfer.data.repositories.AccountRepository;
import com.example.simbavirtualmoneytransfer.data.repositories.AppUserRepository;
import com.example.simbavirtualmoneytransfer.data.repositories.TransactionRepository;
import com.example.simbavirtualmoneytransfer.dtos.request.TransactionRequestDto;
import com.example.simbavirtualmoneytransfer.dtos.response.TransactionResponse;
import com.example.simbavirtualmoneytransfer.web.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AppUserRepository appUserRepository;


    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Transaction send(TransactionRequestDto requestDto) {

        Optional<AppUser> query = appUserRepository.findById(requestDto.getAppUserId());

        if (query.isEmpty()){
            throw new UserNotFoundException("AppUser with id " +
                    requestDto.getAppUserId() + " does not exist");
        }

        AppUser existingUser = query.get();

        Account myAccount = existingUser.getMyAccount();

        if (requestDto.getAmount() < 1.00 || requestDto.getAmount() >= myAccount.getAccountBalance()){
            throw new IllegalArgumentException("Amount sent cannot be below 1.00 and above account balance");
        }

        double newBalance = myAccount.getAccountBalance() - requestDto.getAmount();

        Transaction transaction = Transaction.builder()
                .creationDate(LocalDateTime.now())
                .sender(requestDto.getSender())
                .receiver(requestDto.getReceiver())
                .SourceCurrency(requestDto.getSourceCurrency())
                .targetCurrency(requestDto.getTargetCurrency())
                .amount(requestDto.getAmount())
                .id(requestDto.getAppUserId())
                .accountBalance(newBalance)
                .build();

        transactionRepository.save(transaction);

        myAccount.addTransaction(transaction);
        myAccount.setAccountBalance(newBalance);

        accountRepository.save(myAccount);

        return new Transaction();
    }

    @Override
    public TransactionResponse receive(TransactionRequestDto requestDto) {

        Optional<AppUser> query = appUserRepository.findById(requestDto.getAppUserId());

        if (query.isEmpty()){
            throw new UserNotFoundException("AppUser with id " +
                    requestDto.getAppUserId() + " does not exist");
        }

        AppUser existingUser = query.get();

        Account myAccount = existingUser.getMyAccount();

        if (requestDto.getAmount() < 1.00 ){
            throw new IllegalArgumentException("Amount sent cannot be a negative value");
        }

        double newBalance = myAccount.getAccountBalance() + requestDto.getAmount();

        Transaction transaction = Transaction.builder()
                .creationDate(LocalDateTime.now())
                .sender(requestDto.getSender())
                .receiver(requestDto.getReceiver())
                .SourceCurrency(requestDto.getSourceCurrency())
                .targetCurrency(requestDto.getTargetCurrency())
                .amount(requestDto.getAmount())
                .id(requestDto.getAppUserId())
                .accountBalance(newBalance)
                .build();

        transactionRepository.save(transaction);

        myAccount.addTransaction(transaction);
        myAccount.setAccountBalance(newBalance);

        accountRepository.save(myAccount);

        return new TransactionResponse(true, requestDto.getAmount()
                + " was received successfully and new balance is "
                + myAccount.getAccountBalance() );
    }

    @Override
    public List<Transaction> getAllTransaction(Long appUserId) {

        Account account = accountRepository.findAccountByAppUserId(appUserId);

        return account.getTransactionList();
    }

}


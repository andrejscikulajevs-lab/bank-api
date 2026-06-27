package com.example.bank.service;

import com.example.bank.Exception.NotFoundException;
import com.example.bank.entity.*;
import com.example.bank.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final BankUserRepository bankUserRepository;
    private final TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository,
                          BankUserRepository bankUserRepository,
                          TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.bankUserRepository = bankUserRepository;
        this.transactionRepository = transactionRepository;
    }

    // Create account for user
    public Account createAccount(Long userId, Currency currency) {
        BankUser user = bankUserRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Account acc = new Account();
        acc.setBankUser(user);
        acc.setCurrency(currency);
        acc.setBalance(BigDecimal.ZERO);

        return accountRepository.save(acc);
    }

    // Get account
    public Account getAccount(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Account not found"));
    }

    public List<Account> getUserAccounts(Long userId) {
        return accountRepository.findByBankUserId(userId);
    }


    // Deposit money
    public Account deposit(Long accountId, BigDecimal amount) {
        Account acc = getAccount(accountId);

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Amount must be positive");
        }

        acc.setBalance(acc.getBalance().add(amount));

        saveTransaction(acc, amount, TransactionType.DEPOSIT);

        return accountRepository.save(acc);
    }

    // Debit money
    public Account debit(Long accountId, BigDecimal amount) {
        Account acc = getAccount(accountId);

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Amount must be positive");
        }

        if (acc.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Not enough money");
        }

        try {
            Thread.sleep(200);  // keep it simple for stability
        } catch (InterruptedException e) {
            throw new RuntimeException("External call failed");
        }

        acc.setBalance(acc.getBalance().subtract(amount));

        saveTransaction(acc, amount, TransactionType.DEBIT);

        return accountRepository.save(acc);
    }

    // Get balance
    public BigDecimal getBalance(Long id) {
        return getAccount(id).getBalance();
    }

    // Transactions
    public Transaction getTransaction(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Transaction not found"));
    }

    public Page<Transaction> getTransactions(Long accountId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return transactionRepository.findByAccountId(accountId, pageable);
    }

    // Helper method
    private void saveTransaction(Account acc, BigDecimal amount, TransactionType type) {
        Transaction tx = new Transaction();
        tx.setAccount(acc);
        tx.setAmount(amount);
        tx.setType(type);
        tx.setCreatedAt(LocalDateTime.now());

        transactionRepository.save(tx);
    }
}
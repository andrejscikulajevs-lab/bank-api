package com.example.bank.controller;

import com.example.bank.dto.AccountResponse;
import com.example.bank.entity.*;
import com.example.bank.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService
    ) {
        this.accountService = accountService;
    }

    private AccountResponse map(Account acc) {
        return new AccountResponse(
                acc.getId(),
                acc.getCurrency().name(),
                acc.getBalance()
        );
    }

    @GetMapping("/{id}")
    public AccountResponse getAccount(@PathVariable("id") Long id) {
        Account acc = accountService.getAccount(id);

        return map(acc);
    }

    @PostMapping("/{id}/deposit")
    public AccountResponse deposit(@PathVariable("id") Long id,
                           @RequestParam("amount") BigDecimal amount) {
        Account acc = accountService.deposit(id, amount);

        return map(acc);
    }

    @PostMapping("/{id}/debit")
    public AccountResponse debit(@PathVariable("id") Long id,
                         @RequestParam("amount") BigDecimal amount) {
        Account acc = accountService.debit(id, amount);

        return map(acc);
    }

    @GetMapping("/{id}/transactions")
    public List<Transaction> getTransactions(@PathVariable("id") Long id,
                                             @RequestParam(value = "page", defaultValue = "0") int page,
                                             @RequestParam(value = "size", defaultValue = "10") int size) {
        return accountService.getTransactions(id, page, size).getContent();
    }
    @GetMapping("/transactions/{id}")
    public Transaction getTransaction(@PathVariable("id") Long id) {
        return accountService.getTransaction(id);
    }

}

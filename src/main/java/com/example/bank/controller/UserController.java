package com.example.bank.controller;

import com.example.bank.entity.Account;
import com.example.bank.entity.BankUser;
import com.example.bank.entity.Currency;
import com.example.bank.repository.BankUserRepository;
import com.example.bank.service.AccountService;
import com.example.bank.service.BankUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final BankUserService bankUserService;
    private final AccountService accountService;

    // ✅ Constructor injection
    public UserController(BankUserService bankUserService, AccountService accountService) {
        this.bankUserService = bankUserService;
        this.accountService = accountService;
    }

    @GetMapping
    public List<BankUser> getAllUsers() {
        return bankUserService.getAllUsers();
    }

    @PostMapping
    public BankUser createUser(@RequestParam("name") String name) {
        return this.bankUserService.createBankUser(name);
    }

    @GetMapping("/{id}")
    public BankUser getUser(@PathVariable("id") Long id) {
        return bankUserService.getUser(id);
    }

    @GetMapping("/{id}/accounts")
    public List<Account> getUserAccounts(@PathVariable("id") Long id) {
        return accountService.getUserAccounts(id);
    }

    @PostMapping("/{id}/accounts")
    public Account createAccount(@PathVariable("id") Long id,
                                 @RequestParam("currency") Currency currency) {

        return accountService.createAccount(id, currency);
    }

}

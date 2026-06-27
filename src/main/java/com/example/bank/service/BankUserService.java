package com.example.bank.service;

import com.example.bank.entity.BankUser;
import com.example.bank.repository.BankUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankUserService {

    private final BankUserRepository bankUserRepository;

    public BankUserService(BankUserRepository bankUserRepository) {
        this.bankUserRepository = bankUserRepository;
    }
    // Create  user
    public BankUser createBankUser(String name) {
        BankUser user = new BankUser();
        user.setName(name);
        return bankUserRepository.save(user);
    }

    public List<BankUser> getAllUsers() {
        return bankUserRepository.findAll();
    }

    public BankUser getUser(Long id) {
        return bankUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

}

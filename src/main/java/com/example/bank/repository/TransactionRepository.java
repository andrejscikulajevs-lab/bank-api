package com.example.bank.repository;

import com.example.bank.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Find transactions for a specific account
    Page<Transaction> findByAccountId(Long accountId, Pageable pageable);
}
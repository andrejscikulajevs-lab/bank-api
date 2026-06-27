package com.example.bank.service;

import com.example.bank.entity.Currency;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class CurrencyService {

    // Fixed exchange rates
    private static final Map<String, BigDecimal> RATES = Map.of(
            "EUR_USD", new BigDecimal("1.1"),
            "USD_EUR", new BigDecimal("0.91"),
            "EUR_GBP", new BigDecimal("0.85"),
            "GBP_EUR", new BigDecimal("1.17")
    );

    // Convert amount from one currency to another
    public BigDecimal convert(BigDecimal amount, Currency from, Currency to) {

        // If same currency - return original value
        if (from == to) {
            return amount;
        }

        String key = from + "_" + to;

        BigDecimal rate = RATES.get(key);

        if (rate == null) {
            throw new RuntimeException("Exchange rate not defined: " + key);
        }

        return amount.multiply(rate);
    }
}
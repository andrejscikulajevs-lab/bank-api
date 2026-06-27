package com.example.bank.controller;

import com.example.bank.entity.Currency;
import com.example.bank.service.CurrencyService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currency")
@CrossOrigin(origins = "http://localhost:4200")
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService
    ) {
        this.currencyService = currencyService;
    }
    @GetMapping("/convert")
    public BigDecimal convert(@RequestParam("amount") BigDecimal amount,
                              @RequestParam("from") Currency from,
                              @RequestParam("to") Currency to) {

        return currencyService.convert(amount, from, to);
    }
}

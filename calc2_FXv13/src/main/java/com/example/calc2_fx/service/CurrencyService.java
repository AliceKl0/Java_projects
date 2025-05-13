package com.example.calc2_fx.service;


import com.example.calc2_fx.model.CurrencyRate;
import com.example.calc2_fx.repository.CurrencyRateRepository;

import java.math.BigDecimal;

public class CurrencyService {
    private CurrencyRateRepository repository = new CurrencyRateRepository();

    public BigDecimal convert(String from, String to, BigDecimal amount) {
        CurrencyRate rate = repository.findByFromAndTo(from, to);
        if (rate == null) {
            throw new IllegalArgumentException("Курс не найден");
        }
        return amount.multiply(rate.getRate());
    }
}

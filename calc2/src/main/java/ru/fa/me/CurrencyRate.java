package ru.fa.me;

import jakarta.persistence.*;
import java.math.BigDecimal;


@Entity
public class CurrencyRate {
    @Id
    @GeneratedValue
    private Long id;
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal rate;


    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFromCurrency() { return fromCurrency; }
    public void setFromCurrency(String fromCurrency) { this.fromCurrency = fromCurrency; }
    public String getToCurrency() { return toCurrency; }
    public void setToCurrency(String toCurrency) { this.toCurrency = toCurrency; }
    public BigDecimal getRate() { return rate; }
    public void setRate(BigDecimal rate) { this.rate = rate; }

}

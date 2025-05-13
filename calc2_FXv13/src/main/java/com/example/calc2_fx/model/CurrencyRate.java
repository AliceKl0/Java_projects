package com.example.calc2_fx.model;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.math.BigDecimal;

import javafx.beans.property.*;

public class CurrencyRate {
    private final StringProperty fromCurrency = new SimpleStringProperty();
    private final StringProperty toCurrency = new SimpleStringProperty();
    private final ObjectProperty<BigDecimal> rate = new SimpleObjectProperty<>();

    public CurrencyRate(String from, String to, BigDecimal rate) {
        setFromCurrency(from);
        setToCurrency(to);
        setRate(rate);
    }

    // Геттеры свойств
    public StringProperty fromCurrencyProperty() {
        return fromCurrency;
    }

    public StringProperty toCurrencyProperty() {
        return toCurrency;
    }

    public ObjectProperty<BigDecimal> rateProperty() {
        return rate;
    }

    // Стандартные геттеры и сеттеры
    public String getFromCurrency() {
        return fromCurrency.get();
    }

    public void setFromCurrency(String value) {
        fromCurrency.set(value);
    }

    public String getToCurrency() {
        return toCurrency.get();
    }

    public void setToCurrency(String value) {
        toCurrency.set(value);
    }

    public BigDecimal getRate() {
        return rate.get();
    }

    public void setRate(BigDecimal value) {
        rate.set(value);
    }
}

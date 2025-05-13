package ru.fa.me;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long> {
    CurrencyRate findByFromCurrencyAndToCurrency(String from, String to);

    @Query("SELECT DISTINCT c.fromCurrency FROM CurrencyRate c UNION SELECT DISTINCT c.toCurrency FROM CurrencyRate c")
    List<String> findAllDistinctCurrencies();

    boolean existsByFromCurrencyAndToCurrency(String from, String to);
}

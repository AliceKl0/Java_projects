package ru.fa.me;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.Principal;
import java.sql.Timestamp;


@Controller
@RequestMapping("/calculate")
public class CalculatorController {

    @Autowired
    private CurrencyRateRepository currencyRepo;

    @Autowired
    private CalculationHistoryRepository historyRepo;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/currency")
    public String currencyForm(Model model) {
        model.addAttribute("currencies", currencyRepo.findAllDistinctCurrencies());
        return "currency-converter";
    }

    @PostMapping("/currency")
    public String convertCurrency(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam BigDecimal amount,
            Principal principal,
            Model model) {

        model.addAttribute("currencies", currencyRepo.findAllDistinctCurrencies());

        try {
            CurrencyRate rate = currencyRepo.findByFromCurrencyAndToCurrency(from, to);
            if (rate == null) {
                model.addAttribute("error", "Курс не найден");
                return "currency-converter";
            }

            BigDecimal result = amount.multiply(rate.getRate());

            // Сохранение в историю
            User user = userRepo.findByUsername(principal.getName());
            CalculationHistory history = new CalculationHistory();
            history.setUser(user);
            history.setOperationType("CURRENCY");
            history.setDetails(String.format("%.2f %s → %.2f %s", amount, from, result, to));
            history.setTimestamp(new Timestamp(System.currentTimeMillis()));
            historyRepo.save(history);

            model.addAttribute("from", from);
            model.addAttribute("to", to);
            model.addAttribute("amount", amount);
            model.addAttribute("result", result);

        } catch (Exception e) {
            model.addAttribute("error", "Ошибка: " + e.getMessage());
        }

        return "currency-converter";
    }

    @PostMapping("/ohm")
    public String calculateOhmLaw(
            @RequestParam(required = false) BigDecimal voltage,
            @RequestParam(required = false) BigDecimal current,
            @RequestParam(required = false) BigDecimal resistance,
            Principal principal,
            Model model) {

        // Проверка заполнения двух полей
        int count = 0;
        if (voltage != null) count++;
        if (current != null) count++;
        if (resistance != null) count++;

        if (count != 2) {
            model.addAttribute("error", "Заполните ровно два поля");
            return "ohm-law";
        }

        // Вычисления
        String details;
        if (voltage == null) {
            voltage = current.multiply(resistance);
            details = String.format("V = I * R = %s * %s = %s", current, resistance, voltage);
        } else if (current == null) {
            current = voltage.divide(resistance, 2, RoundingMode.HALF_UP);
            details = String.format("I = V / R = %s / %s = %s", voltage, resistance, current);
        } else {
            resistance = voltage.divide(current, 2, RoundingMode.HALF_UP);
            details = String.format("R = V / I = %s / %s = %s", voltage, current, resistance);
        }

        // Сохранение в историю
        User user = userRepo.findByUsername(principal.getName());
        CalculationHistory history = new CalculationHistory();
        history.setUser(user);
        history.setOperationType("OHM_LAW");
        history.setDetails(details);
        history.setTimestamp(new Timestamp(System.currentTimeMillis()));
        historyRepo.save(history);

        model.addAttribute("voltage", voltage);
        model.addAttribute("current", current);
        model.addAttribute("resistance", resistance);
        return "ohm-law";
    }

    @GetMapping("/ohm")
    public String ohmForm(Model model) {
        model.addAttribute("voltage", BigDecimal.ZERO);
        model.addAttribute("current", BigDecimal.ZERO);
        model.addAttribute("resistance", BigDecimal.ZERO);
        return "ohm-law";
    }

}

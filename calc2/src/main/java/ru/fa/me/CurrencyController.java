package ru.fa.me;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;

@Controller
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    private CurrencyRateRepository currencyRepo;

    @GetMapping
    public String manageCurrencies(Model model) {
        model.addAttribute("rates", currencyRepo.findAll());
        model.addAttribute("newRate", new CurrencyRate());
        return "currency-management";
    }

    @PostMapping
    public String addRate(@ModelAttribute CurrencyRate newRate, Model model) {
        if (currencyRepo.existsByFromCurrencyAndToCurrency(newRate.getFromCurrency(), newRate.getToCurrency())) {
            model.addAttribute("error", "Курс уже существует");
            model.addAttribute("rates", currencyRepo.findAll());
            model.addAttribute("newRate", newRate);
            return "currency-management";
        }
        currencyRepo.save(newRate);
        return "redirect:/currency";
    }

    @PostMapping("/update")
    public String updateRate(@RequestParam Long id, @RequestParam BigDecimal rate) {
        CurrencyRate existing = currencyRepo.findById(id).orElseThrow();
        existing.setRate(rate);
        currencyRepo.save(existing);
        return "redirect:/currency";
    }

    @PostMapping("/currency")
    public String convertCurrency(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam BigDecimal amount,
            Principal principal,
            Model model) {

        model.addAttribute("currencies", currencyRepo.findAllDistinctCurrencies());

        // ... существующая логика ...

        model.addAttribute("from", from);
        model.addAttribute("to", to);
        model.addAttribute("amount", amount);

        return "currency-converter";
    }

    @PostMapping("/delete")
    public String deleteRate(@RequestParam Long id) {
        currencyRepo.deleteById(id);
        return "redirect:/currency";
    }
}

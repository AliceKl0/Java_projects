package ru.fa.me;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@Controller
public class HistoryController {

    @Autowired
    private CalculationHistoryRepository historyRepo;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/history")
    public String viewHistory(Principal principal, Model model) {
        String username = principal.getName();
        User user = userRepo.findByUsername(username);

        if (user == null) return "redirect:/login";

        boolean isAdmin = "ROLE_ADMIN".equals(user.getRole());

        List<CalculationHistory> history = isAdmin
                ? historyRepo.findAllWithUser()
                : historyRepo.findByUserOrderByTimestampDesc(user);

        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("history", history);
        return "history";
    }
}


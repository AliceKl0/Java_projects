package ru.fa.me;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CalculationHistoryRepository historyRepo;

    @GetMapping
    public String userManagement(Principal principal, Model model) {
        User currentUser = userRepo.findByUsername(principal.getName());
        List<User> users = userRepo.findAll();

        model.addAttribute("users", users);
        model.addAttribute("currentUserId", currentUser.getId());
        return "user-management";
    }

    @Transactional // Добавляем аннотацию
    @PostMapping("/delete")
    public String deleteUser(@RequestParam Long userId, Principal principal) {
        User currentUser = userRepo.findByUsername(principal.getName());

        if (currentUser.getId().equals(userId)) {
            throw new IllegalArgumentException("Администратор не может удалить себя");
        }

        // Удаляем историю операций
        historyRepo.deleteByUserId(userId);

        // Удаляем пользователя
        userRepo.deleteUserByIdIfNotAdmin(userId);

        return "redirect:/admin/users";
    }
}

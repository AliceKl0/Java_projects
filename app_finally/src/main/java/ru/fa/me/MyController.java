package ru.fa.me;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam; // Для получения параметров из HTTP-запроса.

@org.springframework.stereotype.Controller
public class MyController {
    @Autowired
    EmployeeService employeeService;

    // Основная страница с параметром для редактирования
    @GetMapping("/")
    public String index(
            @RequestParam(name = "editId", required = false) Integer editId, // Принимаем параметр из URL для активации редактирования
            Model model
    ) {
        model.addAttribute("rows", employeeService.getAllEmployees());
        model.addAttribute("new_employee", new Employee());
        model.addAttribute("editId", editId); // Передаем ID редактируемой записи в шаблон
        return "index";
    }

    // Добавление нового сотрудника
    @PostMapping("/add")
    String add(@ModelAttribute Employee new_employee) {
        employeeService.save(new_employee);
        return "redirect:/";
    }

    // Удаление сотрудника
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        employeeService.delete(id);
        return "redirect:/";
    }

    // Активация режима редактирования
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id) {
        // Перенаправляем на главную страницу с параметром editId
        return "redirect:/?editId=" + id; // Передаем ID в URL для активации формы
    }

    // Обработка обновления данных
    @PostMapping("/update/{id}")
    public String update(
            @PathVariable int id, // Получаем ID из URL
            @RequestParam String name // Получаем новое имя из формы
    ) {
        employeeService.update(id, name); // Обновляем запись в базе
        return "redirect:/"; // Сбрасываем параметр editId после обновления
    }
}
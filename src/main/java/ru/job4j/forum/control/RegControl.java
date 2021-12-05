package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.UserService;

/**
 * Класс RegControl
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Controller
public class RegControl {

    private final UserService userService;

    public RegControl(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user, Model model) {
        if (userService.findUserByEmail(user.getEmail()) != null) {
            String errorMessage = "Пользователь с данной почтой уже зарегистрирован!!!";
            model.addAttribute("errorMessage", errorMessage);
            return "reg";
        }
        userService.saveUser(user);
        return "redirect:/login";
    }
}

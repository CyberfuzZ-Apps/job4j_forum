package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.UserService;

/**
 * Класс LoginControl
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Controller
@SessionAttributes(value = "username")
public class LoginControl {

    private final UserService userService;

    public LoginControl(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            Model model) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "Почта или пароль не верные!!!";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

    @PostMapping("/login")
    public String enter(@ModelAttribute User user, Model model) {
        User savedUser = userService.findUserByEmail(user.getEmail());
        if (savedUser != null
                && savedUser.getEmail().equals(user.getEmail())
                && savedUser.getPassword().equals(user.getPassword())) {
            user.setUsername(savedUser.getUsername());
            model.addAttribute("username", user.getUsername());
            return "redirect:/index?login=true&username=" + user.getUsername();
        }
        String errorMessage = "Почта или пароль не верные!!!";
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }
}

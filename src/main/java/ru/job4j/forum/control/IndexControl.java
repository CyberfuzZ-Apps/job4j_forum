package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.service.PostService;

/**
 * Класс IndexControl
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Controller
public class IndexControl {

    private final PostService posts;

    public IndexControl(PostService posts) {
        this.posts = posts;
    }

    @GetMapping({"/", "/index"})
    public String index(@RequestParam(value = "login", required = false) String login,
                        @RequestParam(value = "username", required = false) String username,
                        Model model) {
        if (login != null) {
            model.addAttribute("username", username);
            model.addAttribute("posts", posts.findAll());
            return "index";
        }
        return "login";
    }
}

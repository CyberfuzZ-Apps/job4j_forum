package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.store.UserRepository;

/**
 * Класс IndexControl
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Controller
@SessionAttributes({"user", "username", "myUser"})
public class IndexControl {

    private final PostService posts;
    private final UserRepository users;

    public IndexControl(PostService posts, UserRepository users) {
        this.posts = posts;
        this.users = users;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        ru.job4j.forum.model.User myUser = users.findByUsername(user.getUsername());
        model.addAttribute("myUser", myUser);
        model.addAttribute("username", myUser.getName());
        model.addAttribute("user", user);
        model.addAttribute("posts", posts.findAll());
        return "index";
    }
}

package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

/**
 * Класс EditControl
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Controller
public class EditControl {

    private final PostService postService;

    public EditControl(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam(value = "id", defaultValue = "0", required = false) int id,
                           Model model) {
        if (id != 0) {
            Post post = postService.findById(id);
            model.addAttribute("post", post);
        }
        return "edit";
    }

}

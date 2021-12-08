package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Answer;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

/**
 * Класс PostControl
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Controller
public class PostControl {

    private final PostService postService;

    public PostControl(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/save")
    public String savePost(@ModelAttribute Post post) {
        postService.save(post);
        return "redirect:/index?login=true&username=" + post.getAuthor();
    }

    @GetMapping("/delete")
    public String deletePost(@RequestParam(value = "id", required = false) int id,
                             @RequestParam(value = "username", required = false) String username) {
        postService.delete(id);
        return "redirect:/index?login=true&username=" + username;
    }

    @GetMapping("/post")
    public String showPost(@RequestParam(value = "id", required = false) int id,
                           @RequestParam(value = "username", required = false) String username,
                           Model model) {
        Post postById = postService.findById(id);
        model.addAttribute("post", postById);
        model.addAttribute("username", username);
        return "post";
    }

    @PostMapping("/saveAnswer")
    public String saveAnswer(@ModelAttribute Answer answer,
                             @RequestParam(value = "post_id", required = false) int postId) {
        postService.addAnswerToPost(postId, answer);
        return "redirect:/post?id=" + postId;
    }
}

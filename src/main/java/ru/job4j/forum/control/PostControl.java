package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Answer;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.store.UserRepository;

/**
 * Класс PostControl
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Controller
public class PostControl {

    private final PostService postService;
    private final UserRepository users;

    public PostControl(PostService postService, UserRepository users) {
        this.postService = postService;
        this.users = users;
    }

    @PostMapping("/save")
    public String savePost(@ModelAttribute Post post) {
        User user = users.findByUsername(post.getAuthor());
        post.setNickname(user.getName());
        postService.save(post);
        return "redirect:/index";
    }

    @GetMapping("/delete")
    public String deletePost(@RequestParam(value = "id", required = false) int id) {
        postService.delete(id);
        return "redirect:/index?login=true";
    }

    @GetMapping("/post")
    public String showPost(@RequestParam(value = "id", required = false) int id,
                           Model model) {
        Post postById = postService.findById(id);
        model.addAttribute("post", postById);
        return "post";
    }

    @PostMapping("/saveAnswer")
    public String saveAnswer(@ModelAttribute Answer answer,
                             @RequestParam(value = "post_id", required = false) int postId) {
        postService.addAnswerToPost(postId, answer);
        return "redirect:/post?id=" + postId;
    }

    @GetMapping("/deleteAnswer")
    public String deleteAnswer(@RequestParam(value = "answer_id", required = false) int answerId,
                               @RequestParam(value = "post_id", required = false) int postId) {
        postService.deleteAnswer(answerId, postId);
        return "redirect:/post?id=" + postId;
    }
}

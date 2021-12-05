package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Answer;
import ru.job4j.forum.model.Post;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Класс PostService
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Service
public class PostService {

    private final Map<Integer, Post> posts = new HashMap<>();
    private final AtomicInteger count = new AtomicInteger();

    public PostService() {
        int id = count.incrementAndGet();
        Post post = Post.of(id, "Горячие клавиши в IntelliJ IDEA",
                "Admin",
                "Какое есть сочетание клавиш, чтобы извлечь переменную из "
                        + "возвращаемого значения метода?");
        post.addAnswer(Answer.of(
                1,
                "Если на винде, то попробуй Ctrl + Alt + V",
                "Евгений"));
        post.addAnswer(Answer.of(
                2,
                "Если ты на маке, то попробуй ⌘ + ⌥ + V",
                "Николай"));
        post.addAnswer(Answer.of(
                3,
                "Можно самому настроить горячие клавиши в настройках Идеи",
                "Иван"));
        posts.put(id, post);
    }

    public void save(Post post) {
        Post postById = findById(post.getId());
        if (postById == null) {
            post.setId(count.incrementAndGet());
        }
        posts.put(post.getId(), post);
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public void delete(int id) {
        posts.remove(id);
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public void addAnswerToPost(int postId, Answer answer) {
        Post post = findById(postId);
        if (post != null) {
            post.addAnswer(answer);
        }
    }
}

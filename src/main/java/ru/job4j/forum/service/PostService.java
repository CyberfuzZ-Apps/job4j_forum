package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.forum.model.Answer;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.store.AnswerRepository;
import ru.job4j.forum.store.PostRepository;

import java.util.*;

/**
 * Класс PostService
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Service
public class PostService {

    private final PostRepository posts;
    private final AnswerRepository answers;

    public PostService(PostRepository posts, AnswerRepository answers) {
        this.posts = posts;
        this.answers = answers;
    }

    @Transactional
    public void save(Post post) {
        if (posts.existsById(post.getId())) {
            posts.update(
                    post.getId(),
                    post.getAuthor(),
                    post.getDescription(),
                    post.getName(),
                    post.getCreated());
        } else {
            posts.save(post);
        }
    }

    public Post findById(int id) {
        Optional<Post> post = posts.findById(id);
        return post.orElse(null);
    }

    public void delete(int id) {
        posts.deleteById(id);
    }

    public Collection<Post> findAll() {
        return (Collection<Post>) posts.findAll();
    }

    @Transactional
    public void addAnswerToPost(int postId, Answer answer) {
        Answer savedAnswer = answers.save(answer);
        Optional<Post> post = posts.findById(postId);
        post.ifPresent(value -> value.addAnswer(savedAnswer));
    }

}

package ru.job4j.forum.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Класс Post
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public class Post {

    private int id;
    private String name;
    private String author;
    private String description;
    private Timestamp created = Timestamp.valueOf(LocalDateTime.now());
    private List<Answer> answers = new ArrayList<>();

    public static Post of(int id, String name, String author, String description) {
        Post post = new Post();
        post.id = id;
        post.name = name;
        post.author = author;
        post.description = description;
        return post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Post{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", author='" + author + '\''
                + ", description='" + description + '\''
                + ", created=" + created
                + '}';
    }
}

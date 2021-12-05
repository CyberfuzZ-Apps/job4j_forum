package ru.job4j.forum.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Класс Answer
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public class Answer {

    private int id;
    private String answer;
    private String author;
    private Timestamp created = Timestamp.valueOf(LocalDateTime.now());

    public static Answer of(int id, String answer, String author) {
        Answer answer1 = new Answer();
        answer1.id = id;
        answer1.answer = answer;
        answer1.author = author;
        return answer1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Answer answer = (Answer) o;
        return id == answer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Answer{"
                + "id=" + id
                + ", answer='" + answer + '\''
                + ", author='" + author + '\''
                + ", created=" + created
                + '}';
    }
}

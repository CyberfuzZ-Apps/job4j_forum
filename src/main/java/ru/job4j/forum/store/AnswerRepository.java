package ru.job4j.forum.store;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.Answer;

/**
 * Класс AnswerRepository
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public interface AnswerRepository extends CrudRepository<Answer, Integer> {

}

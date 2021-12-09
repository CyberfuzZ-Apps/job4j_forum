package ru.job4j.forum.store;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.User;

/**
 * Класс UserRepository
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String email);
}

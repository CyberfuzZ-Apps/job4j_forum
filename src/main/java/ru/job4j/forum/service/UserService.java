package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.forum.model.User;
import ru.job4j.forum.store.UserRepository;

/**
 * Класс UserService
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Service
@Transactional
public class UserService {

    private final UserRepository users;

    public UserService(UserRepository users) {
        this.users = users;
    }

    public void saveUser(User user) {
        User userByEmail = findUserByEmail(user.getEmail());
        if (userByEmail == null) {
            users.save(user);
        }
    }

    public User findUserByEmail(String email) {
        return users.findUserByEmail(email);
    }

}

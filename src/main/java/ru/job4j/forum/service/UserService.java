package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Класс UserService
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Service
public class UserService {

    private final Map<String, User> users = new HashMap<>();
    private final AtomicInteger count = new AtomicInteger();

    public UserService() {
        users.put("root@local", User.of(
                count.getAndIncrement(),
                "Admin",
                "root@local",
                "root"));
    }

    public void saveUser(User user) {
        User userByEmail = findUserByEmail(user.getEmail());
        if (userByEmail == null) {
            user.setId(count.getAndIncrement());
        }
        users.put(user.getEmail(), user);
    }

    public User findUserByEmail(String email) {
        return users.get(email);
    }

}

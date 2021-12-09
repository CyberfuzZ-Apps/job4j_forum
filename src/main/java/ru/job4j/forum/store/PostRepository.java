package ru.job4j.forum.store;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.forum.model.Post;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Optional;

/**
 * Класс PostRepository
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public interface PostRepository extends CrudRepository<Post, Integer> {

    @Modifying
    @Query(value = "update Post p set p.author = :author, p.description = :description, "
            + "p.name = :name, p.created = :created where p.id = :id")
    void update(@Param("id") int id,
                @Param("author") String author,
                @Param("description") String description,
                @Param("name") String name,
                @Param("created") Timestamp created);

}

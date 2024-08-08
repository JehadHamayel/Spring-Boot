package com.jehadscode.database.repositories;

import com.jehadscode.database.domain.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    Iterable<Author> ageLessThan(int age);

    @Query("SELECT a from Author a WHERE a.age > ?1")
    Iterable<Author> getAuthorsAgeGreaterThan(int age);
}

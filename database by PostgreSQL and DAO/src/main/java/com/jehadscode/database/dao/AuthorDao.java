package com.jehadscode.database.dao;

import com.jehadscode.database.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    void createAuthor(Author author);
    Optional<Author> findOneAuthor(long l);
    List<Author> findAllAuthors();

    void updateAuthor(long id, Author author);
}

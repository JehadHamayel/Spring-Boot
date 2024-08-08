package com.jehadscode.database.dao;

import com.jehadscode.database.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    void createBook(Book book);
    Optional<Book> findOneBook(String isbn);
    List<Book> findAllBooks();

    void updateBook(String number, Book book);

    void deleteBook(String number);
}

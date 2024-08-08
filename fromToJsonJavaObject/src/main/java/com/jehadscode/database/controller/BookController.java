package com.jehadscode.database.controller;

import com.jehadscode.database.domain.Book;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class BookController {

    @GetMapping(path = "/books")
    public Book getBook() {
        log.info("Returning book");
        return Book.builder()
                .isbn("1234")
                .title("Spring Boot")
                .author("Jehad")
                .yearPublished("2021")
                .build();
    }

    @PostMapping(path = "/books")
    public Book createBook(@RequestBody final Book book) {
        log.info("Creating book:" + book.toString());
        return book;
    }
}

package com.jehadscode.database.controllers;

import com.jehadscode.database.domain.dao.AuthorDao;
import com.jehadscode.database.domain.entity.AuthorEntity;
import com.jehadscode.database.mappers.Mapper;
import com.jehadscode.database.services.AuthorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {


    private AuthorService authorService;
    private Mapper<AuthorEntity,AuthorDao> authorMapper;

    public AuthorController(AuthorService authorService, Mapper<AuthorEntity, AuthorDao> authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @PostMapping("/authors")
    public AuthorDao createAuthor(@RequestBody AuthorDao authorDao) {
        AuthorEntity authorEntity = authorMapper.mapFrom(authorDao);

        AuthorEntity savedAuthorEntity =  authorService.createAuthor(authorEntity);

        return authorMapper.mapTo(savedAuthorEntity);
    }
}

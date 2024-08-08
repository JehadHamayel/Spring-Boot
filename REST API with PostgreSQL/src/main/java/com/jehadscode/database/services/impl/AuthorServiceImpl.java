package com.jehadscode.database.services.impl;


import com.jehadscode.database.domain.entity.AuthorEntity;
import com.jehadscode.database.repositories.AuthorRepository;
import com.jehadscode.database.services.AuthorService;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity createAuthor(AuthorEntity author) {
        return authorRepository.save(author);
    }



}

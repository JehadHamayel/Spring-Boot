package com.jehadscode.database.mappers.impl;

import com.jehadscode.database.domain.dao.AuthorDao;
import com.jehadscode.database.domain.entity.AuthorEntity;
import com.jehadscode.database.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper implements Mapper<AuthorEntity, AuthorDao> {

    private ModelMapper modelMapper;

    public AuthorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthorDao mapTo(AuthorEntity authorEntity) {
        return modelMapper.map(authorEntity, AuthorDao.class);
    }

    @Override
    public AuthorEntity mapFrom(AuthorDao authorDao) {
        return modelMapper.map(authorDao, AuthorEntity.class);
    }
}

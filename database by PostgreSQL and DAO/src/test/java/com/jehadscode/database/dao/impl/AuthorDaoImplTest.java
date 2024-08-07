package com.jehadscode.database.dao.impl;

import com.jehadscode.database.TestDataUtil;
import com.jehadscode.database.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthorDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDaoImpl authorDaoImpl;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSql() {
        Author author = TestDataUtil.createTestAuthorA();

        authorDaoImpl.createAuthor(author);

        verify(jdbcTemplate).update(
                eq("INSERT INTO authors (id, name, age) VALUES (?, ?, ?)"),
                eq(author.getId()), eq(author.getName()), eq(author.getAge()));

    }

    @Test
    public void testThatFindOneAuthorGeneratesCorrectSql() {
        authorDaoImpl.findOneAuthor(1L);

        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
                eq(1L));
    }

    @Test
    public void testThatFindMultipleAuthorsGeneratesCorrectSql() {
        authorDaoImpl.findAllAuthors();
        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any());
    }

    @Test
    public void testThatUpdateAuthorGeneratesCorrectSql() {
        Author author = TestDataUtil.createTestAuthorA();

        authorDaoImpl.updateAuthor(3L,author);

        verify(jdbcTemplate).update(
                "UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?",
                1L, author.getName(), author.getAge(), 3L);
    }

}
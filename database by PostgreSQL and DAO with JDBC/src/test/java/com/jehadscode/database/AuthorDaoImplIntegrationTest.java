package com.jehadscode.database;

import com.jehadscode.database.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.jehadscode.database.dao.impl.AuthorDaoImpl;
import java.util.List;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorDaoImplIntegrationTest {

    private AuthorDaoImpl authorDaoImpl;

    @Autowired
    public AuthorDaoImplIntegrationTest(AuthorDaoImpl authorDaoImpl) {
        this.authorDaoImpl = authorDaoImpl;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createTestAuthorA();
        authorDaoImpl.createAuthor(author);

        Optional<Author> result = authorDaoImpl.findOneAuthor(author.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);

    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled() {
        Author author1 = TestDataUtil.createTestAuthorA();
        authorDaoImpl.createAuthor(author1);

        Author author2 = TestDataUtil.createTestAuthorB();
        authorDaoImpl.createAuthor(author2);

        Author author3 = TestDataUtil.createTestAuthorC();
        authorDaoImpl.createAuthor(author3);

        List<Author> authors = authorDaoImpl.findAllAuthors();

        assertThat(authors)
                .hasSize(3)
                .containsExactly(author1, author2, author3);

    }

    @Test
    public void testThatAuthorCanUpdated() {
        Author author = TestDataUtil.createTestAuthorA();
        authorDaoImpl.createAuthor(author);
        author.setName("New Name");
        author.setAge(30);
        authorDaoImpl.updateAuthor(author.getId(), author);
        Optional<Author> result = authorDaoImpl.findOneAuthor(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatAuthorCanBeDeleted() {
        Author author = TestDataUtil.createTestAuthorA();
        authorDaoImpl.createAuthor(author);

        authorDaoImpl.deleteAuthor(author.getId());
        Optional<Author> result = authorDaoImpl.findOneAuthor(author.getId());
        assertThat(result).isEmpty();
    }

}

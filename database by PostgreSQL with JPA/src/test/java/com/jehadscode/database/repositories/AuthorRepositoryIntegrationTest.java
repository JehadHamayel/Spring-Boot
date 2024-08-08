package com.jehadscode.database.repositories;

import com.jehadscode.database.TestDataUtil;
import com.jehadscode.database.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorRepositoryIntegrationTest {

    private AuthorRepository underTheTest;

    @Autowired
    public AuthorRepositoryIntegrationTest(AuthorRepository underTheTest) {
        this.underTheTest = underTheTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createTestAuthorA();
        underTheTest.save(author);

        Optional<Author> result = underTheTest.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);

    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled() {
        Author author1 = TestDataUtil.createTestAuthorA();
        underTheTest.save(author1);

        Author author2 = TestDataUtil.createTestAuthorB();
        underTheTest.save(author2);

        Author author3 = TestDataUtil.createTestAuthorC();
        underTheTest.save(author3);

        Iterable<Author> authors = underTheTest.findAll();

        assertThat(authors)
                .hasSize(3)
                .containsExactly(author1, author2, author3);

    }

    @Test
    public void testThatAuthorCanUpdated() {
        Author author = TestDataUtil.createTestAuthorA();
        underTheTest.save(author);
        author.setName("New Name");
        author.setAge(30);
        underTheTest.save(author);
        Optional<Author> result = underTheTest.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatAuthorCanBeDeleted() {
        Author author = TestDataUtil.createTestAuthorA();
        underTheTest.save(author);

        underTheTest.deleteById(author.getId());
        Optional<Author> result = underTheTest.findById(author.getId());
        assertThat(result).isEmpty();
    }

    @Test
    public void testThatGetAuthorsAgeLessThan() {
        Author author1 = TestDataUtil.createTestAuthorA();
        underTheTest.save(author1);

        Author author2 = TestDataUtil.createTestAuthorB();
        underTheTest.save(author2);

        Author author3 = TestDataUtil.createTestAuthorC();
        underTheTest.save(author3);

        Iterable<Author> authors = underTheTest.ageLessThan(23);

        assertThat(authors)
                .hasSize(2)
                .containsExactly(author1, author2);
    }

    @Test
    public void testThatGetAuthorsAgeGreaterThan() {
        Author author1 = TestDataUtil.createTestAuthorA();
        underTheTest.save(author1);

        Author author2 = TestDataUtil.createTestAuthorB();
        underTheTest.save(author2);

        Author author3 = TestDataUtil.createTestAuthorC();
        underTheTest.save(author3);

        Iterable<Author> authors = underTheTest.getAuthorsAgeGreaterThan(23);

        assertThat(authors)
                .hasSize(1)
                .containsExactly(author3);
    }
}

package com.jehadscode.database.repositories;

import com.jehadscode.database.TestDataUtil;
import com.jehadscode.database.domain.entity.AuthorEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorEntityRepositoryIntegrationTest {

    private AuthorRepository underTheTest;

    @Autowired
    public AuthorEntityRepositoryIntegrationTest(AuthorRepository underTheTest) {
        this.underTheTest = underTheTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        underTheTest.save(authorEntity);

        Optional<AuthorEntity> result = underTheTest.findById(authorEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorEntity);

    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled() {
        AuthorEntity authorEntity1 = TestDataUtil.createTestAuthorA();
        underTheTest.save(authorEntity1);

        AuthorEntity authorEntity2 = TestDataUtil.createTestAuthorB();
        underTheTest.save(authorEntity2);

        AuthorEntity authorEntity3 = TestDataUtil.createTestAuthorC();
        underTheTest.save(authorEntity3);

        Iterable<AuthorEntity> authors = underTheTest.findAll();

        assertThat(authors)
                .hasSize(3)
                .containsExactly(authorEntity1, authorEntity2, authorEntity3);

    }

    @Test
    public void testThatAuthorCanUpdated() {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        underTheTest.save(authorEntity);
        authorEntity.setName("New Name");
        authorEntity.setAge(30);
        underTheTest.save(authorEntity);
        Optional<AuthorEntity> result = underTheTest.findById(authorEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorEntity);
    }

    @Test
    public void testThatAuthorCanBeDeleted() {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        underTheTest.save(authorEntity);

        underTheTest.deleteById(authorEntity.getId());
        Optional<AuthorEntity> result = underTheTest.findById(authorEntity.getId());
        assertThat(result).isEmpty();
    }

    @Test
    public void testThatGetAuthorsAgeLessThan() {
        AuthorEntity authorEntity1 = TestDataUtil.createTestAuthorA();
        underTheTest.save(authorEntity1);

        AuthorEntity authorEntity2 = TestDataUtil.createTestAuthorB();
        underTheTest.save(authorEntity2);

        AuthorEntity authorEntity3 = TestDataUtil.createTestAuthorC();
        underTheTest.save(authorEntity3);

        Iterable<AuthorEntity> authors = underTheTest.ageLessThan(23);

        assertThat(authors)
                .hasSize(2)
                .containsExactly(authorEntity1, authorEntity2);
    }

    @Test
    public void testThatGetAuthorsAgeGreaterThan() {
        AuthorEntity authorEntity1 = TestDataUtil.createTestAuthorA();
        underTheTest.save(authorEntity1);

        AuthorEntity authorEntity2 = TestDataUtil.createTestAuthorB();
        underTheTest.save(authorEntity2);

        AuthorEntity authorEntity3 = TestDataUtil.createTestAuthorC();
        underTheTest.save(authorEntity3);

        Iterable<AuthorEntity> authors = underTheTest.getAuthorsAgeGreaterThan(23);

        assertThat(authors)
                .hasSize(1)
                .containsExactly(authorEntity3);
    }
}

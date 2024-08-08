package com.jehadscode.database.repositories;

import com.jehadscode.database.TestDataUtil;
import com.jehadscode.database.domain.entity.AuthorEntity;
import com.jehadscode.database.domain.entity.BookEntity;
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
public class BookEntityRepositoryIntegrationTest {

    private BookRepository underTheTest;

    @Autowired
    public BookEntityRepositoryIntegrationTest(BookRepository underTheTest) {
        this.underTheTest = underTheTest;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        BookEntity bookEntity = TestDataUtil.createTestBookA(authorEntity);
        underTheTest.save(bookEntity);

        Optional<BookEntity> result = underTheTest.findById(bookEntity.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookEntity);

    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled() {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();

        BookEntity bookEntity1 = TestDataUtil.createTestBookA(authorEntity);
        underTheTest.save(bookEntity1);

        BookEntity bookEntity2 = TestDataUtil.createTestBookB(authorEntity);
        underTheTest.save(bookEntity2);

        BookEntity bookEntity3 = TestDataUtil.createTestBookC(authorEntity);
        underTheTest.save(bookEntity3);

        Iterable<BookEntity> result = underTheTest.findAll();

        assertThat(result).hasSize(3).containsExactly(bookEntity1, bookEntity2, bookEntity3);
    }

    @Test
    public void testThatBookCanBeUpdated() {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();

        BookEntity bookEntity = TestDataUtil.createTestBookA(authorEntity);
        underTheTest.save(bookEntity);
        bookEntity.setTitle("New Title");
        underTheTest.save(bookEntity);

        Optional<BookEntity> result = underTheTest.findById(bookEntity.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookEntity);
    }

    @Test
    public void testThatBookCanBeDeleted() {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();

        BookEntity bookEntity = TestDataUtil.createTestBookA(authorEntity);
        underTheTest.save(bookEntity);

        underTheTest.deleteById(bookEntity.getIsbn());
        Optional<BookEntity> result = underTheTest.findById(bookEntity.getIsbn());

        assertThat(result).isEmpty();
    }
}

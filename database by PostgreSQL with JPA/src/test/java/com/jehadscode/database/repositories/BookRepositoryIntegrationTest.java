package com.jehadscode.database.repositories;

import com.jehadscode.database.TestDataUtil;
import com.jehadscode.database.domain.Author;
import com.jehadscode.database.domain.Book;
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
public class BookRepositoryIntegrationTest {

    private BookRepository underTheTest;

    @Autowired
    public BookRepositoryIntegrationTest(BookRepository underTheTest) {
        this.underTheTest = underTheTest;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createTestAuthorA();
        Book book = TestDataUtil.createTestBookA(author);
        underTheTest.save(book);

        Optional<Book> result = underTheTest.findById(book.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);

    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createTestAuthorA();

        Book book1 = TestDataUtil.createTestBookA(author);
        underTheTest.save(book1);

        Book book2 = TestDataUtil.createTestBookB(author);
        underTheTest.save(book2);

        Book book3 = TestDataUtil.createTestBookC(author);
        underTheTest.save(book3);

        Iterable<Book> result = underTheTest.findAll();

        assertThat(result).hasSize(3).containsExactly(book1, book2, book3);
    }

    @Test
    public void testThatBookCanBeUpdated() {
        Author author = TestDataUtil.createTestAuthorA();

        Book book = TestDataUtil.createTestBookA(author);
        underTheTest.save(book);
        book.setTitle("New Title");
        underTheTest.save(book);

        Optional<Book> result = underTheTest.findById(book.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatBookCanBeDeleted() {
        Author author = TestDataUtil.createTestAuthorA();

        Book book = TestDataUtil.createTestBookA(author);
        underTheTest.save(book);

        underTheTest.deleteById(book.getIsbn());
        Optional<Book> result = underTheTest.findById(book.getIsbn());

        assertThat(result).isEmpty();
    }
}

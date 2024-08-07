package com.jehadscode.database;

import com.jehadscode.database.dao.AuthorDao;
import com.jehadscode.database.dao.impl.BookDaoImpl;
import com.jehadscode.database.domain.Author;
import com.jehadscode.database.domain.Book;
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
public class BookDaoImplIntegrationTest {

    private AuthorDao authorDao;
    private BookDaoImpl bookDaoImpl;

    @Autowired
    public BookDaoImplIntegrationTest(BookDaoImpl bookDaoImpl, AuthorDao authorDao) {
        this.bookDaoImpl = bookDaoImpl;
        this.authorDao = authorDao;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createTestAuthorA();
        authorDao.createAuthor(author);

        Book book = TestDataUtil.createTestBookA();
        book.setAuthor_id(author.getId());
        bookDaoImpl.createBook(book);

        Optional<Book> result = bookDaoImpl.findOneBook(book.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);

    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createTestAuthorA();
        authorDao.createAuthor(author);

        Book book1 = TestDataUtil.createTestBookA();
        book1.setAuthor_id(author.getId());
        bookDaoImpl.createBook(book1);

        Book book2 = TestDataUtil.createTestBookB();
        book2.setAuthor_id(author.getId());
        bookDaoImpl.createBook(book2);

        Book book3 = TestDataUtil.createTestBookC();
        book3.setAuthor_id(author.getId());
        bookDaoImpl.createBook(book3);

        List<Book> result = bookDaoImpl.findAllBooks();

        assertThat(result).hasSize(3).containsExactly(book1, book2, book3);
    }

}

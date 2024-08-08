package com.jehadscode.database;

import com.jehadscode.database.domain.Author;
import com.jehadscode.database.domain.Book;
import com.jehadscode.database.repositories.AuthorRepository;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class TestDataUtil {

    public static Author createTestAuthorA() {
        return Author.builder()
                .id(1L)
                .name("Jehad Hamayel")
                .age(22)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .id(2L)
                .name("Mohammad Hamayel")
                .age(21)
                .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
                .id(3L)
                .name("Loay Hamayel")
                .age(26)
                .build();
    }

    public static Book createTestBookA(final Author author) {
        return Book.builder()
                .isbn("1234567890")
                .title("The Clean Coder")
                .author(author)
                .build();
    }

    public static Book createTestBookB(final Author author) {
        return Book.builder()
                .isbn("1234567891")
                .title("The Pragmatic Programmer")
                .author(author)
                .build();
    }

    public static Book createTestBookC(final Author author) {
        return Book.builder()
                .isbn("1234567892")
                .title("The Mythical")
                .author(author)
                .build();
    }
}

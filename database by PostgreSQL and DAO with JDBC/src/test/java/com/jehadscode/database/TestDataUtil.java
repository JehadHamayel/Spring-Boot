package com.jehadscode.database;

import com.jehadscode.database.domain.Author;
import com.jehadscode.database.domain.Book;
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

    public static Book createTestBookA() {
        return Book.builder()
                .isbn("1234567890")
                .title("The Clean Coder")
                .author_id(1L)
                .build();
    }

    public static Book createTestBookB() {
        return Book.builder()
                .isbn("1234567891")
                .title("The Pragmatic Programmer")
                .author_id(2L)
                .build();
    }

    public static Book createTestBookC() {
        return Book.builder()
                .isbn("1234567892")
                .title("The Mythical")
                .author_id(3L)
                .build();
    }
}

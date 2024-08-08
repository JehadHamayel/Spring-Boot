package com.jehadscode.database;

import com.jehadscode.database.domain.entity.AuthorEntity;
import com.jehadscode.database.domain.entity.BookEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class TestDataUtil {

    public static AuthorEntity createTestAuthorA() {
        return AuthorEntity.builder()
                .id(1L)
                .name("Jehad Hamayel")
                .age(22)
                .build();
    }

    public static AuthorEntity createTestAuthorB() {
        return AuthorEntity.builder()
                .id(2L)
                .name("Mohammad Hamayel")
                .age(21)
                .build();
    }

    public static AuthorEntity createTestAuthorC() {
        return AuthorEntity.builder()
                .id(3L)
                .name("Loay Hamayel")
                .age(26)
                .build();
    }

    public static BookEntity createTestBookA(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("1234567890")
                .title("The Clean Coder")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookEntity createTestBookB(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("1234567891")
                .title("The Pragmatic Programmer")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookEntity createTestBookC(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("1234567892")
                .title("The Mythical")
                .authorEntity(authorEntity)
                .build();
    }
}

package com.jehadscode.database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jehadscode.database.domain.Book;
import org.junit.jupiter.api.Test;

import java.lang.runtime.ObjectMethods;

import static org.assertj.core.api.Assertions.assertThat;

public class JacksonTest {

    @Test
    public void testThatObjectMapperCanCreateJsonFromObject() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        Book book = Book.builder()
                .isbn("978-3-16-148410-0")
                .title("Spring Boot")
                .author("Jehad")
                .yearPublished("2021")
                .build();

        String result = objectMapper.writeValueAsString(book);

        assertThat(result).isEqualTo("{\"isbn\":\"978-3-16-148410-0\",\"title\":\"Spring Boot\",\"author\":\"Jehad\",\"Year Published\":\"2021\"}");

    }

    @Test
    public void testThatObjectMapperCanCreateObjectFromJson() throws JsonProcessingException {

        Book book = Book.builder()
                .isbn("978-3-16-148410-0")
                .title("Spring Boot")
                .author("Jehad")
                .yearPublished("2021")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();

        String json = "{\"foo\":\"hello\",\"isbn\":\"978-3-16-148410-0\",\"title\":\"Spring Boot\",\"author\":\"Jehad\",\"Year Published\":\"2021\"}";

        Book result = objectMapper.readValue(json, Book.class);

        assertThat(result).isEqualTo(book);

    }
}

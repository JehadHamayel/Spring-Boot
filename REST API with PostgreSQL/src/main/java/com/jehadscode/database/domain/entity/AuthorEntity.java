package com.jehadscode.database.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "authors")
public class AuthorEntity {

    @Id
    @GeneratedValue( generator = "author_id_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private Integer age;
}

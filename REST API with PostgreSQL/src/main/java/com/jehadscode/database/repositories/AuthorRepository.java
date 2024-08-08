package com.jehadscode.database.repositories;

import com.jehadscode.database.domain.entity.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {
    Iterable<AuthorEntity> ageLessThan(int age);

    @Query("SELECT a from AuthorEntity a WHERE a.age > ?1")
    Iterable<AuthorEntity> getAuthorsAgeGreaterThan(int age);
}

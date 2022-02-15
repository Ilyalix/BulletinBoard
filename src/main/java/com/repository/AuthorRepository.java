package com.repository;

import com.domain.Advertisement;
import com.domain.Author;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query("FROM Advertisement c WHERE c.author.id IN :a_ids")
    List<Advertisement> findAdvertisementByIdAuthor(@Param("a_ids") List<Integer> ids);

    Author findByName(String name);

}

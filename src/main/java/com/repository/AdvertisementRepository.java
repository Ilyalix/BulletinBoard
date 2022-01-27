package com.repository;

import com.domain.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Integer> {

    @Query("FROM Advertisement c WHERE c.category.id = :a_id")
    List<Advertisement> findAdvertisementByCategory(@Param("a_id") int id);

    @Query("FROM Advertisement c WHERE c.category.id IN :a_ids")
    List<Advertisement> findAdvertisementByCategories(@Param("a_ids") List<Integer> ids);

    @Query("FROM Advertisement c WHERE c.text LIKE '%' || :text || '%'")
    List<Advertisement> findAdvertisementByText(@Param("text") String text);

    @Query("FROM Advertisement c WHERE c.dateOfPublic = :date")
    List<Advertisement> findAdvertisementByDateOfPublic(@Param("date") LocalDate date);

    @Transactional
    @Modifying
    @Query("DELETE FROM Advertisement c WHERE c.author.id = :a_id")
    void deleteAdvertisementByAuthor(@Param("a_id") int id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Advertisement c WHERE c.isActive = false")
    void deleteAdvertisementByActive();

}

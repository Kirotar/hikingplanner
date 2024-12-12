package com.example.hikingplanner.repository;

import com.example.hikingplanner.model.HikeTemplates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HikeTemplatesRepository extends JpaRepository<HikeTemplates, Long> {
    boolean existsByName(String name);
    HikeTemplates findByName(String name);
    @Query("SELECT t FROM HikeTemplates t WHERE LOWER(t.name) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(t.location) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<HikeTemplates> searchHikes(@Param("search") String search);
}

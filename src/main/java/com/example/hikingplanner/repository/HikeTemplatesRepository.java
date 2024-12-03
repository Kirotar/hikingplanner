package com.example.hikingplanner.repository;

import com.example.hikingplanner.model.HikeTemplates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HikeTemplatesRepository extends JpaRepository<HikeTemplates, Long> {
    boolean existsByName(String name);
    HikeTemplates findByName(String name);

}
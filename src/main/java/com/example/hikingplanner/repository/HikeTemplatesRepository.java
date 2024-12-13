package com.example.hikingplanner.repository;

import com.example.hikingplanner.model.HikeTemplates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HikeTemplatesRepository extends JpaRepository<HikeTemplates, Long> {
    HikeTemplates findByName(String name);

}

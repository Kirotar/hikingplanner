package com.example.hikingplanner.repository;

import com.example.hikingplanner.model.Hike;
import com.example.hikingplanner.model.HikeTemplates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HikeRepository extends JpaRepository<Hike, Long> {
    @Query("SELECT COALESCE(SUM(t.distanceKm), 0.0) FROM Hike h JOIN h.template t")
    Double getTotalHikedDistance();
}

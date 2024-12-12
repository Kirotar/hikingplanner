package com.example.hikingplanner.repository;

import com.example.hikingplanner.model.Hike;
import com.example.hikingplanner.model.HikeTemplates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface HikeRepository extends JpaRepository<Hike, Long> {
    //Database query to get hike distance from HikeTemplates that are connected to Hikes table and calculate the sum
    @Query("SELECT COALESCE(SUM(t.distanceKm), 0.0) FROM Hike h JOIN h.template t")
    Double getTotalHikedDistance();

    //Database query to check hike start date against current date and if it is completed
    @Query("SELECT h FROM Hike h WHERE h.startDate < :currentDate")
    List<Hike> getPastHikes(@Param("currentDate") LocalDate currentDate);

    @Query("SELECT h FROM Hike h WHERE h.startDate > :currentDate")
    List<Hike> getFutureHikes(@Param("currentDate") LocalDate currentDate);

    long countByIsCompletedTrue();
    long countByIsCompletedFalse();


}

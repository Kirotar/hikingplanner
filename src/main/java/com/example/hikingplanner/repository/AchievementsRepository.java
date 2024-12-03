package com.example.hikingplanner.repository;

import com.example.hikingplanner.model.Achievements;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AchievementsRepository extends JpaRepository<Achievements, Integer> {

    Optional<Achievements> findByHikeId(Integer hikeId);
}

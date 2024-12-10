package com.example.hikingplanner.repository.Achievements;

import com.example.hikingplanner.model.Achievements.Landmarks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LandmarksRepository extends JpaRepository<Landmarks, Long> {
    Optional<Landmarks> findByLandmarkName(String landmarkName);
}

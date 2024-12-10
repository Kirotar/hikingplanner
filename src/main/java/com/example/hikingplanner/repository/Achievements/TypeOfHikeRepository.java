package com.example.hikingplanner.repository.Achievements;

import com.example.hikingplanner.model.Achievements.TypeOfHike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeOfHikeRepository extends JpaRepository<TypeOfHike, Long> {
    Optional<TypeOfHike> findByTypeOfHikeName(String hikeTypeName);
}

package com.example.hikingplanner.repository.Achievements;

import com.example.hikingplanner.model.Animals;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnimalsRepository extends JpaRepository<Animals, Long> {
    Optional<Animals> findByAnimalName(String animalName);
}

package com.example.hikingplanner.repository.Achievements;

import com.example.hikingplanner.model.Achievements.Activities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActivitiesRepository extends JpaRepository<Activities, Long> {
    Optional<Activities> findByActivityName(String activityName);}

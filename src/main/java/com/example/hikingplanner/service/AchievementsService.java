package com.example.hikingplanner.service;


import com.example.hikingplanner.model.Achievements;
import com.example.hikingplanner.repository.AchievementsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional

public class AchievementsService {

    private final AchievementsRepository achievementsRepository;

    public AchievementsService(AchievementsRepository achievementsRepository) {
        this.achievementsRepository = achievementsRepository;
    }

    public List<Achievements> getAllAchievements() {
        return achievementsRepository.findAll();
    }

}

package com.example.hikingplanner.controller;

import com.example.hikingplanner.model.Achievements;
import com.example.hikingplanner.service.AchievementsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/achievement")
public class AchievementsController {
    private final AchievementsService achievementsService;

    public AchievementsController(AchievementsService achievementsService) {
        this.achievementsService = achievementsService;
    }

    @GetMapping ("/get-all-achievements")
    public List<Achievements> getAllAchievements() {
        return achievementsService.getAllAchievements();
    }
}

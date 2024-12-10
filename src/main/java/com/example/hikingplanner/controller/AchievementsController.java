package com.example.hikingplanner.controller;

import com.example.hikingplanner.model.Achievements.Achievements;
import com.example.hikingplanner.service.AchievementsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/achievements")
public class AchievementsController {

    private final AchievementsService achievementsService;

    public AchievementsController(AchievementsService achievementsService) {
        this.achievementsService = achievementsService;
    }

    // Kõik saavutused
    @GetMapping("/get-all-user-achievements")
    public ResponseEntity<List<Achievements>> getAllAchievements() {
        List<Achievements> achievements = achievementsService.getAllAchievements();
        return ResponseEntity.ok(achievements);
    }

    // Salvesta saavutus koos seotud andmetega
    @PostMapping("/save-achievement")
    public ResponseEntity<Achievements> saveAchievementWithRelations(
            @RequestBody Achievements achievement,
            @RequestParam(required = false) String animalName,
            @RequestParam(required = false) String hikeTypeName,
            @RequestParam(required = false) String landmarkName,
            @RequestParam(required = false) String activityName
    ) {
        try {
            Achievements savedAchievement = achievementsService.saveAchievement(achievement, animalName, hikeTypeName, landmarkName, activityName);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAchievement);
        } catch (Exception e) {
            System.err.println("Viga saavutuse salvestamisel: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Kõik loomad
    @GetMapping("/get-all-animals")
    public ResponseEntity<List<?>> getAllAnimals() {
        return ResponseEntity.ok(achievementsService.getAllAnimals());
    }

    // Kõik maamärgid
    @GetMapping("/get-all-landmarks")
    public ResponseEntity<List<?>> getAllLandmarks() {
        return ResponseEntity.ok(achievementsService.getAllLandmarks());
    }

    // Kõik matkatüübid
    @GetMapping("/get-all-hike-types")
    public ResponseEntity<List<?>> getAllHikeTypes() {
        return ResponseEntity.ok(achievementsService.getAllTypeOfHike());
    }
    @GetMapping("/get-all-activities")
    public ResponseEntity<List<?>> getAllActivities() {
        return ResponseEntity.ok(achievementsService.getAllActivities());
    }
}

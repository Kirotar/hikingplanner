package com.example.hikingplanner.controller;

import com.example.hikingplanner.model.*;
import com.example.hikingplanner.service.AchievementsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/matk")
public class AchievementsController {

    private final AchievementsService achievementsService;

    public AchievementsController(AchievementsService achievementsService) {
        this.achievementsService = achievementsService;
    }

    // Kõik saavutused
    @GetMapping("/get-all-achievements")
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
    public ResponseEntity<List<Animals>> getAllAnimals() {
        return ResponseEntity.ok(achievementsService.getAllAnimals());
    }

    //Endpoint to fetch the user-specific achievements items for a hike
    @GetMapping("/{hikeId}/achievements")
    public List<UserAchievements> getAchievementsForHike(@PathVariable Long hikeId) {
        return achievementsService.getAchievementsForHike(hikeId);
    }

    @PostMapping("/{hikeId}/save-achievement")
    public ResponseEntity<List<UserAchievements>> saveAchievements(@PathVariable Long hikeId, @RequestBody List<Long> achievementIds) {
        return achievementsService.saveAchievements(hikeId, achievementIds);
    }

    @PostMapping("/{hikeId}/save-wildlife-sightings")
    public ResponseEntity<List<WildlifeSightings>> saveWildlifeSightings(
            @PathVariable Long hikeId,
            @RequestParam(required = false) List<Long> animalType,
            @RequestParam(required = false) List<Long> birdType) {
        List<WildlifeSightings> sightings = achievementsService.saveWildlifeSightings(hikeId, animalType, birdType);
        return ResponseEntity.ok(sightings);
    }
}
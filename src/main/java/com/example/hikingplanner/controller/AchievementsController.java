package com.example.hikingplanner.controller;

import com.example.hikingplanner.model.*;
import com.example.hikingplanner.service.AchievementsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
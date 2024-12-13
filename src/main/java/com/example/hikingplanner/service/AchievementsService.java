package com.example.hikingplanner.service;

import com.example.hikingplanner.model.*;
import com.example.hikingplanner.repository.Achievements.*;
import com.example.hikingplanner.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Transactional
@Service
public class AchievementsService {

    private final AchievementsRepository achievementsRepository;
    private final AnimalsRepository animalsRepository;
    private final WildlifeSightingsRepository wildlifeSightingsRepository;
    private final HikeRepository hikeRepository;
    private final UserAchievementsRepository userAchievementsRepository;

    public AchievementsService(
            AchievementsRepository achievementsRepository,
            AnimalsRepository animalsRepository,
            WildlifeSightingsRepository wildlifeSightingsRepository,
            HikeRepository hikeRepository,
            UserAchievementsRepository userAchievementsRepository) {
        this.achievementsRepository = achievementsRepository;
        this.animalsRepository = animalsRepository;
        this.wildlifeSightingsRepository = wildlifeSightingsRepository;
        this.hikeRepository = hikeRepository;
        this.userAchievementsRepository = userAchievementsRepository;
    }

    // Kõik saavutused
    public List<Achievements> getAllAchievements() {
        return achievementsRepository.findAll();
    }

    // Kõik loomad
    public List<Animals> getAllAnimals() {
        return animalsRepository.findAll();

    }
    //Fetch user chosen achievements
    public List<UserAchievements> getAchievementsForHike(Long hikeId) {
        return userAchievementsRepository.findByHikeId(hikeId);
    }

    //User selects the items they want for their hike
    public ResponseEntity<List<UserAchievements>> saveAchievements(Long hikeId, List<Long> achievementIds) {
        List<UserAchievements> userAchievements = new ArrayList<>();
        //Fetch the hike object:
        Hike hike = hikeRepository.findById(hikeId).orElseThrow(() -> new RuntimeException("Hike not found"));

        for (Long oneachievement : achievementIds) {
            //Fetch the checklist item object
            Achievements achievement = achievementsRepository.findById(oneachievement).orElseThrow(() -> new RuntimeException("Achievement item not found"));

            //Create a new UserAchievements entry
            UserAchievements entry = new UserAchievements();

            entry.setHike(hike); // Set the Hike object
            entry.setAchievement(achievement);
            userAchievements.add(userAchievementsRepository.save(entry));
        }
        return ResponseEntity.ok(userAchievements); // Return the list of saved UserAchievements objects
    }

    public List<WildlifeSightings> saveWildlifeSightings(Long hikeId, List<Long> animalIds, List<Long> birdIds) {
        List<WildlifeSightings> sightings = new ArrayList<>();

        // Fetch the hike object
        Hike hike = hikeRepository.findById(hikeId)
                .orElseThrow(() -> new RuntimeException("Hike not found"));

        List<Long> validAnimalIds = animalIds != null ? animalIds : Collections.emptyList();
        List<Long> validBirdIds = birdIds != null ? birdIds : Collections.emptyList();

        // Save animal sightings
        for (Long animalId : validAnimalIds) {
            Animals animal = animalsRepository.findById(animalId)
                    .orElseThrow(() -> new RuntimeException("Animal not found"));
            WildlifeSightings sighting = new WildlifeSightings();
            sighting.setHike(hike);
            sighting.setAnimalType(animal);
            sightings.add(wildlifeSightingsRepository.save(sighting));
        }

        // Save bird sightings
        for (Long birdId : validBirdIds) {
            Animals bird = animalsRepository.findById(birdId)
                    .orElseThrow(() -> new RuntimeException("Bird not found"));
            WildlifeSightings sighting = new WildlifeSightings();
            sighting.setHike(hike);
            sighting.setBirdType(bird);
            sightings.add(wildlifeSightingsRepository.save(sighting));
        }

        return sightings;
    }

}
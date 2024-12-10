package com.example.hikingplanner.service;

import com.example.hikingplanner.model.Achievements.*;
import com.example.hikingplanner.model.*;
import com.example.hikingplanner.repository.Achievements.*;
import com.example.hikingplanner.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class AchievementsService {

    private final AchievementsRepository achievementsRepository;
    private final AnimalsRepository animalsRepository;
    private final LandmarksRepository landmarksRepository;
    private final TypeOfHikeRepository typeOfHikeRepository;
    private final WildlifeSightingsRepository wildlifeSightingsRepository;
    private final ActivitiesRepository activitiesRepository;
    private final ActivitiesNumberOfTimesRepository activitiesNumberOfTimesRepository;
    private final HikeRepository hikeRepository;

    public AchievementsService(
            AchievementsRepository achievementsRepository,
            AnimalsRepository animalsRepository,
            LandmarksRepository landmarksRepository,
            TypeOfHikeRepository typeOfHikeRepository,
            WildlifeSightingsRepository wildlifeSightingsRepository,
            ActivitiesRepository activitiesRepository,
            ActivitiesNumberOfTimesRepository activitiesNumberOfTimesRepository,
            HikeRepository hikeRepository
    ) {
        this.achievementsRepository = achievementsRepository;
        this.animalsRepository = animalsRepository;
        this.landmarksRepository = landmarksRepository;
        this.typeOfHikeRepository = typeOfHikeRepository;
        this.wildlifeSightingsRepository = wildlifeSightingsRepository;
        this.activitiesRepository = activitiesRepository;
        this.activitiesNumberOfTimesRepository = activitiesNumberOfTimesRepository;
        this.hikeRepository = hikeRepository;
    }

    // Salvesta uus saavutus koos seotud andmetega
    public Achievements saveAchievement(Achievements achievement, String animalName, String hikeTypeName, String landmarkName, String activityName) {
        try {
            // Salvestame looma, kui see puudub
            if (animalName != null && !animalName.isEmpty()) {
                Animals animal = animalsRepository.findByAnimalName(animalName)
                        .orElseGet(() -> animalsRepository.save(new Animals(null, animalName, "animal")));

                // Loome uue wildlife_sightings kirje
                WildlifeSightings sighting = new WildlifeSightings();
                sighting.setAnimalType(animal.getId());
                sighting = wildlifeSightingsRepository.save(sighting);

                // Seome selle saavutusega
                achievement.setWildlifeSightingsId(sighting.getId());
            }

            // Salvestame matkatüübi, kui see puudub
            if (hikeTypeName != null && !hikeTypeName.isEmpty()) {
                TypeOfHike hikeType = typeOfHikeRepository.findByTypeOfHikeName(hikeTypeName)
                        .orElseGet(() -> typeOfHikeRepository.save(new TypeOfHike(null, hikeTypeName)));
                achievement.setTypeOfHikeId(hikeType.getId());
            }

            // Salvestame maamärgi, kui see puudub
            if (landmarkName != null && !landmarkName.isEmpty()) {
                Landmarks landmark = landmarksRepository.findByLandmarkName(landmarkName)
                        .orElseGet(() -> landmarksRepository.save(new Landmarks(null, landmarkName)));
                achievement.setLandmarksId(landmark.getId());
            }

            // Salvestame activity, kui see puudub
            if (activityName != null && !activityName.isEmpty()) {
                Activities activity = activitiesRepository.findByActivityName(activityName)
                        .orElseGet(() -> activitiesRepository.save(new Activities(null, activityName)));
                achievement.setActivitiesId(Math.toIntExact(activity.getId()));

                // Loome uue numberoftimes kirje
                ActivitiesNumberOfTimes numberOfTimes = new ActivitiesNumberOfTimes();
                numberOfTimes.setActivityNumberOFTimes(activity.getId());
                numberOfTimes = activitiesNumberOfTimesRepository.save(numberOfTimes);

                // Seome selle saavutusega
                achievement.setActivitiesNumberOfTimesId(Math.toIntExact(numberOfTimes.getId()));
            }

            // Salvestame saavutuse
            System.out.println("Salvestatav saavutus: " + achievement);
            return achievementsRepository.save(achievement);

        } catch (Exception e) {
            System.err.println("Viga saavutuse salvestamisel: " + e.getMessage());
            throw e;
        }
    }


    // Kõik saavutused
    public List<Achievements> getAllAchievements() {
        return achievementsRepository.findAll();
    }

    // Kõik loomad
    public List<Animals> getAllAnimals() {
        return animalsRepository.findAll();
    }

    // Kõik maamärgid
    public List<Landmarks> getAllLandmarks() {
        return landmarksRepository.findAll();
    }

    // Kõik matkatüübid
    public List<TypeOfHike> getAllTypeOfHike() {
        return typeOfHikeRepository.findAll();
    }

    // Kõik tegevused
    public List<Activities> getAllActivities() {
        return activitiesRepository.findAll();
    }

    public List<ActivitiesNumberOfTimes> getAllActivitiesNumberOfTimes() {
        return activitiesNumberOfTimesRepository.findAll();
    }

    // Valideeri seotud võtmed
    private void validateForeignKeys(Achievements achievement) {
        if (achievement.getHikeId() != null && !hikeRepository.existsById(achievement.getHikeId())) {
            throw new IllegalArgumentException("Hike ID not found: " + achievement.getHikeId());
        }
        if (achievement.getTypeOfHikeId() != null && !typeOfHikeRepository.existsById(achievement.getTypeOfHikeId())) {
            throw new IllegalArgumentException("Hike Type ID not found: " + achievement.getTypeOfHikeId());
        }
        if (achievement.getLandmarksId() != null && !landmarksRepository.existsById(achievement.getLandmarksId())) {
            throw new IllegalArgumentException("Landmark ID not found: " + achievement.getLandmarksId());
        }
        if (achievement.getWildlifeSightingsId() != null && !wildlifeSightingsRepository.existsById(achievement.getWildlifeSightingsId())) {
            throw new IllegalArgumentException("Wildlife Sighting ID not found: " + achievement.getWildlifeSightingsId());
        }
        if (achievement.getActivitiesId() != null && !activitiesRepository.existsById((long) achievement.getActivitiesId())) {
            throw new IllegalArgumentException("Activities ID not found: " + achievement.getActivitiesId());
        }
        if (achievement.getActivitiesNumberOfTimesId() != null && !activitiesNumberOfTimesRepository.existsById((long) achievement.getActivitiesNumberOfTimesId())) {
            throw new IllegalArgumentException("Activities Number ID not found: " + achievement.getActivitiesNumberOfTimesId());
        }
    }
}

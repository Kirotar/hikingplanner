package com.example.hikingplanner.service;

import com.example.hikingplanner.model.Hike;
import com.example.hikingplanner.model.HikeTemplates;
import com.example.hikingplanner.repository.HikeRepository;
import com.example.hikingplanner.repository.HikeTemplatesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlannedHikeService {

    @Autowired
    private final HikeTemplatesRepository hikeTemplatesRepository;
    private final HikeRepository hikeRepository;

    public PlannedHikeService(HikeTemplatesRepository hikeTemplatesRepository, HikeRepository hikeRepository) {
        this.hikeTemplatesRepository = hikeTemplatesRepository;
        this.hikeRepository = hikeRepository;
    }
//Get all hiking trails from the pre-made list
    public List<HikeTemplates> getAllHikeTemplates() {
        return hikeTemplatesRepository.findAll();
    }
//Get one hike by name
    public HikeTemplates getTrailByName(String name) {
        return hikeTemplatesRepository.findByName(name);
    }
//Add a hike to Hikes table
    public Hike planHike(Hike hike) {
        return hikeRepository.save(hike);
    }
//Get all the user inserted hikes
    public List<Hike> getAllUserHikes() {
        return hikeRepository.findAll();
    }
//Update a user hike from uncompleted to completed
    @Transactional
    public void hikeUpdate(Long id) {
            Hike hike = hikeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Hike not found"));
            hike.setCompleted(true);
            hikeRepository.save(hike);
        }
}

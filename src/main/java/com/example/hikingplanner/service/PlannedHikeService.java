package com.example.hikingplanner.service;

import com.example.hikingplanner.dto.HikeDTO;
import com.example.hikingplanner.model.Hike;
import com.example.hikingplanner.model.HikeTemplates;
import com.example.hikingplanner.repository.HikeRepository;
import com.example.hikingplanner.repository.HikeTemplatesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PlannedHikeService {

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
    public Hike planHike(HikeDTO hikedto) {
        // Map HikeDTO to Hike entity
        Hike hike = new Hike();
        hike.setTemplate(hikedto.getTemplate());  // Assuming the template is already set correctly
        hike.setNotes(hikedto.getNotes());
        hike.setStartDate(hikedto.getStartDate());
        hike.setEndDate(hikedto.getEndDate());
        hike.setDuration(hikedto.getDuration());
        hike.setMeetupPoint(hikedto.getMeetupPoint());
        hike.setCompleted(hikedto.isCompleted());

        // Save the mapped entity
        return hikeRepository.save(hike);
    }
//Get all the user inserted hikes
    public List<Hike> getAllUserHikes() {
        return hikeRepository.findAll();
    }
//Update a user hike from uncompleted to completed

    public void hikeUpdate(Long id) {
            Hike hike = hikeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Hike not found"));
            hike.setCompleted(true);
            hikeRepository.save(hike);
        }
}

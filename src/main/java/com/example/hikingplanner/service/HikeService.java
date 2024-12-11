package com.example.hikingplanner.service;

import com.example.hikingplanner.dto.HikeDTO;
import com.example.hikingplanner.model.Hike;
import com.example.hikingplanner.model.HikeTemplates;
import com.example.hikingplanner.repository.HikeRepository;
import com.example.hikingplanner.repository.HikeTemplatesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class HikeService {

    @Autowired
    private final HikeTemplatesRepository hikeTemplatesRepository;
    private final HikeRepository hikeRepository;

    public HikeService(HikeTemplatesRepository hikeTemplatesRepository, HikeRepository hikeRepository) {
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

    //Get all the user inserted past hikes
    public List<Hike> getAllUserPastHikes() {
        // Get all hikes (you can replace this with the actual data fetching logic, e.g., from a repository)
        List<Hike> hikes = hikeRepository.findAll();

        // Filter hikes by startDate being in the past or today
        LocalDate currentDate = LocalDate.now();
        return hikes.stream()
                .filter(hike -> hike.getStartDate().isBefore(currentDate))
                .collect(Collectors.toList());
    }

    //Get all the user inserted future hikes
    public List<Hike> getAllUserFutureHikes() {
        // Get all hikes (you can replace this with the actual data fetching logic, e.g., from a repository)
        List<Hike> hikes = hikeRepository.findAll();

        // Filter hikes by startDate being in the past or today
        LocalDate currentDate = LocalDate.now();
        return hikes.stream()
                .filter(hike -> hike.getStartDate().isAfter(currentDate) || hike.getStartDate().isEqual(currentDate))
                .collect(Collectors.toList());
    }
//Update a user hike from uncompleted to completed

    public void hikeUpdate(Long id) {
        Hike hike = hikeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hike not found"));
        hike.setCompleted(true);
        hikeRepository.save(hike);
    }


    public Double distanceCompleted() {
        return hikeRepository.getTotalHikedDistance();
    }


    public String distanceCompletedUnlock() {
        Double distance = distanceCompleted(); // Call the non-static method

        if (distance > 10000) {
            return "tenthousand";
        } else if (distance > 1000) {
            return "thousand";
        } else if (distance > 100) {
            return "hundered";
        }
        return "error";
    }

}


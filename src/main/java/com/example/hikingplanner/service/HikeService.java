package com.example.hikingplanner.service;

import com.example.hikingplanner.dto.HikeDTO;
import com.example.hikingplanner.model.Hike;
import com.example.hikingplanner.model.HikeTemplates;
import com.example.hikingplanner.repository.HikeRepository;
import com.example.hikingplanner.repository.HikeTemplatesRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class HikeService {

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

    public void deleteHike(long id) {
        if (hikeRepository.existsById(id)) {
            hikeRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Hike with id " + id + " not found");
        }

    }

    public List<Hike> getAllUserPastHikes() {
        markHikeComplete();
        return hikeRepository.getPastHikes(LocalDate.now());
    }

    public List<Hike> getAllUserFutureHikes() {
        return hikeRepository.getFutureHikes(LocalDate.now());
    }

    public void hikeUpdateCompletion(Long id) {
        Hike hike = hikeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hike not found"));
        hike.setCompleted(true);
        hikeRepository.save(hike);
    }
public void updateHike(Long id, Hike hike) {
    for (Hike onehike : hikeRepository.findAll()) {
        if (onehike.getId().equals(id)) {
           onehike.setId(hike.getId());
           onehike.setStartDate(hike.getStartDate());
           onehike.setEndDate(hike.getEndDate());
           onehike.setDuration(hike.getDuration());
           onehike.setNotes(hike.getNotes());
           onehike.setMeetupPoint(hike.getMeetupPoint());
           hikeRepository.save(onehike);
        }
    }
}
    public Double distanceCompleted() {
        return hikeRepository.getTotalHikedDistance();
    }


    public long totalNumberOfHikes() {
        markHikeComplete();
        long completedHikesCount = hikeRepository.countByIsCompletedTrue();
        return completedHikesCount;
    }

    public void markHikeComplete() {
        LocalDate currentDate = LocalDate.now();
        List<Hike> hikes = hikeRepository.findAll();
        for (Hike hike : hikes)
            if (hike.getStartDate().isBefore(currentDate) && !hike.isCompleted()) {
                hike.setCompleted(true);
                hikeRepository.save(hike); // Persist the change to the database
            }
    }
}


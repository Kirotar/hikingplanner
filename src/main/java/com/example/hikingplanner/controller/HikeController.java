package com.example.hikingplanner.controller;

import com.example.hikingplanner.dto.HikeDTO;
import com.example.hikingplanner.model.HikeTemplates;
import com.example.hikingplanner.model.Hike;
import com.example.hikingplanner.service.HikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matk")
public class HikeController {

    private final HikeService hikeService;

    public HikeController(HikeService hikeService) {
        this.hikeService = hikeService;
    }

    @GetMapping("/get-trails")
    public List<HikeTemplates> getAllHikes() {
        return hikeService.getAllHikeTemplates();
    }

    @GetMapping("/get-past-hikes")
    public List<Hike> getAllUserPastHikes() {
        return hikeService.getAllUserPastHikes();
    }

    @GetMapping("/get-future-hikes")
    public List<Hike> getAllUserFutureHikes() {
        return hikeService.getAllUserFutureHikes();
    }


    @PostMapping("/plan-hike")
    public Hike planHike(@RequestBody HikeDTO hikedto) {
        return hikeService.planHike(hikedto);
    }

    @DeleteMapping("/{id}/delete-hike")
    public void deleteHike(@PathVariable("id") Long id) {
        hikeService.deleteHike(id);
    }


    @PutMapping("/{id}/complete")
    public ResponseEntity<Void> hikeIsCompleted(@PathVariable("id") Long id) {
        hikeService.hikeUpdateCompletion(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }

    @GetMapping("/distance-completed")
    public Double distanceCompleted() {
        return hikeService.distanceCompleted();
    }

    @GetMapping("/nr-hikes-completed")
    public long totalNumberOfHikes() {
        return hikeService.totalNumberOfHikes();
    }

    @PutMapping("/{id}/update")
    public void hikeIsCompleted(@PathVariable("id") Long id, @RequestBody Hike hike) {
        hikeService.updateHike(id, hike);
    }
}

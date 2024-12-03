package com.example.hikingplanner.controller;

import com.example.hikingplanner.model.HikeTemplates;
import com.example.hikingplanner.model.Hike;
import com.example.hikingplanner.service.PlannedHikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matk")
public class PlannedHikeController {

    private final PlannedHikeService plannedHikeService;

    public PlannedHikeController(PlannedHikeService plannedHikeService) {this.plannedHikeService = plannedHikeService; }

    @GetMapping("/get-trails")
    public List<HikeTemplates> getAllHikes(){
        return plannedHikeService.getAllHikeTemplates();
    }

    @GetMapping("/get-hikes")
    public List <Hike> getAllUserHikes(){
        return plannedHikeService.getAllUserHikes();
    }

    @GetMapping("/get-trail/{name}")
    public ResponseEntity<HikeTemplates> getTrailByName(@PathVariable String name) {
        HikeTemplates template = plannedHikeService.getTrailByName(name);
        if (template != null) {
            return ResponseEntity.ok(template); // class in Spring Framework used to represent the entire HTTP response
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Returns 404 if not found
        }
    }

    @PostMapping("/plan-hike")
    public Hike planHike(@RequestBody Hike hike){
        return plannedHikeService.planHike(hike);
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<Void> hikeIsCompleted(@PathVariable("id") Long id) {
        plannedHikeService.hikeUpdate(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }

    //Vaja teha kaasa võetavate asjade nimekiri

}

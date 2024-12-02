package com.example.hikingplanner.controller;

import com.example.hikingplanner.model.HikeTemplates;
import com.example.hikingplanner.model.Hike;
import com.example.hikingplanner.service.PlannedHikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matk")
public class PlannedHikeController {

    private final PlannedHikeService plannedHikeService;

    public PlannedHikeController(PlannedHikeService plannedHikeService) {this.plannedHikeService = plannedHikeService; }

    @GetMapping("/get-hikes")
    public List<HikeTemplates> getAllHikes(){
        return plannedHikeService.getAllHikes();
    }

    @GetMapping("/get-trail/{name}")
    public List<HikeTemplates> getTrailByName(@PathVariable String name){
        return plannedHikeService.getTrailByName(name);
    }

/*    @PostMapping("/plan-hike")
    public String planHike(@RequestBody Hike hike){
        return plannedHikeService.planHike(hike);
    }*/
    /*
    return
    kasutaja valib raja - get kaardilt koht/nupp JA/VÕI sisestab otsingusse VÕI dropdown menüü?
    Igal juhul vist GET päring, sõltub lihtsalt mille järgi?
    see annab koha nime jms - siis mingi nupp et planeeri matk? POST mis lisaks siis selle hikes asja selle key järgi ja kõik muud väljad ka


    kaasa?

     */

}

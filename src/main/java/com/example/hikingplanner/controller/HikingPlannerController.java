package com.example.hikingplanner.controller;

import com.example.hikingplanner.model.HikeTemplates;
import com.example.hikingplanner.service.PastHikeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/hiking-planner")

public class HikingPlannerController {
    private final PastHikeService pastHikeService;

    public HikingPlannerController(PastHikeService hikingPlannerService) {
        this.pastHikeService = hikingPlannerService;
    }

    @GetMapping("/get-hikes")
    public List<HikeTemplates> getAllHikes() {
        return pastHikeService.getAllHikes();
    }

//    @PostMapping("/hike-dates/{startdate}/{enddate}")
//    public List<String> validateDates(@PathVariable("startdate") String startdate,
//                                         @PathVariable("enddate") String enddate) {
//        LocalDate parsedStartDate = LocalDate.parse(startdate);
//        LocalDate parsedEndDate = LocalDate.parse(enddate);
//
//        return pastHikeService.hikeDates(parsedStartDate, parsedEndDate);
//    }
//
//    @PostMapping("/duration/{hours}/{minutes}")
//    public String addHikeDuration(@PathVariable double hours, @PathVariable double minutes) {
//        return pastHikeService.addHikeDuration(hours, minutes);
//    }
//
//    @PostMapping ("/add")
//    public String addNotes(@RequestBody String notes) {
//        return "Comment added: " + pastHikeService.addNotes(notes);
//    }

}
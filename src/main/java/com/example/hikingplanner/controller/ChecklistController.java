package com.example.hikingplanner.controller;

import com.example.hikingplanner.model.ChecklistItem;
import com.example.hikingplanner.model.UserChecklist;
import com.example.hikingplanner.repository.ChecklistItemRepository;
import com.example.hikingplanner.service.ChecklistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ChecklistController {

    private final ChecklistService checklistService;

    public ChecklistController(ChecklistService checklistService) {
        this.checklistService = checklistService;
    }

    //Endpoint to fetch all available checklist items:
    @GetMapping("/checklist")
    public List<ChecklistItem> getChecklistItems() {
        return checklistService.getChecklistItems();
    }
    //Endpoint to allow users to select items for a specific hike
    @PostMapping("/hike/{hikeId}/checklist")
    public ResponseEntity<List<UserChecklist>> addItemsToChecklist(@PathVariable Long hikeId, @RequestBody List<Long> itemIds) {
  return checklistService.addItemsToChecklist(hikeId, itemIds);
    }
    //Endpoint to fetch the user-specific checklist for a hike
    @GetMapping("/hike/{hikeId}/checklist")
    public List<UserChecklist> getChecklistForHike(@PathVariable Long hikeId) {
        return checklistService.getChecklistForHike(hikeId);
    }

    //Endpoint to update the status of items in the checklist
    @PutMapping("/hike/{hikeId}/checklist/{itemId}")
    public ResponseEntity<UserChecklist> markItemAsCompleted(@PathVariable Long hikeId, @PathVariable Long itemId) {
  return checklistService.markItemAsCompleted(hikeId, itemId);
    }
}

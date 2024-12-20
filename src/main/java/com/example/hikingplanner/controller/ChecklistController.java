package com.example.hikingplanner.controller;

import com.example.hikingplanner.model.ChecklistItem;
import com.example.hikingplanner.model.UserChecklist;
import com.example.hikingplanner.service.ChecklistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/matk")
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
    @PostMapping("/{hikeId}/add-checklist")
    public ResponseEntity<List<UserChecklist>> addItemsToChecklist(@PathVariable Long hikeId, @RequestBody List<Long> itemIds) {
  return checklistService.addItemsToChecklist(hikeId, itemIds);
    }
    //Endpoint to fetch the user-specific checklist for a hike
    @GetMapping("/{hikeId}/checklist")
    public List<UserChecklist> getChecklistForHike(@PathVariable Long hikeId) {
        return checklistService.getChecklistForHike(hikeId);
    }

    //Endpoint to update the status of items in the checklist
  @PatchMapping("/checklist/{id}")
    public ResponseEntity<UserChecklist> markItemAsCompleted(@PathVariable Long id) {
  return checklistService.markItemAsCompleted(id);
    }

    @DeleteMapping("/delete-items/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        checklistService.deleteItem(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }
}

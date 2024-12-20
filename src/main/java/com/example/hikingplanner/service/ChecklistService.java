package com.example.hikingplanner.service;

import com.example.hikingplanner.model.ChecklistItem;
import com.example.hikingplanner.model.Hike;
import com.example.hikingplanner.model.UserChecklist;
import com.example.hikingplanner.repository.ChecklistItemRepository;
import com.example.hikingplanner.repository.HikeRepository;
import com.example.hikingplanner.repository.UserChecklistRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ChecklistService {

    private final ChecklistItemRepository checklistItemRepository;
    private final UserChecklistRepository userChecklistRepository;
    private final HikeRepository hikeRepository;


    public ChecklistService(ChecklistItemRepository checklistItemRepository, UserChecklistRepository userChecklistRepository, HikeRepository hikeRepository) {
        this.checklistItemRepository = checklistItemRepository;
        this.userChecklistRepository = userChecklistRepository;
        this.hikeRepository = hikeRepository;
    }
//Fetch all available checklist items
    public List<ChecklistItem> getChecklistItems() {
        return checklistItemRepository.findAll();
    }

//User selects the items they want for their hike
    public ResponseEntity<List<UserChecklist>> addItemsToChecklist(Long hikeId, List<Long> itemIds) {
        List<UserChecklist> userChecklist = new ArrayList<>();
        //Fetch the hike object:
        Hike hike = hikeRepository.findById(hikeId).orElseThrow(() -> new RuntimeException("Hike not found"));

        for (Long itemId : itemIds) {
            //Fetch the checklist item object
            ChecklistItem checklistItem = checklistItemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Checklist item not found"));

            //Create a new UserChecklist entry
            UserChecklist entry = new UserChecklist();

            entry.setHike(hike); // Set the Hike object
            entry.setChecklistItem(checklistItem); // Set the ChecklistItem object
            entry.setIsCompleted(false); // Initially not completed
            userChecklist.add(userChecklistRepository.save(entry));
        }
        return ResponseEntity.ok(userChecklist); // Return the list of saved UserChecklist objects
    }

//Fetch user chosen checklist
    public List<UserChecklist> getChecklistForHike(Long hikeId) {
        return userChecklistRepository.findByHikeId(hikeId);
    }

//Mark items in user chosen checklist as done
    public ResponseEntity<UserChecklist> markItemAsCompleted(Long id) {
        Optional<UserChecklist> optionalEntry = userChecklistRepository.findById(id);
        /*Optional: container object that may or may not contain a non-null value. Optional is used to avoid
        NullPointerExceptions and to handle values that could be null in a more explicit and controlled way.*/
        if (optionalEntry.isPresent()) { //checks if the Optional contains a non-null UserChecklist object
            UserChecklist entry = optionalEntry.get();
            entry.setIsCompleted(true); // Mark as completed
            userChecklistRepository.save(entry);
            return ResponseEntity.ok(entry);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            //If the Optional is empty (i.e., the object isn't found), it returns a NOT_FOUND response with HTTP status 404.

        }
    }

    public void deleteItem(Long id) {
        if (!userChecklistRepository.existsById(id)) {
            throw new RuntimeException("Checklist item not found with ID: " + id);
        }
        userChecklistRepository.deleteById(id);
    }

}

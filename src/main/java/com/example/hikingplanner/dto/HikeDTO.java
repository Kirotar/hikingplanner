package com.example.hikingplanner.dto;

import com.example.hikingplanner.model.ChecklistItem;
import com.example.hikingplanner.model.HikeTemplates;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class HikeDTO {
    private HikeTemplates template; // Assuming it's a nested object with id
    private String notes;
    private LocalDate startDate; // Automatically parsed from ISO 8601 string
    private LocalDate endDate;
    private LocalTime duration;
    private String meetupPoint;
    private boolean isCompleted;

    // Getters and Setters

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }
}
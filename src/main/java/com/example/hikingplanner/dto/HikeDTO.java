package com.example.hikingplanner.dto;

import com.example.hikingplanner.model.HikeTemplates;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.*;

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

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }
}

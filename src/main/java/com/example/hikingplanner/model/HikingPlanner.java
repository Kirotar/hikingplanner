package com.example.hikingplanner.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@ToString
@Table(name = "hikes")
public class HikingPlanner {
    @Id
    @GeneratedValue

    private long id;
    private long template_id;
    private java.util.Date start_date;
    private java.util.Date end_date;
    private double duration; //If we have extra time, lets make a calculator with start and end time
    private String notes;
    private String meetup_point;
    private boolean is_completed;
}
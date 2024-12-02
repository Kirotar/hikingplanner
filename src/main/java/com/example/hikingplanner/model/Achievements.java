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
@Table(name = "achievements")
public class Achievements {
    @Id
    @GeneratedValue

    private long id;
    private int hike_id;
    private int type_of_hike_id;
    private int landmarks_id;
    private int activities_id;
    private int wildlife_sightings_id;
    private int activities_number_of_times_id;
    private int number_of_times_id;
    private boolean is_completed;
}
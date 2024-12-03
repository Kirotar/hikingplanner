package com.example.hikingplanner.model;

import jakarta.persistence.*;
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

    private Long id;

    private Integer hikeId;  // Foreign key to hikes table
    private Integer typeOfHikeId;  // Foreign key to type_of_hike table
    private Integer landmarksId;  // Foreign key to landmarks table
    private Integer activitiesId;  // Foreign key to activities table
    private Integer wildlifeSightingsId;  // Foreign key to wildlife sightings table
    private Integer activitiesNumberOfTimesId;  // Foreign key to activities_number_of_times table
    private Boolean completed = false;  // Indicates if the activity was completed
    private Integer numberOfTimes;  // Number of times an activity was completed

}
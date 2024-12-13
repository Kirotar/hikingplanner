package com.example.hikingplanner.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@ToString
@Table(name = "hikes")
public class Hike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne // Specifies that many instances of the current entity (e.g., Hike)
    // can be associated with one instance of another entity (e.g., HikeTemplate).
    @JoinColumn(name = "template_id", nullable = false) // Specifies the foreign key column in the Hikes table that
    // links it to the HikeTemplates table.
    private HikeTemplates template;//This setup allows the database to associate a hike (in the Hikes table)
    // with its corresponding template (in the HikeTemplates table) through a foreign key.

    private LocalDate startDate;
    private LocalDate endDate;
    //We should add start time as well for Planned hikes
    private String duration; //If we have extra time, lets make a calculator with start and end time

    @Column(length = 500) // Limit the length of notes in the database
    private String notes;
    private String meetupPoint;
    private boolean isCompleted;

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}
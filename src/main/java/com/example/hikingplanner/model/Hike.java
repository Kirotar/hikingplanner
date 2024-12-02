package com.example.hikingplanner.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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

    private LocalDate start_date;
    private LocalDate end_date;
    //We should add start time as well for Planned hikes
    private double duration; //If we have extra time, lets make a calculator with start and end time

    @Column(length = 500) // Limit the length of notes in the database
    private String notes;
    private String meetup_point;
    private boolean is_completed;
}
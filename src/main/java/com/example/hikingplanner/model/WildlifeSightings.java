package com.example.hikingplanner.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@ToString
@Table(name="wildlife_sightings")

// on seotud animal klassiga

public class WildlifeSightings {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    private Long id;
    @ManyToOne
    @JoinColumn(name = "animal_type", referencedColumnName = "id", nullable = true)
    private Animals animalType;

    @ManyToOne
    @JoinColumn(name = "bird_type", referencedColumnName = "id", nullable = true)
    private Animals birdType;

    @ManyToOne
    @JoinColumn(name = "hike_id", nullable = false)
    private Hike hike;
}


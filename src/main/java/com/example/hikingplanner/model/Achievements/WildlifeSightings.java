package com.example.hikingplanner.model.Achievements;

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
    private Long animalType;
    private Long birdType;

}

package com.example.hikingplanner.model.Achievements;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@ToString
@Table(name = "animals")
public class Animals {
    @Id
    @GeneratedValue

    private Long id;
    private String animalName;
    private String category;
}

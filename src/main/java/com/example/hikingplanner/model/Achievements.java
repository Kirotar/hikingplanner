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
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(nullable = false)
    private String achievementName;

}
package com.example.hikingplanner.model.Achievements;

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
@Table(name = "landmarks")
public class Landmarks {

    @Id
    @GeneratedValue
    private Long id;
    private String landmarkName;
}

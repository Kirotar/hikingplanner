package com.example.hikingplanner.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@ToString
@Table(name = "hike_templates")
public class HikeTemplates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String distanceKm; //If we have extra time, lets make a calculator with start and end time    private String location;
    private String detailsUrl;
}
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
    private double distanceKm;
    private String location;
    private String detailsUrl;
}
package com.example.hikingplanner.model;

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
@Table(name = "hike_templates")
public class HikeTemplates {
    @Id
    @GeneratedValue

    private long id;
    private String name;
    private double distance_km;
    private String location;
    private String details_url;
}
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
    @GeneratedValue

    private long id;
    private String name;
    private String distanceKm; //If we have extra time, lets make a calculator with start and end time
    private String detailsUrl;

}

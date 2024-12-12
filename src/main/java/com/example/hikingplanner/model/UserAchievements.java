package com.example.hikingplanner.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "user_achievements")
public class UserAchievements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne //Many UserChecklist records can reference the same Hike or checklistItem
    @JoinColumn(name = "hike_id", nullable = false) //Column that stores the foreign key for the Hike entity, must have value
    private Hike hike;

    @ManyToOne
    @JoinColumn(name = "achievements_id", nullable = false)
    private Achievements achievement;

}

package com.example.hikingplanner.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "user_checklist")
public class UserChecklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne //Many UserChecklist records can reference the same Hike or checklistItem
    @JoinColumn(name = "hike_id", nullable = false) //Column that stores the foreign key for the Hike entity, must have value
    private Hike hike;

    @ManyToOne
    @JoinColumn(name = "checklist_item_id", nullable = false)
    private ChecklistItem checklistItem;

    @Column(nullable = false) //Makes it so that it can't store NULL value and gives customization options
    private Boolean isCompleted = false;
}
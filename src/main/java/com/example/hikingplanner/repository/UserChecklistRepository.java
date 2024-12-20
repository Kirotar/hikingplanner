package com.example.hikingplanner.repository;

import com.example.hikingplanner.model.ChecklistItem;
import com.example.hikingplanner.model.UserChecklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserChecklistRepository extends JpaRepository<UserChecklist, Long> {
    List<UserChecklist> findByHikeId(Long hikeId);

}

package com.example.hikingplanner.repository;

import com.example.hikingplanner.model.Hike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HikeRepository extends JpaRepository<Hike, Long> {

}


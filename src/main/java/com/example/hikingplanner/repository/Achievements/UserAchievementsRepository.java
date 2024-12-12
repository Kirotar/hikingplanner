package com.example.hikingplanner.repository.Achievements;

import com.example.hikingplanner.model.UserAchievements;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserAchievementsRepository extends JpaRepository<UserAchievements, Long> {
    List<UserAchievements> findByHikeId(Long hikeId);
    Optional<UserAchievements> findByHikeIdAndAchievementId(Long hikeId, Long achievement);
}

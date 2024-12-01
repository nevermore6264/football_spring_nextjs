package com.example.QuanLyDoiBong.services;

import com.example.QuanLyDoiBong.dto.GoalDTO;
import com.example.QuanLyDoiBong.dto.TopScorerDTO;
import com.example.QuanLyDoiBong.entity.Goal;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GoalServices {
    List<Goal> getAllGoal();

    ResponseEntity<Object> insertGoal(GoalDTO goal);

    List<TopScorerDTO> getTopScorers();

    List<Goal> getGoalHome(int idmatch, int idteam);

    List<Goal> getGoalAway(int idmatch, int idteam);
    
}

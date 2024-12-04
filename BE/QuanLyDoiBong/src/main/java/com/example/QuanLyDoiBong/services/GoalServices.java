package com.example.QuanLyDoiBong.services;

import com.example.QuanLyDoiBong.dto.request.GoalRequest;
import com.example.QuanLyDoiBong.dto.response.TopScorerResponse;
import com.example.QuanLyDoiBong.entity.Goal;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GoalServices {
    List<Goal> getAllGoal();

    ResponseEntity<Object> insertGoal(GoalRequest goal);

    List<TopScorerResponse> getTopScorers();

    List<Goal> getGoalHome(int idmatch, int idteam);

    List<Goal> getGoalAway(int idmatch, int idteam);

}

package com.example.QuanLyDoiBong.controller;

import com.example.QuanLyDoiBong.dto.GoalDTO;
import com.example.QuanLyDoiBong.dto.TopScorerDTO;
import com.example.QuanLyDoiBong.entity.Goal;
import com.example.QuanLyDoiBong.services.GoalServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GoalController {
    private final GoalServices goalServices;

    public GoalController(GoalServices goalServices) {
        this.goalServices = goalServices;
    }

    @GetMapping("/getAllGoals")
    public List<Goal> getAllGoal() {
        return goalServices.getAllGoal();
    }

    @PostMapping("/insertGoals")
    public ResponseEntity<Object> insertGoals(@RequestBody GoalDTO goalDTO) {
        return goalServices.insertGoal(goalDTO);
    }

    @GetMapping("/top-scorers")
    public List<TopScorerDTO> getTopScorers() {
        return goalServices.getTopScorers();
    }

    @GetMapping("/getGoalHome")
    public List<Goal> getGoalHome(@RequestParam("idmatch") int idmatch,
                                  @RequestParam("idteam") int idteam) {
        return goalServices.getGoalHome(idmatch, idteam);
    }

    @GetMapping("/getGoalAway")
    public List<Goal> getGoalAwat(@RequestParam("idmatch") int idmatch,
                                  @RequestParam("idteam") int idteam) {
        return goalServices.getGoalAway(idmatch, idteam);
    }

}

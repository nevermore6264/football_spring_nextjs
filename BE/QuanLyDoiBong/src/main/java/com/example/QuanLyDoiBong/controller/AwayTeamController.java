package com.example.QuanLyDoiBong.controller;

import com.example.QuanLyDoiBong.entity.AwayTeam;
import com.example.QuanLyDoiBong.services.AwayTeamServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
public class AwayTeamController {
    @Autowired
    private AwayTeamServices awayTeamServices;

    @GetMapping("/getAllTeamAway")
    public List<AwayTeam> getAllTeamAway() {
        return awayTeamServices.getAlAwayTeam();
    }

    @PostMapping("/updateTeamAway")
    public ResponseEntity<Object> updateTeamAway(@RequestBody AwayTeam awayTeam) {
        return awayTeamServices.updateAwayTeam(awayTeam);
    }

    @DeleteMapping("/deleteTeamAway/{IDTeamAway}")
    public ResponseEntity<Object> deleteTeamAway(@PathVariable int IDTeamAway) {
        return awayTeamServices.deleteAwayTeam(IDTeamAway);
    }

    @PostMapping("/insertTeamAway")
    public ResponseEntity<Object> insertTeamAway(@RequestBody AwayTeam teamAway) {
        return awayTeamServices.insertAwayTeam(teamAway);
    }

}

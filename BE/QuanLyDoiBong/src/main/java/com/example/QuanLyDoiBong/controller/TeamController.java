package com.example.QuanLyDoiBong.controller;

import com.example.QuanLyDoiBong.entity.Team;
import com.example.QuanLyDoiBong.services.TeamServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class TeamController {
    private TeamServices teamServices;
    @Autowired
    public TeamController(TeamServices teamServices) {
        this.teamServices = teamServices;
    }
    @GetMapping("/getAllTeam")
    public List<Team> getAllTeam(){
        return teamServices.getAllTeam();
    }
    @PostMapping("/updateTeam")
    public ResponseEntity<Object> updateTeam(@RequestBody Team team){
        return teamServices.updateTeam(team);
    }
    @DeleteMapping("/deleteTeam/{IDTeam}")
    public ResponseEntity<Object> deleteTeam(@PathVariable int IDTeam){
        return teamServices.deleteTeam(IDTeam);
    }
    @PostMapping("/insertTeam")
    public ResponseEntity<Object> insertTeam(@RequestBody Team team){
        return teamServices.insertTeam(team);
    }
}

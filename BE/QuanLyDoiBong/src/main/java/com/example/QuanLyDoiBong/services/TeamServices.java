package com.example.QuanLyDoiBong.services;

import com.example.QuanLyDoiBong.entity.Team;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TeamServices {
    List<Team> getAllTeam();
    ResponseEntity<Object> updateTeam(Team team);
    ResponseEntity<Object> deleteTeam(int IDTeam);
    ResponseEntity<Object> insertTeam(Team team);
}

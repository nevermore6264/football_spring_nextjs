package com.example.QuanLyDoiBong.services;

import com.example.QuanLyDoiBong.entity.AwayTeam;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AwayTeamServices {
    List<AwayTeam> getAlAwayTeam();
    ResponseEntity<Object> updateAwayTeam(AwayTeam teamAway);
    ResponseEntity<Object> deleteAwayTeam(int IDTeaAway);
    ResponseEntity<Object> insertAwayTeam(AwayTeam teamAway);
}

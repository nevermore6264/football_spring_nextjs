package com.example.QuanLyDoiBong.services;

import com.example.QuanLyDoiBong.entity.Tournament;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TournamentServices {
    List<Tournament> getAllTournament();
    ResponseEntity<Object> insertTournament(Tournament tournament);
    ResponseEntity<Object> updateTournament(Tournament tournament);
    ResponseEntity<Object> deleteTournament(int IDTournament);
}

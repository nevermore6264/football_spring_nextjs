package com.example.QuanLyDoiBong.controller;

import com.example.QuanLyDoiBong.entity.Tournament;
import com.example.QuanLyDoiBong.services.TournamentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class TournamentController {
    private final TournamentServices tournamentServices;

    @Autowired
    public TournamentController(TournamentServices tournamentServices) {
        this.tournamentServices = tournamentServices;
    }

    @GetMapping("/getAllTournament")
    public List<Tournament> getAllTour() {
        return tournamentServices.getAllTournament();
    }

    @PostMapping("/insertTour")
    public ResponseEntity<Object> insertTour(@RequestBody Tournament tournament) {
        return tournamentServices.insertTournament(tournament);
    }

    @PutMapping("/updateTour")
    public ResponseEntity<Object> updateTour(@RequestBody Tournament tournament) {
        return tournamentServices.updateTournament(tournament);
    }

    @DeleteMapping("/deleteTour/{IDTour}")
    public ResponseEntity<Object> deleteTour(@PathVariable int IDTour) {
        return tournamentServices.deleteTournament(IDTour);
    }
}
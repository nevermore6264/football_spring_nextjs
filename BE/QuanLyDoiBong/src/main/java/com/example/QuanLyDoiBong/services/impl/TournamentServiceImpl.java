package com.example.QuanLyDoiBong.services.impl;

import com.example.QuanLyDoiBong.entity.Tournament;
import com.example.QuanLyDoiBong.repository.TournamentRepository;
import com.example.QuanLyDoiBong.services.TournamentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TournamentServiceImpl implements TournamentServices {
    public final TournamentRepository tournamentRepository;

    @Autowired
    public TournamentServiceImpl(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    @Override
    public List<Tournament> getAllTournament() {
        return tournamentRepository.findAll();
    }

    @Override
    public ResponseEntity<Object> insertTournament(Tournament tournament) {
        try {
            Tournament tournament1 = new Tournament();
            tournament1.setTournamentsName(tournament.getTournamentsName());
            tournament1.setEndDate(tournament.getEndDate());
            tournament1.setStartDate(tournament.getStartDate());
            tournament1.setShows(true);
            tournamentRepository.save(tournament1);
            return new ResponseEntity<>(Map.of("message:", "Thành công", "data", tournament1), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(Map.of("message:", "Lỗi", "error", ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> updateTournament(Tournament tournament) {
        try {
            Optional<Tournament> editTour = tournamentRepository.findById(tournament.getIDTournaments());
            if (editTour.isPresent()) {
                Tournament updateTour = editTour.get();
                updateTour.setTournamentsName(tournament.getTournamentsName());
                updateTour.setStartDate(tournament.getStartDate());
                updateTour.setEndDate(tournament.getEndDate());
                tournamentRepository.save(updateTour);
                return new ResponseEntity<>(Map.of("message:", "Thành công", "data", updateTour), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(Map.of("message:", "Lỗi", "error", "Không tìm thấy"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(Map.of("message:", "Lỗi", "error", ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> deleteTournament(int IDTournament) {
        try {
            Optional<Tournament> tournament = tournamentRepository.findById(IDTournament);
            if (tournament.isPresent()) {
                Tournament deleteTour = tournament.get();
                deleteTour.setShows(false);
                tournamentRepository.save(deleteTour);
                return new ResponseEntity<>(Map.of("message:", "Thành công"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(Map.of("message:", "Lỗi", "error", "Không tìm thấy"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(Map.of("message:", "Lỗi", "error", ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}

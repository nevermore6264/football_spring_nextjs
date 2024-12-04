package com.example.QuanLyDoiBong.services.impl;

import com.example.QuanLyDoiBong.entity.Team;
import com.example.QuanLyDoiBong.repository.TeamRepository;
import com.example.QuanLyDoiBong.services.TeamServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamServices {
    private final TeamRepository teamRepo;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepo) {
        this.teamRepo = teamRepo;
    }

    @Override
    public List<Team> getAllTeam() {
        return teamRepo.findAll();
    }

    @Override
    public ResponseEntity<Object> updateTeam(Team team) {
        try {
            Optional<Team> updateTeam = teamRepo.findById(team.getIDTeam());
            System.out.println(team.getIDTeam());
            System.out.println(updateTeam);
            if (updateTeam.isPresent()) {
                Team teamUpdate = updateTeam.get();
                teamUpdate.setTeamName(team.getTeamName());
                teamUpdate.setCountry(team.getCountry());
                teamUpdate.setCoachName(team.getCoachName());
                Team savedTeam = teamRepo.save(teamUpdate);
                return new ResponseEntity<>(savedTeam, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Không tìm thấy team", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> deleteTeam(int IDTeam) {
        try {
            Optional<Team> delete = teamRepo.findById(IDTeam);
            if (delete.isPresent()) {
                Team notShow = delete.get();
                notShow.setShows(false);
                teamRepo.save(notShow);
                return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Không tìm thấy team", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> insertTeam(Team team) {
        try {
            Team newObj = new Team();
            newObj.setIDTeam(team.getIDTeam());
            newObj.setTeamName(team.getTeamName());
            newObj.setCountry(team.getCountry());
            newObj.setCoachName(team.getCoachName());
            newObj.setShows(true);
            teamRepo.save(newObj);
            return new ResponseEntity<>("Thêm thành công", HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}

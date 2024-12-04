package com.example.QuanLyDoiBong.services.impl;

import com.example.QuanLyDoiBong.dto.request.GoalRequest;
import com.example.QuanLyDoiBong.dto.response.TopScorerResponse;
import com.example.QuanLyDoiBong.entity.Goal;
import com.example.QuanLyDoiBong.entity.Match;
import com.example.QuanLyDoiBong.entity.Player;
import com.example.QuanLyDoiBong.entity.Team;
import com.example.QuanLyDoiBong.repository.GoalRepository;
import com.example.QuanLyDoiBong.repository.MatchRepository;
import com.example.QuanLyDoiBong.repository.PlayerRepository;
import com.example.QuanLyDoiBong.repository.TeamRepository;
import com.example.QuanLyDoiBong.services.GoalServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GoalServicesImpl implements GoalServices {
    private final GoalRepository goalRepository;
    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public GoalServicesImpl(GoalRepository goalRepository, MatchRepository matchRepository, PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.goalRepository = goalRepository;
        this.matchRepository = matchRepository;
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Goal> getAllGoal() {
        return goalRepository.findAll();
    }

    @Override
    public ResponseEntity<Object> insertGoal(GoalRequest goal) {
        try {
            Match getMatch = matchRepository.findById(goal.getIdmatch()).get();
            Player getPlayer = playerRepository.findById(goal.getIdplayer()).get();
            Team getTeam = teamRepository.findById(goal.getIdteam()).get();
            if (getMatch != null && getPlayer != null && getTeam != null) {
                Goal newObj = new Goal();
                newObj.setGoalTime(goal.getGoal_time());
                newObj.setMatch(getMatch);
                newObj.setPlayer(getPlayer);
                newObj.setTeam(getTeam);
                goalRepository.save(newObj);
                return new ResponseEntity<>(Map.of("message:", "Thêm thành công", "data:", newObj), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Không tìm thấy", HttpStatus.NOT_FOUND);
            }

        } catch (Exception ex) {
            return new ResponseEntity<>(Map.of("message:", "Lỗi", "error:", ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<TopScorerResponse> getTopScorers() {
        List<Object[]> topScorersData = goalRepository.findTopScorers();
        List<TopScorerResponse> topScorersDTO = new ArrayList<>();

        for (Object[] obj : topScorersData) {
            Player player = (Player) obj[0];
            Long goalsScored = (Long) obj[1];
            TopScorerResponse dto = new TopScorerResponse(player, goalsScored.intValue());
            topScorersDTO.add(dto);
        }

        return topScorersDTO;
    }

    @Override
    public List<Goal> getGoalHome(int idmatch, int idteam) {
        return goalRepository.findGoalHome(idmatch, idteam);
    }

    @Override
    public List<Goal> getGoalAway(int idmatch, int idteam) {
        return goalRepository.findGoalAway(idmatch, idteam);
    }
}

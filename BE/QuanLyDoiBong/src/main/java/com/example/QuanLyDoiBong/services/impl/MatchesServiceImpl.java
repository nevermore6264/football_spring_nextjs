package com.example.QuanLyDoiBong.services.impl;

import com.example.QuanLyDoiBong.dto.MatchDTO;
import com.example.QuanLyDoiBong.dto.ThongKeMatch;
import com.example.QuanLyDoiBong.dto.response.MathStatisticsResponse;
import com.example.QuanLyDoiBong.entity.AwayTeam;
import com.example.QuanLyDoiBong.entity.Match;
import com.example.QuanLyDoiBong.entity.Team;
import com.example.QuanLyDoiBong.entity.Tournament;
import com.example.QuanLyDoiBong.repository.AwayResponse;
import com.example.QuanLyDoiBong.repository.GoalRepository;
import com.example.QuanLyDoiBong.repository.MatchRepository;
import com.example.QuanLyDoiBong.repository.TeamRepository;
import com.example.QuanLyDoiBong.repository.TournamentRepository;
import com.example.QuanLyDoiBong.services.MatchesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatchesServiceImpl implements MatchesService {
    private final MatchRepository matchRepository;
    private final TournamentRepository tournamentRepository;
    private final TeamRepository teamRepository;
    @Autowired
    private GoalRepository goalRepository;
    @Autowired
    private AwayResponse awayResponse;

    @Autowired
    public MatchesServiceImpl(MatchRepository matchRepository, TournamentRepository tournamentRepository, TeamRepository teamRepository) {
        this.matchRepository = matchRepository;
        this.tournamentRepository = tournamentRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Match> getAllMatch() {
        return matchRepository.findAll();
    }

    @Override
    public ResponseEntity<Object> insertMacth(MatchDTO match) {
        try {
            Tournament getTour = tournamentRepository.findById(match.getIDTournament()).get();
            Team homeTeam = teamRepository.findById(match.getHomeTeamID()).get();
            AwayTeam awayTeam = awayResponse.findById(match.getAwayTeamID()).get();
            Match newObj = new Match(0,
                    getTour,
                    homeTeam,
                    awayTeam,
                    match.getHomeTeamScore(),
                    match.getAwayTeamScore(),
                    match.getMatchDate(),
                    match.getStatus(),
                    match.getYellowCardsHomeTeam(),
                    match.getRedCardsHomeTeam(),
                    match.getYellowCardsAwayTeam(),
                    match.getRedCardsAwayTeam(),
                    match.getLoaiTranDau(),
                    true
            );
            matchRepository.save(newObj);
            return new ResponseEntity<>(Map.of("message", "Thành công", "data", newObj), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(Map.of("message", "Lỗi", "error", ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> updateMacth(MatchDTO match) {
        try {
            Tournament getTour = tournamentRepository.findById(match.getIDTournament()).get();
            Team homeTeam = teamRepository.findById(match.getHomeTeamID()).get();
            AwayTeam awayTeam = awayResponse.findById(match.getAwayTeamID()).get();
            Match select = matchRepository.findById(match.getIDMatch()).get();
            if (getTour != null && homeTeam != null && awayTeam != null && select != null) {
                select.setIDTournaments(getTour);
                select.setHomeTeam(homeTeam);
                select.setAwayTeam(awayTeam);
                select.setHomeTeamScore(match.getHomeTeamScore());
                select.setAwayTeamScore(match.getAwayTeamScore());
                select.setMatchDate(match.getMatchDate());
                select.setStatus(match.getStatus());
                select.setYellowCardsHomeTeam(match.getYellowCardsHomeTeam());
                select.setRedCardsHomeTeam(match.getRedCardsHomeTeam());
                select.setYellowCardsAwayTeam(match.getYellowCardsAwayTeam());
                select.setRedCardsAwayTeam(match.getRedCardsAwayTeam());
                select.setLoaiTranDau(match.getLoaiTranDau());
                matchRepository.save(select);
                return new ResponseEntity<>(Map.of("message", "Thành công", "data", select), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(Map.of("message", "Lỗi", "error", "Không tìm thấy"), HttpStatus.NOT_FOUND);
            }

        } catch (Exception ex) {
            return new ResponseEntity<>(Map.of("message", "Lỗi", "error", ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> deleteMacth(int IDMatch) {
        try {
            Optional<Match> delete = matchRepository.findById(IDMatch);
            if (delete.isPresent()) {
                Match notShow = delete.get();
                notShow.setShows(false);
                matchRepository.save(notShow);
                return new ResponseEntity<>(Map.of("message", "Thành công"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(Map.of("message", "Lỗi", "error", "Không tìm thấy"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(Map.of("message", "Lỗi", "error", ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<ThongKeMatch>> thongKe(int IDTour) {
        List<Object[]> thongKeList = matchRepository.thongKe(IDTour);

        List<ThongKeMatch> result = thongKeList.stream()
                .map(obj -> {
                    ThongKeMatch thongKeMatch = new ThongKeMatch();
                    thongKeMatch.setTeamName((String) obj[0]);
                    thongKeMatch.setSoTranDaDau(((Number) obj[1]).intValue()); // Sử dụng Number.intValue() để chuyển đổi sang int
                    thongKeMatch.setSoBanThua(((Number) obj[2]).intValue());
                    thongKeMatch.setSoBanThang(((Number) obj[3]).intValue());
                    thongKeMatch.setTongSoTranThang(((Number) obj[4]).intValue());
                    thongKeMatch.setTongSoTranThua(((Number) obj[5]).intValue());
                    thongKeMatch.setTongSoTranHoa(((Number) obj[6]).intValue());
                    thongKeMatch.setPoint(((Number) obj[7]).intValue());
                    return thongKeMatch;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<Map<String, Object>>> thongke2(Integer idtour, Integer idteam) {
        List<Object[]> thongKe2;
        if (idtour == null && idteam == null) {
            thongKe2 = matchRepository.thongKe2(idtour, idteam);
        } else if (idtour == null) {
            thongKe2 = matchRepository.thongKe2(idtour, idteam);
        } else if (idteam == null) {
            thongKe2 = matchRepository.thongKe2(idtour, idteam);
        } else {
            thongKe2 = matchRepository.thongKe2(idtour, idteam);
        }

        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Object[] obj : thongKe2) {
            Map<String, Object> matchMap = new HashMap<>();
            matchMap.put("idMatch", obj[0]);
            matchMap.put("homeTeamName", obj[1]);
            matchMap.put("awayTeamName", obj[2]);
            matchMap.put("homeTeamScore", obj[3]);
            matchMap.put("awayTeamScore", obj[4]);
            matchMap.put("matchDate", obj[5]);
            matchMap.put("status", obj[6]);
            matchMap.put("loaiTranDau", obj[7]);
            matchMap.put("idTournament", obj[8]);
            matchMap.put("nametour", obj[9]);
            matchMap.put("homeTeamId", obj[10]);
            matchMap.put("awayTeamId", obj[11]);
            matchMap.put("totalYellowHome", obj[12]);
            matchMap.put("totalYellowAway", obj[13]);
            matchMap.put("totalRedHome", obj[14]);
            matchMap.put("totalRedAway", obj[15]);
            matchMap.put("totalGoalHome", obj[16]);
            matchMap.put("totalGoalAway", obj[17]);
            resultList.add(matchMap);
        }
        return ResponseEntity.ok(resultList);
    }

    @Override
    public List<Match> getByCaculate(Date tuNgay, Date denNgay, int idTour) {
        return matchRepository.getByCaculate(tuNgay, denNgay, idTour);
    }

    @Override
    public ResponseEntity<List<MathStatisticsResponse>> getAllStatistics() {
        List<Object[]> objects = matchRepository.getAllStatistics();

        List<MathStatisticsResponse> result = objects.stream()
                .map(obj -> {
                    MathStatisticsResponse mathStatisticsResponse = new MathStatisticsResponse();
                    mathStatisticsResponse.setTeamName((String) obj[0]);
                    mathStatisticsResponse.setMatches(((Number) obj[1]).intValue());
                    mathStatisticsResponse.setTotalWins(((Number) obj[2]).intValue()); // Sử dụng Number.intValue() để chuyển đổi sang int
                    mathStatisticsResponse.setPoint(((Number) obj[3]).intValue());
                    mathStatisticsResponse.setTournaments(((String) obj[4]));
                    return mathStatisticsResponse;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<Map<String, Object>>> goals() {
        List<Object[]> goalsList = matchRepository.goals();

        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Object[] obj : goalsList) {
            Map<String, Object> matchMap = new HashMap<>();
            matchMap.put("idMatch", obj[0]);
            matchMap.put("homeTeamName", obj[1]);
            matchMap.put("awayTeamName", obj[2]);
            matchMap.put("homeTeamScore", obj[3]);
            matchMap.put("awayTeamScore", obj[4]);
            matchMap.put("matchDate", obj[5]);
            matchMap.put("status", obj[6]);
            matchMap.put("loaiTranDau", obj[7]);
            matchMap.put("idTournament", obj[8]);
            matchMap.put("nametour", obj[9]);
            matchMap.put("homeTeamId", obj[10]);
            matchMap.put("awayTeamId", obj[11]);
            matchMap.put("totalYellowHome", obj[12]);
            matchMap.put("totalYellowAway", obj[13]);
            matchMap.put("totalRedHome", obj[14]);
            matchMap.put("totalRedAway", obj[15]);
            matchMap.put("totalGoalHome", obj[16]);
            matchMap.put("totalGoalAway", obj[17]);
            resultList.add(matchMap);
        }
        return ResponseEntity.ok(resultList);
    }

}

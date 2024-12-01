package com.example.QuanLyDoiBong.controller;

import com.example.QuanLyDoiBong.dto.MatchDTO;
import com.example.QuanLyDoiBong.dto.ThongKeMatch;
import com.example.QuanLyDoiBong.dto.response.MathStatisticsResponse;
import com.example.QuanLyDoiBong.entity.Match;
import com.example.QuanLyDoiBong.services.MatchesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class MatchesController {
    private final MatchesService matchesService;

    @Autowired
    public MatchesController(MatchesService matchesService) {
        this.matchesService = matchesService;
    }

    @GetMapping("/getAllMatch")
    public List<Match> getAllMatch() {
        return matchesService.getAllMatch();
    }

    @PostMapping("/insertMatches")
    public ResponseEntity<Object> insertMatch(@RequestBody MatchDTO matchDTO) {
        return matchesService.insertMacth(matchDTO);
    }

    @PutMapping("/updateMatches")
    public ResponseEntity<Object> updateMatch(@RequestBody MatchDTO matchDTO) {
        return matchesService.updateMacth(matchDTO);
    }

    @DeleteMapping("/deleteMatches/{IDMatch}")
    public ResponseEntity<Object> deleteMatch(@PathVariable int IDMatch) {
        return matchesService.deleteMacth(IDMatch);
    }

    @GetMapping("/thongke/{IDTour}")
    public ResponseEntity<List<ThongKeMatch>> getThongKe(@PathVariable int IDTour) {
        return matchesService.thongKe(IDTour);
    }

    @GetMapping("/statistics")
    public ResponseEntity<List<MathStatisticsResponse>> getAllStatistics() {
        return matchesService.getAllStatistics();
    }

    @GetMapping("/thongke2")
    public ResponseEntity<List<Map<String, Object>>> getThongKe2(@RequestParam(value = "idtour", required = false) Integer idTour,
                                                                 @RequestParam(value = "idteam", required = false) Integer idTeam
    ) {
        return matchesService.thongke2(idTour, idTeam);
    }

    @GetMapping("/goals")
    public ResponseEntity<List<Map<String, Object>>> goals() {
        return matchesService.goals();
    }

    @GetMapping("/getByCaculate")
    public List<Match> getByCaculate(@RequestParam("tuNgay") String tuNgayStr,
                                     @RequestParam("denNgay") String denNgayStr,
                                     @RequestParam("idTour") int idTour) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date tuNgay = dateFormat.parse(tuNgayStr);
            Date denNgay = dateFormat.parse(denNgayStr);
            return matchesService.getByCaculate(tuNgay, denNgay, idTour);
        } catch (ParseException e) {
            e.getMessage();
            return null;
        }
    }
}

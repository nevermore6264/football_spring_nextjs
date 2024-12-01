package com.example.QuanLyDoiBong.services;

import com.example.QuanLyDoiBong.dto.MatchDTO;
import com.example.QuanLyDoiBong.dto.ThongKeMatch;
import com.example.QuanLyDoiBong.dto.response.MathStatisticsResponse;
import com.example.QuanLyDoiBong.entity.Match;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface MatchesService {
    List<Match> getAllMatch();

    ResponseEntity<Object> insertMacth(MatchDTO match);

    ResponseEntity<Object> updateMacth(MatchDTO match);

    ResponseEntity<Object> deleteMacth(int IDMatch);

    ResponseEntity<List<ThongKeMatch>> thongKe(int IDTour);

    ResponseEntity<List<Map<String, Object>>> thongke2(Integer idtour, Integer idteam);

    List<Match> getByCaculate(Date tuNgay, Date denNgay, int idTour);

    ResponseEntity<List<MathStatisticsResponse>> getAllStatistics();

    ResponseEntity<List<Map<String, Object>>> goals();
}

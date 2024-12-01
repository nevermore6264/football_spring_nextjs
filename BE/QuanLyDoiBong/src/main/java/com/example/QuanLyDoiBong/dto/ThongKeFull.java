package com.example.QuanLyDoiBong.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThongKeFull {
    private long IDMatch;
    private String homeTeamName;
    private String awayTeamName;
    private long homeTeamScore;
    private long awayTeamScore;
    private Date matchDate;
    private String status;
    private String tenTour;
    private String loaiTranDau;
    private long idTournament;
    private long homeTeamID;
    private long awayTeamID;
    private long totalYellowHome;
    private long totalRedHome;
    private long totalYellowAway;
    private long totalRedAway;
    private long totalGoalHome;
    private long totalGoalAway;
}

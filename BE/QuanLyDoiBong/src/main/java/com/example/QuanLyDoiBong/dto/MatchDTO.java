package com.example.QuanLyDoiBong.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchDTO {
    @JsonProperty("IDMatch")
    private int IDMatch;
    @JsonProperty("IDTournament")
    private int IDTournament;
    @JsonProperty("HomeTeamID")
    private int HomeTeamID;
    @JsonProperty("AwayTeamID")
    private int AwayTeamID;
    @JsonProperty("HomeTeamScore")
    private int HomeTeamScore;
    @JsonProperty("AwayTeamScore")
    private int AwayTeamScore;
    @JsonProperty("MatchDate")
    private Date MatchDate;
    @JsonProperty("status")
    private String status;
    @JsonProperty("YellowCardsHomeTeam")
    private int YellowCardsHomeTeam;
    @JsonProperty("RedCardsHomeTeam")
    private int RedCardsHomeTeam;
    @JsonProperty("YellowCardsAwayTeam")
    private int YellowCardsAwayTeam;
    @JsonProperty("RedCardsAwayTeam")
    private int RedCardsAwayTeam;
    @JsonProperty("LoaiTranDau")
    private String LoaiTranDau;
}

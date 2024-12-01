package com.example.QuanLyDoiBong.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmatch")
    private int matchID;

    @ManyToOne
    @JoinColumn(name = "idtournaments")
    private Tournament IDTournaments;

    @ManyToOne
    @JoinColumn(name = "home_teamid")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_teamid")
    private AwayTeam awayTeam;

    @Column(name = "home_team_score")
    private int homeTeamScore;

    @Column(name = "away_team_score")
    private int awayTeamScore;

    @Column(name = "match_date")
    private Date matchDate;

    @Column(name = "status")
    private String status;

    @Column(name = "yellow_cards_home_team")
    private int yellowCardsHomeTeam;

    @Column(name = "red_cards_home_team")
    private int redCardsHomeTeam;

    @Column(name = "yellow_cards_away_team")
    private int yellowCardsAwayTeam;

    @Column(name = "red_cards_away_team")
    private int redCardsAwayTeam;

    @Column(name = "loai_tran_dau")
    private String loaiTranDau;
    @Column(name = "shows")
    private boolean shows;
}

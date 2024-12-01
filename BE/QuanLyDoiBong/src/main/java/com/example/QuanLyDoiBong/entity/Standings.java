package com.example.QuanLyDoiBong.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Standings")
public class Standings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="idstandings")
    private int IDStandings;

    @ManyToOne
    @JoinColumn(name = "idteam")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "idtournaments")
    private Tournament tournament;

    @Column(name = "points")
    private int points;

}

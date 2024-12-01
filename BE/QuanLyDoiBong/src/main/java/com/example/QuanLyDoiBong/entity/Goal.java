package com.example.QuanLyDoiBong.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "goals")
public class Goal{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idgoals")
    private int IDGoals;

    @ManyToOne
    @JoinColumn(name = "idmatch")
    private Match match;

    @ManyToOne
    @JoinColumn(name = "idplayer")
    private Player player;

    @Column(name = "goal_time")
    private Time goalTime;
    @ManyToOne
    @JoinColumn(name = "idteam")
    private Team team;
}


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
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDTeam")
    private int IDTeam;
    @Column(name = "team_name")
    private String teamName;
    @Column(name = "coach_name")
    private String coachName;
    @Column(name = "country")
    private String country;
    @Column(name = "shows")
    private boolean shows;
}

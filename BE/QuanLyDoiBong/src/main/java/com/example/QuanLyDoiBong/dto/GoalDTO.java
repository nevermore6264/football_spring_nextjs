package com.example.QuanLyDoiBong.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoalDTO {
    private int idgoals;
    private int idmatch;
    private int idplayer;
    private Time goal_time;
    private int idteam;
}

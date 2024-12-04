package com.example.QuanLyDoiBong.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoalRequest {
    private int idgoals;
    private int idmatch;
    private int idplayer;
    private Time goal_time;
    private int idteam;
}

package com.example.QuanLyDoiBong.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MathStatisticsResponse {
    private String teamName;
    private long matches;
    private long totalWins;
    private long point;
    private String tournaments;
}

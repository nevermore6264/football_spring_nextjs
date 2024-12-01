package com.example.QuanLyDoiBong.dto;

import com.example.QuanLyDoiBong.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopScorerDTO {
    private Player player;
    private int goalsScored;
}

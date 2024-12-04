package com.example.QuanLyDoiBong.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CardRequest {
    private int idcard;
    private int idmatch;
    private int idplayer;
    private int yellow_cards;
    private int red_cards;
    private int idteam;
}

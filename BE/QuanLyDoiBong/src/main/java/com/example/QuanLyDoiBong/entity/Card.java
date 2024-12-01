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
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcard")
    private int IDCard;
    @ManyToOne
    @JoinColumn(name = "idmatch")
    private Match match;
    @ManyToOne
    @JoinColumn(name = "idplayer")
    private Player player;
    @Column(name = "yellow_cards")
    private int yellowCards;
    @Column(name = "red_cards")
    private int redCards;
    @ManyToOne
    @JoinColumn(name = "idteam")
    private Team team;
}


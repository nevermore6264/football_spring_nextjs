package com.example.QuanLyDoiBong.services;

import com.example.QuanLyDoiBong.dto.CardDTO;
import com.example.QuanLyDoiBong.entity.Card;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CardServices {
    ResponseEntity<Object> insertCard(CardDTO cardDTO);
    List<Card> getAllCard();
    List<Card> getCardHome(int idmatch, int idHome);
    List<Card> getCardAway(int idmatch, int idAway);
}

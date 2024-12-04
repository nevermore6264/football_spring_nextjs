package com.example.QuanLyDoiBong.services;

import com.example.QuanLyDoiBong.dto.request.CardRequest;
import com.example.QuanLyDoiBong.entity.Card;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CardServices {
    ResponseEntity<Object> insertCard(CardRequest cardRequest);
    List<Card> getAllCard();
    List<Card> getCardHome(int idmatch, int idHome);
    List<Card> getCardAway(int idmatch, int idAway);
}

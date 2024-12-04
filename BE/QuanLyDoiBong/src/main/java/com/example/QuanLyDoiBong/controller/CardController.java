package com.example.QuanLyDoiBong.controller;

import com.example.QuanLyDoiBong.dto.request.CardRequest;
import com.example.QuanLyDoiBong.entity.Card;
import com.example.QuanLyDoiBong.services.CardServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardController {
    private final CardServices cardServices;

    @Autowired
    public CardController(CardServices cardServices) {
        this.cardServices = cardServices;
    }

    @GetMapping("/getAllCard")
    public List<Card> getAllCard() {
        return cardServices.getAllCard();
    }

    @PostMapping("/insertCard")
    public ResponseEntity<Object> insertCard(@RequestBody CardRequest cardRequest) {
        return cardServices.insertCard(cardRequest);
    }

    @GetMapping("/getCardHome")
    public List<Card> getCardHome(@RequestParam("idmatch") int idmatch,
                                  @RequestParam("idteam") int idteam) {
        return cardServices.getCardHome(idmatch, idteam);
    }

    @GetMapping("/getCardAway")
    public List<Card> getCardAway(@RequestParam("idmatch") int idmatch,
                                  @RequestParam("idteam") int idteam) {
        return cardServices.getCardAway(idmatch, idteam);
    }

}

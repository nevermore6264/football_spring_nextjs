package com.example.QuanLyDoiBong.controller;

import com.example.QuanLyDoiBong.dto.CardDTO;
import com.example.QuanLyDoiBong.entity.Card;
import com.example.QuanLyDoiBong.services.CardServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardController {
    private final CardServices cardServices;
    @Autowired
    public CardController(CardServices cardServices) {
        this.cardServices = cardServices;
    }
    @GetMapping("/getAllCard")
    public List<Card> getAllCard(){
        return cardServices.getAllCard();
    }
    @PostMapping("/insertCard")
    public ResponseEntity<Object> insertCard(@RequestBody CardDTO cardDTO){
        return cardServices.insertCard(cardDTO);
    }
    @GetMapping("/getCardHome")
    public List<Card> getCardHome(@RequestParam("idmatch") int idmatch,
                                  @RequestParam("idteam") int idteam){
        return cardServices.getCardHome(idmatch, idteam);
    }
    @GetMapping("/getCardAway")
    public List<Card> getCardAway(@RequestParam("idmatch") int idmatch,
                                  @RequestParam("idteam") int idteam){
        return cardServices.getCardAway(idmatch, idteam);
    }
}

package com.example.QuanLyDoiBong.services.impl;

import com.example.QuanLyDoiBong.dto.request.CardRequest;
import com.example.QuanLyDoiBong.entity.*;
import com.example.QuanLyDoiBong.repository.*;
import com.example.QuanLyDoiBong.services.CardServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CardServicesImpl implements CardServices {
    private final CardRepository cardRepository;
    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    @Autowired
    public CardServicesImpl(CardRepository cardRepository, MatchRepository matchRepository, PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.cardRepository = cardRepository;
        this.matchRepository = matchRepository;
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }
    @Override
    public ResponseEntity<Object> insertCard(CardRequest cardRequest) {
        try{
            Match getMatch =  matchRepository.findById(cardRequest.getIdmatch()).get();
            Player getPlayer = playerRepository.findById(cardRequest.getIdplayer()).get();
            Team getTeam = teamRepository.findById(cardRequest.getIdteam()).get();
            if(getMatch != null && getPlayer != null && getTeam != null){
                Card card = new Card();
                card.setMatch(getMatch);
                card.setPlayer(getPlayer);
                card.setTeam(getTeam);
                card.setYellowCards(cardRequest.getYellow_cards());
                card.setRedCards(cardRequest.getRed_cards());
                cardRepository.save(card);
                return new ResponseEntity<>(Map.of("message:", "Thêm thành công", "data:", card), HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Không tìm thấy", HttpStatus.NOT_FOUND);
            }

        }catch(Exception ex){
            return new ResponseEntity<>(Map.of("message:", "Lỗi", "error:", ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<Card> getAllCard() {
        return cardRepository.findAll();
    }

    @Override
    public List<Card> getCardHome(int idmatch, int idHome) {
        return cardRepository.getCardHome(idmatch, idHome);
    }

    @Override
    public List<Card> getCardAway(int idmatch, int idAway) {
        return cardRepository.getCardAway(idmatch, idAway);
    }
}

package com.example.QuanLyDoiBong.services;

import com.example.QuanLyDoiBong.dto.PlayerDTO;
import com.example.QuanLyDoiBong.entity.Player;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PlayerService {
    List<Player> getAllPlayer();
    ResponseEntity<Object> updatePlayer(PlayerDTO player);
    ResponseEntity<Object> deletePlayer(int IDPlayer);
    ResponseEntity<Object> insertPlayer(PlayerDTO player);
    ResponseEntity<Object> updateImage(int IDPlayer, MultipartFile avatar) throws IOException;
    List<Player> getPlayerByIDTeam(int IDTeam);
}

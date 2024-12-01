package com.example.QuanLyDoiBong.controller;

import com.example.QuanLyDoiBong.dto.PlayerDTO;
import com.example.QuanLyDoiBong.entity.Player;
import com.example.QuanLyDoiBong.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@CrossOrigin
@RestController
public class PlayerController {
    private PlayerService playerService;
    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }
    @GetMapping("/getAllPlayer")
    public List<Player> getAllPlayer(){
        return playerService.getAllPlayer();
    }
    @PostMapping("/updatePlayer")
    public ResponseEntity<Object> updatePlayer(@RequestBody PlayerDTO playerDTO){
        return playerService.updatePlayer(playerDTO);
    }
    @DeleteMapping("/deletePlayer/{IDPlayer}")
    public ResponseEntity<Object> deletePlayer(@PathVariable int IDPlayer){
        return playerService.deletePlayer(IDPlayer);
    }
    @PostMapping("/insertPlayer")
    public ResponseEntity<Object> insertPlayer(@RequestBody PlayerDTO playerDTO){
        return playerService.insertPlayer(playerDTO);
    }
    @PostMapping("/updateImagePlayer")
    public ResponseEntity<Object> updateImage(@RequestParam("IDPlayer") int IDPlayer,
                                              @RequestParam("avatar")MultipartFile avt) throws IOException {
        return playerService.updateImage(IDPlayer, avt);
    }
    @GetMapping("/getPlayerByIDTeam/{IDTeam}")
    public List<Player> getByIDTeam(@PathVariable int IDTeam){
        return playerService.getPlayerByIDTeam(IDTeam);
    }

    @GetMapping("/getAllPlayerByIDTeam/{IDTeam}")
    public List<Player> getAllByIDTeam(@PathVariable int IDTeam){
        return playerService.getPlayerByIDTeam(IDTeam);
    }
}


package com.example.QuanLyDoiBong.services.impl;

import com.example.QuanLyDoiBong.dto.PlayerDTO;
import com.example.QuanLyDoiBong.entity.Player;
import com.example.QuanLyDoiBong.entity.Team;
import com.example.QuanLyDoiBong.repository.PlayerRepository;
import com.example.QuanLyDoiBong.repository.TeamRepository;
import com.example.QuanLyDoiBong.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Player> getAllPlayer() {
        return playerRepository.findAll();
    }

    @Override
    public ResponseEntity<Object> updatePlayer(PlayerDTO player) {
        try{
            Optional<Player> updatePlayer = playerRepository.findById(player.getIDPlayer());
            Optional<Team> team = teamRepository.findById(player.getIDTeam());
            System.out.println(team);
            System.out.println(updatePlayer);
            if(updatePlayer.isPresent() && team.isPresent()){
                Player PlayUpdate = updatePlayer.get();
                PlayUpdate.setFullName(player.getFullName());
                PlayUpdate.setCountry(player.getCountry());
                PlayUpdate.setDateOfBirth(player.getDateOfBirth());
                PlayUpdate.setPosition(player.getPosition());
                PlayUpdate.setJerseyNumber(player.getJerseyNumber());
                PlayUpdate.setPhoto(player.getPhoto());
                PlayUpdate.setHeight(player.getHeight());
                PlayUpdate.setWeight(player.getWeight());
                PlayUpdate.setEmail(player.getEmail());
                PlayUpdate.setPhone(player.getPhone());
                PlayUpdate.setTeam(team.get());
                PlayUpdate.setContractEndDate(player.getContractEndDate());
                PlayUpdate.setContractStartDate(player.getContractStartDate());
                Player saved = playerRepository.save(PlayUpdate);
                return new ResponseEntity<>(saved, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Không tìm thấy", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> deletePlayer(int IDPlayer) {
        try{
            Optional<Player> delete = playerRepository.findById(IDPlayer);
            if(delete.isPresent()){
               Player notShow = delete.get();
               notShow.setShows(false);
               playerRepository.save(notShow);
                return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Không tìm thấy", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> insertPlayer(PlayerDTO player) {
        try{
            Optional<Team> selectTeam =  teamRepository.findById(player.getIDTeam());
            Player newObj = new Player();
            newObj.setCountry(player.getCountry());
            newObj.setEmail(player.getEmail());
            newObj.setFullName(player.getFullName());
            newObj.setDateOfBirth(player.getDateOfBirth());
            newObj.setPosition(player.getPosition());
            newObj.setJerseyNumber(player.getJerseyNumber());
            newObj.setPhoto(player.getPhoto());
            newObj.setHeight(player.getHeight());
            newObj.setWeight(player.getWeight());
            newObj.setPhone(player.getPhone());
            newObj.setTeam(selectTeam.get());
            newObj.setContractEndDate(player.getContractEndDate());
            newObj.setContractStartDate(player.getContractStartDate());
            newObj.setShows(true);
            playerRepository.save(newObj);
            return new ResponseEntity<>("Thêm thành công", HttpStatus.OK);

        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> updateImage(int IDPlayer, MultipartFile avatar) throws IOException {
        Optional<Player> player = playerRepository.findById(IDPlayer);
        if(player.isPresent()){
            Player update = player.get();
            update.setPhoto(cloudinaryService.uploadImage(avatar));
            playerRepository.save(update);
            return new ResponseEntity<>("Update thành công",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Không tìm thấy", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Player> getPlayerByIDTeam(int IDTeam) {
        return playerRepository.getPlayerByIDTeam(IDTeam);
    }
}

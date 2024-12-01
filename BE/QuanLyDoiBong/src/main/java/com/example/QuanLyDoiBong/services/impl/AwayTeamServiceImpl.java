package com.example.QuanLyDoiBong.services.impl;

import com.example.QuanLyDoiBong.entity.AwayTeam;
import com.example.QuanLyDoiBong.repository.AwayResponse;
import com.example.QuanLyDoiBong.services.AwayTeamServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AwayTeamServiceImpl implements AwayTeamServices {
    @Autowired
    private AwayResponse awayResponse;
    @Override
    public List<AwayTeam> getAlAwayTeam() {
        return awayResponse.findAll();
    }

    @Override
    public ResponseEntity<Object> updateAwayTeam(AwayTeam teamAway) {
        try{
            Optional<AwayTeam> updateAwayTeam = awayResponse.findById(teamAway.getIdAwayTeam());

            if(updateAwayTeam.isPresent()){
                AwayTeam teamUpdate = updateAwayTeam.get();
                teamUpdate.setTeamAwayName(teamAway.getTeamAwayName());
                teamUpdate.setCountryAway(teamAway.getCountryAway());
                teamUpdate.setCoachAwayName(teamAway.getCoachAwayName());
                AwayTeam savedTeam = awayResponse.save(teamUpdate);
                return new ResponseEntity<>(savedTeam, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Không tìm thấy team", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> deleteAwayTeam(int IDTeaAway) {
        try{
            Optional<AwayTeam> delete = awayResponse.findById(IDTeaAway);
            if(delete.isPresent()){
                AwayTeam notShow = delete.get();
                notShow.setShows(false);
                awayResponse.save(notShow);
                return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Không tìm thấy team", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> insertAwayTeam(AwayTeam teamAway) {
        try{
            AwayTeam newObj = new AwayTeam();
            newObj.setIdAwayTeam(teamAway.getIdAwayTeam());;
            newObj.setTeamAwayName(teamAway.getTeamAwayName());
            newObj.setCountryAway(teamAway.getCountryAway());
            newObj.setCoachAwayName(teamAway.getCoachAwayName());
            newObj.setShows(true);
            if(newObj != null){
                awayResponse.save(newObj);
                return new ResponseEntity<>("Thêm thành công", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Phải truyền đủ tham số", HttpStatus.BAD_REQUEST);
            }

        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

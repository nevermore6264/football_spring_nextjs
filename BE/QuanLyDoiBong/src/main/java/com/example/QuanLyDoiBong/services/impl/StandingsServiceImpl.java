package com.example.QuanLyDoiBong.services.impl;

import com.example.QuanLyDoiBong.entity.Standings;
import com.example.QuanLyDoiBong.repository.StandingsRepository;
import com.example.QuanLyDoiBong.services.StandingsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StandingsServiceImpl implements StandingsServices {
    @Autowired
    private StandingsRepository standingsRepository;

    @Override
    public List<Standings> getAllAsc() {
        return standingsRepository.findAllByOrderByPointsDesc();
    }

}

package com.example.QuanLyDoiBong.repository;

import com.example.QuanLyDoiBong.entity.Standings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StandingsRepository extends JpaRepository<Standings, Integer> {
    List<Standings> findAllByOrderByPointsDesc();

}

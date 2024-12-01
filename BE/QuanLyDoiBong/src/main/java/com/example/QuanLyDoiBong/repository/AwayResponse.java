package com.example.QuanLyDoiBong.repository;

import com.example.QuanLyDoiBong.entity.AwayTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AwayResponse extends JpaRepository<AwayTeam, Integer> {

}

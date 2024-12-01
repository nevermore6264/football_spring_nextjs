package com.example.QuanLyDoiBong.repository;

import com.example.QuanLyDoiBong.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    @Query(value = "select * from player where IDTeam = ?1", nativeQuery = true)
    List<Player> getPlayerByIDTeam(int IDTeam);
}

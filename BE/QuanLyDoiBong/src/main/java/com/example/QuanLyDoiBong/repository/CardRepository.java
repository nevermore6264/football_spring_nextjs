package com.example.QuanLyDoiBong.repository;

import com.example.QuanLyDoiBong.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    @Query(value = "select * from cards where idmatch = ?1 and idteam = ?2", nativeQuery = true)
    public List<Card> getCardHome(int idmatch, int idHome);
    @Query(value = "select * from cards where idmatch = ?1 and idteam = ?2", nativeQuery = true)
    public List<Card> getCardAway(int idmatch, int idAway);
}

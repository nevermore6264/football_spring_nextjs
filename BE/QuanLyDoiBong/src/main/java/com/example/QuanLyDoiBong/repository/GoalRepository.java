package com.example.QuanLyDoiBong.repository;

import com.example.QuanLyDoiBong.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Integer> {
    @Query("SELECT g.player, COUNT(g.IDGoals) AS goalsScored " +
            "FROM Goal g " +
            "GROUP BY g.player " +
            "ORDER BY goalsScored DESC")
    List<Object[]> findTopScorers();
    @Query(value = "SELECT * from goals where idmatch= ?1 and idteam = ?2", nativeQuery = true)
    List<Goal> findGoalHome(int idmatch, int idHome);
    @Query(value = "SELECT * from goals where idmatch= ?1 and idteam = ?2", nativeQuery = true)
    List<Goal> findGoalAway(int idmatch, int idAway);
}

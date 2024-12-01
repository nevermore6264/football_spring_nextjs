package com.example.QuanLyDoiBong.repository;

import com.example.QuanLyDoiBong.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {
    @Query(value = "SELECT \n" +
            "    Team.team_name AS TeamName,\n" +
            "    COUNT(DISTINCT matches.idmatch) AS MatchesPlayed,\n" +
            "    SUM(CASE WHEN matches.home_teamid = Team.idteam THEN matches.away_team_score ELSE matches.home_team_score END) AS Loss,\n" +
            "    SUM(CASE WHEN matches.home_teamid = Team.idteam THEN matches.home_team_score ELSE matches.away_team_score END) AS Goal,\n" +
            "    SUM(CASE WHEN matches.home_teamid = Team.idteam AND matches.home_team_score > matches.away_team_score THEN 1\n" +
            "             WHEN matches.away_teamid = Team.idteam AND matches.away_team_score > matches.home_team_score THEN 1 ELSE 0 END) AS Wins,\n" +
            "    SUM(CASE WHEN matches.home_teamid = Team.idteam AND matches.home_team_score < matches.away_team_score THEN 1\n" +
            "             WHEN matches.away_teamid = Team.idteam AND matches.away_team_score < matches.home_team_score THEN 1 ELSE 0 END) AS Losses,\n" +
            "    SUM(CASE WHEN matches.home_team_score = matches.away_team_score THEN 1 ELSE 0 END) AS Draws,\n" +
            "    SUM(CASE WHEN matches.home_teamid = Team.idteam AND matches.home_team_score > matches.away_team_score THEN 3\n" +
            "             WHEN matches.away_teamid = Team.idteam AND matches.away_team_score > matches.home_team_score THEN 3\n" +
            "             WHEN matches.home_teamid = Team.idteam AND matches.home_team_score = matches.away_team_score THEN 1\n" +
            "             WHEN matches.away_teamid = Team.idteam AND matches.home_team_score = matches.away_team_score THEN 1 ELSE 0 END) AS Points\n" +
            "FROM \n" +
            "    Team\n" +
            "LEFT JOIN \n" +
            "    matches ON Team.idteam = matches.home_teamid OR Team.idteam = matches.away_teamid\n" +
            "WHERE \n" +
            "    matches.idtournaments = ?\n" +
            "    AND matches.status = 'Finished'\n" +
            "GROUP BY \n" +
            "    Team.idteam;\n", nativeQuery = true)
    List<Object[]> thongKe(int tournamentId);


    @Query(value = "SELECT \n" +
            "                m.idmatch,\n" +
            "                t1.team_name AS home_team_name,\n" +
            "                t2.team_away_name AS away_team_name,\n" +
            "                m.home_team_score,\n" +
            "                m.away_team_score,\n" +
            "                m.match_date,\n" +
            "                m.status,\n" +
            "                m.loai_tran_dau,\n" +
            "                m.idtournaments,\n" +
            "                tournaments.tournaments_name AS tournament_name, \n" +
            "                m.home_teamid,\n" +
            "                m.away_teamid,\n" +
            "                COALESCE(c1.total_yellow_cards, 0) AS total_yellow_cards_home,\n" +
            "                COALESCE(c2.total_yellow_cards, 0) AS total_yellow_cards_away,\n" +
            "                COALESCE(c1.total_red_cards, 0) AS total_red_cards_home,\n" +
            "                COALESCE(c2.total_red_cards, 0) AS total_red_cards_away,\n" +
            "                COALESCE(g1.total_goals, 0) AS total_goals_home,\n" +
            "                COALESCE(g2.total_goals, 0) AS total_goals_away\n" +
            "            FROM \n" +
            "                matches m\n" +
            "            INNER JOIN \n" +
            "                Team t1 ON m.home_teamid = t1.idteam\n" +
            "            INNER JOIN \n" +
            "                AwayTeam t2 ON m.away_teamid = t2.id_away_team\n" +
            "            LEFT JOIN \n" +
            "                (SELECT idmatch, idteam, SUM(yellow_cards) AS total_yellow_cards, SUM(red_cards) AS total_red_cards\n" +
            "                 FROM cards\n" +
            "                 GROUP BY idmatch, idteam) c1 ON m.idmatch = c1.idmatch AND m.home_teamid = c1.idteam\n" +
            "            LEFT JOIN \n" +
            "                (SELECT idmatch, idteam, SUM(yellow_cards) AS total_yellow_cards, SUM(red_cards) AS total_red_cards\n" +
            "                 FROM cards\n" +
            "                 GROUP BY idmatch, idteam) c2 ON m.idmatch = c2.idmatch AND m.away_teamid = c2.idteam\n" +
            "            LEFT JOIN \n" +
            "                (SELECT idmatch, idteam, COUNT(*) AS total_goals\n" +
            "                 FROM goals\n" +
            "                 GROUP BY idmatch, idteam) g1 ON m.idmatch = g1.idmatch AND m.home_teamid = g1.idteam\n" +
            "            LEFT JOIN \n" +
            "                (SELECT idmatch, idteam, COUNT(*) AS total_goals\n" +
            "                 FROM goals\n" +
            "                 GROUP BY idmatch, idteam) g2 ON m.idmatch = g2.idmatch AND m.away_teamid = g2.idteam\n" +
            "            INNER JOIN\n" +
            "                tournaments ON m.idtournaments = tournaments.idtournaments \n" +
            "            WHERE \n" +
            "                (m.idtournaments = IFNULL(?1, m.idtournaments))  \n" +
            "                AND (m.home_teamid = IFNULL(?2, m.home_teamid) OR m.away_teamid = IFNULL(?2, m.away_teamid))\n" +
            "                AND m.loai_tran_dau = 'chinhthuc' \n" +
            "                AND m.status = 'Finished'", nativeQuery = true)
    List<Object[]> thongKe2(Integer tournamentId, Integer idteam);


    @Query(value = "SELECT *\n" +
            "FROM matches\n" +
            "WHERE match_date BETWEEN ?1 AND ?2 \n" +
            "AND idtournaments = ?3 \n" +
            "AND shows = 1;\n", nativeQuery = true)
    List<Match> getByCaculate(Date tuNgay, Date denNgay, int idTour);

    @Query(value = "SELECT \n" +
            "    Team.team_name AS teamName,\n" +
            "    COUNT(DISTINCT matches.idmatch) AS matches,\n" +
            "    SUM(CASE WHEN matches.home_teamid = Team.idteam AND matches.home_team_score > matches.away_team_score THEN 1\n" +
            "             WHEN matches.away_teamid = Team.idteam AND matches.away_team_score > matches.home_team_score THEN 1 ELSE 0 END) AS totalWins,\n" +
            "    SUM(CASE WHEN matches.home_teamid = Team.idteam AND matches.home_team_score > matches.away_team_score THEN 3\n" +
            "             WHEN matches.away_teamid = Team.idteam AND matches.away_team_score > matches.home_team_score THEN 3\n" +
            "             WHEN matches.home_teamid = Team.idteam AND matches.home_team_score = matches.away_team_score THEN 1\n" +
            "             WHEN matches.away_teamid = Team.idteam AND matches.home_team_score = matches.away_team_score THEN 1 ELSE 0 END) AS point,\n" +
            "     tournaments.tournaments_name        \n" +
            "FROM \n" +
            "    Team\n" +
            "LEFT JOIN \n" +
            "    matches ON Team.idteam = matches.home_teamid OR Team.idteam = matches.away_teamid\n" +
            "LEFT JOIN \n" +
            "\ttournaments on tournaments.idtournaments = matches.idtournaments\n" +
            "WHERE \n" +
            "\tmatches.status = 'Finished'\n" +
            "GROUP BY \n" +
            "    Team.idteam;", nativeQuery = true)
    List<Object[]> getAllStatistics();

    @Query(value = "SELECT \n" +
            "    m.idmatch,\n" +
            "    t1.team_name AS home_team_name,\n" +
            "    t2.team_away_name AS away_team_name,\n" +
            "    m.home_team_score,\n" +
            "    m.away_team_score,\n" +
            "    m.match_date,\n" +
            "    m.status,\n" +
            "    m.loai_tran_dau,\n" +
            "    m.idtournaments,\n" +
            "    tournaments.tournaments_name AS tournament_name, \n" +
            "    m.home_teamid,\n" +
            "    m.away_teamid,\n" +
            "    COALESCE(c1.total_yellow_cards, 0) AS total_yellow_cards_home,\n" +
            "    COALESCE(c2.total_yellow_cards, 0) AS total_yellow_cards_away,\n" +
            "    COALESCE(c1.total_red_cards, 0) AS total_red_cards_home,\n" +
            "    COALESCE(c2.total_red_cards, 0) AS total_red_cards_away,\n" +
            "    COALESCE(g1.total_goals, 0) AS total_goals_home,\n" +
            "    COALESCE(g2.total_goals, 0) AS total_goals_away\n" +
            "FROM \n" +
            "    matches m\n" +
            "INNER JOIN \n" +
            "    Team t1 ON m.home_teamid = t1.idteam\n" +
            "INNER JOIN \n" +
            "    AwayTeam t2 ON m.away_teamid = t2.id_away_team\n" +
            "LEFT JOIN \n" +
            "    (SELECT idmatch, idteam, SUM(yellow_cards) AS total_yellow_cards, SUM(red_cards) AS total_red_cards\n" +
            "     FROM cards\n" +
            "     GROUP BY idmatch, idteam) c1 ON m.idmatch = c1.idmatch AND m.home_teamid = c1.idteam\n" +
            "LEFT JOIN \n" +
            "    (SELECT idmatch, idteam, SUM(yellow_cards) AS total_yellow_cards, SUM(red_cards) AS total_red_cards\n" +
            "     FROM cards\n" +
            "     GROUP BY idmatch, idteam) c2 ON m.idmatch = c2.idmatch AND m.away_teamid = c2.idteam\n" +
            "LEFT JOIN \n" +
            "    (SELECT idmatch, idteam, COUNT(*) AS total_goals\n" +
            "     FROM goals\n" +
            "     GROUP BY idmatch, idteam) g1 ON m.idmatch = g1.idmatch AND m.home_teamid = g1.idteam\n" +
            "LEFT JOIN \n" +
            "    (SELECT idmatch, idteam, COUNT(*) AS total_goals\n" +
            "     FROM goals\n" +
            "     GROUP BY idmatch, idteam) g2 ON m.idmatch = g2.idmatch AND m.away_teamid = g2.idteam\n" +
            "INNER JOIN\n" +
            "    tournaments ON m.idtournaments = tournaments.idtournaments \n" +
            "WHERE \n" +
            "    m.loai_tran_dau = 'chinhthuc' \n" +
            "    AND m.status = 'Finished'\n", nativeQuery = true)
    List<Object[]> goals();
}

create database quanlydoibong;
use quanlydoibong;

create table Team
(
    idteam int auto_increment primary key,
    team_name nvarchar(255) not null,
    coach_name nvarchar(255) not null,
    country nvarchar(255) not null,
    shows bit
);
create table AwayTeam
(
    id_away_team int auto_increment primary key,
    team_away_name nvarchar(255) not null,
    coach_away_name nvarchar(255) not null,
    country_away nvarchar(255) not null,
	shows bit
);
CREATE TABLE player
(
    idplayer int auto_increment primary key,
    full_name nvarchar(255) not null,
    date_of_birth date not null,
    country nvarchar(255) not null,
    position nvarchar(255) not null,
    jersey_number nvarchar(15) not null,
    photo longtext,
    height nvarchar(25),
    weight nvarchar(25),
    email nvarchar(50),
    phone nvarchar(50),
    IDTeam int,
    shows bit,
    contract_start_date date,
    contract_end_date date,
    foreign key(idteam) references Team(idteam)
);

create table tournaments
(
    idtournaments int auto_increment primary key,
    tournaments_name nvarchar(255) not null,
    start_date date,
    end_date date,
	shows bit
);
create table matches
(
    idmatch  int auto_increment primary key,
    idtournaments int,
    home_teamid int,
    away_teamid int,
    home_team_score int,
    away_team_score int,
    match_date date,
    status nvarchar(25),
    yellow_cards_home_team int,
    red_cards_home_team int,
    yellow_cards_away_team int,
    red_cards_away_team int,
    loai_tran_dau nvarchar(255),
    foreign key(idtournaments) references tournaments(idtournaments),
    foreign key(home_teamid) references Team(idteam),
    foreign key(away_teamid) references AwayTeam(id_away_team),
	shows bit
);
create table Standings
(
    idstandings int auto_increment primary key,
    idteam int,
    idtournaments int,
    points int,
    foreign key(idteam) references Team(idteam),
    foreign key(idtournaments) references tournaments(idtournaments)
);
create table goals
(
    idgoals int auto_increment primary key,
    idmatch int,
    idplayer int,
    goal_time time,
    idteam int,
    foreign key(idteam) references Team(idteam),
    foreign key(idmatch) references matches(idmatch),
    foreign key(idplayer) references player(idplayer)
);
create table cards
(
    idcard int auto_increment primary key,
    idmatch int,
    idplayer int,
    yellow_cards INT,
    red_cards INT,
    idteam int,
    foreign key(idteam) references Team(idteam),
    foreign key(idmatch) references matches(idmatch),
    foreign key(idplayer) references player(idplayer)
);
create table accounts
(
    user_name nvarchar(255) not null primary key,
    pass_word nvarchar(255) not null,
    role nvarchar(255) not null
);

INSERT INTO Team (team_name, coach_name, country, shows) VALUES
('Team A', 'Coach A', 'Country A', 1),
('Team B', 'Coach B', 'Country B', 1),
('Team C', 'Coach C', 'Country C', 1),
('Team D', 'Coach D', 'Country D', 1),
('Team E', 'Coach E', 'Country E', 1);
INSERT INTO AwayTeam (team_away_name, coach_away_name, country_away, shows) VALUES
('Away Team 1', 'Coach 1', 'Country 1', 1),
('Away Team 2', 'Coach 2', 'Country 2', 1),
('Away Team 3', 'Coach 3', 'Country 3', 1),
('Away Team 4', 'Coach 4', 'Country 4', 1),
('Away Team 5', 'Coach 5', 'Country 5', 1);
INSERT INTO player (full_name, date_of_birth, country, position, jersey_number, photo, height, weight, email, phone, IDTeam, shows, contract_start_date, contract_end_date) VALUES
('Player 1', '2000-01-01', 'Country A', 'Position 1', '10', 'photo_url_1', '180cm', '70kg', 'player1@example.com', '123456789', 1, 1, '2020-01-01', '2022-01-01'),
('Player 2', '1999-02-02', 'Country B', 'Position 2', '9', 'photo_url_2', '175cm', '65kg', 'player2@example.com', '987654321', 2, 1, '2020-02-01', '2022-02-01'),
('Player 3', '1998-03-03', 'Country C', 'Position 3', '8', 'photo_url_3', '185cm', '75kg', 'player3@example.com', '555555555', 3, 1, '2020-03-01', '2022-03-01'),
('Player 4', '1997-04-04', 'Country D', 'Position 4', '7', 'photo_url_4', '170cm', '60kg', 'player4@example.com', '444444444', 4, 1, '2020-04-01', '2022-04-01'),
('Player 5', '1996-05-05', 'Country E', 'Position 5', '6', 'photo_url_5', '190cm', '80kg', 'player5@example.com', '333333333', 5, 1, '2020-05-01', '2022-05-01');
INSERT INTO tournaments (tournaments_name, start_date, end_date, shows) VALUES
('Tournament 1', '2024-01-01', '2024-06-01', 1),
('Tournament 2', '2024-02-01', '2024-07-01', 1),
('Tournament 3', '2024-03-01', '2024-08-01', 1),
('Tournament 4', '2024-04-01', '2024-09-01', 1),
('Tournament 5', '2024-05-01', '2024-10-01', 1);
INSERT INTO matches (idtournaments, home_teamid, away_teamid, home_team_score, away_team_score, match_date, status, yellow_cards_home_team, red_cards_home_team, yellow_cards_away_team, red_cards_away_team, loai_tran_dau, shows) VALUES
(1, 1, 2, 2, 1, '2024-01-10', 'Completed', 2, 0, 1, 0, 'Friendly', 1),
(2, 2, 3, 1, 1, '2024-02-10', 'In Progress', 1, 0, 2, 0, 'League', 1),
(3, 3, 4, 3, 2, '2024-03-10', 'Scheduled', 3, 0, 1, 0, 'Cup', 1),
(4, 4, 5, 2, 2, '2024-04-10', 'Completed', 2, 0, 2, 1, 'Friendly', 1),
(5, 5, 1, 1, 2, '2024-05-10', 'Completed', 1, 1, 2, 0, 'League', 1);

-- trigger tính điểm 
DELIMITER //
CREATE TRIGGER CalculatePointsAfterMatchInsert
    AFTER UPDATE ON matches
    FOR EACH ROW
BEGIN
    DECLARE HomeTeamPoints INT;
    DECLARE AwayTeamPoints INT;
    DECLARE existingHomeTeam INT;
    DECLARE existingAwayTeam INT;

    IF NEW.loai_tran_dau = 'chinhthuc' THEN

        -- Tính điểm cho đội nhà
        SET HomeTeamPoints = CASE
                                WHEN NEW.home_team_score > NEW.away_team_score THEN 3
                                WHEN NEW.home_team_score = NEW.away_team_score THEN 1
                                ELSE 0
END;

-- Tính điểm cho đội khách
SET AwayTeamPoints = CASE
                                WHEN NEW.away_team_score > NEW.home_team_score THEN 3
                                WHEN NEW.home_team_score = NEW.away_team_score THEN 1
                                ELSE 0
END;

        -- Kiểm tra xem trận đấu đã kết thúc chưa
        IF NEW.status = 'Finished' THEN

            -- Tìm kiếm điểm của đội nhà trong bảng Standings
SELECT idteam INTO existingHomeTeam
FROM Standings
WHERE idteam = NEW.home_teamid AND idtournaments = NEW.idtournaments;

-- Tìm kiếm điểm của đội khách trong bảng Standings
SELECT idteam INTO existingAwayTeam
FROM Standings
WHERE idteam = NEW.away_teamid AND idtournaments = NEW.idtournaments;

-- Kiểm tra xem điểm cho đội nhà đã tồn tại trong bảng Standings hay chưa, nếu chưa thì thêm mới, nếu có rồi thì cập nhật
IF existingHomeTeam IS NULL THEN
                INSERT INTO Standings (idteam, idtournaments, points)
                VALUES (NEW.home_teamid, NEW.idtournaments, HomeTeamPoints);
ELSE
UPDATE Standings
SET points = points + HomeTeamPoints
WHERE idteam = NEW.home_teamid AND idtournaments = NEW.idtournaments;
END IF;

            -- Kiểm tra xem điểm cho đội khách đã tồn tại trong bảng Standings hay chưa, nếu chưa thì thêm mới, nếu có rồi thì cập nhật
            IF existingAwayTeam IS NULL THEN
                INSERT INTO Standings (idteam, idtournaments, points)
                VALUES (NEW.away_teamid, NEW.idtournaments, AwayTeamPoints);
ELSE
UPDATE Standings
SET points = points + AwayTeamPoints
WHERE idteam = NEW.away_teamid AND idtournaments = NEW.idtournaments;
END IF;

END IF;
END IF;
END;
//
DELIMITER ;

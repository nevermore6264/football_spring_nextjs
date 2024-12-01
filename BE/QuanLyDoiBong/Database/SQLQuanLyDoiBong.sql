create database quanlydoibong;
use quanlydoibong;

create table Team
(
    idteam int auto_increment primary key,
    team_name nvarchar(255) not null,
    coach_name nvarchar(255) not null,
    country nvarchar(255) not null
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
    contract_start_date date,
    contract_end_date date,
    foreign key(idteam) references Team(idteam)
);

create table tournaments
(
    idtournaments int auto_increment primary key,
    tournaments_name nvarchar(255) not null,
    start_date date,
    end_date date
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
    foreign key(away_teamid) references Team(idteam)
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

-- trigger tính điểm
DELIMITER //
CREATE TRIGGER CalculatePointsAfterMatchInsert
    AFTER INSERT ON matches
    FOR EACH ROW
BEGIN
    DECLARE HomeTeamPoints INT;
    DECLARE AwayTeamPoints INT;
    DECLARE existingHomeTeam INT;
    DECLARE existingAwayTeam INT;

    IF NEW.loai_tran_dau = 'chinh thuc' THEN

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



-- Thêm dữ liệu vào bảng Team
INSERT INTO Team (team_name, coach_name, country) VALUES
                                                      ('Team A', 'Coach A', 'Country A'),
                                                      ('Team B', 'Coach B', 'Country B'),
                                                      ('Team C', 'Coach C', 'Country C'),
                                                      ('Team D', 'Coach D', 'Country D'),
                                                      ('Team E', 'Coach E', 'Country E');

-- Thêm dữ liệu vào bảng player
INSERT INTO player (full_name, date_of_birth, country, position, jersey_number, IDTeam, contract_start_date, contract_end_date) VALUES
                                                                                                                                    ('Player 1', '2000-01-01', 'Country A', 'Forward', '10', 1, '2022-01-01', '2023-01-01'),
                                                                                                                                    ('Player 2', '2000-02-02', 'Country B', 'Midfielder', '8', 2, '2022-01-01', '2023-01-01'),
                                                                                                                                    ('Player 3', '2000-03-03', 'Country C', 'Defender', '5', 3, '2022-01-01', '2023-01-01'),
                                                                                                                                    ('Player 4', '2000-04-04', 'Country D', 'Goalkeeper', '1', 4, '2022-01-01', '2023-01-01'),
                                                                                                                                    ('Player 5', '2000-05-05', 'Country E', 'Forward', '7', 5, '2022-01-01', '2023-01-01');

-- Thêm dữ liệu vào bảng tournaments
INSERT INTO tournaments (tournaments_name, start_date, end_date) VALUES
                                                                     ('Tournament 1', '2022-01-01', '2022-01-31'),
                                                                     ('Tournament 2', '2022-02-01', '2022-02-28'),
                                                                     ('Tournament 3', '2022-03-01', '2022-03-31'),
                                                                     ('Tournament 4', '2022-04-01', '2022-04-30'),
                                                                     ('Tournament 5', '2022-05-01', '2022-05-31');

-- Thêm dữ liệu vào bảng matches
INSERT INTO matches (idtournaments, home_teamid, away_teamid, home_team_score, away_team_score, match_date, status, yellow_cards_home_team, red_cards_home_team, yellow_cards_away_team, red_cards_away_team, loai_tran_dau) VALUES
                                                                                                                                                                                                                                 (1, 1, 2, 1, 2, '2022-01-05', 'Finished', 2, 0, 1, 0, 'chinh thuc'),
                                                                                                                                                                                                                                 (2, 2, 3, 1, 1, '2022-02-10', 'Finished', 1, 0, 1, 0, 'Loai Tran Dau 2'),
                                                                                                                                                                                                                                 (3, 3, 4, 3, 0, '2022-03-15', 'Finished', 0, 0, 2, 0, 'Loai Tran Dau 3'),
                                                                                                                                                                                                                                 (4, 4, 5, 0, 2, '2022-04-20', 'Finished', 0, 1, 0, 0, 'Loai Tran Dau 4'),
                                                                                                                                                                                                                                 (5, 5, 1, 2, 2, '2022-05-25', 'Finished', 1, 1, 1, 1, 'Loai Tran Dau 5');
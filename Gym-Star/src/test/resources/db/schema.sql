CREATE TABLE powertraining (
power_id INT PRIMARY KEY AUTO_INCREMENT,
training_count INT DEFAULT 0
);

CREATE TABLE cardiotraining (
cardio_id INT PRIMARY KEY AUTO_INCREMENT,
training_count INT DEFAULT 0
);

CREATE TABLE statistics (
statistics_id INT PRIMARY KEY AUTO_INCREMENT,
power_id INT,
cardio_id INT,
FOREIGN KEY (power_id) REFERENCES powertraining(power_id),
FOREIGN KEY (cardio_id) REFERENCES cardiotraining(cardio_id)
);

CREATE TABLE gym (
gym_id INT PRIMARY KEY AUTO_INCREMENT,
gym_name NVARCHAR(80) NOT NULL,
street NVARCHAR (80) NOT NULL,
city NVARCHAR(30) NOT NULL,
building_number INT NOT NULL
);

CREATE TABLE trainer (
trainer_pesel BIGINT PRIMARY KEY,
trainer_name NVARCHAR(30) NOT NULL,
trainer_surname NVARCHAR (80) NOT NULL,
cost INT NOT NULL,
gym_id INT,
FOREIGN KEY (gym_id)  REFERENCES gym(gym_id)
);

CREATE TABLE sportsmen (
sportsman_pesel BIGINT PRIMARY KEY,
sportsman_name NVARCHAR(30) NOT NULL,
sportsman_surname NVARCHAR (80) NOT NULL,
gender ENUM ('F','M',"N/A") NOT NULL,
trainer_pesel BIGINT,
statistics_id INT,
gym_id INT,
FOREIGN KEY (trainer_pesel)  REFERENCES trainer(trainer_pesel),
FOREIGN KEY (gym_id)  REFERENCES gym(gym_id),
FOREIGN KEY (statistics_id) REFERENCES statistics(statistics_id)
);

CREATE TABLE sportsmentrainingtimestatistics (
statistics_id INT PRIMARY KEY AUTO_INCREMENT,
date DATE NOT NULL,
time TIME NOT NULL,
sportsmanstats_id int NOT NULL,
FOREIGN KEY (sportsmanstats_id) REFERENCES statistics(statistics_id)
);

ALTER TABLE trainer
ADD COLUMN sportsman BIGINT;

ALTER TABLE trainer
ADD FOREIGN KEY (sportsman) REFERENCES sportsmen(sportsman_pesel);

CREATE TABLE trainersatgym (
trainer_pesel BIGINT,
gym_id INT,
PRIMARY KEY(trainer_pesel, gym_id),
FOREIGN KEY (trainer_pesel) REFERENCES trainer(trainer_pesel),
FOREIGN KEY (gym_id) REFERENCES gym(gym_id) );

DELIMITER //
CREATE PROCEDURE getsportsmanstats (sportsmanStatsId INT)
BEGIN
SELECT * FROM sportsmentrainingtimestatistics
WHERE sportsmanstats_id=sportsmanStatsId;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE trainCardio (statsId INT, cardiosportsmanID INT)
BEGIN
UPDATE cardiotraining SET training_count=training_count+1 WHERE cardio_id = cardiosportsmanID;
INSERT INTO sportsmentrainingtimestatistics VALUES(NULL,curdate(),curtime(), statsId);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE trainPower (statsId INT, powersportsmanID INT)
BEGIN
UPDATE powertraining SET training_count=training_count+1 WHERE power_id = powersportsmanID;
INSERT INTO sportsmentrainingtimestatistics VALUES(NULL,curdate(),curtime(), statsId);
END //
DELIMITER ;

INSERT INTO gym VALUES ('1','pierwsza', 'sezamkowa','rudy', 102);
INSERT INTO gym VALUES ('2','druga', 'sezamkowa','kraków', 102);
INSERT INTO gym VALUES ('4','Gym', 'ToDelete','kraków', 200);
INSERT INTO cardiotraining VALUES (1,2);
INSERT INTO powertraining VALUES (1,5);
INSERT INTO statistics VALUES (1,1,1);
INSERT INTO sportsmentrainingtimestatistics VALUES (1,'2020-01-22','18:18:52',1);
INSERT INTO trainer VALUES ('57122299175','John','Wick','30',NULL, NULL);

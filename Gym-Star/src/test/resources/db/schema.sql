
create TABLE PowerTraining (
power_id INT PRIMARY KEY AUTO_INCREMENT,
training_count INT DEFAULT 0
);

create TABLE CardioTraining (
cardio_id INT PRIMARY KEY AUTO_INCREMENT,
training_count INT DEFAULT 0
);

create TABLE Statistics (
statistics_id INT PRIMARY KEY AUTO_INCREMENT,
power_id INT,
cardio_id INT,
FOREIGN KEY (power_id) REFERENCES PowerTraining(power_id),
FOREIGN KEY (cardio_id) REFERENCES CardioTraining(cardio_id)
);

create TABLE Gym (
gym_id INT PRIMARY KEY AUTO_INCREMENT,
gym_name NVARCHAR(80) NOT NULL,
street NVARCHAR (80) NOT NULL,
city NVARCHAR(30) NOT NULL,
building_number INT NOT NULL
);

create TABLE Trainer (
trainer_pesel BIGINT PRIMARY KEY,
trainer_name NVARCHAR(30) NOT NULL,
trainer_surname NVARCHAR (80) NOT NULL,
cost INT NOT NULL
);

create TABLE SportsMen (
sportsman_pesel BIGINT PRIMARY KEY,
sportsman_name NVARCHAR(30) NOT NULL,
sportsman_surname NVARCHAR (80) NOT NULL,
gender ENUM ('F','M',"N/A") NOT NULL,
trainer_pesel BIGINT,
statistics_id INT,
FOREIGN KEY (trainer_pesel)  REFERENCES Trainer(trainer_pesel),
FOREIGN KEY (statistics_id) REFERENCES Statistics(statistics_id)
);

alter table SportsMen
add COLUMN gym_id INT;

alter table SportsMen
add FOREIGN KEY (gym_id) REFERENCES Gym(gym_id);

alter table Trainer
add COLUMN gym_id INT;

alter table Trainer
add FOREIGN KEY (gym_id) REFERENCES Gym(gym_id);

create TABLE SportsMenTrainingTimeStatistics (
statistics_id INT PRIMARY KEY AUTO_INCREMENT,
date DATE NOT NULL,
time TIME NOT NULL,
sportsmanstats_id int NOT NULL,
FOREIGN KEY (sportsmanstats_id) REFERENCES Statistics(statistics_id)
);

alter table SportsMen
add FOREIGN KEY (trainer_pesel) REFERENCES trainer(trainer_pesel);

alter table Trainer
add COLUMN sportsman BIGINT;

alter table Trainer
add FOREIGN KEY (sportsman) REFERENCES SportsMen(sportsman_pesel);

create TABLE trainersatgym (
trainer_pesel BIGINT,
gym_id INT,
PRIMARY KEY(trainer_pesel, gym_id),
FOREIGN KEY (trainer_pesel) REFERENCES trainer(trainer_pesel) ON delete CASCADE,
FOREIGN KEY (gym_id) REFERENCES gym(gym_id) ON delete CASCADE);

DELIMITER //
create procedure getsportsmanstats (sportsmanStatsId int)
BEGIN
SELECT statistics_id, date, time FROM SportsMenTrainingTimeStatistics
WHERE sportsmanstats_id=sportsmanStatsId;
END //
DELIMITER ;

DELIMITER //
create procedure trainCardio (statsId int, cardiosportsmanID int)
BEGIN
UPDATE cardioTraining SET training_count=training_count+1 WHERE cardio_id = cardiosportsmanID;
INSERT INTO SportsMenTrainingTimeStatistics VALUES(NULL,curdate(),curtime(), statsId);
END //
DELIMITER ;

insert into gym values ('1','pierwsza', 'sezamkowa','rudy', 102);
insert into gym values ('2','druga', 'sezamkowa','kraków', 102);
insert into gym values ('4','Gym', 'ToDelete','kraków', 200);
insert into cardiotraining values (1,2);
insert into powertraining values (1,5);
insert into Statistics values (1,1,1);
insert into SportsMenTrainingTimeStatistics values (1,'2020-01-22','18:18:52',1);

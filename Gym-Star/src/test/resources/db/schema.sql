CREATE TABLE Gym (
gym_id INT PRIMARY KEY AUTO_INCREMENT,
gym_name VARCHAR(80) NOT NULL CHECK (DATALENGTH(gym_name) > 0),
street VARCHAR (80) NOT NULL CHECK (DATALENGTH(street) > ''),
city VARCHAR(30) NOT NULL,
building_number INT NOT NULL
);

DELIMITER $$
CREATE TRIGGER notempty BEFORE INSERT ON Gym
     FOR EACH ROW BEGIN
     IF NEW.gym_name = '' OR  NEW.street = '' OR NEW.city = ''
     THEN signal sqlstate '45000';
     END IF;
     END$$
 DELIMITER ;

INSERT INTO gym Values ('1','pierwsza', 'sezamkowa','rudy', 102);
INSERT INTO gym Values ('2','druga', 'sezamkowa','kraków', 102);
create TABLE Gym (
gym_id INT PRIMARY KEY AUTO_INCREMENT,
gym_name VARCHAR(80) NOT NULL,
street VARCHAR (80) NOT NULL,
city VARCHAR(30) NOT NULL,
building_number INT NOT NULL
);
insert into gym values (1,'druga', 'Pancerni','Rudy', 102);
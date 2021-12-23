use sql6457864;
CREATE TABLE `man_utd` (
  `ID` INT NOT NULL,
  `First_Name` VARCHAR(20) NOT NULL,
  `Last_Name` VARCHAR(20) NULL,
  `Age` INT NOT NULL,
  `Position` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`ID`));
  
INSERT INTO `man_utd`
(ID , First_Name , Last_Name , Age , Position)
values
(7,'Cristiano' , 'Ronaldo' , 36, 'Forward'),
(10,'Marcus' , 'Rashford' , 24, 'Forward'),
(1,'David','De Gea', 30,'Goalkeeper');

select * from man_utd;

INSERT INTO `man_utd`
(ID , First_Name , Last_Name , Age , Position)
values
(2,'Victor' , 'Lindelof' , 27, 'Defender'),
(3,'Eric' , 'Bailly' , 27, 'Defender'),
(4,'Phil','Jones', 29,'Defender'),
(5,'Harry','Maguire', 28,'Defender'),
(6,'Paul','Pogba', 28,'Midfielder'),
(8,'Juan','Mata', 33,'Midfielder'),
(9,'Anthony','Martial', 26,'Forward'),
(11,'Mason','Greenwood', 20,'Forward'),
(14,'Jesse','Lingard', 28,'Forward'),
(17,'Frederico','Santos', 28,'Midfielder'),
(18,'Bruno','Fernandes', 26,'Midfielder'),
(19,'Raphael','Varane', 28,'Defender'),
(20,'Diego','Dalot', 22,'Defender'),
(21,'Edinson','Cavani', 34,'Forward'),
(23,'Luke','Shaw', 28,'Defender'),
(25,'Jadon','Sancho', 21,'Forward'),
(27,'Alex','Telles', 28,'Defender'),
(39,'Scott','McTominay', 25,'Midfielder'),
(31,'Nemanja','Matic', 33,'Midfielder');

alter table man_utd
add pic blob;
describe man_utd;

alter table man_utd
add info_text longtext;
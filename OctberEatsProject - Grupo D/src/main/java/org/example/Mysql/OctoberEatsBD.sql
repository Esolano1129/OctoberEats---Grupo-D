CREATE database OctoberEatsDB;

CREATE TABLE Octobereatsdb.Users(
Id int auto_increment NOT NULL,
Usernames VARCHAR (30) NOT NULL,
Passwords VARCHAR (30) NOT NULL,

PRIMARY KEY (Id)
);

SELECT * FROM OctoberEatsDB.Users;

INSERT INTO Octobereatsdb.Users(Usernames,Passwords)
VALUES("Admin","admin");

INSERT INTO Octobereatsdb.Users(Usernames,Passwords)
VALUES("Edgardo","12345");

SELECT * FROM OctoberEatsDB.Users WHERE Users.Usernames = 'Admin' AND Users.Passwords = 'admin';

SELECT host, user, authentication_string FROM mysql.user WHERE user = 'root';

DROP USER 'root'@'localhost';
CREATE USER 'root'@'localhost' IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;




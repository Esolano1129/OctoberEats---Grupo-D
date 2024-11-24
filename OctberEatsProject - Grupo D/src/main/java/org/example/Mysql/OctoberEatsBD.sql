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


CREATE TABLE Octobereatsdb.Usersv2(
Id int auto_increment NOT NULL,
Usernames VARCHAR (30) NOT NULL,
Passwords VARCHAR (30) NOT NULL,
FirstName VARCHAR (255) NOT NULL,
LastName VARCHAR (255) NOT NULL,
Phone VARCHAR (100) NOT NULL,
Email VARCHAR (255) NOT NULL,
Age Int NOT NULL,
Address VARCHAR (255) NOT NULL,

PRIMARY KEY (Id)
);

INSERT INTO Octobereatsdb.Usersv2(Usernames,Passwords,FirstName,LastName,Phone,Email,Age,Address)
VALUES("Admin","admin","Admin","User","550033302","admin@admin.com",18,"Costa Rica");

INSERT INTO Octobereatsdb.Usersv2(Usernames,Passwords,FirstName,LastName,Phone,Email,Age,Address)
VALUES("esolano","12345","Edgardo","Solano","88888888","edgardo@admin.com",27,"Costa Rica");

SELECT * FROM OctoberEatsDB.Usersv2;

SELECT * FROM OctoberEatsDB.Usersv2 WHERE Usersv2.Usernames = 'Admin' AND Usersv2.Passwords = 'admin';

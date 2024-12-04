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


CREATE TABLE Octobereatsdb.Restaurants(
Id int auto_increment NOT NULL,
RestaurantName VARCHAR (100) NOT NULL,
Address VARCHAR (100) NOT NULL,
RestaurantSchedule VARCHAR (100) NOT NULL,
Rating VARCHAR (100) NOT NULL,
Category VARCHAR (50) NOT NULL,
Phone VARCHAR (255) NOT NULL,
Email VARCHAR (255) NOT NULL,

PRIMARY KEY (Id)
);

SELECT * FROM OctoberEatsDB.Restaurants;

INSERT INTO OctoberEatsDB.Restaurants(RestaurantName,Address,RestaurantSchedule,Rating,Category,Phone,Email)
VALUES("Test","Sanjose","8am - 5p.m","5 stars","FastFood","88888888","test@test.com");

SELECT Id,RestaurantName,Address,RestaurantSchedule,Rating,Category,Phone,Email FROM OctoberEatsDB.Restaurants;

CREATE TABLE Octobereatsdb.ProductItems(
Id int auto_increment NOT NULL,
ProductName VARCHAR (100) NOT NULL,
Quantity VARCHAR (100) NOT NULL,
Price VARCHAR (100) NOT NULL,
Descriptions VARCHAR (100) NOT NULL,
Discount VARCHAR (50) NOT NULL,


PRIMARY KEY (Id)
);

SELECT * FROM OctoberEatsDB.ProductItems;

INSERT INTO OctoberEatsDB.ProductItems(ProductName,Quantity,Price,Descriptions,Discount,Restaurant)
VALUES("Hamburguer","5","$30","Delicioso","5%","TacoBell");

ALTER TABLE OctoberEatsDB.ProductItems ADD COLUMN Restaurant varchar(255);

CREATE TABLE OctoberEatsDB.Orders (
    Id INT AUTO_INCREMENT NOT NULL,
    OrderNumber VARCHAR(50) NOT NULL,
    Date DATE NOT NULL,
    Time TIME NOT NULL,
    DeliveryTime TIME NOT NULL,
    User VARCHAR(100) NOT NULL,
    ProductItem TEXT NOT NULL,
    PRIMARY KEY (Id)
);


INSERT INTO OctoberEatsDB.Orders(OrderNumber, Date, Time, DeliveryTime, User, ProductItem)
VALUES('ORD123', '2024-12-03', '14:30:00', '15:00:00', 'Admin', 'Hamburger, Fries');


SELECT * FROM OctoberEatsDB.Orders;
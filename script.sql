CREATE USER 'admin'@'localhost' IDENTIFIED BY 'test';

CREATE DATABASE mddb;

use mddb;

CREATE TABLE user_account (
	id int NOT NULL AUTO_INCREMENT,
	first_Name varchar(255),
	last_Name varchar(255),
	username varchar(255),
	email varchar(255),
	password varchar(255),
	account_Non_Expired boolean default 1,
	account_Non_Locked boolean default 1,
	credentials_Non_Expired boolean default 1,
	enabled boolean default 1,
	PRIMARY KEY (id));
	
CREATE TABLE user_authority (
	id int NOT NULL AUTO_INCREMENT,
	authority varchar(255),
	PRIMARY KEY (id));

CREATE TABLE account_authority (
	id int NOT NULL AUTO_INCREMENT,
	user_account_id int,
	user_authority_id int,
	PRIMARY KEY (id));
	
ALTER TABLE account_authority ADD CONSTRAINT fk_user_account_join FOREIGN KEY (user_account_id) REFERENCES user_account (id);
ALTER TABLE account_authority ADD CONSTRAINT fk_user_authority_join FOREIGN KEY (user_authority_id) REFERENCES user_authority (id);

CREATE TABLE movie_director (
	id int NOT NULL AUTO_INCREMENT,
	name varchar(255),
	PRIMARY KEY (id));
	
CREATE TABLE movie (
	id int NOT NULL AUTO_INCREMENT,
	title varchar(255),
	movie_Director_id int,
	status ENUM('RENTED', 'AVAILABLE'),
	user_Account_id int,
	PRIMARY KEY (id));

ALTER TABLE movie ADD CONSTRAINT fk_movie_director_movie FOREIGN KEY (movie_Director_id) REFERENCES movie_Director (id);
ALTER TABLE movie ADD CONSTRAINT fk_user_account_movie FOREIGN KEY (user_account_id) REFERENCES user_account (id);

insert into user_account(first_Name, last_Name, username, email, password, account_Non_Expired, account_Non_Locked, credentials_Non_Expired, enabled) values ('Leonardo', 'Marques', 'leochassot3', 'leonardo.marques@test.com', '$2a$10$eLM/8ETTN8H.w7V/JfZfZe/nFgr9R7k3NetFErSrA5tpobvKNPyde', true, true, true, true);
insert into user_account(first_Name, last_Name, username, email, password, account_Non_Expired, account_Non_Locked, credentials_Non_Expired, enabled) values ('John', 'Doe', 'johndoe', 'john.doe@test.com', '$2a$10$eLM/8ETTN8H.w7V/JfZfZe/nFgr9R7k3NetFErSrA5tpobvKNPyde', true, true, true, true);
insert into user_account(first_Name, last_Name, username, email, password, account_Non_Expired, account_Non_Locked, credentials_Non_Expired, enabled) values ('Fulano', 'Silva', 'fulanosilva', 'fulano.silva@test.com', '$2a$10$eLM/8ETTN8H.w7V/JfZfZe/nFgr9R7k3NetFErSrA5tpobvKNPyde', true, true, true, true);
insert into user_authority(authority) values('admin');
insert into user_authority(authority) values('user');
insert into user_authority(authority) values('guest');
insert into account_authority(user_account_id, user_authority_id) values(1, 1);
insert into account_authority(user_account_id, user_authority_id) values(2, 2);
insert into account_authority(user_account_id, user_authority_id) values(3, 2);
insert into movie_director(name) values('George Lucas');
insert into movie_director(name) values('Peter Jackson');
insert into movie(title, movie_Director_id, status, user_Account_id) values('Star Wars I', 1, 'RENTED', 2);
insert into movie(title, movie_Director_id, status, user_Account_id) values('Star Wars I', 1, 'RENTED', 3);
insert into movie(title, movie_Director_id, status, user_Account_id) values('Star Wars I', 1, 'AVAILABLE', null);
insert into movie(title, movie_Director_id, status, user_Account_id) values('Star Wars I', 1, 'AVAILABLE', null);
insert into movie(title, movie_Director_id, status, user_Account_id) values('Star Wars I', 1, 'AVAILABLE', null);
insert into movie(title, movie_Director_id, status, user_Account_id) values('Star Wars II', 1, 'RENTED', 2);
insert into movie(title, movie_Director_id, status, user_Account_id) values('Star Wars II', 1, 'AVAILABLE', null);
insert into movie(title, movie_Director_id, status, user_Account_id) values('Lord of the Rings - Fellowship of the Ring', 2, 'RENTED', 1);
insert into movie(title, movie_Director_id, status, user_Account_id) values('Lord of the Rings - Fellowship of the Ring', 2, 'AVAILABLE', null);
insert into movie(title, movie_Director_id, status, user_Account_id) values('Lord of the Rings - Two Towers', 2, 'RENTED', 1);
insert into movie(title, movie_Director_id, status, user_Account_id) values('Lord of the Rings - Two Towers', 2, 'AVAILABLE', null);
insert into movie(title, movie_Director_id, status, user_Account_id) values('Lord of the Rings - Two Towers', 2, 'AVAILABLE', null);
insert into movie(title, movie_Director_id, status, user_Account_id) values('Lord of the Rings - Return of The King', 2, 'RENTED', 1);

GRANT ALL PRIVILEGES ON mddb.* TO 'admin'@'localhost';
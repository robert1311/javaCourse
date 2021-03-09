drop database if exists BullsAndCowsDBTest;

create database BullsAndCowsDBTest;

use BullsAndCowsDBTest;

create table Game (
	`gameId` int primary key auto_increment,
    `currentRound` int null,
    `answer` char(4) not null,
    `isFinished` boolean default false
    );
    
create table Round (
	`roundId` int primary key auto_increment,
    `gameId` int not null,
    `roundNumber` int not null,
    `guess` char(4) not null,
    `timeOfGuess` datetime not null,
    `result` char(7) null,
    foreign key (gameId) 
		references Game(gameId)
    );
    
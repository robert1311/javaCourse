DROP DATABASE IF EXISTS Hotel;

CREATE DATABASE IF NOT EXISTS Hotel;

USE Hotel;

CREATE TABLE `State` (
	StateAbbr CHAR(2) PRIMARY KEY,
	StateName VARCHAR(25) NOT NULL
	);
        
CREATE TABLE `Guest` (
	GuestId INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(25) NOT NULL,
    LastName VARCHAR(25) NOT NULL,
    Address VARCHAR(50) NOT NULL,
    City VARCHAR(45) NOT NULL,
    StateAbbr CHAR(2) NOT NULL,
    FOREIGN KEY fk_Guest_State (StateAbbr)
		REFERENCES State (StateAbbr),
    ZipCode CHAR(5) NOT NULL,
    Phone CHAR(15) NULL
);
    
CREATE TABLE `Reservation` (
	ReservationId INT PRIMARY KEY AUTO_INCREMENT,
    GuestId INT NOT NULL,
    Adults TINYINT NOT NULL,
    Children TINYINT NULL,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    Total DECIMAL(6,2) NOT NULL,
    foreign key fk_Reservation_Guest (GuestId)
    references Guest (GuestId)
);
    

CREATE TABLE `Type` (
	TypeId INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(10) NOT NULL,
    StandardOccupancy TINYINT NOT NULL,
    MaxOccupancy TINYINT NOT NULL,
    BasePrice Decimal(5,2),
    ExtraPersonCost DECIMAL(4,2)
);
    
CREATE TABLE `Room` (
	RoomNumber char(3) primary key,
    TypeId INT NOT NULL,
    isADA BOOLEAN DEFAULT FALSE
);
    
ALTER TABLE Room 
	ADD CONSTRAINT fk_Room_TypeId
    FOREIGN KEY (TypeId)
		REFERENCES `Type` (TypeId);
        
        
CREATE TABLE `Amenity` (
	AmenityId TINYINT PRIMARY KEY AUTO_INCREMENT,
    AmenityName VARCHAR(25) NOT NULL,
    AmenityExtraCost DECIMAL(5,2) NULL
    );
    
CREATE TABLE `RoomAmenity` (
	RoomNumber char(3) not null,
    AmenityId TINYINT NOT NULL,
    PRIMARY KEY pk_Room_Amenity (RoomNumber, AmenityId),
    FOREIGN KEY fk_RoomAmenity_Room (RoomNumber)
    REFERENCES Room (RoomNumber),
    FOREIGN KEY fk_RoomAmenity_Amenity (AmenityId)
    REFERENCES Amenity (AmenityId)
    );
    
CREATE TABLE `RoomReservation` (
	RoomNumber char(3) NOT NULL,
    ReservationId INT NOT NULL,
    PRIMARY KEY pk_Room_Reservation (RoomNumber, ReservationId),
    FOREIGN KEY fk_RoomReservation_Room (RoomNumber)
		REFERENCES Room (RoomNumber),
	FOREIGN KEY fk_RoomReservation_Reservation (ReservationId)
		REFERENCES Reservation (ReservationId)
	);
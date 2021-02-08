use Hotel;

insert into `State` (StateAbbr, StateName)
	values ('IA', 'Iowa'),
			('Ak', 'Arkansas'),
            ('TX', 'Texas'),
            ('NJ', 'New Jersey'),
            ('MI', 'Michigan'),
            ('CO', 'Colorado'),
            ('IL', 'Illinois'),
            ('RI', 'Rhode Island'),
            ('NY', 'New York'),
            ('VA', 'Virginia'),
            ('PA', 'Pennsylvania');
            
insert into `Guest` (FirstName, LastName, Address, City, StateAbbr, ZipCode, Phone)
	values ('Robert', 'Esquivel', '123 Fake St.', 'Dallas', 'TX', '12345', '(111) 123-4567'),
('Mack', 'Simmer',	'379 Old Shore Street',	'Council Bluffs', 'IA', '51501', '(291) 553-0508'),
('Bettyann', 'Seery', '750 Wintergreen Dr.', 'Wasilla',	'AK', '99654', '(478) 277-9632'),
('Duane', 'Cullison', '9662 Foxrun Lane', 'Harlingen', 'TX', '78552', '(308) 494-0198'),
('Karie', 'Yang', '9378 W. Augusta Ave.', 'West Deptford', 'NJ', '08096', '(214) 730-0298'),
('Aurore', 'Lipton', '762 Wild Rose Street', 'Saginaw',	'MI', '48601', '(377) 507-0974'),
('Zachery', 'Luechtefeld', '7 Poplar Dr.', 'Arvada', 'CO', '80003', '(814) 485-2615'),
('Jeremiah', 'Pendergrass',	'70 Oakwood St.', 'Zion', 'IL', '60099', '(279) 491-0960'),
('Walter', 'Holaway', '7556 Arrowhead St.', 'Cumberland', 'RI', '02864', '(446) 396-6785'),
('Wilfred', 'Vise',	'77 West Surrey Street', 'Oswego', 'NY', '13126', '(834) 727-1001'),
('Maritza', 'Tilton', '939 Linda Rd.', 'Burke', 'VA', '22015', '(446) 351-6860'),
('Joleen', 'Tison',	'87 Queen St.',	'Drexel Hill', 'PA',	'19026', '(231) 893-2755');

insert into `Type` (`Name`, StandardOccupancy, MaxOccupancy, BasePrice, ExtraPersonCost)
	values
    ('Single', 2, 2, 149.99, null),
    ('Double', 2, 4, 174.99, 10),
	('Suite', 3, 8, 399.99, 20);
    
insert into `Amenity` (AmenityName, AmenityExtraCost)
	values ('Refrigerator', null),
		('Microwave', null),
        ('Jacuzzi', 25.00),
        ('Oven', null);
        
insert into `Room` (RoomNumber, TypeId, isADA)
	values ('201', 2, 0),
		('202', 2, 1),
        ('203', 2, 0),
        ('204', 2, 1),
        ('205', 1, 0),
        ('206', 1, 1),
        ('207', 1, 0),
        ('208', 1, 1), 
        ('301', 2, 0),
        ('302', 2, 1),
        ('303', 2, 0),
        ('304', 2, 1),
        ('305', 1, 0),
        ('306', 1, 1),
        ('307', 1, 0),
        ('308', 1, 1), 
        ('401', 3, 1),
        ('402', 3, 1);
        
insert into `Reservation` (GuestId, Adults, Children, StartDate, EndDate, Total)
		values (2, 1, 0, '2023-02-02', '2023-02-04', 299.98),
        (3, 2, 1, '2023-02-05', '2023-02-10', 999.95),
        (4, 2, 0, '2023-02-22', '2023-02-24', 349.98),
        (5, 2, 2, '2023-03-06', '2023-03-07', 199.99),
        (1, 1, 1, '2023-03-17', '2023-03-20', 524.97),
        (6, 3, 0, '2023-03-18', '2023-03-23', 924.95),
        (7, 2, 2, '2023-03-29', '2023-03-31', 349.98),
        (8, 2, 0, '2023-03-31', '2023-04-05', 874.95),
        (9, 1, 0, '2023-04-09', '2023-04-13', 799.96),
        (10, 1, 1, '2023-04-23', '2023-04-24', 174.99),
        (11, 2, 4, '2023-05-30', '2023-06-02',1199.97),
        (12, 2, 0, '2023-06-10', '2023-06-14', 599.96),
        (12, 1, 0, '2023-06-10', '2023-06-14', 599.96),
        (6, 3, 0, '2023-06-17', '2023-06-18', 184.99),
        (1, 2, 0, '2023-06-28', '2023-07-02', 699.96),
        (9, 3, 1, '2023-07-13', '2023-07-14', 184.99),
        (10, 4, 2, '2023-07-18', '2023-07-21', 1259.97),
        (3, 2, 1, '2023-07-28', '2023-07-29', 199.99),
        (3, 1, 0, '2023-08-30', '2023-09-01', 349.98),
        (2, 2, 0, '2023-09-16', '2023-09-17', 149.99),
        (5, 2, 2, '2023-09-13', '2023-09-15', 399.98),
        (4, 2, 2, '2023-11-22', '2023-11-25', 1999.97),
        (2, 2, 0, '2023-11-22', '2023-11-25', 449.97),
        (2, 2, 2, '2023-11-22', '2023-11-25', 599.97),
        (11, 2, 0, '2023-12-24', '2023-12-28', 699.96);
        
insert into RoomAmenity (RoomNumber, AmenityId)
	values (201, 2),
		(201, 3),
        (202, 1),
        (203, 2),
        (203, 3),
        (204, 1), 
        (205, 1),
        (205, 2),
        (205, 3),
        (206, 1),
        (206, 2),
        (207, 1),
        (207, 2),
        (207, 3),
        (208, 1),
        (208, 2),
        (301, 2),
		(301, 3),
        (302, 1),
        (303, 2),
        (303, 3),
        (304, 1), 
        (305, 1),
        (305, 2),
        (305, 3),
        (306, 1),
        (306, 2),
        (307, 1),
        (307, 2),
        (307, 3),
        (308, 1),
        (308, 2),
        (401, 1),
        (401, 2),
        (401, 3), 
        (401, 4),
        (402, 1),
        (402, 2),
        (402, 3), 
        (402, 4);
        
insert into RoomReservation (RoomNumber, ReservationId)
	values (308, 1),
		(203, 2),
        (305, 3),
        (201, 4),
        (307, 5),
        (302, 6),
        (202, 7),
        (304, 8),
        (301, 9),
        (207, 10),
        (401, 11),
        (206, 12),
        (208, 13),
        (304, 14),
        (205, 15),
        (204, 16),
        (401, 17),
        (303, 18),
        (305, 19),
        (208, 20),
        (203, 21),
        (401, 22),
        (206, 23),
        (301, 24),
        (302, 25);
        
delete from RoomReservation
where ReservationId = 8;

delete from Reservation 
where ReservationId = 8;

delete from Guest
where GuestId = 8;
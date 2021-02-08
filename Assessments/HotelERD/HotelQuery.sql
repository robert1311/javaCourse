use Hotel;

select 
    r.ReservationId,
    concat (g.FirstName, ' ', g.LastName) `Name`,
    rr.RoomNumber,
    r.StartDate,
    r.EndDate
from Guest g
inner join Reservation r on g.GuestId = r.GuestId
inner join RoomReservation rr on r.ReservationId = rr.ReservationId
 where r.EndDate between '2023-07-01' and '2023-07-31';
 
 select 
	 r.ReservationId,
    concat (g.FirstName, ' ', g.LastName) `Name`,
    rr.RoomNumber,
    r.StartDate,
    r.EndDate
from Guest g
inner join Reservation r on g.GuestId = r.GuestId
inner join RoomReservation rr on r.ReservationId = rr.ReservationId
inner join Room rm on rr.RoomNumber = rm.RoomNumber
inner join RoomAmenity ra on rm.RoomNumber = ra.RoomNumber
where ra.AmenityId = 3;

select 
	concat(FirstName, ' ' , LastName) `Name`,
    r.ReservationId,
    rr.RoomNumber,
    r.StartDate,
    sum(r.Adults + r.Children) NumOfPeople
from Guest g
inner join Reservation r on g.GuestId = r.GuestId
inner join RoomReservation rr on r.ReservationId = rr.ReservationId
where g.LastName = 'Simmer'
group by r.ReservationId;

select 
	rm.RoomNumber,
    ifnull(r.ReservationId, '[none]') ReservationId,
    ifnull(r.Total, '[N/A]') Total
from  `Type` t 
inner join Room rm on t.TypeId = rm.TypeId
left outer join RoomReservation rr on rm.RoomNumber = rr.RoomNumber
left outer join Reservation r on rr.ReservationId = r.ReservationId
group by ReservationId;

select 
r.ReservationId,
	rm.RoomNumber,
    sum(r.Adults + r.Children) Guests,
    r.StartDate,
    r.EndDate
from Room rm
inner join RoomReservation rr on rm.RoomNumber = rr.RoomNumber
inner join Reservation r on rr.ReservationId = r.ReservationId
inner join Guest g on r.GuestId = g.GuestId
where r.StartDate between '2023-04-01' and '2023-04-30'
or r.EndDate between '2023-04-01' and '2023-04-30'
group by rm.RoomNumber
having Guests > 2;

select 
r.ReservationId,
	rm.RoomNumber,
    t.MaxOccupancy,
    r.StartDate,
    r.EndDate
from `Type` t 
inner join Room rm on t.TypeId = rm.TypeId
inner join RoomReservation rr on rm.RoomNumber = rr.RoomNumber
inner join Reservation r on rr.ReservationId = r.ReservationId
inner join Guest g on r.GuestId = g.GuestId
where r.StartDate between '2023-04-01' and '2023-04-30'
or r.EndDate between '2023-04-01' and '2023-04-30'
group by rm.RoomNumber
having t.MaxOccupancy > 2;

select
	g.FirstName,
    g.LastName,
    count(r.ReservationId) Reservations
from Guest g
inner join Reservation r on g.GuestId = r.GuestId
group by g.LastName
order by Reservations desc, g.LastName;

select 
	concat(g.FirstName, ' ' , g.LastName) `Name`,
    g.Address,
    s.StateName,
    g.Phone
from State s 
inner join Guest g on s.StateAbbr = g.StateAbbr
inner join Reservation r on g.GuestId = r.GuestId
where g.Phone = '(111) 123-4567'
limit 0,1;
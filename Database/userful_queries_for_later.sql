USE moviereservation;
-- Find all available seats
-- select m.title, th.name, st.time, s.row_letter, s.seat_num 
-- from seat s 
-- inner join showtime st on s.showtime_id = st.showtime_id and s.is_booked=false 
-- inner join theatre th on th.theatre_id = st.theatre_id 
-- inner join movie m on m.movie_id=th.movie_id;

-- Get all tickets for a transaction
-- select t.ticket_id, t.seat_id, t.transaction_id 
-- from ticket t 
-- inner join seat s on s.seat_id = t.seat_id 
-- inner join showtime st on st.showtime_id = s.showtime_id;

-- Get all Information to print ticket
-- select *
-- from seat s 
-- inner join showtime st on s.showtime_id = st.showtime_id and s.seat_id=7
-- inner join theatre th on th.theatre_id = st.theatre_id 
-- inner join movie m on m.movie_id = th.movie_id;
-- where s.row_letter = 'A' and s.seat_num = "1"

-- Get all seats for a transaction
-- select * 
-- from seat s 
-- inner join ticket t on s.seat_id = t.seat_id
-- inner join transaction tr on t.transaction_id = tr.transaction_id and tr.transaction_id = 1

select *
from seat s
inner join showtime st on s.showtime_id = st.showtime_id and s.seat_id = 8



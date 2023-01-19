USE moviereservation;

INSERT INTO MOVIE(title, release_date, announce_date) 
VALUES
("Batman", "2022-03-04", "2022-01-04"),
("Fast and Furious", "2021-08-22", "2021-06-22"),
("Jumanji", "2020-06-17", "2020-04-17"),
("Avatar", "2012-11-28", "2012-09-28");

INSERT INTO THEATRE(movie_id, name) 
VALUES
(1, "AVX"),
(1, "3D"),
(1, "Regular"),
(1, "Imax"),
(2, "AVX"),
(2, "3D"),
(2, "Regular"),
(2, "Imax"),
(3, "AVX"),
(3, "3D"),
(3, "Regular"),
(3, "Imax"),
(4, "AVX"),
(4, "3D"),
(4, "Regular"),
(4, "Imax");

INSERT INTO SHOWTIME(theatre_id, time)
VALUES
(1, '2022-11-26 09:30:00'),
(1, '2022-11-26 11:30:00'),
(1, '2022-11-26 13:30:00'),
(1, '2022-11-26 15:30:00'),
(1, '2022-11-26 17:30:00');


INSERT INTO SEAT (showtime_id, row_letter, seat_num, is_booked, price)
VALUES
(1, "A", 1, true, 12.50),
(1, "A", 2, true, 12.50),
(1, "A", 3, true, 12.50),
(1, "A", 4, true, 12.50),
(1, "A", 5, true, 12.50),
(1, "A", 6, true, 12.50),
(1, "A", 7, true, 12.50),
(1, "A", 8, false, 12.50),
(1, "A", 9, false, 12.50),
(1, "A", 10, false, 12.50),
(1, "B", 1, false, 12.50),
(1, "B", 2, false, 12.50),
(1, "B", 3, false, 12.50),
(1, "B", 4, false, 12.50),
(1, "B", 5, false, 12.50),
(1, "B", 6, false, 12.50),
(1, "B", 7, false, 12.50),
(1, "B", 8, false, 12.50),
(1, "B", 9, false, 12.50),
(1, "B", 10, false, 12.50),
(1, "C", 1, false, 12.50),
(1, "C", 2, false, 12.50),
(1, "C", 3, false, 12.50),
(1, "C", 4, false, 12.50),
(1, "C", 5, false, 12.50),
(1, "C", 6, false, 12.50),
(1, "C", 7, false, 12.50),
(1, "c", 8, false, 12.50),
(1, "C", 9, false, 12.50),
(1, "C", 10, false, 12.50),
(1, "D", 1, false, 12.50),
(1, "D", 2, false, 12.50),
(1, "D", 3, false, 12.50),
(1, "D", 4, false, 12.50),
(1, "D", 5, false, 12.50),
(1, "D", 6, false, 12.50),
(1, "D", 7, false, 12.50),
(1, "D", 8, false, 12.50),
(1, "D", 9, false, 12.50),
(1, "D", 10, false, 12.50);

INSERT INTO PAYMENT (card_holder_name, card_number, expiry_date, cvc, email_address, address, city, province, postal_code)
VALUES
("Mark Phillipos", "1234567891011120", "2023-11-01", 123, "markphillipos3@gmail.com", "337 Summerside Cove SW", "Edmonton", "AB", "T6X 1B3"),
("Daniel Phillipos", "1234567891234567", "2023-12-01", 321, "markphillipos3@gmail.com", "337 Summerside Cove SW", "Edmonton", "AB", "T6X 1B3"),
("Maria Phillipos", "9876543219876543", "2023-10-01",112, "markphillipos3@gmail.com", "337 Summerside Cove SW", "Edmonton", "AB", "T6X 1B3");

INSERT INTO REGISTEREDUSER(payment_info_payment_id, username, password, subscription_payment_date, subscription_end_date, subscription_payed)
VALUES
(1, "markphillipos", "password", "2022-11-26", "2023-11-26", true),
(2, "danielphillipos", "password", "2022-11-27", "2023-11-27", true),
(3, "mariaphillipos", "password", "2022-11-28", "2023-11-28", true);

INSERT INTO TICKET(seat_id, ticket_id, registereduser_id) 
VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 4, 2),
(5, 5, 2),
(6, 6, 2),
(7, 7, 2);











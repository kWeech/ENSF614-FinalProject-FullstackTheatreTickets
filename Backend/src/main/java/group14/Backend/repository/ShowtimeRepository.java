package group14.Backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import group14.Backend.model.Showtime;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
    Optional<Showtime> findShowtimeById(Long showtimeID);

    @Query(value = """
            select st.showtime_id, st.time
            from Seat s
            inner join Showtime st on s.showtime_id = st.showtime_id and s.seat_id=:seatId""", nativeQuery = true)
    Showtime findShowtimeBySeatId(Long seatId);

    @Query(value = """
            select st.showtime_id, st.time
            from Ticket t
            inner join Seat s on s.seat_id = t.seat_id and t.ticket_id = :ticketId
            inner join Showtime st on st.showtime_id = s.showtime_id""", nativeQuery = true)
    Showtime findShowtimeByTicketId(Long ticketId);
}

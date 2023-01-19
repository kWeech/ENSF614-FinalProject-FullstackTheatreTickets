package group14.Backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import group14.Backend.model.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    @Query(value = """
            select s.seat_id, s.is_booked, s.price, s.row_letter, s.seat_num
            from Ticket t
            inner join Seat s on s.seat_id = t.seat_id and t.ticket_id = :ticketId""", nativeQuery = true)
    Seat findSeatByTicketId(Long ticketId);

    Optional<Seat> findSeatById(Long seatId);
}

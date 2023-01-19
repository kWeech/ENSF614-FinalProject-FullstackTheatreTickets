package group14.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import group14.Backend.model.Ticket;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Ticket findTicketBySeatId(Long seatId);

    @Query(value = """
            select t.ticket_id, s.seat_id
            from ticket t
            inner join seat s on t.seat_id = s.seat_id
            inner join showtime st on s.showtime_id = st.showtime_id
            inner join theatre th on st.theatre_id = th.theatre_id
            inner join movie m on th.movie_id = m.movie_id and m.title = :movieTitle """, nativeQuery = true)
    List<Ticket> findAllTicketsForMovie(String movieTitle);




}

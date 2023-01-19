package group14.Backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import group14.Backend.model.Theatre;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long> {
    Optional<Theatre> findTheatreById(Long id);

    @Query(value = """
            select th.theatre_id, th.name
            from Seat s
            inner join Showtime st on s.showtime_id = st.showtime_id and s.seat_id=:seatId
            inner join Theatre th on th.theatre_id = st.theatre_id""", nativeQuery = true)
    Theatre findTheatreBySeatId(Long seatId);
}

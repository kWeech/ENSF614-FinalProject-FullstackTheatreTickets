package group14.Backend.repository;

import group14.Backend.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findMovieByTitle(String movieTitle);

    @Query(value = """
            select m.movie_id, m.title, m.pre_release_date, m.release_date
            from Seat s
            inner join Showtime st on s.showtime_id = st.showtime_id and s.seat_id=:seatId
            inner join Theatre th on th.theatre_id = st.theatre_id
            inner join movie m on th.movie_id = m.movie_id""", nativeQuery = true)
    Movie findMovieBySeatId(Long seatId);

    @Query(value = """
            select m.movie_id, m.title, m.pre_release_date, m.release_date
            from Movie m
            where m.pre_release_date > :currentDate""", nativeQuery = true)
    List<Movie> findAllNotPreReleasedMovies(LocalDate currentDate);

    @Query(value = """
            select m.movie_id, m.title, m.pre_release_date, m.release_date
            from Movie m
            where :currentDate > m.pre_release_date and :currentDate < m.release_date""", nativeQuery = true)
    List<Movie> findAllAvailableForPreOrderMovies(LocalDate currentDate);

    @Query(value = """
            select m.movie_id, m.title, m.pre_release_date, m.release_date
            from Movie m
            where m.release_date < :currentDate""", nativeQuery = true)
    List<Movie> findAllPubliclyAvailableMovies(LocalDate currentDate);
}

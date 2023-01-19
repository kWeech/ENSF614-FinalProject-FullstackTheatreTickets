package group14.Backend.service;

import java.util.List;
import java.util.Optional;

import group14.Backend.model.Ticket;
import group14.Backend.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group14.Backend.model.Movie;
import group14.Backend.repository.MovieRepository;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final TicketRepository ticketRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, TicketRepository ticketRepository){
        this.movieRepository = movieRepository;
        this.ticketRepository = ticketRepository;
    }

    
    /** 
     * Talks to the repository and queries for all movies
     * @return List<Movie>
     */
    public List<Movie> getAllMovies() {
        List<Movie> movies = this.movieRepository.findAll();
        return movies;
    }

    
    /** 
     * Talks the to repository to insert a new movie into the database
     * @param movie
     */
    public void addNewMovie(Movie movie) {
        Optional<Movie> movieByName = movieRepository.findMovieByTitle(movie.getTitle());
        if (movieByName.isPresent()) {
            throw new IllegalStateException("Movie already exists!");
        }
        movieRepository.save(movie);
    }

    
    /** 
     * Talks to the repo to delete a movie from the database
     * @param movie
     */
    public void deleteMovie(Movie movie){
        Optional<Movie> movieByTitle = movieRepository.findMovieByTitle(movie.getTitle());
        List<Ticket> ticketsForMovie = this.ticketRepository.findAllTicketsForMovie(movie.getTitle());
        if (!movieByTitle.isPresent()) {
            throw new IllegalStateException("Movie doesn't exist");
        }
        if(!ticketsForMovie.isEmpty()){
            throw new IllegalStateException("Cannot delete movie that has tickets registered");
        }
        movieRepository.delete(movieByTitle.get());
    }


}

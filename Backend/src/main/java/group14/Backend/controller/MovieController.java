package group14.Backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import group14.Backend.model.Movie;
import group14.Backend.service.MovieService;

@RestController
@RequestMapping("/movie")
@CrossOrigin
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * GET's a list of all movies
     * @return List<Movie>
     */
    @GetMapping
    public List<Movie> getMovies() {
        return movieService.getAllMovies();
    }

    /**
     * adds a movie to the database via POST. body is the movie
     * @param movie
     */
    @PostMapping
    public void addMovie(@RequestBody Movie movie) {
        movieService.addNewMovie(movie);
    }

    /**
     * deletes movie via DELETE. body is the movie
     * @param movie
     */
    @DeleteMapping
    public void deleteMovie(@RequestBody Movie movie) {
        movieService.deleteMovie(movie);
    }
}

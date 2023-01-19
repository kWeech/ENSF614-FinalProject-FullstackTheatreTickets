package group14.Backend.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id", nullable = false, updatable = false)
    private Long id;
    private String title;
    private LocalDate releaseDate;
    private LocalDate preReleaseDate;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "movie_id")
    private Set<Theatre> theatres;

    public Movie() {
    }

    public Movie(String title, LocalDate releaseDate, LocalDate preReleaseDate, Set<Theatre> theatres) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.preReleaseDate = preReleaseDate;
        this.theatres = theatres;
    }

    public Movie(Long id, String title, LocalDate releaseDate, LocalDate preReleaseDate, Set<Theatre> theatres) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.preReleaseDate = preReleaseDate;
        this.theatres = theatres;
    }

    
    /** 
     * @return Long
     */
    public Long getId() {
        return id;
    }

    
    /** 
     * @return String
     */
    public String getTitle() {
        return title;
    }

    
    /** 
     * @return LocalDate
     */
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    
    /** 
     * @return LocalDate
     */
    public LocalDate getPreReleaseDate() {
        return preReleaseDate;
    }

    
    /** 
     * @return Set<Theatre>
     */
    public Set<Theatre> getTheatres() {
        return theatres;
    }

}

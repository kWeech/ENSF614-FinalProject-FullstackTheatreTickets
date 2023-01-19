package group14.Backend.model;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "showtime")
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "showtime_id", nullable = false, updatable = false)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "showtime_id")
    private Set<Seat> seats;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime time;

    public Showtime(){
    }

    public Showtime(Set<Seat> seats, LocalDateTime time){
        this.seats = seats;
        this.time = time;
    }

    public Showtime(Long id, Set<Seat> seats, LocalDateTime time){
        this.id = id;
        this.seats = seats;
        this.time = time;
    }

    
    /** 
     * @return Long
     */
    public Long getId() {
        return id;
    }

    
    /** 
     * @return Set<Seat>
     */
    public Set<Seat> getSeats() {
        return seats;
    }

    
    /** 
     * @param seats
     */
    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    
    /** 
     * @return LocalDateTime
     */
    public LocalDateTime getTime() {
        return time;
    }

    
    /** 
     * @param time
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
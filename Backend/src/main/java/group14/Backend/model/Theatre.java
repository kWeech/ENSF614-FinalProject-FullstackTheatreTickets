package group14.Backend.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "theatre")
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theatre_id", nullable = false, updatable = false)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "theatre_id")
    private Set<Showtime> showtimes;

    public Theatre(){
    }

    public Theatre(String name, Set<Showtime> showtimes){
        this.name = name;
        this.showtimes = showtimes;
    }

    public Theatre(Long id, String name, Set<Showtime> showtimes){
        this.id = id;
        this.name = name;
        this.showtimes = showtimes;
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
    public String getName() {
        return name;
    }

    
    /** 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    
    /** 
     * @return Set<Showtime>
     */
    public Set<Showtime> getShowtimes() {
        return showtimes;
    }

    
    /** 
     * @param showtimes
     */
    public void setShowtimes(Set<Showtime> showtimes) {
        this.showtimes = showtimes;
    }
}

package group14.Backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id", nullable = false, updatable = false)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "seat_id")
    private Seat seat;

    public Ticket(){
    }

    public Ticket(Seat seat) {
        this.seat = seat;
    }

    public Ticket(Long id, Seat seat) {
        this.seat = seat;
    }

    
    /** 
     * @return Long
     */
    public Long getId() {
        return id;
    }


    
    /** 
     * @return Seat
     */
    public Seat getSeat() {
        return seat;
    }

    
    /** 
     * @param seat
     */
    public void setSeat(Seat seat) {
        this.seat = seat;
    }

}

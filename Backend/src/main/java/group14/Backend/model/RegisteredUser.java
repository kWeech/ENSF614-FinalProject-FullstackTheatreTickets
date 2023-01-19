package group14.Backend.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "registereduser",
        uniqueConstraints = { @UniqueConstraint(name = "username_unique", columnNames = "username")}
        )
public class RegisteredUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registereduser_id", nullable = false, updatable = false)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @OneToOne
    private Payment paymentInfo;
    private LocalDate subscriptionPaymentDate;
    private LocalDate subscriptionEndDate;
    private boolean subscriptionPayed;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "registereduser_id")
    private Set<Ticket> tickets;

    public RegisteredUser(){
    }

    public RegisteredUser(String username, String password, Payment paymentInfo, LocalDate subscriptionPaymentDate, LocalDate subscriptionEndDate, boolean subscriptionPayed, Set<Ticket> tickets) {
        this.username = username;
        this.password = password;
        this.paymentInfo = paymentInfo;
        this.subscriptionPaymentDate = subscriptionPaymentDate;
        this.subscriptionEndDate = subscriptionEndDate;
        this.subscriptionPayed = subscriptionPayed;
        this.tickets = tickets;
    }

    public RegisteredUser(Long id, String username, String password, Payment paymentInfo, LocalDate subscriptionPaymentDate, LocalDate subscriptionEndDate, boolean subscriptionPayed, Set<Ticket> tickets) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.paymentInfo = paymentInfo;
        this.subscriptionPaymentDate = subscriptionPaymentDate;
        this.subscriptionEndDate = subscriptionEndDate;
        this.subscriptionPayed = subscriptionPayed;
        this.tickets = tickets;
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
    public String getUsername() {
        return username;
    }

    
    /** 
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    
    /** 
     * @return String
     */
    public String getPassword() {
        return password;
    }

    
    /** 
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    
    /** 
     * @return Payment
     */
    public Payment getPaymentInfo() {
        return paymentInfo;
    }

    
    /** 
     * @param paymentInfo
     */
    public void setPaymentInfo(Payment paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    
    /** 
     * @return LocalDate
     */
    public LocalDate getSubscriptionPaymentDate() {
        return subscriptionPaymentDate;
    }

    
    /** 
     * @param subscriptionPaymentDate
     */
    public void setSubscriptionPaymentDate(LocalDate subscriptionPaymentDate) {
        this.subscriptionPaymentDate = subscriptionPaymentDate;
    }

    
    /** 
     * @return LocalDate
     */
    public LocalDate getSubscriptionEndDate() {
        return subscriptionEndDate;
    }

    
    /** 
     * @param subscriptionEndDate
     */
    public void setSubscriptionEndDate(LocalDate subscriptionEndDate) {
        this.subscriptionEndDate = subscriptionEndDate;
    }

    
    /** 
     * @return boolean
     */
    public boolean isSubscriptionPayed() {
        return subscriptionPayed;
    }

    
    /** 
     * @param subscriptionPayed
     */
    public void setSubscriptionPayed(boolean subscriptionPayed) {
        this.subscriptionPayed = subscriptionPayed;
    }

    
    /** 
     * @return Set<Ticket>
     */
    public Set<Ticket> getTickets() {
        return tickets;
    }

    
    /** 
     * @param tickets
     */
    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    
    /** 
     * @param ticket
     */
    public void addTicket(Ticket ticket){
        tickets.add(ticket);
    }
}

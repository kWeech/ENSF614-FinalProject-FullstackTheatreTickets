package group14.Backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id", nullable = false, updatable = false)
    private Long id;
    private char rowLetter;
    private int seatNum;
    private boolean isBooked;

    private double price;


    public Seat() {
    }

    public Seat(char rowLetter, int seatNum, boolean isBooked){
        this.rowLetter = rowLetter;
        this.seatNum = seatNum;
        this.isBooked = isBooked;
    }

    public Seat(Long id, char rowLetter, int seatNum, boolean isBooked){
        this.id = id;
        this.rowLetter = rowLetter;
        this.seatNum = seatNum;
        this.isBooked = isBooked;
    }

    
    /** 
     * @return Long
     */
    public Long getId() {
        return id;
    }

    
    /** 
     * @return char
     */
    public char getRowLetter() {
        return rowLetter;
    }

    
    /** 
     * @param rowLetter
     */
    public void setRowLetter(char rowLetter) {
        this.rowLetter = rowLetter;
    }

    
    /** 
     * @return int
     */
    public int getSeatNum() {
        return seatNum;
    }

    
    /** 
     * @param seatNum
     */
    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

    
    /** 
     * @return boolean
     */
    public boolean isBooked() {
        return isBooked;
    }

    
    /** 
     * @param booked
     */
    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    
    /** 
     * @return double
     */
    public double getPrice() {
        return price;
    }

    
    /** 
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
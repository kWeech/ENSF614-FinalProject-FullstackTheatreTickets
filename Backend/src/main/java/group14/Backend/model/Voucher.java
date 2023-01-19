package group14.Backend.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "voucher")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voucher_id", nullable = false, updatable = false)
    private Long id;
    private LocalDate expiryDate;
    private double voucherAmount;

    public Voucher(){
    }

    public Voucher( LocalDate expiryDate, double voucherAmount) {
        this.expiryDate = expiryDate;
        this.voucherAmount = voucherAmount;
    }

    public Voucher(Long id, LocalDate expiryDate, double voucherAmount) {
        this.id = id;
        this.expiryDate = expiryDate;
        this.voucherAmount = voucherAmount;
    }

    
    /** 
     * @return Long
     */
    public Long getId() {
        return id;
    }

    
    /** 
     * @return LocalDate
     */
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    
    /** 
     * @param expiryDate
     */
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    
    /** 
     * @return double
     */
    public double getVoucherAmount() {
        return voucherAmount;
    }

    
    /** 
     * @param voucherAmount
     */
    public void setVoucherAmount(double voucherAmount) {
        this.voucherAmount = voucherAmount;
    }
}

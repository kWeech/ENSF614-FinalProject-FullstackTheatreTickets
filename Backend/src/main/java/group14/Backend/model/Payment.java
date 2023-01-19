package group14.Backend.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "payment",
        uniqueConstraints = { @UniqueConstraint(name = "card_number_unique", columnNames = "cardNumber")}
)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false, updatable = false)
    private Long id;
    private String cardHolderName;
    private String cardNumber;
    private LocalDate expiryDate;
    private int cvc;
    private String emailAddress;
    private String address;
    private String city;
    private String province;
    private String postalCode;

    public Payment() {
    }

    public Payment(String cardHolderName, String cardNumber, LocalDate expiryDate, int cvc, String emailAddress, String address, String city, String province, String postalCode) {
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvc = cvc;
        this.emailAddress = emailAddress;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }

    public Payment(Long id, String cardHolderName, String cardNumber, LocalDate expiryDate, int cvc, String emailAddress, String address, String city, String province, String postalCode) {
        this.id = id;
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvc = cvc;
        this.emailAddress = emailAddress;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
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
    public String getCardHolderName() {
        return cardHolderName;
    }

    
    /** 
     * @param cardHolderName
     */
    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    
    /** 
     * @return String
     */
    public String getCardNumber() {
        return cardNumber;
    }

    
    /** 
     * @param cardNumber
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
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
     * @return int
     */
    public int getCvc() {
        return cvc;
    }

    
    /** 
     * @param cvc
     */
    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

    
    /** 
     * @return String
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    
    /** 
     * @param emailAddress
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    
    /** 
     * @return String
     */
    public String getAddress() {
        return address;
    }

    
    /** 
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    
    /** 
     * @return String
     */
    public String getCity() {
        return city;
    }

    
    /** 
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    
    /** 
     * @return String
     */
    public String getProvince() {
        return province;
    }

    
    /** 
     * @param province
     */
    public void setProvince(String province) {
        this.province = province;
    }

    
    /** 
     * @return String
     */
    public String getPostalCode() {
        return postalCode;
    }

    
    /** 
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}

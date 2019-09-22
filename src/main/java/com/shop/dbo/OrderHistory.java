package com.shop.dbo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class OrderHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "postal_code")
    private Long postalCode;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "order_time")
    private Timestamp orderTime;

    @Column(name = "price")
    private Double price;

    @Column(name = "products")
    private String products;

    @Column(name = "payment_method")
    private String paymentMethod;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public OrderHistory() {
    }

    public String getFirstName() {
        return firstName;
    }

    public OrderHistory(String products, Double price, String paymentMethod, Date orderTime) {
        this.products = products;
        this.price = price;
        this.paymentMethod = paymentMethod;
        this.orderTime = Timestamp.valueOf(orderTime.toString());
    }

    public OrderHistory(User user, String products, Double price, String paymentMethod, Date orderTime) {
        this.user = user;
        this.products = products;
        this.price = price;
        this.paymentMethod = paymentMethod;
        this.orderTime = Timestamp.valueOf(orderTime.toString());
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Long postalCode) {
        this.postalCode = postalCode;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}

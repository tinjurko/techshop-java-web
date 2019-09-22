package com.controller;

import com.repository.OrderHistoryRepository;
import com.repository.UserRepository;
import com.shop.dbo.OrderHistory;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Named
@RequestScoped
public class ConfirmOrder {

    private String firstName;
    private String lastName;
    private String address;
    private Long postalCode;
    private Long phoneNumber;
    private String paymentMethod;

    @Inject
    private StatelessSessionContext statelessSessionContext;

    @Inject
    ExternalContext externalContext;

    @Inject
    UserRepository userRepository;

    @Inject
    CartItemsSessionBean cartItemsSessionBean;

    @Inject
    OrderHistoryRepository orderHistoryRepository;

    public ConfirmOrder() {}


    public void createAndSaveOrder() throws IOException {

        OrderHistory orderHistory = new OrderHistory();

        orderHistory.setFirstName(getFirstName());
        orderHistory.setLastName(getLastName());
        orderHistory.setAddress(getAddress());
        orderHistory.setPostalCode(getPostalCode());
        orderHistory.setPhoneNumber(getPhoneNumber());
        orderHistory.setPaymentMethod(getPaymentMethod());

        orderHistory.setOrderTime(Timestamp.valueOf(LocalDateTime.now()));
        orderHistory.setUser(userRepository.find(statelessSessionContext.getCurrentUsername()));
        orderHistory.setPrice(cartItemsSessionBean.getTotal());

        orderHistory.setProducts(cartItemsSessionBean.getProducts());

        orderHistoryRepository.saveOrder(orderHistory);

        cartItemsSessionBean.clearCart();

        externalContext.redirect(externalContext.getRequestContextPath() + "/order/history.xhtml");;
    }

    public String getFirstName() {
        return firstName;
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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}

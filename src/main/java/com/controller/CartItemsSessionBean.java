package com.controller;

import com.CartItem;
import com.repository.ProductRepository;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class CartItemsSessionBean implements Serializable {

    private List<CartItem> cartItemList;
    private Double total = 0.00;

    DecimalFormat df2 = new DecimalFormat("#.00");

    @Inject
    ProductRepository productRepository;

    public CartItemsSessionBean() {
        this.cartItemList = new ArrayList<CartItem>();
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTotal() {
        return total;
    }

    public void clearCart() {
        this.cartItemList.clear();
        this.total = 0.00;
    }

    public void addItem(Long id, Integer quantity) throws IOException {

        CartItem cartItem = new CartItem();
        cartItem.setQuantity(quantity);
        cartItem.setProduct(productRepository.find(id));

        //If product exists in cart, just add quantity
        for(CartItem cartItem1 : this.cartItemList) {
            if(cartItem1.getProduct().getProductName().equals(cartItem.getProduct().getProductName())) {
                cartItem1.setQuantity(cartItem1.getQuantity() + cartItem.getQuantity());
                FacesContext facesContext = FacesContext.getCurrentInstance();
                FacesMessage facesMessage = new FacesMessage("Added to cart");
                facesContext.addMessage(null, facesMessage);
                countTotal();
                return;
            }
        }

        cartItemList.add(cartItem);
        countTotal();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage("Added to cart");
        facesContext.addMessage(null, facesMessage);
    }

    public void removeItem(Long id) {

        CartItem cartItemToRemove = null;
        Double price =  0d;
        for(CartItem cartItem : cartItemList) {
            if(cartItem.getProduct().getId() == id){
                price = cartItem.getQuantity() * cartItem.getProduct().getPrice();
                cartItemToRemove = cartItem;
            }
        }
        setTotal(getTotal() - price);
        cartItemList.remove(cartItemToRemove);
    }

    public String getProducts() {
        String products = "";
        for(CartItem cartItem : cartItemList) {
            products = products + cartItem.getProduct().getProductName() + " (" + cartItem.getQuantity() + ") " ;
        }

        return products;
    }

    private void countTotal() {
        Double total = 0d;
        for(CartItem cartItem : this.cartItemList) {
            System.out.println(cartItem.getProduct().toString());
            total += cartItem.getProduct().getPrice() * cartItem.getQuantity().doubleValue();
        }
        setTotal(Double.valueOf(df2.format(total)));
    }
}

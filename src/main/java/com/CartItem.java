package com;

import com.shop.dbo.Product;

import java.io.Serializable;


public class CartItem implements  Serializable{

        private Product product;
        private Integer quantity = 1;

        public Integer getQuantity() {
                return quantity;
        }

        @Override
        public String toString() {
                return "CartItem{" +
                        "product=" + product +
                        ", quantity=" + quantity +
                        '}';
        }

        public void setQuantity(Integer quantity) {
                this.quantity = quantity;
        }

        public Product getProduct() {
                return product;
        }

        public void setProduct(Product product) {
                this.product = product;
        }

        public CartItem(){}

        public CartItem(Product product, Integer quantity) {
                this.product = product;
                this.quantity = quantity;
        }
}

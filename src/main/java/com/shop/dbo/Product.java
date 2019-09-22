package com.shop.dbo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Product implements Serializable {

    @Column
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category", length = 80)
    private String category;

    @Column(name = "product_name", length = 80)
    private String productName;

    @Column(name = "price")
    private Double price;

    @Column(name = "image")
    private String image;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }

    public Product(String category, Double price, String productName, Long id) {
        this.category = category;
        this.price = price;
        this.productName = productName;
        this.id = id;
    }

    public Product(){}

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

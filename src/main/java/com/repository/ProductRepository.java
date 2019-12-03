package com.repository;

import com.shop.dbo.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Stateless
public class ProductRepository implements Serializable {

    @PersistenceContext
    EntityManager entityManager;

    public List<Product> queryAll() {
        return entityManager.createQuery("SELECT DISTINCT new com.shop.dbo.Product(e.category, e.price, e.productName, e.id) FROM Product e", Product.class).getResultList();
    }

    public Product find(Long id) {
        return entityManager.find(Product.class, id);
    }


}

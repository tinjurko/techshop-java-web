package com.controller;

import com.repository.ProductRepository;
import com.shop.dbo.Product;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named
public class ProductDatatable implements Serializable {

    @Inject
    ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.queryAll();
    }
}

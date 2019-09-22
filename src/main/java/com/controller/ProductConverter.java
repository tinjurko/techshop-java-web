package com.controller;

import com.repository.ProductRepository;
import com.shop.dbo.Product;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ProductConverter implements Converter {

    @Inject
    private ProductRepository productRepository;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        return productRepository.find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if(o instanceof Product) {
            return String.valueOf(((Product) o).getId());
        } else {
            throw new ConverterException("Product is not valid");
        }
    }
}

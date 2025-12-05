package com.pluralsight.NorthwindTradersSpringBootPart2.services;

import com.pluralsight.NorthwindTradersSpringBootPart2.dao.interfaces.IProductDAO;
import com.pluralsight.NorthwindTradersSpringBootPart2.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {
    private IProductDAO productDAO;


    @Autowired
    public ProductService(IProductDAO productDAO){
        this.productDAO = productDAO;
    }

    public Product addProduct(Product product){
        return productDAO.add(product);
    }

    public List<Product> getAllProducts(){
        return productDAO.getAllProducts();
    }

    public Product getProductByID (int productID){
        return productDAO.getProductById(productID);
    }

    public void updateProducts (int productId, Product product){
        productDAO.update(productId, product);
    }

    public void deleteProduct(int productId){
        productDAO.delete(productId);
    }
}

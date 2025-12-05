package com.pluralsight.NorthwindTradersSpringBootPart2.dao.impl;

import com.pluralsight.NorthwindTradersSpringBootPart2.dao.interfaces.IProductDAO;
import com.pluralsight.NorthwindTradersSpringBootPart2.models.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SimpleProductDAO implements IProductDAO {
    private List<Product> products;

    public SimpleProductDAO() {
        this.products = new ArrayList<>();
        products.add(new Product(1,"Apple", 1, 6));
        products.add(new Product(2,"Banana", 2, 5));
        products.add(new Product(3,"Orange", 3, 4));
    }

    @Override
    public Product add(Product product) {
        int maxId = 0;
        for (Product product1 : products) {
            if (product1.getProductId() > maxId){
                maxId = product1.getProductId();
            }
        }
        product.setProductId(maxId + 1);
        products.add(product);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public Product getProductById(int productId) {
        for (Product product1 : products) {
            if (product1.getProductId() == productId){
                return product1;
            }
        }
        return null;
    }

    @Override
    public void update(int productId, Product product) {
        int index = getProductIndex(productId);
        if (index != -1){
            products.set(index, product);
        }
    }

    @Override
    public void delete(int productId) {
        int index = getProductIndex(productId);
        if (index != -1){
            products.remove(index);
        }
    }

    private int getProductIndex(int productID){
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId() == productID){
                return i;
            }
        }
        return -1;
    }

}

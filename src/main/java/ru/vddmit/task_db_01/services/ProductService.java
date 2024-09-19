package ru.vddmit.task_db_01.services;

import org.springframework.stereotype.Service;
import ru.vddmit.task_db_01.models.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private List<Product> products = new ArrayList<>();
    private int ID;
    {
        products.add(new Product(++ID, "Шестерня", "радиус 20 см", new BigDecimal(121), 3));
        products.add(new Product(++ID, "Гайка", "радиус 3 см", new BigDecimal(24), 4));
    }

    public List<Product> listProducts() {
        return products;
    }
    public void saveProduct(Product product) {
        products.add(product);
    }
    public void deleteProduct(int id) {
        products.removeIf(product -> product.getProduct_id()==id);
    }

    public Product getProductById(int productId) {
        for (Product product : products) {
            if (product.getProduct_id() == productId) {
                return product;
            }
        }

        return null;
    }
}

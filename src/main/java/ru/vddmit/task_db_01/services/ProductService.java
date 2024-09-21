package ru.vddmit.task_db_01.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vddmit.task_db_01.models.Product;
import ru.vddmit.task_db_01.repositories.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> listProducts(String productName) {
        if(productName != null) productRepository.findByProductName(productName);
        return productRepository.findAll();
   }
    public void saveProduct(Product product) {
        log.info("Saving product: {}", product);
        productRepository.save(product);
    }
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public Product getProductById(int productId) {
       return productRepository.findById(productId).orElse(null);
    }
}

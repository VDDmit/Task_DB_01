package ru.vddmit.task_db_01.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.vddmit.task_db_01.models.Product;
import ru.vddmit.task_db_01.repositories.OrderItemRepository;
import ru.vddmit.task_db_01.repositories.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    public List<Product> listProducts(String productName) {
        if (StringUtils.hasText(productName)) {
            return productRepository.findByProductName(productName);
        }
        return productRepository.findAll();
    }


    public Product getProductById(long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public void saveProduct(Product product) {
        log.info("Saving product: {}", product);
        productRepository.save(product);
    }

    public void deleteProduct(long id) {
        log.info("Deleting product with id: {}", id);
        System.out.println("Attempting to delete product with ID: " + id);
        productRepository.deleteById(id);
        System.out.println("Deleted product with ID: " + id);
    }

    public boolean canDeleteProduct(long id) {
        return orderItemRepository.countByProduct_ProductId(id) == 0;
    }
}

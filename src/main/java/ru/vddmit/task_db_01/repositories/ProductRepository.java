package ru.vddmit.task_db_01.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vddmit.task_db_01.models.Product;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductName(String productName);

}

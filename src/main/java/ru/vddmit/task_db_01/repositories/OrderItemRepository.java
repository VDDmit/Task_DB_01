package ru.vddmit.task_db_01.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vddmit.task_db_01.models.Order;
import ru.vddmit.task_db_01.models.OrderItem;
import ru.vddmit.task_db_01.models.Product;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrder(Order order);
    long countByProduct_ProductId(long productId);


}
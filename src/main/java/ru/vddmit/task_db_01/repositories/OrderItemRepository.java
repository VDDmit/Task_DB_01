package ru.vddmit.task_db_01.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vddmit.task_db_01.models.Order;
import ru.vddmit.task_db_01.models.OrderItem;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByOrder(Order order);

    OrderItem findByOrderIdAndProduct_ProductId(Long orderId, Long productId);

    void deleteByOrderIdAndProduct_ProductId(Long orderId, Long productId);

    long countByProduct_ProductId(long productId);
}
package ru.vddmit.task_db_01.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vddmit.task_db_01.models.Order;
import ru.vddmit.task_db_01.models.OrderItem;
import ru.vddmit.task_db_01.repositories.OrderItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public List<OrderItem> getItemsByOrder(Order order) {
        return orderItemRepository.findByOrder(order);
    }

    public void saveOrderItem(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    public void deleteOrderItem(long orderItemId) {
        orderItemRepository.deleteById(orderItemId);

    }

    public OrderItem getOrderItemById(long orderItemId) {
        return orderItemRepository.findById(orderItemId).orElse(null);
    }
}

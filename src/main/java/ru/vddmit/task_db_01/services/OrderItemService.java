package ru.vddmit.task_db_01.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vddmit.task_db_01.models.OrderItem;
import ru.vddmit.task_db_01.repositories.OrderItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public List<OrderItem> getItemsByOrderId(int orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    public void saveOrderItem(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    public void deleteOrderItem(int orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }
}

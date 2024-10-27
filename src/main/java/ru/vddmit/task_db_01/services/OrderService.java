package ru.vddmit.task_db_01.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vddmit.task_db_01.models.Customer;
import ru.vddmit.task_db_01.models.Order;
import ru.vddmit.task_db_01.models.OrderItem;
import ru.vddmit.task_db_01.repositories.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> getOrdersByCustomer(Customer customer) {
        return orderRepository.findByCustomer(customer);
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public Order getOrderById(long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public void deleteOrder(long id) {
        orderRepository.deleteById(id);
    }
}

package ru.vddmit.task_db_01.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vddmit.task_db_01.models.Order;
import ru.vddmit.task_db_01.models.Customer;
import ru.vddmit.task_db_01.services.OrderService;
import ru.vddmit.task_db_01.services.CustomerService;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final CustomerService customerService;

    @GetMapping("/orders/{customerId}")
    public String getOrdersByCustomer(@PathVariable int customerId, Model model) {
        Customer customer = customerService.getCustomerById(customerId);
        model.addAttribute("orders", orderService.getOrdersByCustomer(customer));
        return "orders";  // Шаблон для отображения всех заказов клиента
    }

    @GetMapping("/order/{id}")
    public String getOrderById(@PathVariable int id, Model model) {
        model.addAttribute("order", orderService.getOrderById(id));
        return "order_info";  // Шаблон для отображения информации о заказе
    }

    @PostMapping("/order/create")
    public String createOrder(Order order) {
        orderService.saveOrder(order);
        return "redirect:/orders/" + order.getCustomer().getId();  // Перенаправляем на список заказов клиента
    }

    @DeleteMapping("/order/delete/{id}")
    public String deleteOrder(@PathVariable int id) {
        Order order = orderService.getOrderById(id);
        orderService.deleteOrder(id);
        return "redirect:/orders/" + order.getCustomer().getId();  // Перенаправляем на список заказов после удаления
    }
}

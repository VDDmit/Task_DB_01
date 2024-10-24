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

    @GetMapping("/customers/{customerId}/orders")
    public String getOrdersByCustomer(@PathVariable long customerId, Model model) {
        Customer customer = customerService.getCustomerById(customerId);
        model.addAttribute("orders", orderService.getOrdersByCustomer(customer));
        model.addAttribute("customer", customer);
        return "/customer_orders";
    }


    @GetMapping("/customers/{customerId}/order/{id}")
    public String getOrderById(@PathVariable long id, Model model) {
        model.addAttribute("order", orderService.getOrderById(id));
        return "redirect:/customers/{customerId}/orders";
    }

    @PostMapping("/order/create")
    public String createOrder(Order order) {
        orderService.saveOrder(order);
        return "redirect:/orders/" + order.getCustomer().getId();
    }

    @DeleteMapping("/customers/{customerId}/order/{id}/delete}")
    public String deleteOrder(@PathVariable long id) {
        Order order = orderService.getOrderById(id);
        orderService.deleteOrder(id);
        return "redirect:/orders/" + order.getCustomer().getId();
    }
}

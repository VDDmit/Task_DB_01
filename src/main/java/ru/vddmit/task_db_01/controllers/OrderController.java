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

    @PostMapping("/order/create")
    public String createOrder(Order order) {
        orderService.saveOrder(order);
        return "redirect:/customers/" + order.getCustomer().getId() + "/orders";
    }

    @PostMapping("/customers/{customerId}/orders/{id}/delete")
    public String deleteOrder(@PathVariable String id, @PathVariable String customerId) {
        orderService.deleteOrder(Long.parseLong(id.replace("\u00A0", "").trim()));
        return "redirect:/customers/" + Long.parseLong(customerId.replace("\u00A0", "").trim()) + "/orders";
    }

}

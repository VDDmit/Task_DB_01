package ru.vddmit.task_db_01.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vddmit.task_db_01.models.Order;
import ru.vddmit.task_db_01.models.OrderItem;
import ru.vddmit.task_db_01.services.OrderItemService;
import ru.vddmit.task_db_01.services.OrderService;

@Controller
@RequiredArgsConstructor
public class OrderItemController {
    private final OrderItemService orderItemService;
    private final OrderService orderService;

    @GetMapping("/order/{orderId}/items")
    public String orderItems(@PathVariable int orderId, Model model) {
        Order order = orderService.getOrderById(orderId);
        model.addAttribute("orderItems", orderItemService.getItemsByOrder(order));
        return "order_items";
    }


    @PostMapping("/order/item/create")
    public String createOrderItem(OrderItem orderItem) {
        orderItemService.saveOrderItem(orderItem);
        return "redirect:/order/" + orderItem.getOrder().getId() + "/items";  // Перенаправляем на список позиций заказа
    }

    @DeleteMapping("/order/item/delete/{orderItemId}")
    public String deleteOrderItem(@PathVariable int orderItemId) {
        OrderItem orderItem = orderItemService.getOrderItemById(orderItemId);
        orderItemService.deleteOrderItem(orderItemId);
        return "redirect:/order/" + orderItem.getOrder().getId() + "/items";  // Перенаправляем на список позиций заказа после удаления
    }
}
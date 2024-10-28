package ru.vddmit.task_db_01.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(OrderItemController.class);

    @GetMapping("/order/{orderId}/items")
    public String orderItems(@PathVariable long orderId, Model model) {
        Order order = orderService.getOrderById(orderId);
        model.addAttribute("orderItems", orderItemService.getItemsByOrder(order));
        logger.info("Loaded items for order id {}", orderId);
        return "order_items";
    }

    @PostMapping("/order/item/create")
    public String createOrderItem(OrderItem orderItem) {
        orderItemService.saveOrderItem(orderItem);
        logger.info("Created order item with id {} for order id {}", orderItem.getOrderItemId(), orderItem.getOrder().getId());
        return "redirect:/order/" + orderItem.getOrder().getId() + "/items";
    }

    @DeleteMapping("/order/item/delete/{orderItemId}")
    public String deleteOrderItem(@PathVariable long orderItemId) {
        OrderItem orderItem = orderItemService.getOrderItemById(orderItemId);
        orderItemService.deleteOrderItem(orderItemId);
        logger.info("Deleted order item with id {} from order id {}", orderItemId, orderItem.getOrder().getId());
        return "redirect:/order/" + orderItem.getOrder().getId() + "/items";
    }
}

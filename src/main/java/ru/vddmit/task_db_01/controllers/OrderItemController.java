package ru.vddmit.task_db_01.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.vddmit.task_db_01.models.OrderItem;
import ru.vddmit.task_db_01.services.OrderItemService;

@Controller
@RequiredArgsConstructor
public class OrderItemController {
    private final OrderItemService orderItemService;
    private static final Logger logger = LoggerFactory.getLogger(OrderItemController.class);


    @PostMapping("/order/item/create")
    public String createOrderItem(OrderItem orderItem) {
        orderItemService.saveOrderItem(orderItem);
        logger.info("Created order item with id {} for order id {}", orderItem.getOrderItemId(), orderItem.getOrder().getId());
        return "redirect:/order/" + orderItem.getOrder().getId() + "/items";
    }

    @DeleteMapping("/order/item/delete/{orderItemId}")
    public String deleteOrderItem(@PathVariable String orderItemId) {
        long parsedOrderItemId = Long.parseLong(orderItemId.replace("\u00A0", "").trim());
        OrderItem orderItem = orderItemService.getOrderItemById(parsedOrderItemId);
        orderItemService.deleteOrderItem(parsedOrderItemId);
        logger.info("Deleted order item with id {} from order id {}", parsedOrderItemId, orderItem.getOrder().getId());
        return "redirect:/order/" + orderItem.getOrder().getId() + "/items";
    }
}

package ru.vddmit.task_db_01.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vddmit.task_db_01.models.OrderItem;
import ru.vddmit.task_db_01.services.OrderItemService;

@Controller
@RequiredArgsConstructor
public class OrderItemController {
    private final OrderItemService orderItemService;

    @GetMapping("/order/{order_id}/items")
    public String orderItems(@PathVariable int order_id, Model model) {
        model.addAttribute("orderItems", orderItemService.getItemsByOrderId(order_id));
        return "order_items";
    }

    @PostMapping("/order/item/create")
    public String createOrderItem(OrderItem orderItem) {
        orderItemService.saveOrderItem(orderItem);
        return "redirect:/order/" + orderItem.getOrder().getOrder_id() + "/items";
    }

    @PostMapping("/order/item/delete/{order_item_id}")
    public String deleteOrderItem(@PathVariable int order_item_id) {
        orderItemService.deleteOrderItem(order_item_id);
        return "redirect:/order/{order_id}/items";
    }
}
package ru.vddmit.task_db_01.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vddmit.task_db_01.models.Order;
import ru.vddmit.task_db_01.models.Customer;
import ru.vddmit.task_db_01.models.OrderItem;
import ru.vddmit.task_db_01.models.Product;
import ru.vddmit.task_db_01.services.OrderItemService;
import ru.vddmit.task_db_01.services.OrderService;
import ru.vddmit.task_db_01.services.CustomerService;
import ru.vddmit.task_db_01.services.ProductService;

import java.math.BigDecimal;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final CustomerService customerService;
    private final OrderItemService orderItemService;
    private final ProductService productService;

    @GetMapping("/customers/{customerId}/orders")
    public String getOrdersByCustomer(@PathVariable long customerId, Model model) {
        Customer customer = customerService.getCustomerById(customerId);
        model.addAttribute("orders", orderService.getOrdersByCustomer(customer));
        model.addAttribute("customer", customer);
        return "/customer_orders";
    }

    @GetMapping("/customers/{customerId}/order/{orderId}")
    public String getOrderById(@PathVariable long customerId, @PathVariable long orderId, Model model) {
        Order order = orderService.getOrderById(orderId);
        Customer customer = customerService.getCustomerById(customerId);

        model.addAttribute("customer", customer);
        model.addAttribute("order", order);
        model.addAttribute("orderItems", orderItemService.getItemsByOrder(order));
        model.addAttribute("availableProducts", productService.listProducts(null));
        return "/view_order";
    }

    @PostMapping("/order/create")
    public String createOrder(Order order) {
        orderService.saveOrder(order);
        return "redirect:/customers/" + order.getCustomer().getId() + "/orders";
    }

    @GetMapping("/customers/{customerId}/order/{orderId}/{productId}/edit")
    public String showEditOrderItemPage(@PathVariable long customerId,
                                        @PathVariable long orderId,
                                        @PathVariable long productId,
                                        Model model) {

        Order order = orderService.getOrderById(orderId);
        OrderItem orderItem = orderItemService.getOrderItemByOrderIdAndProductId(orderId, productId);
        Product product = productService.getProductById(productId);
        Customer customer = customerService.getCustomerById(customerId);

        model.addAttribute("customer", customer);
        model.addAttribute("order", order);
        model.addAttribute("orderItem", orderItem);
        model.addAttribute("product", product);

        return "/edit_order";
    }

    @PostMapping("/customers/{customerId}/order/{orderId}/{productId}/edit")
    public String editOrderItem(@PathVariable long customerId,
                                @PathVariable long orderId,
                                @PathVariable long productId,
                                @RequestParam BigDecimal unitPrice,
                                @RequestParam int quantity) {

        OrderItem orderItem = orderItemService.getOrderItemByOrderIdAndProductId(orderId, productId);
        Product product = productService.getProductById(productId);

        int previousQuantity = orderItem.getQuantity();
        int stockDifference = quantity - previousQuantity;
        int availableStock = product.getStockQuantity();

        if (stockDifference > availableStock) {
            return "redirect:/customers/" + customerId + "/order/" + orderId + "?error=not_enough_stock";
        }

        orderItem.setQuantity(quantity);
        orderItem.setUnitPrice(unitPrice);
        product.setStockQuantity(availableStock - stockDifference);

        orderItemService.saveOrderItem(orderItem);
        productService.saveProduct(product);

        return "redirect:/customers/" + customerId + "/order/" + orderId;
    }

    @Transactional
    @PostMapping("/customers/{customerId}/order/{orderId}/products/{productId}/delete")
    public String deleteOrderItem(@PathVariable long orderId,
                                  @PathVariable long productId,
                                  @RequestParam int quantity,
                                  @PathVariable String customerId) {
        Product product = productService.getProductById(productId);
        product.setStockQuantity(product.getStockQuantity() + quantity);
        productService.saveProduct(product);

        orderItemService.deleteOrderItemByOrderIdAndProductId(orderId, productId);

        return "redirect:/customers/" + customerId + "/order/" + orderId;
    }

    @PostMapping("/customers/{customerId}/orders/{id}/delete")
    public String deleteOrder(@PathVariable String id, @PathVariable String customerId) {
        orderService.deleteOrder(Long.parseLong(id.replace("\u00A0", "").trim()));
        return "redirect:/customers/" + Long.parseLong(customerId.replace("\u00A0", "").trim()) + "/orders";
    }
}

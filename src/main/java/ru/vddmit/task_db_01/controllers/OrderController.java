package ru.vddmit.task_db_01.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @GetMapping("/customers/{customerId}/orders")
    public String getOrdersByCustomer(@PathVariable long customerId, Model model) {
        log.info("Fetching orders for customerId={}", customerId);
        Customer customer = customerService.getCustomerById(customerId);
        model.addAttribute("orders", orderService.getOrdersByCustomer(customer));
        model.addAttribute("customer", customer);
        return "/customer_orders";
    }

    @GetMapping("/customers/{customerId}/order/{orderId}")
    public String getOrderById(@PathVariable long customerId, @PathVariable long orderId, Model model) {
        log.info("Fetching orderId={} for customerId={}", orderId, customerId);
        Order order = orderService.getOrderById(orderId);
        Customer customer = customerService.getCustomerById(customerId);

        model.addAttribute("customer", customer);
        model.addAttribute("products", productService.listProducts(null));
        model.addAttribute("order", order);
        model.addAttribute("orderItems", orderItemService.getItemsByOrder(order));
        model.addAttribute("availableProducts", productService.listProducts(null));
        return "/view_order";
    }

    @PostMapping("/order/create")
    public String createOrder(Order order) {
        log.info("Creating order for customerId={}", order.getCustomer().getId());
        orderService.saveOrder(order);
        return "redirect:/customers/" + order.getCustomer().getId() + "/orders";
    }

    @GetMapping("/customers/{customerId}/order/{orderId}/{productId}/edit")
    public String showEditOrderItemPage(@PathVariable long customerId,
                                        @PathVariable long orderId,
                                        @PathVariable long productId,
                                        Model model) {
        log.info("Showing edit page for orderId={}, productId={} and customerId={}", orderId, productId, customerId);
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
        log.info("Editing order item for orderId={}, productId={}, newQuantity={}, unitPrice={}",
                orderId, productId, quantity, unitPrice);

        OrderItem orderItem = orderItemService.getOrderItemByOrderIdAndProductId(orderId, productId);
        Product product = productService.getProductById(productId);

        int previousQuantity = orderItem.getQuantity();
        int stockDifference = quantity - previousQuantity;
        int availableStock = product.getStockQuantity();

        if (stockDifference > availableStock) {
            log.warn("Not enough stock available for productId={} requestedQuantity={}, availableStock={}",
                    productId, quantity, availableStock);
            return "redirect:/customers/" + customerId + "/order/" + orderId + "?error=not_enough_stock";
        }

        orderItem.setQuantity(quantity);
        orderItem.setUnitPrice(unitPrice);
        product.setStockQuantity(availableStock - stockDifference);

        orderItemService.saveOrderItem(orderItem);
        productService.saveProduct(product);

        log.info("Order item updated successfully: {}", orderItem);
        return "redirect:/customers/" + customerId + "/order/" + orderId;
    }

    @Transactional
    @PostMapping("/customers/{customerId}/order/{orderId}/add_product_for_order")
    public String addProductForOrder(@PathVariable Long customerId,
                                     @PathVariable Long orderId,
                                     @RequestParam Long productId,
                                     @RequestParam Integer quantity) {
        log.info("Adding product to order: customerId={}, orderId={}, productId={}, quantity={}",
                customerId, orderId, productId, quantity);

        if (customerId == null || orderId == null || productId == null || quantity == null || quantity <= 0) {
            log.error("Invalid input: customerId={}, orderId={}, productId={}, quantity={}",
                    customerId, orderId, productId, quantity);
            return "redirect:/customers/" + customerId + "/order/" + orderId + "?error=invalid_input";
        }

        Product product = productService.getProductById(productId);
        log.info("Loaded product: {}", product);

        Order order = orderService.getOrderById(orderId);
        log.info("Loaded order: {}", order);

        product.setStockQuantity(product.getStockQuantity() - quantity);
        productService.saveProduct(product);
        log.info("Updated product stock: productId={}, newStock={}", productId, product.getStockQuantity());

        OrderItem existingOrderItem = orderItemService.getOrderItemByOrderIdAndProductId(orderId, productId);
        if (existingOrderItem != null) {
            existingOrderItem.setQuantity(existingOrderItem.getQuantity() + quantity);
            existingOrderItem.setUnitPrice(product.getPrice());
            orderItemService.saveOrderItem(existingOrderItem);
            log.info("Updated existing order item: {}", existingOrderItem);
        } else {
            OrderItem newOrderItem = new OrderItem();
            newOrderItem.setOrder(order);
            newOrderItem.setProduct(product);
            newOrderItem.setQuantity(quantity);
            newOrderItem.setUnitPrice(product.getPrice());
            orderItemService.saveOrderItem(newOrderItem);
            log.info("Created new order item: {}", newOrderItem);
        }

        return "redirect:/customers/" + customerId + "/order/" + orderId;
    }

    @Transactional
    @PostMapping("/customers/{customerId}/order/{orderId}/products/{productId}/delete")
    public String deleteOrderItem(@PathVariable long orderId,
                                  @PathVariable long productId,
                                  @RequestParam int quantity,
                                  @PathVariable String customerId) {
        log.info("Deleting order item for orderId={}, productId={}, quantity={}, customerId={}",
                orderId, productId, quantity, customerId);

        Product product = productService.getProductById(productId);
        product.setStockQuantity(product.getStockQuantity() + quantity);
        productService.saveProduct(product);

        orderItemService.deleteOrderItemByOrderIdAndProductId(orderId, productId);
        log.info("Order item deleted successfully: orderId={}, productId={}", orderId, productId);

        return "redirect:/customers/" + customerId + "/order/" + orderId;
    }

    @PostMapping("/customers/{customerId}/orders/{id}/delete")
    public String deleteOrder(@PathVariable String id, @PathVariable String customerId) {
        log.info("Deleting orderId={} for customerId={}", id, customerId);
        orderService.deleteOrder(Long.parseLong(id.replace("\u00A0", "").trim()));
        return "redirect:/customers/" + Long.parseLong(customerId.replace("\u00A0", "").trim()) + "/orders";
    }
}

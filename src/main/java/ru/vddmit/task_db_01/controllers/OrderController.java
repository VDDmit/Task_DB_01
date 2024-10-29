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
    public String getOrdersByCustomer(@PathVariable String customerId, Model model) {
        long parsedCustomerId = Long.parseLong(customerId.replace("\u00A0", "").trim());
        log.info("Fetching orders for customerId={}", parsedCustomerId);
        Customer customer = customerService.getCustomerById(parsedCustomerId);
        model.addAttribute("orders", orderService.getOrdersByCustomer(customer));
        model.addAttribute("customer", customer);
        return "/customer_orders";
    }

    @GetMapping("/customers/{customerId}/order/{orderId}")
    public String getOrderById(@PathVariable String customerId, @PathVariable String orderId, Model model) {
        long parsedCustomerId = Long.parseLong(customerId.replace("\u00A0", "").trim());
        long parsedOrderId = Long.parseLong(orderId.replace("\u00A0", "").trim());
        log.info("Fetching orderId={} for customerId={}", parsedOrderId, parsedCustomerId);
        Order order = orderService.getOrderById(parsedOrderId);
        Customer customer = customerService.getCustomerById(parsedCustomerId);

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
    public String showEditOrderItemPage(@PathVariable String customerId,
                                        @PathVariable String orderId,
                                        @PathVariable String productId,
                                        Model model) {
        long parsedCustomerId = Long.parseLong(customerId.replace("\u00A0", "").trim());
        long parsedOrderId = Long.parseLong(orderId.replace("\u00A0", "").trim());
        long parsedProductId = Long.parseLong(productId.replace("\u00A0", "").trim());

        log.info("Showing edit page for orderId={}, productId={} and customerId={}", parsedOrderId, parsedProductId, parsedCustomerId);
        Order order = orderService.getOrderById(parsedOrderId);
        OrderItem orderItem = orderItemService.getOrderItemByOrderIdAndProductId(parsedOrderId, parsedProductId);
        Product product = productService.getProductById(parsedProductId);
        Customer customer = customerService.getCustomerById(parsedCustomerId);

        model.addAttribute("customer", customer);
        model.addAttribute("order", order);
        model.addAttribute("orderItem", orderItem);
        model.addAttribute("product", product);

        return "/edit_order";
    }

    @PostMapping("/customers/{customerId}/order/{orderId}/{productId}/edit")
    public String editOrderItem(@PathVariable String customerId,
                                @PathVariable String orderId,
                                @PathVariable String productId,
                                @RequestParam BigDecimal unitPrice,
                                @RequestParam int quantity) {
        long parsedCustomerId = Long.parseLong(customerId.replace("\u00A0", "").trim());
        long parsedOrderId = Long.parseLong(orderId.replace("\u00A0", "").trim());
        long parsedProductId = Long.parseLong(productId.replace("\u00A0", "").trim());

        log.info("Editing order item for orderId={}, productId={}, newQuantity={}, unitPrice={}",
                parsedOrderId, parsedProductId, quantity, unitPrice);

        OrderItem orderItem = orderItemService.getOrderItemByOrderIdAndProductId(parsedOrderId, parsedProductId);
        Product product = productService.getProductById(parsedProductId);

        int previousQuantity = orderItem.getQuantity();
        int stockDifference = quantity - previousQuantity;
        int availableStock = product.getStockQuantity();

        if (stockDifference > availableStock) {
            log.warn("Not enough stock available for productId={} requestedQuantity={}, availableStock={}",
                    parsedProductId, quantity, availableStock);
            return "redirect:/customers/" + parsedCustomerId + "/order/" + parsedOrderId + "?error=not_enough_stock";
        }

        orderItem.setQuantity(quantity);
        orderItem.setUnitPrice(unitPrice);
        product.setStockQuantity(availableStock - stockDifference);

        orderItemService.saveOrderItem(orderItem);
        productService.saveProduct(product);

        log.info("Order item updated successfully: {}", orderItem);
        return "redirect:/customers/" + parsedCustomerId + "/order/" + parsedOrderId;
    }

    @Transactional
    @PostMapping("/customers/{customerId}/order/{orderId}/add_product_for_order")
    public String addProductForOrder(@PathVariable String customerId,
                                     @PathVariable String orderId,
                                     @RequestParam String productId,
                                     @RequestParam Integer quantity) {
        long parsedCustomerId = Long.parseLong(customerId.replace("\u00A0", "").trim());
        long parsedOrderId = Long.parseLong(orderId.replace("\u00A0", "").trim());
        long parsedProductId = Long.parseLong(productId.replace("\u00A0", "").trim());

        log.info("Adding product to order: customerId={}, orderId={}, productId={}, quantity={}",
                parsedCustomerId, parsedOrderId, parsedProductId, quantity);

        if (parsedCustomerId == 0 || parsedOrderId == 0 || parsedProductId == 0 || quantity == null || quantity <= 0) {
            log.error("Invalid input: customerId={}, orderId={}, productId={}, quantity={}",
                    parsedCustomerId, parsedOrderId, parsedProductId, quantity);
            return "redirect:/customers/" + parsedCustomerId + "/order/" + parsedOrderId + "?error=invalid_input";
        }

        Product product = productService.getProductById(parsedProductId);
        log.info("Loaded product: {}", product);

        Order order = orderService.getOrderById(parsedOrderId);
        log.info("Loaded order: {}", order);

        product.setStockQuantity(product.getStockQuantity() - quantity);
        productService.saveProduct(product);
        log.info("Updated product stock: productId={}, newStock={}", parsedProductId, product.getStockQuantity());

        OrderItem existingOrderItem = orderItemService.getOrderItemByOrderIdAndProductId(parsedOrderId, parsedProductId);
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

        return "redirect:/customers/" + parsedCustomerId + "/order/" + parsedOrderId;
    }

    @Transactional
    @PostMapping("/customers/{customerId}/order/{orderId}/products/{productId}/delete")
    public String deleteOrderItem(@PathVariable String orderId,
                                  @PathVariable String productId,
                                  @RequestParam int quantity,
                                  @PathVariable String customerId) {
        long longOrderId = Long.parseLong(orderId.replace("\u00A0", "").trim());
        long parsedProductId = Long.parseLong(productId.replace("\u00A0", "").trim());
        long parsedCustomerId = Long.parseLong(customerId.replace("\u00A0", "").trim());

        log.info("Deleting order item for orderId={}, productId={}, quantity={}, customerId={}",
                longOrderId, parsedProductId, quantity, parsedCustomerId);

        Product product = productService.getProductById(parsedProductId);
        product.setStockQuantity(product.getStockQuantity() + quantity);
        productService.saveProduct(product);

        orderItemService.deleteOrderItemByOrderIdAndProductId(longOrderId, parsedProductId);
        log.info("Order item deleted successfully: orderId={}, productId={}", longOrderId, parsedProductId);

        return "redirect:/customers/" + parsedCustomerId + "/order/" + longOrderId;
    }

    @PostMapping("/customers/{customerId}/orders/{id}/delete")
    public String deleteOrder(@PathVariable String id, @PathVariable String customerId) {
        log.info("Deleting orderId={} for customerId={}", id, customerId);
        orderService.deleteOrder(Long.parseLong(id.replace("\u00A0", "").trim()));
        return "redirect:/customers/" + Long.parseLong(customerId.replace("\u00A0", "").trim()) + "/orders";
    }
}

package ru.vddmit.task_db_01.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vddmit.task_db_01.models.Customer;
import ru.vddmit.task_db_01.services.CustomerService;


@Controller
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/customers")
    public String customers(@RequestParam(name = "email", required = false) String email, Model model) {
        model.addAttribute("customers", customerService.listCustomers(email));
        return "/customers";
    }

    @GetMapping("/customer/{id}")
    public String getCustomerById(@PathVariable long id, Model model) {
        model.addAttribute("customer", customerService.getCustomerById(id));
        return "/customer_info";
    }

    @PostMapping("/customers/create")
    public String createCustomer(Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    @PostMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(Long.parseLong(id.replace("\u00A0", "").trim()));
        logger.info("Customer with id {} deleted", id);
        return "redirect:/customers";
    }
}


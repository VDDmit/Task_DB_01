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
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping("/customers")
    public String customers(@RequestParam(name = "email", required = false) String email, Model model) {
        model.addAttribute("customers", customerService.listCustomers(email));
        return "/customers";
    }

    @PostMapping("/customers/create")
    public String createCustomer(Customer customer) {
        customerService.saveCustomer(customer);
        logger.info("Customer created with id {}", customer.getId());
        return "redirect:/customers";
    }

    @GetMapping("/customers/{customer_id}/edit")
    public String editCustomerForm(@PathVariable String customer_id, Model model) {
        long id = Long.parseLong(customer_id.replace("\u00A0", "").trim());
        Customer customer = customerService.getCustomerById(id);
        if (customer != null) {
            model.addAttribute("customer", customer);
            logger.info("Loaded customer for editing with id {}", customer_id);
            return "/edit_customer";
        } else {
            logger.warn("Customer with id {} not found", customer_id);
            return "redirect:/customers";
        }
    }

    @PostMapping("/customers/{customer_id}/edit")
    public String editCustomer(@PathVariable String customer_id, @ModelAttribute Customer customer) {
        long id = Long.parseLong(customer_id.replace("\u00A0", "").trim());
        customer.setId(id);
        customerService.saveCustomer(customer);
        logger.info("Customer with id {} edited", customer_id);
        return "redirect:/customers";
    }

    @PostMapping("/customers/{id}/delete")
    public String deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(Long.parseLong(id.replace("\u00A0", "").trim()));
        logger.info("Customer with id {} deleted", id);
        return "redirect:/customers";
    }
}

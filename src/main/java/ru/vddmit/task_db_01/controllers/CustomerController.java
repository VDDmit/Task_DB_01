package ru.vddmit.task_db_01.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vddmit.task_db_01.models.Customer;
import ru.vddmit.task_db_01.services.CustomerService;


@Controller
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/customers")
    public String getAllCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customers";
    }

    @GetMapping("/customer/{id}")
    public String getCustomerById(@PathVariable long id, Model model) {
        model.addAttribute("customer", customerService.getCustomerById(id));
        return "customer_info";
    }

    @PostMapping("/customer/create")
    public String createCustomer(Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    @DeleteMapping("/customer/delete/{id}")
    public String deleteCustomer(@PathVariable long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }
}


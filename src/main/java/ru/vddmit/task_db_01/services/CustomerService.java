package ru.vddmit.task_db_01.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.vddmit.task_db_01.models.Customer;
import ru.vddmit.task_db_01.repositories.CustomerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<Customer> listCustomers(String email) {
        if(StringUtils.hasText(email)){
            return customerRepository.findByEmail(email);
        }
        return customerRepository.findAll();
    }

    public Customer getCustomerById(long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer with id " + id + " not found."));
    }


    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteCustomer(long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Customer with id " + id + " not found.");
        }
    }

    public List<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}

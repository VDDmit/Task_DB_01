package ru.vddmit.task_db_01.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.vddmit.task_db_01.models.Customer;
import ru.vddmit.task_db_01.models.Order;
import ru.vddmit.task_db_01.repositories.CustomerRepository;
import ru.vddmit.task_db_01.repositories.OrderItemRepository;
import ru.vddmit.task_db_01.repositories.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    public List<Customer> listCustomers(String email) {
        if (StringUtils.hasText(email)) {
            return customerRepository.findByEmail(email);
        }
        return customerRepository.findAll();
    }

    public Customer getCustomerById(long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer with id " + id + " not found."));
    }

    @Transactional
    public void saveCustomer(Customer customer) {
        try {
            customerRepository.save(customer);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }

    @Transactional
    public void deleteCustomer(long id) {
        if (customerRepository.existsById(id)) {
            Customer customer = customerRepository.getReferenceById(id);
            List<Long> orderIds = orderRepository.findByCustomer(customer).stream()
                    .map(Order::getId)
                    .toList();
            orderRepository.deleteByCustomer(customer);
            orderRepository.deleteByCustomer(customer);
            customerRepository.deleteById(id);

            logger.info("Customer with id {} deleted with his orders their ids: {}", id, orderIds);
        } else {
            throw new EntityNotFoundException("Customer with id " + id + " not found.");
        }
    }

    public List<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}
package ru.vddmit.task_db_01.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vddmit.task_db_01.models.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
}
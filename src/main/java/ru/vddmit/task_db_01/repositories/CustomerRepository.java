package ru.vddmit.task_db_01.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vddmit.task_db_01.models.Customer;

import java.util.List;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByEmail(String email);
}
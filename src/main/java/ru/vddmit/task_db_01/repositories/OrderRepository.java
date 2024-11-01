package ru.vddmit.task_db_01.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.vddmit.task_db_01.models.Customer;
import ru.vddmit.task_db_01.models.Order;


import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer(Customer customer);

    void deleteByCustomer(Customer customer);

    @Query("SELECT AVG(o.totalAmount) FROM Order o")
    BigDecimal findAverageOrderPrice();

    @Query("SELECT MAX(o.totalAmount) FROM Order o")
    BigDecimal findMaxOrderPrice();

    @Query("SELECT COUNT(o) * 1.0 / COUNT(DISTINCT o.customer.id) FROM Order o")
    Double findAverageOrdersPerCustomer();
}
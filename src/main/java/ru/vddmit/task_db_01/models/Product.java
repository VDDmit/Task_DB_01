package ru.vddmit.task_db_01.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Product {
    private int product_id;
    private String product_name;
    private String description;
    private BigDecimal price;
    private int stock_quantity;
}

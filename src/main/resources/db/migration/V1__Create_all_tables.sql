CREATE SCHEMA IF NOT EXISTS db_for_shop;

-- Таблица клиентов
CREATE TABLE db_for_shop.Customers
(
    CustomerID  SERIAL PRIMARY KEY,
    FirstName   VARCHAR(50)         NOT NULL,
    LastName    VARCHAR(50)         NOT NULL,
    Email       VARCHAR(100) UNIQUE NOT NULL,
    PhoneNumber VARCHAR(20),
    Address     TEXT
);

-- Таблица товаров
CREATE TABLE db_for_shop.Products
(
    ProductID     SERIAL PRIMARY KEY,
    ProductName   VARCHAR(100)   NOT NULL,
    Description   TEXT,
    Price         NUMERIC(10, 2) NOT NULL,
    StockQuantity INT            NOT NULL
);

-- Таблица заказов
CREATE TABLE db_for_shop.Orders
(
    OrderID     SERIAL PRIMARY KEY,
    CustomerID  INT            NOT NULL,
    OrderDate   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    TotalAmount NUMERIC(10, 2) NOT NULL,
    FOREIGN KEY (CustomerID) REFERENCES db_for_shop.Customers (CustomerID)
);

-- Таблица позиций заказа
CREATE TABLE db_for_shop.OrderItems
(
    OrderItemID SERIAL PRIMARY KEY,
    OrderID     INT            NOT NULL,
    ProductID   INT            NOT NULL,
    Quantity    INT            NOT NULL,
    UnitPrice   NUMERIC(10, 2) NOT NULL,
    FOREIGN KEY (OrderID) REFERENCES db_for_shop.Orders (OrderID) ON DELETE CASCADE,
    FOREIGN KEY (ProductID) REFERENCES db_for_shop.Products (ProductID) ON DELETE RESTRICT
);
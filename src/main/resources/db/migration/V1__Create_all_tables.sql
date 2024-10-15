CREATE SCHEMA IF NOT EXISTS db_for_shop;

-- Таблица клиентов
CREATE TABLE customer
(
    customer_id   SERIAL PRIMARY KEY,
    first_name    VARCHAR(50)         NOT NULL,
    last_name     VARCHAR(50)         NOT NULL,
    email         VARCHAR(100) UNIQUE NOT NULL,
    phone_number  VARCHAR(20),
    address       TEXT
);

-- Таблица товаров
CREATE TABLE product
(
    product_id     SERIAL PRIMARY KEY,
    product_name   VARCHAR(100)   NOT NULL,
    description    TEXT,
    price          NUMERIC(10, 2) NOT NULL,
    stock_quantity INT            NOT NULL
);

-- Таблица заказов
CREATE TABLE "order"
(
    order_id     SERIAL PRIMARY KEY,
    customer_id  INT            NOT NULL,
    order_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount NUMERIC(10, 2) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer (customer_id)
);

-- Таблица позиций заказа
CREATE TABLE order_item
(
    order_item_id  SERIAL PRIMARY KEY,
    order_id       INT            NOT NULL,
    product_id     INT            NOT NULL,
    quantity       INT            NOT NULL,
    unit_price     NUMERIC(10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES "order" (order_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE RESTRICT
);

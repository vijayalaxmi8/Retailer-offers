-- schema.sql
CREATE TABLE CUSTOMERS (
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(250) NOT NULL,
phone_number VARCHAR(250) NOT NULL,
email VARCHAR(250) DEFAULT NULL
);

CREATE TABLE TRANSACTIONS (
id INT AUTO_INCREMENT PRIMARY KEY,
customer_id INT,
amount DOUBLE,
transaction_date VARCHAR(250) NOT NULL,
points int DEFAULT NULL
);

-- Insert Customers
INSERT INTO customers (name, email, phone_number) VALUES ('John Doe', 'john.doe@example.com', '123-456-7890');
INSERT INTO customers (name, email, phone_number) VALUES ('Jane Smith', 'jane.smith@example.com', '987-654-3210');
INSERT INTO customers (name, email, phone_number) VALUES ('Robert Brown', 'robert.brown@example.com', '555-123-4567');


---- Transactions for Customer 1
INSERT INTO transactions (customer_id, amount, transaction_date,points) VALUES (1, 120.0, '2024-12-01',90);
INSERT INTO transactions (customer_id, amount, transaction_date,points) VALUES (1, 75.0, '2024-12-10',25);
INSERT INTO transactions (customer_id, amount, transaction_date,points) VALUES (1, 200.0, '2024-11-20',250);
INSERT INTO transactions (customer_id, amount, transaction_date,points) VALUES (1, 45.0, '2025-01-05',0);

---- Transactions for Customer 2
INSERT INTO transactions (customer_id, amount, transaction_date,points) VALUES (2, 90.0, '2024-12-02',40);
INSERT INTO transactions (customer_id, amount, transaction_date,points) VALUES (2, 150.0, '2024-12-15',150);
INSERT INTO transactions (customer_id, amount, transaction_date,points) VALUES (2, 60.0, '2025-01-08',10);
INSERT INTO transactions (customer_id, amount, transaction_date,points) VALUES (2, 130.0, '2025-01-20',110);

---- Transactions for Customer 3
INSERT INTO transactions (customer_id, amount, transaction_date,points) VALUES (3, 250.0, '2024-12-07',350);
INSERT INTO transactions (customer_id, amount, transaction_date,points) VALUES (3, 300.0, '2025-01-10',450);
INSERT INTO transactions (customer_id, amount, transaction_date,points) VALUES (3, 50.0, '2025-01-15',0);
INSERT INTO transactions (customer_id, amount, transaction_date,points) VALUES (3, 75.0, '2025-02-01',25);

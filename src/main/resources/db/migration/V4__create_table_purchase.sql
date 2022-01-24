CREATE TABLE purchase(
    id INT auto_increment PRIMARY KEY,
    customer_id INT NOT NULL,
    nfe varchar(255),
    price DECIMAL(15,2) NOT NULL,
    created_at DATETIME NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);

CREATE TABLE purchase_book(
    purchase_id INT NOT NULL,
    book_id INT NOT NULL,
    FOREIGN KEY (purchase_id) REFERENCES purchase(id),
    FOREIGN KEY (book_id) REFERENCES book(id),
    PRIMARY KEY(purchase_id, book_id)
);
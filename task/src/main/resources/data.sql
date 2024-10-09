DELETE FROM product;
DELETE FROM category;

INSERT INTO category (id, name) VALUES (1, 'fruit');
INSERT INTO product (id, name, price, category_id) VALUES (11, 'apple 123', '0.5', 1);
INSERT INTO product (id, name, price, category_id) VALUES (12, 'orange', '0.25', 1);
INSERT INTO product (id, name, price, category_id) VALUES (13, 'strawberry', '0.17', 1);

INSERT INTO category (id, name) VALUES (2, 'clothes');
INSERT INTO product (id, name, price, category_id) VALUES (21, 'socks', '20.01', 2);
INSERT INTO product (id, name, price, category_id) VALUES (22, 't-shirt', '25.11', 2);

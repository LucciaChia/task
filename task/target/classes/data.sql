DELETE FROM product;
DELETE FROM category;

INSERT INTO category (uuid, name) VALUES (1, 'fruit');
INSERT INTO product (uuid, name, price, category_uuid) VALUES (11, 'apple', '0.5', 1);
INSERT INTO product (uuid, name, price, category_uuid) VALUES (12, 'orange', '0.25', 1);
INSERT INTO product (uuid, name, price, category_uuid) VALUES (13, 'strawberry', '0.17', 1);

INSERT INTO category (uuid, name) VALUES (2, 'clothes');
INSERT INTO product (uuid, name, price, category_uuid) VALUES (21, 'socks', '20.01', 2);
INSERT INTO product (uuid, name, price, category_uuid) VALUES (22, 't-shirt', '25.11', 2);

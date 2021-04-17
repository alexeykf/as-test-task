INSERT INTO customers
VALUES ('b8fa0a16-bca9-4491-8b01-b7164250f539', 'Victoria Jones', null, '2021-04-16T07:17:13.000000', null);
INSERT INTO products (id, customer_id, title, description, price, created_at)
VALUES ('a0d000a8-59cb-452f-b6f2-c8513200c6cb', 'b8fa0a16-bca9-4491-8b01-b7164250f539', 'First product',
        'Description of First product', '120.50', '2021-04-16T07:17:13.000000');
INSERT INTO products (id, customer_id, title, description, price, created_at)
VALUES ('63e9799f-2333-419c-87c6-55c91dcb9450', 'b8fa0a16-bca9-4491-8b01-b7164250f539', 'Second product',
        'Description of Second product', '120.50', '2021-04-16T07:17:15.000000');
INSERT INTO products (id, customer_id, title, description, price, created_at, deleted_at)
VALUES ('875fc52a-e1c3-4eb8-b816-faf45a8d96b8', 'b8fa0a16-bca9-4491-8b01-b7164250f539', 'Third product',
        'Description of Third product', '120.50', '2021-04-16T07:17:16.000000', '2021-04-16T07:17:17.000000');

INSERT INTO customers
VALUES ('7ee1993c-c53f-47bc-8e6e-a4892d7039ee', 'REMOVED', '2021-04-16T07:17:13.000000', '2021-04-16T07:17:13.000000',
        null);
INSERT INTO products (id, customer_id, title, description, price, created_at)
VALUES ('f808f5c6-5289-422e-8db2-44d45071ad6e', '7ee1993c-c53f-47bc-8e6e-a4892d7039ee', 'Second product',
        'Description of Second product', '120.50', '2021-04-16T07:17:15.000000');

INSERT INTO customers
VALUES ('5fb25077-f1d1-4537-8da2-ad6e34db17af', 'Another user', null, '2021-04-16T07:17:13.000000',
        null);
INSERT INTO products (id, customer_id, title, description, price, created_at, deleted_at)
VALUES ('246ecc5b-379e-4e08-b3ef-5a1e5deeeaef', '5fb25077-f1d1-4537-8da2-ad6e34db17af', 'Second product',
        'Description of Second product', '120.50', '2021-04-16T07:17:15.000000', '2021-04-16T07:17:15.000000');

INSERT INTO customers
VALUES ('6d4bfbd9-01db-4d76-95b7-a7e06bac1aed', 'Jennifer Martinez', null, '2021-04-16T07:17:13.000000',
        null);
INSERT INTO products (id, customer_id, title, description, price, created_at, deleted_at)
VALUES ('6352e702-086b-43fa-8a58-c0be80dce114', '6d4bfbd9-01db-4d76-95b7-a7e06bac1aed', 'New Product', null, '120.50', '2021-04-16T07:17:15.000000', null);

INSERT INTO customers
VALUES ('007da60a-4d1a-4db6-b577-cec61e3f8314', 'John Doe', null, '2021-04-16T07:17:13.000000', null);
INSERT INTO products (id, customer_id, title, description, price, created_at)
VALUES ('5fb25077-f1d1-4537-8da2-ad6e34db17af', '007da60a-4d1a-4db6-b577-cec61e3f8314', 'Product',
        null, '120.50', '2021-04-16T07:17:15.000000');

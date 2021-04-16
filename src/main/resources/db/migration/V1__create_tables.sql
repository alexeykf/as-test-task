CREATE TABLE IF NOT EXISTS customers
(
    id                UUID PRIMARY KEY,
    title             VARCHAR(255) NOT NULL,
    deleted_at        TIMESTAMP,
    created_at        TIMESTAMP NOT NULL,
    modified_at       TIMESTAMP
);

CREATE INDEX idx_customers_created_at ON customers (created_at) WHERE deleted_at IS NULL;

CREATE TABLE IF NOT EXISTS products
(
    id                UUID PRIMARY KEY,
    customer_id       UUID NOT NULL,
    title             VARCHAR(255) NOT NULL,
    description       VARCHAR(1024) NOT NULL,
    price             DECIMAL(10, 2),
    deleted_at        TIMESTAMP,
    created_at        TIMESTAMP NOT NULL,
    modified_at       TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers (id)
);

CREATE INDEX idx_products_created_at ON products (created_at) WHERE deleted_at IS NULL;

CREATE TABLE tasks (
                       id SERIAL PRIMARY KEY,
                       idempotency_key VARCHAR(255) UNIQUE NOT NULL,
                       name VARCHAR(255),
                       duration BIGINT,
                       status VARCHAR(50),
                       result TEXT
);
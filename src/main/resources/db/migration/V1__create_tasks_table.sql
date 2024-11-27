CREATE TABLE tasks (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255),
                       duration BIGINT,
                       status VARCHAR(50),
                       result TEXT
);
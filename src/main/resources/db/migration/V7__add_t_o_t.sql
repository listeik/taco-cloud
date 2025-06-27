CREATE TABLE taco_order_tacos (
    taco_order_id BIGINT NOT NULL,
    taco_id BIGINT NOT NULL,
    PRIMARY KEY (taco_order_id, taco_id),
    FOREIGN KEY (taco_order_id) REFERENCES taco_order(id),
    FOREIGN KEY (taco_id) REFERENCES taco(id)
);
ALTER TABLE taco_order
ADD COLUMN user_id BIGINT NOT NULL;

ALTER TABLE taco_order
ADD CONSTRAINT fk_taco_order_user
FOREIGN KEY (user_id) REFERENCES users(id);
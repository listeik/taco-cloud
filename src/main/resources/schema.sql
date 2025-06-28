-- Отключаем проверку внешних ключей для безопасной очистки
SET session_replication_role = replica;

-- Удаляем схему public и все её объекты (CASCADE принудительно удаляет зависимости)
DROP SCHEMA IF EXISTS public CASCADE;

-- Создаем схему заново
CREATE SCHEMA public;

-- Даем права (замените 'tacocloud' на вашего пользователя)
GRANT ALL ON SCHEMA public TO tacocloud;

-- Включаем проверку внешних ключей обратно
SET session_replication_role = DEFAULT;

CREATE TABLE Taco_Order (
    id SERIAL PRIMARY KEY,
    delivery_Name varchar(50) not null,
    delivery_Street varchar(50) not null,
    delivery_City varchar(50) not null,
    delivery_State varchar(2) not null,
    delivery_Zip varchar(10) not null,
    cc_number varchar(16) not null,
    cc_expiration varchar(5) not null,
    cc_cvv varchar(3) not null,
    placed_at timestamp not null
);

CREATE TABLE Taco (
    id SERIAL PRIMARY KEY,
    name varchar(50) not null,
    taco_order bigint ,
    taco_order_key bigint ,
    created_at timestamp not null,
    FOREIGN KEY (taco_order) REFERENCES Taco_Order(id)
);

CREATE TABLE Ingredient (
    id VARCHAR(4) PRIMARY KEY,
    name varchar(25) not null,
    type varchar(10) not null
);

CREATE TABLE Ingredient_Ref (
    ingredient varchar(4) not null,
    taco bigint not null,
    taco_key bigint not null,
    FOREIGN KEY (ingredient) REFERENCES Ingredient(id),
    FOREIGN KEY (taco) REFERENCES Taco(id)
);
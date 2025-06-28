-- Очистка данных в правильном порядке (с учетом foreign keys)
DELETE FROM Ingredient_Ref;
DELETE FROM Taco;
DELETE FROM Taco_Order;
DELETE FROM Ingredient;

-- Вставка ингредиентов
INSERT INTO Ingredient (id, name, type) VALUES
('FLTO', 'Flour Tortilla', 'WRAP'),
('COTO', 'Corn Tortilla', 'WRAP'),
('GRBF', 'Ground Beef', 'PROTEIN'),
('CARN', 'Carnitas', 'PROTEIN'),
('TMTO', 'Diced Tomatoes', 'VEGGIES'),
('LETC', 'Lettuce', 'VEGGIES'),
('CHED', 'Cheddar', 'CHEESE'),
('JACK', 'Monterrey Jack', 'CHEESE'),
('SLSA', 'Salsa', 'SAUCE'),
('SRCR', 'Sour Cream', 'SAUCE');
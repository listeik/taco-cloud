-- Очистка таблиц (в обратном порядке из-за foreign keys)
TRUNCATE TABLE Ingredient_Ref CASCADE;
TRUNCATE TABLE Taco CASCADE;
TRUNCATE TABLE Taco_Order CASCADE;
TRUNCATE TABLE Ingredient CASCADE;

-- Наполнение ингредиентов
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
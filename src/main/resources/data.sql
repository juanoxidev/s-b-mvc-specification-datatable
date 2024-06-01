-- Insertando datos en la tabla Estado
INSERT INTO estado (detalle, activo) VALUES ('Activo', true);
INSERT INTO estado (detalle, activo) VALUES ('Inactivo', false);
INSERT INTO estado (detalle, activo) VALUES ('Pendiente', true);

-- Insertando datos en la tabla Persona
INSERT INTO persona (nombre, cargo, salario, estado_id) VALUES ('Juan Pérez', 'Desarrollador', 50000.00, 1);
INSERT INTO persona (nombre, cargo, salario, estado_id) VALUES ('María Gómez', 'Analista', 45000.00, 1);
INSERT INTO persona (nombre, cargo, salario, estado_id) VALUES ('Carlos López', 'Gerente', 70000.00, 2);
INSERT INTO persona (nombre, cargo, salario, estado_id) VALUES ('Ana Torres', 'Diseñadora', 40000.00, 3);
INSERT INTO persona (nombre, cargo, salario, estado_id) VALUES ('Luis Martínez', 'Tester', 35000.00, 1);
INSERT INTO persona (nombre, cargo, salario, estado_id) VALUES ('Laura Sánchez', 'Scrum Master', 60000.00, 1);
INSERT INTO persona (nombre, cargo, salario, estado_id) VALUES ('David Rodríguez', 'Product Owner', 65000.00, 2);
INSERT INTO persona (nombre, cargo, salario, estado_id) VALUES ('Sara Fernández', 'Desarrolladora', 50000.00, 3);
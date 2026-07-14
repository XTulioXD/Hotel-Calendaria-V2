--Tabla de Clientes
CREATE TABLE IF NOT EXISTS cliente (
	nombre VARCHAR(100) NOT NULL,
	dni VARCHAR(8) PRIMARY KEY,
	cantidad_personas INT NOT NULL 
);

--Tabla de Empleados
CREATE TABLE IF NOT EXISTS empleado (
    dni VARCHAR(8) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    cargo VARCHAR(50) NOT NULL    
);

--Tabla de Tipo Habitacion
CREATE TABLE IF NOT EXISTS tipo_habitacion(
	nombre_tipo VARCHAR(50) PRIMARY KEY,
	precio_base DOUBLE PRECISION
);
INSERT INTO tipo_habitacion (nombre_tipo, precio_base) VALUES 
('Simple',85),('VIP',195);


--Tabla de Habitaciones
CREATE TABLE IF NOT EXISTS habitacion (
    numero INT PRIMARY KEY,
    capacidad INT NOT NULL,
    disponible BOOLEAN NOT NULL DEFAULT TRUE,
	aire_acondicionado BOOLEAN,
	tipo VARCHAR (50) NOT NULL,

	CONSTRAINT fk_tipoHabitacion FOREIGN KEY (tipo) REFERENCES tipo_habitacion(nombre_tipo)
);

--Tabla de Pago
CREATE TABLE IF NOT EXISTS pago(
	id_pago SERIAL PRIMARY KEY,
	monto DOUBLE PRECISION
);

--Tabla de Reservas
CREATE TABLE IF NOT EXISTS reserva (
    id_reserva SERIAL PRIMARY KEY,
    cliente_dni VARCHAR(8) REFERENCES cliente(dni),
    empleado_dni VARCHAR(8) REFERENCES empleado(dni),
    habitacion_numero INT REFERENCES habitacion(numero),
    dias INT NOT NULL,
    pago_id SERIAL REFERENCES pago(id_pago)
);
/*
--Insertamos las habitaciones iniciales para que no empiece vacío
INSERT INTO habitacion (numero, tipo, capacidad, disponible) VALUES 
(101, 'Simple', 2, true), (102, 'Simple', 2, true), (103, 'Simple', 2, true), (104, 'Simple', 2, true), (105, 'Simple', 2, true),
(201, 'VIP', 4, true), (202, 'VIP', 4, true), (203, 'VIP', 4, true), (204, 'VIP', 4, true), (205, 'VIP', 4, true)
ON CONFLICT (numero) DO NOTHING;

--Insertamos los empleados iniciales correspondientes a tu código
INSERT INTO empleado (nombre, dni, cargo) VALUES
('Carlos Trujillo', '71223344', 'Recepcionista'),
('Diego Asencios', '73445566', 'Recepcionista'),
('Gian Salinas', '75667788', 'Recepcionista')
ON CONFLICT (dni) DO NOTHING;	
*/
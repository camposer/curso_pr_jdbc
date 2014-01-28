CREATE TABLE persona
(
	id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	nombre VARCHAR(50) NOT NULL,
	apellido varchar(50) NOT NULL,
	fecha_nacimiento DATE,
	altura FLOAT
);

INSERT INTO  persona ( nombre,apellido, fecha_nacimiento, altura)
VALUES ('Juan','Perez','1980-01-03',180);
INSERT INTO  persona ( nombre,apellido, fecha_nacimiento, altura)
VALUES ('Maria','Gonzalez','1970-04-20',160);
INSERT INTO  persona ( nombre,apellido, fecha_nacimiento, altura)
VALUES ('Pedro','Garcia','1971-05-18',170);

SELECT * FROM persona;

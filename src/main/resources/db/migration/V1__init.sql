CREATE TABLE colegio(
  id serial,
  nombreC VARCHAR(45) NOT NULL,
  direccion VARCHAR(45) NULL,
  PRIMARY KEY (id)
  );

CREATE TABLE estudiante(
  id serial,
  nombreE VARCHAR(45) NOT NULL,
  apellido VARCHAR(45) NULL,
  colegio_id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (colegio_id) REFERENCES colegio(id)
  );

CREATE TABLE asignaturas(
    id serial,
    descricion VARCHAR(45) NOT NULL,
    estudiante_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (estudiante_id) REFERENCES estudiante(id)
    );
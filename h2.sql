CREATE SCHEMA transmi AUTHORIZATION rodack;

SET SCHEMA transmi;

-- -----------------------------------------------------
-- Table CONVENIO
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS CONVENIO(
                         id INT NOT NULL,
                         empresa VARCHAR(45) NOT NULL,
                         numero_convenio INT NOT NULL,
                         PRIMARY KEY (id));

-- -----------------------------------------------------
-- Table VEHICULO
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS VEHICULO(
                                       id INT NOT NULL,
                                       palca VARCHAR(45) NOT NULL,
                                       capacidad INT NOT NULL,
                                       color VARCHAR(45) NOT NULL,
                                       PRIMARY KEY (id));

-- -----------------------------------------------------
-- Table PASAJERO
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS PASAJERO(
                         id INT NOT NULL,
                         nombres VARCHAR(45) NOT NULL,
                         edad VARCHAR(45) NOT NULL,
                         genero VARCHAR(1) NOT NULL,
                         PRIMARY KEY (id));

-- -----------------------------------------------------
-- Table RUTA
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS RUTA(
                                   id INT NOT NULL,
                                   nombre VARCHAR(45) NOT NULL,
                                   numero VARCHAR(45) NOT NULL,
                                   inicio DATE NOT NULL,
                                   fin DATE NOT NULL,
                                   PRIMARY KEY (id));

-- -----------------------------------------------------
-- Table TARJETA
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS TARJETA (
                                       id INT NOT NULL,
                                       personalizado VARCHAR(1) NOT NULL,
                                       saldo DECIMAL(10) NOT NULL,
                                       PASAJERO_id INT NOT NULL,
                                       CONVENIO_id INT NOT NULL,
                                       PRIMARY KEY (id),
                                       CONSTRAINT fk_TARJETA_PASAJERO
                                           FOREIGN KEY (PASAJERO_id)
                                               REFERENCES PASAJERO (id)
                                               ON DELETE NO ACTION
                                               ON UPDATE NO ACTION,
                                       CONSTRAINT fk_TARJETA_CONVENIO1
                                           FOREIGN KEY (CONVENIO_id)
                                               REFERENCES CONVENIO (id)
                                               ON DELETE NO ACTION
                                               ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table VIAJE
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS VIAJE (
                                     id INT NOT NULL,
                                     distancia DECIMAL(10) NOT NULL,
                                     TARJETA_id INT NOT NULL,
                                     VEHICULO_id INT NOT NULL,
                                     PRIMARY KEY (id, distancia),
                                     CONSTRAINT fk_VIAJE_TARJETA1
                                         FOREIGN KEY (TARJETA_id)
                                             REFERENCES TARJETA (id)
                                             ON DELETE NO ACTION
                                             ON UPDATE NO ACTION,
                                     CONSTRAINT fk_VIAJE_VEHICULO1
                                         FOREIGN KEY (VEHICULO_id)
                                             REFERENCES VEHICULO (id)
                                             ON DELETE NO ACTION
                                             ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table ESTACION
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ESTACION(
                                       id INT NOT NULL,
                                       nombre VARCHAR(45) NOT NULL,
                                       tiene_taquilla VARCHAR(1) NOT NULL,
                                       ubicacion VARCHAR(45) NOT NULL,
                                       sector VARCHAR(45) NOT NULL,
                                       VIAJE_id INT NOT NULL,
                                       VIAJE_distancia DECIMAL(10) NOT NULL,
                                       RUTA_id INT NOT NULL,
                                       PRIMARY KEY (id),
                                       CONSTRAINT fk_ESTACION_VIAJE1
                                           FOREIGN KEY (VIAJE_id)
                                               REFERENCES VIAJE (id)
                                               ON DELETE NO ACTION
                                               ON UPDATE NO ACTION,
                                       CONSTRAINT fk_ESTACION_RUTA1
                                           FOREIGN KEY (RUTA_id)
                                               REFERENCES RUTA (id)
                                               ON DELETE NO ACTION
                                               ON UPDATE NO ACTION);










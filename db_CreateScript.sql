use db_springboot;

CREATE TABLE IF NOT EXISTS `db_springboot`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(60) NOT NULL,
  `enabled` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC));
                                                    /* Password son en ambos '12345' encriptados por la clase BCryptPasswordEncoder en java */
Insert into users (`username`, `password`, `enabled` ) values ('mayito','$2a$10$Mz1vulRz/DafMXHiOJc6GefjlBz5JG3SULR.RIDnsKvCBFDV1DYdS',1);
Insert into users (`username`, `password`, `enabled` ) values ('admin' ,'$2a$10$VAfnraJ2Srvsz9rOXsWqNev3Bkcm/3h8PugwfJnIfEuR97B4h/mcC',1);
  
  CREATE TABLE IF NOT EXISTS `db_springboot`.`authorities` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `authority` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `user_id_authority_unique` (`user_id` ASC, `authority` ASC),
  CONSTRAINT `fk_authorities_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `db_springboot`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
COMMENT = 'tabla para los roles';

Insert into authorities (`user_id`, `auhtority` ) values (1,'ROLE_USER');
Insert into authorities (`user_id`, `auhtority` ) values (2,'ROLE_USER');
Insert into authorities (`user_id`, `auhtority` ) values (2,'ROLE_ADMIN');


/*Creating tables if they don't exist*/
CREATE TABLE IF NOT EXISTS `db_springboot`.`clientes` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `apellido` VARCHAR(90) NULL,
  `email` VARCHAR(50) NULL,
  `create_at` DATETIME NULL,
  `foto` VARCHAR(90) NULL,
  PRIMARY KEY (`id`)); 
  
/*Populate tables*/
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Andres', 'Guzman', 'profesor@bolsadeideas.com', NOW(), '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'John', 'Doe', 'john.doe@gmail.com', NOW(), '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Pepe', 'Pecas', 'ppecas@bolsadeideas.com', NOW(), '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Ale', 'Vargas', 'avargas@gmail.com', NOW(), '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Emma', 'Stone', 'estone@bolsadeideas.com', NOW(), '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Grace', 'Mendez', 'gmendez@gmail.com', NOW(), '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Jaques', 'Sarmiento', 'jsarmiento@bolsadeideas.com', NOW(), '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Joe', 'Slim', 'jslim@gmail.com', NOW(), '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Naxx', 'Ramas', 'nramas@bolsadeideas.com', NOW(), '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Omar', 'Morales', 'omorales@gmail.com', NOW(), '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Mario', 'Serrano', 'mserrano@bolsadeideas.com', NOW(), '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Andres', 'Guzman', 'aguzman@gmail.com', NOW(), '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Pepe', 'Nava', 'pnava@gmail.com', NOW(), '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Adriana', 'Arreola', 'aarreola@gmail.com', NOW(), '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Gabriela', 'Miranda', 'gmiranda@gmail.com', NOW(), '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Ernestina', 'Macias', 'emacias@gmail.com', NOW(), '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Lalo', 'Cota', 'lcota@gmail.com', NOW(), '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Graciano', 'Sifuentes', 'gsifuentes@gmail.com', NOW(), '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Tona', 'Cervantes', 'tcervantes@gmail.com', NOW(), '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Gilberto', 'Atilano', 'gatilano@gmail.com', NOW(), '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Janeth', 'Gomez', 'jgomez@gmail.com', NOW(), '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Isidoro', 'Smith', 'ismith@gmail.com', NOW(), '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Richard', 'Tex', 'rtex@gmail.com', NOW(), '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Benji', 'Price', 'bprice@gmail.com', NOW(), '');

/* Populate tabla productos */
  CREATE TABLE IF NOT EXISTS `db_springboot`.`productos` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `precio` DOUBLE NOT NULL,  
  `create_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`));
  
INSERT INTO productos (nombre, precio, create_at) VALUES('Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Camara digital DSC-W320B', 123490, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Apple iPod shuffle', 1499990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Notebook Z110', 37990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Hewlett Packard Multifuncional F2280', 69990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Bianchi Bicicleta Aro 26', 69990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Mica Comoda 5 Cajones', 299990, NOW());

/* Creamos algunas facturas */
CREATE TABLE IF NOT EXISTS `db_springboot`.`facturas` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NULL,
  `observacion` VARCHAR(90) NULL,  
  `cliente_id` BIGINT NOT NULL,  
  `create_at` DATETIME NULL,
  PRIMARY KEY (`id`));
  
INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura equipos de oficina', null, 1, NOW());


  CREATE TABLE IF NOT EXISTS `db_springboot`.`facturas_items` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `factura_id` BIGINT NULL,  
  `producto_id` BIGINT NOT NULL,  
  `cantidad` INT NOT NULL ,
  PRIMARY KEY (`id`));
  
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 1);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(2, 1, 4);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 5);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 7);

  
  
INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura Bicicleta', 'Alguna nota importante!', 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(3, 2, 6);
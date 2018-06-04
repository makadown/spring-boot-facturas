  
/*Populate tables*/
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Andres', 'Guzman', 'profesor@bolsadeideas.com', '2017-08-28', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'John', 'Doe', 'john.doe@gmail.com', '2017-08-28', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Pepe', 'Pecas', 'ppecas@bolsadeideas.com', '2017-08-28', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Ale', 'Vargas', 'avargas@gmail.com', '2017-08-28', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Emma', 'Stone', 'estone@bolsadeideas.com', '2017-08-28', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Grace', 'Mendez', 'gmendez@gmail.com', '2017-08-28', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Jaques', 'Sarmiento', 'jsarmiento@bolsadeideas.com', '2017-08-28', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Joe', 'Slim', 'jslim@gmail.com', '2017-08-28', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Naxx', 'Ramas', 'nramas@bolsadeideas.com', '2017-08-28', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Omar', 'Morales', 'omorales@gmail.com', '2017-08-28', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Mario', 'Serrano', 'mserrano@bolsadeideas.com', '2017-08-28', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Andres', 'Guzman', 'aguzman@gmail.com', '2017-08-28', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Pepe', 'Nava', 'pnava@gmail.com', '2017-08-28', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Adriana', 'Arreola', 'aarreola@gmail.com', '2017-08-28', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Gabriela', 'Miranda', 'gmiranda@gmail.com', '2017-08-28', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Ernestina', 'Macias', 'emacias@gmail.com', '2017-08-28', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Lalo', 'Cota', 'lcota@gmail.com', '2017-08-28', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Graciano', 'Sifuentes', 'gsifuentes@gmail.com', '2017-08-28', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Tona', 'Cervantes', 'tcervantes@gmail.com', '2017-08-28', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Gilberto', 'Atilano', 'gatilano@gmail.com', '2017-08-28', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Janeth', 'Gomez', 'jgomez@gmail.com', '2017-08-28', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Isidoro', 'Smith', 'ismith@gmail.com', '2017-08-28', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Richard', 'Tex', 'rtex@gmail.com', '2017-08-28', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) values ( 'Benji', 'Price', 'bprice@gmail.com', '2017-08-28', ''); 
  
INSERT INTO productos (nombre, precio, create_at) VALUES('Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Camara digital DSC-W320B', 123490, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Apple iPod shuffle', 1499990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Notebook Z110', 37990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Hewlett Packard Multifuncional F2280', 69990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Bianchi Bicicleta Aro 26', 69990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Mica Comoda 5 Cajones', 299990, NOW());
 
  
INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura equipos de oficina', null, 1, NOW());  
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 1);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(2, 1, 4);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 5);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 7);
  
  
INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura Bicicleta', 'Alguna nota importante!', 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(3, 2, 6);
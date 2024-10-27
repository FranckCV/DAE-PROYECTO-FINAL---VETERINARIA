create table MASCOTA (
  id               int4 not null, 
  nombre           varchar(150) not null, 
  fecha_nacimiento date not null, 
  altura           numeric(6, 2) not null, 
  peso             numeric(6, 2) not null, 
  notaAdicional    text, 
  sexo             bool not null, 
  esterilizado         bool not null, 
  desparasitado    bool not null, 
  condicion     char(1), 
  raza_id          int4 not null, 
  especie_id 				int4 not null,
  fecha_registro 		date not null, 
  estado						boolean not null,
  primary key (id));
  
create table TIPO_EXAMEN (
  id     int4 not null, 
  nombre varchar(255) not null, 
  primary key (id));
  
create table RAZA (
  id         int4 not null, 
  nombre     varchar(150) not null, 
  especie_id int4 not null, 
  primary key (id));
  
create table ESPECIE (
  id          int4 not null, 
  nombre      varchar(150) not null, 
  primary key (id));
  
create table MEDICO (
  dni             char(8) not null, 
  nombres         varchar(150) not null, 
  apePaterno      varchar(200) not null, 
  apeMaterno      varchar(200) not null, 
  sexo            bool not null, 
  disponibilidad  bool not null, 
  vigencia        bool not null, 
  especialidad_id int4 not null, 
  primary key (dni));
  
create table ESTADO_CITA (
  id            int4 not null, 
  nombre_estado varchar(150) not null, 
  primary key (id));
  
create table MEDICAMENTO (
  id                  int4 not null, 
  nombre              varchar(255) not null, 
  costo               numeric(9, 2) not null, 
  stock               int4 not null, 
  presentacion        varchar(15) not null,
  vigencia 						bool not null,
  tipo_medicamento_id int4 not null, 
  primary key (id));
  
create table EXAMEN (
  id             int4 not null, 
  fecha_examen   date not null, 
  hora_examen    time not null, 
  resultados     text not null, 
  cita_id        int4 not null, 
  tipo_examen_id int4 not null, 
  primary key (id));
  
create table SERVICIO (
  id           int4 not null, 
  nom_servicio varchar(100) not null, 
  descripcion  text not null, 
  costo        numeric(10, 2) not null, 
  primary key (id));
  
create table DUEniO (
  numDoc         varchar(15) not null, 
  nombres     varchar(150) not null, 
  apePaterno  varchar(200) not null, 
  apeMaterno  varchar(200) not null, 
  telefono    varchar(15) not null, 
  telefonoAlt varchar(15), 
  correo      varchar(150), 
  direccion   varchar(255) not null, 
  sexo        bool not null, 
  codtipoD 		int not null,
  
  primary key (numDoc));
  
  
create table tipo_cliente(
codtipo int primary key,
nombre varchar (30)	
);
  
create table ESPECIALIDAD (
  id       int4 not null, 
  nom_especialidad   varchar(100) not null, 
  vigencia bool not null, 
  primary key (id));
  
create table CITA (
  id                int4 not null, 
  estado_cita_id    int4 not null, 
  fecha_cita        date not null, 
  observacion       text, 
  CUSTODIADUEniOdni  char(8) not null, 
  CUSTODIAMASCOTAid int4 not null, 
  primary key (id));
  
create table TIPO_MEDICAMENTO (
  id     int4 not null, 
  nombre varchar(255) not null, 
  primary key (id));
  
create table DETALLE_CITA (
  cita_id                  int4 not null, 
  detalle_servicio_med_dni char(8) not null, 
  detalle_servicio_serv_id int4 not null, 
  horaEntrada              time not null, 
  horaSalida               time not null, 
  nota_adcional            text not null, 
  primary key (cita_id, 
  detalle_servicio_med_dni, 
  detalle_servicio_serv_id));
  
create table COMPROBANTE (
  Tipo         char(1) not null, 
  serie_numero char(10) not null, 
  monto_total  numeric(10, 2) not null, 
  fecha        date not null, 
  CITAid       int4 not null, 
  primary key (Tipo, 
  serie_numero));
  
comment on column COMPROBANTE.Tipo is '"B" para boleta
"F" para factura';

create table VACUNA (
  id             int4 not null, 
  nombre         varchar(150) not null, 
  dosis_x_kgpeso numeric(10, 2) not null, 
  especie_id     int4 not null, 
  primary key (id));
  
create table DETALLES_VACUNACION (
  vacuna_id    int4 not null, 
  mascota_id   int4 not null, 
  fecha_vacuna date not null, 
  observacion  text, 
  primary key (vacuna_id, 
  mascota_id));
  
create table CUSTODIA (
  DUEniOdni       char(8) not null, 
  MASCOTAid      int4 not null, 
  fecha_adopción int4 not null, 
  primary key (DUEniOdni, 
  MASCOTAid));
  
create table DETALLE_SERVICIO (
  medico_dni    char(8) not null, 
  servicio_id   int4 not null, 
  vigencia      bool not null,
  primary key (medico_dni, 
  servicio_id));
  
create table DETALLE_MEDICAMENTO (
  medicamento_id               int4 not null, 
  detalle_cita_id              int4 not null, 
  detalle_servicio_medico_dni  char(8) not null, 
  detalle_servicio_servicio_id int4 not null, 
  dosis                        numeric(9, 2) not null, 
  indicacion                   text not null,
  cantidad                     int4 not null, 
  primary key (medicamento_id, 
  detalle_cita_id, 
  detalle_servicio_medico_dni, 
  detalle_servicio_servicio_id));
  
create table USUARIO (
  codUsuario int4 not null, 
  nomusuario varchar(30) not null, 
  estado     bool not null, 
  sexo       bool not null, 
  clave      varchar(100) not null, 
  nombres    varchar(50) not null, 
  apPaterno  varchar(40) not null, 
  apMaterno  varchar(40) not null, 
  cargo 		 char(1) not null,	
  primary key (codUsuario));
  
alter table RAZA add constraint FKRAZA702100 foreign key (especie_id) references ESPECIE (id);
  alter table MASCOTA add constraint FKMASCOTA643044 foreign key (raza_id) references RAZA (id);
alter table MEDICO add constraint FKMEDICO608762 foreign key (especialidad_id) references ESPECIALIDAD (id);
alter table EXAMEN add constraint FKEXAMEN691988 foreign key (tipo_examen_id) references TIPO_EXAMEN (id);
alter table CITA add constraint FKCITA623091 foreign key (estado_cita_id) references ESTADO_CITA (id);
alter table MEDICAMENTO add constraint FKMEDICAMENT299134 foreign key (tipo_medicamento_id) references TIPO_MEDICAMENTO (id);
alter table EXAMEN add constraint FKEXAMEN477804 foreign key (cita_id) references CITA (id);
alter table VACUNA add constraint FKVACUNA8255 foreign key (especie_id) references ESPECIE (id);
alter table DETALLES_VACUNACION add constraint FKDETALLES_V104108 foreign key (vacuna_id) references VACUNA (id);
alter table DETALLES_VACUNACION add constraint FKDETALLES_V108578 foreign key (mascota_id) references MASCOTA (id);
alter table DETALLE_CITA add constraint FKDETALLE_CI824652 foreign key (cita_id) references CITA (id);
alter table COMPROBANTE add constraint FKCOMPROBANT250686 foreign key (CITAid) references CITA (id);
alter table CUSTODIA add constraint FKCUSTODIA598083 foreign key (DUEniOdni) references DUEniO (dni);
alter table CUSTODIA add constraint FKCUSTODIA281978 foreign key (MASCOTAid) references MASCOTA (id);
alter table CITA add constraint FKCITA430728 foreign key (CUSTODIADUEniOdni, CUSTODIAMASCOTAid) references CUSTODIA (DUEniOdni, MASCOTAid);
alter table DETALLE_SERVICIO add constraint FKDETALLE_SE783847 foreign key (medico_dni) references MEDICO (dni);
alter table DETALLE_SERVICIO add constraint FKDETALLE_SE503336 foreign key (servicio_id) references SERVICIO (id);
alter table DETALLE_CITA add constraint FKDETALLE_CI861789 foreign key (detalle_servicio_med_dni, detalle_servicio_serv_id) references DETALLE_SERVICIO (medico_dni, servicio_id);
alter table DETALLE_MEDICAMENTO add constraint FKDETALLE_ME2678 foreign key (medicamento_id) references MEDICAMENTO (id);
alter table DETALLE_MEDICAMENTO add constraint FKDETALLE_ME638991 foreign key (detalle_cita_id, detalle_servicio_medico_dni, detalle_servicio_servicio_id) references DETALLE_CITA (cita_id, detalle_servicio_med_dni, detalle_servicio_serv_id);

ALTER TABLE duenio ADD CONSTRAINT fk_tipo_cliente FOREIGN KEY (codtipo) REFERENCES tipo_cliente (codtipo);
ALTER TABLE mascota ADD CONSTRAINT fk_especie FOREIGN KEY (especie_id) REFERENCES especie(id);

--------------------------------------------------------------------------------------------------------------------------------------------------



INSERT INTO usuario (codUsuario, nomUsuario, estado, sexo, clave, nombres, apPaterno, apMaterno, cargo) VALUES
(1, '123', TRUE, TRUE, '123', 'Francisco', 'Hernandez', 'Gomez','A'),
(2, 'lrodriguez', TRUE, FALSE, 'clave_segura2', 'Laura', 'Rodriguez', 'Perez','A'),
(3, 'jmendoza', FALSE, TRUE, 'clave_segura3', 'Javier', 'Mendoza', 'Ramirez','E'),
(4, 'ccruz', TRUE, FALSE, 'clave_segura4', 'Claudia', 'Cruz', 'Lopez','E'),
(5, 'rlopez', TRUE, TRUE, 'clave_segura5', 'Ricardo', 'Lopez', 'Diaz','V');

INSERT INTO ESPECIALIDAD (id, nom_especialidad, vigencia) VALUES 
    (1, 'Medicina General', TRUE),
    (2, 'Cirugía Veterinaria', TRUE),
    (3, 'Dermatología', TRUE),
    (4, 'Cardiología Veterinaria', TRUE),
    (5, 'Odontología Veterinaria', FALSE);

INSERT INTO MEDICO (dni, nombres, apePaterno, apeMaterno, sexo, disponibilidad, vigencia, especialidad_id) 
VALUES 
    ('12345678', 'Juan', 'Pérez', 'García',        TRUE, TRUE, TRUE, 1),
    ('23456789', 'Carlos', 'Gómez', 'Sánchez',     TRUE, FALSE, FALSE, 5),
    ('34567890', 'Pedro', 'Hernández', 'Ramírez',  TRUE, FALSE, TRUE, 5),
    ('87654321', 'Ana', 'López', 'Martínez',       FALSE, FALSE, TRUE, 2), 
    ('98765432', 'Lucía', 'Rodríguez', 'Morales',  FALSE, TRUE, TRUE, 4);

INSERT INTO SERVICIO (id, nom_servicio, descripcion, costo) VALUES
(1, 'Consulta General', 'Consulta médica general para revisión de la mascota', 50.00),
(2, 'Vacunación', 'Aplicación de vacuna según el calendario de vacunación', 30.00),
(3, 'Desparasitación', 'Tratamiento para eliminar parásitos internos y externos', 40.00),
(4, 'Cirugía menor', 'Intervenciones quirúrgicas menores como esterilización', 200.00),
(5, 'Emergencia', 'Atención de emergencias veterinarias las 24 horas', 150.00);

INSERT INTO ESPECIE (id, nombre) VALUES (1, 'Perro');
INSERT INTO ESPECIE (id, nombre) VALUES (2, 'Gato');
INSERT INTO ESPECIE (id, nombre) VALUES (3, 'Caballo');
INSERT INTO ESPECIE (id, nombre) VALUES (4, 'Vaca');
INSERT INTO ESPECIE (id, nombre) VALUES (5, 'Oveja');

INSERT INTO RAZA (id, nombre, especie_id) VALUES (1, 'Labrador Retriever', 1);
INSERT INTO RAZA (id, nombre, especie_id) VALUES (2, 'Pastor Alemán', 1);
INSERT INTO RAZA (id, nombre, especie_id) VALUES (3, 'Siames', 2);
INSERT INTO RAZA (id, nombre, especie_id) VALUES (4, 'Persa', 2);
INSERT INTO RAZA (id, nombre, especie_id) VALUES (5, 'Pura Sangre', 3);
INSERT INTO RAZA (id, nombre, especie_id) VALUES (6, 'Percherón', 3);
INSERT INTO RAZA (id, nombre, especie_id) VALUES (7, 'Holstein', 4);
INSERT INTO RAZA (id, nombre, especie_id) VALUES (8, 'Angus', 4);
INSERT INTO RAZA (id, nombre, especie_id) VALUES (9, 'Merino', 5);
INSERT INTO RAZA (id, nombre, especie_id) VALUES (10, 'Suffolk', 5);

INSERT INTO VACUNA (id, nombre, dosis_x_kgpeso, especie_id) VALUES (1, 'Vacuna Antirrábica', 0.05, 1);
INSERT INTO VACUNA (id, nombre, dosis_x_kgpeso, especie_id) VALUES (2, 'Vacuna Parvovirus', 0.10, 1);
INSERT INTO VACUNA (id, nombre, dosis_x_kgpeso, especie_id) VALUES (3, 'Vacuna Triple Felina', 0.08, 2);
INSERT INTO VACUNA (id, nombre, dosis_x_kgpeso, especie_id) VALUES (4, 'Vacuna Influenza Equina', 0.15, 3);
INSERT INTO VACUNA (id, nombre, dosis_x_kgpeso, especie_id) VALUES (5, 'Vacuna Carbunclo', 0.20, 4);



INSERT INTO TIPO_EXAMEN (id, nombre) VALUES (1, 'Examen de sangre');
INSERT INTO TIPO_EXAMEN (id, nombre) VALUES (2, 'Examen de orina');
INSERT INTO TIPO_EXAMEN (id, nombre) VALUES (3, 'Examen fecal');
INSERT INTO TIPO_EXAMEN (id, nombre) VALUES (4, 'Radiografía');
INSERT INTO TIPO_EXAMEN (id, nombre) VALUES (5, 'Ecografía');
INSERT INTO TIPO_EXAMEN (id, nombre) VALUES (6, 'Electrocardiograma');
INSERT INTO TIPO_EXAMEN (id, nombre) VALUES (7, 'Prueba de alergias');
INSERT INTO TIPO_EXAMEN (id, nombre) VALUES (8, 'Examen oftalmológico');
INSERT INTO TIPO_EXAMEN (id, nombre) VALUES (9, 'Examen dermatológico');
INSERT INTO TIPO_EXAMEN (id, nombre) VALUES (10, 'Examen neurológico');
INSERT INTO TIPO_EXAMEN (id, nombre) VALUES (15, 'Tomografía');


INSERT INTO ESTADO_CITA (id, nombre_estado) VALUES
(1, 'Pendiente'),
(2, 'Por confirmar'),
(3, 'Cancelada'),
(4, 'Reprogramada'),
(6, 'Finalizada'),
(7, 'No asistió');

INSERT INTO TIPO_MEDICAMENTO (id, nombre) VALUES 
    (1, 'Antibiótico'),
    (2, 'Analgésico'),
    (3, 'Antiparasitario'),
    (4, 'Antiinflamatorio'),
    (5, 'Vacuna');

INSERT INTO MEDICAMENTO (id, nombre, costo, stock, presentacion, tipo_medicamento_id) VALUES 
    (1, 'Amoxicilina 250mg', 15.50, 50, 'Cápsula', 1),
    (2, 'Meloxicam 0.5mg', 25.00, 30, 'Comprimido', 2),
    (3, 'Drontal Plus 150mg', 40.00, 100, 'Tableta', 3),
    (4, 'Carprofeno 25mg', 35.00, 20, 'Comprimido', 4),
    (5, 'Vacuna Triple Felina', 55.00, 10, 'Inyección', 5),
    (6, 'Ivermectina 1%', 12.00, 80, 'Inyección', 3),
    (7, 'Ketoprofeno 10mg', 20.00, 60, 'Inyección', 4),
    (8, 'Vacuna Antirrábica', 60.00, 15, 'Inyección', 5),
    (9, 'Tramadol 50mg', 30.00, 40, 'Tableta', 2),
    (10, 'Doxiciclina 100mg', 22.00, 25, 'Cápsula', 1);
    
INSERT INTO DUEniO (numdoc, nombres, apePaterno, apeMaterno, telefono, telefonoAlt, correo, direccion, sexo, vigencia, codtipo) VALUES
    ('71234567', 'Carlos', 'García', 'Pérez', '987654321', '912345678', 'cgarcia@example.com', 'Av. Siempre Viva 123', true, true, 1),
    ('82765432', 'María', 'López', 'Sánchez', '976543210', NULL, 'mlopez@example.com', 'Jr. Los Pinos 456', false,true, 1),
    ('65432189', 'José', 'Martínez', 'Guzmán', '964321098', '941234567', NULL, 'Calle Las Rosas 789', true, true, 1),
    ('98651234', 'Ana', 'Rodríguez', 'Fernández', '932165478', NULL, 'arodriguez@example.com', 'Av. Las Palmeras 456', false, true,1),
    ('73512368', 'Luis', 'Paredes', 'Zapata', '911234569', '998877665', 'lparedes@example.com', 'Jr. Las Lomas 234', true, true, 1),
    ('84561234', 'Rosa', 'Chávez', 'Valverde', '945612378', NULL, 'rchavez@example.com', 'Calle Los Cedros 156', false,true, 1),
    ('78912345', 'Pedro', 'Cruz', 'Huaman', '987123654', '954321876', 'pcruz@example.com', 'Av. Perú 789', true,true, 2),
    ('62341234', 'Luisa', 'Ortiz', 'Pérez', '912345674', NULL, 'lortiz@example.com', 'Jr. San Juan 342', false, true, 3),
    ('98123456', 'Miguel', 'Alvarado', 'Reyes', '987412356', '943215678', 'malvarado@example.com', 'Av. Colonial 342', true,true, 2),
    ('81234567', 'Carmen', 'Salinas', 'Ríos', '965412378', '976543212', 'csalinas@example.com', 'Jr. Las Magnolias 765', false, true, 3);
    
INSERT INTO MASCOTA (id, nombre, fecha_nacimiento, altura, peso, notaAdicional, sexo, esterilizado, desparasitado, condicion, raza_id, especie_id, fecha_registro, estado) VALUES
    (1, 'Luna', '2020-05-15', 35.50, 12.30, 'Tiene mucha energía y es muy activa.', false, true, true, 'S', 1, 1, '2020-05-15', true),
    (2, 'Max', '2018-11-22', 45.20, 18.75, 'Le encanta correr largas distancias y es muy fuerte.', true, true, true, 'S', 2, 1, '2020-11-22', true),
    (3, 'Bella', '2019-09-30', 38.10, 15.60, 'Padece artritis crónica, bajo tratamiento.', false, false, true, 'C', 3, 2, '2021-10-07', true),
    (4, 'Simba', '2011-07-05', 50.00, 25.00, 'Un poco tímido al principio, pero muy juguetón.', true, false, false, 'S', 1, 1, '2020-12-07', true),
    (5, 'Rocky', '2017-03-01', 42.50, 20.40, 'Sufre de insuficiencia renal crónica, requiere dieta especial.', true, true, true, 'C', 2, 1, '2017-10-07', true),
    (6, 'Coco', '2022-01-10', 30.80, 10.20, 'Muy cariñosa con los niños y social.', false, true, true, 'S', 8, 4, '2022-01-10', true),
    (7, 'Nala', '2016-06-18', 37.00, 14.50, 'Cáncer avanzado, en tratamiento paliativo.', false, false, false, 'T',10, 5, '2016-06-18', true),
    (8, 'Charlie', '2020-12-09', 48.30, 22.10, 'Amigable con otras mascotas, le gusta nadar.', true, true, true, 'S', 2, 1, '2020-12-09', true),
    (9, 'Milo', '2019-02-25', 39.20, 16.80, 'Alergia crónica a ciertos alimentos.', true, false, true, 'C', 1, 1, '2019-02-25', true),
    (10, 'Chloe', '2015-10-28', 33.50, 11.90, 'Cáncer terminal, con pocos meses de vida.', false, true, true, 'T', 7, 4, '2015-10-28', true);

insert into tipo_cliente (codtipo, nombre) values ( 1, 'DNI'), (2, 'Pasaporte'), (3, 'Carnet de Extranjeria')
  


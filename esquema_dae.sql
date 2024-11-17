create table USUARIO (
  codUsuario int4 not null, 
  nomusuario varchar(30) not null unique, 
  estado     bool not null, 
  sexo       bool not null, 
  clave      varchar(100) not null, 
  nombres    varchar(50) not null, 
  apPaterno  varchar(40) not null, 
  apMaterno  varchar(40) not null, 
  cargo      char(1) not null, 
  primary key (codUsuario));


-- MEDICOS Y SERVICIOS

create table SERVICIO (
  id           int4 not null, 
  nom_servicio varchar(100) not null, 
  descripcion  text not null, 
  disponibilidad     bool not null, 
  costo        numeric(10, 2) not null, 
  primary key (id));

create table ESPECIALIDAD (
  id               int4 not null, 
  nom_especialidad varchar(100) not null, 
  disponibilidad   bool not null, 
  primary key (id));

create table MEDICO (
  id              int4 not null, 
  nombres         varchar(150) not null, 
  apePaterno      varchar(200) not null, 
  apeMaterno      varchar(200) not null, 
  doc_identidad   varchar(20) not null, 
  sexo            bool not null, 
  disponibilidad  bool not null, 
  vigencia        bool not null, 
  especialidad_id int4 not null, 
  primary key (id));

create table DETALLE_SERVICIO (
  servicio_id     int4 not null,
  medico_id        int4 not null,
  disponibilidad  bool not null,
  primary key (servicio_id, 
  medico_id));


-- DUEÑOS Y MASCOTAS

create table ESPECIE (
  id             int4 not null, 
  nombre         varchar(150) not null, 
  disponibilidad bool not null, 
  primary key (id));

create table RAZA (
  id             int4 not null, 
  nombre         varchar(150) not null, 
  especie_id     int4 not null, 
  disponibilidad bool not null, 
  primary key (id));

create table MASCOTA (
  id               int4 not null, 
  nombre           varchar(150) not null, 
  fecha_nacimiento date not null, 
  altura           numeric(6, 2) not null, 
  peso             numeric(6, 2) not null, 
  notaAdicional    text, 
  sexo             bool not null, 
  esterilizado     bool not null, 
  desparasitado    bool not null, 
  estado_salud     char(1), 
  vigencia         bool not null, 
  raza_id          int4 not null, 
  primary key (id));

create table DUEniO (
  id            int4 not null, 
  nombres       varchar(150) not null, 
  apePaterno    varchar(200) not null, 
  apeMaterno    varchar(200) not null, 
  doc_identidad varchar(20) not null, 
  telefono      varchar(15) not null, 
  telefonoAlt   varchar(15), 
  correo        varchar(150), 
  direccion     varchar(255) not null, 
  sexo          bool not null, 
  vigencia      bool not null, 
  primary key (id));

create table VACUNA (
  id             int4 not null, 
  nombre         varchar(150) not null, 
  dosis_x_kgpeso numeric(10, 2) not null, 
  especie_id     int4 not null, 
  disponibilidad bool not null, 
  primary key (id));

create table DETALLES_VACUNACION (
  vacuna_id    int4 not null, 
  mascota_id   int4 not null, 
  fecha_vacuna date not null, 
  observacion  text, 
  primary key (vacuna_id, 
  mascota_id));

create table CUSTODIA (
  MASCOTAid      int4 not null, 
  DUEniOid        int4 not null, 
  fecha_adopción date, 
  primary key (MASCOTAid, 
  DUEniOid));


-- CITAS

create table ESTADO_CITA (
  id            int4 not null, 
  nombre_estado varchar(150) not null, 
  primary key (id));

create table CITA (
  id                int4 not null, 
  estado_cita_id    int4 not null, 
  fecha_cita        date not null, 
  observacion       text, 
  CUSTODIAMASCOTAid int4 not null, 
  CUSTODIADUEniOid   int4, 
  primary key (id));
  
create table COMPROBANTE (
  Tipo         char(1) not null, 
  serie_numero char(10) not null, 
  monto_total  numeric(10, 2) not null, 
  fecha        date not null, 
  CITAid       int4 not null, 
  primary key (Tipo, 
  serie_numero));
comment on column COMPROBANTE.Tipo is '"B" para boleta "F" para factura';

create table DETALLE_CITA (
  cita_id                  int4 not null, 
  detalle_servicio_serv_id int4 not null, 
  detalle_servicio_med_id  int4 not null, 
  horaEntrada              time not null, 
  horaSalida               time not null, 
  nota_adicional           text not null, 
  primary key (cita_id, 
  detalle_servicio_serv_id, 
  detalle_servicio_med_id));


-- MEDICAMENTOS

create table TIPO_MEDICAMENTO (
  id      int4 not null, 
  nomtipo varchar(255) not null, 
  primary key (id));
  
create table MEDICAMENTO (
  id                  int4 not null, 
  nombre              varchar(255) not null, 
  costo               numeric(9, 2) not null, 
  stock               int4 not null, 
  presentacion        varchar(15) not null, 
  vigencia            bool not null, 
  tipo_medicamento_id int4 not null, 
  primary key (id));

create table DETALLE_MEDICAMENTO (
  medicamento_id               int4 not null, 
  detalle_cita_id              int4 not null, 
  detalle_servicio_servicio_id int4 not null, 
  detalle_servicio_medico_id   int4 not null, 
  dosis                        numeric(9, 2) not null, 
  indicacion                   text not null, 
  cantidad                     int4 not null, 
  primary key (medicamento_id, 
  detalle_cita_id, 
  detalle_servicio_servicio_id, 
  detalle_servicio_medico_id));


alter table RAZA add constraint FKRAZA702100 foreign key (especie_id) references ESPECIE (id);
alter table MASCOTA add constraint FKMASCOTA643044 foreign key (raza_id) references RAZA (id);
alter table MEDICO add constraint FKMEDICO608762 foreign key (especialidad_id) references ESPECIALIDAD (id);
alter table CITA add constraint FKCITA623091 foreign key (estado_cita_id) references ESTADO_CITA (id);
alter table MEDICAMENTO add constraint FKMEDICAMENT299134 foreign key (tipo_medicamento_id) references TIPO_MEDICAMENTO (id);
alter table VACUNA add constraint FKVACUNA8255 foreign key (especie_id) references ESPECIE (id);
alter table DETALLES_VACUNACION add constraint FKDETALLES_V104108 foreign key (vacuna_id) references VACUNA (id);
alter table DETALLES_VACUNACION add constraint FKDETALLES_V108578 foreign key (mascota_id) references MASCOTA (id);
alter table DETALLE_CITA add constraint FKDETALLE_CI824652 foreign key (cita_id) references CITA (id);
alter table COMPROBANTE add constraint FKCOMPROBANT250686 foreign key (CITAid) references CITA (id);
alter table CUSTODIA add constraint FKCUSTODIA551514 foreign key (DUEniOid) references DUEniO (id);
alter table CUSTODIA add constraint FKCUSTODIA281978 foreign key (MASCOTAid) references MASCOTA (id);
alter table CITA add constraint FKCITA813108 foreign key (CUSTODIAMASCOTAid, CUSTODIADUEniOid) references CUSTODIA (MASCOTAid, DUEniOid);
alter table DETALLE_SERVICIO add constraint FKDETALLE_SE753796 foreign key (medico_id) references MEDICO (id);
alter table DETALLE_SERVICIO add constraint FKDETALLE_SE503336 foreign key (servicio_id) references SERVICIO (id);
alter table DETALLE_MEDICAMENTO add constraint FKDETALLE_ME2678 foreign key (medicamento_id) references MEDICAMENTO (id);
alter table DETALLE_MEDICAMENTO add constraint FKDETALLE_ME390069 foreign key (detalle_cita_id, detalle_servicio_servicio_id, detalle_servicio_medico_id) references DETALLE_CITA (cita_id, detalle_servicio_serv_id, detalle_servicio_med_id);
alter table DETALLE_CITA add constraint FKDETALLE_CI377475 foreign key (detalle_servicio_serv_id, detalle_servicio_med_id) references DETALLE_SERVICIO (servicio_id, medico_id);


---------------------------------------------------------------


CREATE OR REPLACE FUNCTION contar_relaciones(tabla_base TEXT, id_base INT)
    RETURNS TABLE(tabla_relacionada TEXT, columna_foranea TEXT, cantidad INT) AS $$
DECLARE
    consulta_contar TEXT;
    cuenta INT;
BEGIN
    FOR tabla_relacionada, columna_foranea IN
        SELECT
            c.conrelid::regclass::text AS tabla_relacionada,
            a.attname AS columna_foranea
        FROM
            pg_constraint c
        JOIN pg_class t ON c.conrelid = t.oid
        JOIN pg_attribute a ON a.attnum = ANY (c.conkey) AND a.attrelid = c.conrelid
        WHERE
            c.confrelid = tabla_base::regclass
            AND c.contype = 'f'
    LOOP
        consulta_contar := FORMAT(
            'SELECT COUNT(*) FROM %I WHERE %I = $1',
            tabla_relacionada,
            columna_foranea
        );

        EXECUTE consulta_contar INTO cuenta USING id_base;

        RETURN QUERY SELECT tabla_relacionada, columna_foranea, cuenta;
    END LOOP;
END $$ LANGUAGE plpgsql;



-----------------------------------------------------------------------------------------------------------------------------------------------------

-- MEDICOS Y SERVICIOS

INSERT INTO ESPECIALIDAD (id, nom_especialidad, disponibilidad) VALUES 
(1, 'Medicina General', TRUE),
(2, 'Cirugía Veterinaria', TRUE),
(3, 'Dermatología', TRUE),
(4, 'Cardiología Veterinaria', TRUE),
(5, 'Odontología Veterinaria', FALSE);

INSERT INTO SERVICIO (id, nom_servicio, descripcion, costo, disponibilidad) VALUES
(1, 'Consulta General', 'Consulta médica general para revisión de la mascota', 50.00,true),
(2, 'Vacunación', 'Aplicación de vacuna según el calendario de vacunación', 30.00,true),
(3, 'Desparasitación', 'Tratamiento para eliminar parásitos internos y externos', 40.00,true),
(4, 'Cirugía menor', 'Intervenciones quirúrgicas menores como esterilización', 200.00,true),
(5, 'Emergencia', 'Atención de emergencias veterinarias las 24 horas', 150.00,true);


INSERT INTO MEDICO (id, doc_identidad, nombres, apePaterno, apeMaterno, sexo, disponibilidad, vigencia, especialidad_id) 
VALUES 
(1, '12345678', 'Juan', 'Pérez', 'García', TRUE, TRUE, TRUE, 1),
(2, '23456789', 'Carlos', 'Gómez', 'Sánchez', TRUE, FALSE, FALSE, 5),
(3, '34567890', 'Pedro', 'Hernández', 'Ramírez', TRUE, FALSE, TRUE, 5),
(4, '87654321', 'Ana', 'López', 'Martínez', FALSE, FALSE, TRUE, 2), 
(5, '98765432', 'Lucía', 'Rodríguez', 'Morales', FALSE, TRUE, TRUE, 4);


INSERT INTO DETALLE_SERVICIO (servicio_id, medico_id, disponibilidad) VALUES
(1, 1,true), -- Consulta General realizada por el médico con ID 1
(2, 1,true), -- Vacunación realizada por el médico con ID 1
(3, 2,true), -- Desparasitación realizada por el médico con ID 2
(4, 3,true), -- Cirugía menor realizada por el médico con ID 3
(5, 4,true), -- Emergencia realizada por el médico con ID 4
(1, 2,true), -- Consulta General realizada también por el médico con ID 2
(2, 3,true), -- Vacunación realizada por el médico con ID 3
(3, 4,true), -- Desparasitación realizada por el médico con ID 4
(4, 5,true), -- Cirugía menor realizada por el médico con ID 5
(5, 5,true); -- Emergencia realizada también por el médico con ID 5



-- MASCOTAS Y DUEÑOS


INSERT INTO ESPECIE (id, nombre , disponibilidad) VALUES 
(1, 'Perro' , true),
(2, 'Gato' , true),
(3, 'Caballo' , true),
(4, 'Vaca' , true),
(5, 'Oveja' , true);


INSERT INTO RAZA (id, nombre, especie_id , disponibilidad) VALUES 
(1, 'Labrador Retriever', 1 , true),
(2, 'Pastor Alemán', 1 , true),
(3, 'Siames', 2 , true),
(4, 'Persa', 2 , true),
(5, 'Pura Sangre', 3 , true),
(6, 'Percherón', 3 , true),
(7, 'Holstein', 4 , true),
(8, 'Angus', 4 , true),
(9, 'Merino', 5 , true),
(10, 'Suffolk', 5 , true);


INSERT INTO VACUNA (id, nombre, dosis_x_kgpeso, especie_id , disponibilidad) VALUES 
(1, 'Vacuna Antirrábica', 0.05, 1 , true),
(2, 'Vacuna Parvovirus', 0.10, 1 , true),
(3, 'Vacuna Triple Felina', 0.08, 2 , true),
(4, 'Vacuna Influenza Equina', 0.15, 3 , true),
(5, 'Vacuna Carbunclo', 0.20, 4 , true);



INSERT INTO DUEniO (id, doc_identidad, nombres, apePaterno, apeMaterno, telefono, telefonoAlt, correo, direccion, sexo, vigencia) VALUES
(1, '71234567', 'Carlos', 'García', 'Pérez', '987654321', '912345678', 'cgarcia@example.com', 'Av. Siempre Viva 123', true, true),
(2, '82765432', 'María', 'López', 'Sánchez', '976543210', NULL, 'mlopez@example.com', 'Jr. Los Pinos 456', false, true),
(3, '65432189', 'José', 'Martínez', 'Guzmán', '964321098', '941234567', NULL, 'Calle Las Rosas 789', true, true),
(4, '98651234', 'Ana', 'Rodríguez', 'Fernández', '932165478', NULL, 'arodriguez@example.com', 'Av. Las Palmeras 456', false, true),
(5, '73512368', 'Luis', 'Paredes', 'Zapata', '911234569', '998877665', 'lparedes@example.com', 'Jr. Las Lomas 234', true, true),
(6, '84561234', 'Rosa', 'Chávez', 'Valverde', '945612378', NULL, 'rchavez@example.com', 'Calle Los Cedros 156', false, true),
(7, '78912345', 'Pedro', 'Cruz', 'Huaman', '987123654', '954321876', 'pcruz@example.com', 'Av. Perú 789', true, true),
(8, '62341234', 'Luisa', 'Ortiz', 'Pérez', '912345674', NULL, 'lortiz@example.com', 'Jr. San Juan 342', false, true),
(9, '98123456', 'Miguel', 'Alvarado', 'Reyes', '987412356', '943215678', 'malvarado@example.com', 'Av. Colonial 342', true, true),
(10, '81234567', 'Carmen', 'Salinas', 'Ríos', '965412378', '976543212', 'csalinas@example.com', 'Jr. Las Magnolias 765', false, true);



INSERT INTO MASCOTA (id, nombre, fecha_nacimiento, altura, peso, notaAdicional, sexo, esterilizado, desparasitado, estado_salud, raza_id, vigencia) VALUES
(1, 'Luna', '2020-05-15', 35.50, 12.30, 'Tiene mucha energía y es muy activa.', false, true, true, 'S', 1, true),
(2, 'Max', '2018-11-22', 45.20, 18.75, 'Le encanta correr largas distancias y es muy fuerte.', true, true, true, 'S', 2, true),
(3, 'Bella', '2019-09-30', 38.10, 15.60, 'Padece artritis crónica, bajo tratamiento.', false, false, true, 'C', 3, true),
(4, 'Simba', '2011-07-05', 50.00, 25.00, 'Un poco tímido al principio, pero muy juguetón.', true, false, false, 'S', 1, true),
(5, 'Rocky', '2017-03-01', 42.50, 20.40, 'Sufre de insuficiencia renal crónica, requiere dieta especial.', true, true, true, 'C', 2, true),
(6, 'Coco', '2022-01-10', 30.80, 10.20, 'Muy cariñosa con los niños y social.', false, true, true, 'S', 8,  true),
(7, 'Nala', '2016-06-18', 37.00, 14.50, 'Cáncer avanzado, en tratamiento paliativo.', false, false, false, 'T',10, true),
(8, 'Charlie', '2020-12-09', 48.30, 22.10, 'Amigable con otras mascotas, le gusta nadar.', true, true, true, 'S', 2, true),
(9, 'Milo', '2019-02-25', 39.20, 16.80, 'Alergia crónica a ciertos alimentos.', true, false, true, 'C', 1, true),
(10, 'Chloe', '2015-10-28', 33.50, 11.90, 'Cáncer terminal, con pocos meses de vida.', false, true, true, 'T', 7, true);

INSERT INTO CUSTODIA (MASCOTAid, DUEniOid, fecha_adopción) VALUES
(1, 1, '2020-05-20'), -- Luna adoptada por Carlos
(2, 2, '2018-12-01'), -- Max adoptado por María
(3, 3, '2019-10-05'), -- Bella adoptada por José
(4, 4, '2011-08-01'), -- Simba adoptado por Ana
(5, 5, '2017-04-10'), -- Rocky adoptado por Luis
(6, 6, '2022-01-15'), -- Coco adoptada por Rosa
(7, 7, '2016-07-01'), -- Nala adoptada por Pedro
(8, 8, '2020-12-10'), -- Charlie adoptado por Luisa
(9, 9, '2019-03-01'), -- Milo adoptado por Miguel
(10, 10, '2015-11-01'), -- Chloe adoptada por Carmen
(10, 1, '2016-01-15'),  -- Chloe también adoptada por Carlos
(10, 2, '2017-05-20');  -- Chloe también adoptada por María


-- CITAS



INSERT INTO ESTADO_CITA (id, nombre_estado) VALUES
(1, 'Pendiente'),
(2, 'Por confirmar'),
(3, 'Cancelada'),
(4, 'Reprogramada'),
(6, 'Finalizada'),
(7, 'No asistió');


-- MEDICAMENTOS


INSERT INTO TIPO_MEDICAMENTO (id, nomtipo) VALUES 
(1, 'Antibiótico'),
(2, 'Analgésico'),
(3, 'Antiparasitario'),
(4, 'Antiinflamatorio'),
(5, 'Vacuna');


INSERT INTO MEDICAMENTO (id, nombre, costo, stock, presentacion, vigencia, tipo_medicamento_id) VALUES 
(1, 'Amoxicilina 250mg', 15.50, 50, 'Cápsula', true, 1),
(2, 'Meloxicam 0.5mg', 25.00, 30, 'Comprimido', true, 2),
(3, 'Drontal Plus 150mg', 40.00, 100, 'Tableta', true, 3),
(4, 'Carprofeno 25mg', 35.00, 20, 'Comprimido', true, 4),
(5, 'Vacuna Triple Felina', 55.00, 10, 'Inyección', true, 5),
(6, 'Ivermectina 1%', 12.00, 80, 'Inyección', true, 3),
(7, 'Ketoprofeno 10mg', 20.00, 60, 'Inyección', true, 4),
(8, 'Vacuna Antirrábica', 60.00, 15, 'Inyección', true, 5),
(9, 'Tramadol 50mg', 30.00, 40, 'Tableta', true, 2),
(10, 'Doxiciclina 100mg', 22.00, 25, 'Cápsula', true, 1);

INSERT INTO CITA (id, estado_cita_id, fecha_cita, observacion, CUSTODIAMASCOTAid, CUSTODIADUEniOid) 
VALUES 
    (1, 1, '2024-11-05', 'Primera revisión general', 1, 1),
    (2, 2, '2024-11-10', 'Vacunación inicial', 2, 2),
    (3, 4, '2024-11-15', 'Control de seguimiento', 3, 3),
    (4, 7, '2024-11-20', 'Consulta cancelada por inasistencia', 4, 4),
    (5, 6, '2024-11-25', 'Finalización de tratamiento', 5, 5);




--USUARIOS
INSERT INTO USUARIO (codUsuario, nomusuario, estado, sexo, clave, nombres, apPaterno, apMaterno, cargo) 
VALUES (1, 'jdoe', TRUE, TRUE, md5('1234' || 'jdoe' || 'CODE146'), 'John', 'Doe', 'Smith', 'V');

INSERT INTO USUARIO (codUsuario, nomusuario, estado, sexo, clave, nombres, apPaterno, apMaterno, cargo) 
VALUES (2, 'mgarcia', TRUE, FALSE, md5('4567' || 'mgarcia' || 'CODE146'), 'Maria', 'Garcia', 'Lopez', 'E');

INSERT INTO USUARIO (codUsuario, nomusuario, estado, sexo, clave, nombres, apPaterno, apMaterno, cargo) 
VALUES (3, 'arivera', TRUE, TRUE, md5('7890' || 'arivera' || 'CODE146'), 'Alex', 'Rivera', 'Martinez', 'A');

INSERT INTO USUARIO (codUsuario, nomusuario, estado, sexo, clave, nombres, apPaterno, apMaterno, cargo) 
VALUES (4, 'lfernandez', FALSE, FALSE, md5('1011' || 'lfernandez' || 'CODE146'), 'Laura', 'Fernandez', 'Soto', 'V');

INSERT INTO USUARIO (codUsuario, nomusuario, estado, sexo, clave, nombres, apPaterno, apMaterno, cargo) 
VALUES (5, 'cperez', TRUE, TRUE, md5('1021' || 'cperez' || 'CODE146'), 'Carlos', 'Perez', 'Gutierrez', 'E');

INSERT INTO USUARIO (codUsuario, nomusuario, estado, sexo, clave, nombres, apPaterno, apMaterno, cargo) 
VALUES (6, 'asanchez', FALSE, TRUE, md5('1031' || 'asanchez' || 'CODE146'), 'Andres', 'Sanchez', 'Lopez', 'V');

INSERT INTO USUARIO (codUsuario, nomusuario, estado, sexo, clave, nombres, apPaterno, apMaterno, cargo) 
VALUES (7, 'fabi', FALSE, TRUE, md5('123' || 'fabi' || 'CODE146'), 'Fabiana', 'Paucar', 'Mejia', 'V');

INSERT INTO USUARIO (codUsuario, nomusuario, estado, sexo, clave, nombres, apPaterno, apMaterno, cargo) 
VALUES (8, 'Admin_Fab', TRUE, TRUE, md5('123' || 'Admin_Fab' || 'CODE146'), 'Fabiana', 'Paucar', 'Mejia', 'V');










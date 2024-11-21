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

INSERT INTO USUARIO (codUsuario, nomusuario, estado, sexo, clave, nombres, apPaterno, apMaterno, cargo) s
VALUES (8, 'Admin_Fab', TRUE, TRUE, md5('123' || 'Admin_Fab' || 'CODE146'), 'Fabiana', 'Paucar', 'Mejia', 'V');







-- MEDICOS Y SERVICIOS

INSERT INTO ESPECIALIDAD (id, nom_especialidad, disponibilidad) VALUES 
(1, 'Medicina General', TRUE),
(2, 'Cirugía Veterinaria', TRUE),
(3, 'Dermatología', TRUE),
(4, 'Cardiología Veterinaria', TRUE),
(5, 'Odontología Veterinaria', FALSE),
(6, 'Neurología Veterinaria', TRUE),
(7, 'Oftalmología Veterinaria', TRUE),
(8, 'Endocrinología', TRUE),
(9, 'Nutrición Veterinaria', TRUE),
(10, 'Anestesiología', FALSE),
(11, 'Oncología Veterinaria', TRUE),
(12, 'Traumatología', TRUE),
(13, 'Rehabilitación Física', TRUE),
(14, 'Patología Clínica', FALSE),
(15, 'Terapia Intensiva', TRUE),
(16, 'Etología', TRUE),
(17, 'Radiología', TRUE),
(18, 'Medicina Felina', TRUE),
(19, 'Cirugía Ortopédica', TRUE),
(20, 'Geriatría Veterinaria', FALSE),
(21, 'Cardiología', TRUE),
(22, 'Nefrología', TRUE),
(23, 'Toxicología Veterinaria', TRUE),
(24, 'Parasitología', TRUE),
(25, 'Hematología', FALSE),
(26, 'Urgencias', TRUE),
(27, 'Neonatología Veterinaria', TRUE),
(28, 'Cuidados Paliativos', TRUE),
(29, 'Patología Veterinaria', TRUE),
(30, 'Dermatología Exótica', FALSE),
(31, 'Zootecnia', TRUE),
(32, 'Medicina de Fauna Silvestre', TRUE),
(33, 'Medicina Avícola', TRUE),
(34, 'Odontología Felina', FALSE),
(35, 'Medicina Preventiva', TRUE);


INSERT INTO SERVICIO (id, nom_servicio, descripcion, costo, disponibilidad) VALUES
(1, 'Consulta General', 'Consulta médica general para revisión de la mascota', 50.00,true),
(2, 'Vacunación', 'Aplicación de vacuna según el calendario de vacunación', 30.00,true),
(3, 'Desparasitación', 'Tratamiento para eliminar parásitos internos y externos', 40.00,true),
(4, 'Cirugía menor', 'Intervenciones quirúrgicas menores como esterilización', 200.00,true),
(5, 'Emergencia', 'Atención de emergencias veterinarias las 24 horas', 150.00,true),
(6, 'Radiografía', 'Servicio de radiografía para diagnóstico', 100.00, TRUE),
(7, 'Ecografía', 'Servicio de ecografía para análisis interno', 120.00, TRUE),
(8, 'Limpieza Dental', 'Limpieza profesional para evitar infecciones bucales', 80.00, TRUE),
(9, 'Chequeo Cardiológico', 'Consulta y chequeo de la salud cardiaca', 150.00, TRUE),
(10, 'Terapia Física', 'Rehabilitación para mascotas con lesiones', 200.00, TRUE),
(11, 'Oncología', 'Diagnóstico y tratamiento del cáncer en mascotas', 300.00, TRUE),
(12, 'Consulta Dermatológica', 'Diagnóstico de problemas de piel en mascotas', 70.00, TRUE),
(13, 'Cirugía Mayor', 'Intervenciones quirúrgicas complejas', 500.00, FALSE),
(14, 'Hospitalización', 'Cuidado intensivo para mascotas hospitalizadas', 400.00, TRUE),
(15, 'Consulta de Urgencia', 'Atención de urgencias fuera de horario habitual', 200.00, TRUE),
(16, 'Análisis de Sangre', 'Diagnóstico mediante perfil hemático', 90.00, TRUE),
(17, 'Electrocardiograma', 'Estudio del ritmo cardíaco', 110.00, TRUE),
(18, 'Endoscopía', 'Procedimiento para evaluación interna', 350.00, TRUE),
(19, 'Corte de Uñas', 'Servicio básico para la salud de las patas', 20.00, TRUE),
(20, 'Masaje Terapéutico', 'Masajes para rehabilitación física', 60.00, TRUE),
(21, 'Vacuna Rabia', 'Vacuna específica para prevenir la rabia', 35.00, TRUE),
(22, 'Castración', 'Intervención para esterilización de machos', 180.00, TRUE),
(23, 'Esterilización', 'Esterilización de hembras', 200.00, TRUE),
(24, 'Consulta Geriátrica', 'Chequeo completo para mascotas mayores', 150.00, TRUE),
(25, 'Desparasitación Avanzada', 'Tratamiento contra parásitos resistentes', 50.00, TRUE),
(26, 'Tratamiento Odontológico', 'Procedimientos especializados para dientes', 130.00, TRUE),
(27, 'Consulta Oftalmológica', 'Revisión y tratamiento ocular', 140.00, TRUE),
(28, 'Pruebas Alérgicas', 'Detección de alergias en mascotas', 180.00, TRUE),
(29, 'Consulta de Segunda Opinión', 'Evaluación adicional para diagnóstico', 100.00, TRUE),
(30, 'Consulta Exótica', 'Atención a animales no convencionales', 200.00, FALSE);



INSERT INTO MEDICO (id, doc_identidad, nombres, apePaterno, apeMaterno, sexo, disponibilidad, vigencia, especialidad_id) 
VALUES 
(1, '12345678', 'Juan', 'Pérez', 'García', TRUE, TRUE, TRUE, 1),
(2, '23456789', 'Carlos', 'Gómez', 'Sánchez', TRUE, FALSE, FALSE, 5),
(3, '34567890', 'Pedro', 'Hernández', 'Ramírez', TRUE, FALSE, TRUE, 5),
(4, '87654321', 'Ana', 'López', 'Martínez', FALSE, FALSE, TRUE, 2), 
(5, '98765432', 'Lucía', 'Rodríguez', 'Morales', FALSE, TRUE, TRUE, 4),
(6, '45678901', 'Sofía', 'Mendoza', 'Villanueva', FALSE, TRUE, TRUE, 6),
(7, '56789012', 'María', 'Torres', 'Pérez', FALSE, TRUE, FALSE, 7),
(8, '67890123', 'Diego', 'Vargas', 'Fernández', TRUE, FALSE, TRUE, 8),
(9, '78901234', 'Carmen', 'Ortega', 'Rivera', FALSE, TRUE, TRUE, 9),
(10, '89012345', 'Luis', 'Jiménez', 'Soto', TRUE, TRUE, TRUE, 10),
(11, '90123456', 'Gabriel', 'Silva', 'Chávez', TRUE, FALSE, TRUE, 11),
(12, '01234567', 'Pablo', 'Martínez', 'Campos', TRUE, TRUE, TRUE, 12),
(13, '09876543', 'Alejandro', 'Luna', 'Quispe', TRUE, TRUE, TRUE, 13),
(14, '87654320', 'Elena', 'Castro', 'Muñoz', FALSE, TRUE, TRUE, 14),
(15, '76543210', 'Laura', 'Reyes', 'Ríos', FALSE, TRUE, TRUE, 15),
(16, '65432109', 'Hugo', 'Figueroa', 'Cruz', TRUE, FALSE, TRUE, 16),
(17, '54321098', 'Valeria', 'Espinoza', 'Vega', FALSE, TRUE, TRUE, 17),
(18, '43210987', 'Fernando', 'Arce', 'Valdés', TRUE, FALSE, TRUE, 18),
(19, '32109876', 'Cristina', 'Palacios', 'Montalvo', FALSE, TRUE, TRUE, 19),
(20, '21098765', 'Enrique', 'Rosas', 'Zeballos', TRUE, TRUE, TRUE, 20),
(21, '19876543', 'Martín', 'Carrasco', 'Beltrán', TRUE, TRUE, TRUE, 21),
(22, '12345670', 'Andrés', 'Romero', 'Campos', TRUE, TRUE, TRUE, 22),
(23, '67890543', 'Gloria', 'Vidal', 'Peña', FALSE, TRUE, TRUE, 23),
(24, '09876541', 'Adriana', 'Quinteros', 'Núñez', FALSE, TRUE, TRUE, 24),
(25, '34567890', 'Felipe', 'Díaz', 'Muñoz', TRUE, FALSE, TRUE, 25),
(26, '45678901', 'Raúl', 'Pérez', 'Fernández', TRUE, TRUE, TRUE, 26),
(27, '56789012', 'Patricia', 'Ríos', 'Castillo', FALSE, TRUE, FALSE, 27),
(28, '23456789', 'Ricardo', 'Torres', 'Molina', TRUE, FALSE, TRUE, 28),
(29, '89012345', 'Angela', 'Villanueva', 'Campos', FALSE, TRUE, TRUE, 29),
(30, '90123456', 'Esteban', 'Jiménez', 'Soto', TRUE, TRUE, TRUE, 30);



INSERT INTO DETALLE_SERVICIO (servicio_id, medico_id, disponibilidad) VALUES
(1, 1, TRUE),
(2, 1, TRUE),
(3, 2, TRUE),
(4, 2, TRUE),
(5, 3, FALSE),
(6, 3, TRUE),
(7, 4, TRUE),
(8, 4, TRUE),
(9, 5, TRUE),
(10, 5, FALSE),
(11, 6, TRUE),
(12, 6, TRUE),
(13, 7, FALSE),
(14, 7, TRUE),
(15, 8, TRUE),
(16, 8, TRUE),
(17, 9, TRUE),
(18, 9, TRUE),
(19, 10, TRUE),
(20, 10, TRUE),
(1, 11, FALSE),
(2, 11, TRUE),
(3, 12, TRUE),
(4, 12, TRUE),
(5, 13, TRUE),
(6, 13, TRUE),
(7, 14, TRUE),
(8, 14, TRUE),
(9, 15, FALSE),
(10, 15, TRUE),
(11, 16, TRUE),
(12, 16, TRUE),
(13, 17, FALSE),
(14, 17, TRUE),
(15, 18, TRUE),
(16, 18, TRUE),
(17, 19, TRUE),
(18, 19, TRUE),
(19, 20, TRUE),
(20, 20, TRUE),
(1, 21, TRUE),
(2, 21, FALSE),
(3, 22, TRUE),
(4, 22, TRUE),
(5, 23, TRUE),
(6, 23, TRUE),
(7, 24, TRUE),
(8, 24, TRUE),
(9, 25, FALSE),
(10, 25, TRUE),
(11, 26, TRUE),
(12, 26, TRUE),
(13, 27, FALSE),
(14, 27, TRUE),
(15, 28, TRUE),
(16, 28, TRUE),
(17, 29, TRUE),
(18, 29, TRUE),
(19, 30, TRUE),
(20, 30, TRUE);

-- MASCOTAS Y DUEÑOS


INSERT INTO ESPECIE (id, nombre , disponibilidad) VALUES 
(1, 'Perro' , true),
(2, 'Gato' , true),
(3, 'Caballo' , true),
(4, 'Vaca' , true),
(5, 'Oveja' , true),
(6, 'Conejo', true),
(7, 'Pájaro', true),
(8, 'Pez', true),
(9, 'Hamster', true),
(10, 'Serpiente', true);

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
(10, 'Suffolk', 5 , true),
(11, 'Rex', 6, true),
(12, 'Angora', 6, true),
(13, 'Canario', 7, true),
(14, 'Periquito', 7, true),
(15, 'Betta', 8, true),
(16, 'Goldfish', 8, true),
(17, 'Sirio', 9, true),
(18, 'Roborovski', 9, true),
(19, 'Boa', 10, true),
(20, 'Pitón', 10, true);


INSERT INTO VACUNA (id, nombre, dosis_x_kgpeso, especie_id , disponibilidad) VALUES 
(1, 'Vacuna Antirrábica', 0.05, 1 , true),
(2, 'Vacuna Parvovirus', 0.10, 1 , true),
(3, 'Vacuna Triple Felina', 0.08, 2 , true),
(4, 'Vacuna Influenza Equina', 0.15, 3 , true),
(5, 'Vacuna Carbunclo', 0.20, 4 , true),
(6, 'Vacuna Distemper Canino', 0.07, 1, true),
(7, 'Vacuna Hepatitis Canina', 0.05, 1, true),
(8, 'Vacuna Panleucopenia Felina', 0.06, 2, true),
(9, 'Vacuna Bordetella', 0.12, 1, true),
(10, 'Vacuna Tétano Equino', 0.14, 3, true),
(11, 'Vacuna Leptospirosis', 0.08, 4, true),
(12, 'Vacuna Brucelosis', 0.09, 4, true),
(13, 'Vacuna Aftosa', 0.13, 4, true),
(14, 'Vacuna Moquillo Canino', 0.11, 1, true),
(15, 'Vacuna Rinotraqueitis Felina', 0.06, 2, true),
(16, 'Vacuna Papilomatosis Bovina', 0.10, 4, true),
(17, 'Vacuna Hemorrágica Viral', 0.12, 5, true),
(18, 'Vacuna Virus del Oeste del Nilo', 0.16, 3, true),
(19, 'Vacuna Clostridial', 0.11, 4, true),
(20, 'Vacuna Rota-virus', 0.15, 3, true),
(21, 'Vacuna Leishmaniosis', 0.10, 1, true),
(22, 'Vacuna Herpesvirus Bovino', 0.14, 4, true),
(23, 'Vacuna Adenovirus Canino', 0.09, 1, true),
(24, 'Vacuna Parainfluenza Felina', 0.07, 2, true),
(25, 'Vacuna Rabia Bovina', 0.12, 4, true);


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
(10, '81234567', 'Carmen', 'Salinas', 'Ríos', '965412378', '976543212', 'csalinas@example.com', 'Jr. Las Magnolias 765', false, true),
(11, '71236541', 'Fernando', 'Alvarez', 'Vargas', '987654322', '912345679', 'falvarez@example.com', 'Calle Los Laureles 123', true, true),
(12, '82768543', 'Gloria', 'Ramos', 'López', '976543211', NULL, 'gramos@example.com', 'Av. Los Jazmines 456', false, true),
(13, '65432489', 'Ricardo', 'Mendoza', 'Gómez', '964321099', '941234568', 'rmendoza@example.com', 'Jr. Las Palmas 789', true, true),
(14, '98651454', 'Patricia', 'Quispe', 'Romero', '932165479', NULL, 'pquispe@example.com', 'Av. Los Sauces 456', false, true),
(15, '73512469', 'Enrique', 'Torres', 'Salazar', '911234570', '998877666', 'etorres@example.com', 'Jr. Los Cipreses 234', true, true),
(16, '84561534', 'Marta', 'Delgado', 'Castañeda', '945612379', NULL, 'mdelgado@example.com', 'Calle Los Geranios 156', false, true),
(17, '78912645', 'Héctor', 'Vargas', 'Montes', '987123655', '954321877', 'hvargas@example.com', 'Av. Los Olivos 789', true, true),
(18, '62341324', 'Sofía', 'Valle', 'Torres', '912345675', NULL, 'svalle@example.com', 'Jr. San Luis 342', false, true),
(19, '98123457', 'Daniel', 'Miranda', 'Flores', '987412357', '943215679', 'dmiranda@example.com', 'Av. Los Ángeles 342', true, true),
(20, '81234678', 'Paola', 'Rivera', 'Herrera', '965412379', '976543213', 'privera@example.com', 'Jr. Las Acacias 765', false, true),
(21, '71984562', 'Alejandro', 'Jiménez', 'Flores', '987654323', '912345671', 'ajimenez@example.com', 'Av. Los Robles 123', true, true),
(22, '82896542', 'Silvia', 'Rivera', 'Campos', '976543212', NULL, 'srivera@example.com', 'Jr. Los Cedros 456', false, true),
(23, '65912347', 'Diego', 'Pérez', 'Vera', '964321091', '941234569', 'dperez@example.com', 'Calle Los Nogales 789', true, true),
(24, '98761524', 'Monica', 'Suárez', 'Mendoza', '932165470', NULL, 'msuarez@example.com', 'Av. Las Flores 456', false, true),
(25, '73826545', 'César', 'Fernández', 'Zavala', '911234571', '998877667', 'cfernandez@example.com', 'Jr. Las Orquídeas 234', true, true),
(26, '85621345', 'Lorena', 'Paredes', 'Campos', '945612370', NULL, 'lparedes@example.com', 'Calle Las Dalias 156', false, true),
(27, '79982354', 'Marco', 'Guzmán', 'Torres', '987123656', '954321878', 'mguzman@example.com', 'Av. Los Álamos 789', true, true),
(28, '63412435', 'Lucía', 'Sánchez', 'Montoya', '912345676', NULL, 'lsanchez@example.com', 'Jr. San Miguel 342', false, true),
(29, '98124569', 'Julio', 'Cabrera', 'Castaño', '987412358', '943215680', 'jcabrera@example.com', 'Av. Los Cedros 342', true, true),
(30, '81964572', 'Andrea', 'Espinoza', 'Navarro', '965412371', '976543214', 'aespinoza@example.com', 'Jr. Los Tulipanes 765', false, true),
(31, '71548236', 'Hugo', 'Ramos', 'Flores', '987654325', '912345673', 'hramos@example.com', 'Av. Los Geranios 123', true, true),
(32, '82654312', 'Gabriela', 'Pérez', 'Martínez', '976543213', NULL, 'gperez@example.com', 'Jr. Las Flores 456', false, true),
(33, '65478923', 'Jorge', 'Ramírez', 'López', '964321092', '941234570', 'jramirez@example.com', 'Calle Los Olivos 789', true, true),
(34, '98765423', 'Patricia', 'Díaz', 'Cruz', '932165471', NULL, 'pdiaz@example.com', 'Av. Las Acacias 456', false, true),
(35, '73641258', 'Rafael', 'Mendoza', 'Salinas', '911234572', '998877668', 'rmendoza@example.com', 'Jr. Las Violetas 234', true, true),
(36, '84652314', 'Sofía', 'López', 'Quispe', '945612371', NULL, 'slopez@example.com', 'Calle Las Rosas 156', false, true),
(37, '78965412', 'David', 'Paredes', 'Tito', '987123657', '954321879', 'dparedes@example.com', 'Av. Las Magnolias 789', true, true),
(38, '62481347', 'Verónica', 'Morales', 'Fernández', '912345677', NULL, 'vmorales@example.com', 'Jr. San Pedro 342', false, true),
(39, '98126543', 'Rodrigo', 'Reyes', 'Castro', '987412359', '943215681', 'rreyes@example.com', 'Av. Las Palmeras 342', true, true),
(40, '81652734', 'Lucero', 'Ortega', 'Gómez', '965412372', '976543215', 'lortega@example.com', 'Jr. Los Álamos 765', false, true);



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
(10, 'Chloe', '2015-10-28', 33.50, 11.90, 'Cáncer terminal, con pocos meses de vida.', false, true, true, 'T', 7, true),
(11, 'Duke', '2019-04-15', 47.30, 20.50, 'Tiene ansiedad por separación, necesita compañía constante.', true, true, true, 'S', 1, true),
(12, 'Molly', '2021-02-10', 33.40, 12.80, 'Le encanta jugar con niños y es muy protectora.', false, true, true, 'S', 2, true),
(13, 'Buddy', '2020-07-22', 49.50, 22.40, 'Ha sido entrenado para obedecer comandos básicos.', true, true, true, 'S', 3, true),
(14, 'Lola', '2017-11-05', 36.00, 14.90, 'Es muy tímida con extraños pero cariñosa con la familia.', false, false, true, 'S', 4, true),
(15, 'Bailey', '2015-08-01', 40.20, 18.30, 'Tiene alergias estacionales, necesita medicación.', true, true, true, 'C', 2, true),
(16, 'Sadie', '2018-05-10', 34.80, 13.50, 'Le encanta correr y es muy activa en las mañanas.', false, true, true, 'S', 5, true),
(17, 'Oscar', '2016-09-18', 38.70, 16.20, 'Tiene problemas de visión, requiere cuidados adicionales.', true, false, false, 'T', 6, true),
(18, 'Daisy', '2020-03-25', 45.20, 19.70, 'Es muy juguetona y sociable con otras mascotas.', false, true, true, 'S', 2, true),
(19, 'Jack', '2019-12-12', 48.00, 21.30, 'Fue rescatado, aún muestra signos de trauma.', true, false, true, 'C', 1, true),
(20, 'Rosie', '2014-06-28', 32.70, 11.40, 'Sufre de artritis, bajo tratamiento veterinario.', false, true, true, 'T', 8, true),
(21, 'Toby', '2016-04-10', 30.50, 11.20, 'Es un perro muy obediente.', true, true, true, 'S', 1, true),
(22, 'Lucy', '2018-08-20', 35.40, 13.50, 'Le gusta jugar en el parque.', false, true, true, 'S', 3, true),
(23, 'Rex', '2019-05-14', 42.10, 19.40, 'Es un poco territorial.', true, true, false, 'S', 4, true),
(24, 'Lili', '2017-02-18', 29.00, 10.30, 'Le tiene miedo a los truenos.', false, true, true, 'S', 2, true),
(25, 'Tommy', '2020-06-23', 41.20, 20.10, 'Le encanta correr en la playa.', true, true, true, 'S', 5, true),
(26, 'Sam', '2015-11-12', 36.40, 14.80, 'Es un perro muy sociable.', true, true, true, 'S', 1, true),
(27, 'Bella', '2019-09-02', 38.20, 16.30, 'Tiene una lesión en la pata.', false, true, false, 'C', 3, true),
(28, 'Mimi', '2021-01-30', 32.50, 12.60, 'Es una gata muy juguetona.', false, true, true, 'S', 4, true),
(29, 'Roco', '2014-05-10', 44.70, 21.00, 'Le gusta cazar ratones.', true, true, true, 'S', 2, true),
(30, 'Oreo', '2018-03-15', 31.50, 13.20, 'Es un perro muy cariñoso.', true, true, true, 'S', 5, true),
(31, 'Rocky', '2019-08-09', 39.40, 17.60, 'Tiene miedo a los extraños.', true, false, true, 'S', 1, true),
(32, 'Molly', '2020-12-01', 37.80, 15.10, 'Es muy protectora con los niños.', false, true, true, 'S', 2, true),
(33, 'Lola', '2018-09-15', 34.20, 13.00, 'Le gusta dormir mucho.', false, true, true, 'C', 4, true),
(34, 'Charlie', '2017-04-11', 29.90, 11.70, 'Es un gato muy independiente.', true, true, false, 'S', 3, true),
(35, 'Chispa', '2019-07-25', 40.60, 19.30, 'Le encanta jugar con otros perros.', true, true, true, 'S', 5, true),
(36, 'Canela', '2016-02-22', 32.10, 13.40, 'Es un poco tímida con extraños.', false, true, true, 'C', 1, true),
(37, 'Nina', '2021-11-11', 28.50, 9.60, 'Tiene mucha energía.', false, true, true, 'S', 3, true),
(38, 'Mango', '2022-03-10', 33.00, 12.10, 'Es muy curiosa.', false, true, true, 'S', 4, true),
(39, 'Bruno', '2020-05-21', 45.30, 22.00, 'Es muy territorial.', true, true, true, 'S', 2, true),
(40, 'Tina', '2019-10-17', 29.80, 11.80, 'Es una gata muy tranquila.', false, true, true, 'C', 3, true);





INSERT INTO CUSTODIA (MASCOTAid, DUEniOid, fecha_adopción) VALUES
(1, 1, '2020-06-01'),
(2, 2, '2019-01-15'),
(3, 3, '2020-10-10'),
(4, 4, '2011-08-01'),
(5, 5, '2017-04-01'),
(6, 6, '2022-02-15'),
(7, 7, '2016-07-01'),
(8, 8, '2021-01-05'),
(9, 9, '2019-03-01'),
(10, 10, '2016-11-05'),
(11, 11, '2019-05-01'),
(12, 12, '2021-03-10'),
(13, 13, '2020-08-15'),
(14, 14, '2017-12-01'),
(15, 15, '2015-09-01'),
(16, 16, '2018-06-10'),
(17, 17, '2016-10-10'),
(18, 18, '2020-04-01'),
(19, 19, '2019-01-10'),
(20, 20, '2014-07-01'),
(21, 21, '2016-05-01'),
(22, 22, '2018-09-01'),
(23, 23, '2019-06-01'),
(24, 24, '2017-03-01'),
(25, 13, '2020-07-01'),
(26, 13, '2015-12-01'),
(27, 14, '2019-10-01'),
(28, 14, '2021-02-01'),
(29, 15, '2014-06-01'),
(30, 15, '2018-04-01'),
(31, 16, '2019-09-01'),
(32, 16, '2020-01-01'),
(33, 17, '2018-10-01'),
(34, 17, '2017-05-01'),
(35, 18, '2019-08-01'),
(36, 18, '2016-03-01'),
(37, 19, '2021-12-01'),
(38, 19, '2022-04-01'),
(39, 20, '2020-06-01'),
(40, 20, '2019-11-01');


-- CITAS


INSERT INTO ESTADO_CITA (id, nombre_estado) VALUES
(1, 'Pendiente'),
(2, 'Por confirmar'),
(3, 'Cancelada'),
(4, 'Reprogramada'),
(6, 'Finalizada'),
(7, 'No asistió');



INSERT INTO CITA (id, estado_cita_id, fecha_cita, observacion, CUSTODIAMASCOTAid, CUSTODIADUEniOid) 
VALUES
(1, 1, '2024-11-21', 'Consulta general para revisión anual', 1, 1),
(2, 2, '2024-11-22', 'Vacunación de rutina', 2, 2),
(3, 3, '2024-11-23', 'Chequeo cardiológico especial', 3, 3),
(4, 1, '2024-11-24', 'Desparasitación avanzada', 4, 4),
(5, 1, '2024-11-25', 'Consulta dermatológica por alergias', 5, 5),
(6, 2, '2024-11-26', 'Cirugía menor programada', 6, 6),
(7, 3, '2024-11-27', 'Emergencia nocturna', 7, 7),
(8, 1, '2024-11-28', 'Consulta geriátrica para mascota mayor', 8, 8),
(9, 1, '2024-11-29', 'Pruebas alérgicas solicitadas', 9, 9),
(10, 2, '2024-11-30', 'Limpieza dental programada', 10, 10),
(11, 3, '2024-12-01', 'Consulta de urgencia por accidente', 11, 11),
(12, 2, '2024-12-02', 'Terapia física para rehabilitación', 12, 12),
(13, 1, '2024-12-03', 'Chequeo oftalmológico por irritación', 13, 13),
(14, 1, '2024-12-04', 'Endoscopía programada', 14, 13), -- Ajustado para corresponder a CUSTODIA
(15, 3, '2024-12-05', 'Atención de emergencia por fractura', 15, 15),
(16, 2, '2024-12-06', 'Consulta exótica para reptil', 16, 16),
(17, 1, '2024-12-07', 'Consulta geriátrica', 17, 17),
(18, 3, '2024-12-08', 'Emergencia nocturna por intoxicación', 18, 18),
(19, 2, '2024-12-09', 'Vacunación múltiple', 19, 19),
(20, 1, '2024-12-10', 'Chequeo general con radiografía', 20, 20),
(21, 1, '2024-12-11', 'Consulta dermatológica por caída de pelo', 21, 21),
(22, 2, '2024-12-12', 'Pruebas alérgicas', 22, 22),
(23, 1, '2024-12-13', 'Consulta por dolor articular', 23, 23),
(24, 3, '2024-12-14', 'Consulta urgente por pérdida de apetito', 24, 24),
(25, 1, '2024-12-15', 'Chequeo cardiológico programado', 25, 13), -- Ajustado para corresponder a CUSTODIA
(26, 2, '2024-12-16', 'Limpieza dental', 26, 13), -- Ajustado
(27, 1, '2024-12-17', 'Cirugía menor para esterilización', 27, 14),
(28, 2, '2024-12-18', 'Consulta de segunda opinión', 28, 14),
(29, 1, '2024-12-19', 'Corte de uñas y chequeo', 29, 15),
(30, 3, '2024-12-20', 'Emergencia por envenenamiento', 30, 15);


INSERT INTO DETALLE_CITA (cita_id, detalle_servicio_serv_id, detalle_servicio_med_id, horaEntrada, horaSalida, nota_adicional) 
VALUES
(1, 1, 1, '09:00:00', '09:30:00', 'Consulta general sin complicaciones'),
(2, 2, 2, '10:00:00', '10:20:00', 'Vacunación antirrábica completada'),
(3, 9, 5, '11:00:00', '11:45:00', 'Chequeo de presión arterial normal'),
(4, 3, 3, '12:00:00', '12:30:00', 'Desparasitación realizada sin problemas'),
(5, 12, 6, '13:00:00', '13:40:00', 'Diagnóstico dermatológico inicial realizado'),
(6, 4, 4, '14:00:00', '16:00:00', 'Cirugía menor completada con éxito'),
(7, 5, 7, '01:00:00', '01:45:00', 'Atención de emergencia satisfactoria'),
(8, 24, 8, '15:00:00', '15:30:00', 'Consulta geriátrica con recomendaciones nutricionales'),
(9, 28, 9, '16:00:00', '16:45:00', 'Pruebas alérgicas completadas sin reacciones adversas'),
(10, 8, 10, '17:00:00', '17:30:00', 'Limpieza dental realizada'),
(11, 11, 11, '09:30:00', '10:00:00', 'Atención a herida superficial'),
(12, 20, 12, '10:30:00', '11:00:00', 'Masaje terapéutico aplicado con éxito'),
(13, 27, 13, '11:30:00', '12:00:00', 'Consulta oftalmológica sin hallazgos'),
(14, 18, 14, '12:30:00', '13:30:00', 'Endoscopía realizada con diagnóstico inicial'),
(15, 5, 15, '01:00:00', '01:30:00', 'Emergencia por fractura estabilizada'),
(16, 30, 16, '14:30:00', '15:00:00', 'Consulta exótica para reptil completada'),
(17, 24, 17, '15:30:00', '16:00:00', 'Consulta geriátrica con recomendaciones de ejercicios'),
(18, 7, 18, '16:30:00', '17:00:00', 'Atención nocturna por intoxicación resuelta'),
(19, 21, 19, '17:30:00', '18:00:00', 'Vacunación múltiple sin reacciones adversas'),
(20, 6, 20, '18:30:00', '19:00:00', 'Radiografía para diagnóstico general'),
(21, 3, 1, '08:00:00', '08:30:00', 'Consulta dermatológica con diagnóstico inicial'),
(22, 12, 2, '08:45:00', '09:15:00', 'Pruebas alérgicas completadas'),
(23, 10, 3, '09:30:00', '10:00:00', 'Tratamiento dental básico realizado'),
(24, 13, 4, '10:15:00', '10:45:00', 'Chequeo cardiológico de rutina'),
(25, 5, 5, '11:00:00', '11:30:00', 'Consulta dermatológica programada'),
(26, 2, 6, '11:45:00', '12:15:00', 'Vacunación completada'),
(27, 1, 7, '12:30:00', '13:00:00', 'Consulta general sin hallazgos relevantes'),
(28, 8, 8, '13:15:00', '13:45:00', 'Limpieza dental programada'),
(29, 14, 9, '14:00:00', '14:30:00', 'Chequeo geriátrico detallado'),
(30, 9, 10, '14:45:00', '15:15:00', 'Consulta oftalmológica finalizada'),
(31, 15, 11, '15:30:00', '16:00:00', 'Atención de emergencia'),
(32, 19, 12, '16:15:00', '16:45:00', 'Corte de uñas'),
(33, 4, 13, '17:00:00', '17:30:00', 'Consulta general'),
(34, 23, 14, '17:45:00', '18:15:00', 'Chequeo geriátrico programado'),
(35, 22, 15, '18:30:00', '19:00:00', 'Consulta de segunda opinión'),
(36, 21, 16, '19:15:00', '19:45:00', 'Consulta dermatológica'),
(37, 13, 17, '20:00:00', '20:30:00', 'Endoscopía programada'),
(38, 17, 18, '20:45:00', '21:15:00', 'Consulta geriátrica'),
(39, 14, 19, '21:30:00', '22:00:00', 'Limpieza dental programada'),
(40, 6, 20, '22:15:00', '22:45:00', 'Radiografía para diagnóstico general');


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


INSERT INTO DETALLE_MEDICAMENTO (medicamento_id, detalle_cita_id, detalle_servicio_servicio_id, detalle_servicio_medico_id, dosis, indicacion, cantidad) VALUES
(1, 1, 1, 1, 250.00, 'Administrar cada 8 horas por 7 días', 21),
(2, 1, 2, 1, 0.50, 'Tomar cada 12 horas en caso de dolor', 14),
(3, 2, 2, 3, 150.00, 'Desparasitar según peso, una vez', 1),
(4, 3, 3, 2, 25.00, 'Administrar cada 12 horas por 5 días', 10),
(5, 2, 2, 1, 0.00, 'Vacuna aplicada subcutánea', 1),
(6, 4, 4, 3, 1.00, 'Inyectar cada semana por 3 semanas', 3),
(7, 5, 5, 4, 10.00, 'Administrar en caso de inflamación', 5),
(8, 6, 1, 2, 0.00, 'Vacuna subcutánea, dosis única', 1),
(9, 7, 2, 3, 50.00, 'Administrar cada 6 horas por dolor', 28),
(10, 8, 3, 4, 100.00, 'Tomar una cápsula diaria por 10 días', 10),
(1, 9, 4, 5, 250.00, 'Tratamiento de infección, 7 días', 21),
(2, 10, 5, 1, 0.50, 'Alivio del dolor, cada 12 horas', 14),
(3, 11, 1, 3, 150.00, 'Desparasitación preventiva', 1),
(4, 12, 2, 4, 25.00, 'Administrar cada 12 horas por 5 días', 10),
(5, 13, 3, 5, 0.00, 'Aplicación subcutánea anual', 1),
(6, 14, 4, 2, 1.00, 'Tratamiento antiparasitario semanal', 3),
(7, 15, 5, 3, 10.00, 'Inflamación, tomar según necesidad', 5),
(8, 16, 1, 4, 0.00, 'Vacuna única para prevención', 1),
(9, 17, 2, 5, 50.00, 'Control del dolor, cada 6 horas', 28),
(10, 18, 3, 1, 100.00, 'Tomar cada 24 horas por 7 días', 7),
(1, 19, 4, 2, 250.00, 'Administrar durante recuperación', 14),
(2, 20, 5, 3, 0.50, 'Uso según necesidad del paciente', 10),
(3, 21, 1, 5, 150.00, 'Desparasitación en seguimiento', 1),
(4, 22, 2, 1, 25.00, 'Dosis ajustada según peso', 10),
(5, 23, 3, 4, 0.00, 'Vacunación complementaria', 1),
(6, 24, 4, 3, 1.00, 'Tratamiento semanal', 4),
(7, 25, 5, 2, 10.00, 'Administrar según signos clínicos', 5);


















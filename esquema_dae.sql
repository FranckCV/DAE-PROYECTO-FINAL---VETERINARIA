create table USUARIO (
  codUsuario int4 not null, 
  nomusuario varchar(30) not null unique, 
  estado     bool not null, 
  sexo       bool not null, 
  clave      varchar(150) not null, 
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

CREATE TABLE MEDICO (
  id                int4 NOT NULL, 
  nombres           varchar(150) NOT NULL, 
  apePaterno        varchar(200) NOT NULL, 
  apeMaterno        varchar(200) NOT NULL, 
  doc_identidad     varchar(20) NOT NULL, 
  sexo              bool NOT NULL, 
  disponibilidad    bool NOT NULL, 
  vigencia          bool NOT NULL, 
  especialidad_id   int4 NOT NULL, 
  USUARIOcodUsuario int4 NOT NULL, 
  PRIMARY KEY (id));

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
  nota_adicional           text, 
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


ALTER TABLE MEDICO ADD CONSTRAINT FKMEDICO619165 FOREIGN KEY (USUARIOcodUsuario) REFERENCES USUARIO (codUsuario);
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





CREATE OR REPLACE FUNCTION contar_relaciones_compuestas(tabla_base TEXT, id_base1 INT, id_base2 INT)
    RETURNS TABLE(tabla_relacionada TEXT, columnas_foraneas TEXT, cantidad INT) AS $$
DECLARE
    consulta_contar TEXT;
    cuenta INT;
    columnas TEXT[];
BEGIN
    FOR tabla_relacionada, columnas IN
        SELECT
            c.conrelid::regclass::text AS tabla_relacionada,
            ARRAY_AGG(a.attname) AS columnas_foraneas
        FROM
            pg_constraint c
        JOIN pg_class t ON c.conrelid = t.oid
        JOIN pg_attribute a ON a.attnum = ANY (c.conkey) AND a.attrelid = c.conrelid
        WHERE
            c.confrelid = tabla_base::regclass
            AND c.contype = 'f'
        GROUP BY c.conrelid
    LOOP
        -- Verifica si hay exactamente 2 columnas en la clave compuesta
        IF array_length(columnas, 1) = 2 THEN
            consulta_contar := FORMAT(
                'SELECT COUNT(*) FROM %I WHERE (%I, %I) = ($1, $2)',
                tabla_relacionada,
                columnas[1],
                columnas[2]
            );

            EXECUTE consulta_contar INTO cuenta USING id_base1, id_base2;

            RETURN QUERY SELECT tabla_relacionada, array_to_string(columnas, ', '), cuenta;
        END IF;
    END LOOP;
END $$ LANGUAGE plpgsql;





-----------------------------------------------------------------------------------------------------------------------------------------------------




--USUARIOS
INSERT INTO USUARIO (codUsuario, nomusuario, estado, sexo, clave, nombres, apPaterno, apMaterno, cargo) 
VALUES 
(1, 'jdoe', TRUE, TRUE, md5('1234' || 'jdoe' || 'CODE146'), 'John', 'Doe', 'Smith', 'A'),
(2, 'mgarcia', TRUE, FALSE, md5('4567' || 'mgarcia' || 'CODE146'), 'Maria', 'Garcia', 'Lopez', 'E'),
(3, 'arivera', TRUE, TRUE,  md5('7890' || 'arivera' || 'CODE146'), 'Alex', 'Rivera', 'Martinez', 'A'),
(4, 'lfernandez', FALSE, FALSE, md5('1011' || 'lfernandez' || 'CODE146'), 'Laura', 'Fernandez', 'Soto', 'V'),
(5, 'cperez', TRUE, TRUE, md5('1021' || 'cperez' || 'CODE146'), 'Carlos', 'Perez', 'Gutierrez', 'E'),
(6, 'asanchez', FALSE, TRUE, md5('1031' || 'asanchez' || 'CODE146'), 'Andres', 'Sanchez', 'Lopez', 'V'),
(7, 'fabi', FALSE, TRUE, md5('123' || 'fabi' || 'CODE146'), 'Fabiana', 'Paucar', 'Mejia', 'V'),
(8, 'Admin_Fab', TRUE, TRUE, md5('123' || 'Admin_Fab' || 'CODE146'), 'Fabiana', 'Paucar', 'Mejia', 'V'),
(9, 'dtorres', TRUE, TRUE, md5('2024' || 'dtorres' || 'CODE146'), 'David', 'Torres', 'Guzman', 'V'),
(10, 'slopez', TRUE, FALSE, md5('3035' || 'slopez' || 'CODE146'), 'Sofia', 'Lopez', 'Mendoza', 'V'),
(11, 'emolina', TRUE, TRUE, md5('4046' || 'emolina' || 'CODE146'), 'Erick', 'Molina', 'Campos', 'V'),
(12, 'fluna', FALSE, TRUE, md5('5057' || 'fluna' || 'CODE146'), 'Fernando', 'Luna', 'Rivera', 'V'),
(13, 'gprieto', TRUE, FALSE, md5('6068' || 'gprieto' || 'CODE146'), 'Gabriela', 'Prieto', 'Cruz', 'V'),
(14, 'vmartinez', TRUE, TRUE, md5('7079' || 'vmartinez' || 'CODE146'), 'Victor', 'Martinez', 'Reyes', 'V'),
(15, 'rrios', FALSE, TRUE, md5('8080' || 'rrios' || 'CODE146'), 'Rafael', 'Rios', 'Gonzalez', 'V'),
(16, 'mcarrillo', TRUE, TRUE, md5('9091' || 'mcarrillo' || 'CODE146'), 'María', 'Carrillo', 'Soto', 'V'),
(17, 'acastro', TRUE, FALSE, md5('1011' || 'acastro' || 'CODE146'), 'Andrea', 'Castro', 'Velasco', 'V'),
(18, 'fjimenez', TRUE, TRUE, md5('1213' || 'fjimenez' || 'CODE146'), 'Francisco', 'Jimenez', 'Rojas', 'V'),
(19, 'cluna', TRUE, TRUE, md5('1314' || 'cluna' || 'CODE146'), 'Carlos', 'Luna', 'Gutierrez', 'V'),
(20, 'mfernandez', TRUE, FALSE, md5('1415' || 'mfernandez' || 'CODE146'), 'Mariana', 'Fernandez', 'Perez', 'V'),
(21, 'jmartinez', FALSE, TRUE, md5('1516' || 'jmartinez' || 'CODE146'), 'Javier', 'Martinez', 'Sanchez', 'V'),
(22, 'lorozco', TRUE, FALSE, md5('1617' || 'lorozco' || 'CODE146'), 'Lucia', 'Orozco', 'Mora', 'V'),
(23, 'ggarcia', TRUE, TRUE, md5('1718' || 'ggarcia' || 'CODE146'), 'Gustavo', 'Garcia', 'Vargas', 'V'),
(24, 'nsolano', FALSE, TRUE, md5('1819' || 'nsolano' || 'CODE146'), 'Nicolas', 'Solano', 'Vega', 'V'),
(25, 'rmendez', TRUE, TRUE, md5('1920' || 'rmendez' || 'CODE146'), 'Rosa', 'Mendez', 'Ortiz', 'V'),
(26, 'dvera', TRUE, TRUE, md5('2021' || 'dvera' || 'CODE146'), 'Diego', 'Vera', 'Lopez', 'V'),
(27, 'kcastillo', TRUE, FALSE, md5('2122' || 'kcastillo' || 'CODE146'), 'Karen', 'Castillo', 'Guzman', 'V'),
(28, 'pfigueroa', TRUE, TRUE, md5('2223' || 'pfigueroa' || 'CODE146'), 'Pedro', 'Figueroa', 'Chavez', 'V'),
(29, 'amendoza', TRUE, FALSE, md5('2324' || 'amendoza' || 'CODE146'), 'Ana', 'Mendoza', 'Salazar', 'V'),
(30, 'cmontero', TRUE, TRUE, md5('2425' || 'cmontero' || 'CODE146'), 'Carlos', 'Montero', 'Reyes', 'V'),
(31, 'vmorales', FALSE, TRUE, md5('2526' || 'vmorales' || 'CODE146'), 'Victor', 'Morales', 'Lopez', 'V'),
(32, 'lleon', TRUE, TRUE, md5('2627' || 'lleon' || 'CODE146'), 'Luis', 'León', 'Gonzalez', 'V'),
(33, 'aflores', TRUE, FALSE, md5('2728' || 'aflores' || 'CODE146'), 'Adriana', 'Flores', 'Mejia', 'V'),
(34, 'jbustamante', TRUE, TRUE, md5('2829' || 'jbustamante' || 'CODE146'), 'Jose', 'Bustamante', 'Campos', 'V'),
(35, 'mmiranda', TRUE, FALSE, md5('2930' || 'mmiranda' || 'CODE146'), 'Maria', 'Miranda', 'Ortiz', 'V'),
(36, 'gvelasquez', TRUE, TRUE, md5('3031' || 'gvelasquez' || 'CODE146'), 'Gabriel', 'Velasquez', 'Mendoza', 'V'),
(37, 'krojas', FALSE, FALSE, md5('3132' || 'krojas' || 'CODE146'), 'Karla', 'Rojas', 'Fernandez', 'V'),
(38, 'ffuentes', TRUE, TRUE, md5('3233' || 'ffuentes' || 'CODE146'), 'Fernando', 'Fuentes', 'Diaz', 'V'),
(39, 'hcardenas', TRUE, TRUE, md5('3334' || 'hcardenas' || 'CODE146'), 'Hugo', 'Cárdenas', 'Ramirez', 'V');




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


INSERT INTO MEDICO (id, nombres, apePaterno, apeMaterno, doc_identidad, sexo, disponibilidad, vigencia, especialidad_id, USUARIOcodUsuario) 
VALUES 
(1, 'Laura', 'Fernandez', 'Soto', '10000001', FALSE, TRUE, TRUE, 1, 4),
(2, 'Andres', 'Sanchez', 'Lopez', '10000002', TRUE, TRUE, TRUE, 1, 6),
(3, 'Fabiana', 'Paucar', 'Mejia', '10000003', TRUE, TRUE, TRUE, 2, 7),
(4, 'Fabiana', 'Paucar', 'Mejia', '10000004', TRUE, TRUE, TRUE, 2, 8),
(5, 'David', 'Torres', 'Guzman', '10000005', TRUE, TRUE, TRUE, 3, 9),
(6, 'Sofia', 'Lopez', 'Mendoza', '10000006', FALSE, TRUE, TRUE, 3, 10),
(7, 'Erick', 'Molina', 'Campos', '10000007', TRUE, TRUE, TRUE, 4, 11),
(8, 'Fernando', 'Luna', 'Rivera', '10000008', TRUE, TRUE, TRUE, 4, 12),
(9, 'Gabriela', 'Prieto', 'Cruz', '10000009', FALSE, TRUE, TRUE, 5, 13),
(10, 'Victor', 'Martinez', 'Reyes', '10000010', TRUE, TRUE, TRUE, 5, 14),
(11, 'Rafael', 'Rios', 'Gonzalez', '10000011', TRUE, TRUE, TRUE, 6, 15),
(12, 'María', 'Carrillo', 'Soto', '10000012', TRUE, TRUE, TRUE, 6, 16),
(13, 'Andrea', 'Castro', 'Velasco', '10000013', FALSE, TRUE, TRUE, 7, 17),
(14, 'Francisco', 'Jimenez', 'Rojas', '10000014', TRUE, TRUE, TRUE, 7, 18),
(15, 'Carlos', 'Luna', 'Gutierrez', '10000015', TRUE, TRUE, TRUE, 8, 19),
(16, 'Mariana', 'Fernandez', 'Perez', '10000016', FALSE, TRUE, TRUE, 8, 20),
(17, 'Javier', 'Martinez', 'Sanchez', '10000017', TRUE, TRUE, TRUE, 9, 21),
(18, 'Lucia', 'Orozco', 'Mora', '10000018', FALSE, TRUE, TRUE, 9, 22),
(19, 'Gustavo', 'Garcia', 'Vargas', '10000019', TRUE, TRUE, TRUE, 10, 23),
(20, 'Nicolas', 'Solano', 'Vega', '10000020', TRUE, TRUE, TRUE, 10, 24),
(21, 'Rosa', 'Mendez', 'Ortiz', '10000021', TRUE, TRUE, TRUE, 11, 25),
(22, 'Diego', 'Vera', 'Lopez', '10000022', TRUE, TRUE, TRUE, 11, 26),
(23, 'Karen', 'Castillo', 'Guzman', '10000023', FALSE, TRUE, TRUE, 12, 27),
(24, 'Pedro', 'Figueroa', 'Chavez', '10000024', TRUE, TRUE, TRUE, 12, 28),
(25, 'Ana', 'Mendoza', 'Salazar', '10000025', FALSE, TRUE, TRUE, 13, 29),
(26, 'Carlos', 'Montero', 'Reyes', '10000026', TRUE, TRUE, TRUE, 13, 30),
(27, 'Victor', 'Morales', 'Lopez', '10000027', TRUE, TRUE, TRUE, 14, 31),
(28, 'Luis', 'León', 'Gonzalez', '10000028', TRUE, TRUE, TRUE, 14, 32),
(29, 'Adriana', 'Flores', 'Mejia', '10000029', FALSE, TRUE, TRUE, 15, 33),
(30, 'Jose', 'Bustamante', 'Campos', '10000030', TRUE, TRUE, TRUE, 15, 34),
(31, 'Maria', 'Miranda', 'Ortiz', '10000031', FALSE, TRUE, TRUE, 16, 35),
(32, 'Gabriel', 'Velasquez', 'Mendoza', '10000032', TRUE, TRUE, TRUE, 16, 36),
(33, 'Karla', 'Rojas', 'Fernandez', '10000033', FALSE, TRUE, TRUE, 17, 37),
(34, 'Fernando', 'Fuentes', 'Diaz', '10000034', TRUE, TRUE, TRUE, 17, 38),
(35, 'Hugo', 'Cárdenas', 'Ramirez', '10000035', TRUE, TRUE, TRUE, 18, 39);



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
(2, 'Cancelada'),
(3, 'Asistió'),
(4, 'No asistió');



-- Enero
INSERT INTO CITA (id, estado_cita_id, fecha_cita, observacion, CUSTODIAMASCOTAid, CUSTODIADUEniOid) 
VALUES
(1, 3, '2024-01-01', 'Consulta general para revisión anual', 1, 1),
(2, 2, '2024-01-02', 'Revisión de salud y vacunación', 2, 2),
(3, 3, '2024-01-03', 'Control de parasitosis', 3, 3),
(4, 3, '2024-01-04', 'Chequeo de comportamiento', 4, 4),
(5, 3, '2024-01-05', 'Revisión de dieta y peso', 5, 5),
(6, 3, '2024-01-06', 'Consulta para revisión dental', 6, 6),
(7, 2, '2024-01-07', 'Revisión ocular y auditiva', 7, 7),
(8, 3, '2024-01-08', 'Chequeo de salud general', 8, 8),
(9, 3, '2024-01-09', 'Control de peso y alimentación', 9, 9),
(10, 2, '2024-01-10', 'Consulta por dolor en las patas', 10, 10),
(11, 3, '2024-01-11', 'Revisión y control de parásitos internos', 11, 11),
(12, 3, '2024-01-12', 'Examen general y vacunas', 12, 12),
(13, 2, '2024-01-13', 'Revisión periódica para chequeo de salud', 13, 13),
(14, 3, '2024-01-14', 'Consulta por problemas respiratorios', 14, 14),
(15, 3, '2024-01-15', 'Revisión del comportamiento y socialización', 15, 15),
(16, 2, '2024-01-16', 'Chequeo de salud y cuidados generales', 16, 16),
(17, 3, '2024-01-17', 'Consulta para revisión dental', 17, 17),
(18, 3, '2024-01-18', 'Revisión general de estado físico', 18, 18),
(19, 2, '2024-01-19', 'Revisión ocular y auditiva', 19, 19),
(20, 3, '2024-01-20', 'Control de parásitos y bienestar general', 20, 20),
(21, 3, '2024-01-21', 'Chequeo de salud y vacunación', 21, 21),
(22, 2, '2024-01-22', 'Control de salud', 22, 22),
(23, 3, '2024-01-23', 'Examen de comportamiento y salud', 23, 23),
(24, 3, '2024-01-24', 'Chequeo general', 24, 24),
(25, 2, '2024-01-25', 'Revisión dental y control de salud', 25, 13),
(26, 3, '2024-01-26', 'Revisión periódica de salud general', 26, 13),
(27, 3, '2024-01-27', 'Chequeo de alimentación y salud', 27, 14),
(28, 2, '2024-01-28', 'Consulta de seguimiento', 28, 14),
(29, 3, '2024-01-29', 'Examen general',29, 15),
(30, 3, '2024-01-30', 'Chequeo anual', 30, 15),
(31, 2, '2024-01-31', 'Consulta por alergias', 31, 16),
(32, 3, '2024-01-31', 'Control de salud general', 32, 16),
(33, 1, '2024-01-31', 'Revisión y control de peso', 33, 17),
(34, 2, '2024-01-31', 'Chequeo general', 34, 17),
(35, 3, '2024-01-31', 'Examen completo', 35, 18),
(36, 1, '2024-01-31', 'Chequeo de salud', 36, 18),
(37, 2, '2024-01-31', 'Revisión dental y salud', 37, 19),
(38, 3, '2024-01-31', 'Chequeo general de salud', 38, 19),
(39, 1, '2024-01-31', 'Control de parásitos', 39, 20),
(40, 2, '2024-01-31', 'Chequeo completo', 40, 20);

-- Febrero
INSERT INTO CITA (id, estado_cita_id, fecha_cita, observacion, CUSTODIAMASCOTAid, CUSTODIADUEniOid) 
VALUES
(41, 1, '2024-02-01', 'Consulta general para revisión anual', 1, 1),
(42, 2, '2024-02-02', 'Revisión de salud y vacunación', 2, 2),
(43, 3, '2024-02-03', 'Control de parasitosis', 3, 3),
(44, 1, '2024-02-04', 'Chequeo de comportamiento', 4, 4),
(45, 3, '2024-02-05', 'Revisión de dieta y peso', 5, 5),
(46, 1, '2024-02-06', 'Consulta para revisión dental', 6, 6),
(47, 2, '2024-02-07', 'Revisión ocular y auditiva', 7, 7),
(48, 3, '2024-02-08', 'Chequeo de salud general', 8, 8),
(49, 1, '2024-02-09', 'Control de peso y alimentación', 9, 9),
(50, 2, '2024-02-10', 'Consulta por dolor en las patas', 10, 10),
(51, 3, '2024-02-11', 'Revisión y control de parásitos internos', 11, 11),
(52, 1, '2024-02-12', 'Examen general y vacunas', 12, 12),
(53, 2, '2024-02-13', 'Revisión periódica para chequeo de salud', 13, 13),
(54, 3, '2024-02-14', 'Consulta por problemas respiratorios', 14, 14),
(55, 1, '2024-02-15', 'Revisión del comportamiento y socialización', 15, 15),
(56, 2, '2024-02-16', 'Chequeo de salud y cuidados generales', 16, 16),
(57, 3, '2024-02-17', 'Consulta para revisión dental', 17, 17),
(58, 1, '2024-02-18', 'Revisión general de estado físico', 18, 18),
(59, 2, '2024-02-19', 'Revisión ocular y auditiva', 19, 19),
(60, 3, '2024-02-20', 'Control de parásitos y bienestar general', 20, 20),
(61, 1, '2024-02-21', 'Chequeo de salud y vacunación', 21, 21),
(62, 2, '2024-02-22', 'Control de salud', 22, 22),
(63, 3, '2024-02-23', 'Examen de comportamiento y salud', 23, 23),
(64, 1, '2024-02-24', 'Chequeo general', 24, 24),
(65, 2, '2024-02-25', 'Revisión dental y control de salud', 25, 13);

-- Marzo
INSERT INTO CITA (id, estado_cita_id, fecha_cita, observacion, CUSTODIAMASCOTAid, CUSTODIADUEniOid) 
VALUES
(66, 1, '2024-03-01', 'Consulta general para revisión anual', 1, 1),
(67, 2, '2024-03-02', 'Revisión de salud y vacunación', 2, 2),
(68, 3, '2024-03-03', 'Control de parasitosis', 3, 3),
(69, 1, '2024-03-04', 'Chequeo de comportamiento', 4, 4),
(70, 3, '2024-03-05', 'Revisión de dieta y peso', 5, 5),
(71, 1, '2024-03-06', 'Consulta para revisión dental', 6, 6),
(72, 2, '2024-03-07', 'Revisión ocular y auditiva', 7, 7),
(73, 3, '2024-03-08', 'Chequeo de salud general', 8, 8),
(74, 1, '2024-03-09', 'Control de peso y alimentación', 9, 9),
(75, 2, '2024-03-10', 'Consulta por dolor en las patas', 10, 10),
(76, 3, '2024-03-11', 'Revisión y control de parásitos internos', 11, 11),
(77, 1, '2024-03-12', 'Examen general y vacunas', 12, 12),
(78, 2, '2024-03-13', 'Revisión periódica para chequeo de salud', 13, 13),
(79, 3, '2024-03-14', 'Consulta por problemas respiratorios', 14, 14),
(80, 1, '2024-03-15', 'Revisión del comportamiento y socialización', 15, 15),
(81, 2, '2024-03-16', 'Chequeo de salud y cuidados generales', 16, 16),
(82, 3, '2024-03-17', 'Consulta para revisión dental', 17, 17),
(83, 1, '2024-03-18', 'Revisión general de estado físico', 18, 18),
(84, 2, '2024-03-19', 'Revisión ocular y auditiva', 19, 19),
(85, 3, '2024-03-20', 'Control de parásitos y bienestar general', 20, 20);

-- Abril
INSERT INTO CITA (id, estado_cita_id, fecha_cita, observacion, CUSTODIAMASCOTAid, CUSTODIADUEniOid) 
VALUES
(86, 1, '2024-04-01', 'Consulta general para revisión anual', 1, 1),
(87, 2, '2024-04-02', 'Revisión de salud y vacunación', 2, 2),
(88, 3, '2024-04-03', 'Control de parasitosis', 3, 3),
(89, 1, '2024-04-04', 'Chequeo de comportamiento', 4, 4),
(90, 3, '2024-04-05', 'Revisión de dieta y peso', 5, 5),
(91, 1, '2024-04-06', 'Consulta para revisión dental', 6, 6),
(92, 2, '2024-04-07', 'Revisión ocular y auditiva', 7, 7),
(93, 3, '2024-04-08', 'Chequeo de salud general', 8, 8),
(94, 1, '2024-04-09', 'Control de peso y alimentación', 9, 9),
(95, 2, '2024-04-10', 'Consulta por dolor en las patas', 10, 10),
(96, 3, '2024-04-11', 'Revisión y control de parásitos internos', 11, 11),
(97, 1, '2024-04-12', 'Examen general y vacunas', 12, 12),
(98, 2, '2024-04-13', 'Revisión periódica para chequeo de salud', 13, 13),
(99, 3, '2024-04-14', 'Consulta por problemas respiratorios', 14, 14),
(100, 1, '2024-04-15', 'Revisión del comportamiento y socialización', 15, 15),
(101, 2, '2024-04-16', 'Chequeo de salud y cuidados generales', 16, 16),
(102, 3, '2024-04-17', 'Consulta para revisión dental', 17, 17),
(103, 1, '2024-04-18', 'Revisión general de estado físico', 18, 18),
(104, 2, '2024-04-19', 'Revisión ocular y auditiva', 19, 19);

-- Mayo
INSERT INTO CITA (id, estado_cita_id, fecha_cita, observacion, CUSTODIAMASCOTAid, CUSTODIADUEniOid) 
VALUES
(105, 1, '2024-05-01', 'Consulta general para revisión anual', 1, 1),
(106, 2, '2024-05-02', 'Revisión de salud y vacunación', 2, 2),
(107, 3, '2024-05-03', 'Control de parasitosis', 3, 3),
(108, 1, '2024-05-04', 'Chequeo de comportamiento', 4, 4),
(109, 3, '2024-05-05', 'Revisión de dieta y peso', 5, 5),
(110, 1, '2024-05-06', 'Consulta para revisión dental', 6, 6),
(111, 2, '2024-05-07', 'Revisión ocular y auditiva', 7, 7),
(112, 3, '2024-05-08', 'Chequeo de salud general', 8, 8),
(113, 1, '2024-05-09', 'Control de peso y alimentación', 9, 9),
(114, 2, '2024-05-10', 'Consulta por dolor en las patas', 10, 10),
(115, 3, '2024-05-11', 'Revisión y control de parásitos internos', 11, 11),
(116, 1, '2024-05-12', 'Examen general y vacunas', 12, 12),
(117, 2, '2024-05-13', 'Revisión periódica para chequeo de salud', 13, 13),
(118, 3, '2024-05-14', 'Consulta por problemas respiratorios', 14, 14),
(119, 1, '2024-05-15', 'Revisión del comportamiento y socialización', 15, 15),
(120, 2, '2024-05-16', 'Chequeo de salud y cuidados generales', 16, 16),
(121, 3, '2024-05-17', 'Consulta para revisión dental', 17, 17),
(122, 1, '2024-05-18', 'Revisión general de estado físico', 18, 18),
(123, 2, '2024-05-19', 'Revisión ocular y auditiva', 19, 19);

-- Junio
INSERT INTO CITA (id, estado_cita_id, fecha_cita, observacion, CUSTODIAMASCOTAid, CUSTODIADUEniOid) 
VALUES
(124, 1, '2024-06-01', 'Consulta general para revisión anual', 1, 1),
(125, 2, '2024-06-02', 'Revisión de salud y vacunación', 2, 2),
(126, 3, '2024-06-03', 'Control de parasitosis', 3, 3),
(127, 1, '2024-06-04', 'Chequeo de comportamiento', 4, 4),
(128, 3, '2024-06-05', 'Revisión de dieta y peso', 5, 5),
(129, 1, '2024-06-06', 'Consulta para revisión dental', 6, 6),
(130, 2, '2024-06-07', 'Revisión ocular y auditiva', 7, 7),
(131, 3, '2024-06-08', 'Chequeo de salud general', 8, 8),
(132, 1, '2024-06-09', 'Control de peso y alimentación', 9, 9),
(133, 2, '2024-06-10', 'Consulta por dolor en las patas', 10, 10),
(134, 3, '2024-06-11', 'Revisión y control de parásitos internos', 11, 11),
(135, 1, '2024-06-12', 'Examen general y vacunas', 12, 12),
(136, 2, '2024-06-13', 'Revisión periódica para chequeo de salud', 13, 13),
(137, 3, '2024-06-14', 'Consulta por problemas respiratorios', 14, 14),
(138, 1, '2024-06-15', 'Revisión del comportamiento y socialización', 15, 15),
(139, 2, '2024-06-16', 'Chequeo de salud y cuidados generales', 16, 16),
(140, 3, '2024-06-17', 'Consulta para revisión dental', 17, 17),
(141, 1, '2024-06-18', 'Revisión general de estado físico', 18, 18),
(142, 2, '2024-06-19', 'Revisión ocular y auditiva', 19, 19);

-- Julio
INSERT INTO CITA (id, estado_cita_id, fecha_cita, observacion, CUSTODIAMASCOTAid, CUSTODIADUEniOid) 
VALUES
(143, 1, '2024-07-01', 'Consulta general para revisión anual', 1, 1),
(144, 2, '2024-07-02', 'Revisión de salud y vacunación', 2, 2),
(145, 3, '2024-07-03', 'Control de parasitosis', 3, 3),
(146, 1, '2024-07-04', 'Chequeo de comportamiento', 4, 4),
(147, 3, '2024-07-05', 'Revisión de dieta y peso', 5, 5),
(148, 1, '2024-07-06', 'Consulta para revisión dental', 6, 6),
(149, 2, '2024-07-07', 'Revisión ocular y auditiva', 7, 7),
(150, 3, '2024-07-08', 'Chequeo de salud general', 8, 8),
(151, 1, '2024-07-09', 'Control de peso y alimentación', 9, 9),
(152, 2, '2024-07-10', 'Consulta por dolor en las patas', 10, 10),
(153, 3, '2024-07-11', 'Revisión y control de parásitos internos', 11, 11),
(154, 1, '2024-07-12', 'Examen general y vacunas', 12, 12),
(155, 2, '2024-07-13', 'Revisión periódica para chequeo de salud', 13, 13),
(156, 3, '2024-07-14', 'Consulta por problemas respiratorios', 14, 14),
(157, 1, '2024-07-15', 'Revisión del comportamiento y socialización', 15, 15),
(158, 2, '2024-07-16', 'Chequeo de salud y cuidados generales', 16, 16),
(159, 3, '2024-07-17', 'Consulta para revisión dental', 17, 17),
(160, 1, '2024-07-18', 'Revisión general de estado físico', 18, 18),
(161, 2, '2024-07-19', 'Revisión ocular y auditiva', 19, 19);

-- Agosto
INSERT INTO CITA (id, estado_cita_id, fecha_cita, observacion, CUSTODIAMASCOTAid, CUSTODIADUEniOid) 
VALUES
(162, 1, '2024-08-01', 'Consulta general para revisión anual', 1, 1),
(163, 2, '2024-08-02', 'Revisión de salud y vacunación', 2, 2),
(164, 3, '2024-08-03', 'Control de parasitosis', 3, 3),
(165, 1, '2024-08-04', 'Chequeo de comportamiento', 4, 4),
(166, 3, '2024-08-05', 'Revisión de dieta y peso', 5, 5),
(167, 1, '2024-08-06', 'Consulta para revisión dental', 6, 6),
(168, 2, '2024-08-07', 'Revisión ocular y auditiva', 7, 7),
(169, 3, '2024-08-08', 'Chequeo de salud general', 8, 8),
(170, 1, '2024-08-09', 'Control de peso y alimentación', 9, 9),
(171, 2, '2024-08-10', 'Consulta por dolor en las patas', 10, 10),
(172, 3, '2024-08-11', 'Revisión y control de parásitos internos', 11, 11),
(173, 1, '2024-08-12', 'Examen general y vacunas', 12, 12),
(174, 2, '2024-08-13', 'Revisión periódica para chequeo de salud', 13, 13),
(175, 3, '2024-08-14', 'Consulta por problemas respiratorios', 14, 14),
(176, 1, '2024-08-15', 'Revisión del comportamiento y socialización', 15, 15),
(177, 2, '2024-08-16', 'Chequeo de salud y cuidados generales', 16, 16),
(178, 3, '2024-08-17', 'Consulta para revisión dental', 17, 17),
(179, 1, '2024-08-18', 'Revisión general de estado físico', 18, 18),
(180, 2, '2024-08-19', 'Revisión ocular y auditiva', 19, 19);

-- Septiembre
INSERT INTO CITA (id, estado_cita_id, fecha_cita, observacion, CUSTODIAMASCOTAid, CUSTODIADUEniOid) 
VALUES
(181, 1, '2024-09-01', 'Consulta general para revisión anual', 1, 1),
(182, 2, '2024-09-02', 'Revisión de salud y vacunación', 2, 2),
(183, 3, '2024-09-03', 'Control de parasitosis', 3, 3),
(184, 1, '2024-09-04', 'Chequeo de comportamiento', 4, 4),
(185, 3, '2024-09-05', 'Revisión de dieta y peso', 5, 5),
(186, 1, '2024-09-06', 'Consulta para revisión dental', 6, 6),
(187, 2, '2024-09-07', 'Revisión ocular y auditiva', 7, 7),
(188, 3, '2024-09-08', 'Chequeo de salud general', 8, 8),
(189, 1, '2024-09-09', 'Control de peso y alimentación', 9, 9),
(190, 2, '2024-09-10', 'Consulta por dolor en las patas', 10, 10),
(191, 3, '2024-09-11', 'Revisión y control de parásitos internos', 11, 11),
(192, 1, '2024-09-12', 'Examen general y vacunas', 12, 12),
(193, 2, '2024-09-13', 'Revisión periódica para chequeo de salud', 13, 13),
(194, 3, '2024-09-14', 'Consulta por problemas respiratorios', 14, 14),
(195, 1, '2024-09-15', 'Revisión del comportamiento y socialización', 15, 15),
(196, 2, '2024-09-16', 'Chequeo de salud y cuidados generales', 16, 16),
(197, 3, '2024-09-17', 'Consulta para revisión dental', 17, 17),
(198, 1, '2024-09-18', 'Revisión general de estado físico', 18, 18),
(199, 2, '2024-09-19', 'Revisión ocular y auditiva', 19, 19);

-- Octubre
INSERT INTO CITA (id, estado_cita_id, fecha_cita, observacion, CUSTODIAMASCOTAid, CUSTODIADUEniOid) 
VALUES
(200, 1, '2024-10-01', 'Consulta general para revisión anual', 1, 1),
(201, 2, '2024-10-02', 'Revisión de salud y vacunación', 2, 2),
(202, 3, '2024-10-03', 'Control de parasitosis', 3, 3),
(203, 1, '2024-10-04', 'Chequeo de comportamiento', 4, 4),
(204, 3, '2024-10-05', 'Revisión de dieta y peso', 5, 5),
(205, 1, '2024-10-06', 'Consulta para revisión dental', 6, 6),
(206, 2, '2024-10-07', 'Revisión ocular y auditiva', 7, 7),
(207, 3, '2024-10-08', 'Chequeo de salud general', 8, 8),
(208, 1, '2024-10-09', 'Control de peso y alimentación', 9, 9),
(209, 2, '2024-10-10', 'Consulta por dolor en las patas', 10, 10),
(210, 3, '2024-10-11', 'Revisión y control de parásitos internos', 11, 11),
(211, 1, '2024-10-12', 'Examen general y vacunas', 12, 12),
(212, 2, '2024-10-13', 'Revisión periódica para chequeo de salud', 13, 13),
(213, 3, '2024-10-14', 'Consulta por problemas respiratorios', 14, 14),
(214, 1, '2024-10-15', 'Revisión del comportamiento y socialización', 15, 15),
(215, 2, '2024-10-16', 'Chequeo de salud y cuidados generales', 16, 16),
(216, 3, '2024-10-17', 'Consulta para revisión dental', 17, 17),
(217, 1, '2024-10-18', 'Revisión general de estado físico', 18, 18),
(218, 2, '2024-10-19', 'Revisión ocular y auditiva', 19, 19);

--Noviembre
INSERT INTO CITA (id, estado_cita_id, fecha_cita, observacion, CUSTODIAMASCOTAid, CUSTODIADUEniOid) 
VALUES
(219, 1, '2024-11-21', 'Consulta general para revisión anual', 1, 1),
(220, 2, '2024-11-22', 'Vacunación de rutina', 2, 2),
(221, 3, '2024-11-23', 'Chequeo cardiológico especial', 3, 3),
(222, 1, '2024-11-24', 'Desparasitación avanzada', 4, 4),
(223, 1, '2024-11-25', 'Consulta dermatológica por alergias', 5, 5),
(224, 2, '2024-11-26', 'Cirugía menor programada', 6, 6),
(225, 3, '2024-11-27', 'Emergencia nocturna', 7, 7),
(226, 1, '2024-11-28', 'Consulta geriátrica para mascota mayor', 8, 8),
(227, 1, '2024-11-29', 'Pruebas alérgicas solicitadas', 9, 9),
(228, 2, '2024-11-30', 'Limpieza dental programada', 10, 10);
--Diciembre
INSERT INTO CITA (id, estado_cita_id, fecha_cita, observacion, CUSTODIAMASCOTAid, CUSTODIADUEniOid) 
VALUES
(229, 3, '2024-12-01', 'Consulta de urgencia por accidente', 11, 11),
(230, 2, '2024-12-02', 'Terapia física para rehabilitación', 12, 12),
(231, 1, '2024-12-03', 'Chequeo oftalmológico por irritación', 13, 13),
(232, 1, '2024-12-04', 'Endoscopía programada', 30, 15), -- Ajustado para corresponder a CUSTODIA
(233, 3, '2024-12-05', 'Atención de emergencia por fractura', 15, 15),
(234, 2, '2024-12-06', 'Consulta exótica para reptil', 16, 16),
(235, 1, '2024-12-07', 'Consulta geriátrica', 17, 17),
(236, 3, '2024-12-08', 'Emergencia nocturna por intoxicación', 18, 18),
(237, 2, '2024-12-09', 'Vacunación múltiple', 19, 19),
(238, 1, '2024-12-10', 'Chequeo general con radiografía', 20, 20),
(239, 1, '2024-12-11', 'Consulta dermatológica por caída de pelo', 21, 21),
(240, 2, '2024-12-12', 'Pruebas alérgicas', 25, 13),
(241, 1, '2024-12-13', 'Consulta por dolor articular', 32, 16),
(242, 3, '2024-12-14', 'Consulta urgente por pérdida de apetito', 35, 18),
(243, 1, '2024-12-15', 'Chequeo cardiológico programado', 37, 19), -- Ajustado para corresponder a CUSTODIA
(244, 2, '2024-12-16', 'Limpieza dental', 24, 24), -- Ajustado
(245, 1, '2024-12-17', 'Cirugía menor para esterilización', 27, 14),
(246, 2, '2024-12-18', 'Consulta de segunda opinión', 28, 14),
(247, 1, '2024-12-19', 'Corte de uñas y chequeo', 29, 15),
(248, 3, '2024-12-20', 'Emergencia por envenenamiento', 40, 20);

INSERT INTO DETALLE_CITA (cita_id, detalle_servicio_serv_id, detalle_servicio_med_id, horaEntrada, horaSalida, nota_adicional) 
VALUES
(1, 1, 1, '09:00:00', '09:30:00', 'Consulta general sin complicaciones'),
(2, 2, 1, '10:00:00', '10:20:00', 'Vacunación antirrábica completada'),
(3, 9, 5, '11:00:00', '11:45:00', 'Chequeo de presión arterial normal'),
(4, 3, 2, '12:00:00', '12:30:00', 'Desparasitación realizada sin problemas'),
(5, 12, 6, '13:00:00', '13:40:00', 'Diagnóstico dermatológico inicial realizado'),
(6, 4, 2, '14:00:00', '16:00:00', 'Cirugía menor completada con éxito'),
(7, 5, 3, '01:00:00', '01:45:00', 'Atención de emergencia satisfactoria'),
(8, 11, 26, '15:00:00', '15:30:00', 'Consulta geriátrica con recomendaciones nutricionales'),
(9, 20, 30, '16:00:00', '16:45:00', 'Pruebas alérgicas completadas sin reacciones adversas'),
(10, 8, 14, '17:00:00', '17:30:00', 'Limpieza dental realizada'),
(11, 11, 16, '09:30:00', '10:00:00', 'Atención a herida superficial'),
(12, 20, 20, '10:30:00', '11:00:00', 'Masaje terapéutico aplicado con éxito'),
(13, 6, 13, '11:30:00', '12:00:00', 'Consulta oftalmológica sin hallazgos'),
(14, 8, 14, '12:30:00', '13:30:00', 'Endoscopía realizada con diagnóstico inicial'),
(15, 5, 13, '01:00:00', '01:30:00', 'Emergencia por fractura estabilizada'),
(16, 11, 16, '14:30:00', '15:00:00', 'Consulta exótica para reptil completada'),
(17, 13, 17, '15:30:00', '16:00:00', 'Consulta geriátrica con recomendaciones de ejercicios'),
(18, 15, 18, '16:30:00', '17:00:00', 'Atención nocturna por intoxicación resuelta'),
(19, 18, 19, '17:30:00', '18:00:00', 'Vacunación múltiple sin reacciones adversas'),
(20, 6, 13, '18:30:00', '19:00:00', 'Radiografía para diagnóstico general'),
(21, 3, 12, '08:00:00', '08:30:00', 'Consulta dermatológica con diagnóstico inicial'),
(22, 12, 16, '08:45:00', '09:15:00', 'Pruebas alérgicas completadas'),
(23, 10, 5, '09:30:00', '10:00:00', 'Tratamiento dental básico realizado'),
(24, 13, 7, '10:15:00', '10:45:00', 'Chequeo cardiológico de rutina'),
(25, 5, 13, '11:00:00', '11:30:00', 'Consulta dermatológica programada'),
(26, 2, 21, '11:45:00', '12:15:00', 'Vacunación completada'),
(27, 1, 11, '12:30:00', '13:00:00', 'Consulta general sin hallazgos relevantes'),
(28, 8, 24, '13:15:00', '13:45:00', 'Limpieza dental programada'),
(29, 14, 17, '14:00:00', '14:30:00', 'Chequeo geriátrico detallado'),
(30, 9, 15, '14:45:00', '15:15:00', 'Consulta oftalmológica finalizada'),
(23, 19, 20, '16:15:00', '16:45:00', 'Corte de uñas'),
(23, 4, 22, '17:00:00', '17:30:00', 'Consulta general'),
(15, 7, 14, '17:45:00', '18:15:00', 'Chequeo geriátrico programado'),
(12, 10, 15, '18:30:00', '19:00:00', 'Consulta de segunda opinión'),
(8, 12,16, '19:15:00', '19:45:00', 'Consulta dermatológica'),
(8, 14, 17, '20:00:00', '20:30:00', 'Endoscopía programada'),
(9, 15, 18, '20:45:00', '21:15:00', 'Consulta geriátrica'),
(11, 17, 19, '21:30:00', '22:00:00', 'Limpieza dental programada'),
(24, 20, 20, '22:15:00', '22:45:00', 'Radiografía para diagnóstico general');

INSERT INTO DETALLE_CITA (cita_id, detalle_servicio_serv_id, detalle_servicio_med_id, horaEntrada, horaSalida) 
VALUES
(25,6,3,'08:30:00','09:00:00'),				
(26,7,4,'09:30:00','10:00:00'),				
(27,8,4,'10:30:00','11:00:00'),				
(28,6,3,'11:30:00','12:00:00'),				
(29,7,4,'12:30:00','13:00:00'),				
(30,8,4,'13:30:00','14:00:00'),				
(31,1,1,'14:30:00','15:00:00'),				
(32,2,1,'15:30:00','16:00:00'),				
(33,3,2,'16:00:00','16:30:00'),				
(34,4,2,'16:30:00','17:00:00'),				
(35,5,3,'17:00:00','17:30:00'),				
(36,6,3,'17:30:00','18:00:00'),				
(37,7,4,'18:00:00','18:30:00'),				
(38,8,4,'18:30:00','19:00:00'),				
(39,9,5,'19:00:00','19:30:00'),				
(40,10,5,'19:30:00','20:00:00'),				
(41,11,6,'20:00:00','20:30:00'),				
(42,12,6,'20:30:00','21:00:00'),				
(43,13,7,'21:00:00','21:30:00'),				
(44,14,7,'21:30:00','22:00:00'),				
(45,15,8,'22:00:00','22:30:00'),				
(46,16,8,'22:30:00','23:00:00'),				
(47,17,9,'23:00:00','23:30:00'),				
(48,18,9,'23:30:00','00:00:00'),				
(49,19,10,'00:00:00','00:30:00'),				
(50,20,10,'00:30:00','01:00:00'),				
(51,1,11,'01:00:00','01:30:00'),				
(52,2,11,'01:30:00','02:00:00'),				
(53,3,12,'02:00:00','02:30:00'),				
(54,4,12,'02:30:00','03:00:00'),				
(55,5,13,'03:00:00','03:30:00'),				
(56,6,13,'03:30:00','04:00:00'),				
(57,7,14,'04:00:00','04:30:00'),				
(58,8,14,'04:30:00','05:00:00'),				
(59,9,15,'05:00:00','05:30:00'),				
(60,10,15,'05:30:00','06:00:00'),				
(61,11,16,'06:00:00','06:30:00'),				
(62,12,16,'06:30:00','07:00:00'),				
(63,13,17,'07:00:00','07:30:00'),				
(64,14,17,'07:30:00','08:00:00'),				
(65,15,18,'08:00:00','08:30:00'),				
(66,16,18,'08:30:00','09:00:00'),				
(67,17,19,'09:00:00','09:30:00'),				
(68,18,19,'09:30:00','10:00:00'),				
(69,19,20,'10:00:00','10:30:00'),				
(70,20,20,'10:30:00','11:00:00'),				
(71,1,21,'11:00:00','11:30:00'),				
(72,2,21,'11:30:00','12:00:00'),				
(73,3,22,'12:00:00','12:30:00'),				
(74,4,22,'12:30:00','13:00:00'),				
(75,5,23,'13:00:00','13:30:00'),				
(76,6,23,'13:30:00','14:00:00'),				
(77,7,24,'14:00:00','14:30:00'),				
(78,8,24,'14:30:00','15:00:00'),				
(79,9,25,'15:00:00','15:30:00'),				
(80,10,25,'15:30:00','16:00:00'),				
(81,11,26,'16:00:00','16:30:00'),				
(82,12,26,'16:30:00','17:00:00'),				
(83,13,27,'17:00:00','17:30:00'),				
(84,14,27,'17:30:00','18:00:00'),				
(85,15,28,'18:00:00','18:30:00'),				
(86,16,28,'18:30:00','19:00:00'),				
(87,17,29,'19:00:00','19:30:00'),				
(88,18,29,'19:30:00','20:00:00'),				
(89,19,30,'20:00:00','20:30:00'),				
(90,20,30,'20:30:00','21:00:00'),				
(91,11,26,'21:00:00','21:30:00'),				
(92,12,26,'21:30:00','22:00:00'),				
(93,13,27,'22:00:00','22:30:00'),				
(94,14,27,'22:30:00','23:00:00'),				
(95,15,28,'23:00:00','23:30:00'),				
(96,16,28,'23:30:00','00:00:00'),				
(97,17,29,'00:00:00','00:30:00'),				
(98,18,29,'00:30:00','01:00:00'),				
(99,19,30,'01:00:00','01:30:00'),				
(100,20,30,'01:30:00','02:00:00'),				
(101,1,1,'02:00:00','02:30:00'),				
(102,2,1,'02:30:00','03:00:00'),				
(103,3,2,'03:00:00','03:30:00'),				
(104,4,2,'03:30:00','04:00:00'),				
(105,5,3,'04:00:00','04:30:00'),				
(106,6,3,'04:30:00','05:00:00'),				
(107,7,4,'05:00:00','05:30:00'),				
(108,8,4,'05:30:00','06:00:00'),				
(109,9,5,'06:00:00','06:30:00'),				
(110,10,5,'06:30:00','07:00:00'),				
(111,11,6,'07:00:00','07:30:00'),				
(112,12,6,'07:30:00','08:00:00'),				
(113,13,7,'08:00:00','08:30:00'),				
(114,14,7,'08:30:00','09:00:00'),				
(115,15,8,'09:00:00','09:30:00'),				
(116,16,8,'09:30:00','10:00:00'),				
(117,17,9,'10:00:00','10:30:00'),				
(118,18,9,'10:30:00','11:00:00'),				
(119,19,10,'11:00:00','11:30:00'),				
(120,18,29,'11:30:00','12:00:00'),				
(121,19,30,'12:00:00','12:30:00'),				
(122,20,30,'12:30:00','13:00:00'),				
(123,11,26,'13:00:00','13:30:00'),				
(124,12,26,'13:30:00','14:00:00'),				
(125,13,27,'14:00:00','14:30:00'),				
(126,14,27,'14:30:00','15:00:00'),				
(127,15,28,'15:00:00','15:30:00'),				
(128,9,15,'15:30:00','16:00:00'),				
(129,10,15,'16:00:00','16:30:00'),				
(130,11,16,'16:30:00','17:00:00'),				
(131,12,16,'17:00:00','17:30:00'),				
(132,13,17,'17:30:00','18:00:00'),				
(133,14,17,'18:00:00','18:30:00'),				
(134,15,18,'18:30:00','19:00:00'),				
(135,16,18,'19:00:00','19:30:00'),				
(136,17,19,'19:30:00','20:00:00'),				
(137,18,19,'20:00:00','20:30:00'),				
(138,19,20,'20:30:00','21:00:00'),				
(139,20,20,'21:00:00','21:30:00'),				
(140,12,6,'21:30:00','22:00:00'),				
(141,13,7,'22:00:00','22:30:00'),				
(142,14,7,'22:30:00','23:00:00'),				
(143,15,8,'23:00:00','23:30:00'),				
(144,16,8,'23:30:00','00:00:00'),				
(145,17,9,'00:00:00','00:30:00'),				
(146,18,9,'00:30:00','01:00:00'),				
(147,19,10,'01:00:00','01:30:00'),				
(148,20,10,'01:30:00','02:00:00'),				
(149,1,11,'02:00:00','02:30:00'),				
(150,2,11,'02:30:00','03:00:00'),				
(151,3,12,'03:00:00','03:30:00'),				
(152,4,12,'03:30:00','04:00:00'),				
(153,5,13,'04:00:00','04:30:00'),				
(154,6,13,'04:30:00','05:00:00'),				
(155,7,14,'05:00:00','05:30:00'),				
(156,12,6,'05:30:00','06:00:00'),				
(157,13,7,'06:00:00','06:30:00'),				
(158,14,7,'06:30:00','07:00:00'),				
(159,15,8,'07:00:00','07:30:00'),				
(160,16,8,'07:30:00','08:00:00'),				
(161,17,9,'08:00:00','08:30:00'),				
(162,18,9,'08:30:00','09:00:00'),				
(163,19,10,'09:00:00','09:30:00'),				
(164,20,10,'09:30:00','10:00:00'),				
(165,1,11,'10:00:00','10:30:00'),				
(166,2,11,'10:30:00','11:00:00'),				
(167,3,12,'11:00:00','11:30:00'),				
(168,4,12,'11:30:00','12:00:00'),				
(169,5,13,'12:00:00','12:30:00'),				
(170,6,13,'12:30:00','13:00:00'),				
(171,7,14,'13:00:00','13:30:00'),				
(172,5,3,'13:30:00','14:00:00'),				
(173,6,3,'14:00:00','14:30:00'),				
(174,7,4,'14:30:00','15:00:00'),				
(175,8,4,'15:00:00','15:30:00'),				
(176,9,5,'15:30:00','16:00:00'),				
(177,10,5,'16:00:00','16:30:00'),				
(178,11,6,'16:30:00','17:00:00'),				
(179,12,6,'17:00:00','17:30:00'),				
(180,13,7,'17:30:00','18:00:00'),				
(181,14,7,'18:00:00','18:30:00'),				
(182,15,8,'18:30:00','19:00:00'),				
(183,16,8,'19:00:00','19:30:00'),				
(184,17,9,'19:30:00','20:00:00'),				
(185,18,9,'20:00:00','20:30:00'),				
(186,19,10,'20:30:00','21:00:00'),				
(187,5,3,'21:00:00','21:30:00'),				
(188,6,3,'21:30:00','22:00:00'),				
(189,7,4,'22:00:00','22:30:00'),				
(190,8,4,'22:30:00','23:00:00'),				
(191,9,5,'23:00:00','23:30:00'),				
(192,10,5,'23:30:00','00:00:00'),				
(193,11,6,'00:00:00','00:30:00'),				
(194,12,6,'00:30:00','01:00:00'),				
(195,13,7,'01:00:00','01:30:00'),				
(196,14,7,'01:30:00','02:00:00'),				
(197,15,8,'02:00:00','02:30:00'),				
(198,16,8,'02:30:00','03:00:00'),				
(199,17,9,'03:00:00','03:30:00'),				
(200,18,9,'03:30:00','04:00:00'),				
(201,19,10,'04:00:00','04:30:00'),				
(202,1,1,'04:30:00','05:00:00'),				
(203,2,1,'05:00:00','05:30:00'),				
(204,3,2,'05:30:00','06:00:00'),				
(205,4,2,'06:00:00','06:30:00'),				
(206,5,3,'06:30:00','07:00:00'),				
(207,6,3,'07:00:00','07:30:00'),				
(208,7,4,'07:30:00','08:00:00'),				
(209,8,4,'08:00:00','08:30:00'),				
(210,9,5,'08:30:00','09:00:00'),				
(211,10,5,'09:00:00','09:30:00'),				
(212,11,6,'09:30:00','10:00:00'),				
(213,12,6,'10:00:00','10:30:00'),				
(214,13,7,'10:30:00','11:00:00'),				
(215,14,7,'11:00:00','11:30:00'),				
(216,15,8,'11:30:00','12:00:00'),				
(217,16,8,'12:00:00','12:30:00'),				
(218,17,9,'12:30:00','13:00:00'),				
(219,18,9,'13:00:00','13:30:00'),				
(220,19,10,'13:30:00','14:00:00'),				
(221,20,10,'14:00:00','14:30:00'),				
(222,1,11,'14:30:00','15:00:00'),				
(223,2,11,'15:00:00','15:30:00'),				
(224,3,12,'15:30:00','16:00:00'),				
(225,4,12,'16:00:00','16:30:00'),				
(226,5,13,'16:30:00','17:00:00'),				
(227,6,13,'17:00:00','17:30:00'),				
(228,7,14,'17:30:00','18:00:00'),				
(229,8,14,'18:00:00','18:30:00'),				
(230,1,1,'18:30:00','19:00:00'),				
(231,2,1,'19:00:00','19:30:00'),				
(232,3,2,'19:30:00','20:00:00'),				
(233,4,2,'20:00:00','20:30:00'),				
(234,5,3,'20:30:00','21:00:00'),				
(235,6,3,'21:00:00','21:30:00'),				
(236,7,4,'21:30:00','22:00:00'),				
(237,8,4,'22:00:00','22:30:00'),				
(238,9,5,'22:30:00','23:00:00'),				
(239,10,5,'23:00:00','23:30:00'),				
(240,11,6,'23:30:00','00:00:00'),				
(241,12,6,'00:00:00','00:30:00'),				
(242,13,7,'00:30:00','01:00:00'),				
(243,14,7,'01:00:00','01:30:00'),				
(244,15,8,'01:30:00','02:00:00'),				
(245,16,8,'02:00:00','02:30:00'),				
(246,17,9,'02:30:00','03:00:00'),				
(247,18,9,'03:00:00','03:30:00'),				
(248,19,10,'03:30:00','04:00:00'),				
(123,20,10,'04:00:00','04:30:00'),				
(124,1,11,'04:30:00','05:00:00'),				
(125,2,11,'05:00:00','05:30:00'),				
(126,3,12,'05:30:00','06:00:00'),				
(127,4,12,'06:00:00','06:30:00'),				
(128,5,13,'06:30:00','07:00:00'),				
(129,6,13,'07:00:00','07:30:00'),				
(130,7,14,'07:30:00','08:00:00'),				
(131,8,14,'08:00:00','08:30:00'),				
(132,13,7,'08:30:00','09:00:00');



-- MEDICAMENTOS


INSERT INTO TIPO_MEDICAMENTO (id, nomtipo) VALUES 
(1, 'Antibiótico'),
(2, 'Analgésico'),
(3, 'Antiparasitario'),
(4, 'Antiinflamatorio'),
(5, 'Suplemento');


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
(10, 'Doxiciclina 100mg', 22.00, 25, 'Cápsula', true, 1),
(11, 'Enrofloxacina 50mg', 28.00, 35, 'Tableta', true, 1),
(12, 'Metoclopramida 10mg', 18.00, 40, 'Cápsula', true, 2),
(13, 'Furosemida 40mg', 12.50, 50, 'Comprimido', true, 3),
(14, 'Clorhexidina 0.5%', 15.00, 60, 'Solución', true, 4),
(15, 'Vitamina B12', 10.00, 75, 'Inyección', true, 5),
(16, 'Omeprazol 20mg', 25.00, 20, 'Cápsula', true, 2),
(17, 'Albendazol 400mg', 22.00, 30, 'Tableta', true, 3),
(18, 'Gabapentina 100mg', 45.00, 10, 'Cápsula', true, 4),
(19, 'Desloratadina 5mg', 14.00, 50, 'Comprimido', true, 1),
(20, 'Prednisolona 5mg', 20.00, 25, 'Tableta', true, 2);

INSERT INTO DETALLE_MEDICAMENTO (medicamento_id, detalle_cita_id, detalle_servicio_servicio_id, detalle_servicio_medico_id, dosis, indicacion, cantidad) VALUES
(1, 1, 1, 1, 250.00, 'Tomar cada 8 horas con alimentos', 10),
(2, 2, 2, 1, 0.50, 'Administrar después de las comidas', 5),
(3, 4, 3, 2, 150.00, 'Tomar una tableta cada 24 horas con abundante agua', 7),
(4, 6, 4, 2, 25.00, 'Solo en caso de dolor, cada 12 horas', 6),
(5, 7, 5, 3, 1.00, 'Vacuna inyectada subcutáneamente', 1),
(6, 8, 11, 26, 1.00, 'Inyección de 1ml cada 7 días, uso veterinario', 3),
(7, 9, 20, 30, 10.00, 'Administrar cada 8 horas durante la inflamación', 4),
(8, 10, 8, 14, 1.00, 'Inyección intramuscular, dosis única', 1),
(9, 11, 11, 16, 50.00, 'Tomar cada 12 horas con leche', 8),
(10, 12, 20, 20, 100.00, 'Evitar exposición al sol, cada 24 horas', 2),
(11, 13, 6, 13, 50.00, 'Tomar cada 12 horas, revisar efectos secundarios', 5),
(12, 15, 5, 13, 10.00, 'Suspender en caso de efectos adversos, cada 24 horas', 3),
(13, 17, 13, 17, 40.00, 'Para uso exclusivo bajo prescripción, cada 8 horas', 6),
(14, 18, 15, 18, 2.00, 'Aplicar 2 veces al día, uso tópico', 1),
(15, 19, 18, 19, 1.00, 'Administrar intramuscularmente, dosis única', 1),
(16, 21, 3, 12, 20.00, 'Evitar en pacientes con gastritis, cada 12 horas', 4),
(17, 22, 12, 16, 400.00, 'Administrar con alimentos, dosis única', 2),
(18, 24, 13, 7, 100.00, 'Solo bajo receta médica, cada 8 horas', 3),
(19, 25, 5, 13, 5.00, 'No exceder la dosis indicada, cada 24 horas', 7),
(20, 26, 2, 21, 5.00, 'Administrar en la noche, cada 24 horas', 3),
(1, 27, 1,11, 250.00, 'Solo en caso de infección, cada 8 horas', 5),
(2, 28, 8, 24, 0.50, 'Uso limitado, cada 12 horas', 4),
(3, 12, 10, 15, 150.00, 'Revisar efectos adversos, cada 7 días', 3),
(4, 30, 9, 15, 25.00, 'Suspender en caso de reacciones alérgicas, cada 12 horas', 2),
(5, 8, 11, 26, 1.00, 'Administrar subcutáneamente, dosis única', 1),
(6, 10, 8, 14, 1.00, 'Inyección de 1ml cada 30 días', 1),
(7, 11, 11, 16, 10.00, 'Evitar mezclar con otros medicamentos, cada 24 horas', 3),
(8, 12, 20, 20, 1.00, 'Inyección intramuscular, dosis única', 1),
(9, 13, 6, 13, 50.00, 'Consultar antes de suspender, cada 12 horas', 5),
(10, 15, 5, 13, 100.00, 'Evitar exposición al sol, cada 8 horas', 2),
(11, 17, 13, 17, 50.00, 'Tomar con alimentos, cada 24 horas', 6),
(12, 18, 15, 18, 10.00, 'Solo durante tratamiento específico, cada 12 horas', 4),
(13, 19, 18, 19, 40.00, 'Revisar efectos adversos, cada 8 horas', 5),
(14, 20, 6, 13, 2.00, 'Aplicar 2 veces al día, uso tópico', 2),
(15, 21, 3, 12, 1.00, 'Dosis única, administrar intramuscular', 1),
(16, 22, 12, 16, 20.00, 'Evitar en pacientes con úlceras, cada 12 horas', 3),
(17, 23, 10, 5, 400.00, 'Uso limitado bajo supervisión, cada 7 días', 2);


delete from detalle_medicamento where detalle_cita_id=1;
delete from detalle_medicamento where detalle_cita_id=4;
delete from detalle_medicamento where detalle_cita_id=6;
delete from detalle_medicamento where detalle_cita_id=9;
delete from detalle_medicamento where detalle_cita_id=12;
delete from detalle_medicamento where detalle_cita_id=18;
delete from detalle_medicamento where detalle_cita_id=21;
delete from detalle_medicamento where detalle_cita_id=24;
delete from detalle_medicamento where detalle_cita_id=27;
delete from detalle_medicamento where detalle_cita_id=30;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.*;
import javax.swing.JTable;

/**
 *
 * @author franc
 */
public class clsCita {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    Connection con = null;
    Statement sent;

    public Integer generarCodigoCita() throws Exception {
        strSQL = "SELECT COALESCE(max(id), 0) + 1 as codigo FROM CITA;";
        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar código de cita --> " + e.getLocalizedMessage());
        }
        return 0;
    }

    public ResultSet buscarCita(Integer numCita) throws Exception {
        strSQL = "SELECT CITA.*, "
                + "DUEniO.doc_identidad AS duenio_doc, DUEniO.nombres AS duenio_nombres, DUEniO.apePaterno AS duenio_apPaterno, "
                + "DUEniO.apeMaterno AS duenio_apMaterno, DUEniO.direccion, DUEniO.telefono AS telefono_duenio, "
                + "MASCOTA.id AS codigo_mascota, MASCOTA.nombre AS nombre_mascota, MASCOTA.raza_id, MASCOTA.notaAdicional AS nota, "
                + "MASCOTA.esterilizado AS castrado, "
                + "EXTRACT(YEAR FROM AGE(CURRENT_DATE, MASCOTA.fecha_nacimiento)) AS edad "
                + "FROM CITA "
                + "INNER JOIN CUSTODIA ON CITA.CUSTODIAMASCOTAid = CUSTODIA.MASCOTAid AND CITA.CUSTODIADUEniOid = CUSTODIA.DUEniOid "
                + "INNER JOIN DUEniO ON CUSTODIA.DUEniOid = DUEniO.id "
                + "INNER JOIN MASCOTA ON CUSTODIA.MASCOTAid = MASCOTA.id "
                + "WHERE CITA.id = " + numCita;

        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar cita " + e.getMessage());
        }
    }

    // Método para insertar una nueva cita
//    public void insertarCita(Integer id, Integer estadoCitaId, Date fechaCita, String observacion, Integer custodiaMascotaId, Integer custodiaDuenioId) throws Exception {
//        strSQL = "INSERT INTO CITA (id, estado_cita_id, fecha_cita, observacion, CUSTODIAMASCOTAid, CUSTODIADUENIOid) "
//                + "VALUES (" + id + ", " + estadoCitaId + ", '" + fechaCita + "', '" + observacion + "', " + custodiaMascotaId + ", " + custodiaDuenioId + ")";
//        try {
//            objConectar.ejecutarBD(strSQL);
//        } catch (Exception e) {
//            throw new Exception("Error al insertar cita --> " + e.getLocalizedMessage());
//        }
//    }
    public void registrarCita(int estadoCitaId, int custodiamascotaId, int custodiaDuenioId, JTable tblServicios, Date fechaCita, String observacion) throws Exception {
        try {
            objConectar.conectar();
            con = objConectar.getCon();
            con.setAutoCommit(false);
            sent = con.createStatement();

            int idCita = generarCodigoCita();

            // Insertar la cita con la fecha y la observación proporcionadas
            String strSQL = "INSERT INTO CITA (id, estado_cita_id, fecha_cita, observacion, CUSTODIAMASCOTAid, CUSTODIADUEniOid) "
                    + "VALUES (" + idCita + ", " + estadoCitaId + ", '" + new java.sql.Date(fechaCita.getTime()) + "', '" + observacion + "', "
                    + custodiamascotaId + ", " + custodiaDuenioId + ")";
            sent.executeUpdate(strSQL);

            int rowCount = tblServicios.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                String cadena = String.valueOf(tblServicios.getValueAt(i, 0));
                String[] codigos = cadena.split(" - ");
                int codSer = Integer.parseInt(codigos[0].trim());
                int codMed = Integer.parseInt(codigos[1].trim());
                String horaEntrada = tblServicios.getValueAt(i, 3).toString();
                String horaSalida = tblServicios.getValueAt(i, 4).toString();
                String notaAdicional = tblServicios.getValueAt(i, 5).toString();

                strSQL = "INSERT INTO DETALLE_CITA (cita_id, detalle_servicio_serv_id, detalle_servicio_med_id, horaEntrada, "
                        + "horaSalida, nota_adicional) "
                        + "VALUES (" + idCita + ", " + codSer + ", "
                        + codMed + ", '" + horaEntrada + "', '" + horaSalida + "', '" + notaAdicional + "')";
                sent.executeUpdate(strSQL);

                strSQL = "UPDATE DETALLE_SERVICIO SET disponibilidad = false WHERE servicio_id = " + codSer + " AND medico_id = " + codMed;
                sent.executeUpdate(strSQL);
            }

            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw new Exception("Error al registrar cita --> " + e.getLocalizedMessage());
        } finally {
            objConectar.desconectar();
        }
    }

    public void terminarCita(int idCita) throws Exception {
        int estadoFinalizadoId = 3;
        strSQL = "UPDATE CITA SET estado_cita_id = " + estadoFinalizadoId + " WHERE id = " + idCita;

        try {
            objConectar.conectar();
            sent = objConectar.getCon().createStatement();
            sent.executeUpdate(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al finalizar la cita --> " + e.getLocalizedMessage());
        } finally {
            objConectar.desconectar();
        }
    }

    public void cancelarCita(int idCita) throws Exception {
        int estadoFinalizadoId = 2;
        strSQL = "UPDATE CITA SET estado_cita_id = " + estadoFinalizadoId + " WHERE id = " + idCita;

        try {
            objConectar.conectar();
            sent = objConectar.getCon().createStatement();
            sent.executeUpdate(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al finalizar la cita --> " + e.getLocalizedMessage());
        } finally {
            objConectar.desconectar();
        }
    }

    public void modificarEstado(int idCita, int nuevoEstadoId) throws Exception {
        strSQL = "UPDATE CITA SET estado_cita_id = " + nuevoEstadoId + " WHERE id = " + idCita;

        try {
            objConectar.conectar();
            sent = objConectar.getCon().createStatement();
            sent.executeUpdate(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar el estado de la cita --> " + e.getLocalizedMessage());
        } finally {
            objConectar.desconectar();
        }
    }

    public ResultSet listarCitasxMascota(int mas_id) throws Exception {
        strSQL = " SELECT "
                + " C.id AS id_cita, "
                + " C.fecha_cita, "
                + " C.observacion, "
                + " EC.nombre_estado AS estado_cita "
                + " FROM CITA C "
                + " LEFT JOIN CUSTODIA CU ON C.CUSTODIAMASCOTAid = CU.MASCOTAid AND C.CUSTODIADUEniOid = CU.DUEniOid "
                + " LEFT JOIN MASCOTA M ON CU.MASCOTAid = M.id "
                + " LEFT JOIN ESTADO_CITA EC ON C.estado_cita_id = EC.id "
                + " WHERE M.id = " + mas_id
                + " order by C.fecha_cita desc";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    public ResultSet mesesRegistrado() throws Exception {
        strSQL = "SELECT DISTINCT EXTRACT(MONTH FROM c.fecha_cita) AS mes FROM cita c;";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al obtenener meses");
        }
    }

    public ResultSet listarCitasPendientesPorDNI(String dni) throws Exception {
        strSQL = "SELECT "
                + " C.id AS id_cita, "
                + " C.fecha_cita, "
                + " DUEniO.nombres AS duenio_nombres, "
                + " DUEniO.apePaterno AS duenio_apPaterno, "
                + " DUEniO.apeMaterno AS duenio_apMaterno, "
                + " DUEniO.doc_identidad "
                + "FROM "
                + " CITA C "
                + " LEFT JOIN CUSTODIA CU ON C.CUSTODIAMASCOTAid = CU.MASCOTAid AND C.CUSTODIADUEniOid = CU.DUEniOid "
                + " LEFT JOIN DUEniO ON C.CUSTODIADUEniOid = DUEniO.id "
                + "WHERE "
                + " C.estado_cita_id = 1 "
                + " AND DUEniO.doc_identidad = '" + dni
                + "' ORDER BY "
                + " C.fecha_cita ASC";

        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar las citas pendientes por DNI: " + e.getMessage());
        }
    }

    public ResultSet listarCitasPendientesOrdenadas() throws Exception {
        strSQL = "SELECT "
                + " C.id AS id_cita, "
                + " C.fecha_cita, "
                + " DUEniO.nombres AS duenio_nombres, "
                + " DUEniO.apePaterno AS duenio_apPaterno, "
                + " DUEniO.apeMaterno AS duenio_apMaterno, "
                + " DUEniO.doc_identidad "
                + "FROM "
                + " CITA C "
                + " LEFT JOIN CUSTODIA CU ON C.CUSTODIAMASCOTAid = CU.MASCOTAid AND C.CUSTODIADUEniOid = CU.DUEniOid "
                + " LEFT JOIN DUEniO ON C.CUSTODIADUEniOid = DUEniO.id "
                + "WHERE "
                + " C.estado_cita_id = 1 "
                + "ORDER BY "
                + " C.fecha_cita ASC";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar las citas pendientes: " + e.getMessage());
        }
    }

    public ResultSet listarCitasPendientesOrdenadasFechaActual() throws Exception {
        strSQL = "SELECT "
                + " C.id AS id_cita, "
                + " C.fecha_cita, "
                + " DUEniO.nombres AS duenio_nombres, "
                + " DUEniO.apePaterno AS duenio_apPaterno, "
                + " DUEniO.apeMaterno AS duenio_apMaterno, "
                + " DUEniO.doc_identidad "
                + "FROM "
                + " CITA C "
                + " LEFT JOIN CUSTODIA CU ON C.CUSTODIAMASCOTAid = CU.MASCOTAid AND C.CUSTODIADUEniOid = CU.DUEniOid "
                + " LEFT JOIN DUEniO ON C.CUSTODIADUEniOid = DUEniO.id "
                + "WHERE "
                + " C.estado_cita_id = 1 "
                + " AND DATE(C.fecha_cita) = CURRENT_DATE " // Filtra solo las citas del día actual
                + "ORDER BY "
                + " C.fecha_cita ASC";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar las citas pendientes: " + e.getMessage());
        }
    }

    public int contarFilas(int mes) throws Exception {
        String strSQL = "SELECT COUNT(*) "
                + "FROM ( "
                + "    SELECT "
                + "        s.nom_servicio, "
                + "        COUNT(dc.detalle_servicio_serv_id) AS total_servicios "
                + "    FROM "
                + "        detalle_cita dc "
                + "    INNER JOIN "
                + "        detalle_servicio ds ON ds.servicio_id = dc.detalle_servicio_serv_id "
                + "    INNER JOIN "
                + "        servicio s ON s.id = ds.servicio_id "
                + "    INNER JOIN "
                + "        cita c ON c.id = dc.cita_id "
                + "    WHERE "
                + "        EXTRACT(MONTH FROM c.fecha_cita) = " + mes
                + "    GROUP BY "
                + "        s.nom_servicio "
                + ") as cantidad_filas;";

        try {
            rs = objConectar.consultarBD(strSQL);

            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (Exception e) {
            throw new Exception("Error al contar las filas", e); // Lanzamos la excepción correctamente
        }
    }

    public void manejoDeTodaLaTransaccion(int idCita, JTable tblMedicamentos) throws Exception {
        int estadoFinalizadoId = 3;
        String strSQL_TerminarCita = "UPDATE CITA SET estado_cita_id = " + estadoFinalizadoId + " WHERE id = " + idCita;
        Connection con = null;
        Statement sent = null;

        try {
            objConectar.conectar();
            con = objConectar.getCon();
            con.setAutoCommit(false);
            sent = con.createStatement();
            sent.executeUpdate(strSQL_TerminarCita);

            con.commit();
        } catch (Exception e) {
            if (con != null) {
                con.rollback();
            }
            throw new Exception("Error al finalizar la cita --> " + e.getLocalizedMessage());
        } finally {
            try {
                if (sent != null) {
                    sent.close();
                }
                if (con != null) {
                    con.setAutoCommit(true);
                    objConectar.desconectar();
                }
            } catch (SQLException e) {
                throw new Exception("Error al cerrar la conexión o el statement: " + e.getMessage());
            }
        }
    }

    public ResultSet listarCitasPendientesPorDNI2(String dni) throws Exception {
        strSQL = "SELECT "
                + " C.id AS id_cita, "
                + " C.fecha_cita, "
                + " DUEniO.nombres AS duenio_nombres, "
                + " DUEniO.apePaterno AS duenio_apPaterno, "
                + " DUEniO.apeMaterno AS duenio_apMaterno, "
                + " DUEniO.doc_identidad "
                + "FROM "
                + " CITA C "
                + " LEFT JOIN CUSTODIA CU ON C.CUSTODIAMASCOTAid = CU.MASCOTAid AND C.CUSTODIADUEniOid = CU.DUEniOid "
                + " LEFT JOIN DUEniO ON C.CUSTODIADUEniOid = DUEniO.id "
                + "WHERE "
                + " C.estado_cita_id = 1 "
                + " AND DUEniO.doc_identidad = '" + dni + "' "
                + " AND DATE(C.fecha_cita) = CURRENT_DATE " // Filtra solo las citas del día actual
                + "ORDER BY "
                + " C.fecha_cita ASC";

        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar las citas pendientes por DNI: " + e.getMessage());
        }
    }

    public ResultSet listarCitasPendientesOrdenadasFechaActual3() throws Exception {
        strSQL = "SELECT "
                + " C.id AS id_cita, "
                + " C.fecha_cita, "
                + " DUEniO.nombres AS duenio_nombres, "
                + " DUEniO.apePaterno AS duenio_apPaterno, "
                + " DUEniO.apeMaterno AS duenio_apMaterno, "
                + " DUEniO.doc_identidad, "
                + " MASCOTA.nombre AS mascota_nombre " // Nombre de la mascota
                + "FROM "
                + " CITA C "
                + " LEFT JOIN CUSTODIA CU ON C.CUSTODIAMASCOTAid = CU.MASCOTAid AND C.CUSTODIADUEniOid = CU.DUEniOid "
                + " LEFT JOIN DUEniO ON C.CUSTODIADUEniOid = DUEniO.id "
                + " LEFT JOIN MASCOTA ON CU.MASCOTAid = MASCOTA.id " // Join con la tabla MASCOTA
                + "WHERE "
                + " C.estado_cita_id = 1 "
                + " AND DATE(C.fecha_cita) = CURRENT_DATE " // Filtra solo las citas del día actual
                + "ORDER BY "
                + " C.fecha_cita ASC";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar las citas pendientes: " + e.getMessage());
        }
    }

    public ResultSet listarCitasPendientesPorDNI3(String dni) throws Exception {
        strSQL = "SELECT "
                + " C.id AS id_cita, "
                + " C.fecha_cita, "
                + " DUEniO.nombres AS duenio_nombres, "
                + " DUEniO.apePaterno AS duenio_apPaterno, "
                + " DUEniO.apeMaterno AS duenio_apMaterno, "
                + " DUEniO.doc_identidad, "
                + " MASCOTA.nombre AS nombre_mascota " // Agregamos el nombre de la mascota
                + "FROM "
                + " CITA C "
                + " LEFT JOIN CUSTODIA CU ON C.CUSTODIAMASCOTAid = CU.MASCOTAid AND C.CUSTODIADUEniOid = CU.DUEniOid "
                + " LEFT JOIN DUEniO ON C.CUSTODIADUEniOid = DUEniO.id "
                + " LEFT JOIN MASCOTA ON CU.MASCOTAid = MASCOTA.id " // Aquí hacemos el JOIN con la tabla MASCOTA
                + "WHERE "
                + " C.estado_cita_id = 1 "
                + " AND DUEniO.doc_identidad = '" + dni + "' "
                + " AND DATE(C.fecha_cita) = CURRENT_DATE " // Filtra solo las citas del día actual
                + "ORDER BY "
                + " C.fecha_cita ASC";

        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar las citas pendientes por DNI: " + e.getMessage());
        }
    }

}

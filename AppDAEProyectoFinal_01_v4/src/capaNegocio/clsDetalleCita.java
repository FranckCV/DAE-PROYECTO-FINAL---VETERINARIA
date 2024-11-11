/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;

/**
 *
 * @author Junior
 */
public class clsDetalleCita {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    Connection con = null;
    Statement sent;

//    public void insertarDetalleCita(Integer citaId, Integer servicioId, Integer medicoId, String horaEntrada, String horaSalida, String notaAdicional) throws Exception {
//        String strSQL = "INSERT INTO DETALLE_CITA (cita_id, detalle_servicio_serv_id, detalle_servicio_med_id, horaEntrada, horaSalida, nota_adicional) "
//                + "VALUES (" + citaId + ", " + servicioId + ", " + medicoId + ", '" + horaEntrada + "', '" + horaSalida + "', '" + notaAdicional + "')";
//
//        try {
//            objConectar.ejecutarBD(strSQL);
//        } catch (Exception e) {
//            throw new Exception("Error al insertar detalle de cita --> " + e.getLocalizedMessage());
//        }
//    }
    public ResultSet buscarDetalleCita(Integer numCita) throws Exception {
        strSQL = "SELECT DETALLE_CITA.*, "
                + "SERVICIO.nom_servicio AS servicio_nombre, SERVICIO.descripcion AS servicio_descripcion, SERVICIO.costo AS costo,"
                + "MEDICO.nombres AS medico_nombres, MEDICO.apePaterno AS medico_apPaterno, MEDICO.apeMaterno AS medico_apMaterno, "
                + "MEDICO.doc_identidad AS medico_doc "
                + "FROM DETALLE_CITA "
                + "INNER JOIN SERVICIO ON DETALLE_CITA.detalle_servicio_serv_id = SERVICIO.id "
                + "INNER JOIN MEDICO ON DETALLE_CITA.detalle_servicio_med_id = MEDICO.id "
                + "WHERE DETALLE_CITA.cita_id = " + numCita;

        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar detalle de cita --> " + e.getMessage());
        }
    }

    public void insertarDetalleServicioNoRepetido(int idCita, JTable tblServicios) throws Exception {
        try {
            objConectar.conectar();
            con = objConectar.getCon();
            con.setAutoCommit(false);
            sent = con.createStatement();

            int rowCount = tblServicios.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                // Extrae datos de cada fila
                String cadena = String.valueOf(tblServicios.getValueAt(i, 0));
                String[] codigos = cadena.split(" - ");
                int codSer = Integer.parseInt(codigos[0].trim());
                int codMed = Integer.parseInt(codigos[1].trim());
                String horaEntrada = tblServicios.getValueAt(i, 3).toString();
                String horaSalida = tblServicios.getValueAt(i, 4).toString();
                String notaAdicional = tblServicios.getValueAt(i, 5).toString();

                // Verifica si el detalle ya existe en la base de datos
                String checkSQL = "SELECT COUNT(*) FROM DETALLE_CITA WHERE cita_id = " + idCita
                        + " AND detalle_servicio_serv_id = " + codSer
                        + " AND detalle_servicio_med_id = " + codMed;
                ResultSet rs = sent.executeQuery(checkSQL);

                rs.next();
                int count = rs.getInt(1);
                if (count == 0) {
                    // Si no existe, realiza el INSERT
                    String insertSQL = "INSERT INTO DETALLE_CITA (cita_id, detalle_servicio_serv_id, detalle_servicio_med_id, horaEntrada, "
                            + "horaSalida, nota_adicional) "
                            + "VALUES (" + idCita + ", " + codSer + ", " + codMed + ", '" + horaEntrada + "', '" + horaSalida + "', '" + notaAdicional + "')";
                    sent.executeUpdate(insertSQL);

                    // Actualiza disponibilidad en DETALLE_SERVICIO
                    String updateSQL = "UPDATE DETALLE_SERVICIO SET disponibilidad = false WHERE servicio_id = " + codSer + " AND medico_id = " + codMed;
                    sent.executeUpdate(updateSQL);
                }
            }

            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw new Exception("Error al insertar detalle de servicio no repetido --> " + e.getLocalizedMessage());
        } finally {
            objConectar.desconectar();
        }
    }

}

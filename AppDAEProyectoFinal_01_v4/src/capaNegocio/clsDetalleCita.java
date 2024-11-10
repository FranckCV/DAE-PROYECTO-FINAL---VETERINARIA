/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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

}

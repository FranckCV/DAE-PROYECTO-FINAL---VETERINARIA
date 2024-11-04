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

    public void insertarDetalleCita(Integer citaId, Integer servicioId, Integer medicoId, String horaEntrada, String horaSalida, String notaAdicional) throws Exception {
        String strSQL = "INSERT INTO DETALLE_CITA (cita_id, detalle_servicio_serv_id, detalle_servicio_med_id, horaEntrada, horaSalida, nota_adicional) "
                + "VALUES (" + citaId + ", " + servicioId + ", " + medicoId + ", '" + horaEntrada + "', '" + horaSalida + "', '" + notaAdicional + "')";

        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al insertar detalle de cita --> " + e.getLocalizedMessage());
        }
    }

}

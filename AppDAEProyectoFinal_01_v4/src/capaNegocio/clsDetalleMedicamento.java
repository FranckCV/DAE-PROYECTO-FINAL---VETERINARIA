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
public class clsDetalleMedicamento {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    Connection con = null;
    Statement sent;

    public void registrarDetalleMedicamento(int idCita, JTable tblMedicamentos) throws Exception {
        try {
            objConectar.conectar();
            con = objConectar.getCon();
            con.setAutoCommit(false);
            sent = con.createStatement();

            int rowCount = tblMedicamentos.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                int codMedicamento = Integer.parseInt(tblMedicamentos.getValueAt(i, 0).toString());
                int detalleCitaId = idCita; // ID de la cita
                int detalleServicioServId = Integer.parseInt(tblMedicamentos.getValueAt(i, 1).toString());
                int detalleServicioMedId = Integer.parseInt(tblMedicamentos.getValueAt(i, 2).toString());
                double dosis = Double.parseDouble(tblMedicamentos.getValueAt(i, 3).toString());
                String indicacion = tblMedicamentos.getValueAt(i, 4).toString();
                int cantidad = Integer.parseInt(tblMedicamentos.getValueAt(i, 5).toString());

                String strSQL = "INSERT INTO DETALLE_MEDICAMENTO (medicamento_id, detalle_cita_id, detalle_servicio_servicio_id, "
                        + "detalle_servicio_medico_id, dosis, indicacion, cantidad) "
                        + "VALUES (" + codMedicamento + ", " + detalleCitaId + ", " + detalleServicioServId + ", "
                        + detalleServicioMedId + ", " + dosis + ", '" + indicacion + "', " + cantidad + ")";
                sent.executeUpdate(strSQL);
            }

            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw new Exception("Error al registrar detalle de medicamento --> " + e.getLocalizedMessage());
        } finally {
            objConectar.desconectar();
        }
    }

    public ResultSet obtenerDetalleMedicamentosPorCita(int idCita) throws Exception {
        strSQL = "SELECT detalle_medicamento.*, medicamento.nombre AS nombre_medicamento, "
                + "medicamento.costo "
                + "FROM detalle_medicamento "
                + "INNER JOIN medicamento ON detalle_medicamento.medicamento_id = medicamento.id "
                + "WHERE detalle_medicamento.detalle_cita_id = " + idCita;

        try {
            objConectar.conectar();
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al obtener detalle de medicamentos por cita --> " + e.getLocalizedMessage());
        } finally {
            objConectar.desconectar();
        }
    }
}

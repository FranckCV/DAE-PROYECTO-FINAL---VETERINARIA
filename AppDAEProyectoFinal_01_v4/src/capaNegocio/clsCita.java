/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capaNegocio;

import capaDatos.clsJDBC;
import java.util.Date;
import java.sql.*;

/**
 *
 * @author Junior
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

    public ResultSet buscarCita(Integer idCita) throws Exception {
        strSQL = "SELECT * FROM CITA WHERE id = " + idCita;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar cita --> " + e.getLocalizedMessage());
        }
    }

    // Método para insertar una nueva cita
    public void insertarCita(Integer id, Integer estadoCitaId, Date fechaCita, String observacion, Integer custodiaMascotaId, Integer custodiaDueñoId) throws Exception {
        strSQL = "INSERT INTO CITA (id, estado_cita_id, fecha_cita, observacion, CUSTODIAMASCOTAid, CUSTODIADUEÑOid) "
                + "VALUES (" + id + ", " + estadoCitaId + ", '" + fechaCita + "', '" + observacion + "', " + custodiaMascotaId + ", " + custodiaDueñoId + ")";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al insertar cita --> " + e.getLocalizedMessage());
        }
    }

}

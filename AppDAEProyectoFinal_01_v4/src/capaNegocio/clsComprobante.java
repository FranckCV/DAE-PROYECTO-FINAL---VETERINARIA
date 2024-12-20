/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.Date;
import java.sql.ResultSet;

/**
 *
 * @author franc
 */
public class clsComprobante {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;

    public ResultSet listarComprobantes() throws Exception {
        strSQL = "SELECT * FROM COMPROBANTE ORDER BY fecha DESC";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar comprobantes: " + e.getMessage());
        }
    }

    public int registrarComprobante(String tipo, String serieNumero, double montoTotal, Date fecha, int citaId) throws Exception {
        String strSQL = "INSERT INTO COMPROBANTE (Tipo, serie_numero, monto_total, fecha, CITAid) "
                + "VALUES ('" + tipo + "', '" + serieNumero + "', " + montoTotal + ", '" + fecha + "', " + citaId + ")";
        try {
            objConectar.ejecutarBD(strSQL);

            strSQL = "SELECT citaid, COUNT(*) as cantidad FROM COMPROBANTE WHERE Tipo = '" + tipo + "' "
                    + "AND serie_numero = '" + serieNumero + "' AND CITAid = " + citaId + " "
                    + "GROUP BY citaid";

            rs = objConectar.consultarBD(strSQL);

            rs.next();
            int count = rs.getInt("cantidad");
            if (count > 0) {
                return rs.getInt("citaid");
            }
        } catch (Exception e) {
            throw new Exception("Error al registrar comprobante: " + e.getMessage());
        }
        return 0;
    }

    public String generarNumeroSerieComprobante() throws Exception {
        strSQL = "SELECT serie_numero "
                + "FROM COMPROBANTE "
                + "ORDER BY serie_numero DESC "
                + "LIMIT 1";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return String.format("%06d", rs.getInt("serie_numero") + 1);
            } else {
                return "000001";
            }
        } catch (Exception e) {
            throw new Exception("Error al generar número de serie para comprobante: " + e.getMessage());
        }
    }

    public ResultSet buscarComprobante(String tipo, String serieNumero) throws Exception {
        strSQL = "SELECT * FROM COMPROBANTE WHERE Tipo = '" + tipo + "' AND serie_numero = '" + serieNumero + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                // Retorna el ResultSet con los datos del comprobante encontrado
                return rs;
            } else {
                throw new Exception("Comprobante no encontrado para el tipo: " + tipo + " y serie: " + serieNumero);
            }
        } catch (Exception e) {
            throw new Exception("Error al buscar comprobante: " + e.getMessage());
        }
    }

//    public String generarNumeroSerieComprobante(String tipo) throws Exception {
//        strSQL = "SELECT COALESCE(MAX(serie_numero::int), 0) + 1 AS nuevo_numero "
//                + "FROM COMPROBANTE WHERE Tipo = '" + tipo + "'";
//        try {
//            rs = objConectar.consultarBD(strSQL);
//            if (rs.next()) {
//                return String.format("%06d", rs.getInt("nuevo_numero"));
//            }
//        } catch (Exception e) {
//            throw new Exception("Error al generar número de serie para comprobante: " + e.getMessage());
//        }
//        return "000001";
//    }
}

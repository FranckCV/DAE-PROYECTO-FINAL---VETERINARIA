/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capaNegocio;
import capaDatos.clsJDBC;
import java.sql.ResultSet;

/**
 *
 * @author franc
 */
public class clsDetalle_Servicio {
    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    public static final String TABLA = "DETALLE_SERVICIO";
    public static final String MEDICO_ID = "medico_id";
    public static final String SERVICIO_ID = "servicio_id";
    public static final String DISPONIBILIDAD = "disponibilidad";
    
    
    public ResultSet listarServiciosxMedico(int med_id) throws Exception {
        strSQL = " select  " +
                "    ser.id , " +
                "    ser.nom_servicio , " +
                "    ser.descripcion , " +
                "    ser.vigencia , " +
                "    ser.costo " +
                " from servicio ser " +
                " left join detalle_servicio det ON det.servicio_id = ser.id " +
                " where det.medicoid = " + med_id
                + " ";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error en listar la tabla " + TABLA + " / " + e.getMessage());
        }
    }
    
    
    
    
}

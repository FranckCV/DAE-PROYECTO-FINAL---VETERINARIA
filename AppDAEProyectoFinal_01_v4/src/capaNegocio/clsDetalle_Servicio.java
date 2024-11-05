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
        strSQL= " select  " +
                "    ser.id , " +
                "    ser.nom_servicio , " +
                "    ser.descripcion , " +
                "    ser.disponibilidad , " +
                "    ser.costo , " +
                "    det.disponibilidad as det_disp"+
                " from servicio ser " +
                " left join detalle_servicio det ON det.servicio_id = ser.id " +
                " where det.medico_id = " + med_id + " and ser.disponibilidad = true " +
                " order by det.disponibilidad desc , ser.id ";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error en listar la tabla " + TABLA + " / " + e.getMessage());
        }
    }
    
    public ResultSet listarServiciosDisponiblesRestantesxMedico(int med_id) throws Exception {
        strSQL= " select  " +
                "    ser.id , " +
                "    ser.nom_servicio , " +
                "    ser.descripcion , " +
                "    ser.disponibilidad , " +
                "    ser.costo , " +
                "    det.disponibilidad as det_disp"+
                " from servicio ser " +
                " left join detalle_servicio det ON det.servicio_id = ser.id AND det.medico_id = " +med_id+
                " where det.medico_id IS NULL AND ser.disponibilidad = true " +
                " order by det.disponibilidad desc , ser.id ";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al obtener datos de detalle del servicio --> " + e.getMessage());
        }
    }
    
    
    public void registrarAsignacion(int ser_id, int med_id) throws Exception {
        strSQL = "insert into " + TABLA + " values (" + ser_id + ", " + med_id + ", true )";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar en la tabla " + TABLA + " -->" + e.getMessage());
        }
    }
    
    
    public void eliminarAsignacion(int ser_id, int med_id) throws Exception {
        strSQL = "delete from " + TABLA + " where "+SERVICIO_ID +" = "+ser_id+" and "+MEDICO_ID+" = "+med_id+" ";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar en la tabla " + TABLA + " -->" + e.getMessage());
        }
    }
    
    
    
}

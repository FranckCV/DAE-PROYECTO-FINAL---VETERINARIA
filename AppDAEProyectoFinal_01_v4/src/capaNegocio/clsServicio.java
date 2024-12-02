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
public class clsServicio {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    public static final String TABLA = "SERVICIO";
    public static final String ID = "id";
    public static final String NOMBRE = "nom_servicio";
    public static final String DESCRIPCION = "descripcion";
    public static final String DISPONIBILIDAD = "disponibilidad"; //cambiar despues de nuevo script
    public static final String COSTO = "costo";

    public ResultSet listarServicios() throws Exception {
        strSQL = "select * from " + TABLA + " Tab "
                + " order by Tab." + ID
                + " ";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error en listar la tabla " + TABLA + " / " + e.getMessage());
        }
    }

    public Integer obtenerID(String nom) throws Exception {
        strSQL = "Select " + ID + " from " + TABLA + " where " + NOMBRE + " = '" + nom + "' ";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt(ID);
            }
        } catch (Exception e) {
            throw new Exception("Error al buscar codigo de " + TABLA + " con el nombre " + nom + " --> " + e.getMessage());
        }
        return 0;
    }

    public Integer generarIDServicio() throws Exception {
        strSQL = "Select COALESCE(MAX(" + ID + "),0)+1 as valor from " + TABLA;
        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getInt("valor");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar codigo de la tabla " + TABLA + " --> " + e.getMessage());
        }
        return 0;
    }

    public ResultSet buscarServicio(Integer id) throws Exception {
        strSQL = "select * from " + TABLA + " Tab "
                + " where " + ID + " = " + id;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar en la tabla " + TABLA + ": " + e.getMessage());
        }
    }

    public void registrarServicio(int id, String nom, String desc, double cost) throws Exception {
        strSQL = "insert into " + TABLA + " values (" + id + ", '" + nom + "', '" + desc + "', true," + cost + ")";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar en la tabla " + TABLA + " -->" + e.getMessage());
        }
    }

    public void modificarServicio(int id, String nom, String desc, double cost) throws Exception {
        strSQL = "update " + TABLA + " set "
                + NOMBRE + " = '" + nom + "' ,"
                + DESCRIPCION + " = '" + desc + "' ,"
                + COSTO + " = " + cost + " "
                + " where " + ID + " = " + id + "";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar la tabla " + TABLA + " -->" + e.getMessage());
        }
    }

    public void eliminarServicio(Integer id) throws Exception {
        strSQL = "delete from " + TABLA + " where " + ID + " = " + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar " + TABLA + ": " + e.getMessage());
        }
    }

    public void cambiarDisponibilidad(Integer id) throws Exception {
        Boolean disp = null;

        rs = objConectar.consultarBD("select " + DISPONIBILIDAD + " from " + TABLA + " where " + ID + " = " + id + " ");

        while (rs.next()) {
            disp = rs.getBoolean(DISPONIBILIDAD);
        }

        try {
            strSQL = "update " + TABLA + " set " + DISPONIBILIDAD + " = " + !disp + " where " + ID + " = '" + id + "'";
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al cambiar disponibilidad en ID:" + id + " en tabla " + TABLA + ": " + e.getMessage());
        }
    }

    public ResultSet obtenerDatosDetalleServicio(int codigoServicio, String documentoMedico) throws Exception {
        strSQL = "SELECT ds.*, s.id, s.nom_servicio, s.descripcion, s.costo, "
                + "m.nombres, m.apePaterno, m.apeMaterno "
                + "FROM DETALLE_SERVICIO ds "
                + "INNER JOIN SERVICIO s ON ds.servicio_id = s.id "
                + "INNER JOIN MEDICO m ON ds.medico_id = m.id "
                + "WHERE s.id = " + codigoServicio + " "
                + "AND m.doc_identidad = '" + documentoMedico + "' "
                + "AND m.disponibilidad = true "
                + "AND m.vigencia = true";

        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al obtener datos de detalle del servicio --> " + e.getMessage());
        }
    }

    public ResultSet obtenerDatosDetalleServicioPorCodServicio(int codigoServicio) throws Exception {
        strSQL = "SELECT ds.*, s.id, s.nom_servicio, s.descripcion, s.costo, "
                + "m.nombres, m.apePaterno, m.apeMaterno "
                + "FROM DETALLE_SERVICIO ds "
                + "INNER JOIN SERVICIO s ON ds.servicio_id = s.id "
                + "INNER JOIN MEDICO m ON ds.medico_id = m.id "
                + "WHERE s.id = " + codigoServicio + " "
                + "AND m.disponibilidad = true " // Asegura que el médico esté disponible
                + "AND m.vigencia = true";

        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al obtener datos de detalle del servicio --> " + e.getMessage());
        }
    }

    public ResultSet obtenerDatosDetalleServicioPorMedico(String documentoMedico) throws Exception {
        // Consulta SQL que incluye disponibilidad y vigencia del médico
        strSQL = "SELECT ds.*, s.id, s.nom_servicio, s.descripcion, s.costo, "
                + "m.nombres, m.apePaterno, m.apeMaterno "
                + "FROM DETALLE_SERVICIO ds "
                + "INNER JOIN SERVICIO s ON ds.servicio_id = s.id "
                + "INNER JOIN MEDICO m ON ds.medico_id = m.id "
                + "WHERE m.doc_identidad = '" + documentoMedico + "' "
                + "AND m.disponibilidad = true " // Asegura que el médico esté disponible
                + "AND m.vigencia = true";  // Asegura que el médico esté vigente

        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al obtener datos de detalle del servicio --> " + e.getMessage());
        }
    }

    public ResultSet obtenerDatosDetalleServicioTodos() throws Exception {
        // Consulta SQL con las condiciones de disponibilidad y vigencia del médico
        strSQL = "SELECT ds.*, s.id, s.nom_servicio, s.descripcion, s.costo, "
                + "m.nombres, m.apePaterno, m.apeMaterno "
                + "FROM DETALLE_SERVICIO ds "
                + "INNER JOIN SERVICIO s ON ds.servicio_id = s.id "
                + "INNER JOIN MEDICO m ON ds.medico_id = m.id "
                + "WHERE m.disponibilidad = true " // Solo médicos disponibles
                + "AND m.vigencia = true";  // Solo médicos vigentes

        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al obtener todos los datos de detalle del servicio --> " + e.getMessage());
        }
    }

    public ResultSet obtenerDatosDetalleServicio(int codigoServicio, int codMedico) throws Exception {
        strSQL = "SELECT ds.*, s.id, s.nom_servicio, s.descripcion, s.costo, "
                + "m.nombres, m.apePaterno, m.apeMaterno "
                + "FROM DETALLE_SERVICIO ds "
                + "INNER JOIN SERVICIO s ON ds.servicio_id = s.id "
                + "INNER JOIN MEDICO m ON ds.medico_id = m.id "
                + "WHERE s.id = " + codigoServicio + " "
                + "AND m.id = " + codMedico + " "
                + "AND m.disponibilidad = true " // Solo médicos disponibles
                + "AND m.vigencia = true";  // Solo médicos vigentes

        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al obtener datos de detalle del servicio --> " + e.getMessage());
        }
    }

}

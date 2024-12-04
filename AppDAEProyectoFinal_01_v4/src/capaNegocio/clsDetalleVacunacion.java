/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capaNegocio;

import capaDatos.clsJDBC;
import static capaNegocio.clsVacuna.TABLA;
import com.sun.jdi.connect.spi.Connection;
import java.beans.Statement;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author franc
 */
public class clsDetalleVacunacion {
    

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    Connection con = null;
    Statement sent;

//listarDueños
    public ResultSet listarDetalleV() throws Exception {
        strSQL = "SELECT * FROM DETALLES_VACUNACION  order by 1";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar Detalle Vacunación");
        }
    }

    public ResultSet listarVacunas() throws Exception {
        strSQL = "select v.*, e.nombre as es_nom, v.disponibilidad as v_dis from VACUNA v INNER JOIN especie e ON e.id = v.especie_id where v.disponibilidad=true order by id";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error en listar la tabla " + TABLA + " / " + e.getMessage());
        }
    }

    public ResultSet filtrarVacunas(int id_v) throws Exception {
        strSQL = "SELECT v.*, e.nombre as es_nom "
                + "FROM vacuna v "
                + "INNER JOIN especie e ON e.id = v.especie_id"
                + " WHERE v.id =" + id_v;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al filtrar Custodia por Código de Dueño" + e.getMessage());
        }
    }

    public ResultSet filtrarVacunasN(String nom_v) throws Exception {
        strSQL = "SELECT v.*, e.nombre as es_nom "
                + "FROM vacuna v "
                + "INNER JOIN especie e ON e.id = v.especie_id "
                + "WHERE UPPER(v.nombre) LIKE UPPER('%" + nom_v + "%')";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al filtrar Custodia por Código de Dueño" + e.getMessage());
        }
    }


    public void asignarVacuna(int va_id, int mas_id, Date F_V, String obs) throws Exception {
        String fecha = (F_V == null) ? "NULL" : "'" + new java.sql.Date(F_V.getTime()) + "'";

        strSQL = "INSERT INTO DETALLES_VACUNACION (vacuna_id, mascota_id, fecha_vacuna, observacion) VALUES ("
                + va_id + ", " + mas_id + ", " + fecha + ", '" + obs + "' )";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al Asignar la vacuna -->" + e.getMessage());
        }
    }

    public boolean existeDetalleVacunacion(int vacId, int masId) throws Exception {
        String strSQL = "SELECT COUNT(*) FROM DETALLES_VACUNACION WHERE vacuna_id = " + vacId + " AND mascota_id = " + masId;
        ResultSet rs = null;

        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt(1) > 0;  // Si el contador es mayor que 0, existe la combinación
            }
        } catch (Exception e) {
            throw new Exception("Error al verificar la existencia de la combinación vacuna-mascota: " + e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
            }
        }
        return false;
    }

    public void eliminarDetalleVac(int id_V, int id_M) throws Exception {
        strSQL = "DELETE FROM DETALLES_VACUNACION WHERE vacuna_id = " + id_V + " AND mascota_id = " + id_M;

        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar el código de mascota: " + id_M + " --> " + e.getMessage());
        }
    }
    
    
    public ResultSet listarDetalleVigentes() throws Exception {
        strSQL = "SELECT dv.*,  ma.nombre as nom_mas,va.nombre as nom_vac FROM DETALLES_VACUNACION  dv "
                + " inner join mascota ma on dv.mascota_id= ma.id"
                + " inner join vacuna va on dv.vacuna_id=va.id  ";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar Detalle Vacunación");
        }
    }

    
    public ResultSet listarVacunasPorEspecie(int idEspecie) throws Exception {
        strSQL = "SELECT v.id, v.nombre, v.dosis_x_kgpeso, e.nombre as es_nom, v.disponibilidad "
                + "FROM VACUNA v "
                + "INNER JOIN ESPECIE e ON e.id = v.especie_id "
                + "WHERE v.disponibilidad = true AND v.especie_id = " + idEspecie + "";  // Consulta corregida

        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al filtrar las vacunas por Nombre de mascota" + e.getMessage());
        }
    }
    
    public ResultSet filtrarPorNombreYEspecie(String nom_m, int idEspecie) throws Exception {
        // Construcción dinámica de la consulta SQL
        strSQL = "SELECT v.id AS vacuna_id, v.nombre AS vacuna_nombre, v.dosis_x_kgpeso, e.nombre AS especie_nombre, "
                + "v.disponibilidad, ma.id AS mascota_id, ma.nombre AS mascota_nombre "
                + "FROM VACUNA v "
                + "INNER JOIN ESPECIE e ON e.id = v.especie_id "
                + "INNER JOIN MASCOTA ma ON e.id = ma.especie_id "
                + "WHERE v.disponibilidad = true AND v.especie_id = " + idEspecie
                + " AND UPPER(ma.nombre) LIKE UPPER('%" + nom_m + "%')";

        try {
            rs = objConectar.consultarBD(strSQL); // Ejecuta la consulta
            return rs; // Devuelve el conjunto de resultados
        } catch (Exception e) {
            throw new Exception("Error al filtrar por nombre de mascota y especie: " + e.getMessage());
        }
    }
    
    public ResultSet filtrarporNombreNVIG(String nom_m) throws Exception {
        strSQL = "SELECT dv.*, ma.nombre as nom_mas, va.nombre as nom_vac "
                + "FROM DETALLES_VACUNACION dv "
                + "INNER JOIN mascota ma ON dv.mascota_id = ma.id "
                + "INNER JOIN vacuna va ON dv.vacuna_id = va.id "
                + "WHERE UPPER(ma.nombre) LIKE UPPER('%" + nom_m + "%')";

        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al filtrar Custodia por Nombre de mascota" + e.getMessage());
        }
    }
    
    
    
    
}

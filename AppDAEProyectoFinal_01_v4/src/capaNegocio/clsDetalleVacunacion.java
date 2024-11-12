package capaNegocio;

import capaDatos.clsJDBC;
import static capaNegocio.clsVacuna.TABLA;
import com.sun.jdi.connect.spi.Connection;
import java.beans.Statement;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author Windows10
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
}

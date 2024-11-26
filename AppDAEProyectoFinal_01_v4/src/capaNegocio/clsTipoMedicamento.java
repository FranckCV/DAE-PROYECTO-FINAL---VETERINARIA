package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.ResultSet;

/**
 *
 * @author Grupo_Veterinaria
 */
public class clsTipoMedicamento {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;

    public ResultSet listarTiposMedicamentos() throws Exception {
        strSQL = "SELECT * FROM tipo_medicamento order by id";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar los tipos de medicamentos --> " + e.getMessage());
        }
    }

    public Integer generarCodigoTipoMedicamento() throws Exception {
        strSQL = "SELECT COALESCE(MAX(id), 0) + 1 AS codigo FROM tipo_medicamento";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar el código del tipo de medicamento --> " + e.getMessage());
        } finally {
            if (rs != null) {
                rs.close(); // Cerrar ResultSet para liberar recursos
            }
        }
        return 0;
    }

    public Integer obtenerCodigoTipoMedicamento(String nombre) throws Exception {
        strSQL = "SELECT id AS codigo FROM tipo_medicamento WHERE nomTipo = '" + nombre + "';";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("codigo");
            } else {
                throw new Exception("No se encontró el tipo de medicamento con el nombre: " + nombre);
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener el código del tipo de medicamento --> " + e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

    public void registrarTipoMedicamento(int id, String nomtipo) throws Exception {
        strSQL = "INSERT INTO tipo_medicamento VALUES(" + id + ", '" + nomtipo + "')";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar el tipo de medicamento --> " + e.getMessage());
        }
    }

    public ResultSet buscarTipoMedicamento(Integer id) throws Exception {
        strSQL = "SELECT * FROM tipo_medicamento WHERE id = " + id;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar tipo de medicamento --> " + e.getMessage());
        }
    }

    public void eliminarTipoMedicamento(Integer id) throws Exception {
        strSQL = "DELETE FROM tipo_medicamento WHERE id = " + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar tipo de medicamento --> " + e.getMessage());
        }
    }

    public void modificarTipoMedicamento(Integer id, String nombre) throws Exception {
        strSQL = "UPDATE tipo_medicamento SET nomtipo = '" + nombre + "' WHERE id = " + id ;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar el tipo de medicamento --> " + e.getMessage());
        }
    }

    public boolean existeNombreTipoMedicamento(String nombre) throws Exception {
        strSQL = "SELECT COUNT(*) AS cantidad FROM tipo_medicamento WHERE nomTipo = '" + nombre + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("cantidad") > 0;
            }
        } catch (Exception e) {
            throw new Exception("Error al verificar el nombre del tipo de medicamento --> " + e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return false;
    }
}

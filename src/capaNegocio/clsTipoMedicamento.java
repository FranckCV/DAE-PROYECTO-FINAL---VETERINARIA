package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.ResultSet;

/**
 *
 * @author Leonardo
 */
public class clsTipoMedicamento {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;

    public ResultSet listarTiposMedicamentos() throws Exception {
        strSQL = "select * from tipo_medicamento";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar los tipos de medicamentos --> " + e.getMessage());
        }
    }

    public Integer generarCodigoTipoMedicamento() throws Exception {
        strSQL = "Select COALESCE(Max(id), 0) + 1 as codigo from tipo_medicamento";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar el código del tipo de medicamento --> " + e.getMessage());
        }
        return 0;
    }

    public Integer obtenerCodigoTipoMedicamento(String nombre) throws Exception {
        strSQL = "Select id from tipo_medicamento where nombre = '" + nombre + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("id");  // Retorna el ID del tipo de medicamento
            } else {
                throw new Exception("No se encontró el tipo de medicamento con el nombre: " + nombre);
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener el código del tipo de medicamento --> " + e.getMessage());
        }
    }

public void registrarTipoMedicamento(int id, String nombre) throws Exception {
        strSQL = "Insert into tipo_medicamento Values(" + id + ",'" + nombre + "')";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar el tipo de medicamento --> " + e.getMessage());
        }
    }

    public ResultSet buscarTipoMedicamento(Integer id) throws Exception {
        strSQL = "Select * from tipo_medicamento where id=" + id;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar tipo de medicamento --> " + e.getMessage());
        }
    }

    public void eliminarTipoMedicamento(Integer id) throws Exception {
        strSQL = "Delete from tipo_medicamento where id=" + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar tipo de medicamento --> " + e.getMessage());
        }
    }

    public void modificarTipoMedicamento(Integer id, String nombre) throws Exception {
        strSQL = "Update tipo_medicamento set nombre='" + nombre + "' where id=" + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar el tipo de medicamento --> " + e.getMessage());
        }
    }
}

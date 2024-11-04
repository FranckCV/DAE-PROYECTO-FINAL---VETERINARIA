package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.ResultSet;

/**
 *
 * @author Leonardo
 */
public class clsMedicamento {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;

    public ResultSet listarMedicamentos() throws Exception {
        String strSQL = "SELECT m.id, m.nombre, m.costo, m.stock, m.presentacion, m.vigencia, t.nomtipo AS tipo_medicamento "
                + "FROM medicamento m "
                + "JOIN tipo_medicamento t ON m.tipo_medicamento_id = t.id";
        try {
            return objConectar.consultarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al listar los medicamentos --> " + e.getMessage());
        }
    }

    public Integer generarCodigoMedicamento() throws Exception {
        strSQL = "Select COALESCE(Max(id), 0)+1 as codigo from medicamento";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar el código del medicamento --> " + e.getMessage());
        }
        return 0;
    }

    public void registrarMedicamento(int id, String nombre, double costo, int stock, String presentacion, boolean vigencia, int tipoMedicamentoId) throws Exception {
        strSQL = "Insert into medicamento Values(" + id + ",'" + nombre + "'," + costo + "," + stock + ",'" + presentacion + "','" + vigencia + "'," + tipoMedicamentoId + ")";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar el medicamento --> " + e.getMessage());
        }
    }

    public ResultSet buscarMedicamento(Integer id) throws Exception {
        strSQL = "Select * from medicamento where id=" + id;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar medicamento --> " + e.getMessage());
        }
    }

    public void eliminarMedicamento(Integer id) throws Exception {
        strSQL = "Delete from medicamento where id=" + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar medicamento --> " + e.getMessage());
        }
    }

    public void modificarMedicamento(Integer id, String nombre, double costo, int stock, String presentacion, boolean vigencia , int tipoMedicamentoId) throws Exception {
        strSQL = "Update medicamento set nombre='" + nombre + "', costo=" + costo + ", stock=" + stock + ", presentacion='" + presentacion + "', vigencia= '" + vigencia + "', tipo_medicamento_id=" + tipoMedicamentoId + " where id=" + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar el medicamento --> " + e.getMessage());
        }
    }

    // PARA LA TRANSACCION
    public ResultSet filtrar(String nom) throws Exception {
        strSQL = "SELECT M.*, T.nomTipo FROM (SELECT * FROM medicamento "
                + "WHERE UPPER(nombre) LIKE UPPER('%" + nom + "%') AND vigencia = true) M "
                + "INNER JOIN tipo_medicamento T on M.tipo_medicamento_id = T.id";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al filtrar medicamentos --> " + e.getLocalizedMessage());
        }
    }

    public Integer getStock(int cod) throws Exception {
        strSQL = "SELECT stock FROM medicamento WHERE id = " + cod + ";";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener stock --> " + e.getMessage());
        }
        return 0;
    }

}

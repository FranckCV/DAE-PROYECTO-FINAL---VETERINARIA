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
public class clsMedicamento {
    

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;

    public ResultSet listarMedicamentos() throws Exception {
            String strSQL = "SELECT m.id, m.nombre, m.costo, m.stock, m.presentacion, m.vigencia, t.nomtipo AS tipo_medicamento "
                    + "FROM medicamento m "
                    + "JOIN tipo_medicamento t ON m.tipo_medicamento_id = t.id order by id";
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
        String strSQL = "INSERT INTO medicamento VALUES("
                + id + ", '"
                + nombre + "', "
                + costo + ", "
                + stock + ", '"
                + presentacion + "', "
                + (vigencia ? "true" : "false") + ", "
                + tipoMedicamentoId + ")";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar el medicamento --> " + e.getMessage());
        }
    }

    public ResultSet buscarMedicamento(Integer id) throws Exception {
        strSQL = "SELECT m.id, m.nombre, m.costo, m.stock, m.presentacion, m.vigencia, t.nomtipo AS tipo_medicamento "
                + "FROM medicamento m "
                + "JOIN tipo_medicamento t ON m.tipo_medicamento_id = t.id "
                + "WHERE m.id = " + id;
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

    public void modificarMedicamento(Integer id, String nombre, double costo, int stock, String presentacion, int tipoMedicamentoId) throws Exception {
        strSQL = "Update medicamento set nombre='" + nombre + "', costo=" + costo + ", stock=" + stock + ", presentacion='" + presentacion + "', tipo_medicamento_id=" + tipoMedicamentoId
                + " where id=" + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar el medicamento --> " + e.getMessage());
        }
    }

    public void actualizarDisponibilidad(int id, boolean vigencia) throws Exception {
        strSQL = "UPDATE medicamento SET vigencia = '" + vigencia + "' WHERE id = " + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al actualizar disponibilidad del medicamento --> " + e.getMessage());
        }
    }

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
        ResultSet rs = null;
        try {
            strSQL = "SELECT stock FROM medicamento WHERE id = " + cod + ";";
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener stock --> " + e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return 0;
    }

    public boolean existeNombreMedicamento(String nombre) throws Exception {
        strSQL = "SELECT COUNT(*) AS cantidad FROM medicamento WHERE nombre = '" + nombre + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("cantidad") > 0;
            }
        } catch (Exception e) {
            throw new Exception("Error al verificar el nombre del medicamento --> " + e.getMessage());
        }
        return false;
    }

    public void reducirStock(int idProducto, int cantidad) throws Exception {
        strSQL = "SELECT stock FROM MEDICAMENTO WHERE id = " + idProducto;
        rs = null;

        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                int stockActual = rs.getInt("stock");
                if (stockActual < cantidad) {
                    throw new Exception("Stock insuficiente para el producto con ID " + idProducto);
                }
            } else {
                throw new Exception("El producto con ID " + idProducto + " no existe en la base de datos.");
            }

            strSQL = "UPDATE MEDICAMENTO SET stock = stock - " + cantidad + " WHERE id = " + idProducto;
            objConectar.ejecutarBD(strSQL);

        } catch (Exception e) {
            throw new Exception("Error al reducir el stock del producto con ID " + idProducto + " --> " + e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    System.err.println("Error al cerrar el ResultSet: " + e.getMessage());
                }
            }
        }
    }
    
}

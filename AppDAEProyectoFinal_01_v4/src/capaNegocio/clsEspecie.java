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
public class clsEspecie {
    

    String verificar;
    boolean dependencia;

    public boolean isDependencia() {
        return dependencia;
    }
    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;

    public static final String TABLA = "Especie";
    public static final String ID = "id";
    public static final String NOMBRE = "nombre";

    public ResultSet listarEspecies() throws Exception {
        strSQL = "select * from " + TABLA +" where disponibilidad = true order by id";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error en listar la tabla " + TABLA + " / " + e.getMessage());
        }
    }

    public Integer obtenerIdEspecie(String nom) throws Exception {
        strSQL = "Select " + ID + " from " + TABLA + " where " + NOMBRE + " = '" + nom + "' ";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt(ID);
            }
        } catch (Exception e) {
            throw new Exception("Error al buscar ID de " + TABLA + " con el nombre " + nom + " --> " + e.getMessage());
        }
        return 0;
    }

    public Integer generarIDEspecie() throws Exception {
        strSQL = "Select COALESCE(MAX(" + ID + "),0)+1 as valor from " + TABLA;
        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getInt("valor");
            }
        } catch (Exception e) {
            throw new Exception("Error al mostrar ID de la tabla " + TABLA + " --> " + e.getMessage());
        }
        return 0;
    }

    public ResultSet buscarEspecie(Integer id) throws Exception {
        strSQL = " select * from " + TABLA + " where " + ID + " = " + id;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar en la tabla " + TABLA + ": " + e.getMessage());
        }
    }

    public void registrarEspecie(int id, String nom) throws Exception {
        strSQL = "insert into " + TABLA + " values (" + id + ", '" + nom + "', true )";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar en la tabla " + TABLA + " -->" + e.getMessage());
        }
    }

    public void modificarEspecie(int id, String nom) throws Exception {
        strSQL = "update " + TABLA + " set " + NOMBRE + " = '" + nom + "' "
                + " where " + ID + " = " + id + "";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar la tabla " + TABLA + " -->" + e.getMessage());
        }
    }

    public int eliminarEspecie(Integer id) throws Exception {
        verificar = "select COUNT(*) as cantidad from raza where especie_id= " + id;
        strSQL = "delete from " + TABLA + " where " + ID + " = " + id;
        try {
            rs = objConectar.consultarBD(verificar);
            int count = -1;
            while (rs.next()) {
                count = rs.getInt("cantidad");
                if (count > 0) {
                    dependencia = true;
                }
            }
            if (!dependencia) {
                objConectar.ejecutarBD(strSQL);
//                System.out.println(count);
                return 0;
            } else {
//                System.out.println(count);
                return 1;
            }
        } catch (Exception e) {
            throw new Exception("Error al eliminar " + TABLA + ": " + e.getMessage());
        }
    }

    public boolean validarNombre(String nom) throws Exception {
        strSQL = "SELECT nombre FROM especie WHERE LOWER(nombre) = LOWER('" + nom + "')";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            throw new Exception("Error al validar nombre: " + e.getMessage());
        }
        return false;
    }
    
    
}

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
public class clsRaza {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;

    public static final String TABLA = "RAZA";
    public static final String ID = "id";
    public static final String NOMBRE = "nombre";
    public static final String ESPECIE_ID = "especie_id";

    public ResultSet listarRazas() throws Exception {
        strSQL = "select * from " + TABLA;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error en listar la tabla " + TABLA + " / " + e.getMessage());
        }
    }

     public ResultSet listarRazasE(Integer cod) throws Exception {
        strSQL = "select nombre from raza rs where especie_id=" + cod;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error en listar la tabla " + TABLA + " / " + e.getMessage());
        }
    }
    public Integer obtenerIdRaza(String nom) throws Exception {
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

    public Integer obtenerIdEspecie(String nom) throws Exception {
        strSQL = "Select " + ID + " from especie where " + NOMBRE + " = '" + nom + "' ";
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

    public Integer generarIDRaza() throws Exception {
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

    public ResultSet buscarRaza(Integer id) throws Exception {
        strSQL = "select * from " + TABLA + " where " + ID + " = " + id;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar en la tabla " + TABLA + ": " + e.getMessage());
        }
    }

    public void registrarRaza(int id, String nom, int especie_id) throws Exception {
        strSQL = "insert into " + TABLA + " values (" + id + ", '" + nom + "', " + especie_id + ")";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar en la tabla " + TABLA + " -->" + e.getMessage());
        }
    }

    public void modificarRaza(int id, String nom, Integer especie_id) throws Exception {
        strSQL = "update " + TABLA + " set " + NOMBRE + " = '" + nom + "', " + ESPECIE_ID + " = " + especie_id + " "
                + "where " + ID + " = " + id + "";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar la tabla " + TABLA + " -->" + e.getMessage());
        }
    }

    public void eliminarRaza(Integer id) throws Exception {
        strSQL = "delete from " + TABLA + " where " + ID + " = " + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar " + TABLA + ": " + e.getMessage());
        }
    }

    public String obtenerNombreEspecie(Integer id) throws Exception {
        strSQL = "SELECT ES.nombre from " + TABLA + " RA "
                + "inner join especie ES on RA.especie_id = ES.id "
                + "where RA.especie_id = " + id;
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getString("nombre");
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener nombre de la especie: " + e.getMessage());
        }
        return "";
    }
    
    public boolean validarNombre(String nom) throws Exception {
        strSQL = "SELECT nombre FROM raza WHERE LOWER(nombre) = LOWER('" + nom + "')";
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capaNegocio;

import capaDatos.clsJDBC;

import java.sql.ResultSet;

/**
 *
 * @author Junior
 */
public class clsVacuna {
    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;

    public static final String TABLA = "VACUNA";
    public static final String ID = "id";
    public static final String NOMBRE = "nombre";
    public static final String DOSIS_X_KGPESO = "dosis_x_kgpeso";
    public static final String ESPECIE_ID = "especie_id";
    
    public ResultSet listarVacunas() throws Exception {
        strSQL = "select * from " + TABLA;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error en listar la tabla " + TABLA + " / " + e.getMessage());
        }
    }

    public Integer obtenerIdVacuna(String nom) throws Exception {
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

    public Integer generarIDVacuna() throws Exception {
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

    public ResultSet buscarVacuna(Integer id) throws Exception {
        strSQL = "select * from " + TABLA + " where " + ID + " = " + id;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar en la tabla " + TABLA + ": " + e.getMessage());
        }
    }

    public void registrarVacuna(int id, String nom, Double dosis_x_kgpeso ,int especie_id) throws Exception {
        strSQL = "insert into " + TABLA + " values (" + id + ", '" + nom + "', " + dosis_x_kgpeso + ", " + especie_id + ")";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar en la tabla " + TABLA + " -->" + e.getMessage());
        }
    }

    public void modificarVacuna(int id, String nom, double dosis_x_kgpeso, Integer especie_id) throws Exception {
        strSQL = "update " + TABLA + " set " + NOMBRE + " = '" + nom + "', " + DOSIS_X_KGPESO + " = " + dosis_x_kgpeso
                + ", " + ESPECIE_ID + " = " + especie_id + " "
                + "where " + ID + " = " + id + "";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar la tabla " + TABLA + " -->" + e.getMessage());
        }
    }

    public void eliminarVacuna(Integer id) throws Exception {
        strSQL = "delete from " + TABLA + " where " + ID + " = " + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar " + TABLA + ": " + e.getMessage());
        }
    }

    public String obtenerNombreEspecie(Integer id) throws Exception {
        strSQL = "SELECT ES.nombre from " + TABLA + " VA "
                + "inner join especie ES on VA.especie_id = ES.id "
                + "where VA.especie_id = " + id;
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
    
}

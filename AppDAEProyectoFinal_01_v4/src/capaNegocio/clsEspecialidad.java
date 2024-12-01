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
public class clsEspecialidad {
    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    public static final String TABLA = "ESPECIALIDAD";
    public static final String ID = "id";
    public static final String NOMBRE = "nom_especialidad";
    public static final String DISPONIBILIDAD = "disponibilidad";
    
    public ResultSet listarEspecialidad() throws Exception{
        strSQL = " select  * from " +TABLA+" order by id" ;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error en listar la tabla "+TABLA+" / " + e.getMessage());
        }
    }
    
    public ResultSet listarEspecialidadesVigentes() throws Exception{
        strSQL = "select * from "+TABLA+" where "+DISPONIBILIDAD+" = true";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error en listar la tabla "+TABLA+" / " + e.getMessage());
        }
    }
    
    public Integer obteneIdEspecialidad(String nom) throws Exception {
        strSQL = "Select "+ID+" from "+TABLA+" where "+NOMBRE+" = '"+nom+"' ";        
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {                
                return rs.getInt(ID);
            }
        } catch (Exception e) {
            throw new Exception("Error al buscar ID de "+TABLA+" con el nombre "+nom+" --> "+e.getMessage());
        }        
        return 0;
    }
    
    public Integer generarIDEspecialidad() throws Exception {
        strSQL = "Select COALESCE(MAX("+ID+"),0)+1 as valor from "+TABLA;
        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {                
                return rs.getInt("valor");
            }
        } catch (Exception e) {
            throw new Exception("Error al mostrar ID de la tabla "+TABLA+" --> "+e.getMessage());
        }        
        return 0;
    }
    
    public ResultSet buscarEspecialidad(Integer id) throws Exception {
        strSQL = "select * from "+TABLA+" where "+ID+" = "+id;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar en la tabla "+TABLA+": " + e.getMessage());
        }        
    }
    
    public void registrarEspecialidad (int id, String nom) throws Exception {
        strSQL = "insert into "+TABLA+" values ("+id+", '"+ nom +"', true)";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar en la tabla "+TABLA+" -->" + e.getMessage());
        }
    }
    
    public void modificarEspecialidad (int id, String nom) throws Exception {
        strSQL = "update "+TABLA+" set "+NOMBRE+" = '"+nom+"' where "+ID+" = "+ id +"";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar la tabla "+TABLA+" -->" + e.getMessage());
        }
    }
    
    public void eliminarEspecialidad(Integer id) throws Exception {
        strSQL = "delete from "+TABLA+" where "+ID+" = "+id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar "+TABLA+": " + e.getMessage());
        }        
    }
    
    public void darBaja(Integer id) throws Exception {
        strSQL = "update "+TABLA+" set "+DISPONIBILIDAD+" = false where "+ID+" = "+id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar tabla "+TABLA+": " + e.getMessage());
        }        
    }
    
    
    public void cambiarDisponibilidad(Integer id) throws Exception {
        Boolean disp = null;

        rs = objConectar.consultarBD("select "+DISPONIBILIDAD+" from "+TABLA+" where "+ID+" = '"+id+"'");

        while (rs.next()) {                
            disp = rs.getBoolean(DISPONIBILIDAD);
        }
        
        try {
            strSQL = "update "+TABLA+" set "+DISPONIBILIDAD+" = "+!disp+" where "+ID+" = '"+id+"'";
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al cambiar disponibilidad en ID:"+id+" en tabla "+TABLA+": " + e.getMessage());
        }        
    }
    
    
    
}


package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.ResultSet;

public class clsMedico {
    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    public static final String TABLA = "MEDICO";
    public static final String DNI = "doc_identidad";
    public static final String NOMBRES = "nombres";
    public static final String APE_PATERNO = "apePaterno";
    public static final String APE_MATERNO = "apeMaterno";
    public static final String SEXO = "sexo";
    public static final String DISPONIBILIDAD = "disponibilidad";
    public static final String VIGENCIA = "vigencia";
    public static final String ESPECIALIDAD_ID = "especialidad_id";
    
    public ResultSet listarMedicos() throws Exception{
        strSQL = "select "
                + " M.*, E."+clsEspecialidad.NOMBRE+" "
                + " from "+TABLA+" M "
                + " inner join "+clsEspecialidad.TABLA+" E on M."+ESPECIALIDAD_ID+" = E."+clsEspecialidad.ID
                + " order by M."+DNI
                +" ";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error en listar la tabla "+TABLA+" / " + e.getMessage());
        }
    }
        
    public ResultSet buscarMedico(String dni) throws Exception {
        strSQL = "select "
                + " M.*, E."+clsEspecialidad.NOMBRE+" "
                + " from "+TABLA+" M "
                + " inner join "+clsEspecialidad.TABLA+" E on M."+ESPECIALIDAD_ID+" = E."+clsEspecialidad.ID
                +" where "+DNI+" = '"+dni+"'";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar dni: '"+dni+"' en la tabla "+TABLA+": " + e.getMessage());
        }        
    }
    
    public void registrarMedico (
            String dni, 
            String nom,
            String apeP,
            String apeM,
            Boolean sexo,
            Integer esp
    ) throws Exception {
        strSQL = "insert into "+TABLA+" values ("+
                "'"+dni+"'" +","+
                "'"+nom+"'" +","+
                "'"+apeP+"'" +","+
                "'"+apeM+"'" +","+
                sexo +","+
                "true,"+
                "true,"+
                esp+
                ")";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar DNI: "+dni+" en la tabla "+TABLA+" -->" + e.getMessage());
        }
    }
    
    public void modificarMedico (
            String dni, 
            String nom,
            String apeP,
            String apeM,
            Boolean sexo,
            Integer esp
    ) throws Exception {
        strSQL = "update "+TABLA+" set "+
                NOMBRES+" = "+"'"+nom+"'"+","+
                APE_PATERNO+" = "+"'"+apeP+"'"+","+
                APE_MATERNO+" = "+"'"+apeM+"'"+","+
                SEXO+" = "+"'"+sexo+"'"+","+
                ESPECIALIDAD_ID+" = "+esp+
                " where "+DNI+" = '"+ dni +"'";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar DNI: "+dni+" en la tabla "+TABLA+" -->" + e.getMessage());
        }
    }
    
    public void eliminarMedico(String dni) throws Exception {
        strSQL = "delete from "+TABLA+" where "+DNI+" = '"+dni+"'";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar DNI: "+dni+" "+TABLA+": " + e.getMessage());
        }        
    }
    
    public void cambiarDisponibilidad(String dni) throws Exception {
        Boolean disp = null;
        Boolean vig = null;

        rs = objConectar.consultarBD("select "+DISPONIBILIDAD+","+VIGENCIA+" from "+TABLA+" where "+DNI+" = '"+dni+"'");

        while (rs.next()) {                
            disp = rs.getBoolean(DISPONIBILIDAD);
            vig = rs.getBoolean(VIGENCIA);
        }
        
        try {
            if (vig) {
                strSQL = "update "+TABLA+" set "+DISPONIBILIDAD+" = "+!disp+" where "+DNI+" = '"+dni+"'";
            }        
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al cambiar disponibilidad en DNI:"+dni+" en tabla "+TABLA+": " + e.getMessage());
        }        
    }
    
    public void darBaja(String dni) throws Exception {
        strSQL = "update "+TABLA+" set "+VIGENCIA+" = false, "+DISPONIBILIDAD+" = false where "+DNI+" = '"+dni+"'";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar tabla "+TABLA+": " + e.getMessage());
        }        
    }
    
     public Integer obtenerID(String nom) throws Exception {
        strSQL = "Select id from " + TABLA + " where nombres = '" + nom + "' ";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            throw new Exception("Error al buscar codigo de " + TABLA + " con el nombre " + nom + " --> " + e.getMessage());
        }
        return 0;
    }
    
    
}

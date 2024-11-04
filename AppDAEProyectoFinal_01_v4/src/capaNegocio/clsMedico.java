
package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.ResultSet;

public class clsMedico {
    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    public static final String TABLA = "MEDICO";
    public static final String ID = "id";
    public static final String DOC_IDENTIDAD = "doc_identidad";
    public static final String NOMBRES = "nombres";
    public static final String APE_PATERNO = "apePaterno";
    public static final String APE_MATERNO = "apeMaterno";
    public static final String SEXO = "sexo";
    public static final String DISPONIBILIDAD = "disponibilidad";
    public static final String VIGENCIA = "vigencia";
    public static final String ESPECIALIDAD_ID = "especialidad_id";
    public static final String CANT_SERVICIOS = "cant_servicios";
    
    public Integer generarIDMedico() throws Exception {
        strSQL = "Select COALESCE(MAX(" + ID + "),0)+1 as valor from " + TABLA;
        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getInt("valor");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar codigo de la tabla " + TABLA + " --> " + e.getMessage());
        }
        return 0;
    }
    
    public ResultSet listarMedicos() throws Exception{
        strSQL = "select "
                    + " M.*, "
                    + " E."+clsEspecialidad.NOMBRE+" "
                + " from "+TABLA+" M "
                + " inner join "+clsEspecialidad.TABLA+" E on M."+ESPECIALIDAD_ID+" = E."+clsEspecialidad.ID
                + " order by M."+ID
                +" ";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error en listar la tabla "+TABLA+" / " + e.getMessage());
        }
    }
        
    public ResultSet listarMedicosconServicios() throws Exception{
        strSQL = """
                select 
                   med.*, 
                   esp.nom_especialidad, 
                   count(det.servicio_id) as cant_servicios
                from medico med
                left join especialidad esp on esp.id = med.especialidad_id
                left join detalle_servicio det ON det.medico_id = med.id
                group by med.id, nom_especialidad
                order by med.id 
                """;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error en listar la tabla "+TABLA+": \n" + e.getMessage());
        }
    }
        
    public ResultSet buscarMedico(int id) throws Exception {
        strSQL = "select " +
                "    med.*," +
                "    esp.nom_especialidad, " +
                "    count(det.servicio_id) as cant_servicios" +
                " from medico med " +
                " left join especialidad esp on esp.id = med.especialidad_id " +
                " left join detalle_servicio det ON det.medico_id = med.id " +
                " where med."+ID+" = " + id +
                " group by med.id,nom_especialidad " +
                " order by med.id "
                + " ";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar id: '"+id+"' en la tabla "+TABLA+": " + e.getMessage());
        }        
    }
    
    public ResultSet buscarMedico_1(String dni) throws Exception {
        strSQL = "select "
                + " M.*, E."+clsEspecialidad.NOMBRE+" "
                + " from "+TABLA+" M "
                + " inner join "+clsEspecialidad.TABLA+" E on M."+ESPECIALIDAD_ID+" = E."+clsEspecialidad.ID
                +" where "+DOC_IDENTIDAD+" = '"+dni+"'";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar dni: '"+dni+"' en la tabla "+TABLA+": " + e.getMessage());
        }        
    }
        
    
    public void registrarMedico (
            Integer id,
            String nom,
            String apeP,
            String apeM,
            String dni, 
            Boolean sexo,
            Integer esp
    ) throws Exception {
        strSQL = "insert into "+TABLA+" values ("+
                id+","+
                "'"+nom+"'" +","+
                "'"+apeP+"'" +","+
                "'"+apeM+"'" +","+
                "'"+dni+"'" +","+
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
            Integer id,
            String nom,
            String apeP,
            String apeM,
            String dni, 
            Boolean sexo,
            Integer esp
    ) throws Exception {
        strSQL = "update "+TABLA+" set "+
                NOMBRES+" = "+"'"+nom+"'"+","+
                APE_PATERNO+" = "+"'"+apeP+"'"+","+
                APE_MATERNO+" = "+"'"+apeM+"'"+","+
                DOC_IDENTIDAD+" = "+"'"+dni+"'"+","+
                SEXO+" = "+"'"+sexo+"'"+","+
                ESPECIALIDAD_ID+" = "+esp+
                " where id = '"+ id +"'";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar DNI: "+dni+" en la tabla "+TABLA+" -->" + e.getMessage());
        }
    }
    
    
    public void eliminarMedico(int id) throws Exception {
        strSQL = "delete from "+TABLA+" where id = '"+id+"'";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar ID: "+id+" en tabla "+TABLA+": " + e.getMessage());
        }        
    }
    
    
    public void cambiarDisponibilidad(Integer id) throws Exception {
        Boolean disp = null;
        Boolean vig = null;

        rs = objConectar.consultarBD("select "+DISPONIBILIDAD+","+VIGENCIA+" from "+TABLA+" where "+ID+" = '"+id+"'");

        while (rs.next()) {                
            disp = rs.getBoolean(DISPONIBILIDAD);
            vig = rs.getBoolean(VIGENCIA);
        }
        
        try {
            if (vig) {
                strSQL = "update "+TABLA+" set "+DISPONIBILIDAD+" = "+!disp+" where "+ID+" = '"+id+"'";
            }        
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al cambiar disponibilidad en ID:"+id+" en tabla "+TABLA+": " + e.getMessage());
        }        
    }
    
    
    public void darBaja(Integer id) throws Exception {
        strSQL = "update "+TABLA+" set "+VIGENCIA+" = false, "+DISPONIBILIDAD+" = false where "+ID+" = '"+id+"'";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar tabla "+TABLA+": " + e.getMessage());
        }        
    }
    
    
}

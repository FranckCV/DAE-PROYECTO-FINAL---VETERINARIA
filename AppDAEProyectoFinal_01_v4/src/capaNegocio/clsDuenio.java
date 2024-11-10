package capaNegocio;

import capaDatos.clsJDBC;
import com.sun.jdi.connect.spi.Connection;
import java.beans.Statement;
import java.sql.ResultSet;

/**
 *
 * @author Grupo_Veterinaria
 */

public class clsDuenio {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    Connection con = null;
    Statement sent;

//listarDueños
    public ResultSet listarDuenios() throws Exception {
        strSQL = "SELECT * FROM duenio d INNER JOIN tipo_cliente t ON d.codtipo = t.codtipo";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar Dueño");
        }
    }
    
//listarTipoClientes    
    public ResultSet listarTipoClientes() throws Exception{
        strSQL=" select * from tipo_cliente";
        try{
            rs=objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e){
            throw new Exception("Error al listar tipo documento");
        }
    }
    
    public Integer obtenerCodigoTipoCliente(String nom) throws Exception{
        strSQL=" select codtipo from tipo_cliente where nombre= '" + nom + "' ";
        try{
            rs=objConectar.consultarBD(strSQL);
            if(rs.next()) return rs.getInt("codtipo");
            
        } catch (Exception e ){
            throw new Exception("Error al buscar tipo cliente");
        }
    return 0;          
    }
    

  public void registrarDuenio(String numDoc, String nom, String app, String apm,
                           String tel, String telAlt, String correo, String direc, 
                           boolean sexo, boolean vig, Integer codTipo) throws Exception {
    // Construcción de la consulta de inserción
    strSQL = "INSERT INTO duenio (numdoc, nombres, apepaterno, apematerno, " +
             "telefono, telefonoalt, correo, direccion, sexo, vigencia, codtipo) " +
             "VALUES ('" + numDoc + "', '" + nom + "', '" + app + "', '" + apm + "', '" +
             tel + "', '" + telAlt + "', '" + correo + "', '" + direc + "', " + sexo + ", " +
             vig + ", " + codTipo + ")";
    try {
        objConectar.ejecutarBD(strSQL);
    } catch (Exception e) {
        throw new Exception("Error al registrar dueño: " + e.getMessage());
    }
}


public void modificarDuenio(String numDoc, String nom, String app, String apm,
                           String tel, String telAlt, String correo, String direc, 
                           boolean sexo, boolean vig, Integer codTipo) throws Exception {
    strSQL = "UPDATE duenio SET " +
             "nombres = '" + nom + "', " +
             "apepaterno = '" + app + "', " +
             "apematerno = '" + apm + "', " +
             "sexo = " + sexo + ", " +
             "telefono = '" + tel + "', " +
             "telefonoalt = '" + telAlt + "', " +
             "correo = '" + correo + "', " +
             "direccion = '" + direc + "', " + 
             "vigencia = " + vig + " ," + 
             "codtipo = " + codTipo + " " + 
             "WHERE numdoc = '" + numDoc + "'"; 

    try {
        objConectar.ejecutarBD(strSQL);
    } catch (Exception e) {
        throw new Exception("Error al modificar numdoc: " + numDoc + " en la tabla Dueño --> " + e.getMessage());
    }
}

//buscar

    public ResultSet buscarDuenio(String numDoc) throws Exception {
        strSQL = "SELECT * FROM duenio where doc_identidad='" + numDoc + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar Dueño");
        }
    }

}

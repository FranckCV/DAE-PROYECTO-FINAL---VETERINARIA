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
        strSQL = "SELECT * FROM DUEniO order by 1";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar Dueño");
        }
    }


  public void registrarDuenio(int id,String numDoc, String nom, String app, String apm,
                           String tel, String telAlt, String correo, String direc, 
                           boolean sexo, boolean vig) throws Exception {
    // Construcción de la consulta de inserción
    strSQL = "INSERT INTO DUEniO (id, doc_identidad, nombres, apePaterno, apeMaterno, " +
             "telefono, telefonoAlt, correo, direccion, sexo, vigencia) " +
             "VALUES (" + id + ", '"+ numDoc + "', '" + nom + "', '" + app + "', '" + apm + "', '" +
             tel + "', '" + telAlt + "', '" + correo + "', '" + direc + "', " + sexo + ", " +
             vig + ")";
    try {
        objConectar.ejecutarBD(strSQL);
    } catch (Exception e) {
        throw new Exception("Error al registrar dueño: " + e.getMessage());
    }
}
    public Integer generarCodigoDuenio() throws Exception {
        strSQL = "Select COALESCE(Max(id), 0)+1 as codigo from DUEnio";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar el código del duenio --> " + e.getMessage());
        }
        return 0;
    }

public void modificarDuenio(int id, String doc, String nom, String app, String apm,
                           String tel, String telAlt, String correo, String direc, 
                           boolean sexo, boolean vig) throws Exception {
    strSQL = "UPDATE duenio SET " +
             "nombres = '" + nom + "', " +
             "apepaterno = '" + app + "', " +
             "apematerno = '" + apm + "', " +
             "sexo = " + sexo + ", " +
             "doc_identidad = ' " + doc + "' , "+
             "telefono = '" + tel + "', " +
             "telefonoalt = '" + telAlt + "', " +
             " correo = '" + correo + "', " +
             " direccion = '" + direc + "', " + 
             " vigencia = " + vig  + 
             " where id = " + id  ; 

    try {
        objConectar.ejecutarBD(strSQL);
    } catch (Exception e) {
        throw new Exception("Error al modificar id: " + id + " en la tabla Dueño --> " + e.getMessage());
    }
}

//buscar

    public ResultSet buscarDuenio(String id) throws Exception {
        strSQL = "SELECT * FROM duenio where id=" + id ;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar Dueño");
        }
    }
    public ResultSet buscarDuenioN(String numDoc) throws Exception {
            strSQL = "SELECT * FROM duenio where doc_identidad= '" + numDoc + "' ;";
            try {
                rs = objConectar.consultarBD(strSQL);
                return rs;
            } catch (Exception e) {
                throw new Exception("Error al buscar Dueño por número de documento");
            }
        }
}

package capaNegocio;

import capaDatos.clsJDBC;
import static capaNegocio.clsMedico.DISPONIBILIDAD;
import static capaNegocio.clsMedico.ID;
import static capaNegocio.clsMedico.TABLA;
import static capaNegocio.clsMedico.VIGENCIA;
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

    public static final String DISPONIBILIDAD = "disponibilidad";
    public static final String VIGENCIA = "vigencia";
    public static final String TABLA = "DUENIO";

    
     public ResultSet buscarDuenio(Integer codDueño) throws Exception {
        strSQL = "Select * from duenio where id=" + codDueño;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar el dueño --> " + e.getMessage());
        }
    }
    
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

    public void darBaja(Integer id) throws Exception {
        strSQL = "update " + TABLA + " set " + VIGENCIA + " = false, " + DISPONIBILIDAD + " = false where " + ID + " = '" + id + "'";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar tabla " + TABLA + ": " + e.getMessage());
        }
    }

    public void eliminarDuenio(int id) throws Exception {
        strSQL = "delete from duenio where id = '" + id + "'";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar ID: " + id + " en tabla dueño: " + e.getMessage());
        }
    }

<<<<<<< HEAD


=======
  
>>>>>>> 9cf6d0e97b3469cc3189e9d0bf5b4d0f33f55630
    public void registrarDuenio(int id, String numDoc, String nom, String app, String apm,
            String tel, String telAlt, String correo, String direc,
            boolean sexo, boolean vig) throws Exception {
        // Construcción de la consulta de inserción
        strSQL = "INSERT INTO DUEniO (id, doc_identidad, nombres, apePaterno, apeMaterno, "
                + "telefono, telefonoAlt, correo, direccion, sexo, vigencia) "
                + "VALUES (" + id + ", '" + numDoc + "', '" + nom + "', '" + app + "', '" + apm + "', '"
                + tel + "', '" + telAlt + "', '" + correo + "', '" + direc + "', " + sexo + ", "
                + vig + ")";
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
        strSQL = "UPDATE duenio SET "
                + "nombres = '" + nom + "', "
                + "apepaterno = '" + app + "', "
                + "apematerno = '" + apm + "', "
                + "sexo = " + sexo + ", "
                + "doc_identidad = ' " + doc + "' , "
                + "telefono = '" + tel + "', "
                + "telefonoalt = '" + telAlt + "', "
                + " correo = '" + correo + "', "
                + " direccion = '" + direc + "', "
                + " vigencia = " + vig
                + " where id = " + id;

        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar id: " + id + " en la tabla Dueño --> " + e.getMessage());
        }
    }

    public void darAlta(int codDueño) throws Exception {
        strSQL = "UPDATE duenio SET estado = true WHERE codUsuario = " + codDueño;

        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar la tabla usuario: " + e.getMessage());
        }
    }
    
<<<<<<< HEAD

=======
  
>>>>>>> 9cf6d0e97b3469cc3189e9d0bf5b4d0f33f55630
    public ResultSet buscarDuenio(String id) throws Exception {
        strSQL = "SELECT * FROM duenio where id=" + id;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar Dueño por número de documento");
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

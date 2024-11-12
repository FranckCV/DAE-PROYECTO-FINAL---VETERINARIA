/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.*;

/**
 *
 * @author franc
 */
public class clsUsuario {
    
    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;

    public String login(String usu, String cont) throws Exception {
        strSQL = "select * from usuario where nomusuario = ? "
                + "and clave = md5(? || ? || 'CODE146')";
        try {
            Connection micon = null;
            objConectar.conectar();
            micon = objConectar.getCon();
            PreparedStatement sp = micon.prepareStatement(strSQL);
            sp.setString(1, usu);  
            sp.setString(2, cont); 
            sp.setString(3, usu);  
            rs = sp.executeQuery();
            while (rs.next()) {
                return rs.getString("nombres");
            }
        } catch (Exception e) {
            throw new Exception("Error al iniciar sesión");
        }
        return "";
    }

    public String obtenerUsuario(String usu) throws Exception {
        strSQL = "SELECT CONCAT(nombres, ' ', appaterno) AS nombre_completo FROM usuario WHERE nomusuario = '" + usu + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getString("nombre_completo");
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener user");
        }
        return "";
    }

    public String obtenerCargo(String usu) throws Exception {
        strSQL = "select cargo from usuario where nomusuario='" + usu + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getString("cargo");
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener cargo de usuario");
        }
        return "";
    }

    public ResultSet listarUsuarios() throws Exception {
        strSQL = "select * from usuario";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar los usuarios --> " + e.getMessage());
        }
    }

    // Método para generar un nuevo código de usuario
//    public Integer generarCodigoUsuario() throws Exception {
//        strSQL = "Select COALESCE(Max(codUsuario), 0) + 1 as codigo from usuario";
//        try {
//            rs = objConectar.consultarBD(strSQL);
//            if (rs.next()) {
//                return rs.getInt("codigo");
//            }
//        } catch (Exception e) {
//            throw new Exception("Error al generar el código del usuario --> " + e.getMessage());
//        }
//        return 0;
//    }
    // Método para registrar un nuevo usuario
    public void registrarUsuario(int codUsuario, String nomUsuario, boolean estado, boolean sexo, String clave,
            String nombres, String apPaterno, String apMaterno, String cargo) throws Exception {
        strSQL = "Insert into usuario Values(" + codUsuario + ",'" + nomUsuario + "'," + estado + "," + sexo + ",'" + clave + "','"
                + nombres + "','" + apPaterno + "','" + apMaterno + "','" + cargo + "')";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar el usuario --> " + e.getMessage());
        }
    }

    // Método para buscar un usuario por su código
    public ResultSet buscarUsuario(Integer codUsuario) throws Exception {
        strSQL = "Select * from usuario where codUsuario=" + codUsuario;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar el usuario --> " + e.getMessage());
        }
    }

    // Método para eliminar un usuario
    public void eliminarUsuario(Integer codUsuario) throws Exception {
        strSQL = "Delete from usuario where codUsuario=" + codUsuario;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar el usuario --> " + e.getMessage());
        }
    }

    // Método para modificar los datos de un usuario
    public void modificarUsuario(Integer codUsuario, String nomUsuario, boolean estado, boolean sexo, String clave,
            String nombres, String apPaterno, String apMaterno, String cargo) throws Exception {
        strSQL = "Update usuario set nomUsuario='" + nomUsuario + "', estado=" + estado + ", sexo=" + sexo
                + ", clave='" + clave + "', nombres='" + nombres + "', apPaterno='" + apPaterno + "', apMaterno='" + apMaterno
                + "', cargo='" + cargo + "' where codUsuario=" + codUsuario;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar el usuario --> " + e.getMessage());
        }
    }

    public void darBaja(int codUsuario) throws Exception {
        // Modificar la consulta para la tabla usuario
        strSQL = "UPDATE usuario SET estado = false WHERE codUsuario = " + codUsuario;

        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar la tabla usuario: " + e.getMessage());
        }
    }

    public Integer generarCodigoUsuario() throws Exception {
        strSQL = "Select COALESCE(Max(codUsuario), 0) + 1 as codigo from usuario";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar el código del usuario --> " + e.getMessage());
        }
        return 0;
    }

}

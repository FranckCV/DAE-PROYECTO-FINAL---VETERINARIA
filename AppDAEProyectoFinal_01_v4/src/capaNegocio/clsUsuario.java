/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

    public String obtenerNombreUsuario(String usu, String cont) throws Exception {
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
                return rs.getString("nomusuario");
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
        String strSQL = "INSERT INTO usuario (codUsuario, nomusuario, estado, sexo, clave, nombres, apPaterno, apMaterno, cargo) "
                + "VALUES (?, ?, ?, ?, md5(? || ? || 'CODE146'), ?, ?, ?, ?)";
        try {
            Connection micon = null;
            objConectar.conectar();
            micon = objConectar.getCon();
            PreparedStatement sp = micon.prepareStatement(strSQL);

            sp.setInt(1, codUsuario);
            sp.setString(2, nomUsuario);
            sp.setBoolean(3, estado);
            sp.setBoolean(4, sexo);
            sp.setString(5, clave);
            sp.setString(6, nomUsuario);
            sp.setString(7, nombres);
            sp.setString(8, apPaterno);
            sp.setString(9, apMaterno);
            sp.setString(10, cargo);

            sp.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Error al registrar el usuario --> " + e.getMessage());
        }
    }

    // Método para buscar un usuario por su código
    public ResultSet buscarUsuario(Integer codUsuario) throws Exception {
        strSQL = "select * from (select* from usuario where cargo != 'A' or codUsuario = 8) where codusuario = " + codUsuario;
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
        strSQL = "UPDATE usuario SET estado = false WHERE codUsuario = " + codUsuario;

        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar la tabla usuario: " + e.getMessage());
        }
    }

    public void darAlta(int codUsuario) throws Exception {
        strSQL = "UPDATE usuario SET estado = true WHERE codUsuario = " + codUsuario;

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

    public boolean validarUsuario(String usu) throws Exception {
        strSQL = "select estado from usuario where nomusuario='" + usu + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getBoolean("estado");
            }
        } catch (Exception e) {
            throw new Exception("Error al validar usuario");
        }
        return false;
    }

    public void modificarContraseña(Integer cod, String usu, String clave) throws Exception {
        strSQL = "update usuario set clave= md5(? || ? || 'CODE146') where codusuario="+cod;
        try {
            Connection micon = null;
            objConectar.conectar();
            micon = objConectar.getCon();
            PreparedStatement sp = micon.prepareStatement(strSQL);


            sp.setString(1, clave);
            sp.setString(2, usu);
            sp.setInt(3,cod);


            sp.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Error al modificar contraseña");
        }
    }
    
}

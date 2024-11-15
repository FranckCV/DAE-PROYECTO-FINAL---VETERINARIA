/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capaDatos;

import java.sql.*;

/**
 *
 * @author Grupo_Veterinaria
 */
public class clsJDBC {

    private String driver, url, user, password;
    private Connection con;
    private Statement sent = null;

    public clsJDBC() {
        this.driver = "org.postgresql.Driver";
        this.url = "jdbc:postgresql://localhost:5432/bd_veterinaria";
        this.user = "postgres";
//        this.password = "tirsarios123";
//        this.password = "1234567890";
//       this.password = "Leocix06015";
        this.password = "USAT2023";
//        this.password= "1234";
        this.con = null;
    }

    public void conectar() throws Exception {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new Exception("Error al conectarse con la BD " + ex.getMessage());
        }
    }

    public void desconectar() throws Exception {
        try {
            con.close();
        } catch (SQLException ex) {
            throw new Exception("Error al desconectar la BD!");
        }
    }

    public ResultSet consultarBD(String strSQL) throws Exception {
        ResultSet rs = null;
        try {
            conectar();
            sent = con.createStatement();
            rs = sent.executeQuery(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error en la consulta" + e.getMessage());
        } finally {
            if (con != null) {
                desconectar();
            }
        }
    }

    public void ejecutarBD(String strSQL) throws Exception {
        try {
            conectar();
            sent = con.createStatement();
            sent.executeUpdate(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al ejecutar Update ---> " + e.getMessage());
        } finally {
            if (con != null) {
                desconectar();
            }
        }
    }

    // Nuevo método para consultas con parámetros usando PreparedStatement
    public ResultSet consultarBDConParametros(String strSQL, Object[] parametros) throws Exception {
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            conectar();
            ps = con.prepareStatement(strSQL);

            // Asignar los parámetros al PreparedStatement
            for (int i = 0; i < parametros.length; i++) {
                ps.setObject(i + 1, parametros[i]);
            }

            rs = ps.executeQuery();
            return rs; // Dejar la conexión abierta para procesar el ResultSet
        } catch (Exception e) {
            throw new Exception("Error en la consulta con parámetros: " + e.getMessage());
        }
    }

    // Método para ejecutar una actualización (INSERT, UPDATE, DELETE) con PreparedStatement
    public int ejecutarActualizacion(String strSQL, Object[] parametros) throws Exception {
        PreparedStatement ps = null;
        try {
            conectar();
            ps = con.prepareStatement(strSQL);

            // Asignar los parámetros al PreparedStatement
            for (int i = 0; i < parametros.length; i++) {
                ps.setObject(i + 1, parametros[i]);
            }

            return ps.executeUpdate(); // Retorna la cantidad de filas afectadas
        } catch (Exception e) {
            throw new Exception("Error en la actualización: " + e.getMessage());
        } finally {
            desconectar();
        }
    }

    public String obtenerNombresColumnas(String tabla) throws Exception {
        ResultSet rs = null;
        try {
            conectar();
            sent = con.createStatement();
            // Consultar la tabla para obtener una fila de datos
            rs = sent.executeQuery("SELECT * FROM " + tabla + " LIMIT 1");

            // Obtener los metadatos del ResultSet
            ResultSetMetaData rsmd = rs.getMetaData();
            int numeroColumnas = rsmd.getColumnCount();
//            System.out.println("Nombres de columnas de la tabla '" + tabla + "':");
            for (int i = 1; i <= numeroColumnas; i++) {
                // Imprimir el nombre de cada columna
                return rsmd.getColumnName(i);
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener los nombres de las columnas: " + e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            desconectar();
        }
        return "";
    }
    
    public Connection getCon() {
        return this.con;
    }

}

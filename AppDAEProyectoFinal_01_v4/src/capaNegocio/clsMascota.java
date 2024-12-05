/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author franc
 */
public class clsMascota {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;

    // Método para listar todas las mascotas
    public ResultSet listarMascotas() throws Exception {
        strSQL = "SELECT ma.*, ra.nombre AS raza_nombre, es.nombre AS especie_nombre FROM MASCOTA ma INNER JOIN raza ra ON ra.id = ma.raza_id INNER JOIN especie es ON es.id = ra.especie_id order by 1";
        try {
            return objConectar.consultarBD(strSQL);

        } catch (Exception e) {
            throw new Exception("Error al listar Mascotas: " + e.getMessage());
        }
    }

    // Método para registrar una mascota
    public void registrarMascota(int id, String nombre, Date fechaN, double altura, double peso,
            String notaAdicional, boolean sexo, boolean esterilizado,
            boolean desparasitado, String condicion, boolean estado, int razaId
    ) throws Exception {

        String strSQL = "INSERT INTO MASCOTA (id, nombre, fecha_nacimiento, altura, peso, notaAdicional, sexo, esterilizado, desparasitado, estado_salud,vigencia,raza_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Parámetros para el PreparedStatement
        Object[] parametros = {
            id, nombre, new java.sql.Date(fechaN.getTime()), altura, peso, notaAdicional,
            sexo, esterilizado, desparasitado, condicion, estado, razaId
        };

        objConectar.ejecutarActualizacion(strSQL, parametros);
    }

    public void modificarMascota(int id, String nombre, Date fechaN, double altura, double peso,
            String notaAdicional, boolean sexo, boolean castrado,
            boolean desparasitado, String condicion, int razaId, boolean estado
    ) throws Exception {

        // Consulta SQL con concatenación directa
        strSQL = "UPDATE MASCOTA SET nombre = '" + nombre + "', "
                + "fecha_nacimiento = '" + new java.sql.Date(fechaN.getTime()) + "', "
                + "altura = " + altura + ", "
                + "peso = " + peso + ", "
                + "notaAdicional = '" + notaAdicional + "', "
                + "sexo = " + sexo + ", "
                + "esterilizado = " + castrado + ", "
                + "desparasitado = " + desparasitado + ", "
                + "estado_salud = '" + condicion + "', "
                + "raza_id = " + razaId + ", "
                + "vigencia = " + estado + " "
                + "WHERE id = " + id;
        objConectar.ejecutarBD(strSQL);
    }

    // Método para generar un nuevo código de mascota
    public Integer generarCodigoMascota() throws Exception {
        strSQL = "SELECT COALESCE(MAX(id), 0) + 1 AS id FROM MASCOTA";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar código de la mascota: " + e.getMessage());
        }
        return 0;
    }

    // Método para buscar una mascota por su ID
    public ResultSet buscarMascota(int id) throws Exception {
        strSQL = "SELECT ma.*, ra.nombre AS raza_nombre, es.nombre AS especie_nombre "
                + "FROM MASCOTA ma "
                + "INNER JOIN raza ra ON ra.id = ma.raza_id "
                + "INNER JOIN especie es ON es.id = ra.especie_id "
                + "WHERE ma.id =" + id;
        try {
            return objConectar.consultarBD(strSQL);

        } catch (Exception e) {
            throw new Exception("Error al buscar Mascotas: " + e.getMessage());
        }
    }

    public ResultSet buscarMascota(String nom) throws Exception {
        strSQL = "SELECT ma.*, ra.nombre AS raza_nombre, es.nombre AS especie_nombre "
                + "FROM MASCOTA ma "
                + "INNER JOIN raza ra ON ra.id = ma.raza_id "
                + "INNER JOIN especie es ON es.id = ra.especie_id "
                + "WHERE ma.nombre = '" + nom + "';";

        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar mascota" + e.getMessage());
        }
    }

    // Método para listar todas las razas
    public ResultSet listarRaza() throws Exception {
        strSQL = "SELECT * FROM raza ";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar las razas: " + e.getMessage());
        }
    }

    public ResultSet filtrar(String nom) throws Exception {
        strSQL = "SELECT ma.*, ra.nombre AS raza_nombre, es.nombre AS especie_nombre "
                + "FROM MASCOTA ma "
                + "INNER JOIN raza ra ON ra.id = ma.raza_id "
                + "INNER JOIN especie es ON es.id = ra.especie_id "
                + "WHERE UPPER(ma.nombre) LIKE UPPER ('%" + nom + "%') ";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al filtrar mascotas por nombre" + e.getMessage());
        }
    }

    public ResultSet filtrarID(int id) throws Exception {
        strSQL = "SELECT ma.*, ra.nombre AS raza_nombre, es.nombre AS especie_nombre "
                + "FROM MASCOTA ma "
                + "INNER JOIN raza ra ON ra.id = ma.raza_id "
                + "INNER JOIN especie es ON es.id = ra.especie_id "
                + "WHERE ma.id =" + id;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al filtrar mascotas por Código" + e.getMessage());
        }
    }

    // Método para obtener el código de una raza a partir de su nombre
    public Integer obtenerCodigoRaza(String nombreRaza) throws Exception {
        strSQL = "SELECT id FROM raza WHERE nombre = ?";
        try {
            ResultSet rs = objConectar.consultarBDConParametros(strSQL, new Object[]{nombreRaza});
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener el código de la raza: " + e.getMessage());
        }
        return 0;
    }

    public Integer calcularEdadMascota(Integer idMascota) throws Exception {
        strSQL = "SELECT EXTRACT(YEAR FROM AGE(CURRENT_DATE, fecha_nacimiento)) AS edad FROM mascota WHERE id = " + idMascota;

        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("edad");
            } else {
                throw new Exception("No se encontró la mascota con el ID proporcionado.");
            }
        } catch (Exception e) {
            throw new Exception("Error al calcular la edad de la mascota --> " + e.getLocalizedMessage());
        }
    }

    public ResultSet filtrarMascotaPorDuenioYNombre(Integer duenioId, String nombreMascota) throws Exception {
        // Base de la consulta
        strSQL = "SELECT m.*, r.nombre FROM MASCOTA m "
                + "INNER JOIN CUSTODIA c ON m.id = c.MASCOTAid "
                + "INNER JOIN DUEniO d ON c.DUEniOid = d.id "
                + "INNER JOIN RAZA r ON m.raza_id = r.id "
                + "WHERE m.vigencia = true AND d.id = " + duenioId;

        // Agregar filtro por nombre si se proporciona
        if (nombreMascota != null && !nombreMascota.isEmpty()) {
            strSQL += " AND UPPER(m.nombre) LIKE UPPER('%" + nombreMascota + "%')";
        }

        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al filtrar mascotas por dueño y nombre --> " + e.getLocalizedMessage());
        }
    }

    public String calcularEdadMascota(int idMascota) throws Exception {
        String strSQL = "SELECT EXTRACT(YEAR FROM AGE(CURRENT_DATE, fecha_nacimiento)) AS años, "
                + "EXTRACT(MONTH FROM AGE(CURRENT_DATE, fecha_nacimiento)) AS meses, "
                + "EXTRACT(DAY FROM AGE(CURRENT_DATE, fecha_nacimiento)) AS días "
                + "FROM mascota WHERE id = " + idMascota;
        ResultSet rs = null;
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                int años = rs.getInt("años");
                int meses = rs.getInt("meses");
                int días = rs.getInt("días");
                return String.format("%d años, %d meses, %d días", años, meses, días);
            } else {
                throw new Exception("No se encontró la mascota con el ID proporcionado.");
            }
        } catch (Exception e) {
            throw new Exception("Error al calcular la edad de la mascota --> " + e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
        }

    }

    public ResultSet listarMascotasxDuenio(String doc_ident) throws Exception {
        strSQL = " select "
                + " ma.*, ra.nombre AS raza_nombre, es.nombre AS especie_nombre "
                + " from custodia cu "
                + " inner join duenio du on du.id = cu.duenioid "
                + " inner join mascota ma on ma.id = cu.mascotaid "
                + " inner join raza ra on ra.id = ma.raza_id "
                + " inner join especie es on es.id = ra.especie_id "
                + " where du.doc_identidad = '" + doc_ident + "'";
        try {
            return objConectar.consultarBD(strSQL);

        } catch (Exception e) {
            throw new Exception("Error al listar Mascotas: " + e.getMessage());
        }
    }

    public void darBaja(Integer id) throws Exception {
        strSQL = "UPDATE MASCOTA  SET vigencia = false WHERE id = " + id;

        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar la tabla mascota: " + e.getMessage());
        }
    }

    public void darAlta(int codmascota) throws Exception {
        strSQL = "UPDATE MASCOTA SET vigencia = true WHERE id= " + codmascota;

        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar la tabla duenio: " + e.getMessage());
        }
    }

    public void eliminarMascota(Integer id) throws Exception {
        strSQL = "delete from MASCOTA where id = " + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar la mascota: " + e.getMessage());
        }
    }

    public String calcularEdadMascota2(int idMascota) throws Exception {
        strSQL = "SELECT EXTRACT(YEAR FROM AGE(CURRENT_DATE, fecha_nacimiento)) AS años, "
                + "EXTRACT(MONTH FROM AGE(CURRENT_DATE, fecha_nacimiento)) AS meses "
                + "FROM mascota WHERE id = " + idMascota;
        rs = null;
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                int anios = rs.getInt("años");
                int meses = rs.getInt("meses");
                if (anios!=0) {
                    return String.format("%d años", anios);
                } else {
                    return String.format("%d meses", meses);
                }
                
            } else {
                throw new Exception("No se encontró la mascota con el ID proporcionado.");
            }
        } catch (Exception e) {
            throw new Exception("Error al calcular la edad de la mascota --> " + e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

    public ResultSet filtrarMascotaPorDuenioYNombre2(Integer duenioId, String nombreMascota) throws Exception {
        // Base de la consulta
        strSQL = "SELECT m.*, r.nombre as nombre_raza FROM MASCOTA m "
                + "INNER JOIN CUSTODIA c ON m.id = c.MASCOTAid "
                + "INNER JOIN DUEniO d ON c.DUEniOid = d.id "
                + "INNER JOIN RAZA r ON m.raza_id = r.id "
                + "WHERE m.vigencia = true AND d.id = " + duenioId;

        // Agregar filtro por nombre si se proporciona
        if (nombreMascota != null && !nombreMascota.isEmpty()) {
            strSQL += " AND UPPER(m.nombre) LIKE UPPER('%" + nombreMascota + "%')";
        }

        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al filtrar mascotas por dueño y nombre --> " + e.getLocalizedMessage());
        }
    }

}

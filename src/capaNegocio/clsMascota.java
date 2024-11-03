package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.sql.PreparedStatement;

public class clsMascota {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;

    // Método para listar todas las mascotas
    public ResultSet listarMascotas() throws Exception {
        strSQL = "SELECT ma.*, ra.nombre AS raza_nombre, es.nombre AS especie_nombre FROM MASCOTA ma INNER JOIN raza ra ON ra.id = ma.raza_id INNER JOIN especie es ON es.id = ra.especie_id";
        try {
            return objConectar.consultarBD(strSQL);

        } catch (Exception e) {
            throw new Exception("Error al listar Mascotas: " + e.getMessage());
        }
    }

    // Método para registrar una mascota
    public void registrarMascota(int id, String nombre, Date fechaN, double altura, double peso,
            String notaAdicional, boolean sexo, boolean esterilizado,
            boolean desparasitado, String condicion, int razaId, boolean estado,
            Date fechaR, int especieId) throws Exception {

        String strSQL = "INSERT INTO MASCOTA (id, nombre, fecha_nacimiento, altura, peso, notaAdicional, sexo, esterilizado, desparasitado, condicion, raza_id, estado, fecha_registro, especie_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Parámetros para el PreparedStatement
        Object[] parametros = {
            id, nombre, new java.sql.Date(fechaN.getTime()), altura, peso, notaAdicional,
            sexo, esterilizado, desparasitado, condicion, razaId, estado,
            new java.sql.Date(fechaR.getTime()), especieId
        };

        objConectar.ejecutarActualizacion(strSQL, parametros);
    }

    public void modificarMascota(int id, String nombre, Date fechaN, double altura, double peso,
            String notaAdicional, boolean sexo, boolean castrado,
            boolean desparasitado, String condicion, int razaId, boolean estado,
            Date fechaR, int especieId) throws Exception {

        strSQL = "UPDATE MASCOTA SET nombre = ?, fecha_nacimiento = ?, altura = ?, peso = ?, notaAdicional = ?, "
                + "sexo = ?, esterilizado = ?, desparasitado = ?, condicion = ?, raza_id = ?, estado = ?, "
                + "fecha_registro = ?, especie_id = ? WHERE id = ?";

        // Parámetros para el PreparedStatement
        Object[] parametros = {
            nombre, new java.sql.Date(fechaN.getTime()), altura, peso, notaAdicional,
            sexo, castrado, desparasitado, condicion, razaId, estado,
            new java.sql.Date(fechaR.getTime()), especieId, id
        };

        objConectar.ejecutarActualizacion(strSQL, parametros);
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
                + "WHERE ma.id = ?";

        return objConectar.consultarBDConParametros(strSQL, new Object[]{id});
    }

    // Método para listar todas las razas
    public ResultSet listarRaza(Integer cod) throws Exception {
        strSQL = "SELECT * FROM raza WHERE especie_id = " + cod;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar las razas: " + e.getMessage());
        }
    }

    public ResultSet listarEspecie() throws Exception {
        strSQL = "SELECT especie.id, especie.nombre AS nombre_especie "
                + "FROM especie";  // Solo necesitas seleccionar de la tabla especie para listar las especies
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar las especies: " + e.getMessage());
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

    public Integer obtenerCodigoEspecie(String nombreEsp) throws Exception {
        String strSQL = "SELECT id FROM especie WHERE nombre = ?";
        try {
            ResultSet rs = objConectar.consultarBDConParametros(strSQL, new Object[]{nombreEsp});
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener el código de la especie: " + e.getMessage());
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

}

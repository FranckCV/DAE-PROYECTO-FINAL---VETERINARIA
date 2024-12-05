/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author franc
 */
public class clsMedico {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    public static final String TABLA = "MEDICO";
    public static final String DNI = "doc_identidad";
    public static final String ID = "id";
    public static final String DOC_IDENTIDAD = "doc_identidad";
    public static final String NOMBRES = "nombres";
    public static final String APE_PATERNO = "apePaterno";
    public static final String APE_MATERNO = "apeMaterno";
    public static final String SEXO = "sexo";
    public static final String DISPONIBILIDAD = "disponibilidad";
    public static final String VIGENCIA = "vigencia";
    public static final String ESPECIALIDAD_ID = "especialidad_id";
    public static final String CODIGO_USUARIO = "usuariocodusuario";
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

    public ResultSet listarMedicos() throws Exception {
        strSQL = "select "
                + " M.*, "
                + " E." + clsEspecialidad.NOMBRE + " "
                + " from " + TABLA + " M "
                + " inner join " + clsEspecialidad.TABLA + " E on M." + ESPECIALIDAD_ID + " = E." + clsEspecialidad.ID
                + " order by M." + ID
                + " ";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error en listar la tabla " + TABLA + " / " + e.getMessage());
        }
    }

    public ResultSet listarMedicoUsuario() throws Exception {
        strSQL = "select "
                + " M.*, "
                + " U.nomusuario ,"
                + " E." + clsEspecialidad.NOMBRE + " "
                + " from " + TABLA + " M "
                + " inner join usuario U on U.codusuario = M.usuariocodusuario "
                + " inner join " + clsEspecialidad.TABLA + " E on M." + ESPECIALIDAD_ID + " = E." + clsEspecialidad.ID
                + " order by M." + ID
                + " ";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error en listar la tabla " + TABLA + " / " + e.getMessage());
        }
    }

    public ResultSet listarMedicosconServicios() throws Exception {
        strSQL = """
                SELECT 
                    med.*,
                    U.nomusuario ,
                    esp.nom_especialidad,
                    esp.disponibilidad AS disp_esp,
                    COALESCE(COUNT(det.servicio_id), 0) AS cant_servicios
                FROM medico med
                LEFT JOIN especialidad esp ON esp.id = med.especialidad_id
                LEFT JOIN detalle_servicio det ON det.medico_id = med.id AND det.disponibilidad = true
                LEFT JOIN servicio ser ON ser.id = det.servicio_id AND ser.disponibilidad = true
                LEFT join usuario U on U.codusuario = Med.usuariocodusuario 
                GROUP BY  med.id, esp.id , U.nomusuario
                ORDER BY  med.vigencia DESC, med.id;
                """;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error en listar la tabla " + TABLA + ": \n" + e.getMessage());
        }
    }

//    public boolean verificarDisponibilidad(int medicoId, String horaEntrada, String horaSalida) throws SQLException {
//        // Formato de la hora: solo horas y minutos (sin fecha)
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");  // Asumiendo que la hora viene en formato "HH:mm"
//
//        try {
//            // Convertir las horas de entrada y salida a java.sql.Time
//            Time entradaTime = null;
//            Time salidaTime = null;
//
//            // Manejar posibles ParseExceptions
//            try {
//                entradaTime = new Time(sdf.parse(horaEntrada).getTime());
//                salidaTime = new Time(sdf.parse(horaSalida).getTime());
//            } catch (ParseException e) {
//                e.printStackTrace();  // O puedes mostrar un mensaje de error si es necesario
//                return false;  // Si el parseo falla, puedes retornar false o manejarlo de otra manera
//            }
//
//            // Formateamos las horas como cadena para incluirlas directamente en la consulta SQL
//            String horaEntradaStr = entradaTime.toString();
//            String horaSalidaStr = salidaTime.toString();
//
//            // Consulta SQL para verificar si hay algún cruce de horarios
//            String query = "SELECT COUNT(*) FROM detalle_cita WHERE detalle_servicio_med_id = " + medicoId + " "
//                + "AND ((horaEntrada BETWEEN '" + horaEntradaStr + "' AND '" + horaSalidaStr + "') "
//                + "OR (horaSalida BETWEEN '" + horaEntradaStr + "' AND '" + horaSalidaStr + "') "
//                + "OR ('" + horaEntradaStr + "' BETWEEN horaEntrada AND horaSalida) "
//                + "OR ('" + horaSalidaStr + "' BETWEEN horaEntrada AND horaSalida))";
//
//
//            // Ejecutar la consulta (usando Statement)
//            try (Statement stmt = objConectar.getCon().createStatement()) {
//                ResultSet rs = stmt.executeQuery(query);
//                if (rs.next()) {
//                    return rs.getInt(1) == 0;  // Si el resultado es 0, significa que no hay cruces de horarios
//                }
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();  // Maneja los errores de SQL
//        }
//
//        return false;  // Si ocurre algún error, asumimos que no está disponible
//    }
    public ResultSet buscarMedico(int id) throws Exception {
        strSQL = " select "
                + "     med.*,"
                + "     U.nomusuario, "
                + "     esp.nom_especialidad, "
                + "     esp.disponibilidad as disp_esp, "
                + "     count(det.servicio_id) as " + CANT_SERVICIOS
                + " from medico med "
                + " LEFT JOIN especialidad esp ON esp.id = med.especialidad_id "
                + " LEFT JOIN  detalle_servicio det ON det.medico_id = med.id AND det.disponibilidad = true "
                + " LEFT JOIN servicio ser ON ser.id = det.servicio_id AND ser.disponibilidad = true "
                + " LEFT join usuario U on U.codusuario = Med.usuariocodusuario "
                + " where med.id = " + id + " "
                + " group by med.id, esp.id , U.nomusuario "
                + " order by med.vigencia desc, med.id ";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar id: '" + id + "' en la tabla " + TABLA + ": " + e.getMessage());
        }
    }


    public ResultSet buscarDniMedicoPorNombreCompleto(String nombreCompleto) throws Exception {
        // Formulamos la consulta SQL para buscar el DNI del médico por nombre completo
        String strSQL = "SELECT med.doc_identidad " // Solo seleccionamos el DNI
                + " FROM medico med "
                + " WHERE (med.nombres || ' ' || med.apePaterno || ' ' || med.apeMaterno) ILIKE '%"
                + nombreCompleto + "%'"; // Concatenamos el nombre completo con los comodines

        try {
            rs = objConectar.consultarBD(strSQL);  // Suponiendo que 'objConectar.consultarBD' ejecuta la consulta SQL
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar el DNI del médico por nombre completo: " + e.getMessage());
        }
    }

    public ResultSet buscarMedicosPorServicio(int servicioId) throws Exception {
        String strSQL = "SELECT "
                + "    med.*, "
                + "    U.nomusuario, "
                + "    esp.nom_especialidad, "
                + "    esp.disponibilidad AS disp_esp, "
                + "    COUNT(det.servicio_id) AS " + CANT_SERVICIOS
                + " FROM medico med "
                + " LEFT JOIN especialidad esp ON esp.id = med.especialidad_id "
                + " LEFT JOIN detalle_servicio det ON det.medico_id = med.id AND det.disponibilidad = true "
                + " LEFT JOIN servicio ser ON ser.id = det.servicio_id AND ser.disponibilidad = true "
                + " LEFT JOIN usuario U ON U.codusuario = med.usuariocodusuario "
                + " WHERE det.servicio_id = " + servicioId + " "
                + " GROUP BY med.id, esp.id, U.nomusuario "
                + " ORDER BY med.vigencia DESC, med.id";

        try {
            rs = objConectar.consultarBD(strSQL);  // Suponiendo que 'objConectar.consultarBD' ejecuta la consulta SQL y devuelve el ResultSet.
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar médicos para el servicio con id: '" + servicioId + "': " + e.getMessage());
        }
    }

    public ResultSet buscarMedico_1(String dni) throws Exception {
        strSQL = "select "
                + " M.*, E." + clsEspecialidad.NOMBRE + " "
                + " from " + TABLA + " M "
                + " inner join " + clsEspecialidad.TABLA + " E on M." + ESPECIALIDAD_ID + " = E." + clsEspecialidad.ID
                + " where " + DOC_IDENTIDAD + " = '" + dni + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar dni: '" + dni + "' en la tabla " + TABLA + ": " + e.getMessage());
        }
    }

    public void registrarMedico(
            Integer id,
            String nom,
            String apeP,
            String apeM,
            String dni,
            Boolean sexo,
            Integer esp,
            Integer usu
    ) throws Exception {
        strSQL = "insert into " + TABLA + " values ("
                + id + ","
                + "'" + nom + "'" + ","
                + "'" + apeP + "'" + ","
                + "'" + apeM + "'" + ","
                + "'" + dni + "'" + ","
                + sexo + ","
                + "true,"
                + "true,"
                + esp + ","
                + usu
                + ")";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar DNI: " + dni + " en la tabla " + TABLA + " -->" + e.getMessage());
        }
    }

    public void modificarMedico(
            Integer id,
            String nom,
            String apeP,
            String apeM,
            String dni,
            Boolean sexo,
            Integer esp
    ) throws Exception {
        strSQL = "update " + TABLA + " set "
                + NOMBRES + " = " + "'" + nom + "'" + ","
                + APE_PATERNO + " = " + "'" + apeP + "'" + ","
                + APE_MATERNO + " = " + "'" + apeM + "'" + ","
                + DOC_IDENTIDAD + " = " + "'" + dni + "'" + ","
                + SEXO + " = " + "'" + sexo + "'" + ","
                + ESPECIALIDAD_ID + " = " + esp
                + " where id = '" + id + "'";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar DNI: " + dni + " en la tabla " + TABLA + " -->" + e.getMessage());
        }
    }

    public void eliminarMedico(int id) throws Exception {
        strSQL = "delete from " + TABLA + " where id = '" + id + "'";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar ID: " + id + " en tabla " + TABLA + ": " + e.getMessage());
        }
    }

    public void cambiarDisponibilidad(Integer id) throws Exception {
        Boolean disp = null;
        Boolean vig = null;

        rs = objConectar.consultarBD("select " + DISPONIBILIDAD + "," + VIGENCIA + " from " + TABLA + " where " + ID + " = '" + id + "'");

        while (rs.next()) {
            disp = rs.getBoolean(DISPONIBILIDAD);
            vig = rs.getBoolean(VIGENCIA);
        }

        try {
            if (vig) {
                strSQL = "update " + TABLA + " set " + DISPONIBILIDAD + " = " + !disp + " where " + ID + " = '" + id + "'";
            }
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al cambiar disponibilidad en ID:" + id + " en tabla " + TABLA + ": " + e.getMessage());
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

    public Integer obtenerIDconDoc(String docIdentidad) throws Exception {
        strSQL = "SELECT id FROM " + TABLA + " WHERE doc_identidad = '" + docIdentidad + "' ";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            throw new Exception("Error al buscar código de " + TABLA + " con el documento de identidad " + docIdentidad + " --> " + e.getMessage());
        }
        return 0;
    }

    
    public Integer obtenerIDUser(Integer id_med) throws Exception {
        strSQL = "SELECT " + CODIGO_USUARIO + " as user_id FROM " + TABLA + " WHERE " + ID + " = " + id_med + " ";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("user_id");
            }
        } catch (Exception e) {
            throw new Exception("Error al buscar código de " + TABLA + " con el ID " + id_med + " --> " + e.getMessage());
        }
        return 0;
    }

    public ResultSet listarMedicosVigentesDisponibles() throws Exception {
        strSQL = "SELECT "
                + " M.*, "
                + " E." + clsEspecialidad.NOMBRE + " "
                + " FROM " + TABLA + " M "
                + " INNER JOIN " + clsEspecialidad.TABLA + " E ON M." + ESPECIALIDAD_ID + " = E." + clsEspecialidad.ID
                + " WHERE M.disponibilidad = true "
                + " AND M.vigencia = true "
                + " ORDER BY M." + ID;

        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar los médicos vigentes y disponibles: " + e.getMessage());
        }
    }

  public ResultSet listarMedicosVigentesDisponiblesConCitasPendientes() throws Exception {
    strSQL = "SELECT distinct "
            + " Me.*, "
            + " E." + clsEspecialidad.NOMBRE + " "
            + " FROM detalle_cita dc "
            + " INNER JOIN cita c ON c.id = dc.cita_id "
            + " INNER JOIN detalle_servicio ds ON ds.servicio_id = dc.detalle_servicio_serv_id "
            + " AND ds.medico_id = dc.detalle_servicio_med_id "
            + " INNER JOIN medico me ON me.id = dc.detalle_servicio_med_id "
            + " INNER JOIN " + clsEspecialidad.TABLA + " E ON me." + ESPECIALIDAD_ID + " = E." + clsEspecialidad.ID
            + " WHERE Me.disponibilidad = true "
            + " AND Me.vigencia = true "
            + " AND c.estado_cita_id = 1 AND c.fecha_cita::DATE >= CURRENT_DATE "
            + " ORDER BY Me." + ID;

    try {
        rs = objConectar.consultarBD(strSQL);
        return rs;
    } catch (Exception e) {
        throw new Exception("Error al listar los médicos vigentes y disponibles: " + e.getMessage());
    }
}

    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JTable;

/**
 *
 * @author Junior
 */
public class clsDetalleCita {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    Connection con = null;
    Statement sent;

//    public void insertarDetalleCita(Integer citaId, Integer servicioId, Integer medicoId, String horaEntrada, String horaSalida, String notaAdicional) throws Exception {
//        String strSQL = "INSERT INTO DETALLE_CITA (cita_id, detalle_servicio_serv_id, detalle_servicio_med_id, horaEntrada, horaSalida, nota_adicional) "
//                + "VALUES (" + citaId + ", " + servicioId + ", " + medicoId + ", '" + horaEntrada + "', '" + horaSalida + "', '" + notaAdicional + "')";
//
//        try {
//            objConectar.ejecutarBD(strSQL);
//        } catch (Exception e) {
//            throw new Exception("Error al insertar detalle de cita --> " + e.getLocalizedMessage());
//        }
//    }
    
    public ResultSet buscarDetalleCita(Integer numCita) throws Exception {
        strSQL = "SELECT DETALLE_CITA.*, "
                + "SERVICIO.nom_servicio AS servicio_nombre, SERVICIO.descripcion AS servicio_descripcion, SERVICIO.costo AS costo,"
                + "MEDICO.nombres AS medico_nombres, MEDICO.apePaterno AS medico_apPaterno, MEDICO.apeMaterno AS medico_apMaterno, "
                + "MEDICO.doc_identidad AS medico_doc "
                + "FROM DETALLE_CITA "
                + "INNER JOIN SERVICIO ON DETALLE_CITA.detalle_servicio_serv_id = SERVICIO.id "
                + "INNER JOIN MEDICO ON DETALLE_CITA.detalle_servicio_med_id = MEDICO.id "
                + "WHERE DETALLE_CITA.cita_id = " + numCita;

        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar detalle de cita --> " + e.getMessage());
        }
    }

    public void insertarDetalleServicioSiNoExiste(int idCita, JTable tblServicios, int codSer, int codMed, String hora_ini, String hora_fin, String nota) throws Exception {
        try {
            objConectar.conectar();
            con = objConectar.getCon();
            con.setAutoCommit(false);

            // Convertir las horas de String a Time
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            Time horaEntrada = new Time(format.parse(hora_ini).getTime());
            Time horaSalida = new Time(format.parse(hora_fin).getTime());

            // Verifica si el detalle ya existe en la base de datos
            String checkSQL = "SELECT COUNT(*) FROM DETALLE_CITA WHERE cita_id = ? AND detalle_servicio_serv_id = ? AND detalle_servicio_med_id = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkSQL);
            checkStmt.setInt(1, idCita);
            checkStmt.setInt(2, codSer);
            checkStmt.setInt(3, codMed);

            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int countInDB = rs.getInt(1);

            boolean existsInTable = false;
            int rowCount = tblServicios.getRowCount();

            // Verificar si el detalle ya existe en tblServicios
            for (int i = 0; i < rowCount; i++) {
                String cadena = String.valueOf(tblServicios.getValueAt(i, 0));
                String[] codigos = cadena.split(" - ");
                int codSerInTable = Integer.parseInt(codigos[0].trim());
                int codMedInTable = Integer.parseInt(codigos[1].trim());

                if (codSer == codSerInTable && codMed == codMedInTable) {
                    existsInTable = true;
                    break;
                }
            }

            // Solo inserta si no existe en la tabla y tampoco en la base de datos
            if (countInDB == 0 && !existsInTable) {
                String insertSQL = "INSERT INTO DETALLE_CITA (cita_id, detalle_servicio_serv_id, detalle_servicio_med_id, horaEntrada, horaSalida, nota_adicional) "
                        + "VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement insertStmt = con.prepareStatement(insertSQL);
                insertStmt.setInt(1, idCita);
                insertStmt.setInt(2, codSer);
                insertStmt.setInt(3, codMed);
                insertStmt.setTime(4, horaEntrada);
                insertStmt.setTime(5, horaSalida);
                insertStmt.setString(6, nota);
                insertStmt.executeUpdate();

                System.out.println("Detalle de servicio insertado correctamente.");
            } else if (existsInTable) {
                System.out.println("El detalle de servicio ya existe en la tabla y no se ha insertado.");
            } else {
                System.out.println("El detalle de servicio ya existe en la base de datos y no se ha insertado.");
            }

            con.commit();
        } catch (ParseException e) {
            throw new Exception("Formato de hora incorrecto. Utilice el formato HH:mm.");
        } catch (Exception e) {
            con.rollback();
            throw new Exception("Error al insertar detalle de servicio no repetido --> " + e.getLocalizedMessage());
        } finally {
            objConectar.desconectar();
        }
    }

    public ResultSet listarServiciosxMascota(int mas_id) throws Exception {
        strSQL= " SELECT DISTINCT " +
                    " S."+clsServicio.ID+" as ser_id, " +
                    " S.nom_servicio, " +
                    " S.descripcion, " +
                    " S.costo, " +
                    " S.disponibilidad " +
                " FROM DETALLE_CITA DC " +
                " LEFT JOIN DETALLE_SERVICIO DS ON DC.detalle_servicio_serv_id = DS.servicio_id AND DC.detalle_servicio_med_id = DS.medico_id " +
                " LEFT JOIN SERVICIO S ON DS.servicio_id = S.id " +
                " LEFT JOIN CITA C ON DC.cita_id = C.id " +
                " LEFT JOIN CUSTODIA CU ON C.CUSTODIAMASCOTAid = CU.MASCOTAid AND C.CUSTODIADUEniOid = CU.DUEniOid " +
                " WHERE CU.MASCOTAid = "+mas_id+
                " order by ser_id"
                ;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error: "+e.getMessage());
        }
    }
    
    public ResultSet listarServiciosxMascotaCita(int mas_id , int cita_id) throws Exception {
        strSQL= " SELECT " +
                    " DC.cita_id, " +
                    " S.id as ser_id, " +
                    " S.nom_servicio, " +
                    " DC.horaentrada, " +
                    " DC.horasalida, " +
                    " ME.id as med_id , " +
                    " ME.nombres, " +
                    " ME.apepaterno, " +
                    " ME.apematerno " +
                " FROM DETALLE_CITA DC " +
                " LEFT JOIN DETALLE_SERVICIO DS ON DC.detalle_servicio_serv_id = DS.servicio_id AND DC.detalle_servicio_med_id = DS.medico_id " +
                " LEFT JOIN SERVICIO S ON DS.servicio_id = S.id " +
                " LEFT JOIN CITA C ON DC.cita_id = C.id " +
                " LEFT JOIN CUSTODIA CU ON C.CUSTODIAMASCOTAid = CU.MASCOTAid AND C.CUSTODIADUEniOid = CU.DUEniOid " +
                " LEFT JOIN MEDICO ME ON ME.id = DS.medico_id " +
                " WHERE CU.MASCOTAid = "+mas_id+" and dc.cita_id = "+cita_id+
                " order by dc.horaentrada "
                ;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error: "+e.getMessage());
        }
    }
        
    public ResultSet listarMedicosxMascota(int mas_id) throws Exception {
        strSQL= " SELECT DISTINCT " +
                "    MD.id AS id_medico, " +
                "    MD.nombres AS nombre_medico, " +
                "    MD.apePaterno AS apellido_paterno, " +
                "    MD.apeMaterno AS apellido_materno, " +
                "    MD.doc_identidad, " +
                "    E.nom_especialidad AS especialidad " +
                " FROM DETALLE_CITA DC " +
                " LEFT JOIN DETALLE_SERVICIO DS ON DC.detalle_servicio_serv_id = DS.servicio_id AND DC.detalle_servicio_med_id = DS.medico_id " +
                " LEFT JOIN MEDICO MD ON DS.medico_id = MD.id " +
                " LEFT JOIN ESPECIALIDAD E ON MD.especialidad_id = E.id " +
                " LEFT JOIN CITA C ON DC.cita_id = C.id " +
                " LEFT JOIN CUSTODIA CU ON C.CUSTODIAMASCOTAid = CU.MASCOTAid AND C.CUSTODIADUEniOid = CU.DUEniOid " +
                " WHERE CU.MASCOTAid = "+mas_id+
                " order by id_medico "
                ;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error: "+e.getMessage());
        }
    }
    
    
    
    
    
}

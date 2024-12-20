/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capaNegocio;

import capaDatos.clsJDBC;
import static capaNegocio.clsDetalle_Servicio.TABLA;
import com.sun.jdi.connect.spi.Connection;
import java.beans.Statement;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author franc
 */
public class clsCustodia {
    

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    Connection con = null;
    Statement sent;

    //listarDueños
    public ResultSet listarDueniosV() throws Exception {
        strSQL = "SELECT * FROM DUEniO where vigencia= true order by 1";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar Dueño");
        }
    }

    
    public ResultSet listarMascotasV() throws Exception {
        strSQL = "SELECT ma.*,d.nombres as d_nom, ra.nombre AS raza_nombre FROM MASCOTA ma INNER JOIN raza ra ON ra.id = ma.raza_id "
                + " INNER JOIN CUSTODIA c ON ma.id = c.MASCOTAid "
                + " INNER JOIN DUEniO d ON c.DUEniOid = d.id "
                + " where ma.vigencia= true order by 1";
        try {
            return objConectar.consultarBD(strSQL);

        } catch (Exception e) {
            throw new Exception("Error al listar Mascotas: " + e.getMessage());
        }
    }

    
    public ResultSet filtrarDuenioV(int id) throws Exception {
        strSQL = "SELECT * FROM duenio where vigencia= true and id=" + id;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar Dueño");
        }
    }

    
    public ResultSet filtrarDuenioNV(String numDoc) throws Exception {
        strSQL = "SELECT * FROM duenio where vigencia= true and doc_identidad= '" + numDoc + "' ;";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar Dueño por número de documento");
        }
    }

    
    public ResultSet filtrarMascota(int id) throws Exception {
        strSQL = "SELECT ma.*, ra.nombre AS raza_nombre, ma.id as ma_id ,ma.nombre as nom_mas "
                + "FROM MASCOTA ma "
                + "INNER JOIN raza ra ON ra.id = ma.raza_id "
                + "WHERE ma.id ="+ id +";";

        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al filtrar mascotas por código" + e.getMessage());
        }
    }

    
    public ResultSet filtrarMascota(String nom) throws Exception {
    // Asegurarse de sanitizar la entrada para evitar problemas de inyección SQL
    strSQL = "SELECT m.*, ra.nombre AS raza_nombre, m.id as ma_id, m.nombre as nom_mas "
           + "FROM MASCOTA m "
           + "INNER JOIN raza ra ON m.raza_id = ra.id "  // Asegurando que la relación se haga por el campo correcto
           + "WHERE UPPER(m.nombre) LIKE UPPER ('%" + nom + "%')";  // Concatenación con cuidado (pero no recomendado)

    try {
        rs = objConectar.consultarBD(strSQL);  // Ejecutar la consulta
        return rs;  // Retornar el ResultSet con los resultados
    } catch (Exception e) {
        throw new Exception("Error al filtrar mascotas por nombre: " + e.getMessage());
    }
}


//    public ResultSet listarCustodia() throws Exception {
//        strSQL = "SELECT m.*,c.MASCOTAid as id_mas,c.DUEniOid as id_due, m.nombre as nom_mas, d.nombres as due_mas, c.fecha_adopción as fa "
//                + "FROM MASCOTA m "
//                + "INNER JOIN CUSTODIA c ON m.id = c.MASCOTAid "
//                + "INNER JOIN DUEniO d ON c.DUEniOid = d.id "
//                + "ORDER BY nom_mas";
//
//        try {
//            rs = objConectar.consultarBD(strSQL);
//            return rs;
//        } catch (Exception e) {
//            throw new Exception("Error al listar las custodias");
//        }
//    }

    
    public void registrarCustodia(int mas_id, int due_id, Date F_A) throws Exception {
        // Si la fecha es null, se debe insertar 'NULL' como valor en la base de datos
        String fecha = (F_A == null) ? "NULL" : "'" + new java.sql.Date(F_A.getTime()) + "'";

        // Construir la sentencia SQL
        strSQL = "INSERT INTO custodia (MASCOTAid, DUEniOid, fecha_adopción) VALUES ("
                + mas_id + ", " + due_id + ", " + fecha + ")";

        try {
            // Ejecutar la consulta
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar en la tabla custodia -->" + e.getMessage());
        }
    }

    
    public ResultSet filtrarCustodia(int nom_M) throws Exception {
        strSQL = "SELECT m.*, c.duenioid as id_d, c.mascotaid as id_m,m.nombre as nom_mas, d.nombres as due_mas, c.fecha_adopción as fa "
                + "FROM MASCOTA m "
                + "INNER JOIN CUSTODIA c ON m.id = c.MASCOTAid "
                + "INNER JOIN DUEniO d ON c.DUEniOid = d.id "
                + "WHERE c.mascotaid= " + nom_M ;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al filtrar Custodia por Código de Mascota" + e.getMessage());
        }
    }

    
    public ResultSet filtrarCustodiaD(int nom_D) throws Exception {
         strSQL = "SELECT m.*, c.duenioid as id_d, c.mascotaid as id_m, m.nombre as nom_mas, d.nombres as due_mas, c.fecha_adopción as fa "
                + "FROM MASCOTA m "
                + "INNER JOIN CUSTODIA c ON m.id = c.MASCOTAid "
                + "INNER JOIN DUEniO d ON c.DUEniOid = d.id "
                + "WHERE c.duenioid =" + nom_D ;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al filtrar Custodia por Código de Dueño" + e.getMessage());
        }
    }

    
    public void eliminarCustodiaMAscota(int id_M, int id_DAntiguo) throws Exception {
        strSQL = "DELETE FROM custodia WHERE MASCOTAid = " + id_M + " AND DUEniOid = " + id_DAntiguo;

        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar id: " + id_DAntiguo + " en la tabla Dueño --> " + e.getMessage());
        }
        
    }
    
    
    public boolean existeCustodia(int dueId, int masId) throws Exception {
        String strSQL = "SELECT COUNT(*) FROM custodia WHERE duenioid = " + dueId + " AND mascotaid = " + masId;
        ResultSet rs = null;

        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt(1) > 0;  // Si el contador es mayor que 0, existe la combinación
            }
        } catch (Exception e) {
            throw new Exception("Error al verificar la existencia de custodia: " + e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
            }
        }
        return false;
    }

    
    public ResultSet listarDueniosxMascota(int mas_id) throws Exception {
        strSQL =  " SELECT "
                + " D.id, "
                + " D.nombres, "
                + " D.apePaterno, " 
                + " D.apeMaterno, " 
                + " D.doc_identidad, " 
                + " D.telefono, " 
                + " D.correo, " 
                + " D.direccion, " 
                + " D.vigencia"
                + " FROM CUSTODIA CU "
                + " LEFT JOIN DUEniO D ON CU.DUEniOid = D.id "
                + " WHERE CU.MASCOTAid = "+mas_id;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error: "+e.getMessage());
        }
    }
    
    public ResultSet filtrarCustodiaD(String nom_D) throws Exception {
        strSQL = "SELECT m.*, c.duenioid as id_d, c.mascotaid as id_m, m.nombre as nom_mas, d.nombres as due_mas, c.fecha_adopción as fa "
                + "FROM MASCOTA m "
                + "INNER JOIN CUSTODIA c ON m.id = c.MASCOTAid "
                + "INNER JOIN DUEniO d ON c.DUEniOid = d.id "
                + "WHERE d.doc_identidad = '" + nom_D + "' ";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al filtrar Custodia por Código de Dueño" + e.getMessage());
        }
    }
    
    public ResultSet filtrarMascotaa(int id) throws Exception {
        strSQL = "SELECT ma.*,ra.nombre AS raza_nombre, ma.id as ma_id ,ma.nombre as nom_mas "
                + "FROM MASCOTA ma "
                + "INNER JOIN raza ra ON ra.id = ma.raza_id "
                + "where vigencia= true and ma.id =" + id + " ";

        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al filtrar mascotas por código" + e.getMessage());
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
    
     public ResultSet filtrarMascotaVig(String nom) throws Exception {
        strSQL = "SELECT m.*, ra.nombre AS raza_nombre, m.id as ma_id, m.nombre as nom_mas , especie_id "
                + "FROM MASCOTA m "
                + "INNER JOIN raza ra ON m.raza_id = ra.id " 
                + " inner join especie esp on esp.id = ra.especie_id "// Asegurando que la relación se haga por el campo correcto
                + " WHERE UPPER(m.nombre) LIKE UPPER ('%" + nom + "%') and vigencia= true";  // Concatenación con cuidado (pero no recomendado)

        try {
            rs = objConectar.consultarBD(strSQL);  // Ejecutar la consulta
            return rs;  // Retornar el ResultSet con los resultados
        } catch (Exception e) {
            throw new Exception("Error al filtrar mascotas por nombre: " + e.getMessage());
        }
    }
	
    public ResultSet listarMascotasVig() throws Exception {
        strSQL = "SELECT ma.*, ra.nombre AS raza_nombre , ra.* FROM MASCOTA ma INNER JOIN raza ra ON ra.id = ma.raza_id "
                + " where ma.vigencia= true order by 1 desc";
        try {
            return objConectar.consultarBD(strSQL);

        } catch (Exception e) {
            throw new Exception("Error al listar Mascotas: " + e.getMessage());
        }
    }
	    //listarDueños
    public ResultSet listarDueniosV(String cod) throws Exception {
        strSQL = "SELECT * FROM DUEniO where vigencia= true order by 1";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar Dueño");
        }
    }
 public ResultSet listarDueniosVIg() throws Exception {
        strSQL = "SELECT * FROM DUEniO where vigencia= true order by 1";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar Dueño");
        }
    }
  public ResultSet listarMascVIg() throws Exception {
        strSQL = "SELECT * FROM MASCOTA where vigencia= true order by 1";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar Dueño");
        }
    }
  
    public ResultSet listarCustodia() throws Exception {
        strSQL = " SELECT m.* , "
                + " c.MASCOTAid as id_mas, "
                + " c.DUEniOid as id_due, "
                + " m.nombre as nom_mas, "
                + " d.nombres as due_mas,"
                + " d.apePaterno as due_app,"
                + " d.apeMaterno as due_apm,"
                + " raz.nombre as raz_nom,"
                + " esp.nombre as esp_nom,"
                + " c.fecha_adopción as fa "
                + " FROM MASCOTA m "
                + " INNER JOIN RAZA raz ON raz.id = m.raza_id "
                + " INNER JOIN ESPECIE esp ON esp.id = raz.especie_id "
                + " INNER JOIN CUSTODIA c ON m.id = c.MASCOTAid "
                + " INNER JOIN DUEniO d ON c.DUEniOid = d.id "
                + " ORDER BY id_due ";

        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar las custodias : "+e.getMessage());
        }
    }
    
    public void eliminarCustodia(int ser_id, int med_id) throws Exception {
        strSQL = "delete from CUSTODIA where MASCOTAid= " + ser_id + " and DUEniOid = " + med_id + " ";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar en la tabla " + TABLA + " -->" + e.getMessage());
        }
    }
    
}

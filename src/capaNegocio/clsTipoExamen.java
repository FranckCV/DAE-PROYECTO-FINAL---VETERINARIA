/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capaNegocio;
import capaDatos.clsJDBC;
import java.sql.ResultSet;
/**
 *
 * @author Fabiana Lucía
 */
public class clsTipoExamen {
    //Empezamos creando una variable para mascota
    clsJDBC objConectar = new clsJDBC(); //
    String strSQL; //Variable para guardar la consulta
    ResultSet rs = null;
    
    //Método para listar, debo devolver un resultset
    public ResultSet listarTipoExamen() throws Exception{
        strSQL="select*from tipo_examen";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error en listar la tabla tipo_examen" + e.getMessage());
        }
    }
    //Método para buscar, debo devolver un resulset tmb, pero solo un registro
    public ResultSet buscar(int id)throws Exception{
        strSQL="select * from tipo_examen where id="+id;
        try{
            rs= objConectar.consultarBD(strSQL);
            return rs;
        }
        catch(Exception e){
            throw new Exception("Error en listar la tabla tipo_examen" + e.getMessage());
        }
    }
     public Integer generarCodigo() throws Exception{
        strSQL="select coalesce( max(id) , 0)+1 as codigo from tipo_examen";
        try{
            rs=objConectar.consultarBD(strSQL);
            while(rs.next()){
                return rs.getInt("codigo");
            }
        }
        catch (Exception e){
            throw new Exception("Error al generar codigo de Tipo de examen --> "+e.getMessage());
        }
        return 0;
    }
    //Método para registrar/ insertar
    public void registrar(int cod, String nom)throws Exception{
     strSQL="insert into tipo_examen values(" + cod + ", '"+nom+"')";
        try{
            objConectar.ejecutarBD(strSQL);
        }
        catch(Exception e){
            throw new Exception("Error al registrar el tipo_examen --> "+e.getMessage());
        }   
    }
    //Método para eliminar
    public void eliminar(int cod) throws Exception{
        strSQL="delete from tipo_examen where id="+cod;
        try{
            objConectar.ejecutarBD(strSQL);
        }
        catch(Exception e){
            throw new Exception("Error al eliminar en tipo_examen"+e.getMessage());
        }
    }
    //Método para modificar
    public void modificar(int cod,String nombre) throws Exception{
        strSQL="update tipo_examen set nombre='"+nombre+"'where id="+cod;
        try{
            objConectar.ejecutarBD(strSQL);
        }
        catch(Exception e){
            throw new Exception("Error al modificar en tipo_examen"+e.getMessage());
        }
    }
    
}

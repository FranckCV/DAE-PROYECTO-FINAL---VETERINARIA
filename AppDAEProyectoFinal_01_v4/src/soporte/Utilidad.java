/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package soporte;

import capaNegocio.*;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.NumberFormatter;
import capaDatos.clsJDBC;
import java.sql.ResultSet;

/**
 *
 * @author franc
 */
public class Utilidad {
//    clsJDBC objConectar = new clsJDBC();
//    String strSQL;
//    ResultSet rs = null;
    
//    Texto en Botones
    public static final String BTN_NUEVO = "Registrar";
    public static final String BTN_GUARDAR = "Guardar";
    public static final String BTN_MODIFICAR = "Modificar";
    public static final String BTN_ELIMINAR = "Eliminar";
    public static final String BTN_LIMPIAR = "Vaciar campos";
    public static final String BTN_CANCELAR = "Cancelar";
    public static final String BTN_DISPONIBILIDAD = "Cambiar Disponibilidad";    
    public static final String BTN_VIGENCIA = "Dar de Baja";  
    
//    Texto en Listados
    public static final String SEXO_MAS = "Masculino";
    public static final String SEXO_FEM = "Femenino";
    public static final String VIGENCIA_SI = "Vigente";
    public static final String VIGENCIA_NO = "No Vigente";
    public static final String DISPONIBILIDAD_SI = "Disponible";
    public static final String DISPONIBILIDAD_NO = "No Disponible";
    public static final String DISPONIBLE_NO_EXT = "(No Disp)";    
    
//    Texto de valores Booleanos
    
    public static String textoBool(boolean vig, String txtTrue, String txtFalse) {
        if (vig) {
            return txtTrue;
        } else {
            return txtFalse;
        }
    }
    
//    Validaciones Mantenimiento
    
    public static boolean validarElementoTextoRepetido(String tabla, String columna, String campo) throws Exception{
        clsJDBC objConectar = new clsJDBC();
        String strSQL;
        ResultSet rs = null;
        
        strSQL= " select * from "+tabla+
                " where "+columna+" = '"+campo+"' ";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs.next();
        } catch (Exception e) {
            throw new Exception("Error al buscar Elemento "+campo+" en la tabla " + tabla + " / " + e.getMessage());
        }
    }
    
//    Validaciones de Elementos de Interfaz
    
    public static void validarCampoTextoSoloNumero(java.awt.event.KeyEvent evt){
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57    ;

        if (!(numeros)) {
            evt.consume();
        }
    }
        
    public static void validarCampoTextoSoloNumeroDecimal(java.awt.event.KeyEvent evt){
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57    ;
        boolean punto = key == 46;

        if (!(numeros || punto)) {
            evt.consume();
        }
    }
    
    public static void validarCampoTextoSoloLetras(java.awt.event.KeyEvent evt){
        int key = evt.getKeyChar();

        boolean mayusculas = key >= 65 && key <= 90;
        boolean minusculas = key >= 97 && key <= 122;
        boolean espacio = key == 32;

        if (!(minusculas || mayusculas || espacio)) {
            evt.consume();
        }
    }
    
    public static void validarSpinnerNumerosPositivos(JSpinner spn){
        SpinnerNumberModel model = new SpinnerNumberModel(00, 00, null, 1);
        spn.setModel(model);
        JFormattedTextField txt = ((JSpinner.NumberEditor) spn.getEditor()).getTextField();
        ((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
    }
    
        
    public static void validarCampoTextoDocIdentidad(java.awt.event.KeyEvent evt){
//        if(evt.getComponent()getText().length() >= 8) {
//            evt.consume();
//        }
        
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57    ;
//        boolean guion = key == 45;

        if (!(numeros)) {
            evt.consume();
        } 
    }
    
//    Mensajes de Error 
    
    public static String mensajeErrorEliminacionForanea (Exception e , String entidad, String nombre) {
        String mensaje = e.getMessage();
        String[] palabras = { 
            "referida desde la tabla", 
            "foránea", 
            "fk", 
            "ERROR: update o delete en"
        };
        
        for (String keyword : palabras) {
            if (!mensaje.contains(keyword)) {
                return mensaje;
            }
        }
        return "Hay datos externos asociados a "+entidad+" \"" + nombre + "\".\n" +
               "Considere cambiar su disponibilidad o vigencia para que ya no pueda ser usado. ";
    }
    
    
    
    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package soporte;

import java.sql.*;
import capaNegocio.*;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.NumberFormatter;
import capaDatos.clsJDBC;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author franc
 */
public class Utilidad {
    clsJDBC objConectar = new clsJDBC();
    String strSQL = "";
    ResultSet rs = null;
    
//    Texto en Botones
    public static final String BTN_NUEVO = "Registrar";
    public static final String BTN_GUARDAR = "Guardar";
    public static final String BTN_MODIFICAR = "Modificar";
    public static final String BTN_ELIMINAR = "Eliminar";
    public static final String BTN_LIMPIAR = "Vaciar campos";
    public static final String BTN_CANCELAR = "Cancelar";
    public static final String BTN_DISPONIBILIDAD = "Cambiar Disponibilidad";
    public static final String BTN_VIGENCIA = "Dar de Baja";
    public static final String BTN_AGREGAR = "Agregar";
    public static final String BTN_QUITAR = "Quitar";
   
//    Texto en Listados
    public static final String SEXO_MAS = "Masculino";
    public static final String SEXO_FEM = "Femenino";
    public static final String SEXO_MASC_MAS = "Macho";
    public static final String SEXO_MASC_FEM = "Hembra";
    public static final String VIGENCIA_SI = "Vigente";
    public static final String VIGENCIA_NO = "No Vigente";
    public static final String DISPONIBILIDAD_SI = "Disponible";
    public static final String DISPONIBILIDAD_NO = "No Disponible";
    public static final String DISPONIBLE_NO_EXT = "(No Disp)";

//    Texto de Caracteres
    
    public static String mostrarEstadoMascota(String est) {
        if (est.equals("T")) {
            return "Terminal";
        } else if (est.equals("C")) {
            return "Crónica";
        } else if (est.equals("S")) {
            return "Saludable";
        }
        return null;
    }
    
    public static String obtenerCargoxCaracter(String cargo) {
        if (cargo.equals('A')) {
            return "Administrador";
        } else if (cargo.equals('E')){
            return "Empleado";
        } else if (cargo.equals('V')){
            return "Veterinario";
        }
        return null;
    }
    
    public static String obtenerCargoxCadena(String cargo) {
        if (cargo.equalsIgnoreCase("Administrador")) {
            return "A";
        } else if (cargo.equalsIgnoreCase("Empleado")){
            return "E";
        } else if (cargo.equalsIgnoreCase("Veterinario")){
            return "V";
        }
        return null;
    }
    
    
//    Texto de valores Booleanos
    
    public static String textoBool(boolean valor, String txtTrue, String txtFalse) {
        if (valor) {
            return txtTrue;
        } else {
            return txtFalse;
        }
    }

//    INTERFACES
        
//    public static void mostrarInterfazjDialog(String nombreClase, JFrame parent) {
//        try {
//            Class<?> clase = Class.forName(nombreClase);
//
//            if (!JDialog.class.isAssignableFrom(clase)) {
//                throw new IllegalArgumentException("La clase proporcionada no es un JDialog válido.");
//            }
//
//            JDialog objForm = (JDialog) clase
//                    .getConstructor(java.awt.Frame.class, boolean.class)
//                    .newInstance(parent, true);
//
//            objForm.setLocationRelativeTo(parent);
//            objForm.setVisible(true);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Error al intentar abrir el diálogo: " + e.getMessage());
//        }
//    }
    
//    private void mostrarInterfazjDialog(JDialog interfaz){
//        Class clase = interfaz.getClass();
//        clase objForm = new clase(this, true);
//        objForm.setLocationRelativeTo(this);
//        objForm.setVisible(true);
//    }
    
    
//    Validaciones de Elementos de Interfaz
    public static void validarLimiteCampoTexto(java.awt.event.KeyEvent evt, String columna, String tabla) throws Exception {
        JTextField source = (JTextField) evt.getSource();

        clsJDBC objConectar = new clsJDBC();
        String strSQL;
        ResultSet rs = null;

        strSQL = " SELECT character_maximum_length as limite"
                + " FROM information_schema.columns "
                + " WHERE table_name = '" + tabla + "'  "
                + " AND column_name = '" + columna + "' ;";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                if (source.getText().length() >= rs.getInt("limite")) {
                    evt.consume();
                }
            }
        } catch (Exception e) {
            throw new Exception("Error al marcar limite a Elemento " + source.getName() + " en la tabla " + tabla + " / " + e.getMessage());
        }
    }

    public static void validarCampoTextoSoloNumero(java.awt.event.KeyEvent evt) {
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!(numeros)) {
            evt.consume();
        }
    }

    public static void validarCampoTextoSoloNumeroDecimal(java.awt.event.KeyEvent evt) {
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;
        boolean punto = key == 46;

        if (!(numeros || punto)) {
            evt.consume();
        }
    }

    public static void validarCampoTextoSoloLetras(java.awt.event.KeyEvent evt) {
        int key = evt.getKeyChar();

        boolean mayusculas = key >= 65 && key <= 90;
        boolean minusculas = key >= 97 && key <= 122;
        boolean espacio = key == 32;

        if (!(minusculas || mayusculas || espacio)) {
            evt.consume();
        }
    }

    public static void validarSpinnerNumerosPositivos(JSpinner spn) {
        SpinnerNumberModel model = new SpinnerNumberModel(00, 00, null, 1);
        spn.setModel(model);
        JFormattedTextField txt = ((JSpinner.NumberEditor) spn.getEditor()).getTextField();
        ((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
    }

    public static void validarCampoTextoDocIdentidad(java.awt.event.KeyEvent evt) {
//        if(evt.getComponent()getText().length() >= 8) {
//            evt.consume();
//        }

        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;
//        boolean guion = key == 45;

        if (!(numeros)) {
            evt.consume();
        }
    }

//    Mensajes de Error 
    public static String mensajeErrorEliminacionForanea(Exception e, String entidad, String nombre) {
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
        return "Hay datos externos asociados a " + entidad + " \"" + nombre + "\".\n"
                + "Considere cambiar su disponibilidad o vigencia para que ya no pueda ser usado. ";
    }
    
//    Validaciones con Base de Datos
    
    public static boolean validarEliminacionForanea(String tabla, int valor_id) throws Exception {
        clsJDBC objConectar = new clsJDBC();
        String strSQL;
        ResultSet rs = null;

        strSQL = " select sum(cantidad) as total from contar_relaciones('"+tabla+"',"+valor_id+") ";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                int total = rs.getInt("total");
                if (total > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            throw new Exception("Error al validar si elemento ID: " + valor_id + " en la tabla " + tabla + " / " + e.getMessage());
        }
        return false;
    }
    
    public static boolean validarElementoTextoRepetido(String tabla, String columna, String campo) throws Exception {
        clsJDBC objConectar = new clsJDBC();
        String strSQL;
        ResultSet rs = null;

        strSQL = " select * from " + tabla
                + " where " + columna + " = '" + campo + "' ";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs.next();
        } catch (Exception e) {
            throw new Exception("Error al buscar Elemento " + campo + " en la tabla " + tabla + " / " + e.getMessage());
        }
    }

    //BLOQUEAR BOTONES
    public static void desactivarBotones(String botonActivo, JButton... botones) {
        for (JButton boton : botones) {
            if (boton.getText().equals(botonActivo)) {
                boton.setEnabled(true); 
            } else {
                boton.setEnabled(false); 
            }
        }
    }
    //ACTIVAR BOTONES
    public static void activarBotones(JButton... botones) {
        for (JButton boton : botones) {
                boton.setEnabled(true); 
        }
    }
    

}

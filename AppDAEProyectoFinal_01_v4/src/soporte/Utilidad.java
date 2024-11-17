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
<<<<<<< HEAD
import javax.swing.JTable;
=======
import javax.swing.JOptionPane;
>>>>>>> 16272af685e9c5b2821f7aace8a645f7c181fb26
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
    
    public static String extraerEstado(String est) {
        if (est.equals("Terminal")) {
            return "T";
        } else if (est.equals("Crónica")) {
            return "C";
        } else if (est.equals("Saludable")) {
            return "S";
        }
        return null;
    }
    

    public static String obtenerCargoxCaracter(String cargo) {
        switch (cargo) {
            case "A":
                return "Administrador";
            case "E":
                return "Empleado";
            case "V":
                return "Veterinario";
            default:
                return null;
        }
    }

    public static String obtenerCargoxCadena(String cargo) {
        if (cargo.equalsIgnoreCase("Administrador")) {
            return "A";
        } else if (cargo.equalsIgnoreCase("Empleado")) {
            return "E";
        } else if (cargo.equalsIgnoreCase("Veterinario")) {
            return "V";
        }
        return null;
    }
    
    public static String obtenerCargoUsuario(String cargo) throws Exception {
        try {
            switch (cargo) {
                case "V":
                    return "Veterinario";
                case "E":
                    return "Empleado";
                case "A":
                    return "Administrador";
                default:
                    throw new IllegalArgumentException("Cargo no reconocido: " + cargo);
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener cargo: " + e.getMessage(), e);
        }
    }
    
//    Opciones de Cuadros de Dialogo
    
    public static final String[] opcionesEliminar = {
        "Eliminar", 
        "Cancelar"
    };
    
    public static final String[] opcionesVigencia = {
        "Dar Baja", 
        "Cancelar"
    };
    
    public static final String[] opcionesDisponibilidad = {
        "Cmabiar Disponibilidad", 
        "Cancelar"
    };
    
    public static final String[] opcionesModificar = {
        "Modificar", 
        "Cancelar"
    };
    
    public static final String[] opcionesRegistrar = {
        "Guardar información", 
        "Cancelar"
    };
    

//    Texto de valores Booleanos
    public static String textoBool(boolean valor, String txtTrue, String txtFalse) {
        if (valor) {
            return txtTrue;
        } else {
            return txtFalse;
        }
    }

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
    
    public static void mensajeErrorFaltaID(JDialog parent) {
        JOptionPane.showMessageDialog(
                parent, 
                " Debe ingresar una ID para continuar esta operación"
        );
    }
    
    public static int mensajeConfirmarEliminar(String entidad , int id ,String nombre) {
        int valor = JOptionPane.showOptionDialog(
                null, 
                "¿Está seguro que desea eliminar " + entidad.toLowerCase() + " \"" + nombre + "\" (ID: "+id+")? ",
                "Confirmar Eliminacion",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesEliminar,
                opcionesEliminar[0]
        );
        return valor;
    }
    
    public static int mensajeConfirmarModificar(String entidad , int id ,String nombre) {
        int valor = JOptionPane.showOptionDialog(
                null, 
                "¿Está seguro de guardar los cambios en " + entidad.toLowerCase() + " \"" + nombre + "\" (ID: "+id+")? ",
                "Confirmar Modificación",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesModificar,
                opcionesModificar[0]
        );
        return valor;
    }
    
    public static int mensajeConfirmarDisponibilidad(String entidad , int id ,String nombre) {
        int valor = JOptionPane.showOptionDialog(
                null, 
                "¿Está seguro cambiar la disponibilidad de " + entidad.toLowerCase() + " \"" + nombre + "\" (ID: "+id+")? ",
                "Confirmar Cambio de Disponibilidad",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesDisponibilidad,
                opcionesDisponibilidad[0]
        );
        return valor;
    }
    
    public static int mensajeConfirmarVigencia(String entidad , int id ,String nombre) {
        int valor = JOptionPane.showOptionDialog(
                null, 
                "¿Está seguro dar de baja " + entidad.toLowerCase() + " \"" + nombre + "\" (ID: "+id+")? ",
                "Confirmar Bar de Baja",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesVigencia,
                opcionesVigencia[0]
        );
        return valor;
    }
    
    public static void mensajeErrorNoEliminarForanea(String entidad , String nombre) {
        JOptionPane.showMessageDialog(
                null, 
                "Hay datos externos asociados a " + entidad.toLowerCase() + " \"" + nombre + "\".\n"
                + "Considere cambiar su disponibilidad o vigencia para que ya no pueda ser usado. "
        );
    }

//    Validaciones con Base de Datos
    public static boolean validarEliminacionForanea(String tabla, int valor_id) throws Exception {
        clsJDBC objConectar = new clsJDBC();
        String strSQL;
        ResultSet rs = null;

        strSQL = " select sum(cantidad) as total from contar_relaciones('" + tabla + "'," + valor_id + ") ";
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

    public static boolean verificarElementoParaUso(String tabla , String columna, Integer id ) throws Exception {
        clsJDBC objConectar = new clsJDBC();
        ResultSet rs;
        try {
            rs = objConectar.consultarBD("select "+columna+" from "+tabla+" where id = "+id+" ");
            while (rs.next()) {
                return rs.getBoolean(columna);
            }
        } catch (Exception e) {
            throw new Exception("Error al verificar "+columna+" de ID:" + id + " en tabla " + tabla + ": " + e.getMessage());
        }
        return false;
    }
    
    
    
    
    
    
//    DESCARTADAS (no borrar aun)
        
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
        
    
        
//    public static String mensajeErrorEliminacionForanea(Exception e, String entidad, String nombre) {
//        String mensaje = e.getMessage();
//        String[] palabras = {
//            "referida desde la tabla",
//            "foránea",
//            "fk",
//            "ERROR: update o delete en"
//        };
//
//        for (String keyword : palabras) {
//            if (!mensaje.contains(keyword)) {
//                return mensaje;
//            }
//        }
//        return "Hay datos externos asociados a " + entidad + " \"" + nombre + "\".\n"
//                + "Considere cambiar su disponibilidad o vigencia para que ya no pueda ser usado. ";
//    }
    
    
    
    
    

    //BLOQUEAR BOTONES
    public static void desactivarBotones(JButton botonActivo, JButton... botones) {
        for (JButton boton : botones) {
            boton.setEnabled(boton.equals(botonActivo));
        }
    }

    //ACTIVAR BOTONES
    public static void activarBotones(JButton... botones) {
        for (JButton boton : botones) {
            boton.setEnabled(true);
        }
    }

    //DESACTIVAR CAMPOS
    public static void desactivarFields(JTextField textFieldActivo, JTextField... textFields) {
        for (JTextField textField : textFields) {
            textField.setEnabled(textField.equals(textFieldActivo));
        }
    }

    //ACTIVAR BOTONES
    public static void activarFields(JTextField... textFields) {
        for (JTextField textField : textFields) {
            textField.setEnabled(true);
        }
    }

    //VERIFICAR QUE TODOS LOS CAMPOS ESTÁN COMPLETADOS
    public static boolean verificarCamposLlenos(JTextField... textFields) {
        for (JTextField textField : textFields) {
            if (textField.getText().trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    //PARA EL MOUSECLICKED
    public static void buscarPorTabla(JTable tbl, JButton btn, JTextField txt) {
        if (tbl.isEnabled() && tbl.getSelectedRow() != -1) {
            txt.setText(String.valueOf(tbl.getValueAt(tbl.getSelectedRow(), 0)));
            btn.doClick();
        }
    }

}

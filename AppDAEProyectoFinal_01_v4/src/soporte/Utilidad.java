/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package soporte;

import capaDatos.clsJDBC;
import com.toedter.calendar.JDateChooser;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.NumberFormatter;

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
    public static final String BTN_CONTRASENIA = "Cambiar Contraseña";

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
        "Cambiar Disponibilidad",
        "Cancelar"
    };

    public static final String[] opcionesModificar = {
        "Modificar",
        "Cancelar"
    };

    public static final String[] opcionesRegistrar = {
        "Registrar datos",
        "Cancelar"
    };

    public static final String[] opcionesAgregarMedicamentos = {
        "Agregar",
        "Cancelar"
    };

    public static final String[] opcionesDarAlta = {
        "Dar alta",
        "Cancelar"
    };

    public static final String[] opcionesModificarContraseña = {
        "Modificar contraseña",
        "Cancelar"
    };

    public static final String[] opcionesCancelarCita = {
        "Cancelar Cita",
        "No cancelar"
    };

//    Texto de valores Booleanos
    public static String textoBool(boolean valor, String txtTrue, String txtFalse) {
        if (valor) {
            return txtTrue;
        } else {
            return txtFalse;
        }
    }

    public static String textoFormatoFecha(String fechaOriginal) {
        if (!fechaOriginal.isBlank()){
            LocalDate fecha = LocalDate.parse(fechaOriginal);
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaFormateada = fecha.format(formato);
            return fechaFormateada;
        }
        return null;
    }
    
    public static double numeroFormato2Decimales(double number) {
        return Math.round(number * 100.0) / 100.0;
    }

    public static String numeroFormato2Decimales(String numberText) {
        try {
            double number = Double.parseDouble(numberText);
            return String.format("%.2f", number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El texto proporcionado no es un número válido: " + numberText);
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

    public static int mensajeConfirmarEliminarDetalleServicio(String nombre) {
        int valor = JOptionPane.showOptionDialog(
                null,
                "¿Está seguro que desea eliminar el servicio \"" + nombre + "?",
                "Confirmar Eliminacion",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesEliminar,
                opcionesEliminar[0]
        );
        return valor;
    }

    public static int mensajeConfirmarCancelarCita(String nombre) {
        int valor = JOptionPane.showOptionDialog(
                null,
                "¿Está seguro que desea cancelar la cita \"" + nombre + "?",
                "Confirmar Cancelación de cita",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesCancelarCita,
                opcionesCancelarCita[0]
        );
        return valor;
    }

    public static void validarCampoTextoSoloLetras(java.awt.event.KeyEvent evt) {
        char key = evt.getKeyChar();

        // Verificar si el carácter es una letra o un espacio
        if (!(Character.isLetter(key) || Character.isSpaceChar(key))) {
            evt.consume(); // Consumir evento si no es válido
        }
    }

    public static void validarSpinnerNumerosPositivos(JSpinner spn) {
        SpinnerNumberModel model = new SpinnerNumberModel(00, 00, null, 1);
        spn.setModel(model);
        JFormattedTextField txt = ((JSpinner.NumberEditor) spn.getEditor()).getTextField();
        ((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
    }
    
    public static void validarSpinnerNumerosDecimales(JSpinner spn) {
        SpinnerNumberModel model = new SpinnerNumberModel(0.00, 0.00, null, 0.10);
        spn.setModel(model);
        
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spn, "0.00");
        spn.setEditor(editor);
        
        JFormattedTextField txt = ((JSpinner.NumberEditor) spn.getEditor()).getTextField();
        ((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
    }

    public static void validarCampoTextoDocIdentidad(java.awt.event.KeyEvent evt) {
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!(numeros)) {
            evt.consume();
        }
    }

//    Mensajes 
    public static void mensajeErrorFaltaID(JDialog parent) {
        JOptionPane.showMessageDialog(
                parent,
                " Debe ingresar una ID para continuar esta operación"
        );
    }

    public static void mensajeVerificarCamposLlenos() {
        JOptionPane.showMessageDialog(
                null,
                "Debe llenar todos los campos para continuar esta operación"
        );
    }
    
    public static int mensajeConfirmarRegistro(String entidad, int id, String nombre) {
        int valor = JOptionPane.showOptionDialog(
                null,
                "¿Está seguro que desea registrar " + entidad.toLowerCase() + " \"" + nombre + "\" (ID: " + id + ")? ",
                "Confirmar registro",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesRegistrar,
                opcionesRegistrar[0]
        );
        return valor;
    }

    public static int mensajeConfirmarAgregarMedicamento(String entidad) {
        int valor = JOptionPane.showOptionDialog(
                null,
                "¿Desea agregar algún " + entidad.toLowerCase() + " ?",
                "Confirmación ",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesAgregarMedicamentos,
                opcionesAgregarMedicamentos[0]
        );
        return valor;
    }
    
    public static int mensajeConfirmar() {
        int valor = JOptionPane.showOptionDialog(
                null,
                "¿Los datos son correctos?",
                "Confirmación ",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesAgregarMedicamentos,
                opcionesAgregarMedicamentos[0]
        );
        return valor;
    }

    public static int mensajeConfirmarAgregarServicio(String entidad) {
        int valor = JOptionPane.showOptionDialog(
                null,
                "¿Desea agregar el " + entidad.toLowerCase() + " ?",
                "Confirmación ",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesAgregarMedicamentos,
                opcionesAgregarMedicamentos[0]
        );
        return valor;
    }

    public static int mensajeConfirmarEliminar(String entidad, int id, String nombre) {
        int valor = JOptionPane.showOptionDialog(
                null,
                "¿Está seguro que desea eliminar " + entidad.toLowerCase() + " \"" + nombre + "\" (ID: " + id + ")? ",
                "Confirmar Eliminacion",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesEliminar,
                opcionesEliminar[0]
        );
        return valor;
    }

    public static int mensajeConfirmarDarAlta(String entidad, int id, String nombre) {
        int valor = JOptionPane.showOptionDialog(
                null,
                "¿Está seguro que desea dar alta " + entidad.toLowerCase() + " \"" + nombre + "\" (ID: " + id + ")? ",
                "Confirmar Dar de Alta",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesDarAlta,
                opcionesDarAlta[0]
        );
        return valor;
    }

    public static int mensajeConfirmarModificar(String entidad, int id, String nombre) {
        int valor = JOptionPane.showOptionDialog(
                null,
                "¿Está seguro de guardar los cambios en " + entidad.toLowerCase() + " \"" + nombre + "\" (ID: " + id + ")? ",
                "Confirmar Modificación",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesModificar,
                opcionesModificar[0]
        );
        return valor;
    }

    public static int mensajeConfirmarDisponibilidad(String entidad, int id, String nombre) {
        int valor = JOptionPane.showOptionDialog(
                null,
                "¿Está seguro cambiar la disponibilidad de " + entidad.toLowerCase() + " \"" + nombre + "\" (ID: " + id + ")? ",
                "Confirmar Cambio de Disponibilidad",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesDisponibilidad,
                opcionesDisponibilidad[0]
        );
        return valor;
    }

    public static int mensajeConfirmarVigencia(String entidad, int id, String nombre) {
        int valor = JOptionPane.showOptionDialog(
                null,
                "¿Está seguro dar de baja " + entidad.toLowerCase() + " \"" + nombre + "\" (ID: " + id + ")? ",
                "Confirmar Bar de Baja",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesVigencia,
                opcionesVigencia[0]
        );
        return valor;
    }

    public static int mensajeConfirmarModificarContraseña(String entidad, int id, String nombre) {
        int valor = JOptionPane.showOptionDialog(
                null,
                "¿Está seguro de modificar contraseña " + entidad.toLowerCase() + " \"" + nombre + "\" (ID: " + id + ")? ",
                "Confirmar modificación de contraseña",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesModificarContraseña,
                opcionesModificarContraseña[0]
        );
        return valor;
    }

    public static void mensajeExitoCambioDisponibilidad(String entidad) {
        JOptionPane.showMessageDialog(
                null,
                "Se modificó la disponibilidad de " + entidad.toLowerCase() + " con exito"
        );
    }

    public static void mensajeErrorNoEliminarForanea(String entidad, String nombre) {
        JOptionPane.showMessageDialog(
                null,
                "Hay datos externos asociados a " + entidad.toLowerCase() + " \"" + nombre + "\".\n"
                + "Considere cambiar su disponibilidad o vigencia para que ya no pueda ser usado. "
        );
    }

    public static void mensajeElementoNoVigente(String entidad, String nombre) {
        JOptionPane.showMessageDialog(
                null,
                "La informacion del " + entidad.toLowerCase() + " \"" + nombre + "\" no se encuentra vigente para esta operación. "
        );
    }
    
    public static final String[] opcionesAgregar = {
        "Agregar",
        "Cancelar"
    };

    
    public static Object mensajeInputSpinnerDecimal(String titulo , String texto){
        JSpinner spinner = new JSpinner();
        validarSpinnerNumerosDecimales(spinner);

        Object[] message = {
            texto, 
            spinner
        };

        // Mostrar el JOptionPane con el componente personalizado
        int option = JOptionPane.showOptionDialog(
            null, 
            message, 
            titulo, 
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opcionesAgregar,
            opcionesAgregar[0]
        );

        if (option == 0) {            
            return spinner.getValue();
        }
        return null;
    }
    
    public static Object mensajeInputSpinnerEntero(String titulo , String texto){
        JSpinner spinner = new JSpinner();
        validarSpinnerNumerosPositivos(spinner);

        Object[] message = {
            texto, 
            spinner
        };

        // Mostrar el JOptionPane con el componente personalizado
        int option = JOptionPane.showOptionDialog(
            null, 
            message, 
            titulo, 
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opcionesAgregar,
            opcionesAgregar[0]
        );

        if (option == 0) {            
            return spinner.getValue();
        }
        return null;
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
            throw new Exception("Error al validar si elemento ID: " + valor_id + " en la tabla " + tabla.toLowerCase() + " / " + e.getMessage());
        }
        return false;
    }

    public static boolean validarEliminacionForaneaCompuesta(String tabla, int valor_id1, int valor_id2) throws Exception {
        clsJDBC objConectar = new clsJDBC();
        String strSQL;
        ResultSet rs = null;

        strSQL = " select sum(cantidad) as total from contar_relaciones_compuestas('" + tabla + "'," + valor_id1 + " , " + valor_id2 + ") ";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                int total = rs.getInt("total");
                if (total > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            throw new Exception("Error al validar si elemento ID: " + valor_id1 + " con ID: " + valor_id2 + " en la tabla " + tabla.toLowerCase() + " / " + e.getMessage());
        }
        return false;
    }

    public static boolean validarElementoTextoRepetido(String tabla, String columna, String campo) throws Exception {
        clsJDBC objConectar = new clsJDBC();
        String strSQL;
        ResultSet rs = null;

        strSQL = " select * from " + tabla
                + " where LOWER(" + columna + ") = LOWER('" + campo + "') ";

        try {
            rs = objConectar.consultarBD(strSQL);
            return rs.next();
        } catch (Exception e) {
            throw new Exception("Error al buscar Elemento " + campo.toLowerCase() + " en la tabla " + tabla.toLowerCase() + " / " + e.getMessage());
        }
    }

    public static boolean verificarElementoParaUso(String tabla, String columna, Integer id) throws Exception {
        clsJDBC objConectar = new clsJDBC();
        ResultSet rs;
        try {
            rs = objConectar.consultarBD("select " + columna + " from " + tabla + " where id = " + id);
            while (rs.next()) {
                return !rs.getBoolean(columna);
            }
        } catch (Exception e) {
            throw new Exception("Error al verificar " + columna.toLowerCase() + " de ID: " + id + " en tabla " + tabla.toLowerCase() + ": " + e.getMessage());
        }
        return false;
    }

    public static boolean verificarElementoParaUsoCodigo(String tabla, String columna, String columna_codigo, Integer id) throws Exception {
        clsJDBC objConectar = new clsJDBC();
        ResultSet rs;
        try {
            rs = objConectar.consultarBD("select " + columna + " from " + tabla + " where " + columna_codigo + " = " + id);
            while (rs.next()) {
                return !rs.getBoolean(columna);
            }
        } catch (Exception e) {
            throw new Exception("Error al verificar " + columna.toLowerCase() + " de ID: " + id + " en tabla " + tabla.toLowerCase() + ": " + e.getMessage());
        }
        return false;
    }

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
            textField.setEditable(textField.equals(textFieldActivo));
        }
    }

    //ACTIVAR CAMPOS
    public static void activarFields(JTextField... textFields) {
        for (JTextField textField : textFields) {
            textField.setEditable(true);
        }
    }

    //VERIFICAR QUE TODOS LOS CAMPOS ESTÁN COMPLETADOS
    public static boolean verificarCamposLlenos(JTextField... textFields) {
        for (JTextField textField : textFields) {
            if (textField.getText().trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    //PARA EL MOUSECLICKED
    public static void buscarPorTabla(JTable tbl, JButton btn, JTextField txt) {
        if (tbl.isEnabled() && tbl.getSelectedRow() != -1) {
            txt.setText(String.valueOf(tbl.getValueAt(tbl.getSelectedRow(), 0)));
            btn.doClick();
        }
    }

    public static void validarFechasFuturas(JDateChooser dateFecha , java.beans.PropertyChangeEvent evt) {
        if ("date".equals(evt.getPropertyName())) {
            if (dateFecha.getDate() == null) {
                return;
            }
            Date fechaSeleccionada = dateFecha.getDate();
            Date fechaActual = new Date();

            if (fechaSeleccionada.after(fechaActual)) {
                JOptionPane.showMessageDialog(null, "En este campo no es permitido ingresar fechas futuras");
                dateFecha.setDate(null);
            }
        }
    }
    
    public static void validacionTabla(JTable table, boolean desactivarReordenacion, boolean desactivarModificacionCabecera, boolean desactivarEdicion) {
        // Desactivar la reordenación de columnas si es necesario
        if (desactivarReordenacion) {
            table.getTableHeader().setReorderingAllowed(false);
        }

        // Desactivar la reordenación y modificación de la cabecera de columnas si es necesario
        if (desactivarModificacionCabecera) {
            table.getTableHeader().setReorderingAllowed(false);
            table.getTableHeader().setResizingAllowed(false);
        }

        // Desactivar la edición de celdas si es necesario
        if (desactivarEdicion) {
            table.setDefaultEditor(Object.class, null);
        }
    }

    public static void alineacionDerechaColumnaTabla(JTable table, int columnIndex) {
//        TableCellRenderer renderer = table.getDefaultRenderer(Object.class);

        // Configurar el renderer para la columna específica
        DefaultTableCellRenderer alignRenderer = new DefaultTableCellRenderer();
        alignRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

        TableColumn columna = table.getColumnModel().getColumn(columnIndex);
        columna.setPreferredWidth(Math.round(5 / 100 * table.getWidth()));
        
        table.getColumnModel().getColumn(columnIndex).setCellRenderer(alignRenderer);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    public static void alineacionDerechaColumnaTabla(JTable table, int columnIndex , int porcentaje) {
//        TableCellRenderer renderer = table.getDefaultRenderer(Object.class);

        // Configurar el renderer para la columna específica
        DefaultTableCellRenderer alignRenderer = new DefaultTableCellRenderer();
        alignRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

        TableColumn columna = table.getColumnModel().getColumn(columnIndex);
        columna.setPreferredWidth(Math.round(porcentaje / 100 * table.getWidth()));
        
        table.getColumnModel().getColumn(columnIndex).setCellRenderer(alignRenderer);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    public static void tamañoColumnaTablaxPos(JTable table, int columnIndex , int ancho) {
        table.getColumnModel().getColumn(columnIndex).setPreferredWidth(ancho);
    }
    
//   
//     public static void tamañoColumnaTablaxNombre(JTable table, String nombreColumn , int ancho) {
//        table.getColumnModel().getColumn(0).getHeaderValue().toString().equals(nombreColumn);
//}
//    
    
    public static void atajoTecladoBoton(JDialog dialog, JButton boton, char tecla, String nombreAccion) {
        // Para ejecutar el botón con CTRL + tecla
        dialog.getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(javax.swing.KeyStroke.getKeyStroke(tecla, java.awt.event.InputEvent.CTRL_DOWN_MASK), nombreAccion);

        dialog.getRootPane().getActionMap().put(nombreAccion, new javax.swing.AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                boton.doClick(); // Simula clic en el botón
            }
        });
    }





}

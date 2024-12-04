/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package capaPresentacion;

import capaNegocio.clsCita;
import capaNegocio.clsComprobante;
import capaNegocio.clsDetalleCita;
import capaNegocio.clsDetalleMedicamento;
import capaNegocio.clsDetalle_Servicio;
import capaNegocio.clsDuenio;
import capaNegocio.clsEstadoCita;
import capaNegocio.clsMascota;
import capaNegocio.clsMedicamento;
import capaNegocio.clsMedico;
import capaNegocio.clsRaza;
import capaNegocio.clsServicio;
import java.awt.Frame;
import javax.swing.SwingUtilities;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.bcel.generic.AALOAD;
import org.hibernate.cfg.annotations.reflection.XMLContext;
import soporte.*;

/**
 *
 * @author Junior
 */
public class jdCita_v2 extends javax.swing.JDialog {

    clsMedicamento objMedicamento = new clsMedicamento();
    clsCita objCita = new clsCita();
    clsDuenio objDuenio = new clsDuenio();
    clsMedico objMedico = new clsMedico();
    clsServicio objServicio = new clsServicio();
    clsMascota objMascota = new clsMascota();
    clsEstadoCita objEstadoCita = new clsEstadoCita();
    clsDetalleCita objDetalleCita = new clsDetalleCita();
    clsRaza objRaza = new clsRaza();
    clsDetalle_Servicio objDetalleServicio = new clsDetalle_Servicio();
    clsDetalleMedicamento objDetalleMedicamento = new clsDetalleMedicamento();
    clsComprobante objComprobante = new clsComprobante();

//    int codTablaServicio = -1;
//    int codTablaMedico = -1;
    public jdCita_v2(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        llenarTablaCitasPendientes();

        llenarCboServicios();
        llenarCboEstadoCita();
        this.setTitle("Gestión de Cita");

        llenarTablaInicialServicio();
        llenarTablaInicialMedicamento();
        cboMedicos.removeAllItems();

        actualizarResumenes();
        calcularTotales();

        txtDocMedico.setText("");

    }

    private void llenarCboEstadoCita() {
        ResultSet rsEstadoCita = null;
        DefaultComboBoxModel modeloSer = new DefaultComboBoxModel();
        cboEstadoCita.setModel(modeloSer);

        try {
            rsEstadoCita = objEstadoCita.listarEstadoCita();

            while (rsEstadoCita.next()) {
                modeloSer.addElement(rsEstadoCita.getString("nombre_estado"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Error al listar en interfaz los estados");
        }
    }

    private void llenarTablaInicialMedicamento() {
        // Crear el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID MEDICAMENTO");
        modelo.addColumn("ID SERVICIO");
        modelo.addColumn("ID MEDICO");
        modelo.addColumn("MEDICAMENTO");
        modelo.addColumn("DOSIS");
        modelo.addColumn("INDICACIÓN");
        modelo.addColumn("CANTIDAD");
        modelo.addColumn("COSTO");

        tblDetalleMedicamento.setModel(modelo);

        ocultarColumna(tblDetalleMedicamento, 0);  // Ocultar "ID MEDICAMENTO"
        ocultarColumna(tblDetalleMedicamento, 1);  // Ocultar "ID SERVICIO"
        ocultarColumna(tblDetalleMedicamento, 2);
        tblDetalleMedicamento.getColumnModel().getColumn(3).setPreferredWidth(250);

    }

    private void ocultarColumna(JTable tabla, int indiceColumna) {
        tabla.getColumnModel().getColumn(indiceColumna).setMaxWidth(0);
        tabla.getColumnModel().getColumn(indiceColumna).setMinWidth(0);
        tabla.getColumnModel().getColumn(indiceColumna).setWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(indiceColumna).setMaxWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(indiceColumna).setMinWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(indiceColumna).setWidth(0);
    }

    private void llenarCboServicios() {
        ResultSet rsServicios = null;
        DefaultComboBoxModel modeloSer = new DefaultComboBoxModel();
        cboServicios.setModel(modeloSer);
        cboServicios_PANEL_MEDIC.setModel(modeloSer);

        try {
            rsServicios = objServicio.listarServicios();

            while (rsServicios.next()) {
                modeloSer.addElement(rsServicios.getString("nom_servicio"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Error al listar en interfaz los servicios");
        }
    }

    private void llenarCboMedicosPorServicio() {
        ResultSet rsMedicos = null;
        DefaultComboBoxModel modeloSer = new DefaultComboBoxModel();
        cboMedicos.setModel(modeloSer);
//        cboServicios_PANEL_MEDIC.setModel(modeloSer);

        try {
            rsMedicos = objMedico.buscarMedicosPorServicio(Integer.parseInt(txtCodServicio.getText()));

            while (rsMedicos.next()) {
                modeloSer.addElement(rsMedicos.getString("nombres") + " " + rsMedicos.getString("apepaterno") + " " + rsMedicos.getString("apematerno"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Error al listar medicos por servicio");
        }
    }

    private void llenarTablaInicialServicio() {

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID SERV_MED");
        modelo.addColumn("SERVICIO");
        modelo.addColumn("MEDICO");
        modelo.addColumn("H.ENTRADA");
        modelo.addColumn("H. SALIDA");
        modelo.addColumn("COSTO");

        tblDetalleServicio.setModel(modelo);

        tblDetalleServicio.getColumnModel().getColumn(0).setMaxWidth(0);
        tblDetalleServicio.getColumnModel().getColumn(0).setMinWidth(0);
        tblDetalleServicio.getColumnModel().getColumn(0).setWidth(0);
        tblDetalleServicio.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tblDetalleServicio.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        tblDetalleServicio.getTableHeader().getColumnModel().getColumn(0).setWidth(0);

        tblDetalleServicio.getColumnModel().getColumn(2).setPreferredWidth(120);
        tblDetalleServicio.getColumnModel().getColumn(3).setPreferredWidth(5); // HORA ENTRADA
        tblDetalleServicio.getColumnModel().getColumn(4).setPreferredWidth(5); // HORA SALIDA
        tblDetalleServicio.getColumnModel().getColumn(5).setPreferredWidth(5); // COSTO

        tblDetalleServicio.getTableHeader().setReorderingAllowed(false); //no mover los headers

        llenarTablaDetalleServicio_PANEL_MEDIC();
    }

    private void llenarTablaInicialServicio2() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID SERV_MED");
        modelo.addColumn("SERVICIO");
        modelo.addColumn("MEDICO");
        modelo.addColumn("H.ENTRADA");
        modelo.addColumn("H. SALIDA");
        modelo.addColumn("COSTO");

        tblDetalleServicio.setModel(modelo);

        tblDetalleServicio.getColumnModel().getColumn(0).setMaxWidth(0);
        tblDetalleServicio.getColumnModel().getColumn(0).setMinWidth(0);
        tblDetalleServicio.getColumnModel().getColumn(0).setWidth(0);
        tblDetalleServicio.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tblDetalleServicio.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        tblDetalleServicio.getTableHeader().getColumnModel().getColumn(0).setWidth(0);

        tblDetalleServicio.getColumnModel().getColumn(2).setPreferredWidth(120);
        tblDetalleServicio.getColumnModel().getColumn(3).setPreferredWidth(5); // HORA ENTRADA
        tblDetalleServicio.getColumnModel().getColumn(4).setPreferredWidth(5); // HORA SALIDA
        tblDetalleServicio.getColumnModel().getColumn(5).setPreferredWidth(5); // COSTO

        tblDetalleServicio.getTableHeader().setReorderingAllowed(false); //no mover los headers
        llenarTablaDetalleServicio_PANEL_MEDIC();
    }

    private void llenarServicioMedico() {
        ResultSet rsSer;
        ResultSet rsMed;
        String cadena = String.valueOf(tblDetalleServicio.getValueAt(tblDetalleServicio.getSelectedRow(), 0));
        String[] codigos = cadena.split(" - ");
        int codigoTablaSer = Integer.parseInt(codigos[0].trim());
        int codTablaMed = Integer.parseInt(codigos[1].trim());

        try {
            rsSer = objServicio.buscarServicio(codigoTablaSer);
            rsMed = objMedico.buscarMedico(codTablaMed);

            if (rsSer.next()) {
                cboServicios.setSelectedItem(rsSer.getString("nom_servicio"));
            }

            if (rsMed.next()) {
                txtDocMedico.setText(rsMed.getString("doc_identidad"));
                cboMedicos.setSelectedItem(rsMed.getString("nombres") + " " + rsMed.getString("apepaterno") + " " + rsMed.getString("apematerno"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Error al llenar campos SER-MED " + e.getMessage());
        }

    }

    private void llenarTablaDetalleServicio_PANEL_MEDIC() {
        DefaultTableModel modeloOriginal = (DefaultTableModel) tblDetalleServicio.getModel();

        // Crear un nuevo modelo para la tabla tblDetalleServicio_PANEL_MEDIC
        DefaultTableModel modeloPanelMedicam = new DefaultTableModel();

        // Añadir las columnas correspondientes al nuevo modelo
        modeloPanelMedicam.addColumn(modeloOriginal.getColumnName(0));
        modeloPanelMedicam.addColumn(modeloOriginal.getColumnName(1));
        modeloPanelMedicam.addColumn(modeloOriginal.getColumnName(2));

        for (int i = 0; i < modeloOriginal.getRowCount(); i++) {
            Object[] fila = new Object[3];
            fila[0] = modeloOriginal.getValueAt(i, 0);
            fila[1] = modeloOriginal.getValueAt(i, 1);
            fila[2] = modeloOriginal.getValueAt(i, 2);
            modeloPanelMedicam.addRow(fila);
        }

        tblDetalleServicio_PANEL_MEDIC.setModel(modeloPanelMedicam);

        tblDetalleServicio_PANEL_MEDIC.getColumnModel().getColumn(0).setMaxWidth(0);
        tblDetalleServicio_PANEL_MEDIC.getColumnModel().getColumn(0).setMinWidth(0);
        tblDetalleServicio_PANEL_MEDIC.getColumnModel().getColumn(0).setWidth(0);
        tblDetalleServicio_PANEL_MEDIC.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tblDetalleServicio_PANEL_MEDIC.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        tblDetalleServicio_PANEL_MEDIC.getTableHeader().getColumnModel().getColumn(0).setWidth(0);
    }

    private void cancelarCita() {
        try {
            int valor = Utilidad.mensajeConfirmarCancelarCita("Cita");

            if (valor == 0) {
                objCita.cancelarCita(Integer.parseInt(txtNumero.getText()));
//                btnBuscarCitaActionPerformed(null);
                limpiarTodoAlFinalizar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudo cancelar la cita " + e.getMessage());
        }
    }

    private void llenarTablaCitasPendientes() {
        ResultSet rsPendientes;

        try {
            rsPendientes = objCita.listarCitasPendientesOrdenadas();
            DefaultTableModel modelo = new DefaultTableModel();

            modelo.addColumn("ID");
            modelo.addColumn("DNI");
            modelo.addColumn("FECHA");

            while (rsPendientes.next()) {
                Object[] fila = new Object[3];
                fila[0] = rsPendientes.getString("id_cita");
                fila[1] = rsPendientes.getString("doc_identidad");
                fila[2] = rsPendientes.getDate("fecha_cita");

                modelo.addRow(fila);
            }

            tblCitasPendientes.setModel(modelo);

//            tblCitasPendientes.getColumnModel().getColumn(0).setPreferredWidth(100);
//            tblCitasPendientes.getColumnModel().getColumn(1).setPreferredWidth(150);
//            tblCitasPendientes.getColumnModel().getColumn(0).setMaxWidth(0);
//            tblCitasPendientes.getColumnModel().getColumn(0).setMinWidth(0);
//            tblCitasPendientes.getColumnModel().getColumn(0).setWidth(0);
//            tblCitasPendientes.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
//            tblCitasPendientes.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
//            tblCitasPendientes.getTableHeader().getColumnModel().getColumn(0).setWidth(0);
            ocultarColumna(tblCitasPendientes, 0);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage() + "...");
        }
    }

    private void actualizarResumenes() {
        DefaultTableModel model = (DefaultTableModel) tblDetalleServicio.getModel();
        tblServicioResumen.setModel(model);

        ocultarColumna(tblServicioResumen, 0);
        ocultarColumna(tblServicioResumen, 3);
        ocultarColumna(tblServicioResumen, 4);

        DefaultTableModel modeloMed = (DefaultTableModel) tblDetalleMedicamento.getModel();
        tblMedicamentoResumen.setModel(modeloMed);

        ocultarColumna(tblMedicamentoResumen, 0);
        ocultarColumna(tblMedicamentoResumen, 1);
        ocultarColumna(tblMedicamentoResumen, 2);
        ocultarColumna(tblMedicamentoResumen, 4);
        ocultarColumna(tblMedicamentoResumen, 5);
    }

    private void llenarTablaCitasPendientesDNI() {
        ResultSet rsPendientes;
        try {
            rsPendientes = objCita.listarCitasPendientesPorDNI(txtDNITabla.getText());

            DefaultTableModel modelo = new DefaultTableModel();

            modelo.addColumn("ID");
            modelo.addColumn("DNI");
            modelo.addColumn("FECHA");

            while (rsPendientes.next()) {
                Object[] fila = new Object[3];
                fila[0] = rsPendientes.getString("id_cita");
                fila[1] = rsPendientes.getString("doc_identidad");
                fila[2] = rsPendientes.getDate("fecha_cita");

                modelo.addRow(fila);
            }

            tblCitasPendientes.setModel(modelo);

//            tblCitasPendientes.getColumnModel().getColumn(0).setPreferredWidth(100);
//            tblCitasPendientes.getColumnModel().getColumn(1).setPreferredWidth(150);
//            tblCitasPendientes.getColumnModel().getColumn(0).setMaxWidth(0);
//            tblCitasPendientes.getColumnModel().getColumn(0).setMinWidth(0);
//            tblCitasPendientes.getColumnModel().getColumn(0).setWidth(0);
//            tblCitasPendientes.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
//            tblCitasPendientes.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
//            tblCitasPendientes.getTableHeader().getColumnModel().getColumn(0).setWidth(0);
            ocultarColumna(tblCitasPendientes, 0);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage() + "...");
        }
    }

    private void llenarTablitaServicios() {
        try {
            ResultSet rsDetalleCitaEncontrado = null;
            DefaultTableModel modelo = (DefaultTableModel) tblDetalleServicio.getModel();
            modelo.setRowCount(0);

            rsDetalleCitaEncontrado = objDetalleCita.buscarDetalleCita(Integer.parseInt(txtNumero.getText()));

            while (rsDetalleCitaEncontrado.next()) {
                String idServMed = rsDetalleCitaEncontrado.getInt("detalle_servicio_serv_id") + " - " + rsDetalleCitaEncontrado.getInt("detalle_servicio_med_id");
                String servicio = rsDetalleCitaEncontrado.getString("servicio_nombre");
                String medico = rsDetalleCitaEncontrado.getString("medico_nombres") + " "
                        + rsDetalleCitaEncontrado.getString("medico_apPaterno") + " "
                        + rsDetalleCitaEncontrado.getString("medico_apMaterno");
                String horaEntrada = rsDetalleCitaEncontrado.getString("horaEntrada");
                String horaSalida = rsDetalleCitaEncontrado.getString("horaSalida");
                double costo = rsDetalleCitaEncontrado.getDouble("costo");

                modelo.addRow(new Object[]{idServMed, servicio, medico, horaEntrada, horaSalida, costo});
            }

            tblDetalleServicio.setModel(modelo);

            calcularTotales();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private int obtenerIdMedico(String docMedico) throws Exception {
        return objMedico.obtenerIDconDoc(docMedico);
    }

    private void agregarDetalleCita(String horaE, String horaS, String notita) {
        try {
            String docMedico = txtDocMedico.getText().trim();

            if (docMedico.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar el documento de identidad del médico.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idMedico = obtenerIdMedico(docMedico);
            if (idMedico == 0) {
                JOptionPane.showMessageDialog(this, "No se encontró un médico con el documento proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idServicio = Integer.parseInt(txtCodServicio.getText().trim());

            if (!objDetalleServicio.existeDetalleServicio(idServicio, idMedico)) {
                JOptionPane.showMessageDialog(this, "El médico no ofrece el servicio seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

//            String horaInicio = JOptionPane.showInputDialog(this, "Ingrese la hora de inicio (HH:mm):");
//            if (horaInicio == null || horaInicio.trim().isEmpty()) {
//                JOptionPane.showMessageDialog(this, "Debe ingresar una hora de inicio.", "Error", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//
//            String horaFin = JOptionPane.showInputDialog(this, "Ingrese la hora de fin (HH:mm):");
//            if (horaFin == null || horaFin.trim().isEmpty()) {
//                JOptionPane.showMessageDialog(this, "Debe ingresar una hora de fin.", "Error", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//
//            String notaAdicional = JOptionPane.showInputDialog(this, "Ingrese una nota adicional (opcional):");
//            if (notaAdicional == null) {
//                notaAdicional = ""; // Establece un valor por defecto si se cancela el cuadro de diálogo
//            }
            String horaInicio = horaE;
            String horaFin = horaS;
            String notaAdicional = notita;

            objDetalleCita.insertarDetalleServicioSiNoExiste(
                    Integer.parseInt(txtNumero.getText().trim()),
                    tblDetalleServicio,
                    Integer.parseInt(txtCodServicio.getText()),
                    objMedico.obtenerIDconDoc(docMedico),
                    horaInicio,
                    horaFin,
                    notaAdicional
            );

            JOptionPane.showMessageDialog(this, "Detalle de cita agregado correctamente.");
            llenarTablitaServicios();
            calcularTotales();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error en el formato del ID o servicio. Asegúrese de que sean números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al agregar el detalle de cita: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void llenarDatos_PANEL_MEDIC(int codigoTablaSer, int codTablaMed) {

        ResultSet rsSer;
        ResultSet rsMed;

        try {
            rsSer = objServicio.buscarServicio(codigoTablaSer);
            rsMed = objMedico.buscarMedico(codTablaMed);

            if (rsSer.next()) {
                cboServicios_PANEL_MEDIC.setSelectedItem(rsSer.getString("nom_servicio"));
            }

            if (rsMed.next()) {
                txtNombreMedico1_PANEL_MEDIC.setText(rsMed.getString("nombres") + " " + rsMed.getString("apepaterno") + " " + rsMed.getString("apematerno"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Error al llenar campos SER-MED " + e.getMessage());
        }
    }

    private void agregarMedicamento(int medicamento, int cantidad, float dosis, String indicacion) {
        ResultSet rs = null; //aqui hay problema

        String cadena = String.valueOf(tblDetalleServicio_PANEL_MEDIC.getValueAt(tblDetalleServicio_PANEL_MEDIC.getSelectedRow(), 0));
        if (!cadena.isEmpty()) {
            try {
                String[] codigos = cadena.split(" - ");
                if (codigos.length == 2) {
                    int codigoTablaSer = Integer.parseInt(codigos[0].trim());
                    int codTablaMed = Integer.parseInt(codigos[1].trim());
                    // Aquí puedes usar los valores de codigoTablaSer y codTablaMed
                } else {
                    System.out.println("La cadena no tiene el formato esperado.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error al convertir los códigos a enteros: " + e.getMessage());
            }

            try {
                // Verifica que los parámetros sean válidos
                if (medicamento != 0 && cantidad != 0 && dosis != 0) {
                    // Confirmación del usuario
                    if (JOptionPane.showConfirmDialog(this, "¿Los datos son correctos?", "Confirmar",
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                        int stock = objMedicamento.getStock(medicamento);
                        DefaultTableModel modelito = (DefaultTableModel) tblDetalleMedicamento.getModel();
                        int idMed = objMedico.obtenerIDconDoc(txtDocMedico.getText());
                        System.out.println("funka antes validar");

                        if (modelito.getRowCount() != 0) {
                            for (int i = modelito.getRowCount() - 1; i >= 0; i--) {
                                int medicamento_id = Integer.parseInt(tblDetalleMedicamento.getValueAt(i, 0).toString());
                                int servicio_id = Integer.parseInt(tblDetalleMedicamento.getValueAt(i, 1).toString());
                                int medico_id = Integer.parseInt(tblDetalleMedicamento.getValueAt(i, 2).toString());

                                if (medicamento_id == medicamento
                                        && servicio_id == objServicio.obtenerID(cboServicios_PANEL_MEDIC.getSelectedItem().toString())
                                        && medico_id == idMed) {
                                    modelito.removeRow(i);
                                    break;
                                }
                            }
                        }
                        System.out.println("funka");
                        // Busca el medicamento en la base de datos para obtener su costo
                        rs = objMedicamento.buscarMedicamento(medicamento);
                        if (rs.next()) {
                            // Agrega nueva fila con los datos del medicamento
                            modelito.addRow(new Object[]{
                                medicamento, // Código de medicamento
                                txtCodServicio.getText(), // Código de servicio
                                idMed, // ID del médico
                                rs.getString("nombre"),
                                dosis, // Dosis
                                indicacion, // Indicaciones
                                cantidad, // Cantidad
                                rs.getFloat("costo") // Costo del medicamento
                            });
                        }

                        tblDetalleMedicamento.setModel(modelito); // Actualiza el modelo de la tabla
                    }
                }
                calcularTotales();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage() + " agregar medicamento");
            }
        }
    }

    private void limpiarTodoAlFinalizar() {
        txtANotaAdicional.setText("");
        txtCodMascota.setText("");
        txtCodMedicamento.setText("");
        txtCodServicio.setText("");
        txtDocMedico.setText("");
        txtDosis.setText("");

        txtIgv.setText("");
        txtIndicacion.setText("");
        txtNombreDuenio.setText("");
        txtNombreMascota.setText("");
        txtNombreMedicamento.setText("");

        txtNotaMascota.setText("");
        txtNumero.setText("");

        txtSubtotal.setText("");
        txtTelefono.setText("");
        txtTotal.setText("");

        spnCantidad.setValue(0);
        jDateChooser1.setDate(null);

        llenarTablaInicialServicio();
        llenarTablaInicialMedicamento();
        llenarTablaCitasPendientes();

//        cboEstadoCita.setSelectedIndex(-1);
//        cboServicios.setSelectedIndex(-1);
    }

    private void eliminarMedicamentoDeTabla() {
//        int codMed = Integer.parseInt(txtCodMedicamento.getText());

        DefaultTableModel modeloMedic = (DefaultTableModel) tblDetalleMedicamento.getModel();

        modeloMedic.removeRow(tblDetalleMedicamento.getSelectedRow());

    }

    private void calcularTotales() {
        double subtotal = 0.0;
        double igv;
        double total;

        // Sumar costos de los servicios en la tabla tblDetalleServicio
        DefaultTableModel modeloServicios = (DefaultTableModel) tblDetalleServicio.getModel();
        for (int i = 0; i < modeloServicios.getRowCount(); i++) {
            double costoServicio = Double.parseDouble(modeloServicios.getValueAt(i, 5).toString());
            subtotal += costoServicio;
        }

        // Sumar costos de los medicamentos en la tabla tblDetalleMedicamento
        DefaultTableModel modeloMedicamentos = (DefaultTableModel) tblDetalleMedicamento.getModel();
        for (int i = 0; i < modeloMedicamentos.getRowCount(); i++) {
            double costoMedicamento = Double.parseDouble(modeloMedicamentos.getValueAt(i, 7).toString());
            int cantidad = Integer.parseInt(modeloMedicamentos.getValueAt(i, 6).toString());
            subtotal += costoMedicamento * cantidad;
        }

        // Calcular IGV y total
        igv = subtotal * 0.18;
        total = subtotal + igv;

        // Mostrar en los campos de texto correspondientes
        txtSubtotal.setText(String.format("%.2f", subtotal));
        txtIgv.setText(String.format("%.2f", igv));
        txtTotal.setText(String.format("%.2f", total));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        btnBuscarCita = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cboEstadoCita = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCitasPendientes = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtDNITabla = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtCodServicio = new javax.swing.JTextField();
        txtDocMedico = new javax.swing.JTextField();
        btnEliminarServicio = new javax.swing.JButton();
        btnAgregarServicio = new javax.swing.JButton();
        cboServicios = new javax.swing.JComboBox<>();
        cboMedicos = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtNombreDuenio = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        txtCodMascota = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtNombreMascota = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtNotaMascota = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDetalleServicio = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        btnTerminar2 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        txtANotaAdicional = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel23 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDetalleServicio_PANEL_MEDIC = new javax.swing.JTable();
        jLabel31 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtNombreMedico1_PANEL_MEDIC = new javax.swing.JTextField();
        cboServicios_PANEL_MEDIC = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtCodMedicamento = new javax.swing.JTextField();
        txtNombreMedicamento = new javax.swing.JTextField();
        btnBuscarMedicamento = new javax.swing.JButton();
        txtIndicacion = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtDosis = new javax.swing.JTextField();
        spnCantidad = new javax.swing.JSpinner();
        btnEliminarMedicamento = new javax.swing.JButton();
        btnAgregarMedicamento = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDetalleMedicamento = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        btnTerminar3 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblMedicamentoResumen = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblServicioResumen = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JTextField();
        txtIgv = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtANotaAdicional1 = new javax.swing.JTextArea();
        btnTerminar = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        lblCitaPersona = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));

        jLabel1.setText("Nro de cita:");

        btnBuscarCita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/buscar-pequeño.png"))); // NOI18N
        btnBuscarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCitaActionPerformed(evt);
            }
        });

        jLabel3.setText("Estado:");

        cboEstadoCita.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboEstadoCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboEstadoCitaActionPerformed(evt);
            }
        });

        jLabel5.setText("Fecha");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscarCita, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboEstadoCita, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(cboEstadoCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addComponent(btnBuscarCita))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(204, 255, 255));

        tblCitasPendientes.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tblCitasPendientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblCitasPendientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCitasPendientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCitasPendientes);

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel2.setText("DNI:");

        txtDNITabla.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtDNITabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDNITablaActionPerformed(evt);
            }
        });
        txtDNITabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDNITablaKeyTyped(evt);
            }
        });

        btnBuscar.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDNITabla, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDNITabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel27.setText("Servicio:");

        jLabel28.setText("Médico:");

        btnEliminarServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/eliminar.png"))); // NOI18N
        btnEliminarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarServicioActionPerformed(evt);
            }
        });

        btnAgregarServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/veterinario.png"))); // NOI18N
        btnAgregarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarServicioActionPerformed(evt);
            }
        });

        cboServicios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboServicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboServiciosActionPerformed(evt);
            }
        });

        cboMedicos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboMedicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMedicosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addComponent(jLabel27))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(txtDocMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCodServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboMedicos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboServicios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnAgregarServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEliminarServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregarServicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(txtCodServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboServicios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(txtDocMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnEliminarServicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setText("Dueño:");

        jLabel13.setText("Teléfono:");

        jLabel12.setText("Nota:");

        jLabel14.setText("Mascota:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNombreDuenio, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodMascota, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNombreMascota, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNotaMascota)
                    .addComponent(txtTelefono))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreDuenio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtNombreMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtNotaMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        tblDetalleServicio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblDetalleServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetalleServicioMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDetalleServicio);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        btnTerminar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/flecha derecha.png"))); // NOI18N
        btnTerminar2.setText("Ir a resumen");
        btnTerminar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerminar2ActionPerformed(evt);
            }
        });

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/eliminar.png"))); // NOI18N
        jButton9.setText("Cancelar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/salir.png"))); // NOI18N
        jButton10.setText("Salir");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTerminar2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTerminar2)
                .addGap(18, 18, 18)
                .addComponent(jButton9)
                .addGap(18, 18, 18)
                .addComponent(jButton10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jLabel32.setText("Nota adicional de la cita:");

        txtANotaAdicional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtANotaAdicionalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtANotaAdicional)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel32))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtANotaAdicional, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel18.setBackground(new java.awt.Color(0, 0, 102));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ATENCIÓN CITA");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(376, 376, 376)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel23.setBackground(new java.awt.Color(0, 0, 102));

        jLabel18.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("DATOS DEL DUEÑO");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel24.setBackground(new java.awt.Color(0, 0, 102));

        jLabel23.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("DATOS DEL SERVICIO");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Atención cita", jPanel1);

        jPanel5.setBackground(new java.awt.Color(204, 255, 255));

        tblDetalleServicio_PANEL_MEDIC.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tblDetalleServicio_PANEL_MEDIC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblDetalleServicio_PANEL_MEDIC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetalleServicio_PANEL_MEDICMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblDetalleServicio_PANEL_MEDIC);

        jLabel31.setText("Tabla de servicios:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4)
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(204, 255, 255));

        jLabel29.setText("Servicio:");

        jLabel30.setText("Médico:");

        cboServicios_PANEL_MEDIC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboServicios_PANEL_MEDIC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboServicios_PANEL_MEDICActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboServicios_PANEL_MEDIC, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNombreMedico1_PANEL_MEDIC)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(cboServicios_PANEL_MEDIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(txtNombreMedico1_PANEL_MEDIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel19.setText("Medicamento:");

        jLabel20.setText("Cantidad:");

        jLabel21.setText("Indicación:");

        btnBuscarMedicamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/buscar-pequeño.png"))); // NOI18N
        btnBuscarMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarMedicamentoActionPerformed(evt);
            }
        });

        jLabel22.setText("Dosis:");

        btnEliminarMedicamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/eliminar.png"))); // NOI18N
        btnEliminarMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarMedicamentoActionPerformed(evt);
            }
        });

        btnAgregarMedicamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/veterinario.png"))); // NOI18N
        btnAgregarMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarMedicamentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel20))
                        .addGap(23, 23, 23)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(spnCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(txtCodMedicamento))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDosis, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNombreMedicamento)))
                    .addComponent(txtIndicacion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAgregarMedicamento, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                    .addComponent(btnBuscarMedicamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminarMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(txtCodMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20)
                            .addComponent(jLabel22)
                            .addComponent(txtDosis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(btnBuscarMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(txtIndicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnEliminarMedicamento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                        .addComponent(btnAgregarMedicamento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        tblDetalleMedicamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblDetalleMedicamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetalleMedicamentoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDetalleMedicamento);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        btnTerminar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/veterinario.png"))); // NOI18N
        btnTerminar3.setText("Ir a resumen");
        btnTerminar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerminar3ActionPerformed(evt);
            }
        });

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/eliminar.png"))); // NOI18N
        jButton11.setText("Cancelar");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/salir.png"))); // NOI18N
        jButton12.setText("Salir");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTerminar3, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnTerminar3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton11)
                .addGap(18, 18, 18)
                .addComponent(jButton12)
                .addContainerGap(152, Short.MAX_VALUE))
        );

        jPanel19.setBackground(new java.awt.Color(0, 0, 102));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("MEDICAMENTO");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 1057, Short.MAX_VALUE)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(375, 375, 375)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Medicamento", jPanel13);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        tblMedicamentoResumen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(tblMedicamentoResumen);

        tblServicioResumen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(tblServicioResumen);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
                    .addComponent(jScrollPane5))
                .addContainerGap())
        );

        jPanel17.setBackground(new java.awt.Color(204, 255, 255));

        jLabel10.setText("Subtotal:");

        jLabel15.setText("IGV:");

        jLabel16.setText("Total:");

        txtANotaAdicional1.setColumns(20);
        txtANotaAdicional1.setRows(5);
        jScrollPane7.setViewportView(txtANotaAdicional1);

        btnTerminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/veterinario.png"))); // NOI18N
        btnTerminar.setText("Terminar");
        btnTerminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerminarActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/editar.png"))); // NOI18N
        jButton4.setText("Cancelar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/salir.png"))); // NOI18N
        jButton5.setText("Salir");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel17.setText("Nota Adicional:");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSubtotal)
                            .addComponent(txtIgv)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnTerminar, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtIgv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(btnTerminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel20.setBackground(new java.awt.Color(0, 0, 102));

        lblCitaPersona.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblCitaPersona.setForeground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("RESUMEN DE LA CITA DE:");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCitaPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCitaPersona, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel21.setBackground(new java.awt.Color(204, 255, 255));

        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel9.setText("RESUMEN DE MEDICAMENTO");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel9)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(17, 17, 17))
        );

        jPanel22.setBackground(new java.awt.Color(204, 255, 255));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel7.setText("RESUMEN DE SERVICIO");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel7)
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Resumen", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboServicios_PANEL_MEDICActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboServicios_PANEL_MEDICActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboServicios_PANEL_MEDICActionPerformed

    private void tblDetalleServicio_PANEL_MEDICMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetalleServicio_PANEL_MEDICMouseClicked

        ResultSet rsSer;
        ResultSet rsMed;
        String cadena = String.valueOf(tblDetalleServicio_PANEL_MEDIC.getValueAt(tblDetalleServicio_PANEL_MEDIC.getSelectedRow(), 0));
        String[] codigos = cadena.split(" - ");
        int codigoTablaSer = Integer.parseInt(codigos[0].trim());
        int codTablaMed = Integer.parseInt(codigos[1].trim());

        llenarDatos_PANEL_MEDIC(codigoTablaSer, codTablaMed);


    }//GEN-LAST:event_tblDetalleServicio_PANEL_MEDICMouseClicked

    private void btnTerminar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminar2ActionPerformed

        this.jTabbedPane1.setSelectedIndex(2);

    }//GEN-LAST:event_btnTerminar2ActionPerformed

    private void tblDetalleServicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetalleServicioMouseClicked
        llenarServicioMedico();

        int valor = Utilidad.mensajeConfirmarAgregarMedicamento("Medicamento");

        ResultSet rs = null;

        String cadena1 = String.valueOf(tblDetalleServicio.getValueAt(tblDetalleServicio.getSelectedRow(), 0));
        String[] codigos = cadena1.split(" - ");
        int codigoTablaSer = Integer.parseInt(codigos[0].trim());
        int codTablaMed = Integer.parseInt(codigos[1].trim());

        cboServicios_PANEL_MEDIC.setSelectedItem(cboServicios.getSelectedItem());
        txtNombreMedico1_PANEL_MEDIC.setText(cboMedicos.getSelectedItem().toString());

//        int codTablaServicio = codigoTablaSer;
//        int codTablaMedico = codTablaMed;
        if (valor == 0) {
            llenarTablaDetalleServicio_PANEL_MEDIC();
            this.jTabbedPane1.setSelectedIndex(1);
        }
    }//GEN-LAST:event_tblDetalleServicioMouseClicked

    private void cboServiciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboServiciosActionPerformed
        String nom_servicio = cboServicios.getSelectedItem().toString();

        try {
            Integer codServicio = objServicio.obtenerID(nom_servicio);
            ResultSet rsServ = objServicio.buscarServicio(codServicio);
            txtCodServicio.setText(codServicio.toString());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage() + "...");
        }

        llenarCboMedicosPorServicio();

    }//GEN-LAST:event_cboServiciosActionPerformed

    private void btnAgregarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarServicioActionPerformed
        ResultSet rsDetalle;
        try {
            if (txtDocMedico.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor rellenar los campos");
            } else {
                jdColocarHora objAniadirServicio
                        = new jdColocarHora((Frame) SwingUtilities.getWindowAncestor(this), true);
                objAniadirServicio.setLocationRelativeTo(this);
                objAniadirServicio.setVisible(true);

                String horaEntrada = objAniadirServicio.getHoraEntrada();
                String horaSalida = objAniadirServicio.getHoraSalida();
                String nota = objAniadirServicio.getNota();

                try {
//                    agregarServicio(horaEntrada, horaSalida, nota);
                    agregarDetalleCita(horaEntrada, horaSalida, nota);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }

//                limpiarControlesDespuesDeAgregarServicio();
            }
            llenarTablaDetalleServicio_PANEL_MEDIC();
            actualizarResumenes();

            //            objDetalleCita.insertarDetalleServicioNoRepetido(Integer.parseInt(txtNumero.getText()), tblDetalleServicio);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage() + " AL AGREGAR SERVICIO DESDE ATENCION");
        }
    }//GEN-LAST:event_btnAgregarServicioActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {
            llenarTablaCitasPendientesDNI();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Cita no encontrada");

        }
        //        mostrar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tblCitasPendientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCitasPendientesMouseClicked
        // TODO add your handling code here:
        txtDNITabla.setText(String.valueOf(tblCitasPendientes.getValueAt(tblCitasPendientes.getSelectedRow(), 1)));
        txtNumero.setText(String.valueOf(tblCitasPendientes.getValueAt(tblCitasPendientes.getSelectedRow(), 0)));
        btnBuscarCitaActionPerformed(null);
        btnBuscarActionPerformed(null);
        llenarTablaDetalleServicio_PANEL_MEDIC();

        actualizarResumenes();
    }//GEN-LAST:event_tblCitasPendientesMouseClicked

    private void cboEstadoCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboEstadoCitaActionPerformed
        if (!cboEstadoCita.getSelectedItem().equals("Pendiente")) {
            btnTerminar.setEnabled(false);
            btnTerminar2.setEnabled(false);
            btnTerminar3.setEnabled(false);
            this.jTabbedPane1.setEnabledAt(1, false);
            this.jTabbedPane1.setEnabledAt(2, false);

            this.jTabbedPane1.setSelectedIndex(0);
        }
    }//GEN-LAST:event_cboEstadoCitaActionPerformed

    private void btnBuscarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCitaActionPerformed
        ResultSet rsCitaEncontrada = null;

        try {
            rsCitaEncontrada = objCita.buscarCita(Integer.parseInt(txtNumero.getText()));

            if (rsCitaEncontrada.next()) {
                //                int codDuenio = rsCitaEncontrada.getInt("custodiaduenioid");
                int codMascota = rsCitaEncontrada.getInt("codigo_mascota");
                txtCodMascota.setText(String.valueOf(codMascota));

                // del dueño
//                String docDuenio = rsCitaEncontrada.getString("duenio_doc");
                String nombreDuenio = rsCitaEncontrada.getString("duenio_nombres") + " "
                        + rsCitaEncontrada.getString("duenio_apPaterno") + " "
                        + rsCitaEncontrada.getString("duenio_apMaterno");

                txtNombreDuenio.setText(nombreDuenio);
                //                String direccion = rsCitaEncontrada.getString("direccion");
                String telefono_duenio = rsCitaEncontrada.getString("telefono_duenio");

                // Datos de la mascota
                String nombreMascota = rsCitaEncontrada.getString("nombre_mascota");
                txtNombreMascota.setText(nombreMascota);
//                int edad = rsCitaEncontrada.getInt("edad");
                //                Date fechaNacimientoMascota = rsCitaEncontrada.getDate("mascota_fecha_nacimiento");
                //                double alturaMascota = rsCitaEncontrada.getDouble("mascota_altura");
                //                double pesoMascota = rsCitaEncontrada.getDouble("mascota_peso");
                //                boolean sexoMascota = rsCitaEncontrada.getBoolean("mascota_sexo");
//                boolean esterilizadoMascota = rsCitaEncontrada.getBoolean("castrado");
                //                boolean desparasitadoMascota = rsCitaEncontrada.getBoolean("mascota_desparasitado");
                String nota = rsCitaEncontrada.getString("nota");
                txtNotaMascota.setText(nota);
//                int raza_id = rsCitaEncontrada.getInt("raza_id");
//
//                ResultSet rsRaza = objRaza.buscarRaza(raza_id);

//                if (rsRaza.next()) {
//                    txtRaza.setText(rsRaza.getString("nombre"));
//                }
                // cita
                int idCita = rsCitaEncontrada.getInt("id");
                int estadoCitaId = rsCitaEncontrada.getInt("estado_cita_id");
                Date fechaCita = rsCitaEncontrada.getDate("fecha_cita");
                String observacionCita = rsCitaEncontrada.getString("observacion");

                ResultSet rsEstadoCita = objEstadoCita.buscar(estadoCitaId);

                if (rsEstadoCita.next()) {
                    cboEstadoCita.setSelectedItem(rsEstadoCita.getString("nombre_estado"));
                }

                jDateChooser1.setDate(fechaCita);
                txtANotaAdicional.setText(observacionCita);

                //                txtCodDuenio.setText(String.valueOf(codDuenio));
//                txtDniRuc.setText(docDuenio);
                //                txtNombreDuenio.setText(nombreDuenio);
                //                txtDireccion.setText(direccion);
                txtTelefono.setText(telefono_duenio);

                //                txtCodMascota.setText(String.valueOf(codMascota));
//                txtEstadoSaludMascota.setText(nombreMascota);
//                spnEdad.setValue(edad);
                //                txtEstadoSaludMascota.setText(direccion);
//                if (esterilizadoMascota) {
//                    rdbCastrado.setSelected(true);
//                } else {
//                    rdbNoCastrado.setSelected(true);
//                }
//
//                btnBuscarDuenioActionPerformed(null);
//                btnBuscarMascotaActionPerformed(null);
            }
//            llenarDetalleMedicamentosEnTabla();
            String texto = "Resumen cita de: ";
            String duenio = txtNombreDuenio.getText();
            String mascota = txtNombreMascota.getText();

            lblCitaPersona.setText(texto + duenio + " y su mascota " + mascota);

            llenarTablitaServicios();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage() + "...");
        }
    }//GEN-LAST:event_btnBuscarCitaActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void txtDNITablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDNITablaActionPerformed

    }//GEN-LAST:event_txtDNITablaActionPerformed

    private void txtDNITablaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDNITablaKeyTyped
        if (txtDNITabla.getText().isEmpty()) {
            llenarTablaCitasPendientes();
        }
    }//GEN-LAST:event_txtDNITablaKeyTyped

    private void btnBuscarMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarMedicamentoActionPerformed
        ResultSet rsMedicamento;
        try {
            rsMedicamento = objMedicamento.buscarMedicamento(Integer.parseInt(txtCodMedicamento.getText()));

            if (rsMedicamento.next()) {
                txtNombreMedicamento.setText(rsMedicamento.getString("nombre"));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnBuscarMedicamentoActionPerformed

    private void btnEliminarMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarMedicamentoActionPerformed

        int id_m = Integer.parseInt(tblDetalleMedicamento.getValueAt(tblDetalleMedicamento.getSelectedRow(), 2).toString());

        if (id_m != 0) {
            String nombre = tblDetalleMedicamento.getValueAt(tblDetalleMedicamento.getSelectedRow(), 3).toString();

            int valor = Utilidad.mensajeConfirmarEliminar("Medicamento", id_m, nombre);

            if (valor == 0) {
                eliminarMedicamentoDeTabla();
            }
            actualizarResumenes();
        }
    }//GEN-LAST:event_btnEliminarMedicamentoActionPerformed

    private void btnAgregarMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarMedicamentoActionPerformed
//        if (txtCodServicio.getText().isEmpty() || txtDocMedico.getText().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Indicar el servicio y el médico por favor");
//        } else {
//            if (!txtCodMedicamento.getText().isEmpty()
//                    && !txtNombreMedicamento.getText().isEmpty()
//                    && !txtDosis.getText().isEmpty()
//                    && ((Integer) spnCantidad.getValue() != 0)) {
//
//                int stock = -1;
//                try {
//                    stock = objMedicamento.getStock(Integer.parseInt(txtCodMedicamento.getText()));
//                } catch (Exception e) {
//                    JOptionPane.showMessageDialog(this, e.getMessage() + "Error al obtener el stock");
//                }
//                if ((Integer) spnCantidad.getValue() >= stock) {
//                    JOptionPane.showMessageDialog(this, "Stock insuficiente");
//                } else {
//                    agregarMedicamento(Integer.parseInt(txtCodMedicamento.getText()), (Integer) spnCantidad.getValue(),
//                            Float.parseFloat(txtDosis.getText()), txtIndicacion.getText());
////                    limpiarTodoMedicamento();
//                }
//            } else {
//                jdAniadirMedicamento objAniadirMedicamento
//                        = new jdAniadirMedicamento((Frame) SwingUtilities.getWindowAncestor(this), true);
//                objAniadirMedicamento.setLocationRelativeTo(this);
//                objAniadirMedicamento.setVisible(true);
//
//                int codMedicamento = objAniadirMedicamento.getCodMed();
//                int cantidad = objAniadirMedicamento.getCant();
//                float dosis = objAniadirMedicamento.getDosis();
//                String indicacion = objAniadirMedicamento.getIndic();
//
//                try {
//                    //                    JOptionPane.showMessageDialog(this, "si llego");
//                    agregarMedicamento(codMedicamento, cantidad, dosis, indicacion);
//                    //                    JOptionPane.showMessageDialog(this, "pa aca tmb");
//                } catch (Exception e) {
//                    JOptionPane.showMessageDialog(this, e.getMessage() + "al agregar medicamento");
//                }
//            }
//        }

//        calcularTotales();
////////////////////////////////////////////////aaaaaaaaaaaaaaaaaaaaaaaaaa///////////////////////////////////////
        if (txtCodServicio.getText().isEmpty() || txtDocMedico.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Indicar el servicio y el médico por favor");
        } else {
            // Verificar si todos los campos de medicamento están llenos
            if (!txtCodMedicamento.getText().isEmpty()
                    && !txtNombreMedicamento.getText().isEmpty()
                    && !txtDosis.getText().isEmpty()
                    && ((Integer) spnCantidad.getValue() != 0)) {

                int stock = -1;
                try {
                    // Obtener el stock del medicamento
                    stock = objMedicamento.getStock(Integer.parseInt(txtCodMedicamento.getText()));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Código de medicamento no válido. Verifique e intente nuevamente.");
                    return;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e.getMessage() + " Error al obtener el stock");
                    return;
                }

                // Verificar si el stock es suficiente
                if (stock < 0) {
                    JOptionPane.showMessageDialog(this, "Error al obtener el stock. Inténtelo nuevamente.");
                } else if ((Integer) spnCantidad.getValue() > stock) {
                    JOptionPane.showMessageDialog(this, "Stock insuficiente");
                } else {
                    // Agregar el medicamento
                    try {
                        agregarMedicamento(
                                Integer.parseInt(txtCodMedicamento.getText()),
                                (Integer) spnCantidad.getValue(),
                                Float.parseFloat(txtDosis.getText()),
                                txtIndicacion.getText()
                        );
                        JOptionPane.showMessageDialog(this, "Medicamento agregado exitosamente.");
//                        limpiarTodoMedicamento(); // Limpiar los campos después de agregar
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Dosis no válida. Verifique e intente nuevamente.");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e.getMessage() + " al agregar medicamento1");
                    }
                }
            } else {
                // Si los campos de medicamento están vacíos, abrir el cuadro de diálogo para añadir medicamento
                jdAniadirMedicamento objAniadirMedicamento = new jdAniadirMedicamento(
                        (Frame) SwingUtilities.getWindowAncestor(this), true);
                objAniadirMedicamento.setLocationRelativeTo(this);
                objAniadirMedicamento.setVisible(true);

                int codMedicamento = objAniadirMedicamento.getCodMed();
                int cantidad = objAniadirMedicamento.getCant();
                float dosis = objAniadirMedicamento.getDosis();
                String indicacion = objAniadirMedicamento.getIndic();

                // Validar si se obtuvieron datos del cuadro de diálogo
                if (codMedicamento != -1 && cantidad > 0 && dosis > 0) {
                    try {
                        agregarMedicamento(codMedicamento, cantidad, dosis, indicacion);
                        JOptionPane.showMessageDialog(this, "Medicamento agregado exitosamente.");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e.getMessage() + " al agregar medicamento2");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se añadió ningún medicamento.");
                }
            }
        }

        actualizarResumenes();

        calcularTotales();
    }//GEN-LAST:event_btnAgregarMedicamentoActionPerformed

    private void tblDetalleMedicamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetalleMedicamentoMouseClicked
        txtCodMedicamento.setText(tblDetalleMedicamento.getValueAt(tblDetalleMedicamento.getSelectedRow(), 0).toString());
        btnBuscarMedicamentoActionPerformed(null);

        spnCantidad.setValue(Integer.parseInt(tblDetalleMedicamento.getValueAt(tblDetalleMedicamento.getSelectedRow(), 6).toString()));
        txtDosis.setText(tblDetalleMedicamento.getValueAt(tblDetalleMedicamento.getSelectedRow(), 4).toString());
        txtIndicacion.setText(tblDetalleMedicamento.getValueAt(tblDetalleMedicamento.getSelectedRow(), 5).toString());
    }//GEN-LAST:event_tblDetalleMedicamentoMouseClicked

    private void btnTerminar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminar3ActionPerformed
        this.jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_btnTerminar3ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton12ActionPerformed


    private void btnEliminarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarServicioActionPerformed
        int valor = Utilidad.mensajeConfirmarEliminarDetalleServicio(cboServicios.getSelectedItem().toString() + "\"");

        if (valor == 0) {
            DefaultTableModel modelito = (DefaultTableModel) tblDetalleServicio.getModel();
            modelito.removeRow(tblDetalleServicio.getSelectedRow());
        }

        actualizarResumenes();
    }//GEN-LAST:event_btnEliminarServicioActionPerformed

    private void cboMedicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMedicosActionPerformed
        ResultSet rsMedicoDNI;
        if (cboMedicos.getSelectedItem() != null && !cboMedicos.getSelectedItem().toString().equals("")) {
            try {
                rsMedicoDNI = objMedico.buscarDniMedicoPorNombreCompleto(cboMedicos.getSelectedItem().toString());

                if (rsMedicoDNI.next()) {
                    txtDocMedico.setText(rsMedicoDNI.getString("doc_identidad"));
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró el médico con ese nombre.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "No se muestra dni: " + e.getMessage());
            }
        }

    }//GEN-LAST:event_cboMedicosActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        cancelarCita();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void txtANotaAdicionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtANotaAdicionalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtANotaAdicionalActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        cancelarCita();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnTerminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminarActionPerformed
        try {
            objDetalleMedicamento.registrarDetalleMedicamento(Integer.parseInt(txtNumero.getText()), tblDetalleMedicamento);
            objCita.terminarCita(Integer.parseInt(txtNumero.getText()));

            for (int i = 0; i < tblDetalleMedicamento.getRowCount(); i++) {
                int idMedicamento = Integer.parseInt(tblDetalleMedicamento.getValueAt(i, 0).toString());
                int cantidad = Integer.parseInt(tblDetalleMedicamento.getValueAt(i, 6).toString());
                objMedicamento.reducirStock(idMedicamento, cantidad);
            }

            String tipo;

            String num = objComprobante.generarNumeroSerieComprobante();
            java.util.Date utilDate = jDateChooser1.getDate();
            java.sql.Date fecha = new java.sql.Date(utilDate.getTime());

            objComprobante.registrarComprobante("B", num, Float.parseFloat(txtTotal.getText()), fecha,
                    Integer.parseInt(txtNumero.getText()));

            int idCita = Integer.parseInt(txtNumero.getText());
            jdComprobante.id_cita = idCita;
            jdComprobante objComprobante_interfaz = new jdComprobante((Frame) SwingUtilities.getWindowAncestor(this), true);
            // Establecer id_cita en la instancia
            System.out.println(objComprobante_interfaz.getIdCita());

            objComprobante_interfaz.setVisible(true);
            JOptionPane.showMessageDialog(this, "La cita finalizó");

            //            limpiarTodoAlTerminar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudo terminar " + e.getMessage());
        }
    }//GEN-LAST:event_btnTerminarActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        cancelarCita();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarMedicamento;
    private javax.swing.JButton btnAgregarServicio;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarCita;
    private javax.swing.JButton btnBuscarMedicamento;
    private javax.swing.JButton btnEliminarMedicamento;
    private javax.swing.JButton btnEliminarServicio;
    private javax.swing.JButton btnTerminar;
    private javax.swing.JButton btnTerminar2;
    private javax.swing.JButton btnTerminar3;
    private javax.swing.JComboBox<String> cboEstadoCita;
    private javax.swing.JComboBox<String> cboMedicos;
    private javax.swing.JComboBox<String> cboServicios;
    private javax.swing.JComboBox<String> cboServicios_PANEL_MEDIC;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblCitaPersona;
    private javax.swing.JSpinner spnCantidad;
    private javax.swing.JTable tblCitasPendientes;
    private javax.swing.JTable tblDetalleMedicamento;
    private javax.swing.JTable tblDetalleServicio;
    private javax.swing.JTable tblDetalleServicio_PANEL_MEDIC;
    private javax.swing.JTable tblMedicamentoResumen;
    private javax.swing.JTable tblServicioResumen;
    private javax.swing.JTextField txtANotaAdicional;
    private javax.swing.JTextArea txtANotaAdicional1;
    private javax.swing.JTextField txtCodMascota;
    private javax.swing.JTextField txtCodMedicamento;
    private javax.swing.JTextField txtCodServicio;
    private javax.swing.JTextField txtDNITabla;
    private javax.swing.JTextField txtDocMedico;
    private javax.swing.JTextField txtDosis;
    private javax.swing.JTextField txtIgv;
    private javax.swing.JTextField txtIndicacion;
    private javax.swing.JTextField txtNombreDuenio;
    private javax.swing.JTextField txtNombreMascota;
    private javax.swing.JTextField txtNombreMedicamento;
    private javax.swing.JTextField txtNombreMedico1_PANEL_MEDIC;
    private javax.swing.JTextField txtNotaMascota;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}

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
import java.sql.Date;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Grupo_Veterinaria
 */
public class jdCitaFirme extends javax.swing.JDialog {

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

    public jdCitaFirme(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        llenarCboServicios();
        llenarCboEstadoCita();
        this.setTitle("Gestión de Cita");

        llenarTablaInicialServicio();
        llenarTablaInicialMedicamento();

        calcularTotales();

    }

    private void limpiarTodoMedicamento() {
        txtCodMedicamento.setText("");
        spnCantidad.setValue(0);
        txtNombreMedicamento.setText("");
        txtIndicacion.setText("");
        txtDosis.setText("");
    }

    private void limpiarTodoAlTerminar() {
        txtANotaAdicional.setText("");
        txtCodDuenio.setText("");
        txtCodMascota.setText("");
        txtCodMedicamento.setText("");
        txtCodServicio.setText("");
        txtDescripcionServicio.setText("");
        txtDireccion.setText("");
        txtDniRuc.setText("");
        txtDocMedico.setText("");
        txtDosis.setText("");
        txtEstadoSaludMascota.setText("");
        txtIgv.setText("");
        txtIndicacion.setText("");
        txtNombreDuenio.setText("");
        txtNombreMascota.setText("");
        txtNombreMedicamento.setText("");
        txtNombreMedico.setText("");
        txtNotaMascota.setText("");
        txtNumero.setText("");
        txtRaza.setText("");
        txtSubtotal.setText("");
        txtTelefono.setText("");
        txtTotal.setText("");

        spnEdad.setValue(0);
        spnCantidad.setValue(0);
        jDateChooser1.setDate(null);

        llenarTablaInicialServicio();
        llenarTablaInicialMedicamento();

//        cboEstadoCita.setSelectedIndex(-1);
//        cboServicios.setSelectedIndex(-1);
    }

    private void llenarTablaInicialServicio() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID SERV_MED");
        modelo.addColumn("SERVICIO");
        modelo.addColumn("MEDICO");
        modelo.addColumn("HORA ENTRADA");
        modelo.addColumn("HORA SALIDA");
        modelo.addColumn("COSTO");

        tblDetalleServicio.setModel(modelo);
        tblDetalleServicio.getTableHeader().setReorderingAllowed(false); //no mover los headers
    }

    private void llenarTablaInicialMedicamento() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID MEDICAMENTO");
        modelo.addColumn("ID SERVICIO");
        modelo.addColumn("ID MEDICO");
        modelo.addColumn("DOSIS");
        modelo.addColumn("INDICACIÓN");
        modelo.addColumn("CANTIDAD");
        modelo.addColumn("COSTO");

        tblDetalleMedicamento.setModel(modelo);
        tblDetalleMedicamento.getTableHeader().setReorderingAllowed(false); //no mover los headers
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

    private void llenarCboServicios() {
        ResultSet rsServicios = null;
        DefaultComboBoxModel modeloSer = new DefaultComboBoxModel();
        cboServicios.setModel(modeloSer);

        try {
            rsServicios = objServicio.listarServicios();

            while (rsServicios.next()) {
                modeloSer.addElement(rsServicios.getString("nom_servicio"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Error al listar en interfaz los servicios");
        }
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
                txtDescripcionServicio.setText(rsSer.getString("descripcion"));
            }

            if (rsMed.next()) {
                txtDocMedico.setText(rsMed.getString("doc_identidad"));
                txtNombreMedico.setText(rsMed.getString("nombres") + " " + rsMed.getString("apepaterno") + " " + rsMed.getString("apematerno"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Error al llenar campos SER-MED " + e.getMessage());
        }

    }

    private void llenarDetalleMedicamentosEnTabla() {
        ResultSet rsDetalleMedicamento;
        ResultSet rsMedicamento;
        try {
            rsDetalleMedicamento = objDetalleMedicamento.obtenerDetalleMedicamentosPorCita(Integer.parseInt(txtNumero.getText()));
            DefaultTableModel modelo = (DefaultTableModel) tblDetalleMedicamento.getModel();
            modelo.setRowCount(0);
            while (rsDetalleMedicamento.next()) {
                int medicamento_id = rsDetalleMedicamento.getInt("medicamento_id");
                int servicio_id = rsDetalleMedicamento.getInt("detalle_servicio_servicio_id");
                int medico_id = rsDetalleMedicamento.getInt("detalle_servicio_medico_id");
                String dosis = rsDetalleMedicamento.getString("dosis");
                String indicacion = rsDetalleMedicamento.getString("indicacion");
                int cantidad = rsDetalleMedicamento.getInt("cantidad");
                Float costo = rsDetalleMedicamento.getFloat("costo");

                modelo.addRow(new Object[]{medicamento_id, servicio_id, medico_id, dosis, indicacion,
                    cantidad, costo});

            }
            tblDetalleMedicamento.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Error al llenar campos tabla ser " + e.getMessage());
        }

    }

    //para agregar medicamentos debe estar llena la parte de servicio en el formulario u.u
    private void agregarMedicamento(int medicamento, int cantidad, float dosis, String indicacion) {
        ResultSet rs = null;
        try {
            // Verifica que los parámetros sean válidos
            if (medicamento != 0 && cantidad != 0 && dosis != 0) {
                // Confirmación del usuario
                if (JOptionPane.showConfirmDialog(this, "¿Los datos son correctos?", "Confirmar",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                    DefaultTableModel modelito = (DefaultTableModel) tblDetalleMedicamento.getModel();
                    int idMed = objMedico.obtenerIDconDoc(txtDocMedico.getText()); // Obtener ID del médico

                    // Elimina fila duplicada en tblDetalleMedicamento si ya existe
                    for (int i = modelito.getRowCount() - 1; i >= 0; i--) {
                        int medicamento_id = Integer.parseInt(tblDetalleMedicamento.getValueAt(i, 0).toString());
                        int servicio_id = Integer.parseInt(tblDetalleMedicamento.getValueAt(i, 1).toString());
                        int medico_id = Integer.parseInt(tblDetalleMedicamento.getValueAt(i, 2).toString());

                        if (medicamento_id == medicamento
                                && servicio_id == Integer.parseInt(txtCodServicio.getText())
                                && medico_id == idMed) {
                            modelito.removeRow(i); // Elimina la fila duplicada
                            break;
                        }
                    }

                    // Busca el medicamento en la base de datos para obtener su costo
                    rs = objMedicamento.buscarMedicamento(medicamento);
                    if (rs.next()) {
                        // Agrega nueva fila con los datos del medicamento
                        modelito.addRow(new Object[]{
                            medicamento, // Código de medicamento
                            txtCodServicio.getText(), // Código de servicio
                            idMed, // ID del médico
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

    private void agregarDetalleCita() {
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

            String horaInicio = JOptionPane.showInputDialog(this, "Ingrese la hora de inicio (HH:mm):");
            if (horaInicio == null || horaInicio.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar una hora de inicio.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String horaFin = JOptionPane.showInputDialog(this, "Ingrese la hora de fin (HH:mm):");
            if (horaFin == null || horaFin.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar una hora de fin.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String notaAdicional = JOptionPane.showInputDialog(this, "Ingrese una nota adicional (opcional):");
            if (notaAdicional == null) {
                notaAdicional = ""; // Establece un valor por defecto si se cancela el cuadro de diálogo
            }

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

// Función para obtener el ID del médico
    private int obtenerIdMedico(String docMedico) throws Exception {
        return objMedico.obtenerIDconDoc(docMedico);
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
            double costoMedicamento = Double.parseDouble(modeloMedicamentos.getValueAt(i, 6).toString());
            int cantidad = Integer.parseInt(modeloMedicamentos.getValueAt(i, 5).toString());
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

        jPanel10 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jTextField21 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jSpinner3 = new javax.swing.JSpinner();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        btnBuscarCita = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cboEstadoCita = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtDniRuc = new javax.swing.JTextField();
        txtCodDuenio = new javax.swing.JTextField();
        txtNombreDuenio = new javax.swing.JTextField();
        btnBuscarDuenio = new javax.swing.JButton();
        txtDireccion = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        rdbBoleta = new javax.swing.JRadioButton();
        rdbFactura = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtNombreMascota = new javax.swing.JTextField();
        txtCodMascota = new javax.swing.JTextField();
        txtEstadoSaludMascota = new javax.swing.JTextField();
        btnBuscarMascota = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        txtRaza = new javax.swing.JTextField();
        rdbCastrado = new javax.swing.JRadioButton();
        rdbNoCastrado = new javax.swing.JRadioButton();
        txtNotaMascota = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        spnEdad = new javax.swing.JSpinner();
        jPanel11 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtANotaAdicional = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        btnTerminar = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JTextField();
        txtIgv = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
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
        jPanel6 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txtCodServicio = new javax.swing.JTextField();
        txtDocMedico = new javax.swing.JTextField();
        txtDescripcionServicio = new javax.swing.JTextField();
        btnEliminarServicio = new javax.swing.JButton();
        btnAgregarServicio = new javax.swing.JButton();
        txtNombreMedico = new javax.swing.JTextField();
        cboServicios = new javax.swing.JComboBox<>();
        btnBuscarDetalleServicio = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDetalleMedicamento = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDetalleServicio = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();

        jPanel10.setBackground(new java.awt.Color(204, 255, 204));

        jLabel23.setText("Medicamento:");

        jLabel24.setText("Cantidad:");

        jLabel25.setText("Indicación:");

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/buscar-pequeño.png"))); // NOI18N

        jLabel26.setText("Dosis:");

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/eliminar.png"))); // NOI18N

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/veterinario.png"))); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSpinner3, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(jTextField19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField22)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel23)
                                .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel26))
                            .addComponent(jLabel24)
                            .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jButton13)
                        .addGap(18, 18, 18)
                        .addComponent(jButton14)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(0, 0, 102));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("CITAS");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(460, 460, 460)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jLabel1.setText("Número:");

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

        jLabel4.setText("Estado:");

        jButton2.setText("Ver adicionales ");

        jLabel5.setText("Fecha");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscarCita, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboEstadoCita, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(btnBuscarCita)
                        .addComponent(jLabel3)
                        .addComponent(cboEstadoCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jButton2)
                        .addComponent(jLabel5)))
                .addGap(13, 13, 13))
        );

        jPanel4.setBackground(new java.awt.Color(204, 255, 204));

        jLabel10.setText("Doc. de Identidad:");

        jLabel11.setText("Dueño:");

        jLabel12.setText("Dirección:");

        btnBuscarDuenio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/buscar-pequeño.png"))); // NOI18N
        btnBuscarDuenio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarDuenioActionPerformed(evt);
            }
        });

        jLabel13.setText("Teléfono:");

        rdbBoleta.setText("Boleta");

        rdbFactura.setText("Factura");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtCodDuenio, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombreDuenio, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtDniRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarDuenio, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(rdbBoleta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rdbFactura))
                            .addComponent(txtTelefono)))
                    .addComponent(txtDireccion))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnBuscarDuenio)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtDniRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCodDuenio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNombreDuenio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdbBoleta)
                        .addComponent(rdbFactura)))
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(204, 255, 204));

        jLabel14.setText("Nombre:");

        jLabel15.setText("Macota:");

        jLabel16.setText("Nota:");

        btnBuscarMascota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/buscar-pequeño.png"))); // NOI18N
        btnBuscarMascota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarMascotaActionPerformed(evt);
            }
        });

        jLabel17.setText("Raza:");

        rdbCastrado.setText("Castrado");

        rdbNoCastrado.setText("No Castrado");

        jLabel18.setText("Edead:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txtNombreMascota, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(btnBuscarMascota, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRaza))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(txtCodMascota, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtEstadoSaludMascota, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(rdbCastrado, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(rdbNoCastrado, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(txtNotaMascota, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(spnEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtNombreMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarMascota)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtRaza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCodMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEstadoSaludMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdbCastrado)
                        .addComponent(rdbNoCastrado))
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNotaMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel18)
                    .addComponent(spnEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(204, 204, 204));

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel6.setText("Nota Adicional:");

        txtANotaAdicional.setColumns(20);
        txtANotaAdicional.setRows(5);
        jScrollPane1.setViewportView(txtANotaAdicional);

        btnTerminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/veterinario.png"))); // NOI18N
        btnTerminar.setText("Terminar");
        btnTerminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerminarActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/editar.png"))); // NOI18N
        jButton4.setText("Reprogramar");

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/eliminar.png"))); // NOI18N
        jButton5.setText("Cancelar");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTerminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTerminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setText("Subtotal:");

        jLabel8.setText("IGV:");

        jLabel9.setText("Total:");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSubtotal)
                            .addComponent(txtIgv)
                            .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
                        .addGap(13, 13, 13))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addGap(5, 5, 5)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtIgv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(204, 255, 204));

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
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(spnCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(txtCodMedicamento))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNombreMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDosis)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscarMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtIndicacion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEliminarMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel19)
                                .addComponent(txtCodMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNombreMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnBuscarMedicamento))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtDosis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel22))
                            .addComponent(jLabel20)
                            .addComponent(spnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtIndicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(btnEliminarMedicamento)
                        .addGap(18, 18, 18)
                        .addComponent(btnAgregarMedicamento)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(204, 255, 204));

        jLabel27.setText("Servicio:");

        jLabel28.setText("Médico:");

        jLabel29.setText("Descripción:");

        btnEliminarServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/eliminar.png"))); // NOI18N

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

        btnBuscarDetalleServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/buscar-pequeño.png"))); // NOI18N
        btnBuscarDetalleServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarDetalleServicioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtDocMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNombreMedico))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDescripcionServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txtCodServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboServicios, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBuscarDetalleServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEliminarServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel27)
                                .addComponent(txtCodServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboServicios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnBuscarDetalleServicio))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(txtDocMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(txtDescripcionServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnEliminarServicio)
                        .addGap(18, 18, 18)
                        .addComponent(btnAgregarServicio)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 204, 204));

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
        jScrollPane2.setViewportView(tblDetalleMedicamento);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel12.setBackground(new java.awt.Color(255, 204, 204));

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
        tblDetalleServicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblDetalleServicioKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(tblDetalleServicio);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel13.setBackground(new java.awt.Color(0, 51, 204));

        jLabel30.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("DATOS DUEÑO");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(202, 202, 202)
                .addComponent(jLabel30)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(0, 51, 204));

        jLabel31.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("DATOS MASCOTA");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel31)
                .addGap(183, 183, 183))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCitaActionPerformed
        ResultSet rsCitaEncontrada = null;

        try {
            rsCitaEncontrada = objCita.buscarCita(Integer.parseInt(txtNumero.getText()));

            if (rsCitaEncontrada.next()) {
//                int codDuenio = rsCitaEncontrada.getInt("custodiaduenioid");
//                int codMascota = rsCitaEncontrada.getInt("custodiamascotaid");

                // del dueño
                String docDuenio = rsCitaEncontrada.getString("duenio_doc");
//                String nombreDuenio = rsCitaEncontrada.getString("duenio_nombres") + " "
//                        + rsCitaEncontrada.getString("duenio_apPaterno") + " "
//                        + rsCitaEncontrada.getString("duenio_apMaterno");
//                String direccion = rsCitaEncontrada.getString("direccion");
                String telefono_duenio = rsCitaEncontrada.getString("telefono_duenio");

                // Datos de la mascota
                String nombreMascota = rsCitaEncontrada.getString("nombre_mascota");
                int edad = rsCitaEncontrada.getInt("edad");
//                Date fechaNacimientoMascota = rsCitaEncontrada.getDate("mascota_fecha_nacimiento");
//                double alturaMascota = rsCitaEncontrada.getDouble("mascota_altura");
//                double pesoMascota = rsCitaEncontrada.getDouble("mascota_peso");
//                boolean sexoMascota = rsCitaEncontrada.getBoolean("mascota_sexo");
                boolean esterilizadoMascota = rsCitaEncontrada.getBoolean("castrado");
//                boolean desparasitadoMascota = rsCitaEncontrada.getBoolean("mascota_desparasitado");
//                String nota = rsCitaEncontrada.getString("nota");
                int raza_id = rsCitaEncontrada.getInt("raza_id");

                ResultSet rsRaza = objRaza.buscarRaza(raza_id);

                if (rsRaza.next()) {
                    txtRaza.setText(rsRaza.getString("nombre"));
                }

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
                txtDniRuc.setText(docDuenio);
//                txtNombreDuenio.setText(nombreDuenio);
//                txtDireccion.setText(direccion);
                txtTelefono.setText(telefono_duenio);

//                txtCodMascota.setText(String.valueOf(codMascota));
                txtEstadoSaludMascota.setText(nombreMascota);
                spnEdad.setValue(edad);
//                txtEstadoSaludMascota.setText(direccion);
                if (esterilizadoMascota) {
                    rdbCastrado.setSelected(true);
                } else {
                    rdbNoCastrado.setSelected(true);
                }

                btnBuscarDuenioActionPerformed(null);
                btnBuscarMascotaActionPerformed(null);

            }
            llenarDetalleMedicamentosEnTabla();
            llenarTablitaServicios();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnBuscarCitaActionPerformed

    private void btnBuscarDuenioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarDuenioActionPerformed
        ResultSet rsCliente = null;

        try {

            rsCliente = objDuenio.buscarDuenioN(txtDniRuc.getText());

            if (rsCliente.next()) {
                txtCodDuenio.setText(String.valueOf(rsCliente.getString("id")));
                txtNombreDuenio.setText(String.valueOf(rsCliente.getString("nombres") + " " + rsCliente.getString("apepaterno")
                        + " " + rsCliente.getString("apematerno")));
                txtDireccion.setText(String.valueOf(rsCliente.getString("direccion")));

                if (rsCliente.getString("doc_identidad").length() != 11) {
                    rdbBoleta.setSelected(true);
                } else {
                    rdbFactura.setSelected(true);
                }
            } else {
                if (JOptionPane.showConfirmDialog(this, "Dueño no existe ¿Desea registrarlo?", "Alerta!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    jdMantDuenio_v2 objMantDuenio = new jdMantDuenio_v2(null, true);
                    objMantDuenio.setLocationRelativeTo(this);
                    objMantDuenio.setVisible(true);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }//GEN-LAST:event_btnBuscarDuenioActionPerformed

    private void btnBuscarMascotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarMascotaActionPerformed
        ResultSet rsMascota = null;

        try {

            if (txtCodDuenio.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un dueño para buscar mascotas");
            } else if (txtEstadoSaludMascota.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar el nombre de la mascota");
            } else {
                rsMascota = objMascota.filtrarMascotaPorDuenioYNombre(Integer.valueOf(txtCodDuenio.getText()),
                        txtEstadoSaludMascota.getText());

                if (rsMascota.next()) {
                    txtNombreMascota.setText(rsMascota.getString("nombre"));
                    txtCodMascota.setText(String.valueOf(rsMascota.getString("id")));
                    txtNotaMascota.setText(String.valueOf(rsMascota.getString("notaAdicional")));
                    txtEstadoSaludMascota.setText(String.valueOf(objMascota.calcularEdadMascota(rsMascota.getInt("id"))));

                    if (rsMascota.getBoolean("esterilizado")) {
                        rdbCastrado.setSelected(true);
                    } else {
                        rdbNoCastrado.setSelected(true);
                    }
                } else {
                    if (JOptionPane.showConfirmDialog(this, "Mascota no existe ¿Desea registrar?", "Alerta!",
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        jdMantMascota_v2 objMantMascota = new jdMantMascota_v2(null, true);
                        objMantMascota.setLocationRelativeTo(this);
                        objMantMascota.setVisible(true);
                    }
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnBuscarMascotaActionPerformed

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
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarMedicamentoActionPerformed

    private void btnAgregarMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarMedicamentoActionPerformed
        if (txtCodServicio.getText().isEmpty() || txtDocMedico.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Indicar el servicio y el médico por favor");
        } else {
            if (!txtCodMedicamento.getText().isEmpty()
                    && !txtNombreMedicamento.getText().isEmpty()
                    && !txtDosis.getText().isEmpty()
                    && ((Integer) spnCantidad.getValue() != 0)) {

                int stock = -1;
                try {
                    stock = objMedicamento.getStock(Integer.parseInt(txtCodMedicamento.getText()));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
                if ((Integer) spnCantidad.getValue() >= stock) {
                    JOptionPane.showMessageDialog(this, "Stock insuficiente");
                } else {
                    agregarMedicamento(Integer.parseInt(txtCodMedicamento.getText()), (Integer) spnCantidad.getValue(),
                            Float.parseFloat(txtDosis.getText()), txtIndicacion.getText());
                    limpiarTodoMedicamento();
                }
            } else {
                jdAniadirMedicamento objAniadirMedicamento
                        = new jdAniadirMedicamento((Frame) SwingUtilities.getWindowAncestor(this), true);
                objAniadirMedicamento.setLocationRelativeTo(this);
                objAniadirMedicamento.setVisible(true);

                int codMedicamento = objAniadirMedicamento.getCodMed();
                int cantidad = objAniadirMedicamento.getCant();
                float dosis = objAniadirMedicamento.getDosis();
                String indicacion = objAniadirMedicamento.getIndic();

                try {
//                    JOptionPane.showMessageDialog(this, "si llego");
                    agregarMedicamento(codMedicamento, cantidad, dosis, indicacion);
//                    JOptionPane.showMessageDialog(this, "pa aca tmb");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
            }
        }

        calcularTotales();
    }//GEN-LAST:event_btnAgregarMedicamentoActionPerformed

    private void btnBuscarDetalleServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarDetalleServicioActionPerformed
        ResultSet rsServicio = null;

        if (txtDocMedico.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Debe ingresar un DNI de médico");
        } else {
            try {

                rsServicio = objServicio.obtenerDatosDetalleServicio(Integer.parseInt(txtCodServicio.getText()), txtDocMedico.getText());

                if (rsServicio.next()) {
                    txtNombreMedico.setText(String.valueOf(rsServicio.getString("nombres") + " " + rsServicio.getString("apepaterno")
                            + " " + rsServicio.getString("apematerno")));
                    txtDescripcionServicio.setText(String.valueOf(rsServicio.getString("descripcion")));

                } else {
                    JOptionPane.showMessageDialog(this, "Este veterinario no brinda dicho servicio");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }//GEN-LAST:event_btnBuscarDetalleServicioActionPerformed

    private void btnAgregarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarServicioActionPerformed
        ResultSet rsDetalle;
        try {
            if (txtDocMedico.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor rellenar los campos");
            } else {
                agregarDetalleCita();
            }

//            objDetalleCita.insertarDetalleServicioNoRepetido(Integer.parseInt(txtNumero.getText()), tblDetalleServicio);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage() + " AL AGREGAR SERVICIO DESDE ATENCION");
        }
    }//GEN-LAST:event_btnAgregarServicioActionPerformed

    private void btnTerminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminarActionPerformed
        try {
            objDetalleMedicamento.registrarDetalleMedicamento(Integer.parseInt(txtNumero.getText()), tblDetalleMedicamento);
            objCita.terminarCita(Integer.parseInt(txtNumero.getText()));

            for (int i = 0; i < tblDetalleMedicamento.getRowCount(); i++) {
                int idMedicamento = Integer.parseInt(tblDetalleMedicamento.getValueAt(i, 0).toString());
                int cantidad = Integer.parseInt(tblDetalleMedicamento.getValueAt(i, 5).toString());
                objMedicamento.reducirStock(idMedicamento, cantidad);
            }

            String tipo;

            if (rdbBoleta.isSelected()) {
                tipo = "B";
            } else {
                tipo = "F";
            }

            String num = objComprobante.generarNumeroSerieComprobante();
            java.util.Date utilDate = jDateChooser1.getDate(); 
            java.sql.Date fecha = new java.sql.Date(utilDate.getTime());

            objComprobante.registrarComprobante(tipo, num, Float.parseFloat(txtTotal.getText()), fecha,
                    Integer.parseInt(txtNumero.getText()));

            JOptionPane.showMessageDialog(this, "La cita finalizó");
            limpiarTodoAlTerminar();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudo terminar " + e.getMessage());
        }
    }//GEN-LAST:event_btnTerminarActionPerformed

    private void tblDetalleMedicamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetalleMedicamentoMouseClicked
        txtCodMedicamento.setText(tblDetalleMedicamento.getValueAt(tblDetalleMedicamento.getSelectedRow(), 0).toString());
        btnBuscarMedicamentoActionPerformed(null);

        spnCantidad.setValue(Integer.parseInt(tblDetalleMedicamento.getValueAt(tblDetalleMedicamento.getSelectedRow(), 5).toString()));
        txtDosis.setText(tblDetalleMedicamento.getValueAt(tblDetalleMedicamento.getSelectedRow(), 3).toString());
        txtIndicacion.setText(tblDetalleMedicamento.getValueAt(tblDetalleMedicamento.getSelectedRow(), 4).toString());
    }//GEN-LAST:event_tblDetalleMedicamentoMouseClicked

    private void tblDetalleServicioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblDetalleServicioKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDetalleServicioKeyPressed

    private void tblDetalleServicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetalleServicioMouseClicked
        llenarServicioMedico();
    }//GEN-LAST:event_tblDetalleServicioMouseClicked

    private void cboEstadoCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboEstadoCitaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboEstadoCitaActionPerformed

    private void cboServiciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboServiciosActionPerformed
        String nom_servicio = cboServicios.getSelectedItem().toString();

        try {
            Integer codServicio = objServicio.obtenerID(nom_servicio);
            ResultSet rsServ = objServicio.buscarServicio(codServicio);
            txtCodServicio.setText(codServicio.toString());
            if (rsServ.next()) {
                txtDescripcionServicio.setText(rsServ.getString("descripcion"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_cboServiciosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarMedicamento;
    private javax.swing.JButton btnAgregarServicio;
    private javax.swing.JButton btnBuscarCita;
    private javax.swing.JButton btnBuscarDetalleServicio;
    private javax.swing.JButton btnBuscarDuenio;
    private javax.swing.JButton btnBuscarMascota;
    private javax.swing.JButton btnBuscarMedicamento;
    private javax.swing.JButton btnEliminarMedicamento;
    private javax.swing.JButton btnEliminarServicio;
    private javax.swing.JButton btnTerminar;
    private javax.swing.JComboBox<String> cboEstadoCita;
    private javax.swing.JComboBox<String> cboServicios;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
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
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
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
    private javax.swing.JPanel jPanel2;
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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JRadioButton rdbBoleta;
    private javax.swing.JRadioButton rdbCastrado;
    private javax.swing.JRadioButton rdbFactura;
    private javax.swing.JRadioButton rdbNoCastrado;
    private javax.swing.JSpinner spnCantidad;
    private javax.swing.JSpinner spnEdad;
    private javax.swing.JTable tblDetalleMedicamento;
    private javax.swing.JTable tblDetalleServicio;
    private javax.swing.JTextArea txtANotaAdicional;
    private javax.swing.JTextField txtCodDuenio;
    private javax.swing.JTextField txtCodMascota;
    private javax.swing.JTextField txtCodMedicamento;
    private javax.swing.JTextField txtCodServicio;
    private javax.swing.JTextField txtDescripcionServicio;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDniRuc;
    private javax.swing.JTextField txtDocMedico;
    private javax.swing.JTextField txtDosis;
    private javax.swing.JTextField txtEstadoSaludMascota;
    private javax.swing.JTextField txtIgv;
    private javax.swing.JTextField txtIndicacion;
    private javax.swing.JTextField txtNombreDuenio;
    private javax.swing.JTextField txtNombreMascota;
    private javax.swing.JTextField txtNombreMedicamento;
    private javax.swing.JTextField txtNombreMedico;
    private javax.swing.JTextField txtNotaMascota;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtRaza;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}

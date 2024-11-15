/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package capaPresentacion;

import capaNegocio.clsCita;
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
import java.sql.*;
import java.util.function.ObjDoubleConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Junior
 */
public class jdCita extends javax.swing.JDialog {

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

    public jdCita(java.awt.Frame parent, boolean modal) {
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

        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel24 = new javax.swing.JLabel();
        cboEstadoCita = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        btnEliminarProducto2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        btnBuscarCita = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        txtDniRuc = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNombreDuenio = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnBuscarDuenio = new javax.swing.JButton();
        txtCodDuenio = new javax.swing.JTextField();
        rdbBoleta = new javax.swing.JRadioButton();
        rdbFactura = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        rdbNoCastrado = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        txtRaza = new javax.swing.JTextField();
        txtNombreMascota = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        spnEdad = new javax.swing.JSpinner();
        txtEstadoSaludMascota = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtNotaMascota = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        btnBuscarMascota = new javax.swing.JButton();
        txtCodMascota = new javax.swing.JTextField();
        rdbCastrado = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtCodMedicamento = new javax.swing.JTextField();
        spnCantidad = new javax.swing.JSpinner();
        btnAgregarMedicamento = new javax.swing.JButton();
        btnEliminarMedicamento = new javax.swing.JButton();
        txtDosis = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtIndicacion = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        btnBuscarMedicamento = new javax.swing.JButton();
        txtNombreMedicamento = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtCodServicio = new javax.swing.JTextField();
        txtDocMedico = new javax.swing.JTextField();
        txtDescripcionServicio = new javax.swing.JTextField();
        txtNombreMedico = new javax.swing.JTextField();
        cboServicios = new javax.swing.JComboBox<>();
        btnBuscarDetalleServicio = new javax.swing.JButton();
        btnEliminarServicio = new javax.swing.JButton();
        btnAgregarServicio = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalleMedicamento = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDetalleServicio = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        txtTotal = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtANotaAdicional = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JTextField();
        txtIgv = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnTerminar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel8.setBackground(new java.awt.Color(102, 153, 255));
        jPanel8.setForeground(new java.awt.Color(255, 51, 0));

        jLabel11.setText("Número:");

        txtNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroActionPerformed(evt);
            }
        });

        jLabel24.setText("Estado:");

        cboEstadoCita.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboEstadoCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboEstadoCitaActionPerformed(evt);
            }
        });

        jLabel25.setText("Estado:");

        btnEliminarProducto2.setText("Ver adicionales");
        btnEliminarProducto2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProducto2ActionPerformed(evt);
            }
        });

        jLabel10.setText("Fecha:");

        btnBuscarCita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/buscar.png"))); // NOI18N
        btnBuscarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCitaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscarCita)
                .addGap(18, 18, 18)
                .addComponent(jLabel24)
                .addGap(18, 18, 18)
                .addComponent(cboEstadoCita, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel25)
                .addGap(18, 18, 18)
                .addComponent(btnEliminarProducto2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscarCita)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10))
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel24)
                                .addComponent(cboEstadoCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel25)
                                .addComponent(btnEliminarProducto2)))))
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(255, 51, 51));

        txtDniRuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDniRucKeyPressed(evt);
            }
        });

        jLabel2.setText("Dueño:");

        jLabel3.setText("Dirección:");

        jLabel8.setText("Teléfono:");

        btnBuscarDuenio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/buscar-pequeño.png"))); // NOI18N
        btnBuscarDuenio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarDuenioActionPerformed(evt);
            }
        });

        rdbBoleta.setText("Boleta");
        rdbBoleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbBoletaActionPerformed(evt);
            }
        });

        rdbFactura.setText("Factura");
        rdbFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbFacturaActionPerformed(evt);
            }
        });

        jLabel1.setText("Doc. de Ident.:");

        jLabel5.setText("Datos Dueño");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(txtDniRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscarDuenio))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(txtCodDuenio, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombreDuenio, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rdbBoleta)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdbFactura)))
                    .addComponent(txtDireccion))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscarDuenio)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtDniRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombreDuenio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(txtCodDuenio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdbBoleta)
                            .addComponent(rdbFactura))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 51, 51));

        rdbNoCastrado.setText("No castrado");
        rdbNoCastrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbNoCastradoActionPerformed(evt);
            }
        });

        jLabel7.setText("Nombre:");

        txtNombreMascota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreMascotaKeyPressed(evt);
            }
        });

        jLabel4.setText("Edad:");

        jLabel12.setText("Mascota:");

        jLabel19.setText("Nota:");

        jLabel20.setText("Raza:");

        btnBuscarMascota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/buscar-pequeño.png"))); // NOI18N
        btnBuscarMascota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarMascotaActionPerformed(evt);
            }
        });

        rdbCastrado.setText("Castrado");
        rdbCastrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbCastradoActionPerformed(evt);
            }
        });

        jLabel9.setText("Datos Mascota");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel12)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addComponent(txtNombreMascota, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnBuscarMascota))
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addComponent(txtCodMascota, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtEstadoSaludMascota, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                        .addComponent(rdbCastrado)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdbNoCastrado))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtRaza, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(txtNotaMascota, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(spnEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel9))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscarMascota)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtNombreMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEstadoSaludMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(txtCodMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtRaza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdbCastrado)
                            .addComponent(rdbNoCastrado))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtNotaMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(spnEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel13.setBackground(new java.awt.Color(255, 51, 51));

        jLabel15.setText("Dosis:");

        txtCodMedicamento.setMinimumSize(new java.awt.Dimension(61, 22));
        txtCodMedicamento.setPreferredSize(new java.awt.Dimension(61, 22));
        txtCodMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodMedicamentoActionPerformed(evt);
            }
        });

        btnAgregarMedicamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/veterinario.png"))); // NOI18N
        btnAgregarMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarMedicamentoActionPerformed(evt);
            }
        });

        btnEliminarMedicamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/eliminar.png"))); // NOI18N
        btnEliminarMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarMedicamentoActionPerformed(evt);
            }
        });

        txtDosis.setMinimumSize(new java.awt.Dimension(61, 22));
        txtDosis.setPreferredSize(new java.awt.Dimension(61, 22));
        txtDosis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDosisActionPerformed(evt);
            }
        });

        jLabel23.setText("Indicación:");

        txtIndicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIndicacionActionPerformed(evt);
            }
        });

        jLabel13.setText("Medicamento:");

        btnBuscarMedicamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/buscar-pequeño.png"))); // NOI18N
        btnBuscarMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarMedicamentoActionPerformed(evt);
            }
        });

        txtNombreMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreMedicamentoActionPerformed(evt);
            }
        });

        jLabel14.setText("Cantidad:");

        jLabel28.setText("Datos Medicamento");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCodMedicamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(spnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDosis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNombreMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(txtIndicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(btnEliminarMedicamento)
                                .addGap(18, 18, 18)
                                .addComponent(btnAgregarMedicamento))
                            .addComponent(btnBuscarMedicamento)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel28)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(btnBuscarMedicamento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminarMedicamento))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombreMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(txtCodMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(spnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(txtDosis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(txtIndicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnAgregarMedicamento))
                .addContainerGap())
        );

        jPanel14.setBackground(new java.awt.Color(255, 51, 51));

        jLabel22.setText("Médico:");

        jLabel21.setText("Servicio:");

        jLabel27.setText("Descrip:");

        txtCodServicio.setMinimumSize(new java.awt.Dimension(61, 22));
        txtCodServicio.setPreferredSize(new java.awt.Dimension(61, 22));
        txtCodServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodServicioActionPerformed(evt);
            }
        });

        txtDocMedico.setMinimumSize(new java.awt.Dimension(61, 22));
        txtDocMedico.setPreferredSize(new java.awt.Dimension(61, 22));
        txtDocMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDocMedicoActionPerformed(evt);
            }
        });

        txtDescripcionServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionServicioActionPerformed(evt);
            }
        });

        txtNombreMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreMedicoActionPerformed(evt);
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

        jLabel29.setText("Datos Servicio");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel21)
                    .addComponent(jLabel27))
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodServicio, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(txtDocMedico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboServicios, 0, 212, Short.MAX_VALUE)
                            .addComponent(txtNombreMedico)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtDescripcionServicio)))
                .addGap(32, 32, 32)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(btnEliminarServicio)
                        .addGap(18, 18, 18)
                        .addComponent(btnAgregarServicio))
                    .addComponent(btnBuscarDetalleServicio))
                .addContainerGap())
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(btnBuscarDetalleServicio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAgregarServicio)
                            .addComponent(btnEliminarServicio)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtCodServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboServicios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txtDocMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(txtDescripcionServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(51, 0, 51));

        tblDetalleMedicamento.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDetalleMedicamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetalleMedicamentoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDetalleMedicamento);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));

        tblDetalleServicio.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDetalleServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetalleServicioMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDetalleServicio);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(102, 102, 0));

        txtANotaAdicional.setColumns(20);
        txtANotaAdicional.setRows(5);
        jScrollPane3.setViewportView(txtANotaAdicional);

        jLabel16.setText("Nota adicional:");

        jLabel26.setText("Subtotal:");

        jLabel17.setText("IGV:");

        jLabel18.setText("TOTAL:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 225, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSubtotal)
                    .addComponent(txtIgv)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtIgv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(102, 0, 102));

        btnTerminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/veterinario.png"))); // NOI18N
        btnTerminar.setText("Terminar");
        btnTerminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerminarActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/editar.png"))); // NOI18N
        jButton2.setText("Reprogramar");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/darBaja.png"))); // NOI18N
        jButton3.setText("Cancelar");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTerminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTerminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
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
                txtNombreMascota.setText(nombreMascota);
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

    private void txtNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroActionPerformed

    private void txtNombreMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreMedicamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreMedicamentoActionPerformed

    private void txtCodMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodMedicamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodMedicamentoActionPerformed

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
                    JOptionPane.showMessageDialog(this, "si llego");
                    agregarMedicamento(codMedicamento, cantidad, dosis, indicacion);
                    JOptionPane.showMessageDialog(this, "pa aca tmb");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
            }
        }

        calcularTotales();
    }//GEN-LAST:event_btnAgregarMedicamentoActionPerformed

    private void btnEliminarMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarMedicamentoActionPerformed
//        eliminarProducto(Integer.parseInt(txtCodProducto.getText()));
//        txtCodProducto.setText("");
//        txtNombreProducto.setText("");
//        jSpinner1.setValue(0);
    }//GEN-LAST:event_btnEliminarMedicamentoActionPerformed

    private void txtDosisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDosisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDosisActionPerformed

    private void txtDniRucKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniRucKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            if (txtDniRuc.getText().length() == 8 || txtDniRuc.getText().length() == 11) {
                btnBuscarCita.requestFocus();
                btnBuscarDuenioActionPerformed(null);

            } else {
            }
            JOptionPane.showMessageDialog(this, "Ingresar DNI (8 dígitos) / RUC (11 dígitos)");
        } else {
        }
    }//GEN-LAST:event_txtDniRucKeyPressed

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
                    jdMantDuenio objMantDuenio = new jdMantDuenio(null, true);
                    objMantDuenio.setLocationRelativeTo(this);
                    objMantDuenio.setVisible(true);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }//GEN-LAST:event_btnBuscarDuenioActionPerformed

    private void rdbBoletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbBoletaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbBoletaActionPerformed

    private void rdbFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbFacturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbFacturaActionPerformed

    private void txtNombreMascotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreMascotaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreMascotaKeyPressed

    private void btnBuscarMascotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarMascotaActionPerformed
        ResultSet rsMascota = null;

        try {

            if (txtCodDuenio.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un dueño para buscar mascotas");
            } else if (txtNombreMascota.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar el nombre de la mascota");
            } else {
                rsMascota = objMascota.filtrarMascotaPorDuenioYNombre(Integer.valueOf(txtCodDuenio.getText()),
                        txtNombreMascota.getText());

                if (rsMascota.next()) {
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
                        jdMantMascota objMantMascota = new jdMantMascota(null, true);
                        objMantMascota.setLocationRelativeTo(this);
                        objMantMascota.setVisible(true);
                    }
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnBuscarMascotaActionPerformed

    private void rdbCastradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbCastradoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbCastradoActionPerformed

    private void rdbNoCastradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbNoCastradoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbNoCastradoActionPerformed

    private void btnEliminarProducto2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProducto2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarProducto2ActionPerformed

    private void txtDocMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDocMedicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDocMedicoActionPerformed

    private void btnEliminarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarServicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarServicioActionPerformed

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

    private void txtCodServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodServicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodServicioActionPerformed

    private void txtNombreMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreMedicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreMedicoActionPerformed

    private void txtIndicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIndicacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIndicacionActionPerformed

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

    private void txtDescripcionServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionServicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionServicioActionPerformed

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

    private void tblDetalleServicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetalleServicioMouseClicked
        llenarServicioMedico();
    }//GEN-LAST:event_tblDetalleServicioMouseClicked

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

    private void tblDetalleMedicamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetalleMedicamentoMouseClicked
        txtCodMedicamento.setText(tblDetalleMedicamento.getValueAt(tblDetalleMedicamento.getSelectedRow(), 0).toString());
        btnBuscarMedicamentoActionPerformed(null);

        spnCantidad.setValue(Integer.parseInt(tblDetalleMedicamento.getValueAt(tblDetalleMedicamento.getSelectedRow(), 5).toString()));
        txtDosis.setText(tblDetalleMedicamento.getValueAt(tblDetalleMedicamento.getSelectedRow(), 3).toString());
        txtIndicacion.setText(tblDetalleMedicamento.getValueAt(tblDetalleMedicamento.getSelectedRow(), 4).toString());
    }//GEN-LAST:event_tblDetalleMedicamentoMouseClicked

    private void btnTerminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminarActionPerformed
        try {
            objDetalleMedicamento.registrarDetalleMedicamento(Integer.parseInt(txtNumero.getText()), tblDetalleMedicamento);
            objCita.terminarCita(Integer.parseInt(txtNumero.getText()));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudo guardar medic " + e.getMessage());
        }
    }//GEN-LAST:event_btnTerminarActionPerformed

    private void cboEstadoCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboEstadoCitaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboEstadoCitaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarMedicamento;
    private javax.swing.JButton btnAgregarServicio;
    private javax.swing.JButton btnBuscarCita;
    private javax.swing.JButton btnBuscarDetalleServicio;
    private javax.swing.JButton btnBuscarDuenio;
    private javax.swing.JButton btnBuscarMascota;
    private javax.swing.JButton btnBuscarMedicamento;
    private javax.swing.JButton btnEliminarMedicamento;
    private javax.swing.JButton btnEliminarProducto2;
    private javax.swing.JButton btnEliminarServicio;
    private javax.swing.JButton btnTerminar;
    private javax.swing.JComboBox<String> cboEstadoCita;
    private javax.swing.JComboBox<String> cboServicios;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
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

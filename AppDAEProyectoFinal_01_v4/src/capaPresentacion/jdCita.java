/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package capaPresentacion;

import capaNegocio.clsCita;
import capaNegocio.clsDuenio;
import capaNegocio.clsMascota;
import capaNegocio.clsMedicamento;
import capaNegocio.clsMedico;
import capaNegocio.clsServicio;
import java.awt.Frame;
import java.sql.*;
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

    public jdCita(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setTitle("Gestión de Cita");

    }

    //para agregar medicamentos debe estar llena la parte de servicio en el formulario u.u
//    private void agregarMedicamento(int medicamento, int cantidad, float dosis, String indicacion) {
//        if (medicamento != 0 && cantidad != 0) {
//            ResultSet rs = null;
//
//            try {
//                DefaultTableModel modelo = (DefaultTableModel) tblDetalleMedicamento.getModel();
//                boolean repetido = false;
//                int fila = -1;
//
//                for (int i = 0; i < tblDetalleMedicamento.getRowCount(); i++) {
//                    if (Integer.parseInt(String.valueOf(tblDetalleMedicamento.getValueAt(i, 0))) == medicamento) {
//                        repetido = true;
//                        fila = i;
//                    }
//
//                    if (repetido) {
//                        int aux = Integer.parseInt(String.valueOf(tblDetalleMedicamento.getValueAt(i, 3)));
//
//                        cantidad += aux;
//                        modelo.removeRow(fila);
//                    }
//
//                }
//
//                int stock = objMedicamento.getStock(medicamento);
//
//                if (cantidad > stock) {
//                    cantidad = stock;
//                    JOptionPane.showMessageDialog(rootPane, "Stock Insuficiente");
//                } else {
//                    rs = objProducto.buscarProducto(producto);
//
//                    while (rs.next()) {
//                        modelo.addRow(new Object[]{rs.getString("codproducto"), rs.getString("nomproducto"),
//                            rs.getString("precio"), cantidad, descuento + "%",
//                            rs.getFloat("precio") - descuento * rs.getFloat("precio") / 100,
//                            (cantidad * (rs.getFloat("precio") - descuento * rs.getFloat("precio") / 100))});
//                    }
//
//                }
//
//                tblDetalleMedicamento.setModel(modelo);
//
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(this, "Error al calcular el total" + e.getMessage());
//            }
//        }
//        calcularTotal();
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtIgv = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtANotaAdicional = new javax.swing.JTextArea();
        jLabel26 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnDarBaja = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalleMedicamento = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        btnBuscarCita = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel24 = new javax.swing.JLabel();
        cboEstadoCita = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        btnEliminarProducto2 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtNombreProducto = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtCodProducto = new javax.swing.JTextField();
        jSpinner1 = new javax.swing.JSpinner();
        btnAgregarMedicamento = new javax.swing.JButton();
        btnEliminarMedicamento = new javax.swing.JButton();
        txtDosis = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtIndicacion = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDniRuc = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnBuscarDuenio = new javax.swing.JButton();
        txtCodDuenio = new javax.swing.JTextField();
        rdbBoleta = new javax.swing.JRadioButton();
        rdbFactura = new javax.swing.JRadioButton();
        txtTipo = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtNombreMascota = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtEdadMascota = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtNotaMascota = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        btnBuscarMascota = new javax.swing.JButton();
        txtCodMascota = new javax.swing.JTextField();
        rdbCastrado = new javax.swing.JRadioButton();
        rdbNoCastrado = new javax.swing.JRadioButton();
        txtTipo2 = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        txtNombreServicio = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtCodServicio = new javax.swing.JTextField();
        btnAgregarServicio = new javax.swing.JButton();
        btnEliminarServicio = new javax.swing.JButton();
        txtCodMedico = new javax.swing.JTextField();
        txtNombreMedico = new javax.swing.JTextField();
        btnBuscarServicio = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        txtDescripcionServicio = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDetalleServicio = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel5.setBackground(new java.awt.Color(204, 255, 255));

        jLabel16.setText("Nota adicional:");

        jLabel17.setText("IGV:");

        jLabel18.setText("TOTAL:");

        txtANotaAdicional.setColumns(20);
        txtANotaAdicional.setRows(5);
        jScrollPane3.setViewportView(txtANotaAdicional);

        jLabel26.setText("Subtotal:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSubtotal)
                    .addComponent(txtIgv)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtIgv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/veterinario.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/editar.png"))); // NOI18N
        btnModificar.setText("Modificar");

        btnDarBaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/darBaja.png"))); // NOI18N
        btnDarBaja.setText("Dar baja");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDarBaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDarBaja)
                .addGap(63, 63, 63))
        );

        jPanel3.setBackground(new java.awt.Color(51, 0, 51));

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
        jScrollPane1.setViewportView(tblDetalleMedicamento);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        jLabel10.setText("Fecha:");

        btnBuscarCita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/buscar.png"))); // NOI18N
        btnBuscarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCitaActionPerformed(evt);
            }
        });

        jLabel11.setText("Número:");

        txtNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroActionPerformed(evt);
            }
        });

        jLabel24.setText("Estado:");

        cboEstadoCita.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel25.setText("Estado:");

        btnEliminarProducto2.setText("Ver adicionales");
        btnEliminarProducto2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProducto2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnBuscarCita))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(jLabel11)
                                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel24)
                                .addComponent(cboEstadoCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel25)
                                .addComponent(btnEliminarProducto2)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(204, 255, 204));

        jLabel13.setText("Medicamento:");

        txtNombreProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreProductoActionPerformed(evt);
            }
        });

        jLabel14.setText("Cantidad:");

        jLabel15.setText("Dosis:");

        txtCodProducto.setMinimumSize(new java.awt.Dimension(61, 22));
        txtCodProducto.setPreferredSize(new java.awt.Dimension(61, 22));
        txtCodProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodProductoActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel23)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCodProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSpinner1))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDosis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(txtIndicacion))
                .addGap(26, 26, 26)
                .addComponent(btnEliminarMedicamento)
                .addGap(18, 18, 18)
                .addComponent(btnAgregarMedicamento)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAgregarMedicamento)
                    .addComponent(btnEliminarMedicamento)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(txtCodProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(txtDosis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(txtIndicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setText("Doc. de Ident.:");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtDniRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscarDuenio))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtCodDuenio, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombreCliente)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rdbBoleta)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdbFactura)
                            .addComponent(txtTipo)))
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscarDuenio)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtDniRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(txtCodDuenio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdbBoleta)
                            .addComponent(rdbFactura))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(204, 255, 204));

        jLabel7.setText("Nombre:");

        txtNombreMascota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreMascotaKeyPressed(evt);
            }
        });

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

        rdbNoCastrado.setText("No castrado");
        rdbNoCastrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbNoCastradoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel12)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(txtNombreMascota, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscarMascota))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(txtCodMascota, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEdadMascota)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(rdbCastrado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdbNoCastrado))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTipo2)))
                        .addGap(12, 12, 12))
                    .addComponent(txtNotaMascota))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscarMascota)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtNombreMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEdadMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(txtCodMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTipo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdbCastrado)
                            .addComponent(rdbNoCastrado))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtNotaMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(204, 255, 204));

        jLabel21.setText("Servicio:");

        txtNombreServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreServicioActionPerformed(evt);
            }
        });

        jLabel22.setText("Médico:");

        txtCodServicio.setMinimumSize(new java.awt.Dimension(61, 22));
        txtCodServicio.setPreferredSize(new java.awt.Dimension(61, 22));
        txtCodServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodServicioActionPerformed(evt);
            }
        });

        btnAgregarServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/veterinario.png"))); // NOI18N
        btnAgregarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarServicioActionPerformed(evt);
            }
        });

        btnEliminarServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/eliminar.png"))); // NOI18N
        btnEliminarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarServicioActionPerformed(evt);
            }
        });

        txtCodMedico.setMinimumSize(new java.awt.Dimension(61, 22));
        txtCodMedico.setPreferredSize(new java.awt.Dimension(61, 22));
        txtCodMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodMedicoActionPerformed(evt);
            }
        });

        txtNombreMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreMedicoActionPerformed(evt);
            }
        });

        btnBuscarServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/buscar-pequeño.png"))); // NOI18N
        btnBuscarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarServicioActionPerformed(evt);
            }
        });

        jLabel27.setText("Descrip:");

        txtDescripcionServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionServicioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel21)
                    .addComponent(jLabel27))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCodServicio, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                            .addComponent(txtCodMedico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombreServicio)
                            .addComponent(txtNombreMedico)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtDescripcionServicio)))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(btnEliminarServicio)
                        .addGap(18, 18, 18)
                        .addComponent(btnAgregarServicio))
                    .addComponent(btnBuscarServicio))
                .addGap(14, 14, 14))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(btnBuscarServicio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAgregarServicio)
                            .addComponent(btnEliminarServicio)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombreServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21)
                            .addComponent(txtCodServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txtCodMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(txtDescripcionServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(12, 12, 12))
        );

        jPanel7.setBackground(new java.awt.Color(51, 0, 51));

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
        jScrollPane2.setViewportView(tblDetalleServicio);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnBuscarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCitaActionPerformed
//        ResultSet rsVenta = null;
//        ResultSet rsDetalle = null;
//
//        try {
//            if (rsVenta.next()) {
//                llenarTablaInicial();
//                DefaultTableModel modelo = (DefaultTableModel) tblDetalleMedicamento.getModel();
//                txtDniRuc.setText(rsVenta.getString("dni"));
//                btnBuscarClienteActionPerformed(evt);
//                jDateChooser1.setDate(rsVenta.getDate("fecha"));
//                rsDetalle = objVenta.listarDetalleVenta(Integer.parseInt(txtNumero.getText()));
//
//                while(rsDetalle.next()){
//                    modelo.addRow(new Object[]{rsDetalle.getString("codProducto"),
//                        rsDetalle.getString("nomProducto"), rsDetalle.getString("preciVenta"),
//                        rsDetalle.getString("cantidad"), rsDetalle.getString("descuento") + "%",
//                        rsDetalle.getString("precioVenta"), rsDetalle.getFloat("subtotal")});
//            }
//        }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, e.getMessage());
//        }

    }//GEN-LAST:event_btnBuscarCitaActionPerformed

    private void txtNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroActionPerformed

    private void txtNombreProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreProductoActionPerformed

    private void txtCodProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodProductoActionPerformed

    private void btnAgregarMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarMedicamentoActionPerformed
        jdAniadirMedicamento objAniadirMedicamento
                = new jdAniadirMedicamento((Frame) SwingUtilities.getWindowAncestor(this), true);
        objAniadirMedicamento.setLocationRelativeTo(this);
        objAniadirMedicamento.setVisible(true);

        int producto = objAniadirMedicamento.getCod();
        int cantidad = objAniadirMedicamento.getCant();
        float dosis = objAniadirMedicamento.getDosis();

        try {
//            agregarProducto(producto, cantidad, descuento);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
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

            rsCliente = objDuenio.buscarDuenio(txtDniRuc.getText());

            if (rsCliente.next()) {
                txtCodDuenio.setText(String.valueOf(rsCliente.getString("id")));
                txtNombreCliente.setText(String.valueOf(rsCliente.getString("nombres") + " " + rsCliente.getString("apepaterno")
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
                    txtEdadMascota.setText(String.valueOf(objMascota.calcularEdadMascota(rsMascota.getInt("id"))));

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

    private void txtCodMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodMedicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodMedicoActionPerformed

    private void btnEliminarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarServicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarServicioActionPerformed

    private void btnAgregarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarServicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarServicioActionPerformed

    private void txtCodServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodServicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodServicioActionPerformed

    private void txtNombreServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreServicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreServicioActionPerformed

    private void txtNombreMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreMedicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreMedicoActionPerformed

    private void txtIndicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIndicacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIndicacionActionPerformed

    private void btnBuscarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarServicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarServicioActionPerformed

    private void txtDescripcionServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionServicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionServicioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarMedicamento;
    private javax.swing.JButton btnAgregarServicio;
    private javax.swing.JButton btnBuscarCita;
    private javax.swing.JButton btnBuscarDuenio;
    private javax.swing.JButton btnBuscarMascota;
    private javax.swing.JButton btnBuscarServicio;
    private javax.swing.JButton btnDarBaja;
    private javax.swing.JButton btnEliminarMedicamento;
    private javax.swing.JButton btnEliminarProducto2;
    private javax.swing.JButton btnEliminarServicio;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cboEstadoCita;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JRadioButton rdbBoleta;
    private javax.swing.JRadioButton rdbCastrado;
    private javax.swing.JRadioButton rdbFactura;
    private javax.swing.JRadioButton rdbNoCastrado;
    private javax.swing.JTable tblDetalleMedicamento;
    private javax.swing.JTable tblDetalleServicio;
    private javax.swing.JTextArea txtANotaAdicional;
    private javax.swing.JTextField txtCodDuenio;
    private javax.swing.JTextField txtCodMascota;
    private javax.swing.JTextField txtCodMedico;
    private javax.swing.JTextField txtCodProducto;
    private javax.swing.JTextField txtCodServicio;
    private javax.swing.JTextField txtDescripcionServicio;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDniRuc;
    private javax.swing.JTextField txtDosis;
    private javax.swing.JTextField txtEdadMascota;
    private javax.swing.JTextField txtIgv;
    private javax.swing.JTextField txtIndicacion;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombreMascota;
    private javax.swing.JTextField txtNombreMedico;
    private javax.swing.JTextField txtNombreProducto;
    private javax.swing.JTextField txtNombreServicio;
    private javax.swing.JTextField txtNotaMascota;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTipo;
    private javax.swing.JTextField txtTipo2;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
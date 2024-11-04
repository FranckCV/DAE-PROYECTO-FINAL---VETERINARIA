/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package capaPresentacion;
import capaNegocio.*;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


import capaNegocio.clsCita;
import capaNegocio.clsDuenio;
import capaNegocio.clsMascota;
import capaNegocio.clsMedicamento;
import capaNegocio.clsMedico;
import capaNegocio.clsServicio;
import java.awt.Frame;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Junior
 */
public class jdProgramacionCita extends javax.swing.JDialog {
    clsServicio objServicio = new clsServicio();
    clsMascota objMascota = new clsMascota();

<<<<<<< HEAD
    clsMedicamento objMedicamento = new clsMedicamento();
    clsCita objCita = new clsCita();
    clsDuenio objDuenio = new clsDuenio();
    clsMedico objMedico = new clsMedico();
    clsServicio objServicio = new clsServicio();
    clsMascota objMascota = new clsMascota();

=======
    clsDuenio objDuenio = new clsDuenio();


    
    /**
     * Creates new form jdProgramacionCita
     */
>>>>>>> 4e92a84c02e100b9a260d1b6b506706f9bf2559f
    public jdProgramacionCita(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        llenarTablaInicial();
    }

    private void llenarTablaInicial() {
        ResultSet rsServicios = null;
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID SERV_MED");
        modelo.addColumn("SERVICIO");
        modelo.addColumn("MEDICO");
        modelo.addColumn("COSTO");

        tblDetalleServicio.setModel(modelo);
    }

    private void agregarServicio(int codServ, int codMed) {
    if (codServ != -1 && codMed != -1) {
        ResultSet rs = null;

        try {
            DefaultTableModel modelo = (DefaultTableModel) tblDetalleServicio.getModel();
            boolean repetido = false;

            // Verificar si el servicio ya está en la tabla
            for (int i = 0; i < tblDetalleServicio.getRowCount(); i++) {
                String cadena = String.valueOf(tblDetalleServicio.getValueAt(i, 0));
                String[] codigos = cadena.split(" - ");
                int codigoTablaSer = Integer.parseInt(codigos[0].trim());
                int codTablaMed = Integer.parseInt(codigos[1].trim());

                if (codigoTablaSer == codServ && codTablaMed == codMed) {
                    repetido = true;
                    break; // Rompe el bucle si ya se encontró el servicio repetido
                }
            }

            // Mostrar mensaje si el servicio ya está en la tabla
            if (repetido) {
                JOptionPane.showMessageDialog(rootPane, "Este servicio ya se encuentra agregado");
            } else {
                // Obtener datos del servicio y agregarlos a la tabla si no está repetido
                rs = objServicio.obtenerDatosDetalleServicio(codServ, codMed);

                if (rs == null) {
                    JOptionPane.showMessageDialog(this, "Error: El ResultSet está vacío.");
                    return;
                }

                boolean hasData = false; // Para verificar si hay datos en el ResultSet
                while (rs.next()) {
                    hasData = true;
                    modelo.addRow(new Object[]{
                        rs.getInt("ID") + " - " + rs.getInt("medicoid"),
                        rs.getString("nom_servicio"),
                        rs.getString("nombres") + " " + rs.getString("apepaterno"),
                        rs.getString("costo")
                    });
                }

                if (!hasData) {
                    JOptionPane.showMessageDialog(this, "No se encontraron datos para el servicio y médico especificados.");
                }

                tblDetalleServicio.setModel(modelo); // Asegurar que el modelo actualizado esté en la tabla
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al llenar tabla detalle servicio: " + e.getMessage());
        }
    }
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnDarBaja = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtCodServicio = new javax.swing.JTextField();
        btnAgregarServicio = new javax.swing.JButton();
        btnEliminarServicio = new javax.swing.JButton();
        txtDocMedico = new javax.swing.JTextField();
        txtNombreMedico = new javax.swing.JTextField();
        btnBuscarDetalleServicio = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        txtDescripcionServicio = new javax.swing.JTextField();
        cboServicios = new javax.swing.JComboBox<>();
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDocDuenio = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnBuscarDuenio = new javax.swing.JButton();
        txtCodDuenio = new javax.swing.JTextField();
        txtTipo = new javax.swing.JTextField();
        rdbBoleta = new javax.swing.JRadioButton();
        rdbFactura = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalleServicio = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtNombreMascota = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtEdadMascota = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtNotaMascota = new javax.swing.JTextField();
        btnBuscarMascota = new javax.swing.JButton();
        txtCodMascota = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtEdadMascota1 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtNotaMascota1 = new javax.swing.JTextField();
        txtCodMascota1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        jPanel10.setBackground(new java.awt.Color(204, 255, 204));

        jLabel21.setText("Servicio:");

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

        txtDocMedico.setMinimumSize(new java.awt.Dimension(61, 22));
        txtDocMedico.setPreferredSize(new java.awt.Dimension(61, 22));
        txtDocMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDocMedicoActionPerformed(evt);
            }
        });

        txtNombreMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreMedicoActionPerformed(evt);
            }
        });

        btnBuscarDetalleServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/buscar-pequeño.png"))); // NOI18N
        btnBuscarDetalleServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarDetalleServicioActionPerformed(evt);
            }
        });

        jLabel27.setText("Descrip:");

        txtDescripcionServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionServicioActionPerformed(evt);
            }
        });

        cboServicios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboServicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboServiciosActionPerformed(evt);
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
                            .addComponent(txtCodServicio, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(txtDocMedico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombreMedico)
                            .addComponent(cboServicios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtDescripcionServicio)))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(btnEliminarServicio)
                        .addGap(18, 18, 18)
                        .addComponent(btnAgregarServicio))
                    .addComponent(btnBuscarDetalleServicio))
                .addGap(14, 14, 14))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(btnBuscarDetalleServicio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAgregarServicio)
                            .addComponent(btnEliminarServicio)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtCodServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboServicios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txtDocMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(txtDescripcionServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(12, 12, 12))
        );

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
                    .addComponent(jLabel16)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIgv, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
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
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setText("Doc. Ide:");

        txtDocDuenio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDocDuenioKeyPressed(evt);
            }
        });

        jLabel2.setText("Dueño:");

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
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtDocDuenio, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarDuenio))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtCodDuenio, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreCliente)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rdbBoleta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdbFactura)))
                .addGap(98, 98, 98))
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
                                .addComponent(txtDocDuenio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(51, 0, 51));

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
        jScrollPane1.setViewportView(tblDetalleServicio);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        jLabel10.setText("Día de la cita:");

        jLabel11.setText("Número:");

        txtNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jLabel11)
                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
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

        btnBuscarMascota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/buscar-pequeño.png"))); // NOI18N
        btnBuscarMascota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarMascotaActionPerformed(evt);
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
                        .addComponent(txtNombreMascota, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarMascota))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txtCodMascota, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEdadMascota))
                    .addComponent(txtNotaMascota))
                .addGap(0, 20, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscarMascota)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNombreMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEdadMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtCodMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtNotaMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(204, 255, 204));

        jLabel13.setText("Hora de entrada - Hora de salida");

        jLabel23.setText("Nota:");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(txtCodMascota1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtEdadMascota1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(18, 18, 18)
                                .addComponent(txtNotaMascota1))))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel13)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEdadMascota1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodMascota1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNotaMascota1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtCodServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodServicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodServicioActionPerformed

    private void btnAgregarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarServicioActionPerformed

        jdAniadirServicio objAniadirServicio
                = new jdAniadirServicio((Frame) SwingUtilities.getWindowAncestor(this), true);
        objAniadirServicio.setLocationRelativeTo(this);
        objAniadirServicio.setVisible(true);

        int codServicio = objAniadirServicio.getCodServicio();
        int codMedico = objAniadirServicio.getCodMedico();
        try {
            System.out.println(codServicio + " - " + codMedico);
            agregarServicio(codServicio, codMedico);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }//GEN-LAST:event_btnAgregarServicioActionPerformed

    private void btnEliminarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarServicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarServicioActionPerformed

    private void txtDocMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDocMedicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDocMedicoActionPerformed

    private void txtNombreMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreMedicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreMedicoActionPerformed

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

    private void cboServiciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboServiciosActionPerformed
        String nom_servicio = cboServicios.getSelectedItem().toString();

        try {
            Integer codServicio = objServicio.obtenerID(nom_servicio);
            txtCodServicio.setText(codServicio.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_cboServiciosActionPerformed

<<<<<<< HEAD
    private void txtDocDuenioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDocDuenioKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            if (txtDocDuenio.getText().length() == 8 || txtDocDuenio.getText().length() == 11) {
                btnBuscarDuenio.requestFocus();
                btnBuscarDuenioActionPerformed(null);

            } else {
                JOptionPane.showMessageDialog(this, "Ingresar DNI (8 dígitos) / RUC (11 dígitos)");
            }
        } else {
        }
    }//GEN-LAST:event_txtDocDuenioKeyPressed

    private void btnBuscarDuenioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarDuenioActionPerformed
        ResultSet rsCliente = null;

        try {

            rsCliente = objDuenio.buscarDuenio(txtDocDuenio.getText());

            if (rsCliente.next()) {
                txtCodDuenio.setText(String.valueOf(rsCliente.getString("id")));
                txtNombreCliente.setText(String.valueOf(rsCliente.getString("nombres") + " " + rsCliente.getString("apepaterno")
                        + " " + rsCliente.getString("apematerno")));

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
=======
    private void txtDniRucKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniRucKeyPressed
//        if (evt.getKeyCode() == evt.VK_ENTER) {
//            if (txtDniRuc.getText().length() == 8 || txtDniRuc.getText().length() == 11) {
//                btnBuscarCita.requestFocus();
//                btnBuscarDuenioActionPerformed(null);
//
//            } else {
//            }
//            JOptionPane.showMessageDialog(this, "Ingresar DNI (8 dígitos) / RUC (11 dígitos)");
//        } else {
//        }
    }//GEN-LAST:event_txtDniRucKeyPressed

    private void btnBuscarDuenioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarDuenioActionPerformed
//        ResultSet rsCliente = null;
//
//        try {
//
//            rsCliente = objDuenio.buscarDuenio(txtDniRuc.getText());
//
//            if (rsCliente.next()) {
//                txtCodDuenio.setText(String.valueOf(rsCliente.getString("id")));
//                txtNombreCliente.setText(String.valueOf(rsCliente.getString("nombres") + " " + rsCliente.getString("apepaterno")
//                    + " " + rsCliente.getString("apematerno")));
//            txtDireccion.setText(String.valueOf(rsCliente.getString("direccion")));
//
//            if (rsCliente.getString("doc_identidad").length() != 11) {
//                rdbBoleta.setSelected(true);
//            } else {
//                rdbFactura.setSelected(true);
//            }
//        } else {
//            if (JOptionPane.showConfirmDialog(this, "Dueño no existe ¿Desea registrarlo?", "Alerta!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
//                jdMantDuenio objMantDuenio = new jdMantDuenio(null, true);
//                objMantDuenio.setLocationRelativeTo(this);
//                objMantDuenio.setVisible(true);
//            }
//        }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
//        }
>>>>>>> 4e92a84c02e100b9a260d1b6b506706f9bf2559f
    }//GEN-LAST:event_btnBuscarDuenioActionPerformed

    private void txtNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroActionPerformed

    private void txtNombreMascotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreMascotaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreMascotaKeyPressed

    private void btnBuscarMascotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarMascotaActionPerformed
<<<<<<< HEAD
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

=======
//        ResultSet rsMascota = null;
//
//        try {
//
//            if (txtCodDuenio.getText().isEmpty()) {
//                JOptionPane.showMessageDialog(this, "Debe ingresar un dueño para buscar mascotas");
//            } else if (txtNombreMascota.getText().isEmpty()) {
//                JOptionPane.showMessageDialog(this, "Debe ingresar el nombre de la mascota");
//            } else {
//                rsMascota = objMascota.filtrarMascotaPorDuenioYNombre(Integer.valueOf(txtCodDuenio.getText()),
//                    txtNombreMascota.getText());
//
//                if (rsMascota.next()) {
//                    txtCodMascota.setText(String.valueOf(rsMascota.getString("id")));
//                    txtNotaMascota.setText(String.valueOf(rsMascota.getString("notaAdicional")));
//                    txtEdadMascota.setText(String.valueOf(objMascota.calcularEdadMascota(rsMascota.getInt("id"))));
//
>>>>>>> 4e92a84c02e100b9a260d1b6b506706f9bf2559f
//                    if (rsMascota.getBoolean("esterilizado")) {
//                        rdbCastrado.setSelected(true);
//                    } else {
//                        rdbNoCastrado.setSelected(true);
//                    }
<<<<<<< HEAD
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
=======
//                } else {
//                    if (JOptionPane.showConfirmDialog(this, "Mascota no existe ¿Desea registrar?", "Alerta!",
//                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
//                    jdMantMascota objMantMascota = new jdMantMascota(null, true);
//                    objMantMascota.setLocationRelativeTo(this);
//                    objMantMascota.setVisible(true);
//                }
//            }
//        }
//
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
//        }
>>>>>>> 4e92a84c02e100b9a260d1b6b506706f9bf2559f
    }//GEN-LAST:event_btnBuscarMascotaActionPerformed

    private void txtDescripcionServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionServicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionServicioActionPerformed

    private void rdbBoletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbBoletaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbBoletaActionPerformed

    private void rdbFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbFacturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbFacturaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarServicio;
    private javax.swing.JButton btnBuscarDetalleServicio;
    private javax.swing.JButton btnBuscarDuenio;
    private javax.swing.JButton btnBuscarMascota;
    private javax.swing.JButton btnDarBaja;
    private javax.swing.JButton btnEliminarServicio;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cboServicios;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton rdbBoleta;
    private javax.swing.JRadioButton rdbFactura;
    private javax.swing.JTable tblDetalleServicio;
    private javax.swing.JTextArea txtANotaAdicional;
    private javax.swing.JTextField txtCodDuenio;
    private javax.swing.JTextField txtCodMascota;
    private javax.swing.JTextField txtCodMascota1;
    private javax.swing.JTextField txtCodServicio;
    private javax.swing.JTextField txtDescripcionServicio;
    private javax.swing.JTextField txtDocDuenio;
    private javax.swing.JTextField txtDocMedico;
    private javax.swing.JTextField txtEdadMascota;
    private javax.swing.JTextField txtEdadMascota1;
    private javax.swing.JTextField txtIgv;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombreMascota;
    private javax.swing.JTextField txtNombreMedico;
    private javax.swing.JTextField txtNotaMascota;
    private javax.swing.JTextField txtNotaMascota1;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTipo;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}

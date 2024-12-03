/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package capaPresentacion;

import capaNegocio.clsCustodia;
import capaNegocio.clsDuenio;
import capaNegocio.clsEspecialidad;
import capaNegocio.clsMascota;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import soporte.Utilidad;

/**
 *
 * @author Windows10
 */
public class jdCustodia1_v2 extends javax.swing.JDialog {

    clsMascota objMas = new clsMascota();
    clsDuenio objDue = new clsDuenio();
    clsCustodia objCus = new clsCustodia();
    jdCustodiaDue_v2 objagregarDue = new jdCustodiaDue_v2((Frame) SwingUtilities.getWindowAncestor(this), true);
    jdCustodiaMasc_v2 objagregarMas;
    private Integer codigoDuenio = null;
    private Integer codigoMascota = null;

    public jdCustodia1_v2(java.awt.Frame parent, boolean modal) throws SQLException, Exception {
        super(parent, modal);
        this.objagregarMas = new jdCustodiaMasc_v2((Frame) SwingUtilities.getWindowAncestor(this), true);
        initComponents();
        listarCustodia();
        btnDue.setEnabled(false);
        btnMas.setEnabled(false);
        dtcFechaNacimiento.setEnabled(false);

    }

    private void listarCustodia() throws SQLException {
        ResultSet rsMas = null;
        DefaultTableModel modeloM = new DefaultTableModel();
        modeloM.addColumn("Id Dueño");
        modeloM.addColumn("Nombre Dueño");
        modeloM.addColumn("Id Mascota");
        modeloM.addColumn("Nombre Mascota");
        modeloM.addColumn("Fecha Adopción");
        tblCustodia.setModel(modeloM);
        try {
            rsMas = objCus.listarCustodia();  // Obtener el ResultSet
            while (rsMas.next()) {
                String ado = rsMas.getDate("fa") != null ? rsMas.getDate("fa").toString() : null;
                modeloM.addRow(new Object[]{
                    rsMas.getInt("id_due"),
                    rsMas.getString("due_mas"),
                    rsMas.getInt("id_mas"),
                    rsMas.getString("nom_mas"),
                    ado,});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al listar las custodias: " + e.getMessage());
        } finally {
            if (rsMas != null) {
                rsMas.close();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jSpinner1 = new javax.swing.JSpinner();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        dtcFechaNacimiento = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        btnDue = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btnMas = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCustodia = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnRe1 = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnAgregarMas = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtCus = new javax.swing.JTextField();
        btnCustodia = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel3.setText("Fecha Adopción:");

        dtcFechaNacimiento.setDateFormatString("yyyy-MM-dd");
        dtcFechaNacimiento.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        dtcFechaNacimiento.setOpaque(false);
        dtcFechaNacimiento.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtcFechaNacimientoPropertyChange(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel6.setText("Seleccionar dueño:");

        btnDue.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnDue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/buscar-pequeño.png"))); // NOI18N
        btnDue.setBorderPainted(false);
        btnDue.setContentAreaFilled(false);
        btnDue.setFocusPainted(false);
        btnDue.setRequestFocusEnabled(false);
        btnDue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDueActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel7.setText("Seleccionar mascota:");

        btnMas.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnMas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/buscar-pequeño.png"))); // NOI18N
        btnMas.setBorderPainted(false);
        btnMas.setContentAreaFilled(false);
        btnMas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        btnLimpiar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/escoba-mascota.png"))); // NOI18N
        btnLimpiar.setBorderPainted(false);
        btnLimpiar.setContentAreaFilled(false);
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dtcFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(btnDue))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(btnMas))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLimpiar))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLimpiar)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dtcFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDue)
                            .addComponent(btnMas)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(0, 0, 102));

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("CUSTODIA DUEÑO A MASCOTA");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel4.setText("CUSTODIAS:");

        tblCustodia.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCustodia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCustodiaMouseClicked(evt);
            }
        });
        tblCustodia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblCustodiaKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(tblCustodia);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        btnRe1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnRe1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/flecha derecha.png"))); // NOI18N
        btnRe1.setText("Asignar Custodia");
        btnRe1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRe1ActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/eliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar Custodia");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnAgregarMas.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnAgregarMas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/veterinario.png"))); // NOI18N
        btnAgregarMas.setText("Agregar Mascota");
        btnAgregarMas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarMasActionPerformed(evt);
            }
        });

        btnRegistrar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/veterinario.png"))); // NOI18N
        btnRegistrar.setText("Agregar Dueño");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRe1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAgregarMas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 5, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRe1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnAgregarMas, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRegistrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel8.setText("Número Documento Dueño:");

        txtCus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCusActionPerformed(evt);
            }
        });
        txtCus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCusKeyTyped(evt);
            }
        });

        btnCustodia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/buscar-pequeño.png"))); // NOI18N
        btnCustodia.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnCustodia.setBorderPainted(false);
        btnCustodia.setContentAreaFilled(false);
        btnCustodia.setName(""); // NOI18N
        btnCustodia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCustodiaActionPerformed(evt);
            }
        });
        btnCustodia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnCustodiaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(142, 142, 142)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCus, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCustodia))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(jLabel8)
                                .addComponent(txtCus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnCustodia))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        try {
            jdMantDuenio_v2 objForm = new jdMantDuenio_v2(null, true);
            objForm.setLocationRelativeTo(this);
            objForm.setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(jdCustodia1_v2.class.getName()).log(Level.SEVERE, null, ex);
        }
        dispose();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
    if (tblCustodia.getSelectedRow() != -1) {
        try {
            eliminarAsignacion();
            listarCustodia();
            limpiarCamposCustodia();
        } catch (SQLException ex) {
            Logger.getLogger(jdCustodia1_v2.class.getName()).log(Level.SEVERE, null, ex);
        }

        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una fila de la 2da tabla para hacer esta operacion");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        try {
            limpiarCamposCustodia();
            listarCustodia();
        } catch (SQLException ex) {
            Logger.getLogger(jdCustodia1_v2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnAgregarMasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarMasActionPerformed
        try {
            jdMantMascota_v2 objForm = new jdMantMascota_v2(null, true);
            objForm.setLocationRelativeTo(this);
            objForm.setVisible(true);
            dispose();
        } catch (Exception ex) {

        }
        dispose();
    }//GEN-LAST:event_btnAgregarMasActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        try {

            listarCustodia();
        } catch (SQLException ex) {
            Logger.getLogger(jdCustodia1_v2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowOpened

    private void btnCustodiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustodiaActionPerformed
        // TODO add your handling code here:
        ResultSet rsDuenio = null;
        DefaultTableModel modeloD = new DefaultTableModel();
        modeloD.addColumn("Id Dueño");
        modeloD.addColumn("Nombre Dueño");
        modeloD.addColumn("Id Mascota");
        modeloD.addColumn("Nombre Mascota");
        modeloD.addColumn("Fecha Adopción");
        tblCustodia.setModel(modeloD);
        try {
            if (txtCus.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe llenar el campo vacío para realizar la búsqueda");
                listarCustodia();
                txtCus.setText("");
                return;
            }
            rsDuenio = objCus.filtrarCustodiaD(txtCus.getText());
            if (rsDuenio != null && rsDuenio.next()) {
                do {
                    String ado = rsDuenio.getDate("fa") != null ? rsDuenio.getDate("fa").toString() : null;
                    modeloD.addRow(new Object[]{
                        rsDuenio.getInt("id_m"),
                        rsDuenio.getString("nom_mas"),
                        rsDuenio.getInt("id_d"),
                        rsDuenio.getString("due_mas"),
                        ado
                    });
                } while (rsDuenio.next());
            } else {
                JOptionPane.showMessageDialog(this, "No se encontraron resultados para el documento ingresado");
                listarCustodia();
                txtCus.setText("");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error en la búsqueda: " + e.getMessage());
        }
    }//GEN-LAST:event_btnCustodiaActionPerformed

    private void txtCusKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCusKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
            evt.consume();
            JOptionPane.showMessageDialog(txtCus, "Solo se permiten números en este campo.");
        }

    }//GEN-LAST:event_txtCusKeyTyped

    private void btnCustodiaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCustodiaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCustodiaKeyTyped

    private void tblCustodiaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblCustodiaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tblCustodiaKeyTyped

    private void tblCustodiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCustodiaMouseClicked
        int filaSeleccionada = tblCustodia.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fila válida.");
            return;
        }
    }//GEN-LAST:event_tblCustodiaMouseClicked

    private void txtCusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCusActionPerformed

    private void btnRe1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRe1ActionPerformed
        Object[] opciones = {"Sí", "No"};
        int respuesta = JOptionPane.showOptionDialog(
                null,
                "¿Seguro que el dueño desea obtener la información de la mascota?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );
        if (respuesta == 0) { // Opción "Sí"
            btnRe1.setText("Guardar Datos");
            btnDue.setEnabled(true);
            btnMas.setEnabled(true);
            dtcFechaNacimiento.setEnabled(true);
            btnAgregarMas.setEnabled(false);
            btnRegistrar.setEnabled(false);
            btnLimpiar.setEnabled(false);
            btnEliminar.setEnabled(false);
            btnCustodia.setEnabled(false);
            txtCus.setEditable(false);
        } else if (respuesta == 1) { // Opción "No"
            limpiarCamposCustodia();
            btnRe1.setText("Asignar Custodia");
            btnDue.setEnabled(false);
            btnMas.setEnabled(false);
            dtcFechaNacimiento.setEnabled(false);
            btnAgregarMas.setEnabled(true);
            btnRegistrar.setEnabled(true);
            btnLimpiar.setEnabled(true);
            btnEliminar.setEnabled(true);
            btnCustodia.setEnabled(true);
            txtCus.setEditable(true);
            return;
        }
        if (btnRe1.getText().equals("Guardar Datos")) {
            try {
                if (codigoDuenio == 0) {
                    JOptionPane.showMessageDialog(this, "Debe seleccionar un Dueño válido antes de continuar.");
                    return;
                }
                if (codigoMascota == 0) {
                    JOptionPane.showMessageDialog(this, "Debe seleccionar una Mascota válida antes de continuar.");
                    return;
                }
                Date fechaNacimiento = dtcFechaNacimiento.getDate();
                if (objCus.existeCustodia(codigoDuenio, codigoMascota)) {
                    JOptionPane.showMessageDialog(this, "Error: Ya existe registro para esta combinación de Dueño y Mascota.");
                    limpiarCamposCustodia();
                    btnRe1.setText("Asignar Custodia");
                    btnAgregarMas.setEnabled(true);
                    btnRegistrar.setEnabled(true);
                    btnLimpiar.setEnabled(true);
                    btnCustodia.setEnabled(true);
                    txtCus.setEditable(true);
                    btnEliminar.setEnabled(true);
                    return;
                }
                objCus.registrarCustodia(codigoMascota, codigoDuenio, fechaNacimiento);
                JOptionPane.showMessageDialog(this, "Custodia asignada con éxito, ahora el dueño tendrá acceso a toda la información de su mascota");
                limpiarCamposCustodia();
                btnRe1.setText("Asignar Custodia");
                btnAgregarMas.setEnabled(true);
                btnRegistrar.setEnabled(true);
                btnLimpiar.setEnabled(true);
                btnEliminar.setEnabled(true);
                btnCustodia.setEnabled(true);
                txtCus.setEditable(true);
                listarCustodia();
            } catch (Exception e) {
                btnRe1.setText("Guardar Datos");
            }
        }
    }//GEN-LAST:event_btnRe1ActionPerformed
    private void limpiarCamposCustodia() {
        codigoDuenio = 0;
        codigoMascota = 0;
        btnDue.setEnabled(false);
        btnMas.setEnabled(false);
        dtcFechaNacimiento.setEnabled(false);
        dtcFechaNacimiento.setDate(null);
        jLabel1.setText("");
        jLabel2.setText("");
        txtCus.setText("");
    }

    private void btnDueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDueActionPerformed
        jdCustodiaDue_v2 objForm = new jdCustodiaDue_v2(null, true);
        objForm.setLocationRelativeTo(this);
        objForm.setVisible(true);

        Integer duenio = objForm.getCod();
        if (duenio != null && duenio != 0) {
            codigoDuenio = duenio;
            try {
                agregarDue(duenio);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "ERROR EN EL PROCESO DE GUARDADO: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se seleccionó ningún Dueño.");
        }
    }//GEN-LAST:event_btnDueActionPerformed

    private void btnMasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasActionPerformed
        try {
            jdCustodiaMasc_v2 objFormM = new jdCustodiaMasc_v2(null, true);
            objFormM.setLocationRelativeTo(this);
            objFormM.setVisible(true);
            
            
            Integer mascota = objFormM.getCod();
            if (mascota != null && mascota != 0) {
                codigoMascota = mascota;
                try {
                    agregarMasc(mascota);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "ERROR EN EL PROCESO DE GUARDADO: " + e.getMessage());
                }
            }
        } catch (Exception ex) {
        }
        

    }//GEN-LAST:event_btnMasActionPerformed

    private void dtcFechaNacimientoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtcFechaNacimientoPropertyChange
        if ("date".equals(evt.getPropertyName())) {
            if (dtcFechaNacimiento.getDate() == null) {
                return;
            }
            Date fechaSeleccionada = dtcFechaNacimiento.getDate();
            Date fechaActual = new Date();

            if (fechaSeleccionada.after(fechaActual)) {
                JOptionPane.showMessageDialog(this, "En este campo no es permitido ingresar fechas futuras");
                dtcFechaNacimiento.setDate(null);
                btnRe1.setText("Guardar Datos");
            }
        }
    }//GEN-LAST:event_dtcFechaNacimientoPropertyChange

    private void agregarDue(int codDue) {
        if (codDue > 0) {
            ResultSet rs = null;
            try {
                rs = objCus.filtrarDuenioV(codDue);
                if (rs != null && rs.next()) {
                    StringBuilder datosDueño = new StringBuilder();
                    datosDueño.append("").append(rs.getString("nombres"))
                            .append(" ").append(rs.getString("apePaterno"))
                            .append(" ").append(rs.getString("apeMaterno"))
                            .append("\n");
                    jLabel1.setText(datosDueño.toString());
                } else {
                    jLabel1.setText("No se encontraron datos para el código: " + codDue);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "ERROR AL MOSTRAR LOS DATOS DEL DUEÑO--->" + e.getMessage());
            }
        } else {
            jLabel1.setText("Código del dueño inválido.");
        }
    }

    private void agregarMasc(int codMas) {
        if (codMas > 0) {
            ResultSet rs = null;
            try {
                rs = objCus.filtrarMascotaa(codMas);

                if (rs != null && rs.next()) {
                    StringBuilder datosMascota = new StringBuilder();
                    datosMascota.append("").append(rs.getString("nombre"))
                            .append(" | ").append(rs.getString("fecha_nacimiento"));
                    jLabel2.setText(datosMascota.toString());
                } else {
                    jLabel2.setText("No se encontraron datos para el código: " + codMas);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "ERROR AL MOSTRAR LOS DATOS DE LA MASCOTA---> " + e.getMessage());
            }
        } else {
            jLabel2.setText("Código de la mascota inválido.");
        }
    }
    
private void eliminarAsignacion() {
    int fila = tblCustodia.getSelectedRow();
    if (fila == -1) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar un registro para eliminar.");
        return; // Detiene el flujo si no hay fila seleccionada.
    }

    try {
        Object valorColumna1 = tblCustodia.getValueAt(fila, 1);
        Object valorColumna2 = tblCustodia.getValueAt(fila, 2);
        Object valorColumna0 = tblCustodia.getValueAt(fila, 0);

        if ( valorColumna2 == null || valorColumna0 == null) {
            JOptionPane.showMessageDialog(this, "El registro seleccionado tiene datos incompletos.");
            return;
        }

        if (Utilidad.validarEliminacionForanea("CUSTODIA", fila)) {
            JOptionPane.showMessageDialog(this,
                "Hay datos externos asociados al servicio asignado \"" +
                valorColumna1.toString() +
                "\".\nConsidere cambiar su disponibilidad o vigencia para que ya no pueda ser usado."
            );
        } else {
            objCus.eliminarCustodia(
                Integer.parseInt(valorColumna2.toString()),
                Integer.parseInt(valorColumna0.toString())
            );
            JOptionPane.showMessageDialog(this, "Eliminación exitosa.");
        }
    } catch (NumberFormatException nfe) {
        JOptionPane.showMessageDialog(this, "Error de formato en los datos: " + nfe.getMessage());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage());
    }
    
    
    
    
    
    
    
    
    
    
    
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarMas;
    private javax.swing.JButton btnCustodia;
    private javax.swing.JButton btnDue;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnMas;
    private javax.swing.JButton btnRe1;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private com.toedter.calendar.JDateChooser dtcFechaNacimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable tblCustodia;
    private javax.swing.JTextField txtCus;
    // End of variables declaration//GEN-END:variables
}

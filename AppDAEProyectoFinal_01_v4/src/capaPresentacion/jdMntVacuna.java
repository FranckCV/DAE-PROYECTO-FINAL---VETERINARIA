/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package capaPresentacion;

import capaNegocio.clsEspecie;
import capaNegocio.clsVacuna;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;
import soporte.*;

/**
 *
 * @author Fabiana Lucía
 */
public class jdMntVacuna extends javax.swing.JDialog {

    clsVacuna objVacuna = new clsVacuna();
    clsEspecie objEspecie = new clsEspecie();

    public jdMntVacuna(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        formatoSpinner();
        listarVacunas();
        listarEspecies();
        btnRegistrar.setText(Utilidad.BTN_NUEVO);
        btnModificar.setText(Utilidad.BTN_MODIFICAR);
        btnEliminar.setText(Utilidad.BTN_ELIMINAR);
        spnDosis.setEnabled(false);
        chkDisponibilidad.setEnabled(false);
        chkDisponibilidad.setSelected(true);
    }

    private void formatoSpinner() {
        SpinnerNumberModel model = new SpinnerNumberModel(0.0, 0.0, Double.NaN, 1);
        spnDosis.setModel(model);
        JFormattedTextField txt = ((JSpinner.NumberEditor) spnDosis.getEditor()).getTextField();
        ((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
    }

    private void listarEspecies() {
        ResultSet rsEspec = null;
        DefaultComboBoxModel modeloEspecie = new DefaultComboBoxModel();
        cmbEspecie.setModel(modeloEspecie);

        try {
            rsEspec = objEspecie.listarEspecies();
            while (rsEspec.next()) {
                modeloEspecie.addElement(rsEspec.getString("nombre")); // Muestra nombres
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al listar especies: " + e.getMessage());
        }
    }

    public void listarVacunas() {
        ResultSet rsVac = null;
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Dosis por Kg");
        modelo.addColumn("Especie");
        modelo.addColumn("Disponible");
        tblVacunas.setModel(modelo);

        try {
            rsVac = objVacuna.listarVacunas();
            while (rsVac.next()) {
                boolean disponibilidad = rsVac.getBoolean("disponibilidad");
                String disponibleText = disponibilidad ? "Disponible" : "No disponible";

                modelo.addRow(new Object[]{
                    rsVac.getInt("id"),
                    rsVac.getString("nombre"),
                    rsVac.getDouble("dosis_x_kgpeso"),
                    rsVac.getString("nombre_especie"),
                    disponibleText
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al listar vacunas: " + e.getMessage());
        }
    }

    private void usarBotonesVacuna(boolean buscar, boolean registrar, boolean modificar, boolean eliminar, boolean limpiar, boolean disponibilidad) {
        btnBuscar.setEnabled(buscar);
        btnRegistrar.setEnabled(registrar);
        btnModificar.setEnabled(modificar);
        btnEliminar.setEnabled(eliminar);
        btnLimpiar.setEnabled(limpiar);
        btnDisponibilidad.setEnabled(disponibilidad);
    }

    private void editableControlesVacuna(boolean id, boolean nombre, boolean dosisPorPeso, boolean especie, boolean disponibilidad) {
        txtId.setEditable(id);
        txtNombre.setEditable(nombre);
        spnDosis.setEnabled(dosisPorPeso);
        cmbEspecie.setEnabled(especie);
        chkDisponibilidad.setEnabled(disponibilidad);
    }

    private void cancelarAccionVacuna() {
        btnRegistrar.setText(Utilidad.BTN_NUEVO);
        btnEliminar.setText(Utilidad.BTN_ELIMINAR);
        btnModificar.setText(Utilidad.BTN_MODIFICAR);
        usarBotonesVacuna(true, true, false, false, true, false);
        limpiarControles();
        editableControlesVacuna(true, false, false, false, false);
        listarVacunas();
    }

    private void limpiarControles() {
        txtId.setText("");
        txtNombre.setText("");
        spnDosis.setValue(0.00);
        cmbEspecie.setSelectedIndex(-1);
        chkDisponibilidad.setSelected(false);
        listarVacunas();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVacunas = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cmbEspecie = new javax.swing.JComboBox<>();
        spnDosis = new javax.swing.JSpinner();
        txtId = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        chkDisponibilidad = new javax.swing.JCheckBox();
        btnModificar = new javax.swing.JButton();
        btnDisponibilidad = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 0, 102));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Vacuna");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(225, 225, 225)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(204, 255, 255));

        tblVacunas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVacunasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblVacunas);

        jPanel4.setBackground(new java.awt.Color(138, 238, 238));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel6.setText("Nombre:");

        txtNombre.setBorder(null);
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel7.setText("Dosis por peso (Kg):");

        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel14.setText("Especie:");

        cmbEspecie.setBorder(null);

        spnDosis.setEnabled(false);
        spnDosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                spnDosisKeyTyped(evt);
            }
        });

        txtId.setBorder(null);
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel4.setText("ID:");

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel8.setText("Disponibilidad:");

        chkDisponibilidad.setText("(Disponibilidad)");

        btnModificar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/editar.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnDisponibilidad.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnDisponibilidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/disponible.png"))); // NOI18N
        btnDisponibilidad.setText("Disponibilidad");
        btnDisponibilidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisponibilidadActionPerformed(evt);
            }
        });

        btnRegistrar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/veterinario.png"))); // NOI18N
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/eliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/buscar-pequeño.png"))); // NOI18N
        btnBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnBuscar.setBorderPainted(false);
        btnBuscar.setContentAreaFilled(false);
        btnBuscar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/buscar-pequeño.png"))); // NOI18N
        btnBuscar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/buscar.png"))); // NOI18N
        btnBuscar.setVerifyInputWhenFocusTarget(false);
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
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbEspecie, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(spnDosis)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(chkDisponibilidad)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDisponibilidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnRegistrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDisponibilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnBuscar))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(cmbEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(spnDosis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkDisponibilidad)
                            .addComponent(jLabel8))))
                .addGap(9, 9, 9))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/vacuna.png"))); // NOI18N

        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/opacado.png"))); // NOI18N
        btnLimpiar.setBorder(null);
        btnLimpiar.setContentAreaFilled(false);
        btnLimpiar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/opacado.png"))); // NOI18N
        btnLimpiar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/escoba-mascota.png"))); // NOI18N
        btnLimpiar.setVerifyInputWhenFocusTarget(false);
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(btnLimpiar)
                        .addGap(76, 76, 76))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        ResultSet rsVacuna = null;
        try {
            if (txtId.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar una ID para buscar");
            } else {
                // Selecciona la fila en la tabla que coincide con el ID ingresado
                for (int i = 0; i < tblVacunas.getRowCount(); i++) {
                    String valorCodigo = tblVacunas.getValueAt(i, 0).toString();
                    if (valorCodigo.equals(txtId.getText())) {
                        tblVacunas.setRowSelectionInterval(i, i);
                        tblVacunas.scrollRectToVisible(tblVacunas.getCellRect(i, 0, true));
                        break;
                    }
                }

                rsVacuna = objVacuna.buscarVacuna(Integer.parseInt(txtId.getText()));
                if (rsVacuna.next()) {

                    txtNombre.setText(rsVacuna.getString("nombre"));
                    spnDosis.setValue(rsVacuna.getDouble("dosis_x_kgpeso"));
                    cmbEspecie.setSelectedItem(objVacuna.obtenerNombreEspecie(rsVacuna.getInt("especie_id")));
                    chkDisponibilidad.setSelected(rsVacuna.getBoolean("disponibilidad"));

                    rsVacuna.close(); // Cierra el ResultSet después de usarlo
                } else {
                    // Si no se encuentra el código, muestra un mensaje y limpia los controles
                    JOptionPane.showMessageDialog(this, "Este código en vacuna no existe");
                    limpiarControles();
                    listarVacunas();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            listarVacunas();
            limpiarControles();
        }

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyTyped
        if (txtId.getText().length() >= 10) {
            evt.consume();
        }

        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }

        if (txtId.getText().trim().length() == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txtIdKeyTyped

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        try {
            if (txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una vacuna para modificar.");
                return; 
                
            }

            if (btnModificar.getText().equals(Utilidad.BTN_MODIFICAR)) {
                btnModificar.setText(Utilidad.BTN_GUARDAR);
                btnEliminar.setText(Utilidad.BTN_CANCELAR);
                editableControlesVacuna(false, true, true, true, false);
                usarBotonesVacuna(false, false, true, true, true, false);
                chkDisponibilidad.setEnabled(false);
                return; 
            }

            int confirmacion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Está seguro de que desea guardar los cambios realizados?",
                    "Confirmar modificación",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmacion != JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, "No se realizó ningún cambio");
                return; 
            }

            objVacuna.modificarVacuna(
                    Integer.parseInt(txtId.getText()),
                    txtNombre.getText(),
                    (Double) spnDosis.getValue(),
                    objEspecie.obtenerIdEspecie(cmbEspecie.getSelectedItem().toString()),
                    chkDisponibilidad.isSelected()
            );

            btnModificar.setText(Utilidad.BTN_MODIFICAR);
            btnEliminar.setText(Utilidad.BTN_ELIMINAR);
            editableControlesVacuna(true, false, false, false, false);
            usarBotonesVacuna(true, true, true, true, true, true);
            limpiarControles();
            listarVacunas();
            JOptionPane.showMessageDialog(this, "Vacuna modificada con éxito.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al modificar la vacuna: " + e.getMessage());
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        try {
            if (btnRegistrar.getText().equals(Utilidad.BTN_NUEVO)) {

                btnRegistrar.setText(Utilidad.BTN_GUARDAR);
                btnEliminar.setText(Utilidad.BTN_CANCELAR);
                chkDisponibilidad.setEnabled(true);
                limpiarControles();
                editableControlesVacuna(false, true, true, true, false);
                txtId.setText(objVacuna.generarIDVacuna().toString());
                chkDisponibilidad.setSelected(true);
                usarBotonesVacuna(true, true, false, true, false, false);
                txtNombre.requestFocus();
            } else {
                // Guardar los datos
                if (txtNombre.getText().trim().isEmpty() || spnDosis.getValue() == null || cmbEspecie.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(this, "Debe llenar todos los campos");
                    return;
                }
                if (objVacuna.existeNombreVacuna(txtNombre.getText())) {
                    JOptionPane.showMessageDialog(this, "El nombre de la vacuna ya está registrado. Elija un nombre diferente.");
                    return;
                }

                objVacuna.registrarVacuna(
                        Integer.parseInt(txtId.getText()),
                        txtNombre.getText(),
                        (Double) spnDosis.getValue(),
                        objEspecie.obtenerIdEspecie(cmbEspecie.getSelectedItem().toString()),
                        chkDisponibilidad.isSelected()
                );

                btnRegistrar.setText(Utilidad.BTN_NUEVO);
                btnEliminar.setText(Utilidad.BTN_ELIMINAR);
                editableControlesVacuna(true, false, false, false, false);
                usarBotonesVacuna(true, true, false, true, true, false);
                limpiarControles();
                listarVacunas();
                JOptionPane.showMessageDialog(this, "Vacuna registrada con éxito");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar la vacuna: " + e.getMessage());
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            if (btnRegistrar.getText().equals(Utilidad.BTN_GUARDAR) || btnModificar.getText().equals(Utilidad.BTN_GUARDAR)) {
                cancelarAccionVacuna();
            } else {
                if (txtId.getText().equals("")) {
                    Utilidad.mensajeErrorFaltaID(this);
                } else if (Utilidad.validarEliminacionForanea("vacuna", Integer.parseInt(txtId.getText()))) {
                    JOptionPane.showMessageDialog(this, "Hay datos externos asociados a" + "vacuna" + "\" " + txtNombre.getText() + "\".\n"
                            + "Considere cambiar su disponibilidad o vigencia para que ya no pueda ser usado");
                } else {
                    int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres eliminar la vacuna con código " + txtId.getText() + "?", "Confirmación", JOptionPane.YES_NO_OPTION);
                    if (confirmacion == JOptionPane.YES_OPTION) {
                        objVacuna.eliminarVacuna(Integer.valueOf(txtId.getText()));
                        limpiarControles();
                        listarVacunas();
                        JOptionPane.showMessageDialog(this, "Vacuna eliminada con éxito");
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarControles();
        editableControlesVacuna(true, false, false, false, false);
        usarBotonesVacuna(true, true, false, false, true, false); // Habilita todos los botones

    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void tblVacunasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVacunasMouseClicked
        if (btnModificar.getText().equals(Utilidad.BTN_GUARDAR) || btnRegistrar.getText().equals(Utilidad.BTN_GUARDAR)) {
            JOptionPane.showMessageDialog(this, "Porfavor, antes de realizar otra operación "
                    + "complete el proceso actual");
            return;
        }
        txtId.setText(String.valueOf(tblVacunas.getValueAt(tblVacunas.getSelectedRow(), 0)));
        btnBuscarActionPerformed(null);
        usarBotonesVacuna(true, true, true, true, true, true);
    }//GEN-LAST:event_tblVacunasMouseClicked

    private void btnDisponibilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisponibilidadActionPerformed
        // TODO add your handling code here:
        try {
            if (txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seleccione una vacuna para cambiar su disponibilidad.");
                return;
            }

            // Obtener el estado actual de disponibilidad
            boolean disponibilidadActual = chkDisponibilidad.isSelected();
            String estadoActual = disponibilidadActual ? "Disponible" : "No Disponible";
            String nuevoEstado = disponibilidadActual ? "No Disponible" : "Disponible";

            // Confirmar el cambio de disponibilidad
            int confirmacion = JOptionPane.showConfirmDialog(
                    this,
                    "La vacuna \"" + txtNombre.getText() + "\" actualmente está " + estadoActual + ".\n"
                    + "¿Desea cambiar su estado a \"" + nuevoEstado + "\"?",
                    "Confirmación de cambio de disponibilidad",
                    JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                // Actualizar la disponibilidad en la base de datos
                objVacuna.actualizarDisponibilidad(Integer.parseInt(txtId.getText()), !disponibilidadActual);

                // Refrescar los controles
                chkDisponibilidad.setSelected(!disponibilidadActual);
                listarVacunas();

                JOptionPane.showMessageDialog(this, "Disponibilidad actualizada con éxito.");
            } else {
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar disponibilidad: " + e.getMessage());
        }
    }//GEN-LAST:event_btnDisponibilidadActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        listarVacunas();
        listarEspecies();
        limpiarControles();
        editableControlesVacuna(true, false, false, false, false);
        usarBotonesVacuna(true, true, false, false, true, false);
    }//GEN-LAST:event_formWindowOpened

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
        Utilidad.validarCampoTextoSoloLetras(evt);
    }//GEN-LAST:event_txtNombreKeyTyped

    private void spnDosisKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spnDosisKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();
        boolean numeros = key >= 48 && key <= 57;
        if (!(numeros)) {
            evt.consume();
        }
    }//GEN-LAST:event_spnDosisKeyTyped

    private void txtIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) { // Detectar Enter
            try {
                if (txtId.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Ingrese un ID válido.", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int id = Integer.parseInt(txtId.getText());
                ResultSet rsVac = objVacuna.buscarVacuna(id);

                if (rsVac.next()) {
                    txtNombre.setText(rsVac.getString("nombre"));
                    spnDosis.setValue(rsVac.getDouble("dosis_x_kgpeso"));
                    cmbEspecie.setSelectedItem(rsVac.getString("nombre_especie"));
                    chkDisponibilidad.setSelected(rsVac.getBoolean("disponibilidad"));
                    rsVac.close();
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró información para el ID ingresado.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    limpiarControles();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al buscar información: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtIdKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDisponibilidad;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JCheckBox chkDisponibilidad;
    private javax.swing.JComboBox<String> cmbEspecie;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner spnDosis;
    private javax.swing.JTable tblVacunas;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}

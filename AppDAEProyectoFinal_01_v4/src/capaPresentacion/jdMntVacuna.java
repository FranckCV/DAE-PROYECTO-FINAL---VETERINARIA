/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package capaPresentacion;

import capaNegocio.clsVacuna;
import capaNegocio.clsEspecie;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

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
        btnNuevo.setText("Nuevo");
        listarVacunas();
        spnDosis.setEnabled(true);
        formatoSpinner();
        listarEspecies();
        limpiarControles();
    }

    private void listarEspecies() {
        ResultSet rsEspec = null;
        DefaultComboBoxModel modeloEspecie = new DefaultComboBoxModel();
        cmbEspecie.setModel(modeloEspecie);

        try {
            rsEspec = objEspecie.listarEspecies();
            while (rsEspec.next()) {
                modeloEspecie.addElement(rsEspec.getString("nombre"));
            }
        } catch (Exception e) {
        }

    }

    private void formatoSpinner() {
        SpinnerNumberModel model = new SpinnerNumberModel(0.00, 0.00, null, 0.01);

        spnDosis.setModel(model);
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
                    rsVac.getString("especie_id"),
                    disponibleText
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al listar vacunas: " + e.getMessage());
        }
    }

    private void usarBotonesVacuna(boolean buscar, boolean registrar, boolean modificar, boolean eliminar, boolean limpiar, boolean disponibilidad) {
        btnBuscar.setEnabled(buscar);
        btnNuevo.setEnabled(registrar);
        btnModificar.setEnabled(modificar);
        btnEliminar.setEnabled(eliminar);
        btnLimpiar.setEnabled(limpiar);
        btnDisponibilidad.setEnabled(disponibilidad);
    }

    private void editableControlesVacuna(boolean id, boolean nombre, boolean dosisPorPeso, boolean especie, boolean disponibilidad) {
        txtId.setEditable(id);
        txtNombre.setEditable(nombre);
        spnDosis.setEnabled(dosisPorPeso); // Asegúrate de que spnDosisPorPeso sea el nombre correcto de este campo en tu interfaz
        cmbEspecie.setEnabled(especie); // Asegúrate de que cbxEspecie sea el nombre correcto del combo box en tu interfaz
        chkDisponibilidad.setEnabled(disponibilidad); // Asegúrate de que chkDisponibilidad sea el nombre correcto del check box en tu interfaz
    }

    private void cancelarAccionVacuna() {
        btnNuevo.setText("Registrar");
        btnModificar.setText("Modificar");
        btnEliminar.setText("Eliminar");
        editableControlesVacuna(false, false, false, false, true);
        usarBotonesVacuna(true, true, true, true, true, true);
        limpiarControles();
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
        btnNuevo = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
                .addGap(162, 162, 162)
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

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel7.setText("Dosis por peso (Kg):");

        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel14.setText("Especie:");

        cmbEspecie.setBorder(null);

        spnDosis.setEnabled(false);

        txtId.setBorder(null);
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel4.setText("id: ");

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
        btnDisponibilidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/editar.png"))); // NOI18N
        btnDisponibilidad.setText("Disponibilidad");
        btnDisponibilidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisponibilidadActionPerformed(evt);
            }
        });

        btnNuevo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/veterinario.png"))); // NOI18N
        btnNuevo.setText("Registrar");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
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
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbEspecie, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(spnDosis)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(chkDisponibilidad)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDisponibilidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDisponibilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLimpiar))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                .addContainerGap())
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        try {
            ResultSet rsVacuna = null;

            if (txtId.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un código para buscar");
            } else {
                
                rsVacuna = objVacuna.buscarVacuna(Integer.valueOf(txtId.getText()));

                if (rsVacuna.next()) {
                    txtNombre.setText(rsVacuna.getString("nombre"));
                    spnDosis.setValue(rsVacuna.getDouble("dosis_x_kgpeso"));
                    cmbEspecie.setSelectedItem(objVacuna.obtenerNombreEspecie(rsVacuna.getInt("especie_id")));
                    chkDisponibilidad.setSelected(rsVacuna.getBoolean("disponibilidad")); // Si tienes un checkbox de disponibilidad

                    rsVacuna.close();
                } else {
                    JOptionPane.showMessageDialog(this, "Código de vacuna no existente");
                    limpiarControles();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getLocalizedMessage());
        }

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyTyped
        char c = evt.getKeyChar();

        if (!Character.isDigit(c) && c != '\b') {
            evt.consume();
        }
    }//GEN-LAST:event_txtIdKeyTyped

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        try {
            if (txtId.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un código a modificar");
            } else {
                int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres modificar la vacuna con código " + txtId.getText() + "?",
                        "Confirmación", JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    Integer id = Integer.valueOf(txtId.getText());
                    String nombre = txtNombre.getText();
                    Double dosis = (double) spnDosis.getValue();
                    Integer especieId = objVacuna.obtenerIdEspecie(cmbEspecie.getSelectedItem().toString());
                    boolean disponibilidad = chkDisponibilidad.isSelected();

                    // Llama al método de modificación en la clase objVacuna
                    objVacuna.modificarVacuna(id, nombre, dosis, especieId, disponibilidad);

                    // Limpia los controles y actualiza la lista de vacunas
                    limpiarControles();
                    listarVacunas();

                    JOptionPane.showMessageDialog(this, "Vacuna modificada con éxito");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al modificar la vacuna: " + e.getLocalizedMessage());
        }

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        try {
            boolean ningunCampo = txtId.getText().equals("") && txtNombre.getText().equals("");

            if ((txtId.getText().equals("") || txtNombre.getText().equals("") || cmbEspecie.getSelectedIndex() == -1 || (double) spnDosis.getValue() == 0) && !ningunCampo) {
                JOptionPane.showMessageDialog(this, "Llenar todos los campos");
            } else {
                if (btnNuevo.getText().equals("Nuevo")) {
                    btnNuevo.setText("Guardar");
                    limpiarControles();
                    txtId.setText(objVacuna.generarIDVacuna().toString());
                    txtNombre.requestFocus();
                } else { // Guardar
                    btnNuevo.setText("Nuevo");
                    Integer codEspecie = objVacuna.obtenerIdEspecie(cmbEspecie.getSelectedItem().toString());
                    objVacuna.registrarVacuna(
                            Integer.valueOf(txtId.getText()),
                            txtNombre.getText(),
                            (double) spnDosis.getValue(),
                            codEspecie,
                            chkDisponibilidad.isSelected()
                    );

                    limpiarControles();
                    listarVacunas();
                    JOptionPane.showMessageDialog(this, "Vacuna registrada con éxito");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar una vacuna: " + e.getLocalizedMessage());
        }

    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            if (txtId.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un código a eliminar");
            } else {
                int confirmacion = JOptionPane.showConfirmDialog(this, "Estás seguro de que quieres eliminar a la vacuna"
                        + " con codigo " + txtId.getText(),
                        "Confirmacion", JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    objVacuna.eliminarVacuna(Integer.valueOf(txtId.getText()));
                    limpiarControles();
                    listarVacunas();
                } else {
                    JOptionPane.showMessageDialog(this, "Ok, no se eliminó");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarControles();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void tblVacunasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVacunasMouseClicked
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
            boolean disponibilidadActual = chkDisponibilidad.isSelected();
            objVacuna.actualizarDisponibilidad(Integer.parseInt(txtId.getText()), !disponibilidadActual);
            chkDisponibilidad.setSelected(!disponibilidadActual);
            listarVacunas();
            JOptionPane.showMessageDialog(this, "Disponibilidad actualizada con éxito.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar disponibilidad: " + e.getMessage());
        }
    }//GEN-LAST:event_btnDisponibilidadActionPerformed

    private void limpiarControles() {
        txtId.setText("");
        txtNombre.setText("");
        spnDosis.setValue(0.00);
        cmbEspecie.setSelectedIndex(-1);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDisponibilidad;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
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

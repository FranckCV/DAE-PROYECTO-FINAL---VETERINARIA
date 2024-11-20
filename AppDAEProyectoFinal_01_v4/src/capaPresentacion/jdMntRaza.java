/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package capaPresentacion;

import capaNegocio.clsEspecie;
import capaNegocio.clsRaza;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fabiana Lucía
 */
public class jdMntRaza extends javax.swing.JDialog {

    clsRaza objRaza = new clsRaza();
    clsEspecie objEspecie = new clsEspecie();

    public jdMntRaza(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        listarRazas();
        listarEspecies();
        chkVigente.setEnabled(false);
        btnNuevo.setText("Nuevo");
    }

    private void listarRazas() {
        ResultSet rsRazas = null;
        Vector registro;
        String especie = "";
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Especie");
        modelo.addColumn("Disponibilidad");
        try {
            rsRazas = objRaza.listarRazas();
            while (rsRazas.next()) {
                especie = objRaza.obtenerNombreEspecie(rsRazas.getInt("especie_id"));
                registro = new Vector();
                registro.add(0, rsRazas.getInt("id"));
                registro.add(1, rsRazas.getString("nombre"));
                registro.add(2, especie);
                registro.add(3, rsRazas.getBoolean("disponibilidad"));

                modelo.addRow(registro);
            }
            tblRazas.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public void listarEspecies() {
        ResultSet rsMar = null;
        DefaultComboBoxModel modeloEspecie = new DefaultComboBoxModel();
        cmbEspecie.setModel(modeloEspecie);

        try {
            rsMar = objEspecie.listarEspecies();
            while (rsMar.next()) {
                if (rsMar.getBoolean("disponibilidad")) {
                    modeloEspecie.addElement(rsMar.getString("nombre"));
                }
            }
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRazas = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        cmbEspecie = new javax.swing.JComboBox<>();
        chkVigente = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(0, 0, 102));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Raza");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(162, 162, 162))
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

        btnNuevo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/veterinario.png"))); // NOI18N
        btnNuevo.setText("Registrar");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/editar.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
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

        tblRazas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRazasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblRazas);

        jPanel4.setBackground(new java.awt.Color(138, 238, 238));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel6.setText("Nombre:");

        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombre.setBorder(null);
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel4.setText("id: ");

        txtId.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
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

        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel14.setText("Especie");

        cmbEspecie.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbEspecie.setBorder(null);
        cmbEspecie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEspecieActionPerformed(evt);
            }
        });

        chkVigente.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        chkVigente.setText("(Vigente)");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNombre))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkVigente))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14)
                                .addComponent(cmbEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4))))
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chkVigente))
                    .addComponent(jLabel6))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/refugio-de-animales.png"))); // NOI18N
        jLabel1.setText("jLabel1");

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
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
<<<<<<< HEAD
                        .addComponent(btnLimpiar))
=======
                        .addComponent(jButton5))
>>>>>>> 1e08c282a5e19da6921fad72546c3f1fa3c914ca
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(btnLimpiar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevo))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void activarBotones() {
        btnBuscar.setEnabled(true);
        btnLimpiar.setEnabled(true);
        btnModificar.setEnabled(true);
    }

    private void bloquearBotones() {
        btnModificar.setEnabled(false);
        btnBuscar.setEnabled(false);
        btnLimpiar.setEnabled(false);
    }

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        try {
            boolean ningun_campo = txtId.getText().equals("") && txtNombre.getText().equals("");
            if ((txtId.getText().equals("") || txtNombre.getText().equals("")) && !ningun_campo) {
                JOptionPane.showMessageDialog(this, "Llenar todos los campos");
            } else {
                if (btnNuevo.getText().equals("Nuevo")) {

                    btnNuevo.setText("Guardar");
                    bloquearBotones();
                    btnEliminar.setText("Cancelar");
                    limpiarControles();
                    txtId.setText(objEspecie.generarIDEspecie().toString());
                    txtNombre.requestFocus();
                    tblRazas.setEnabled(false);

                } else { //Guardar
                    if (!objRaza.validarNombre(txtNombre.getText())) {
                        btnNuevo.setText("Nuevo");
                        activarBotones();
                        tblRazas.setEnabled(true);
                        objRaza.registrarRaza(Integer.valueOf(txtId.getText()),
                                txtNombre.getText(), objEspecie.obtenerIdEspecie(cmbEspecie.getSelectedItem().toString()));

                        limpiarControles();
                        listarEspecies();
                    } else {
                        JOptionPane.showMessageDialog(null, "Ya se registró esa especie");
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar un producto" + e.getLocalizedMessage());
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarControles();
    }//GEN-LAST:event_btnLimpiarActionPerformed

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
                Object[] options = {"Sí", "No"};

                int confirmacion = JOptionPane.showOptionDialog(this,
                        "¿Estás seguro de que quieres modificar la raza con código " + txtId.getText() + "?",
                        "Confirmación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    String nom = txtNombre.getText();

                        Integer id = Integer.valueOf(txtId.getText());
                        Integer especie_id = objRaza.obtenerIdEspecie(cmbEspecie.getSelectedItem().toString());
                        objRaza.modificarRaza(id, nom, especie_id);
                        limpiarControles();
                        listarRazas();

                    JOptionPane.showMessageDialog(this, "Se modificó");
                } else {
                    limpiarControles();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            if (txtId.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un código a eliminar");
            } else {
                if (btnEliminar.getText().equals("Cancelar")) {
                    activarBotones();
                    limpiarControles();
                    btnNuevo.setText("Nuevo");
                    btnEliminar.setText("Eliminar");
                } else {
                    Object[] opciones = {"Sí", "No"};
                    int confirmacion = JOptionPane.showOptionDialog(this,
                            "¿Estás seguro de que quieres modificar?",
                            "Confirmación",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null, opciones, opciones[1]);
                    if (confirmacion == JOptionPane.YES_OPTION) {

                        int num = objEspecie.eliminarEspecie(Integer.valueOf(txtId.getText()));

                        if (num == 0) {
                            JOptionPane.showMessageDialog(this, "Se eliminó");
                        } else {
                            JOptionPane.showMessageDialog(this, "No se eliminó porque está referenciado");
                        }

                        limpiarControles();
                        listarEspecies();

                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tblRazasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRazasMouseClicked
        String codigo;
        codigo = String.valueOf(tblRazas.getValueAt(tblRazas.getSelectedRow(), 0));

        txtId.setText(codigo);
        btnBuscarActionPerformed(null);
    }//GEN-LAST:event_tblRazasMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        ResultSet rsRaza = null;

        try {
            if (txtId.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un código para buscar");
            } else {
                rsRaza = objRaza.buscarRaza(Integer.valueOf(txtId.getText()));

                if (rsRaza.next()) {
                    txtNombre.setText(rsRaza.getString("nombre"));
                    cmbEspecie.setSelectedItem(objRaza.obtenerNombreEspecie(rsRaza.getInt("especie_id")));
                    chkVigente.setSelected(rsRaza.getBoolean("disponibilidad"));

                    rsRaza.close();
                } else {
                    JOptionPane.showMessageDialog(this, "Codigo de raza no existente");
                    limpiarControles();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error " + e.getLocalizedMessage());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

<<<<<<< HEAD
    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
        char key = evt.getKeyChar();

        if (!(Character.isLetter(key) || key == ' ')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped
=======
    private void cmbEspecieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEspecieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbEspecieActionPerformed
>>>>>>> 1e08c282a5e19da6921fad72546c3f1fa3c914ca

    private void limpiarControles() {
        txtId.setText("");
        txtNombre.setText("");
        cmbEspecie.setSelectedIndex(-1);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JCheckBox chkVigente;
    private javax.swing.JComboBox<String> cmbEspecie;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblRazas;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}

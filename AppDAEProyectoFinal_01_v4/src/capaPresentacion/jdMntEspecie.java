/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package capaPresentacion;

import capaNegocio.clsEspecie;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import soporte.Utilidad;

/**
 *
 * @author Fabiana Lucía
 */
public class jdMntEspecie extends javax.swing.JDialog {
    
    clsEspecie objEspecie = new clsEspecie();
    
    public jdMntEspecie(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        listarEspecies();
        btnNuevo.setText(Utilidad.BTN_NUEVO);
        btnModificar.setText(Utilidad.BTN_MODIFICAR);
        btnDisponibilidad.setText(Utilidad.BTN_DISPONIBILIDAD);
        btnEliminar.setText(Utilidad.BTN_ELIMINAR);
        chkVigencia.setEnabled(false);
        chkVigencia.setSelected(true);
        Utilidad.alineacionDerechaColumnaTabla(tblEspecies , 0);
        Utilidad.validacionTabla(tblEspecies, modal, rootPaneCheckingEnabled, modal);
        txtId.requestFocus();
    }
    
    private void listarEspecies() {
        ResultSet rsEspecies = null;
        Vector registro;
        String vigencia = "";
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Vigencia");
        try {
            rsEspecies = objEspecie.listarEspecies();
            while (rsEspecies.next()) {
                registro = new Vector();
                registro.add(0, rsEspecies.getInt("id"));
                registro.add(1, rsEspecies.getString("nombre"));
                if (rsEspecies.getString("disponibilidad").equals("t")) {
                    vigencia = "Disponible";
                } else {
                    vigencia = "No disponible";
                }
                registro.add(2, vigencia);
                modelo.addRow(registro);
            }
            tblEspecies.setModel(modelo);
            Utilidad.alineacionDerechaColumnaTabla(tblEspecies, 0 );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
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
        tblEspecies = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        chkVigencia = new javax.swing.JCheckBox();
        btnBuscar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnDisponibilidad = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mantenimiento de especie");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 0, 102));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Especie");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(296, 296, 296))
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

        tblEspecies.setCellSelectionEnabled(true);
        tblEspecies.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEspeciesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEspecies);

        jPanel4.setBackground(new java.awt.Color(138, 238, 238));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel6.setText("Nombre:");

        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
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

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel4.setText("ID:");

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

        chkVigencia.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        chkVigencia.setText("Disponible");
        chkVigencia.setContentAreaFilled(false);
        chkVigencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkVigenciaActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscar)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chkVigencia)
                .addGap(31, 31, 31)
                .addComponent(btnLimpiar)
                .addGap(14, 14, 14))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLimpiar)
                    .addComponent(btnBuscar)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chkVigencia)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/refugio-de-animales.png"))); // NOI18N

        btnDisponibilidad.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnDisponibilidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/disponible.png"))); // NOI18N
        btnDisponibilidad.setText("Disponible");
        btnDisponibilidad.setMaximumSize(new java.awt.Dimension(118, 39));
        btnDisponibilidad.setMinimumSize(new java.awt.Dimension(118, 39));
        btnDisponibilidad.setPreferredSize(new java.awt.Dimension(118, 39));
        btnDisponibilidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisponibilidadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(btnDisponibilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(12, 12, 12))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(224, 224, 224))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDisponibilidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
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
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void txtIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyTyped
        Utilidad.validarCampoTextoSoloNumero(evt);
    }//GEN-LAST:event_txtIdKeyTyped
    
    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        try {
            Utilidad.activarFields(txtNombre);
            
            txtId.setEnabled(false);
            
            if (btnNuevo.getText().equals(Utilidad.BTN_NUEVO)) {
                btnNuevo.setText(Utilidad.BTN_GUARDAR);
                btnEliminar.setText(Utilidad.BTN_CANCELAR);
                
                Utilidad.desactivarBotones(btnNuevo, btnModificar, btnDisponibilidad, btnLimpiar);
                
                tblEspecies.setEnabled(false);
                limpiarControles();
                txtNombre.requestFocus();
                
                txtId.setText(String.valueOf(objEspecie.generarIDEspecie()));
                
            } else {
                if (Utilidad.verificarCamposLlenos(txtId, txtNombre)) {
                    JOptionPane.showMessageDialog(this, "Debe llenar todos los campos");
                } else if (Utilidad.validarElementoTextoRepetido("especie", "nombre", txtNombre.getText())) {
                    JOptionPane.showMessageDialog(this, "Ya existe este nombre de especie");
                } else {
                    int valor = Utilidad.mensajeConfirmarRegistro("especie", Integer.parseInt(txtId.getText()), txtNombre.getText());
                    if (valor == JOptionPane.YES_OPTION) {
                        btnNuevo.setText(Utilidad.BTN_NUEVO);
                        btnEliminar.setText(Utilidad.BTN_ELIMINAR);
                        
                        objEspecie.registrarEspecie(Integer.parseInt(txtId.getText()),
                                txtNombre.getText());
                        
                        tblEspecies.setEnabled(true);
                        
                        limpiarControles();
                        listarEspecies();
                        
                        Utilidad.activarBotones(btnBuscar, btnEliminar, btnLimpiar, btnDisponibilidad, btnModificar);
                        txtId.setEnabled(true);
                        JOptionPane.showMessageDialog(this, "Se registró con éxito");
                    } else {
                        JOptionPane.showMessageDialog(this, "Se canceló operación con éxito");
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error:" + e.getMessage());
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarControles();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void tblEspeciesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEspeciesMouseClicked
        
        if (tblEspecies.isEnabled()) {
            Utilidad.buscarPorTabla(tblEspecies, btnBuscar, txtId);
        } else {
            JOptionPane.showMessageDialog(null, "No se puede buscar por tabla mientras nos se cancele la operación");
        }
        

    }//GEN-LAST:event_tblEspeciesMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        ResultSet rsEspecie = null;
        
        try {
            if (txtId.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un código para buscar");
            } else {
                for (int i = 0; i < tblEspecies.getRowCount(); i++) {
                    String valorCodigo = tblEspecies.getValueAt(i, 0).toString();
                    if (valorCodigo.equals(txtId.getText())) {
                        tblEspecies.setRowSelectionInterval(i, i);
                        tblEspecies.scrollRectToVisible(tblEspecies.getCellRect(i, 0, true));
                        break;
                    }
                }
                rsEspecie = objEspecie.buscarEspecie(Integer.parseInt(txtId.getText()));
                Utilidad.desactivarFields(txtId, txtId, txtNombre);
                if (rsEspecie.next()) {
                    txtNombre.setText(rsEspecie.getString("nombre"));
                    
                    chkVigencia.setSelected(rsEspecie.getBoolean("disponibilidad"));
                    
                    rsEspecie.close();
                } else {
                    JOptionPane.showMessageDialog(this, "Codigo de especie no existente");
                    limpiarControles();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error " + e.getLocalizedMessage());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        try {
            if (txtId.getText().isBlank()) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un elemento a modificar");
            } else {
                if (btnModificar.getText().equals(Utilidad.BTN_MODIFICAR)) {
                    txtId.setEnabled(false);
                    Utilidad.activarFields(txtNombre);
                    btnModificar.setText(Utilidad.BTN_GUARDAR);
                    btnEliminar.setText(Utilidad.BTN_CANCELAR);
                    Utilidad.desactivarBotones(btnModificar, btnLimpiar, btnDisponibilidad, btnBuscar, btnNuevo);
                    tblEspecies.setEnabled(false);
                } else if (Utilidad.validarElementoTextoRepetido("especie", "nombre", txtNombre.getText())) {
                    JOptionPane.showMessageDialog(this, "Este nombre ya fue registrado");
                    limpiarControles();
                    btnModificar.setText(Utilidad.BTN_MODIFICAR);
                    btnEliminar.setText(Utilidad.BTN_ELIMINAR);
                    Utilidad.activarBotones(btnNuevo, btnBuscar, btnDisponibilidad, btnLimpiar, btnModificar);
                    tblEspecies.setEnabled(true);
                } else {
                    int valor = Utilidad.mensajeConfirmarModificar("Usuario", Integer.parseInt(txtId.getText()), txtNombre.getText());
                    if (valor == JOptionPane.YES_OPTION) {
                        
                        objEspecie.modificarEspecie(Integer.parseInt(txtId.getText()), txtNombre.getText());
                        btnModificar.setText(Utilidad.BTN_MODIFICAR);
                        btnEliminar.setText(Utilidad.BTN_ELIMINAR);
                        Utilidad.activarBotones(btnNuevo, btnBuscar, btnDisponibilidad, btnLimpiar, btnModificar);
                        limpiarControles();
                        listarEspecies();
                        JOptionPane.showMessageDialog(this, "Se modificó con exito");
                        tblEspecies.setEnabled(true);
                        txtId.setEnabled(true);
                    }
                    
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error:" + e.getMessage());
        }
    }//GEN-LAST:event_btnModificarActionPerformed
    
    private void eliminar() {
        try {
            if (txtId.getText().equals("")) {
                Utilidad.mensajeErrorFaltaID(this);
            } else if (Utilidad.validarEliminacionForanea("especie", Integer.parseInt(txtId.getText()))) {
                btnBuscarActionPerformed(null);
                Utilidad.mensajeErrorNoEliminarForanea("especie", txtNombre.getText());
            } else {
                btnBuscarActionPerformed(null);
                int valor = Utilidad.mensajeConfirmarEliminar("especie", Integer.parseInt(txtId.getText()), txtNombre.getText());
                if (valor == JOptionPane.YES_OPTION) {
                    objEspecie.eliminarEspecie(Integer.parseInt(txtId.getText()));
                    limpiarControles();
                    listarEspecies();
                    JOptionPane.showMessageDialog(this, "Se ha eliminado con éxito");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtNombreActionPerformed
    
    private void cancelarAccion() {
        btnNuevo.setText(Utilidad.BTN_NUEVO);
        btnModificar.setText(Utilidad.BTN_MODIFICAR);
        btnEliminar.setText(Utilidad.BTN_ELIMINAR);
        txtNombre.setEditable(false);
        limpiarControles();
        listarEspecies();
    }
    
    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
        Utilidad.validarCampoTextoSoloLetras(evt);
    }//GEN-LAST:event_txtNombreKeyTyped

    private void chkVigenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkVigenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkVigenciaActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (btnNuevo.getText().equals(Utilidad.BTN_GUARDAR) || btnModificar.getText().equals(Utilidad.BTN_GUARDAR)) {
            cancelarAccion();
            tblEspecies.setEnabled(true);
            txtId.setEnabled(true);
            Utilidad.activarBotones(btnNuevo, btnBuscar, btnDisponibilidad, btnLimpiar, btnModificar);
            
        } else {
            
            eliminar();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnDisponibilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisponibilidadActionPerformed
        // TODO add your handling code here:
        //        String id = txtID.getText();
        cambiarDisponibilidad();
    }//GEN-LAST:event_btnDisponibilidadActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        Utilidad.desactivarFields(txtId, txtId, txtNombre);
    }//GEN-LAST:event_formWindowOpened
    
    private void cambiarDisponibilidad() {
        String campoID = txtId.getText();
        int valorID;
        try {
            ResultSet rsCateg = null;
            if (campoID.isBlank()) {
                Utilidad.mensajeErrorFaltaID(this);
            } else {
                btnBuscarActionPerformed(null);
                valorID = Integer.parseInt(campoID);
                rsCateg = objEspecie.buscarEspecie(valorID);
                int valor = Utilidad.mensajeConfirmarDisponibilidad("especie",
                        Integer.parseInt(txtId.getText()), txtNombre.getText() + " " + txtNombre.getText());
                if (rsCateg.next()) {
                    if (valor == 0) {
                        objEspecie.cambiarDisponibilidad(valorID);
                        limpiarControles();
                        listarEspecies();
                        txtId.setText(String.valueOf(valorID));
                        btnBuscarActionPerformed(null);
                        Utilidad.mensajeExitoCambioDisponibilidad("Especie");
                    }
                }                
            }            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    
    public void limpiarControles() {
        txtId.setText("");
        txtNombre.setText("");
        txtNombre.setEditable(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDisponibilidad;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JCheckBox chkVigencia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblEspecies;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}

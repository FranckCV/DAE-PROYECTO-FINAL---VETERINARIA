/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package capaPresentacion;

import soporte.*;
import capaNegocio.*;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author franc
 */
public class jdMantEspecialidad extends javax.swing.JDialog {
    clsEspecialidad objTabla = new clsEspecialidad();
    
    /**
     * Creates new form jdMantMarca
     */
    public jdMantEspecialidad(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        btnNuevo.setText(Utilidad.BTN_NUEVO);
        btnModificar.setText(Utilidad.BTN_MODIFICAR);
        btnDisponibilidad.setText(Utilidad.BTN_DISPONIBILIDAD);
        btnEliminar.setText(Utilidad.BTN_ELIMINAR);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        chkDisponibilidad = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEspecialidad = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnDisponibilidad = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(0, 0, 102));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Especialidad");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(198, 198, 198)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(204, 255, 255));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/especialidad.png"))); // NOI18N
        jLabel4.setMaximumSize(new java.awt.Dimension(250, 250));
        jLabel4.setName(""); // NOI18N
        jLabel4.setPreferredSize(new java.awt.Dimension(250, 250));

        jPanel1.setBackground(new java.awt.Color(138, 238, 238));

        txtNombre.setEditable(false);
        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel2.setText("Nombre:");

        chkDisponibilidad.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        chkDisponibilidad.setText("(Disponibilidad)");
        chkDisponibilidad.setContentAreaFilled(false);
        chkDisponibilidad.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel3.setText("Disponibilidad:");

        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/opacado.png"))); // NOI18N
        btnLimpiar.setBorder(null);
        btnLimpiar.setContentAreaFilled(false);
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(75, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(chkDisponibilidad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLimpiar)
                        .addGap(18, 18, 18))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLimpiar)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(chkDisponibilidad))))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        tblEspecialidad.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tblEspecialidad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblEspecialidad.setToolTipText("");
        tblEspecialidad.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblEspecialidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEspecialidadMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEspecialidad);

        btnNuevo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/veterinario.png"))); // NOI18N
        btnNuevo.setText("reg");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/editar.png"))); // NOI18N
        btnModificar.setText("mod");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/eliminar.png"))); // NOI18N
        btnEliminar.setText("eli");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnDisponibilidad.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnDisponibilidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/disponible.png"))); // NOI18N
        btnDisponibilidad.setText("disp");
        btnDisponibilidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisponibilidadActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(138, 238, 238));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel6.setText("ID:");

        txtID.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIDKeyTyped(evt);
            }
        });

        btnBuscar.setBackground(new java.awt.Color(242, 242, 242));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/buscar-pequeño.png"))); // NOI18N
        btnBuscar.setBorder(null);
        btnBuscar.setContentAreaFilled(false);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscar)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDisponibilidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDisponibilidad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        if (btnNuevo.getText().equals(Utilidad.BTN_NUEVO)) {
            limpiarControles();
        } else {
            JOptionPane.showMessageDialog(this, "No puede ejecutar esta accion");
        }
        listarEspecialidades();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        listarEspecialidades();
    }//GEN-LAST:event_formWindowOpened

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        nuevaEspecialidad();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        buscarEspecialidad();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tblEspecialidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEspecialidadMouseClicked
        // TODO add your handling code here:
        try {
            txtID.setText(String.valueOf(tblEspecialidad.getValueAt(tblEspecialidad.getSelectedRow(), 0)));
            btnBuscarActionPerformed(null);  
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());
            JOptionPane.showMessageDialog(this,"Debe finalizar la operacion actual primero");
        }
      
    }//GEN-LAST:event_tblEspecialidadMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        if (btnNuevo.getText().equals(Utilidad.BTN_GUARDAR) || btnModificar.getText().equals(Utilidad.BTN_GUARDAR)) {
            cancelarOperacion();
        } else {
            eliminarEspecialidad();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        modificarEspecialidad();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnDisponibilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisponibilidadActionPerformed
        // TODO add your handling code here:
        if (!txtID.getText().isBlank()) {
            String id = txtID.getText();
            cambiarDisponibilidad();
            txtID.setText(id);
            btnBuscarActionPerformed(null);
        } else {
            Utilidad.mensajeErrorFaltaID(this);

        }
    }//GEN-LAST:event_btnDisponibilidadActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
        try {
            Utilidad.validarCampoTextoSoloLetras(evt);
            Utilidad.validarLimiteCampoTexto(evt, clsEspecialidad.NOMBRE, clsEspecialidad.TABLA);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en el campo "+evt.getSource().getClass().getName()+": " + e.getMessage());
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtIDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDKeyTyped
    
    private void listarEspecialidades(){
        ResultSet rsDato = null;
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Disponiblidad");
        tblEspecialidad.setModel(modelo);        
        try {
            rsDato = objTabla.listarEspecialidad();
            while (rsDato.next()){                
                modelo.addRow(new Object[]{
                    rsDato.getInt(clsEspecialidad.ID),
                    rsDato.getString(clsEspecialidad.NOMBRE),
                    Utilidad.textoBool(rsDato.getBoolean(clsEspecialidad.DISPONIBILIDAD), Utilidad.DISPONIBILIDAD_SI, Utilidad.DISPONIBILIDAD_NO)
                });
            }
            tblEspecialidad.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al listar especialidades registradas: " + e.getMessage());
        }
    }

//    private void generarCodigo() {
//        int idEspecialidad ;
//        try {
//            idEspecialidad = objTabla.generarIDEspecialidad();
//            txtID.setText(String.valueOf(idEspecialidad));            
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, e.getMessage());
//        }
//    }
    
    private void buscarEspecialidad() {
        ResultSet rsEsp = null;
        try {
            if (txtID.getText().isBlank()) {
                Utilidad.mensajeErrorFaltaID(this);
            } else {
                
                for (int i = 0; i < tblEspecialidad.getRowCount(); i++) {
                    String valorCodigo = tblEspecialidad.getValueAt(i, 0).toString();
                    if (valorCodigo.equals(txtID.getText())) {
                        tblEspecialidad.setRowSelectionInterval(i, i);
                        tblEspecialidad.scrollRectToVisible(tblEspecialidad.getCellRect(i, 0, true));
                        break;
                    }
                }
                
                rsEsp = objTabla.buscarEspecialidad(Integer.parseInt(txtID.getText()));
                if (rsEsp.next()){
                    txtNombre.setText(rsEsp.getString(clsEspecialidad.NOMBRE));
                    chkDisponibilidad.setSelected(rsEsp.getBoolean(clsEspecialidad.DISPONIBILIDAD));
                    rsEsp.close();
                } else {
                    JOptionPane.showMessageDialog(this, "Este codigo en "+clsEspecialidad.TABLA+" no existe");
                    limpiarControles();
                    listarEspecialidades();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error:" + e.getMessage());
            listarEspecialidades();
            limpiarControles();
        }
    }
    
    // de informacion
    
    private void usarBotones(boolean bus, boolean nue, boolean mod, boolean eli, boolean baj, boolean lim) {
        btnBuscar.setEnabled(bus);
        btnNuevo.setEnabled(nue);
        btnEliminar.setEnabled(eli);
        btnLimpiar.setEnabled(lim);
        btnModificar.setEnabled(mod);
        btnDisponibilidad.setEnabled(baj);
        tblEspecialidad.setEnabled(bus);
    }
    
    private void limpiarControles() {
        txtID.setText("");
        txtNombre.setText("");
        chkDisponibilidad.setSelected(false);
        
        txtID.requestFocus();        
    }
   
    private void editableControles(boolean cod, boolean nom) {
        txtID.setEditable(cod);
        txtNombre.setEditable(nom);
    }
          
    private void eliminarEspecialidad() {
        try {
            if (txtID.getText().isBlank()) {
                Utilidad.mensajeErrorFaltaID(this);
            } else if (Utilidad.validarEliminacionForanea(clsEspecialidad.TABLA, Integer.parseInt(txtID.getText()))){
                Utilidad.mensajeErrorNoEliminarForanea(clsEspecialidad.TABLA,txtNombre.getText());
            } else {
                int valor = Utilidad.mensajeConfirmarEliminar(clsEspecialidad.TABLA, Integer.parseInt(txtID.getText()), txtNombre.getText());
                
                if (valor == 0) {
                    objTabla.eliminarEspecialidad(Integer.parseInt(txtID.getText()));
                    limpiarControles();
                    listarEspecialidades();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage()  );
        }
    }
      
    private void cancelarOperacion() {
        btnNuevo.setText(Utilidad.BTN_NUEVO);
        btnModificar.setText(Utilidad.BTN_MODIFICAR);
        btnEliminar.setText(Utilidad.BTN_ELIMINAR);        
        limpiarControles();
        listarEspecialidades();
        editableControles(true, false);
        usarBotones(true, true, true, true, true ,true);
    }
   
    private void modificarEspecialidad() {
        try {
            if (txtID.getText().isBlank()) {
                Utilidad.mensajeErrorFaltaID(this);
            } else {
                if (btnModificar.getText().equals(Utilidad.BTN_MODIFICAR)) {
                    btnModificar.setText(Utilidad.BTN_GUARDAR);
                    btnEliminar.setText(Utilidad.BTN_CANCELAR);
                    editableControles(false, true);
                    usarBotones(false, false, true, true, false, false);
                } else {
                    int valor = Utilidad.mensajeConfirmarModificar(clsEspecialidad.TABLA, Integer.parseInt(txtID.getText()),txtNombre.getText());
                    if (valor == 0) {
                        objTabla.modificarEspecialidad(
                            Integer.parseInt(txtID.getText()),
                            txtNombre.getText()
                        );
                        btnModificar.setText(Utilidad.BTN_MODIFICAR);
                        btnEliminar.setText(Utilidad.BTN_ELIMINAR);
                        editableControles(true, false);
                        usarBotones(true, true, true, true, true, true);
                        limpiarControles();
                        listarEspecialidades();
                        txtID.setText(txtID.getText());
                        btnBuscarActionPerformed(null);
                        JOptionPane.showMessageDialog(this, "Se modificó con exito");
                    }
                }
            }            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error:" + e.getMessage());
        }
    }    

    private void nuevaEspecialidad() {
        try {
            if (btnNuevo.getText().equals(Utilidad.BTN_NUEVO)) {
                btnNuevo.setText(Utilidad.BTN_GUARDAR);
                btnEliminar.setText(Utilidad.BTN_CANCELAR);
                editableControles(false, true);
                usarBotones(false, true, false, true, false, false);
                limpiarControles();
                listarEspecialidades();
                txtID.setText(objTabla.generarIDEspecialidad().toString());
                txtNombre.requestFocus();
                chkDisponibilidad.setSelected(true);
            }else{
                if (txtNombre.getText().trim().isBlank() || txtID.getText().trim().isBlank()) {
                    JOptionPane.showMessageDialog(this, "Debe llenar todos los campos");
                } else {
                    btnNuevo.setText(Utilidad.BTN_NUEVO);
                    btnEliminar.setText(Utilidad.BTN_ELIMINAR);
                    objTabla.registrarEspecialidad(
                            Integer.parseInt(txtID.getText()),
                            txtNombre.getText()
                    );
                    editableControles(true, false);
                    usarBotones(true, true, true, true, true, true);
                    limpiarControles();
                    listarEspecialidades();
                    JOptionPane.showMessageDialog(this, "Se registró con exito");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error:" + e.getMessage());
        }
    }
    
    private void cambiarDisponibilidad() {
        String campoID = txtID.getText();
        int valorID = Integer.parseInt(txtID.getText());
        try {
            if (campoID.isBlank()) {
                Utilidad.mensajeErrorFaltaID(this);
            } else {
                int valor = Utilidad.mensajeConfirmarDisponibilidad(clsEspecialidad.TABLA, Integer.parseInt(txtID.getText()),txtNombre.getText());
                if (valor == 0) {
                    objTabla.cambiarDisponibilidad(Integer.parseInt(txtID.getText()));
                    limpiarControles();
                    listarEspecialidades();
                    JOptionPane.showMessageDialog(this, "Se cambió la disponibilidad de esta especialidad con exito");
                }                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDisponibilidad;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JCheckBox chkDisponibilidad;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tblEspecialidad;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}

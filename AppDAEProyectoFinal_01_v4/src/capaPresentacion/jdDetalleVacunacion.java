package capaPresentacion;

import capaNegocio.clsCustodia;
import capaNegocio.clsDetalleVacunacion;
import capaNegocio.clsMascota;
import capaNegocio.clsVacuna;
import java.awt.Frame;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import soporte.Utilidad;

public class jdDetalleVacunacion extends javax.swing.JDialog {

    private Integer codigoEspecie = null;
    jdDVacuna objagregarVac = null;
    jdCustodiaMasc objagregarMas = null;
    private Integer codigoVacuna = null;
    private Integer codigoMascota = null;
    /**
     * Creates new form jsDetalleVacunacion
     */
    clsCustodia objCus = new clsCustodia();
    clsVacuna objVac = new clsVacuna();
    clsMascota objMas = new clsMascota();
    clsDetalleVacunacion objDvac = new clsDetalleVacunacion();

    public jdDetalleVacunacion(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);

        initComponents();
        listarDetalle_V();
        dtcFechaNacimiento.setEnabled(false);
        btnMas.setEnabled(false);
        btnVac.setEnabled(false);
        txtObser.setEnabled(false);
    }

    private boolean asignarVacunacionJ(int vacId, int masId) {
        try {
            if (objDvac.existeDetalleVacunacion(vacId, masId)) {
                JOptionPane.showMessageDialog(this, "Error: La combinación de vacuna y mascota ya existe en el registro.");
                return false; // Si ya existe, devolvemos false
            }
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al realizar la acción: " + e.getMessage());
            return false; // Si hay un error, devolvemos false para detener el proceso
        }
    }

    private void limpiarControles() {
        jTextField1.setText("");
        jLabel1.setText("");
        codigoVacuna = 0;
        codigoMascota = 0;
        jLabel2.setText("");
        dtcFechaNacimiento.setDate(null);
        txtObser.setText("");
        tblDetVac1.clearSelection();

    }

    public void listarDetalle_V() {
        ResultSet rsVac = null;
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Código Vacuna");
        modelo.addColumn("Nombre Vacuna");
        modelo.addColumn("Código Mascota");
        modelo.addColumn("Nombre Mascota");
        modelo.addColumn("Fecha Vacuna");
        modelo.addColumn("Observación");
        tblDetVac1.setModel(modelo);
        Utilidad.alineacionDerechaColumnaTabla(tblDetVac1, 0);
        Utilidad.alineacionDerechaColumnaTabla(tblDetVac1, 2);
        Utilidad.alineacionDerechaColumnaTabla(tblDetVac1, 4);
        Utilidad.tamañoColumnaTablaxPos(tblDetVac1, 0, 20);
        Utilidad.tamañoColumnaTablaxPos(tblDetVac1, 2, 20);
        Utilidad.tamañoColumnaTablaxPos(tblDetVac1, 4, 50);
        
        try {
            rsVac = objDvac.listarDetalleVigentes();
            while (rsVac.next()) {
                modelo.addRow(new Object[]{
                    rsVac.getInt("vacuna_id"),
                    rsVac.getString("nom_vac"),
                    rsVac.getInt("mascota_id"),
                    rsVac.getString("nom_mas"),
                    Utilidad.textoFormatoFecha(rsVac.getString("fecha_vacuna")),
                    rsVac.getString("observacion")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al listar vacunas: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtObser = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnVac = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dtcFechaNacimiento = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        btnMas = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblDetVac1 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        btnMas1 = new javax.swing.JButton();
        btnRe = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel5.setBackground(new java.awt.Color(0, 0, 102));

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("DETALLE VACUNACIÓN");

        jPanel6.setBackground(new java.awt.Color(204, 204, 255));
        jPanel6.setForeground(new java.awt.Color(204, 204, 255));

        txtObser.setColumns(20);
        txtObser.setRows(5);
        jScrollPane3.setViewportView(txtObser);

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel4.setText("Observación:");

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel7.setText("Seleccionar Vacuna:");

        btnVac.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnVac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/buscar-pequeño.png"))); // NOI18N
        btnVac.setBorderPainted(false);
        btnVac.setContentAreaFilled(false);
        btnVac.setFocusPainted(false);
        btnVac.setRequestFocusEnabled(false);
        btnVac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVacActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel3.setText("Fecha Vacunación:");

        dtcFechaNacimiento.setDateFormatString("yyyy-MM-dd");
        dtcFechaNacimiento.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        dtcFechaNacimiento.setOpaque(false);
        dtcFechaNacimiento.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtcFechaNacimientoPropertyChange(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel6.setText("Seleccionar Mascota:");

        btnMas.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnMas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/buscar-pequeño.png"))); // NOI18N
        btnMas.setBorderPainted(false);
        btnMas.setContentAreaFilled(false);
        btnMas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasActionPerformed(evt);
            }
        });

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

        tblDetVac1.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDetVac1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetVac1MouseClicked(evt);
            }
        });
        tblDetVac1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblDetVac1KeyTyped(evt);
            }
        });
        jScrollPane5.setViewportView(tblDetVac1);

        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel10.setText("Buscar por nombre de mascota:");

        btnMas1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnMas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/buscar-pequeño.png"))); // NOI18N
        btnMas1.setBorderPainted(false);
        btnMas1.setContentAreaFilled(false);
        btnMas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMas1ActionPerformed(evt);
            }
        });

        btnRe.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnRe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/veterinario.png"))); // NOI18N
        btnRe.setText("Asignar Vacunación");
        btnRe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/eliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar Asignación");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtcFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(btnLimpiar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnMas1)
                            .addGap(12, 12, 12))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRe, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(37, 37, 37)
                                .addComponent(btnMas))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnVac)
                                .addGap(38, 38, 38))
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dtcFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnMas)
                        .addGap(28, 28, 28))
                    .addComponent(btnVac, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnRe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnMas1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed

        listarDetalle_V();
        limpiarControles();


    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (btnRe.getText().equals("Guardar Datos")) {
            try {
                listarDetalle_V();
            limpiarControles();
                btnRe.setText("Asignar Vacunación");
                btnVac.setEnabled(false);
                btnMas.setEnabled(false);
                dtcFechaNacimiento.setEnabled(false);
                btnMas1.setEnabled(true);
                jTextField1.setEditable(true);
                btnLimpiar.setEnabled(true);
                btnEliminar.setText("Eliminar Asignación");
                btnEliminar.setEnabled(true);
                txtObser.setEditable(true);
            } catch (Exception ex) {
                Logger.getLogger(jdMantMascota.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (tblDetVac1.getSelectedRow() != -1) {
            eliminarAsignacion();
            listarDetalle_V();
            limpiarControles();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una fila de la tabla Vacunación para hacer esta operacion");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed


    private void btnReActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReActionPerformed
        Object[] opciones = {"Sí", "No"};
        int respuesta = JOptionPane.showOptionDialog(
                null,
                "¿Seguro que desea asignar una vacunación?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );
        if (respuesta == 0) { // Opción "Sí"
            btnRe.setText("Guardar Datos");
            
            btnVac.setEnabled(true);
            btnMas.setEnabled(true);
            txtObser.setEnabled(true);
            dtcFechaNacimiento.setEnabled(true);
            btnLimpiar.setEnabled(false);
            btnEliminar.setText("Cancelar");
            btnMas1.setEnabled(false);
            jTextField1.setEnabled(false);
        } else if (respuesta == 1) { // Opción "No"
            limpiarControles();
            btnVac.setEnabled(false);
            btnRe.setText("Asignar Vacuna");
                        btnEliminar.setText("Eliminar Custodia");

            btnMas.setEnabled(false);
            dtcFechaNacimiento.setEnabled(false);
            btnLimpiar.setEnabled(true);
            btnEliminar.setEnabled(true);
            btnMas1.setEnabled(true);
            jTextField1.setEnabled(true);
            txtObser.setEnabled(false);
            return;
        }
        if (btnRe.getText().equals("Guardar Datos")) {
            try {
                if (codigoVacuna == 0) {
                    JOptionPane.showMessageDialog(this, "Debe seleccionar una vacuna válida antes de continuar.");
                    return;
                }
                if (codigoMascota == 0) {
                    JOptionPane.showMessageDialog(this, "Debe seleccionar una Mascota válida antes de continuar.");
                    return;
                }
                if (objDvac.existeDetalleVacunacion(codigoVacuna, codigoMascota)) {
                    JOptionPane.showMessageDialog(this, "Error: Ya existe registro de vacunación para esta Mascota.");
                    limpiarControles();
                    btnRe.setText("Asignar Vacuna");
                    btnVac.setEnabled(true);
                    btnMas.setEnabled(true);
                    dtcFechaNacimiento.setEnabled(true);
                    btnLimpiar.setEnabled(false);
                    btnEliminar.setEnabled(false);
                    btnMas1.setEnabled(false);
                    jTextField1.setEnabled(false);
                    return;
                }
                if (dtcFechaNacimiento.getDate() == null) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar la fecha de vacunación");

                }
                Date fechaNacimiento = dtcFechaNacimiento.getDate();
                objDvac.asignarVacuna(codigoVacuna, codigoMascota, fechaNacimiento, txtObser.getText());
                JOptionPane.showMessageDialog(this, "Vacuna asignada con éxito");
                limpiarControles();
                btnRe.setText("Asignar Vacuna");
                btnVac.setEnabled(false);
                btnMas.setEnabled(false);
                dtcFechaNacimiento.setEnabled(false);
                btnLimpiar.setEnabled(true);
                btnEliminar.setEnabled(true);
                btnMas1.setEnabled(true);
                txtObser.setEnabled(false);
                            btnEliminar.setText("Eliminar Asignación");

                jTextField1.setEnabled(true);
                listarDetalle_V();
            } catch (Exception e) {
                btnRe.setText("Guardar Datos");
            }
        }

    }//GEN-LAST:event_btnReActionPerformed

    private void btnMasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasActionPerformed
        if (objagregarMas == null) {
            try {
                objagregarMas = new jdCustodiaMasc(null, true); // Crea la instancia
            } catch (Exception ex) {
                Logger.getLogger(jdDetalleVacunacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            objagregarMas.setVisible(true); // Abre el diálogo para seleccionar mascota
            codigoMascota = objagregarMas.getCod(); // Obtén el código de la mascota
            codigoEspecie = objagregarMas.getCodigoEspecie(); // Obtén el código de especie asociado

            if (codigoMascota > 0) {
                agregarMasc(codigoMascota); // Actualiza detalles en la interfaz principal
            } else {
                JOptionPane.showMessageDialog(this, "No se seleccionó una mascota válida.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al seleccionar la mascota: " + e.getMessage());
        }
    }//GEN-LAST:event_btnMasActionPerformed

    private void btnVacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVacActionPerformed
        if (codigoEspecie == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una mascota válida primero.");
            return;
        }

        try {
            jdDVacuna objForm = new jdDVacuna(null, true, codigoEspecie);
            objForm.setLocationRelativeTo(this);
            objForm.setVisible(true);
            Integer vacuna = objForm.getCod();
            if (vacuna != null && vacuna != 0) {
                codigoVacuna = vacuna;
                try {
                    agregarVac(vacuna);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "ERROR EN EL PROCESO DE GUARDADO: " + e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se seleccionó ninguna vacuna.");
            }
        } catch (Exception ex) {
            Logger.getLogger(jdDetalleVacunacion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnVacActionPerformed

    private void tblDetVac1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetVac1MouseClicked
        int filaSeleccionada = tblDetVac1.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fila válida.");
            return;
        }
    }//GEN-LAST:event_tblDetVac1MouseClicked

    private void tblDetVac1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblDetVac1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDetVac1KeyTyped

    private void btnMas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMas1ActionPerformed
        ResultSet rsDuenio = null;
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Código Vacuna");
        modelo.addColumn("Nombre Vacuna");
        modelo.addColumn("Código Mascota");
        modelo.addColumn("Nombre Mascota");
        modelo.addColumn("Fecha Vacuna");
        modelo.addColumn("Observación");
        tblDetVac1.setModel(modelo);
        try {
            if (jTextField1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe llenar el campo vacío para realizar la búsqueda");
                listarDetalle_V();
                jTextField1.setText("");
                return;
            }
            rsDuenio = objDvac.filtrarporNombreNVIG(jTextField1.getText());
            if (rsDuenio != null && rsDuenio.next()) {
                do {
                    modelo.addRow(new Object[]{
                        rsDuenio.getInt("vacuna_id"),
                        rsDuenio.getString("nom_vac"),
                        rsDuenio.getInt("mascota_id"),
                        rsDuenio.getString("nom_mas"),
                        rsDuenio.getString("fecha_vacuna"),
                        rsDuenio.getString("observacion")
                    });
                } while (rsDuenio.next());
            } else {
                JOptionPane.showMessageDialog(this, "No se encontraron resultados para el documento ingresado");
                listarDetalle_V();
                jTextField1.setText("");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error en la búsqueda: " + e.getMessage());
        }
    }//GEN-LAST:event_btnMas1ActionPerformed

    private void dtcFechaNacimientoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtcFechaNacimientoPropertyChange
        Utilidad.validarFechasFuturas(dtcFechaNacimiento, evt);
    }//GEN-LAST:event_dtcFechaNacimientoPropertyChange
    private void agregarVac(int codVac) {
        if (codVac > 0) {
            ResultSet rs = null;
            try {
                rs = objDvac.filtrarVacunas(codVac);
                if (rs != null && rs.next()) {
                    StringBuilder datosVacuna = new StringBuilder();
                    datosVacuna.append("").append(rs.getString("nombre"))
                            .append("\n");
                    jLabel2.setText(datosVacuna.toString());
                } else {
                    jLabel2.setText("No se encontraron datos para el código: " + codVac);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "ERROR AL MOSTRAR LOS DATOS DE LA VACUNA--->" + e.getMessage());
            }
        } else {
            jLabel2.setText("Código del vacuna inválido.");
        }
    }

    private void eliminarAsignacion() {
        int fila = tblDetVac1.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un registro para eliminar.");
            return;
        }

        try {
            Object valorColumna1 = tblDetVac1.getValueAt(fila, 3);
            Object valorColumna2 = tblDetVac1.getValueAt(fila, 2);
            Object valorColumna0 = tblDetVac1.getValueAt(fila, 0);

            if (valorColumna2 == null || valorColumna0 == null) {
                JOptionPane.showMessageDialog(this, "El registro seleccionado tiene datos incompletos.");
                return;
            }

            if (Utilidad.validarEliminacionForanea("DETALLES_VACUNACION", fila)) {
                JOptionPane.showMessageDialog(this,
                        "Hay datos externos asociados al servicio asignado \""
                        + valorColumna1.toString()
                        + "\".\nConsidere cambiar su disponibilidad o vigencia para que ya no pueda ser usado."
                );
            } else {
                objDvac.eliminarDetalleVac(
                        Integer.parseInt(valorColumna0.toString()),
                        Integer.parseInt(valorColumna2.toString())
                );
                JOptionPane.showMessageDialog(this, "Eliminación exitosa.");
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Error de formato en los datos: " + nfe.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage());
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
                    jLabel1.setText(datosMascota.toString());
                    codigoVacuna = null; // Resetea el código de la vacuna
                    jLabel2.setText("");
                } else {
                    jLabel1.setText("No se encontraron datos para el código: " + codMas);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "ERROR AL MOSTRAR LOS DATOS DE LA MASCOTA---> " + e.getMessage());
            }
        } else {
            jLabel1.setText("Código de la mascota inválido.");
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnMas;
    private javax.swing.JButton btnMas1;
    private javax.swing.JButton btnRe;
    private javax.swing.JButton btnVac;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.toedter.calendar.JDateChooser dtcFechaNacimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblDetVac1;
    private javax.swing.JTextArea txtObser;
    // End of variables declaration//GEN-END:variables
}

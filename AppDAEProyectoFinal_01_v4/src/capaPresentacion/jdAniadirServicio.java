/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package capaPresentacion;

import capaNegocio.clsMedico;
import capaNegocio.clsServicio;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import soporte.Utilidad;

/**
 *
 * @author Junior
 */
public class jdAniadirServicio extends javax.swing.JDialog {

    clsServicio objServicio = new clsServicio();
    clsMedico objMedico = new clsMedico();

    String docMedico = "";
    Integer codServicio = -1;
    private Integer codServicioPasar = -1;
    private Integer codMedicoPasar = -1;
    private String horaEntradaPasar = "";
    private String horaSalidaPasar = "";

    public jdAniadirServicio(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        llenarMedico();
        llenarServicio();
        llenarComboHorasMinutosYAMPM();
        listarDetalleServicios();
        Utilidad.validacionTabla(tblServicios, modal, rootPaneCheckingEnabled, modal);
    }

    private void ocultarColumna(JTable tabla, int indiceColumna) {
        tabla.getColumnModel().getColumn(indiceColumna).setMaxWidth(0);
        tabla.getColumnModel().getColumn(indiceColumna).setMinWidth(0);
        tabla.getColumnModel().getColumn(indiceColumna).setWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(indiceColumna).setMaxWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(indiceColumna).setMinWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(indiceColumna).setWidth(0);
    }

    private void llenarServicio() {
        ResultSet rsSer = null;
        DefaultComboBoxModel modeloSer = new DefaultComboBoxModel();
        modeloSer.addElement("-Ninguno-");

        try {
            rsSer = objServicio.listarServicios();
            while (rsSer.next()) {
                modeloSer.addElement(rsSer.getString("nom_servicio"));
            }
            cboServicio.setModel(modeloSer);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al llenar servicios");
        }
    }

    private void llenarComboHorasMinutosYAMPM() {

        cboHoraEntrada.removeAllItems();
        cboMinutosEntrada.removeAllItems();
        cboAMPMEntrada.removeAllItems();

        cboHoraSalida.removeAllItems();
        cboAMPMSalida.removeAllItems();
        cboMinutosSalida.removeAllItems();

        for (int i = 0; i <= 12; i++) {
            cboHoraEntrada.addItem(String.format("%02d", i));
            cboHoraSalida.addItem(String.format("%02d", i)); // Formato para mostrar siempre dos dígitos (ej. "00", "01", ..., "12")
        }

        // Llenar el JComboBox para minutos (00, 15, 30, 45)
        int[] minutos = {0, 15, 30, 45};
        for (int minuto : minutos) {
            cboMinutosEntrada.addItem(String.format("%02d", minuto));
            cboMinutosSalida.addItem(String.format("%02d", minuto)); // Formato para mostrar siempre dos dígitos (ej. "00", "15", "30", "45")
        }

        // Llenar el JComboBox para AM/PM
        String[] amPm = {"AM", "PM"};
        for (String periodo : amPm) {
            cboAMPMEntrada.addItem(periodo);  // Llenamos el combo con "AM" y "PM"
            cboAMPMSalida.addItem(periodo);
        }
    }

    private void llenarMedico() {
        ResultSet rsMed = null;
        DefaultComboBoxModel modeloMed = new DefaultComboBoxModel();
        modeloMed.addElement("-Ninguno-");

        try {
            rsMed = objMedico.listarMedicos();
            while (rsMed.next()) {
                modeloMed.addElement("DNI: " + rsMed.getString("doc_identidad") + " | "
                        + rsMed.getString("nombres") + " " + rsMed.getString("apepaterno"));
            }
            cboMedico.setModel(modeloMed);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al llenar medicos");
        }
    }

    private void listarDetalleServicios() {
        ResultSet rsServicios = null;
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID SERV_MED");
        modelo.addColumn("SERVICIO");
        modelo.addColumn("MEDICO");
        modelo.addColumn("COSTO");

        try {

            if (!cboMedico.getSelectedItem().equals("-Ninguno-")) {
                if (!cboServicio.getSelectedItem().equals("-Ninguno-")) {
                    rsServicios = objServicio.obtenerDatosDetalleServicio(codServicio, docMedico);
                } else {
                    rsServicios = objServicio.obtenerDatosDetalleServicioPorMedico(docMedico);
                }
            } else {
                if (!cboServicio.getSelectedItem().equals("-Ninguno-")) {
                    rsServicios = objServicio.obtenerDatosDetalleServicioPorCodServicio(codServicio);
                } else {
                    rsServicios = objServicio.obtenerDatosDetalleServicioTodos();
                }
            }

            while (rsServicios.next()) {
                modelo.addRow(new Object[]{rsServicios.getInt("ID") + " - " + rsServicios.getInt("medico_id"),
                    rsServicios.getString("nom_servicio"),
                    rsServicios.getString("nombres") + " " + rsServicios.getString("apepaterno"),
                    rsServicios.getString("costo")
                });
            }

            tblServicios.setModel(modelo);
            txtTotalProductos.setText(String.valueOf(tblServicios.getRowCount()));

            ocultarColumna(tblServicios, 0);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }

    }

    private void pasarDatos(int codSer, int codMed, String horaE, String horaS) {
        try {
            if (JOptionPane.showConfirmDialog(rootPane, "¿Desea agregar el servicio?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                codServicioPasar = codSer;
                codMedicoPasar = codMed;
                
                horaSalidaPasar = horaS;
                horaEntradaPasar = horaE;
                dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }

    public String getHoraEntrada() {
        return horaEntradaPasar;
    }

    public String getHoraSalida() {
        return horaSalidaPasar;
    }

    public int getCodServicio() {
        return codServicioPasar;
    }

    public int getCodMedico() {
        return codMedicoPasar;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblServicios = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTotalProductos = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        cboServicio = new javax.swing.JComboBox<>();
        btnLimpiar = new javax.swing.JButton();
        cboMedico = new javax.swing.JComboBox<>();
        txtNombre = new javax.swing.JTextField();
        cboHoraEntrada = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cboMinutosEntrada = new javax.swing.JComboBox<>();
        cboAMPMEntrada = new javax.swing.JComboBox<>();
        cboMinutosSalida = new javax.swing.JComboBox<>();
        cboAMPMSalida = new javax.swing.JComboBox<>();
        cboHoraSalida = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblServicios.setModel(new javax.swing.table.DefaultTableModel(
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
        tblServicios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblServiciosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblServicios);

        jLabel1.setText("Servicio:");

        jLabel2.setText("Medico:");

        jLabel6.setText("Total de Elegibles:");

        txtTotalProductos.setText("total");

        jLabel4.setText("Palabra clave:");

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        cboServicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboServicioActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Guardar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        cboMedico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMedicoActionPerformed(evt);
            }
        });

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        cboHoraEntrada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Hora Entrada:");

        cboMinutosEntrada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboAMPMEntrada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboMinutosSalida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboAMPMSalida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboHoraSalida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("Hora Salida:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboServicio, 0, 303, Short.MAX_VALUE)
                            .addComponent(cboMedico, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNombre)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTotalProductos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLimpiar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSalir))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(cboHoraEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cboMinutosEntrada, 0, 1, Short.MAX_VALUE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cboAMPMEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(cboHoraSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cboMinutosSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cboAMPMSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cboServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboHoraEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboMinutosEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboAMPMEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboHoraSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboMinutosSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboAMPMSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTotalProductos)
                    .addComponent(btnSalir)
                    .addComponent(btnLimpiar))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblServiciosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblServiciosMouseClicked

        String cadena = String.valueOf(tblServicios.getValueAt(tblServicios.getSelectedRow(), 0));
        String[] codigos = cadena.split(" - ");
        int codSer = Integer.parseInt(codigos[0].trim());
        int codMed = Integer.parseInt(codigos[1].trim());
        String horaEntrada = cboHoraEntrada.getSelectedItem().toString() + ":" + cboMinutosEntrada.getSelectedItem().toString() + ":00 " + cboAMPMEntrada.getSelectedItem().toString();
        String horaSalida = cboHoraSalida.getSelectedItem().toString() + ":" + cboMinutosSalida.getSelectedItem().toString() + ":00 " + cboAMPMSalida.getSelectedItem().toString();
        System.out.println(codSer + " - " + codMed);
        try {
            pasarDatos(codSer, codMed, horaEntrada, horaSalida);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Cantidad no válida");
        }

    }//GEN-LAST:event_tblServiciosMouseClicked

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void cboServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboServicioActionPerformed
        try {
            codServicio = objServicio.obtenerID(cboServicio.getSelectedItem().toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "No se obtuvo el id de servicio" + e.getMessage());
        }

        listarDetalleServicios();

    }//GEN-LAST:event_cboServicioActionPerformed

    private void cboMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMedicoActionPerformed
        String selectedItem = cboMedico.getSelectedItem().toString();

        String[] parts = selectedItem.split(" ");
        if (parts.length > 1) {
            docMedico = parts[1];

            System.out.println(docMedico);
        }

        listarDetalleServicios();
    }//GEN-LAST:event_cboMedicoActionPerformed

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        listarDetalleServicios();
    }//GEN-LAST:event_txtNombreKeyReleased

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cboAMPMEntrada;
    private javax.swing.JComboBox<String> cboAMPMSalida;
    private javax.swing.JComboBox<String> cboHoraEntrada;
    private javax.swing.JComboBox<String> cboHoraSalida;
    private javax.swing.JComboBox<String> cboMedico;
    private javax.swing.JComboBox<String> cboMinutosEntrada;
    private javax.swing.JComboBox<String> cboMinutosSalida;
    private javax.swing.JComboBox<String> cboServicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblServicios;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JLabel txtTotalProductos;
    // End of variables declaration//GEN-END:variables
}

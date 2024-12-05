/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package capaPresentacion;

import capaNegocio.clsMedico;
import capaNegocio.clsServicio;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
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
    private String notaAdicionalPasar = "";

    public jdAniadirServicio(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setTitle("Agregar servicio");

        txtSeleccion.setEditable(false);
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
            JOptionPane.showMessageDialog(this, "Error al llenar servicios", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
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
            cboHoraSalida.addItem(String.format("%02d", i)); // Formato para mostrar siempre dos dígitos (ej. "00", "01", ..., "12")
            cboHoraEntrada.addItem(String.format("%02d", i));
        }

        // Llenar el JComboBox para minutos (00, 15, 30, 45)
        int[] minutos = {0, 15, 30, 45};
        for (int minuto : minutos) {
            cboMinutosSalida.addItem(String.format("%02d", minuto)); // Formato para mostrar siempre dos dígitos (ej. "00", "15", "30", "45")
            cboMinutosEntrada.addItem(String.format("%02d", minuto));
        }

        // Llenar el JComboBox para AM/PM
        String[] amPm = {"AM", "PM"};
        for (String periodo : amPm) {
            cboAMPMEntrada.addItem(periodo);  // Llenamos el combo con "AM" y "PM"
            cboAMPMSalida.addItem(periodo);
        }
    }

    private Date convertirHoraAFecha(String hora) {
        // Detectamos si es AM o PM
        String amPm = hora.substring(hora.length() - 2).trim();
        int horas = Integer.parseInt(hora.substring(0, 2).trim());
        int minutos = Integer.parseInt(hora.substring(3, 5).trim());

        if (amPm.equalsIgnoreCase("PM") && horas != 12) {
            horas += 12;
        } else if (amPm.equalsIgnoreCase("AM") && horas == 12) {
            horas = 0;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, horas);
        calendar.set(Calendar.MINUTE, minutos);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime();
    }

    private void llenarMedico() {
        ResultSet rsMed = null;
        DefaultComboBoxModel modeloMed = new DefaultComboBoxModel();
        modeloMed.addElement("-Ninguno-");

        try {
            rsMed = objMedico.listarMedicosVigentesDisponibles();
            while (rsMed.next()) {
                modeloMed.addElement("DNI: " + rsMed.getString("doc_identidad") + " | "
                        + rsMed.getString("nombres") + " " + rsMed.getString("apepaterno"));
            }
            cboMedico.setModel(modeloMed);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al llenar medicos", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void alinearIzquierda(int columna) {
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        tblServicios.getColumnModel().getColumn(columna).setCellRenderer(leftRenderer);
    }

    private void alinearDerecha(int columna) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        tblServicios.getColumnModel().getColumn(columna).setCellRenderer(rightRenderer);
    }

    private void alinearCentro(int columna) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tblServicios.getColumnModel().getColumn(columna).setCellRenderer(centerRenderer);
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
            alinearDerecha(3);
            alinearIzquierda(1);
            alinearIzquierda(2);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage(), "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private void pasarDatos(int codSer, int codMed, String horaE, String horaS, String notita) {
        try {
            codServicioPasar = codSer;
            codMedicoPasar = codMed;

            horaSalidaPasar = horaS;
            horaEntradaPasar = horaE;

            notaAdicionalPasar = notita;
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage(), "Mensaje", JOptionPane.INFORMATION_MESSAGE);
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

    public String getNota() {
        return notaAdicionalPasar;
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
        txtSeleccion = new javax.swing.JTextField();
        cboHoraEntrada = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cboMinutosEntrada = new javax.swing.JComboBox<>();
        cboAMPMEntrada = new javax.swing.JComboBox<>();
        cboMinutosSalida = new javax.swing.JComboBox<>();
        cboAMPMSalida = new javax.swing.JComboBox<>();
        cboHoraSalida = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNota = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Añadir Servicio");

        tblServicios.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
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

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel1.setText("Servicio:");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel2.setText("Medico:");

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel6.setText("Total de Elegibles:");

        txtTotalProductos.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtTotalProductos.setText("total");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel4.setText("Seleccion:");

        btnSalir.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        cboServicio.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cboServicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboServicioActionPerformed(evt);
            }
        });

        btnLimpiar.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnLimpiar.setText("Guardar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        cboMedico.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cboMedico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMedicoActionPerformed(evt);
            }
        });

        txtSeleccion.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtSeleccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSeleccionActionPerformed(evt);
            }
        });
        txtSeleccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSeleccionKeyReleased(evt);
            }
        });

        cboHoraEntrada.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cboHoraEntrada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboHoraEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboHoraEntradaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel3.setText("Hora Entrada:");

        cboMinutosEntrada.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cboMinutosEntrada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboMinutosEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMinutosEntradaActionPerformed(evt);
            }
        });

        cboAMPMEntrada.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cboAMPMEntrada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboMinutosSalida.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cboMinutosSalida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboAMPMSalida.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cboAMPMSalida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboHoraSalida.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cboHoraSalida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel8.setText("Hora Salida:");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel5.setText("Nota Adicional:");

        txtNota.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cboHoraEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cboMinutosEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 1, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cboServicio, 0, 303, Short.MAX_VALUE)
                                    .addComponent(cboMedico, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(1, 1, 1)))
                .addContainerGap(29, Short.MAX_VALUE))
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
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
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
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
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

        String servicio = tblServicios.getValueAt(tblServicios.getSelectedRow(), 1).toString();
        String medico = tblServicios.getValueAt(tblServicios.getSelectedRow(), 2).toString();
        txtSeleccion.setText(servicio + " - " + medico);

    }//GEN-LAST:event_tblServiciosMouseClicked

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void cboServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboServicioActionPerformed
        try {
            codServicio = objServicio.obtenerID(cboServicio.getSelectedItem().toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "No se obtuvo el id de servicio" + e.getMessage(), "Mensaje", JOptionPane.INFORMATION_MESSAGE);
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

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed

        Date hora1 = convertirHoraAFecha(cboHoraEntrada.getSelectedItem().toString() + ":"
                + cboMinutosEntrada.getSelectedItem().toString()
                + cboAMPMEntrada.getSelectedItem().toString());

        Date hora2 = convertirHoraAFecha(cboHoraSalida.getSelectedItem().toString() + ":"
                + cboMinutosSalida.getSelectedItem().toString()
                + cboAMPMSalida.getSelectedItem().toString());

        Calendar inicioRango = Calendar.getInstance();
        inicioRango.set(Calendar.HOUR_OF_DAY, 8);
        inicioRango.set(Calendar.MINUTE, 0);
        inicioRango.set(Calendar.SECOND, 0);

        Calendar finRango = Calendar.getInstance();
        finRango.set(Calendar.HOUR_OF_DAY, 20);
        finRango.set(Calendar.MINUTE, 0);
        finRango.set(Calendar.SECOND, 0);

        if (hora1.before(inicioRango.getTime()) || hora1.after(finRango.getTime())) {
            JOptionPane.showMessageDialog(this, "La hora de entrada debe estar entre las 8 AM y las 8 PM.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } else if (hora2.before(hora1) || (hora2.getTime() - hora1.getTime()) < 15 * 60 * 1000) {

            JOptionPane.showMessageDialog(this, "La hora de salida debe ser al menos 15 minutos después de la de entrada.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } else {
            int valor = Utilidad.mensajeConfirmarAgregarServicio("Servicio");

            if (valor == 0) {
                if (tblServicios.getSelectedRow() != -1) {
                    String cadena = String.valueOf(tblServicios.getValueAt(tblServicios.getSelectedRow(), 0));
                    String[] codigos = cadena.split(" - ");
                    int codSer = Integer.parseInt(codigos[0].trim());
                    int codMed = Integer.parseInt(codigos[1].trim());
                    String horaEntrada = cboHoraEntrada.getSelectedItem().toString() + ":" + cboMinutosEntrada.getSelectedItem().toString() + ":00 " + cboAMPMEntrada.getSelectedItem().toString();
                    String horaSalida = cboHoraSalida.getSelectedItem().toString() + ":" + cboMinutosSalida.getSelectedItem().toString() + ":00 " + cboAMPMSalida.getSelectedItem().toString();
                    String notaAdicional = txtNota.getText();
                    System.out.println(codSer + " - " + codMed);
                    try {
                        pasarDatos(codSer, codMed, horaEntrada, horaSalida, notaAdicional);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(rootPane, "Ocurrió un error", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Por favor seleccionar en la tabla el servicio", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void txtSeleccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSeleccionKeyReleased

    }//GEN-LAST:event_txtSeleccionKeyReleased

    private void txtSeleccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSeleccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSeleccionActionPerformed

    private void cboHoraEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboHoraEntradaActionPerformed
        if (cboHoraEntrada.getSelectedIndex() != -1) {
            cboHoraSalida.setSelectedIndex(cboHoraEntrada.getSelectedIndex());
        }
    }//GEN-LAST:event_cboHoraEntradaActionPerformed

    private void cboMinutosEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMinutosEntradaActionPerformed
        if (cboHoraEntrada.getSelectedIndex() != -1 && cboMinutosEntrada.getSelectedIndex() != -1) {
            if (cboMinutosEntrada.getSelectedIndex() == 3) {
                int horaEntradaIndex = cboHoraEntrada.getSelectedIndex();
                if (horaEntradaIndex + 1 < cboHoraSalida.getItemCount()) {
                    cboHoraSalida.setSelectedIndex(horaEntradaIndex + 1);
                }
            } else {
                int minutoEntradaIndex = cboMinutosEntrada.getSelectedIndex();
                if (minutoEntradaIndex + 1 < cboMinutosSalida.getItemCount()) {
                    cboMinutosSalida.setSelectedIndex(minutoEntradaIndex + 1);
                }
            }
        }
    }//GEN-LAST:event_cboMinutosEntradaActionPerformed

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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblServicios;
    private javax.swing.JTextField txtNota;
    private javax.swing.JTextField txtSeleccion;
    private javax.swing.JLabel txtTotalProductos;
    // End of variables declaration//GEN-END:variables
}

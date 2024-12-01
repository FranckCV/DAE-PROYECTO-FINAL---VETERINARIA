/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package capaPresentacion;

import capaNegocio.clsCustodia;
import capaNegocio.clsDuenio;
import capaNegocio.clsMascota;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import soporte.Utilidad;

/**
 *
 * @author Windows10
 */
public class jdCustodia extends javax.swing.JDialog {

    clsMascota objMas = new clsMascota();
    clsDuenio objDue = new clsDuenio();
    clsCustodia objCus = new clsCustodia();

    public jdCustodia(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
        initComponents();
        listarMascotas();
        listarDuenios();
        buttonGroup1.add(jRBCod);
        buttonGroup1.add(jRBNomM);
        buttonGroup2.add(jRBCodD);
        buttonGroup2.add(jRBNumDoc);
        listarCustodia();
        buttonGroup3.add(bgMas);
        buttonGroup3.add(Dueño);
        Utilidad.validacionTabla(tblDuenos, modal, rootPaneCheckingEnabled, modal);
    }

    private void listarMascotas() throws SQLException {
        ResultSet rsMas = null;
        DefaultTableModel modeloM = new DefaultTableModel();

        // Definir las columnas de la tabla
        modeloM.addColumn("Id");
        modeloM.addColumn("Nombre");
        modeloM.addColumn("Fecha Nacimiento");
        modeloM.addColumn("Sexo");
        modeloM.addColumn("Raza");
        tblMascotas.setModel(modeloM);

        try {
            rsMas = objMas.listarMascotas();  // Obtener el ResultSet de mascotas
            while (rsMas.next()) {
                String sexoTexto = rsMas.getBoolean("sexo") ? "Macho" : "Hembra";

                // Convertir las fechas de Date a String en formato adecuado
                String fechaNacimiento = rsMas.getDate("fecha_nacimiento") != null
                        ? rsMas.getDate("fecha_nacimiento").toString() : "Sin fecha";

                modeloM.addRow(new Object[]{
                    rsMas.getInt("id"),
                    rsMas.getString("nombre"),
                    fechaNacimiento,
                    sexoTexto,
                    rsMas.getString("raza_nombre"),});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al listar Mascotas: " + e.getMessage());
        } finally {
            if (rsMas != null) {
                rsMas.close();
            }
        }
    }

    private void eliminarAsignacion() {
        int fila = tblCustodia.getSelectedRow();
        int masId = Integer.parseInt(txtMas.getText());
        int dueId = Integer.parseInt(txtDuenio.getText());
        try {
            objCus.eliminarCustodiaMAscota(dueId, masId);
        } catch (Exception e) {

        }
    }

    private void listarDuenios() {
        ResultSet rsDue = null;
        String vigencia = "";

        DefaultTableModel modeloD = new DefaultTableModel();
        modeloD.addColumn("Código");
        modeloD.addColumn("Num. Doc.");
        modeloD.addColumn("Nombres");
        modeloD.addColumn("Ap. Paterno");
        modeloD.addColumn("Ap. Materno");
        modeloD.addColumn("Teléfono");

        tblDuenos.setModel(modeloD);
        try {
            rsDue = objCus.listarDueniosV();
            while (rsDue.next()) {

                modeloD.addRow(new Object[]{
                    rsDue.getString("id"),
                    rsDue.getString("doc_identidad"),
                    rsDue.getString("nombres"),
                    rsDue.getString("apepaterno"),
                    rsDue.getString("apematerno"),
                    rsDue.getString("telefono"),});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al listar Dueños!");
        }

    }

    private void limpiarControles() throws SQLException {

        // Limpiar los campos de texto
        txtDuenio.setText("");
        txtMas.setText("");
        dtcFechaNacimiento.setDate(null);
        txtCus.setText("");
        // Desmarcar botones de radio
        buttonGroup2.clearSelection();
        buttonGroup3.clearSelection();
        buttonGroup1.clearSelection();
        // Restablecer selección en tablas
        tblMascotas.clearSelection();
        tblDuenos.clearSelection();

        JOptionPane.showMessageDialog(this, "Los controles han sido limpiados.");

    }

    private void listarCustodia() throws SQLException {
        ResultSet rsMas = null;
        DefaultTableModel modeloM = new DefaultTableModel();

        // Agregar columnas: las primeras dos son invisibles para los IDs
        modeloM.addColumn("Código Mascota");
        modeloM.addColumn("Nombre Mascota");
        modeloM.addColumn("Código Dueño");
        modeloM.addColumn("Nombre Dueño");
        modeloM.addColumn("Fecha Adopción");
        tblCustodia.setModel(modeloM);

        try {
            rsMas = objCus.listarCustodia();  // Obtener el ResultSet
            while (rsMas.next()) {
                String ado = rsMas.getDate("fa") != null ? rsMas.getDate("fa").toString() : null;

                modeloM.addRow(new Object[]{
                    rsMas.getInt("id_mas"),
                    rsMas.getString("nom_mas"),
                    rsMas.getInt("id_due"),
                    rsMas.getString("due_mas"),
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

    private boolean camposEstanLlenos() {
        // Verificar si los campos de texto están llenos
        if (txtMas.getText().isEmpty() || txtDuenio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de mascota y dueño deben estar llenos para seguir el registro.");
            return false; // Retorna false si falta algún campo importante
        }

        return true; // Retorna true solo si todos los campos están llenos
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jRBCod = new javax.swing.JRadioButton();
        txtMas = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMascotas = new javax.swing.JTable();
        jRBNomM = new javax.swing.JRadioButton();
        btnBuscar1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jRBNumDoc = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        txtDuenio = new javax.swing.JTextField();
        jRBCodD = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDuenos = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        dtcFechaNacimiento = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        btnRegistrar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnAgregarMas = new javax.swing.JButton();
        btnRe = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCustodia = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        txtCus = new javax.swing.JTextField();
        btnCustodia = new javax.swing.JButton();
        Dueño = new javax.swing.JRadioButton();
        bgMas = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel2.setText("Buscar mascota:");

        jRBCod.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jRBCod.setText("Código");
        jRBCod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jRBCodKeyTyped(evt);
            }
        });

        txtMas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMasKeyTyped(evt);
            }
        });

        tblMascotas.setModel(new javax.swing.table.DefaultTableModel(
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
        tblMascotas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMascotasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMascotas);

        jRBNomM.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jRBNomM.setText("Nombre");
        jRBNomM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBNomMActionPerformed(evt);
            }
        });

        btnBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/buscar-pequeño.png"))); // NOI18N
        btnBuscar1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnBuscar1.setBorderPainted(false);
        btnBuscar1.setContentAreaFilled(false);
        btnBuscar1.setName(""); // NOI18N
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jRBCod)
                                .addGap(30, 30, 30)
                                .addComponent(jRBNomM))
                            .addComponent(jLabel2)
                            .addComponent(txtMas, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRBCod)
                    .addComponent(jRBNomM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));

        jRBNumDoc.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jRBNumDoc.setText("Número Doc.");

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel1.setText("Buscar dueño:");

        txtDuenio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDuenioKeyTyped(evt);
            }
        });

        jRBCodD.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jRBCodD.setText("Código");

        tblDuenos.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDuenos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDuenosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDuenos);

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/buscar-pequeño.png"))); // NOI18N
        btnBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnBuscar.setBorderPainted(false);
        btnBuscar.setContentAreaFilled(false);
        btnBuscar.setName(""); // NOI18N
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jRBCodD)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRBNumDoc))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtDuenio, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRBNumDoc)
                    .addComponent(jRBCodD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDuenio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel3.setText("Fecha Adopción:");

        dtcFechaNacimiento.setDateFormatString("yyyy-MM-dd");
        dtcFechaNacimiento.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        dtcFechaNacimiento.setOpaque(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dtcFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addGap(72, 72, 72))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dtcFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(204, 255, 204));

        btnRegistrar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/veterinario.png"))); // NOI18N
        btnRegistrar.setText("Agregar Dueño");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
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

        btnLimpiar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/escoba-mascota.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
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

        btnRe.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnRe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/flecha derecha.png"))); // NOI18N
        btnRe.setText("Asignar Custodia");
        btnRe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRe, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregarMas)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrar)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar)
                    .addComponent(btnAgregarMas, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRe, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(201, 201, 201)
                .addComponent(jLabel9)
                .addContainerGap(253, Short.MAX_VALUE))
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
        jLabel4.setText("TABLA DE CUSTODIAS:");

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

        jPanel6.setBackground(new java.awt.Color(255, 255, 204));

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

        Dueño.setText("Dueño");

        bgMas.setText("Mascota");

        jLabel5.setText("Buscar por código:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Dueño)
                            .addComponent(bgMas)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(27, 27, 27))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(txtCus, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addComponent(btnCustodia)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCustodia)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(11, 11, 11)
                        .addComponent(bgMas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Dueño)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRBNomMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBNomMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBNomMActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        try {
            jdMantDuenio objForm = new jdMantDuenio(null, true);
            objForm.setLocationRelativeTo(this);
            objForm.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(jdCustodia.class.getName()).log(Level.SEVERE, null, ex);
        }
        dispose();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        Object[] opciones = {"Sí", "No"};
        int respuesta = JOptionPane.showOptionDialog(null,
                "¿Estás seguro que deseas eliminar la vacunación asignada? Recuerda que puedes asignar nuevamente.",
                "Confirmar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);

        if (respuesta == JOptionPane.YES_OPTION) {
            if (tblCustodia.getSelectedRow() != -1) {
                try {
                    eliminarAsignacion();
                    listarCustodia();
                    limpiarControles();
                    listarMascotas();
                    listarDuenios();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error al realizar la operación: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una fila de la 3ra tabla para hacer esta operación", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        try {
            // TODO add your handling code here:
            limpiarControles();
            listarMascotas();
            listarDuenios();
            listarCustodia();
        } catch (SQLException ex) {
            Logger.getLogger(jdCustodia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnAgregarMasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarMasActionPerformed
        try {
            jdMantMascota objForm = new jdMantMascota(null, true);
            objForm.setLocationRelativeTo(this);
            objForm.setVisible(true);
        }catch (Exception ex) {

        }
        dispose();
    }//GEN-LAST:event_btnAgregarMasActionPerformed
    private void btnBuscarDUctionPerformed(java.awt.event.ActionEvent evt) {
        ResultSet rsDuenio = null;
        DefaultTableModel modeloD = new DefaultTableModel();

// Configurar columnas de la tabla
        modeloD.addColumn("Código");
        modeloD.addColumn("Num. Doc.");
        modeloD.addColumn("Nombres");
        modeloD.addColumn("Ap. Paterno");
        modeloD.addColumn("Ap. Materno");
        modeloD.addColumn("Teléfono");
        tblDuenos.setModel(modeloD);

        try {
            // Verificar si el campo de texto está vacío
            if (txtDuenio.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe llenar el campo vacío para realizar la búsqueda");
                listarDuenios();
                txtDuenio.setText("");
                return;  // Terminar ejecución si el campo está vacío
            }

            // Realizar la búsqueda dependiendo de la opción seleccionada
            if (jRBCodD.isSelected()) {
                try {
                    int codigo = Integer.parseInt(txtDuenio.getText());
                    rsDuenio = objCus.filtrarDuenioV(codigo);

                    // Procesar resultados
                    while (rsDuenio != null && rsDuenio.next()) {
                        modeloD.addRow(new Object[]{
                            rsDuenio.getString("id"),
                            rsDuenio.getString("doc_identidad"),
                            rsDuenio.getString("nombres"),
                            rsDuenio.getString("apepaterno"),
                            rsDuenio.getString("apematerno"),
                            rsDuenio.getString("telefono")
                        });
                    }

                    if (modeloD.getRowCount() == 0) {
                        JOptionPane.showMessageDialog(this, "Código de Dueño no existe");
                        listarDuenios();
                        txtDuenio.setText("");
                    }

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "El código debe ser un número válido");
                    listarDuenios();
                    txtDuenio.setText("");
                    return;  // Terminar si el código no es numérico
                }

            } else if (jRBNumDoc.isSelected()) {
                rsDuenio = objCus.filtrarDuenioNV(txtDuenio.getText());

                // Procesar resultados
                while (rsDuenio != null && rsDuenio.next()) {
                    modeloD.addRow(new Object[]{
                        rsDuenio.getString("id"),
                        rsDuenio.getString("doc_identidad"),
                        rsDuenio.getString("nombres"),
                        rsDuenio.getString("apepaterno"),
                        rsDuenio.getString("apematerno"),
                        rsDuenio.getString("telefono")
                    });
                }

                if (modeloD.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(this, "No se encontraron resultados para el número de documento ingresado");
                    listarDuenios();
                    txtDuenio.setText("");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error del sistema en la búsqueda: " + e.getMessage());
        } finally {
            // Liberar el ResultSet al finalizar
            if (rsDuenio != null) {
                try {
                    rsDuenio.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Error al cerrar el ResultSet: " + e.getMessage());
                }
            }
        }
    }
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        ResultSet rsDuenio = null;
        DefaultTableModel modeloD = new DefaultTableModel();

// Configurar columnas de la tabla
        modeloD.addColumn("Código");
        modeloD.addColumn("Num. Doc.");
        modeloD.addColumn("Nombres");
        modeloD.addColumn("Ap. Paterno");
        modeloD.addColumn("Ap. Materno");
        modeloD.addColumn("Teléfono");
        tblDuenos.setModel(modeloD);

        try {
            // Verificar si se ha seleccionado al menos una opción
            if (!jRBCodD.isSelected() && !jRBNumDoc.isSelected()) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un botón para realizar la búsqueda");
                return;  // Terminar la ejecución si no hay selección
            }

            // Verificar si el campo de texto está vacío
            if (txtDuenio.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe llenar el campo vacío para realizar la búsqueda");
                listarDuenios();
                txtDuenio.setText("");
                return;  // Terminar ejecución si el campo está vacío
            }

            // Realizar la búsqueda dependiendo de la opción seleccionada
            if (jRBCodD.isSelected()) {
                try {
                    int codigo = Integer.parseInt(txtDuenio.getText());
                    rsDuenio = objCus.filtrarDuenioV(codigo);

                    // Procesar resultados
                    while (rsDuenio != null && rsDuenio.next()) {
                        modeloD.addRow(new Object[]{
                            rsDuenio.getString("id"),
                            rsDuenio.getString("doc_identidad"),
                            rsDuenio.getString("nombres"),
                            rsDuenio.getString("apepaterno"),
                            rsDuenio.getString("apematerno"),
                            rsDuenio.getString("telefono")
                        });
                    }

                    if (modeloD.getRowCount() == 0) {
                        JOptionPane.showMessageDialog(this, "Código de Dueño no existe o no está vigente");
                        listarDuenios();
                        txtDuenio.setText("");
                    }

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "El código debe ser un número válido");
                    listarDuenios();
                    txtDuenio.setText("");
                    return;  // Terminar si el código no es numérico
                }

            } else if (jRBNumDoc.isSelected()) {
                rsDuenio = objCus.filtrarDuenioNV(txtDuenio.getText());

                // Procesar resultados
                while (rsDuenio != null && rsDuenio.next()) {
                    modeloD.addRow(new Object[]{
                        rsDuenio.getString("id"),
                        rsDuenio.getString("doc_identidad"),
                        rsDuenio.getString("nombres"),
                        rsDuenio.getString("apepaterno"),
                        rsDuenio.getString("apematerno"),
                        rsDuenio.getString("telefono")
                    });
                }

                if (modeloD.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(this, "No se encontraron resultados para el número de documento ingresado");
                    listarDuenios();
                    txtDuenio.setText("");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error del sistema en la búsqueda: " + e.getMessage());
        } finally {
            // Liberar el ResultSet al finalizar
            if (rsDuenio != null) {
                try {
                    rsDuenio.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Error al cerrar el ResultSet: " + e.getMessage());
                }
            }
        }

    }//GEN-LAST:event_btnBuscarActionPerformed
    private void btnBuscaMActionPerformed(java.awt.event.ActionEvent evt) {
        ResultSet rsDuenio = null;
        DefaultTableModel modeloM = new DefaultTableModel();

// Configurar las columnas de la tabla
        modeloM.addColumn("Código");
        modeloM.addColumn("Nombre");
        modeloM.addColumn("Fecha Nacimiento");
        modeloM.addColumn("Sexo");
        modeloM.addColumn("Raza");
        tblMascotas.setModel(modeloM);

        try {
            txtMas.setEnabled(true);

            // Verificar si el campo de texto está vacío
            if (txtMas.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe llenar el campo vacío para realizar la búsqueda");
                listarMascotas();
                txtMas.setText("");
                return;  // Terminar la ejecución si el campo está vacío
            }
            if (jRBCod.isSelected()) {
                try {
                    int codigo = Integer.parseInt(txtMas.getText());
                    rsDuenio = objCus.filtrarMascota(codigo);

                    // Procesar resultados
                    while (rsDuenio.next()) {
                        String sexoTexto = rsDuenio.getBoolean("sexo") ? "Macho" : "Hembra";
                        String fechaNacimiento = rsDuenio.getDate("fecha_nacimiento") != null
                                ? rsDuenio.getDate("fecha_nacimiento").toString() : "Sin fecha";

                        modeloM.addRow(new Object[]{
                            rsDuenio.getInt("id"),
                            rsDuenio.getString("nombre"),
                            fechaNacimiento,
                            sexoTexto,
                            rsDuenio.getString("raza_nombre")
                        });
                    }

                    if (modeloM.getRowCount() == 0) {
                        JOptionPane.showMessageDialog(this, "Código de Mascota no existe");
                        listarMascotas();
                        txtMas.setText("");
                    }

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "El código debe ser un número válido");
                    listarMascotas();
                    txtMas.setText("");
                    return;  // Terminar si el código no es numérico
                }
            } else if (jRBNomM.isSelected()) {
                rsDuenio = objCus.filtrarMascota(txtMas.getText());

                // Procesar resultados
                while (rsDuenio.next()) {
                    String sexoTexto = rsDuenio.getBoolean("sexo") ? "Macho" : "Hembra";
                    String fechaNacimiento = rsDuenio.getDate("fecha_nacimiento") != null
                            ? rsDuenio.getDate("fecha_nacimiento").toString() : "Sin fecha";

                    modeloM.addRow(new Object[]{
                        rsDuenio.getInt("id"),
                        rsDuenio.getString("nombre"),
                        fechaNacimiento,
                        sexoTexto,
                        rsDuenio.getString("raza_nombre")
                    });
                }

                if (modeloM.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(this, "No se encontraron resultados para el nombre ingresado");
                    listarMascotas();
                    txtMas.setText("");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error del sistema en la búsqueda: " + e.getMessage());
        } finally {
            if (rsDuenio != null) {
                try {
                    rsDuenio.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Error al cerrar el ResultSet: " + e.getMessage());
                }
            }
        }
    }
    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        ResultSet rsDuenio = null;
        DefaultTableModel modeloM = new DefaultTableModel();

// Configurar las columnas de la tabla
        modeloM.addColumn("Código");
        modeloM.addColumn("Nombre");
        modeloM.addColumn("Fecha Nacimiento");
        modeloM.addColumn("Sexo");
        modeloM.addColumn("Raza");
        tblMascotas.setModel(modeloM);

        try {
            // Verificar si se ha seleccionado al menos una opción
            if (!jRBCod.isSelected() && !jRBNomM.isSelected()) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un botón para realizar la búsqueda");
                return;  // Terminar la ejecución si no hay selección
            }

            // Habilitar el campo de texto porque hay una opción seleccionada
            txtMas.setEnabled(true);

            // Verificar si el campo de texto está vacío
            if (txtMas.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe llenar el campo vacío para realizar la búsqueda");
                listarMascotas();  // Recargar la lista completa
                txtMas.setText("");
            }
            modeloM.setRowCount(0);

            if (jRBCod.isSelected()) {
                try {
                    int codigo = Integer.parseInt(txtMas.getText());
                    rsDuenio = objCus.filtrarMascota(codigo);
                    while (rsDuenio.next()) {
                        String sexoTexto = rsDuenio.getBoolean("sexo") ? "Macho" : "Hembra";
                        String fechaNacimiento = rsDuenio.getDate("fecha_nacimiento") != null
                                ? rsDuenio.getDate("fecha_nacimiento").toString() : "Sin fecha";

                        modeloM.addRow(new Object[]{
                            rsDuenio.getInt("ma_id"),
                            rsDuenio.getString("nom_mas"),
                            fechaNacimiento,
                            sexoTexto,
                            rsDuenio.getString("raza_nombre")
                        });
                    }

                    // Si no se encontraron resultados, mostrar mensaje
                    if (modeloM.getRowCount() == 0) {
                        JOptionPane.showMessageDialog(this, "Código de Mascota no existe");
                        listarMascotas();  // Recargar la lista completa
                        txtMas.setText("");
                    }

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "El código debe ser un número válido");
                    listarMascotas();  // Recargar la lista completa
                    txtMas.setText("");
                }

            } else if (jRBNomM.isSelected()) {
                rsDuenio = objCus.filtrarMascota(txtMas.getText());

                // Procesar resultados
                while (rsDuenio.next()) {
                    String sexoTexto = rsDuenio.getBoolean("sexo") ? "Macho" : "Hembra";
                    String fechaNacimiento = rsDuenio.getDate("fecha_nacimiento") != null
                            ? rsDuenio.getDate("fecha_nacimiento").toString() : "Sin fecha";

                    modeloM.addRow(new Object[]{
                        rsDuenio.getInt("ma_id"),
                        rsDuenio.getString("nom_mas"),
                        fechaNacimiento,
                        sexoTexto,
                        rsDuenio.getString("raza_nombre")
                    });
                }

                // Si no se encontraron resultados, mostrar mensaje
                if (modeloM.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(this, "No se encontraron resultados para el nombre ingresado");
                    listarMascotas();  // Recargar la lista completa
                    txtMas.setText("");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error del sistema en la búsqueda: " + e.getMessage());
        } finally {
            // Cerrar el ResultSet si no es nulo
            if (rsDuenio != null) {
                try {
                    rsDuenio.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Error al cerrar el ResultSet: " + e.getMessage());
                }
            }
        }

    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void tblDuenosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDuenosMouseClicked
        // TODO add your handling code here:
        jRBCodD.setSelected(true);
        txtDuenio.setText(String.valueOf(tblDuenos.getValueAt(tblDuenos.getSelectedRow(), 0)));
        btnBuscarDUctionPerformed(null);
        listarDuenios();
    }//GEN-LAST:event_tblDuenosMouseClicked

    private void tblMascotasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMascotasMouseClicked
        // TODO add your handling code here:
        jRBCod.setSelected(true);
        txtMas.setText(String.valueOf(tblMascotas.getValueAt(tblMascotas.getSelectedRow(), 0)));
        btnBuscaMActionPerformed(null);
        try {
            listarMascotas();
        } catch (SQLException ex) {
            Logger.getLogger(jdCustodia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblMascotasMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        try {
            listarMascotas();
            listarDuenios();
            listarCustodia();
        } catch (SQLException ex) {
            Logger.getLogger(jdCustodia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowOpened

    private void jRBCodKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jRBCodKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBCodKeyTyped

    private void txtMasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMasKeyTyped
        // TODO add your handling code here:
        if (jRBCod.isSelected()) {
            // Si la opción de código está seleccionada, permitir solo números
            char c = evt.getKeyChar();
            if (!Character.isDigit(c)) {
                evt.consume(); // Ignora la entrada si no es un dígito
                JOptionPane.showMessageDialog(txtDuenio, "Solo se permiten números en esta opción");
            }
        } else if (jRBNomM.isSelected()) {
            // Si la opción de nombre está seleccionada, permitir solo letras
            char c = evt.getKeyChar();
            if (!Character.isLetter(c) && c != ' ') {
                evt.consume(); // Ignora la entrada si no es una letra o espacio
                JOptionPane.showMessageDialog(txtDuenio, "Solo se permiten letras en esta opción");
            }
        }
    }//GEN-LAST:event_txtMasKeyTyped

    private void txtDuenioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDuenioKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume(); // Ignora la entrada si no es un dígito
            JOptionPane.showMessageDialog(txtDuenio, "Solo se permiten números en esta opción");
        }
    }//GEN-LAST:event_txtDuenioKeyTyped

    private void btnReActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReActionPerformed

        try {
            if (btnRe.getText().equals("Asignar Vacunación")) {
                btnRe.setText("Guardar Datos");
                JOptionPane.showMessageDialog(this, "TE RECUERDO QUE DEBES GUARDAR LA ASIGNACIÓN DE CUSTODIA POR CÓDIGO EN AMBOS CASOS");
                btnEliminar.setEnabled(false);
                btnLimpiar.setEnabled(false);
                limpiarControles();
                // Suponiendo que tienes un JButton llamado btnMiBoton
jRBCod.requestFocusInWindow();

jRBCodD.requestFocusInWindow();
            } else {
                int masId = Integer.parseInt(txtMas.getText());
                int dueId = Integer.parseInt(txtDuenio.getText());
                Date fechaNacimiento = dtcFechaNacimiento.getDate();

                if (camposEstanLlenos()) {
                    btnRe.setText("Asignar Vacunación");

                    try {
                        // Verifica la existencia antes de asignar
                        if (objCus.existeCustodia(dueId, masId)) {
                            JOptionPane.showMessageDialog(this, "Error: Ya existe registro para este dueño - mascota");
                            return ;
                        } else {

                            objCus.registrarCustodia(masId, dueId, fechaNacimiento);
                            JOptionPane.showMessageDialog(this, "Custodia asignada con éxito");

                            // Cambiar el texto del botón y actualizar la vista
                            btnRe.setText("Asignar Vacunación");
                            btnBuscar.setEnabled(true);
                            btnEliminar.setEnabled(true);
                            btnLimpiar.setEnabled(true);
                            listarCustodia();
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(jsDetalleVacunacion.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(this, "Error al realizar la acción: " + ex.getMessage());
                    }
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Debes guardar por código tanto mascota como vacuna.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al realizar la acción: " + e.getMessage());
        }

    }//GEN-LAST:event_btnReActionPerformed

    private void btnCustodiaLActionPerformed(java.awt.event.ActionEvent evt) {
        ResultSet rsDuenio = null;
        DefaultTableModel modeloD = new DefaultTableModel();
        modeloD.addColumn("Nombre Mascota");
        modeloD.addColumn("Nombre Dueño");
        modeloD.addColumn("Fecha Adopción");
        tblCustodia.setModel(modeloD);

        try {
            if (bgMas.isSelected()) {
                try {
                    rsDuenio = objCus.filtrarCustodia(Integer.parseInt(txtCus.getText()));
                    while (rsDuenio.next()) {
                        String ado = rsDuenio.getDate("fa") != null
                                ? rsDuenio.getDate("fa").toString() : null;

                        modeloD.addRow(new Object[]{
                            rsDuenio.getString("nom_mas"),
                            rsDuenio.getString("due_mas"),
                            ado,});
                    }

                    if (modeloD.getRowCount() == 0) {
                        JOptionPane.showMessageDialog(this, "Nombre de mascota no existe");
                        listarCustodia();
                        txtDuenio.setText("");
                    }

                } catch (NumberFormatException e) {
                }
            } else if (Dueño.isSelected()) {
                rsDuenio = objCus.filtrarCustodiaD(Integer.parseInt(txtCus.getText()));
                while (rsDuenio.next()) {
                    String ado = rsDuenio.getDate("fa") != null
                            ? rsDuenio.getDate("fa").toString() : "Sin fecha";

                    modeloD.addRow(new Object[]{
                        rsDuenio.getString("nom_mas"),
                        rsDuenio.getString("due_mas"),
                        ado,});
                }
                if (modeloD.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(this, "No se encontraron resultados para el nombre ingresado");
                    listarCustodia();
                    txtDuenio.setText("");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error del sistema en la búsqueda: " + e.getMessage());
        } finally {
            // Liberar el ResultSet al finalizar
            if (rsDuenio != null) {
                try {
                    rsDuenio.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Error al cerrar el ResultSet: " + e.getMessage());
                }
            }
        }
    }
    private void btnCustodiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustodiaActionPerformed
        // TODO add your handling code here:
        ResultSet rsDuenio = null;
        DefaultTableModel modeloD = new DefaultTableModel();

        modeloD.addColumn("Código Mascota");
        modeloD.addColumn("Nombre Mascota");
        modeloD.addColumn("Dueño Mascota");
        modeloD.addColumn("Nombre Dueño");
        modeloD.addColumn("Fecha Adopción");
        tblCustodia.setModel(modeloD);

        try {
            // Verificar selección de opción y campo de texto vacío
            if (!bgMas.isSelected() && !Dueño.isSelected()) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un botón para realizar la búsqueda");
                listarCustodia();
                txtCus.setText("");
                return;
            }
            if (txtCus.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe llenar el campo vacío para realizar la búsqueda");
                listarCustodia();
                txtCus.setText("");
                return;
            }

            if (bgMas.isSelected()) {
                rsDuenio = objCus.filtrarCustodia(Integer.parseInt(txtCus.getText()));

                // Si existen resultados, rellenar campos
                if (rsDuenio != null && rsDuenio.next()) {
                    txtDuenio.setText(String.valueOf(rsDuenio.getInt("id_d")));
                    txtMas.setText(String.valueOf(rsDuenio.getInt("id_m")));

                    dtcFechaNacimiento.setDate(rsDuenio.getDate("fa"));
                    txtCus.setText(rsDuenio.getString("id_m"));

                    // Añadir filas a la tabla
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
                    JOptionPane.showMessageDialog(this, "Nombre de mascota no existe");
                    listarCustodia();
                    txtDuenio.setText("");
                }

            } else if (Dueño.isSelected()) {
                rsDuenio = objCus.filtrarCustodiaD(Integer.parseInt(txtCus.getText()));

                if (rsDuenio != null && rsDuenio.next()) {
                    txtDuenio.setText(String.valueOf(rsDuenio.getInt("id_d")));
                    txtMas.setText(String.valueOf(rsDuenio.getInt("id_m")));

                    dtcFechaNacimiento.setDate(rsDuenio.getDate("fa"));
                    txtCus.setText(rsDuenio.getString("id_d"));

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
                    JOptionPane.showMessageDialog(this, "No se encontraron resultados para el nombre ingresado");
                    listarCustodia();
                    txtDuenio.setText("");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error en la búsqueda: " + e.getMessage());
        } finally {
            if (rsDuenio != null) {
                try {
                    rsDuenio.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Error al cerrar el ResultSet: " + e.getMessage());
                }
            }
        }

    }//GEN-LAST:event_btnCustodiaActionPerformed

    private void txtCusKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCusKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume(); // Ignora la entrada si no es un dígito
            JOptionPane.showMessageDialog(txtDuenio, "Solo se permiten números en esta opción");
        }

    }//GEN-LAST:event_txtCusKeyTyped

    private void btnCustodiaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCustodiaKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_btnCustodiaKeyTyped

    private void tblCustodiaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblCustodiaKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_tblCustodiaKeyTyped

    private void tblCustodiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCustodiaMouseClicked
        // TODO add your handling code here:
        txtMas.setText(String.valueOf(tblCustodia.getValueAt(tblCustodia.getSelectedRow(), 0)));
        txtDuenio.setText(String.valueOf(tblCustodia.getValueAt(tblCustodia.getSelectedRow(), 2)));
        jRBCod.setSelected(true);
        jRBCodD.setSelected(true);
        String fechaAdopcionStr = String.valueOf(tblCustodia.getValueAt(tblCustodia.getSelectedRow(), 4));

        try {
            if (fechaAdopcionStr != null && !fechaAdopcionStr.equals("null")) {
                // Define el formato de fecha
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaAdopcion = dateFormat.parse(fechaAdopcionStr);

                // Asigna la fecha al campo dtcFechaNacimiento
                dtcFechaNacimiento.setDate(fechaAdopcion);
            } else {
                // Limpia el campo si la fecha es nula
                dtcFechaNacimiento.setDate(null);
            }
        } catch (ParseException e) {
            Logger.getLogger(jdCustodia.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(this, "Error al convertir la fecha: " + e.getMessage());
        }

// Ejecuta el método btnCustodiaLActionPerformed
        btnCustodiaLActionPerformed(null);

// Actualiza la lista de custodia
        try {
            listarCustodia();
        } catch (SQLException ex) {
            Logger.getLogger(jdCustodia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblCustodiaMouseClicked

    private void txtCusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCusActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton Dueño;
    private javax.swing.JRadioButton bgMas;
    private javax.swing.JButton btnAgregarMas;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnCustodia;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRe;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private com.toedter.calendar.JDateChooser dtcFechaNacimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRBCod;
    private javax.swing.JRadioButton jRBCodD;
    private javax.swing.JRadioButton jRBNomM;
    private javax.swing.JRadioButton jRBNumDoc;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tblCustodia;
    private javax.swing.JTable tblDuenos;
    private javax.swing.JTable tblMascotas;
    private javax.swing.JTextField txtCus;
    private javax.swing.JTextField txtDuenio;
    private javax.swing.JTextField txtMas;
    // End of variables declaration//GEN-END:variables
}

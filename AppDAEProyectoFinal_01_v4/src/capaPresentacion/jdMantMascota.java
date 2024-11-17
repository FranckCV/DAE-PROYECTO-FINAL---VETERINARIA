package capaPresentacion;

import capaNegocio.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import soporte.Utilidad;

public class jdMantMascota extends javax.swing.JDialog {

    clsMascota objMasco = new clsMascota();
    clsRaza objRaza = new clsRaza();

    public jdMantMascota(java.awt.Frame parent, boolean modal) throws Exception {
        super(parent, modal);
        initComponents();
        sinEditarP();
        txtId.requestFocus();
        listarMascotas();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jToggleButton1 = new javax.swing.JToggleButton();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMascota = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNotaAdicional = new javax.swing.JTextArea();
        cbxRaza = new javax.swing.JComboBox<>();
        dtcFechaNacimiento = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        cbxSexo = new javax.swing.JComboBox<>();
        txtNombre = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtAltura = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        chkCastrado = new javax.swing.JCheckBox();
        chkDesparasitado = new javax.swing.JCheckBox();
        cmbEstadoSalud = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtPeso = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        chkEstado = new javax.swing.JCheckBox();
        btnRaza = new javax.swing.JButton();
        btnBuscar1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtId = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btnRegistrar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnAgregarD = new javax.swing.JButton();

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 0, 102));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Mascota");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(523, 523, 523))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(204, 255, 255));

        tblMascota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMascotaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMascota);

        jPanel4.setBackground(new java.awt.Color(138, 238, 238));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel6.setText("Nombre:");

        jButton5.setBorder(null);
        jButton5.setContentAreaFilled(false);
        jButton5.setVerifyInputWhenFocusTarget(false);

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel9.setText("Altura:");

        jLabel11.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel11.setText("Peso:");

        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel13.setText("Nota adicional:");

        jScrollPane2.setAutoscrolls(true);

        txtNotaAdicional.setColumns(20);
        txtNotaAdicional.setRows(5);
        txtNotaAdicional.setAutoscrolls(false);
        txtNotaAdicional.setBorder(null);
        jScrollPane2.setViewportView(txtNotaAdicional);

        cbxRaza.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        dtcFechaNacimiento.setDateFormatString("yyyy-MM-dd");
        dtcFechaNacimiento.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        dtcFechaNacimiento.setOpaque(false);

        jLabel15.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel15.setText("Sexo:");

        cbxSexo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cbxSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Macho", "Hembra" }));

        txtNombre.setBorder(null);
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel16.setText("cm");

        txtAltura.setBorder(null);
        txtAltura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAlturaKeyTyped(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel17.setText("kg");

        chkCastrado.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        chkCastrado.setText("Esterilizado");
        chkCastrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCastradoActionPerformed(evt);
            }
        });

        chkDesparasitado.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        chkDesparasitado.setText("Desparasitado");

        cmbEstadoSalud.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbEstadoSalud.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Saludable", "Terminal", "Crónica" }));
        cmbEstadoSalud.setSelectedIndex(-1);
        cmbEstadoSalud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEstadoSaludActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel18.setText("Condición:");

        jLabel19.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel19.setText("Raza:");

        txtPeso.setBorder(null);
        txtPeso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPesoKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel8.setText("Fecha Nacimiento:");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel3.setText("Vigencia:");

        chkEstado.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        chkEstado.setText("(Vigente)");

        btnRaza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/patas.png"))); // NOI18N
        btnRaza.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnRaza.setBorderPainted(false);
        btnRaza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRazaActionPerformed(evt);
            }
        });

        btnBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/buscar.png"))); // NOI18N
        btnBuscar1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnBuscar1.setBorderPainted(false);
        btnBuscar1.setContentAreaFilled(false);
        btnBuscar1.setName(""); // NOI18N
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                .addComponent(jLabel11)
                                                .addGap(10, 10, 10))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(116, 116, 116)
                                                .addComponent(jLabel17))
                                            .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel16))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chkEstado)))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxRaza, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cbxSexo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dtcFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbEstadoSalud, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(chkCastrado)
                        .addGap(35, 35, 35)
                        .addComponent(chkDesparasitado))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5)
                        .addGap(16, 16, 16))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRaza)
                        .addContainerGap(27, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel6)
                                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3)))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel16)
                            .addComponent(cmbEstadoSalud, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton5)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(dtcFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(cbxRaza, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chkEstado)
                        .addComponent(jLabel3))
                    .addComponent(btnRaza))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkDesparasitado, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkCastrado))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/refugio-de-animales.png"))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(138, 238, 238));

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

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/buscar.png"))); // NOI18N
        btnBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnBuscar.setBorderPainted(false);
        btnBuscar.setContentAreaFilled(false);
        btnBuscar.setName(""); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel4.setText("id: ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(138, 238, 238));

        btnRegistrar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/veterinario.png"))); // NOI18N
        btnRegistrar.setText("Registrar Mascota");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
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

        btnLimpiar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/escoba-mascota.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnAgregarD.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnAgregarD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/cuidado-de-mascotas.png"))); // NOI18N
        btnAgregarD.setText("Registrar Dueño");
        btnAgregarD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarD, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLimpiar)
                .addGap(18, 18, 18)
                .addComponent(btnAgregarD, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 325, Short.MAX_VALUE))
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbEstadoSaludActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEstadoSaludActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbEstadoSaludActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // Volver a listar las especies
        listarNombreRaza();
        try {
            // Volver a listar las razas
            listarMascotas();
            listarNombreRaza();
            
        } catch (SQLException ex) {
            Logger.getLogger(jdMantMascota.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowOpened


    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
        try {
            if (btnRegistrar.getText().equals("Registrar Mascota")) {
                btnRegistrar.setText("Guardar Datos");
                limpiarCampos();  // Limpiar los campos antes de un nuevo registro
                txtId.setText(objMasco.generarCodigoMascota().toString());  // Generar nuevo código para la mascota
                txtId.requestFocus();  // Colocar el foco en el campo del ID
                // Deshabilitar los botones para evitar acciones adicionales durante la edición
                btnBuscar.setEnabled(false);
                btnModificar.setEnabled(false);
                btnLimpiar.setEnabled(false);
                btnAgregarD.setEnabled(false);
                btnRaza.setEnabled(false);
                txtId.setEditable(false);

                Editar();

            } else {
                if (camposEstanLlenos()) {  // Verificar si todos los campos están llenos
                    btnRegistrar.setText("Registrar Mascota");
                    Editar();
                    txtId.setEditable(false);// Puede que esto deba ser ajustado dependiendo de su propósito

                    // Registrar la mascota usando los valores de los campos del formulario
                    objMasco.registrarMascota(
                            Integer.parseInt(txtId.getText()),
                            txtNombre.getText(),
                            dtcFechaNacimiento.getDate(),
                            Double.parseDouble(txtAltura.getText()),
                            Double.parseDouble(txtPeso.getText()),
                            txtNotaAdicional.getText(),
                            cbxSexo.getSelectedItem().toString().equals("Macho"),
                            chkCastrado.isSelected(),
                            chkDesparasitado.isSelected(),
                            extraerEstado(cmbEstadoSalud.getSelectedItem().toString()),
                            chkEstado.isSelected(),
                            objRaza.obtenerIdRaza(cbxRaza.getSelectedItem().toString())
                    );
                    // Volver a listar las especies
                    listarNombreRaza();  // Volver a listar las razas
                    listarMascotas();  // Actualizar la lista de mascotas
                    limpiarCampos();
                    JOptionPane.showMessageDialog(this, "Mascota registrada con éxito");
                }
                btnBuscar.setEnabled(true);
                btnModificar.setEnabled(true);
                btnLimpiar.setEnabled(true);
                btnAgregarD.setEnabled(true);
                btnRaza.setEnabled(true);
                txtId.setEditable(true);// Puede que esto deba ser ajustado dependiendo de su propósito

            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Solo se aceptan números en altura y peso");
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos de fecha.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error interno al registrar la mascota ");
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void tblMascotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMascotaMouseClicked
        // TODO add your handling code here:
        txtId.setText(String.valueOf(tblMascota.getValueAt(tblMascota.getSelectedRow(), 0)));
        btnBuscarActionPerformed(null);
    }//GEN-LAST:event_tblMascotaMouseClicked
 private String getSexoString(boolean sexo) {
        return sexo ? "Macho" : "Hembra";
    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        try {
            if (txtId.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un código de mascota o seleccionarla para buscar");
                // Método que gestiona el estado del formulario
            } else {

                try {
                    
                    ResultSet rsBus = objMasco.buscarMascota(Integer.parseInt(txtId.getText()));
                    if (rsBus.next()) {
                        // Asignamos los valores obtenidos desde la base de datos a los campos del formulario
                        txtNombre.setText(rsBus.getString("nombre"));
                        txtId.setText(rsBus.getString("id"));
                        cbxSexo.setSelectedItem(getSexoString(rsBus.getBoolean("sexo")));
                        txtAltura.setText(String.valueOf(rsBus.getDouble("altura")));
                        txtPeso.setText(String.valueOf(rsBus.getDouble("peso")));
                        cbxRaza.setSelectedItem(rsBus.getString("raza_nombre"));
                        txtNotaAdicional.setText(rsBus.getString("notaAdicional"));
                        chkCastrado.setSelected(rsBus.getBoolean("esterilizado"));
                        chkDesparasitado.setSelected(rsBus.getBoolean("desparasitado"));
                        cmbEstadoSalud.setSelectedItem(mostrarEstado(rsBus.getString("estado_salud")));
                        chkEstado.setSelected(rsBus.getBoolean("vigencia"));
                        dtcFechaNacimiento.setDate(rsBus.getDate("fecha_nacimiento"));
                        sinEditarP();
                    } else {
                        JOptionPane.showMessageDialog(this, "Mascota no encontrada");
                        limpiarCampos();  // Método para limpiar el formulario
                        sinEditarP();
                        listarMascotas();
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "El código de mascota debe ser un número válido.");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado: " + e.getMessage());
        }

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        Object[] opciones = {"Sí", "No"};
        int respuesta = JOptionPane.showOptionDialog(null,
                "¿Estás seguro que deseas modificar la información?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);
        if (respuesta == 0) {
            try {
                if (txtId.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe seleccionar un elemento a modificar");
                } else {
                    if (btnModificar.getText().equals("Modificar")) {
                        btnModificar.setText("Guardar Datos");
                        btnBuscar.setEnabled(false);
                        txtId.setEditable(false);// Puede que esto deba ser ajustado dependiendo de su propósito
                        Editar();
                    } else {
                        if (camposEstanLlenos()) {
                            btnModificar.setText("Modificar");
                            int idMascota = Integer.parseInt(txtId.getText().trim());
                            double altura = Double.parseDouble(txtAltura.getText().trim());
                            double peso = Double.parseDouble(txtPeso.getText().trim());
                            objMasco.modificarMascota(
                                    idMascota,
                                    txtNombre.getText(),
                                    dtcFechaNacimiento.getDate(),
                                    altura,
                                    peso,
                                    txtNotaAdicional.getText(),
                                    cbxSexo.getSelectedItem().toString().equals("Macho"),
                                    chkCastrado.isSelected(),
                                    chkDesparasitado.isSelected(),
                                    extraerEstado(cmbEstadoSalud.getSelectedItem().toString()),
                                    objRaza.obtenerIdRaza(cbxRaza.getSelectedItem().toString()),
                                    chkEstado.isSelected());
                            JOptionPane.showMessageDialog(this, "Información de mascota modificada exitosamente.");
                            limpiarCampos();  // Limpiar los campos del formulario
                            listarMascotas();  // Actualizar la lista de mascotas
                            listarNombreRaza();

                            sinEditarP();
                            btnModificar.setText("Modificar");
                            btnBuscar.setEnabled(true);
                            txtId.setEditable(true);// Puede que esto deba ser ajustado dependiendo de su propósito

                        }
                    }
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error: Los campos de ID, altura o peso deben ser números válidos.");
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(this, "Error: Verifique que todos los campos obligatorios estén llenos, especialmente las fechas.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al modificar la mascota: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void chkCastradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCastradoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkCastradoActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        try {
            limpiarCampos();
            listarMascotas();
            listarNombreRaza();

        } catch (Exception ex) {
        }

    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnRazaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRazaActionPerformed
        // TODO add your handling code here:
        jdMntRaza segundDialogo = new jdMntRaza(null, true); // 'this' indica el diálogo padre, y 'true' indica que es modal
        segundDialogo.setVisible(true);

    }//GEN-LAST:event_btnRazaActionPerformed

    private void btnAgregarDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDActionPerformed
        // TODO add your handling code here:
        jdMantDuenio segundDialogo = new jdMantDuenio(null, true); // 'this' indica el diálogo padre, y 'true' indica que es modal
        segundDialogo.setVisible(true);

    }//GEN-LAST:event_btnAgregarDActionPerformed

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        try {
            // TODO add your handling code here:
            listarMascotasNombre();
        } catch (SQLException ex) {
            Logger.getLogger(jdMantMascota.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        // Verifica si el carácter no es una letra y tampoco un espacio
        if (!Character.isLetter(c) && c != ' ') {
            evt.consume(); // Consume el evento para evitar que se escriba el carácter no permitido
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        // Verifica si el carácter no es un dígito
        if (!Character.isDigit(c)) {
            evt.consume(); // Consume el evento para evitar que se escriba el carácter no permitido
        }
    }//GEN-LAST:event_txtIdKeyTyped

    private void txtPesoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        String text = ((javax.swing.JTextField) evt.getSource()).getText();

// Permite solo dígitos y un solo punto o coma
        if (!Character.isDigit(c) && c != '.' && c != ',') {
            evt.consume();
        } else if ((c == '.' || c == ',') && (text.contains(".") || text.contains(","))) {
            evt.consume(); // Evita múltiples puntos o comas
        }

    }//GEN-LAST:event_txtPesoKeyTyped

    private void txtAlturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlturaKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        String text = ((javax.swing.JTextField) evt.getSource()).getText();

// Permite solo dígitos y un solo punto o coma
        if (!Character.isDigit(c) && c != '.' && c != ',') {
            evt.consume();
        } else if ((c == '.' || c == ',') && (text.contains(".") || text.contains(","))) {
            evt.consume(); // Evita múltiples puntos o comas
        }

    }//GEN-LAST:event_txtAlturaKeyTyped
    private void listarMascotas() throws SQLException {
        ResultSet rsMas = null;
        DefaultTableModel modeloM = new DefaultTableModel();

        // Definir las columnas de la tabla
        modeloM.addColumn("Id");
        modeloM.addColumn("Nombre");
        modeloM.addColumn("Fecha Nacimiento");
        modeloM.addColumn("Altura");
        modeloM.addColumn("Peso");
        modeloM.addColumn("Sexo");
        modeloM.addColumn("Nota Adicional");
        modeloM.addColumn("Esterilizado");
        modeloM.addColumn("Desparasitado");
        modeloM.addColumn("Condición");
        modeloM.addColumn("Estado");
        modeloM.addColumn("Raza");
        tblMascota.setModel(modeloM);

        try {
            rsMas = objMasco.listarMascotas();  // Obtener el ResultSet de mascotas
            while (rsMas.next()) {
                String sexoTexto = rsMas.getBoolean("sexo") ? "Macho" : "Hembra";
                String despaTexto = rsMas.getBoolean("desparasitado") ? "SI" : "NO";
                String castTexto = rsMas.getBoolean("esterilizado") ? "SI" : "NO";
                String estado = rsMas.getBoolean("vigencia") ? Utilidad.VIGENCIA_SI : Utilidad.VIGENCIA_NO;

                // Convertir las fechas de Date a String en formato adecuado
                String fechaNacimiento = rsMas.getDate("fecha_nacimiento") != null
                        ? rsMas.getDate("fecha_nacimiento").toString() : "Sin fecha";

                modeloM.addRow(new Object[]{
                    rsMas.getInt("id"),
                    rsMas.getString("nombre"),
                    fechaNacimiento,
                    rsMas.getString("altura"),
                    rsMas.getString("peso"),
                    sexoTexto,
                    rsMas.getString("notaAdicional"),
                    castTexto,
                    despaTexto,
                    mostrarEstado(rsMas.getString("estado_salud")), // Asegurarse de que mostrarEstado maneje valores nulos
                    estado,
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

  

    private void listarNombreRaza() {
        ResultSet rss = null;
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        cbxRaza.setModel(modelo);
        try {

            rss = objRaza.listarRazas();
            while (rss.next()) {
                modelo.addElement(rss.getString("nombre"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al listar las Razas--->" + e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean camposEstanLlenos() {

        if (txtId.getText().isEmpty() || txtNombre.getText().isEmpty() || txtPeso.getText().isEmpty() || txtAltura.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben estar completos.");
            return false;
        }
        if (dtcFechaNacimiento.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar las fechas de nacimiento");
            return false;
        }

        return true;

    }

    private String extraerEstado(String est) {
        if (est.equals("Terminal")) {
            return "T";
        } else if (est.equals("Crónica")) {
            return "C";
        } else if (est.equals("Saludable")) {
            return "S";
        }
        return null;
    }

    private String mostrarEstado(String est) {
        if (est.equals("T")) {
            return "Terminal";
        } else if (est.equals("C")) {
            return "Crónica";
        } else if (est.equals("S")) {
            return "Saludable";
        }
        return null;
    }

// Método para limpiar los campos de la interfaz
    private void limpiarCampos() throws SQLException, Exception {
        // Limpiar campos de texto
        txtId.setText("");
        txtNombre.setText("");
        txtAltura.setText("");
        txtPeso.setText("");
        txtNotaAdicional.setText("");
        dtcFechaNacimiento.setDate(null);
        //cbxSexo.setSelectedIndex(0);  // Volver a la primera opción (0)
        cbxSexo.setEditable(false);     // Habilitar el ComboBox de sexo para futura edición
        //cbxRaza.setSelectedIndex(0);  // Volver a la primera opción de la raza
        cbxRaza.setEditable(false);     // Habilitar el ComboBox de raza para futura edición        cmbEstadoSalud.setSelectedIndex(-1);  // Restablecer el ComboBox de estado de salud
        cmbEstadoSalud.setEditable(false);
        chkCastrado.setEnabled(false);
        chkEstado.setSelected(false);
        chkDesparasitado.setSelected(false);
        chkCastrado.setSelected(false);
        chkDesparasitado.setEnabled(false);
        listarNombreRaza();
        listarMascotas();      // Refrescar la lista de mascotas
        // Refrescar las especies en el ComboBox
    }

    private void listarMascotasNombre() throws SQLException {
        ResultSet rsMas = null;
        DefaultTableModel modeloM = new DefaultTableModel();

        // Definir las columnas de la tabla
        modeloM.addColumn("Id");
        modeloM.addColumn("Nombre");
        modeloM.addColumn("Fecha Nacimiento");
        modeloM.addColumn("Altura");
        modeloM.addColumn("Peso");
        modeloM.addColumn("Sexo");
        modeloM.addColumn("Nota Adicional");
        modeloM.addColumn("Esterilizado");
        modeloM.addColumn("Desparasitado");
        modeloM.addColumn("Condición");
        modeloM.addColumn("Estado");
        modeloM.addColumn("Raza");
        tblMascota.setModel(modeloM);

        try {
            rsMas = objMasco.filtrar(txtNombre.getText());  // Obtener el ResultSet de mascotas
            while (rsMas.next()) {
                String sexoTexto = rsMas.getBoolean("sexo") ? "Macho" : "Hembra";
                String despaTexto = rsMas.getBoolean("desparasitado") ? "SI" : "NO";
                String castTexto = rsMas.getBoolean("esterilizado") ? "SI" : "NO";
                String estado = rsMas.getBoolean("vigencia") ? "VIVO" : "MUERTO";

                // Convertir las fechas de Date a String en formato adecuado
                String fechaNacimiento = rsMas.getDate("fecha_nacimiento") != null
                        ? rsMas.getDate("fecha_nacimiento").toString() : "Sin fecha";

                modeloM.addRow(new Object[]{
                    rsMas.getInt("id"),
                    rsMas.getString("nombre"),
                    fechaNacimiento,
                    rsMas.getString("altura"),
                    rsMas.getString("peso"),
                    sexoTexto,
                    rsMas.getString("notaAdicional"),
                    castTexto,
                    despaTexto,
                    mostrarEstado(rsMas.getString("estado_salud")), // Asegurarse de que mostrarEstado maneje valores nulos
                    estado,
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

    private void sinEditar() {
        txtId.setText("");
        txtNombre.setEditable(false);
        dtcFechaNacimiento.setEnabled(false);
        txtAltura.setEditable(false);
        txtPeso.setEditable(false);
        txtNotaAdicional.setEditable(false);
        //cbxSexo.setSelectedIndex(-1);
        cbxSexo.setEditable(false);// Restablecer el combo box al valor predeterminado
        // cbxRaza.setSelectedIndex(-1);
        cbxRaza.setEnabled(false);
        //cmbEspecie.setSelectedIndex(-1);
        chkCastrado.setEnabled(false);
        chkEstado.setEnabled(false);
        chkDesparasitado.setEnabled(false);
        //cmbEstadoSalud.setSelectedIndex(-1);
        cmbEstadoSalud.setEnabled(false);
    }

    private void sinEditarP() {

        dtcFechaNacimiento.setEnabled(false);

        txtAltura.setEditable(false);
        txtPeso.setEditable(false);
        txtNotaAdicional.setEditable(false);
        //cbxSexo.setSelectedIndex(1);
        cbxSexo.setEnabled(false);
        //cbxRaza.setSelectedIndex(0);
        cbxRaza.setEnabled(false);

        chkCastrado.setEnabled(false);
        chkEstado.setEnabled(false);
        chkDesparasitado.setEnabled(false);
        //cmbEstadoSalud.setSelectedIndex(-1);
        cmbEstadoSalud.setEnabled(false);
    }

    private void Editar() {

        txtNombre.setEditable(true);
        dtcFechaNacimiento.setEnabled(true);

        txtAltura.setEditable(true);
        txtPeso.setEditable(true);
        txtNotaAdicional.setEditable(true);
        //cbxSexo.setSelectedIndex(-1);
        cbxSexo.setEnabled(true);// Restablecer el combo box al valor predeterminado
        //cbxRaza.setSelectedIndex(-1);
        cbxRaza.setEnabled(true);
        //cmbEspecie.setSelectedIndex(-1);

        chkCastrado.setEnabled(true);
        chkEstado.setEnabled(true);
        chkDesparasitado.setEnabled(true);
        //cmbEstadoSalud.setSelectedIndex(-1);
        cmbEstadoSalud.setEnabled(true);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarD;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRaza;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbxRaza;
    private javax.swing.JComboBox<String> cbxSexo;
    private javax.swing.JCheckBox chkCastrado;
    private javax.swing.JCheckBox chkDesparasitado;
    private javax.swing.JCheckBox chkEstado;
    private javax.swing.JComboBox<String> cmbEstadoSalud;
    private com.toedter.calendar.JDateChooser dtcFechaNacimiento;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTable tblMascota;
    private javax.swing.JTextField txtAltura;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextArea txtNotaAdicional;
    private javax.swing.JTextField txtPeso;
    // End of variables declaration//GEN-END:variables
}

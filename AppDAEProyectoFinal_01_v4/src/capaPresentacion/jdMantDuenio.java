package capaPresentacion;

import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import capaNegocio.clsDuenio;
import capaNegocio.clsEspecialidad;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import soporte.Utilidad;

public class jdMantDuenio extends javax.swing.JDialog {

    clsDuenio objDuenio = new clsDuenio();

    public jdMantDuenio(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        sinEditarP();
        txtId.requestFocus();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cmbSexo = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtPaterno = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtNumC = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtMaterno = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtNumCA = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        chkVigencia = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        numDoc = new javax.swing.JTextField();
        btnBuscar1 = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDuenio = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnRegistrar = new javax.swing.JButton();
        btnAgregarMas = new javax.swing.JButton();
        btnModificar1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 0, 102));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("DUEÑO");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(464, 464, 464)
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

        jPanel2.setBackground(new java.awt.Color(138, 238, 238));

        cmbSexo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Femenino" }));
        cmbSexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSexoActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel7.setText("Nombres:");

        txtNombres.setBorder(null);
        txtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombresKeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel10.setText("Apellido Paterno:");

        jLabel15.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel15.setText("Sexo:");

        txtPaterno.setBorder(null);
        txtPaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPaternoKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel9.setText("Apellido Materno:");

        jLabel12.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel12.setText("Teléfono:");

        txtNumC.setBorder(null);
        txtNumC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumCKeyTyped(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel14.setText("Correo Electrónico:");

        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel13.setText("Dirección:");

        txtMaterno.setBorder(null);
        txtMaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaternoActionPerformed(evt);
            }
        });
        txtMaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMaternoKeyTyped(evt);
            }
        });

        txtCorreo.setBorder(null);

        jLabel16.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel16.setText("Teléfono Alternativo:");

        txtNumCA.setBorder(null);
        txtNumCA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumCAKeyTyped(evt);
            }
        });

        txtDireccion.setBorder(null);

        chkVigencia.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        chkVigencia.setText("Vigencia");

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel1.setText("Vigencia:");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel5.setText("Número de Doc.:");

        numDoc.setBorder(null);
        numDoc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numDocKeyTyped(evt);
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

        btnLimpiar.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/opacado.png"))); // NOI18N
        btnLimpiar.setBorder(null);
        btnLimpiar.setBorderPainted(false);
        btnLimpiar.setContentAreaFilled(false);
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumCA, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel10))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtMaterno)
                                        .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtNumC, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(37, 37, 37)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel15)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cmbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(numDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(87, 87, 87)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(chkVigencia))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtDireccion))))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLimpiar)
                    .addComponent(btnBuscar1))
                .addGap(40, 40, 40))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(txtMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtNumC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(numDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnBuscar1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(cmbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel16)
                                .addComponent(txtNumCA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14)
                                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkVigencia)
                            .addComponent(jLabel1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnLimpiar)
                        .addGap(9, 9, 9)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        tblDuenio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblDuenio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDuenioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDuenio);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/perro.png"))); // NOI18N

        jPanel4.setBackground(new java.awt.Color(128, 238, 238));

        btnRegistrar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/veterinario.png"))); // NOI18N
        btnRegistrar.setText("Nuevo");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
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

        btnModificar1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnModificar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/editar.png"))); // NOI18N
        btnModificar1.setText("Modificar");
        btnModificar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnAgregarMas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRegistrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnModificar1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(btnAgregarMas, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel8.setText("Código:");

        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel4))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar)))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
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
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        ResultSet rsDuenio = null;
        try {
            if (txtId.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un código para buscar");
                sinEditarP();
            } else {
                rsDuenio = objDuenio.buscarDuenio(txtId.getText());
                editar();

                if (rsDuenio.next()) {
                    txtNombres.setText(rsDuenio.getString("nombres"));
                    txtPaterno.setText(rsDuenio.getString("apePaterno"));
                    txtMaterno.setText(rsDuenio.getString("apeMaterno"));
                    txtNumC.setText(rsDuenio.getString("telefono"));
                    txtNumCA.setText(rsDuenio.getString("telefonoAlt"));
                    txtCorreo.setText(rsDuenio.getString("correo"));
                    txtDireccion.setText(rsDuenio.getString("direccion"));
                    cmbSexo.setSelectedItem(getSexoString(rsDuenio.getBoolean("sexo")));
                    chkVigencia.setSelected(rsDuenio.getBoolean("vigencia"));
                    numDoc.setText(rsDuenio.getString("doc_identidad"));
                    sinEditarP();
                    rsDuenio.close();
                } else {
                    JOptionPane.showMessageDialog(this, "Código de Dueño no existe");
                    limpiarControles();
                    sinEditarP();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error del sistema en la búsqueda");

        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        
        try {
            if (btnRegistrar.getText().equals("Agregar Dueño")) {
                btnRegistrar.setText("Guardar Datos");
                btnBuscar.setEnabled(false);
                btnModificar1.setEnabled(false);
                btnLimpiar.setEnabled(false);
                btnAgregarMas.setEnabled(false);
                limpiarControles();
                editar();
                txtId.setText(objDuenio.generarCodigoDuenio().toString());
                txtNombres.requestFocus();
                txtId.setEditable(false);
            } else {
                if (camposEstanLlenos()) {
                    btnRegistrar.setText("Agregar Dueño");
                    editar();
                    boolean sexo = cmbSexo.getSelectedItem().toString().equals("Masculino");
                    objDuenio.registrarDuenio(
                            Integer.parseInt(txtId.getText()),
                            numDoc.getText(),
                            txtNombres.getText(),
                            txtPaterno.getText(),
                            txtMaterno.getText(),
                            txtNumC.getText(),
                            txtNumCA.getText(),
                            txtCorreo.getText(),
                            txtDireccion.getText(),
                            sexo,
                            chkVigencia.isSelected());
                    // Limpiar los controles una vez se guarda el dueño
                    listarDuenios();  // Actualiza la lista de dueños
                    limpiarControles();
                    JOptionPane.showMessageDialog(null, "Dueño registrado con éxito!");

                }
                btnBuscar.setEnabled(true);
                btnModificar1.setEnabled(true);
                btnLimpiar.setEnabled(true);
                btnAgregarMas.setEnabled(true);
                txtId.requestFocus();
                sinEditarP();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: uno de los campos numéricos tiene formato incorrecto");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar al dueño: " + e.getMessage());
        }

    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        listarDuenios();

    }//GEN-LAST:event_formWindowOpened

    private void tblDuenioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDuenioMouseClicked
        // TODO add your handling code here:
        txtId.setText(String.valueOf(tblDuenio.getValueAt(tblDuenio.getSelectedRow(), 0)));
        btnBuscarActionPerformed(null);
    }//GEN-LAST:event_tblDuenioMouseClicked

    private void btnAgregarMasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarMasActionPerformed
        try {
            abrirOtroJDialog();
        } catch (SQLException ex) {
            Logger.getLogger(jdMantDuenio.class.getName()).log(Level.SEVERE, null, ex);
        }
        dispose();
    }//GEN-LAST:event_btnAgregarMasActionPerformed

    private void btnModificar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificar1ActionPerformed
        Object[] opciones = {"Sí", "No"};
        int respuesta = JOptionPane.showOptionDialog(null,
                "¿Estás seguro que deseas modificar la información?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);
        if (respuesta == 0) { // Si la respuesta es "Sí"
            try {
                // Validar que txtNumDoc no esté vacío
                if (txtId.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe seleccionar un elemento a modificar");
                } else {
                    if (btnModificar1.getText().equals("Modificar")) {
                        btnModificar1.setText("Guardar Datos");
                        btnBuscar.setEnabled(false);
                        editar();
                        txtId.setEditable(false);
                    } else {
                        if (camposEstanLlenos()) {
                            btnModificar1.setText("Modificar");
                            objDuenio.modificarDuenio(
                                    Integer.parseInt(txtId.getText()),
                                    numDoc.getText(),// numDoc debe ir primero
                                    txtNombres.getText(),
                                    txtPaterno.getText(),
                                    txtMaterno.getText(),
                                    txtNumC.getText(),
                                    txtNumCA.getText(),
                                    txtCorreo.getText(),
                                    txtDireccion.getText(),
                                    cmbSexo.getSelectedItem().toString().equals("Masculino"), // Booleano para el sexo
                                    chkVigencia.isSelected());
                            // Limpiar controles y actualizar la lista de dueños
                            listarDuenios();
                            limpiarControles();
                            sinEditarP();
                            JOptionPane.showMessageDialog(this, "Se Modificó con exito");
                            btnBuscar.setEnabled(true);
                        } else {

                        }

                    }
                }
            } catch (Exception e) {
                // Manejo de excepciones con mensaje más legible
                JOptionPane.showMessageDialog(this, "Ocurrió un problema interno en el sistema: " + e.getMessage());

            }
        }
    }//GEN-LAST:event_btnModificar1ActionPerformed

    private void cmbSexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSexoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSexoActionPerformed

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        // TODO add your handling code here:
         ResultSet rsDuenio = null;
        try {
            if (numDoc.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un número de documento para buscar");
                sinEditarP();
            } else {
                rsDuenio = objDuenio.buscarDuenioN(numDoc.getText());
                editar();

                if (rsDuenio.next()) {
                    txtId.setText(rsDuenio.getString("id"));
                    txtNombres.setText(rsDuenio.getString("nombres"));
                    txtPaterno.setText(rsDuenio.getString("apePaterno"));
                    txtMaterno.setText(rsDuenio.getString("apeMaterno"));
                    txtNumC.setText(rsDuenio.getString("telefono"));
                    txtNumCA.setText(rsDuenio.getString("telefonoAlt"));
                    txtCorreo.setText(rsDuenio.getString("correo"));
                    txtDireccion.setText(rsDuenio.getString("direccion"));
                    cmbSexo.setSelectedItem(getSexoString(rsDuenio.getBoolean("sexo")));
                    chkVigencia.setSelected(rsDuenio.getBoolean("vigencia"));
 
                    sinEditarP();
                  
                } else {
                    JOptionPane.showMessageDialog(this, "Código de Dueño no existe");
                    limpiarControles();
                    sinEditarP();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error del sistema en la búsqueda");

        }
        
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void numDocKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numDocKeyTyped
        // TODO add your handling code here:
        
         char c = evt.getKeyChar();
        // Verifica si el carácter no es un dígito
        if (!Character.isDigit(c)) {
            evt.consume(); // Consume el evento para evitar que se escriba el carácter no permitido
        }
    }//GEN-LAST:event_numDocKeyTyped

    private void txtIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyTyped
        // TODO add your handling code here:
         char c = evt.getKeyChar();
        // Verifica si el carácter no es un dígito
        if (!Character.isDigit(c)) {
            evt.consume(); // Consume el evento para evitar que se escriba el carácter no permitido
        }
    }//GEN-LAST:event_txtIdKeyTyped

    private void txtNumCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumCKeyTyped
        // TODO add your handling code here:
         char c = evt.getKeyChar();
        // Verifica si el carácter no es un dígito
        if (!Character.isDigit(c)) {
            evt.consume(); // Consume el evento para evitar que se escriba el carácter no permitido
        }
    }//GEN-LAST:event_txtNumCKeyTyped

    private void txtNumCAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumCAKeyTyped
        // TODO add your handling code here:
         char c = evt.getKeyChar();
        // Verifica si el carácter no es un dígito
        if (!Character.isDigit(c)) {
            evt.consume(); // Consume el evento para evitar que se escriba el carácter no permitido
        }
    }//GEN-LAST:event_txtNumCAKeyTyped

    private void txtNombresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresKeyTyped
        // TODO add your handling code here:
         char c = evt.getKeyChar();
        // Verifica si el carácter no es una letra y tampoco un espacio
        if (!Character.isLetter(c) && c != ' ') {
            evt.consume(); // Consume el evento para evitar que se escriba el carácter no permitido
        }
    }//GEN-LAST:event_txtNombresKeyTyped

    private void txtMaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaternoActionPerformed

    private void txtMaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaternoKeyTyped
        // TODO add your handling code here:
         char c = evt.getKeyChar();
        // Verifica si el carácter no es una letra y tampoco un espacio
        if (!Character.isLetter(c) && c != ' ') {
            evt.consume(); // Consume el evento para evitar que se escriba el carácter no permitido
        }
    }//GEN-LAST:event_txtMaternoKeyTyped

    private void txtPaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPaternoKeyTyped
        // TODO add your handling code here:
         char c = evt.getKeyChar();
        // Verifica si el carácter no es una letra y tampoco un espacio
        if (!Character.isLetter(c) && c != ' ') {
            evt.consume(); // Consume el evento para evitar que se escriba el carácter no permitido
        }
    }//GEN-LAST:event_txtPaternoKeyTyped

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        if (btnRegistrar.getText().equals(Utilidad.BTN_NUEVO)) {
            limpiarControles();
        } else {
            JOptionPane.showMessageDialog(this, "No puede ejecutar esta accion");
        }
        listarDuenios();
        tblDuenio.setModel(new DefaultTableModel());
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private String getSexoString(boolean sexo) {
        return sexo ? "Masculino" : "Femenino";
    }

    private boolean camposEstanLlenos() {
        // Verificar si los campos de texto están llenos
        if (txtId.getText().isEmpty() || txtNombres.getText().isEmpty() || txtPaterno.getText().isEmpty()
                || numDoc.getText().isEmpty() || txtMaterno.getText().isEmpty()
                || txtNumC.getText().isEmpty() || txtDireccion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Los campos más importantes deben estar llenos.");
            return false; // Retorna false si falta algún campo importante
        }
        return true; // Retorna true solo si todos los campos están llenos
    }

    private void abrirOtroJDialog() throws SQLException {
        // TODO add your handling code here:
        try {
            // Llamada al constructor que puede lanzar una excepción
            jdMantMascota objForm = new jdMantMascota(null, true);
            objForm.setLocationRelativeTo(this);
            objForm.setVisible(true);
        } catch (Exception e) {
            // Manejo de la excepción
            JOptionPane.showMessageDialog(this, "Error al abrir el formulario de mascota: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void sinEditar() {
        txtId.setEditable(false);
        numDoc.setEditable(false);
        txtNombres.setEditable(false);
        txtPaterno.setEditable(false);
        txtMaterno.setEditable(false); 
        txtNumC.setEditable(false);
        txtNumCA.setEditable(false);
        txtCorreo.setEditable(false);
        txtDireccion.setEditable(false);
        cmbSexo.setEnabled(false);
        chkVigencia.setEnabled(false);
    }

    private void sinEditarP() {
        
        txtNombres.setEditable(false);
        txtPaterno.setEditable(false);
        txtMaterno.setEditable(false);
        txtNumC.setEditable(false);
        txtNumCA.setEditable(false);
        txtCorreo.setEditable(false);
        txtDireccion.setEditable(false);
        cmbSexo.setEnabled(false);
        chkVigencia.setEnabled(false);
        txtId.setEditable(true);
    }

    private void editar() {
        txtId.setEditable(true);
        numDoc.setEditable(true);
        txtNombres.setEditable(true);
        txtPaterno.setEditable(true);
        txtMaterno.setEditable(true);
        txtNumC.setEditable(true);
        txtNumCA.setEditable(true);
        txtCorreo.setEditable(true);
        txtDireccion.setEditable(true);
        cmbSexo.setEnabled(true);
        chkVigencia.setEnabled(true);
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
        modeloD.addColumn("Teléfono Alt.");
        modeloD.addColumn("Correo");
        modeloD.addColumn("Dirección");
        modeloD.addColumn("Sexo");
        modeloD.addColumn("Vigencia");

        tblDuenio.setModel(modeloD);
        try {
            rsDue = objDuenio.listarDuenios();
            while (rsDue.next()) {
                String sexoTexto = rsDue.getBoolean("sexo") ? "Masculino" : "Femenino";
                if (rsDue.getString("vigencia").equals("t")) {
                    vigencia = "Si";
                } else {
                    vigencia = "No";
                }
                modeloD.addRow(new Object[]{
                    rsDue.getString("id"),
                    rsDue.getString("doc_identidad"),
                    rsDue.getString("nombres"),
                    rsDue.getString("apepaterno"),
                    rsDue.getString("apematerno"),
                    rsDue.getString("telefono"),
                    rsDue.getString("telefonoalt"),
                    rsDue.getString("correo"),
                    rsDue.getString("direccion"),
                    sexoTexto,
                    vigencia});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al listar Dueños!");
        }

    }

    private void limpiarControles() {

        txtId.setText("");
        txtNombres.setText("");
        txtPaterno.setText("");
        txtMaterno.setText("");
        txtNumC.setText("");
        txtNumCA.setText("");
        txtCorreo.setText("");
        txtDireccion.setText("");
        cmbSexo.setSelectedIndex(-1);
        numDoc.setText("");
        chkVigencia.setSelected(false);

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarMas;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar1;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JCheckBox chkVigencia;
    private javax.swing.JComboBox<String> cmbSexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField numDoc;
    private javax.swing.JTable tblDuenio;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtMaterno;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtNumC;
    private javax.swing.JTextField txtNumCA;
    private javax.swing.JTextField txtPaterno;
    // End of variables declaration//GEN-END:variables

}

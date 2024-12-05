package capaPresentacion;

import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import capaNegocio.clsDuenio;
import capaNegocio.clsEspecialidad;
import capaNegocio.clsServicio;
import java.awt.Frame;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import soporte.Utilidad;

public class jdMantDuenio extends javax.swing.JDialog {

    private static jdMantDuenio instanciaUnica;

    clsDuenio objDuenio = new clsDuenio();

    public jdMantDuenio(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        sinEditarP();
        txtId.requestFocus();
        listarDuenios();
    }

    public static jdMantDuenio getInstance(Frame parent, boolean modal) throws Exception {
        if (instanciaUnica == null) {
            instanciaUnica = new jdMantDuenio(parent, modal);
        }
        return instanciaUnica;
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
        txtNumDoc = new javax.swing.JTextField();
        btnBuscarPorDni = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDuenio = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnAgregarMas1 = new javax.swing.JButton();
        btnVigencia = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();

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
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(301, 301, 301)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jSeparator1)
                .addContainerGap())
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
        jLabel10.setText("Ap. Paterno:");

        jLabel15.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel15.setText("Sexo:");

        txtPaterno.setBorder(null);
        txtPaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPaternoKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel9.setText("Ap. Materno:");

        jLabel12.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel12.setText("Teléfono:");

        txtNumC.setBorder(null);
        txtNumC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumCKeyTyped(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel14.setText("Dirección:");

        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel13.setText("Correo:");

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
        jLabel16.setText("Tel. Alternativo:");

        txtNumCA.setBorder(null);
        txtNumCA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumCAKeyTyped(evt);
            }
        });

        txtDireccion.setBorder(null);

        chkVigencia.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        chkVigencia.setText("(Vigente)");

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel1.setText("Vigencia:");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel5.setText("Número de Doc.:");

        txtNumDoc.setBorder(null);
        txtNumDoc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumDocKeyTyped(evt);
            }
        });

        btnBuscarPorDni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/buscar-pequeño.png"))); // NOI18N
        btnBuscarPorDni.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnBuscarPorDni.setBorderPainted(false);
        btnBuscarPorDni.setContentAreaFilled(false);
        btnBuscarPorDni.setName(""); // NOI18N
        btnBuscarPorDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPorDniActionPerformed(evt);
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
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(41, 41, 41)
                                .addComponent(txtDireccion)
                                .addGap(2, 2, 2))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(28, 28, 28)
                                .addComponent(chkVigencia)
                                .addGap(49, 49, 49)
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addComponent(cmbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                                .addComponent(btnLimpiar)))
                        .addGap(44, 44, 44))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12)
                            .addComponent(jLabel14))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNombres, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                                    .addComponent(txtMaterno)
                                    .addComponent(txtNumC))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel16)
                                            .addComponent(jLabel9))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNumCA)
                                            .addComponent(txtPaterno)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNumDoc)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarPorDni)
                                .addGap(12, 12, 12))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtCorreo)
                                .addGap(44, 44, 44))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(41, 41, 41)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(txtPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(73, 73, 73)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel16)
                                .addComponent(txtNumCA, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnBuscarPorDni)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel5)
                                        .addComponent(txtNumDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel10))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(txtNumC, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCorreo))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(chkVigencia)
                        .addComponent(jLabel15)
                        .addComponent(cmbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnLimpiar))
                .addGap(0, 9, Short.MAX_VALUE))
        );

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

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/dueno-de-una-mascota.png"))); // NOI18N

        jPanel4.setBackground(new java.awt.Color(128, 238, 238));

        btnAgregarMas1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnAgregarMas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/veterinario.png"))); // NOI18N
        btnAgregarMas1.setText("Agregar Mascota");
        btnAgregarMas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarMas1ActionPerformed(evt);
            }
        });

        btnVigencia.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnVigencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/darBaja.png"))); // NOI18N
        btnVigencia.setText("Dar de baja");
        btnVigencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVigenciaActionPerformed(evt);
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

        btnNuevo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/veterinario.png"))); // NOI18N
        btnNuevo.setText("Registrar Dueño");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVigencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAgregarMas1))
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVigencia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregarMas1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(128, 238, 238));

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel8.setText("Id:");

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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscar)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel3))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabel4))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        listarDuenios();

    }//GEN-LAST:event_formWindowOpened

    private void tblDuenioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDuenioMouseClicked
        // TODO add your handling code here:
        Utilidad.buscarPorTabla(tblDuenio, btnBuscar, txtId);
        if (tblDuenio.getValueAt(tblDuenio.getSelectedRow(), 5).equals("Vigente")) {
            btnVigencia.setText("Dar baja");
            btnVigencia.setIcon(new ImageIcon(getClass().getResource("/conector/Recursos/darBaja.png")));
        } else {
            btnVigencia.setText("Dar alta");
            btnVigencia.setIcon(new ImageIcon(getClass().getResource("/conector/Recursos/darAlta.png")));
        }
    }//GEN-LAST:event_tblDuenioMouseClicked

    private void cmbSexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSexoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSexoActionPerformed

    private void btnBuscarPorDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPorDniActionPerformed
        // TODO add your handling code here:
        ResultSet rsDuenio = null;
        try {
            if (txtNumDoc.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un número de documento para buscar");
                sinEditarP();
            } else {
                rsDuenio = objDuenio.buscarDuenioN(txtNumDoc.getText());
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

    }//GEN-LAST:event_btnBuscarPorDniActionPerformed

    private void txtNumDocKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumDocKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume(); // Consume el evento para evitar que se escriba el carácter no permitido
        }
    }//GEN-LAST:event_txtNumDocKeyTyped

    private void txtIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume(); // Consume el evento para evitar que se escriba el carácter no permitido
        }
    }//GEN-LAST:event_txtIdKeyTyped

    private void txtNumCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumCKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume(); // Consume el evento para evitar que se escriba el carácter no permitido
        }
    }//GEN-LAST:event_txtNumCKeyTyped

    private void txtNumCAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumCAKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume(); // Consume el evento para evitar que se escriba el carácter no permitido
        }
    }//GEN-LAST:event_txtNumCAKeyTyped

    private void txtNombresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
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
        if (!Character.isLetter(c) && c != ' ') {
            evt.consume(); // Consume el evento para evitar que se escriba el carácter no permitido
        }
    }//GEN-LAST:event_txtMaternoKeyTyped

    private void txtPaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPaternoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isLetter(c) && c != ' ') {
            evt.consume(); // Consume el evento para evitar que se escriba el carácter no permitido
        }
    }//GEN-LAST:event_txtPaternoKeyTyped

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnAgregarMas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarMas1ActionPerformed
        try {
            jdMantMascota objForm = new jdMantMascota(null, true);
            objForm.setLocationRelativeTo(this);
            objForm.setVisible(true);
            this.dispose();
        } catch (Exception ex) {
            Logger.getLogger(jdMantDuenio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAgregarMas1ActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        ResultSet rsMascota = null;

        try {
            if (txtId.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un código para buscar");
            } else {
                rsMascota = objDuenio.buscarDuenio(Integer.parseInt(txtId.getText()));
                Utilidad.desactivarFields(txtId, txtCorreo, txtDireccion, txtMaterno, txtNombres, txtNumC, txtNumCA, txtPaterno, txtNumDoc);
                cmbSexo.setEnabled(false);
                chkVigencia.setEnabled(false);
                if (rsMascota.next()) {
                    txtNombres.setText(rsMascota.getString("nombres"));
                    txtPaterno.setText(rsMascota.getString("apepaterno"));
                    txtMaterno.setText(rsMascota.getString("apematerno"));
                    txtNumDoc.setText(rsMascota.getString("doc_identidad"));
                    txtNumC.setText(rsMascota.getString("telefono"));
                    txtNumCA.setText(rsMascota.getString("telefonoalt"));
                    txtCorreo.setText(rsMascota.getString("correo"));
                    txtDireccion.setText(rsMascota.getString("direccion"));
                    chkVigencia.setSelected(rsMascota.getBoolean("vigencia"));
                    String sexo = "";
                    if (rsMascota.getBoolean("sexo")) {
                        sexo = "Masculino";
                    } else {
                        sexo = "Femenino";
                    }
                    cmbSexo.setSelectedItem(sexo);

                    rsMascota.close();
                } else {
                    JOptionPane.showMessageDialog(this, "Codigo de usuario no existente");
                    limpiarControles();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error " + e.getLocalizedMessage());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    

    private void btnVigenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVigenciaActionPerformed
        // TODO add your handling code here:
        darBaja();
        if (tblDuenio.getValueAt(tblDuenio.getSelectedRow(), 5).equals("Vigente")) {
            darBaja();
        } else {
            darAlta();
        }
    }//GEN-LAST:event_btnVigenciaActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
      
        
        
        try {
            if (txtId.getText().isBlank()) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un elemento a modificar");
            } else {
                if (btnModificar.getText().equals("Modificar")) {
                    btnModificar.setText("Guardar Datos");
                    btnEliminar.setText("Cancelar");
btnAgregarMas1.setEnabled(false);
                    txtId.setEnabled(false);
                    Utilidad.activarFields(txtCorreo, txtDireccion, txtMaterno, txtNombres, txtNumC);
                    cmbSexo.setEnabled(true);
                    //   btnEliminar.setText(Utilidad.BTN_CANCELAR);
                    Utilidad.desactivarBotones(btnModificar, btnLimpiar, btnBuscar, btnNuevo, btnVigencia);
                    tblDuenio.setEnabled(true);
                } else {
                 //   int valor = Utilidad.mensajeConfirmarModificar("Usuario", Integer.parseInt(txtId.getText()), txtNombres.getText());
                 //   if (valor == JOptionPane.YES_OPTION) {
                        boolean sexo = false;
                        if (cmbSexo.getSelectedItem().toString().equals("Masculino")) {
                            sexo = true;
                        }

                        objDuenio.modificarDuenio(Integer.parseInt(txtId.getText()), txtNumDoc.getText(), txtNombres.getText(),
                                txtPaterno.getText(), txtMaterno.getText(), txtNumC.getText(), txtNumCA.getText(), txtCorreo.getText(),
                                txtDireccion.getText(), sexo, chkVigencia.isSelected());
                        btnEliminar.setText("Eliminar");
                        Utilidad.activarBotones(btnNuevo, btnBuscar, btnLimpiar, btnModificar, btnVigencia);
                        limpiarControles();
                        listarDuenios();
                        btnAgregarMas1.setEnabled(true);
                        btnModificar.setText("Modificar");
                        JOptionPane.showMessageDialog(this, "Se modificó con exito");
                        tblDuenio.setEnabled(true);
                    }

             //   }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error:" + e.getMessage());
        }
    }//GEN-LAST:event_btnModificarActionPerformed



    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        if (btnNuevo.getText().equals("Guardar Datos") || btnModificar.getText().equals("Guardar Datos")) {
            limpiarControles();
            listarDuenios();
            tblDuenio.setEnabled(true);
            btnNuevo.setText("Registrar Dueño");
            btnModificar.setText("Modificar");
            btnBuscar.setEnabled(true);
            btnModificar.setEnabled(true);
            btnNuevo.setEnabled(true);
            btnEliminar.setText("Eliminar");
            btnEliminar.setEnabled(true);
            btnVigencia.setEnabled(true);
            btnLimpiar.setEnabled(true);
            txtId.setEditable(true);
            btnAgregarMas1.setEnabled(true);
            chkVigencia.setEnabled(false);
            chkVigencia.setSelected(false);
            btnBuscarPorDni.setSelected(true);
            sinEditarP();

        } else {
            eliminarDuenio();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        if (btnNuevo.getText().equals("Registrar Dueño")) {
            try {
                btnNuevo.setText("Guardar Datos");
                btnEliminar.setText("Cancelar");
                
                limpiarControles();
                txtId.setText(objDuenio.generarCodigoDuenio().toString());
                txtNombres.requestFocus();
                btnBuscar.setEnabled(false);
                btnModificar.setEnabled(false);
                btnLimpiar.setEnabled(true);
                txtId.setEnabled(false);
                btnAgregarMas1.setEnabled(false);
                // btnEliminar.setEnabled(false);
                btnVigencia.setEnabled(false);
                chkVigencia.setEnabled(false);
                chkVigencia.setSelected(true);
                btnBuscarPorDni.setSelected(false);
                editar();
            } catch (Exception ex) {
                Logger.getLogger(jdMantDuenio.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (!camposEstanLlenos()) {
                return;
            }
            boolean sexo;
            if (cmbSexo.getSelectedItem().toString().equals("Masculino")) {
                sexo = true;
            } else {
                sexo = false;
            }

            try {
                objDuenio.registrarDuenio(Integer.parseInt(
                        txtId.getText()), txtNumDoc.getText(), txtNombres.getText(),
                        txtPaterno.getText(), txtMaterno.getText(), txtNumC.getText(), txtNumCA.getText(), txtCorreo.getText(),
                        txtDireccion.getText(), sexo, chkVigencia.isSelected()
                );
                limpiarControles();
                listarDuenios();
                sinEditarP();
                JOptionPane.showMessageDialog(this, "Dueño registrada con éxito");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al registrar el dueño: " + e.getMessage());
                return;
            }
            btnNuevo.setText("Registrar Dueño");
            btnBuscar.setEnabled(true);
            btnModificar.setEnabled(true);
            btnEliminar.setText("Eliminar");
            btnVigencia.setEnabled(true);
            btnLimpiar.setEnabled(true);
            txtId.setEditable(true);
            btnAgregarMas1.setSelected(true);
            chkVigencia.setEnabled(false);
            chkVigencia.setSelected(false);
            btnBuscarPorDni.setSelected(true);
        }
    

    }//GEN-LAST:event_btnNuevoActionPerformed

    private void eliminarDuenio() {
        try {
            int selectedRow = tblDuenio.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona una fila.");
                return;
            }
            int idMascota = Integer.parseInt(tblDuenio.getValueAt(selectedRow, 0).toString()); // Suponiendo que el ID está en la primera columna

            if (txtId.getText().isBlank()) {
                Utilidad.mensajeErrorFaltaID(this);
            } else if (Utilidad.validarEliminacionForanea(clsServicio.TABLA, idMascota)) {
                JOptionPane.showMessageDialog(this,
                        "Hay datos externos asociados a al  Dueño \"" + txtNombres.getText() + "\".\n"
                        + "Considere cambiar su vigencia para que ya no pueda ser usado.");
            } else {
                int valor = JOptionPane.showConfirmDialog(null, "Deseas continuar?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (valor == JOptionPane.YES_OPTION) {
                    objDuenio.eliminarDuenio(idMascota);
                    limpiarControles();
                    listarDuenios();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private String getSexoString(boolean sexo) {
        return sexo ? "Masculino" : "Femenino";
    }

    private boolean camposEstanLlenos() {
        if (txtId.getText().isEmpty() || txtNombres.getText().isEmpty() || txtPaterno.getText().isEmpty()
                || txtNumDoc.getText().isEmpty() || txtMaterno.getText().isEmpty()
                || txtNumC.getText().isEmpty() || txtDireccion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Los campos más importantes deben estar llenos.");
            return false;
        }
        return true;
    }

    private void sinEditar() {
        txtId.setEditable(false);
        txtNumDoc.setEditable(false);
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
        txtNumDoc.setEditable(true);
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
            tblDuenio.setModel(modeloD);
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
        txtNumDoc.setText("");
        chkVigencia.setSelected(false);

    }

    private void darBaja() {
        try {
            Integer id = Integer.parseInt(txtId.getText());
            ResultSet rsUsuario = null;
            if (id.equals("")) {
                JOptionPane.showConfirmDialog(this, "Debe ingresar un codigo");
            } else {
                int valor = Utilidad.mensajeConfirmarVigencia("Usuario", Integer.parseInt(txtId.getText()), txtNombres.getText());
                if (valor == JOptionPane.YES_OPTION) {
                    rsUsuario = objDuenio.buscarDuenio(id);
                    if (rsUsuario.next()) {
                        if (rsUsuario.getBoolean("vigencia")) {
                            objDuenio.darBaja(id);
                            limpiarControles();
                            listarDuenios();
                            JOptionPane.showMessageDialog(null, "Fue dado de baja con éxito");
                        } else {
                            JOptionPane.showMessageDialog(this, "Este elemento ya fue dado de baja");
                        }
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void darAlta() {
        try {
            Integer id = Integer.parseInt(txtId.getText());
            ResultSet rsUsuario = null;
            if (id.equals("")) {
                JOptionPane.showConfirmDialog(this, "Debe ingresar un codigo");
            } else {
                int valor = Utilidad.mensajeConfirmarVigencia("Usuario", Integer.parseInt(txtId.getText()), txtNombres.getText());
                if (valor == JOptionPane.YES_OPTION) {
                    rsUsuario = objDuenio.buscarDuenio(id);
                    if (rsUsuario.next()) {
                        if (!rsUsuario.getBoolean("vigencia")) {
                            objDuenio.darAlta(id);
                            limpiarControles();
                            listarDuenios();
                            JOptionPane.showMessageDialog(null, "Fue dado de alta con éxito");
                        } else {
                            JOptionPane.showMessageDialog(this, "Este elemento ya fue dado de alta");
                        }
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarMas1;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarPorDni;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnVigencia;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblDuenio;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtMaterno;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtNumC;
    private javax.swing.JTextField txtNumCA;
    private javax.swing.JTextField txtNumDoc;
    private javax.swing.JTextField txtPaterno;
    // End of variables declaration//GEN-END:variables

}

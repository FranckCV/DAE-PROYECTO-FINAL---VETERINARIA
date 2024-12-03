package capaPresentacion;

import capaNegocio.*;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import soporte.Utilidad;

public class jdMantMascotaNOVALE extends javax.swing.JDialog {

    private static jdMantMascotaNOVALE instanciaUnica;

    clsMascota objMasco = new clsMascota();
    clsRaza objRaza = new clsRaza();
    clsEspecie objEsp = new clsEspecie();
    Integer IdEspecie = -1;

    public jdMantMascotaNOVALE(java.awt.Frame parent, boolean modal) throws SQLException, Exception {
        super(parent, modal);
        initComponents();
        sinEditarP();
        txtId.requestFocus();
        listarMascotas();
        Utilidad.validacionTabla(tblMascota, modal, rootPaneCheckingEnabled, modal);

    }

    public static jdMantMascotaNOVALE getInstance(Frame parent, boolean modal) throws Exception {
        if (instanciaUnica == null) {
            instanciaUnica = new jdMantMascotaNOVALE(parent, modal);
        }
        return instanciaUnica;
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
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNotaAdicional = new javax.swing.JTextArea();
        cbxEspeciee = new javax.swing.JComboBox<>();
        dtcFechaNacimiento = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        cbxSexo = new javax.swing.JComboBox<>();
        txtNombre = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtAltura = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        cmbEstadoSalud = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtPeso = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnBuscar1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        cbxRazita = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtId = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        btnModificar = new javax.swing.JButton();
        btnAgregarD = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        btnVigencia = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        chkEstado = new javax.swing.JCheckBox();
        chkCastrado = new javax.swing.JCheckBox();
        chkDesparasitado = new javax.swing.JCheckBox();
        jLabel13 = new javax.swing.JLabel();

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
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(366, 366, 366)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 654, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(204, 255, 255));

        tblMascota.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
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
        jLabel9.setText("Especie:");

        jLabel11.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel11.setText("Peso:");

        jScrollPane2.setAutoscrolls(true);

        txtNotaAdicional.setColumns(20);
        txtNotaAdicional.setRows(5);
        txtNotaAdicional.setAutoscrolls(false);
        txtNotaAdicional.setBorder(null);
        jScrollPane2.setViewportView(txtNotaAdicional);

        cbxEspeciee.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cbxEspeciee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxEspecieeActionPerformed(evt);
            }
        });

        dtcFechaNacimiento.setDateFormatString("yyyy-MM-dd");
        dtcFechaNacimiento.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        dtcFechaNacimiento.setOpaque(false);
        dtcFechaNacimiento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dtcFechaNacimientoFocusLost(evt);
            }
        });
        dtcFechaNacimiento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dtcFechaNacimientoMouseClicked(evt);
            }
        });
        dtcFechaNacimiento.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtcFechaNacimientoPropertyChange(evt);
            }
        });

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

        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel10.setText("Altura:");

        cbxRazita.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cbxRazita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxRazitaActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel14.setText("Nota adicional:");

        btnLimpiar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/escoba-mascota.png"))); // NOI18N
        btnLimpiar.setBorderPainted(false);
        btnLimpiar.setContentAreaFilled(false);
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
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel14))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnLimpiar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(144, 144, 144))
                                    .addComponent(cbxEspeciee, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addComponent(jLabel11)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel17))
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addComponent(jLabel10)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel16))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbxRazita, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbEstadoSalud, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(cbxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dtcFechaNacimiento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addGap(16, 16, 16))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jButton5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(21, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGap(9, 9, 9)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxEspeciee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(dtcFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmbEstadoSalud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbxRazita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(80, 80, 80)
                                .addComponent(jLabel19)
                                .addGap(18, 18, 18)))))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
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

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel4.setText("Id:");

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/buscar.png"))); // NOI18N
        btnBuscar.setBorderPainted(false);
        btnBuscar.setContentAreaFilled(false);
        btnBuscar.setDoubleBuffered(true);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscar)
                .addGap(21, 21, 21))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(138, 238, 238));

        btnModificar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/editar.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
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

        btnRegistrar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/veterinario.png"))); // NOI18N
        btnRegistrar.setText("Registrar Mascota");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnVigencia.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnVigencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/darBaja.png"))); // NOI18N
        btnVigencia.setText("Dar Baja");
        btnVigencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVigenciaActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAgregarD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVigencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVigencia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregarD, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(138, 238, 238));

        chkEstado.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        chkEstado.setText("Vivo");

        chkCastrado.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        chkCastrado.setText("Esterilizado");
        chkCastrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCastradoActionPerformed(evt);
            }
        });

        chkDesparasitado.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        chkDesparasitado.setText("Desparasitado");

        jLabel13.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel13.setText("Estado:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(86, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(30, 30, 30)
                .addComponent(chkEstado)
                .addGap(59, 59, 59)
                .addComponent(chkCastrado)
                .addGap(62, 62, 62)
                .addComponent(chkDesparasitado)
                .addGap(75, 75, 75))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkEstado)
                    .addComponent(chkCastrado)
                    .addComponent(chkDesparasitado, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(0, 4, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        try {
            listarMascotas();
            inicializarComboSexo();
            listarNombreEspecie();
            listarNombreRaza();
            inicializarComboEstado();
        } catch (SQLException ex) {
            Logger.getLogger(jdMantMascotaNOVALE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(jdMantMascotaNOVALE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowOpened

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        try {
            if (btnRegistrar.getText().equals("Registrar Mascota")) {
                btnRegistrar.setText("Guardar Datos");
                limpiarCampos();
                txtId.setText(objMasco.generarCodigoMascota().toString());
                txtNombre.requestFocus();
                btnBuscar.setEnabled(false);
                btnModificar.setEnabled(false);
                btnLimpiar.setEnabled(false);
                btnAgregarD.setEnabled(false);
                txtId.setEditable(false);
                btnEliminar.setEnabled(false);
                    btnVigencia.setEnabled(false);
                chkEstado.setEnabled(false);
                chkEstado.setSelected(true);
                txtNotaAdicional.setEditable(true);
                Editar();
            } else {
                if (!camposEstanLlenos()) {
                    return;
                }
                String razaSeleccionada = cbxRazita.getSelectedItem() != null ? cbxRazita.getSelectedItem().toString() : null;
                if (razaSeleccionada == null || razaSeleccionada.equals("-Sin Razas-")) {
                    JOptionPane.showMessageDialog(this, "Debe seleccionar una raza válida antes de registrar.");
                    return;
                }
                int idRaza = objRaza.obtenerIdRaza(razaSeleccionada);
                if (idRaza == 0) {
                    JOptionPane.showMessageDialog(this, "La raza seleccionada no es válida.");
                    return;
                }
                try {
                    objMasco.registrarMascota(
                            Integer.parseInt(txtId.getText()),
                            txtNombre.getText(),
                            dtcFechaNacimiento.getDate(),
                            Double.parseDouble(txtAltura.getText()),
                            Double.parseDouble(txtPeso.getText()),
                            txtNotaAdicional.getText(),
                            cbxSexo.getSelectedItem().toString().equals(""),
                            chkCastrado.isSelected(),
                            chkDesparasitado.isSelected(),
                            extraerEstado(cmbEstadoSalud.getSelectedItem().toString()),
                            chkEstado.isSelected(),
                            idRaza
                    );
                    listarMascotas();
                    inicializarComboSexo();
                    listarNombreEspecie();
                    listarNombreRaza();
                    limpiarCampos();
                    sinEditarP();
                    JOptionPane.showMessageDialog(this, "Mascota registrada con éxito");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error al registrar la mascota: " + e.getMessage());
                    return;
                }
                btnRegistrar.setText("Registrar Mascota");
                btnBuscar.setEnabled(true);
                btnModificar.setEnabled(true);
                btnEliminar.setEnabled(true);
                    btnVigencia.setEnabled(true);
                btnLimpiar.setEnabled(true);
                btnAgregarD.setEnabled(true);
                txtId.setEditable(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error interno al registrar la mascota: " + e.getMessage());
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void tblMascotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMascotaMouseClicked
        // TODO add your handling code here:
        try {
            txtId.setText(String.valueOf(tblMascota.getValueAt(tblMascota.getSelectedRow(), 0)));
            mostrarCampos();

            if (tblMascota.getValueAt(tblMascota.getSelectedRow(), 10).equals("VIGENTE")) {
                btnVigencia.setText("Dar baja");
                btnVigencia.setIcon(new ImageIcon(getClass().getResource("/conector/Recursos/darBaja.png")));
            } else {
                btnVigencia.setText("Dar alta");
                btnVigencia.setIcon(new ImageIcon(getClass().getResource("/conector/Recursos/darAlta.png")));
            }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_tblMascotaMouseClicked
    private String getSexoString(boolean sexo) {
        return sexo ? "Macho" : "Hembra";
    }

    private void inicializarComboSexo() {
        DefaultComboBoxModel<String> modeloSexo = new DefaultComboBoxModel<>();
        modeloSexo.addElement("-Selecciona-");
        modeloSexo.addElement("Macho");
        modeloSexo.addElement("Hembra");
        cbxSexo.setModel(modeloSexo);
        cbxSexo.setSelectedIndex(0);
    }

    private void inicializarComboEstado() {
        DefaultComboBoxModel<String> modeloEstado = new DefaultComboBoxModel<>();
        modeloEstado.addElement("-Selecciona-"); // Primera opción
        modeloEstado.addElement("Terminal");
        modeloEstado.addElement("Crónica");
        modeloEstado.addElement("Saludable");
        cmbEstadoSalud.setModel(modeloEstado);
        cmbEstadoSalud.setSelectedIndex(0);
    }

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
                    JOptionPane.showMessageDialog(this, "Debe seleccionar un elemento a modificar.");
                    return;
                }
                if (btnModificar.getText().equals("Modificar")) {
                    btnModificar.setText("Guardar Datos");
                    btnBuscar.setEnabled(false);
                    btnLimpiar.setEnabled(false);
                    btnRegistrar.setEnabled(false);
                    btnAgregarD.setEnabled(false);
                    txtId.setEditable(false);
                    btnEliminar.setEnabled(false);
                    btnVigencia.setEnabled(false);
                    chkEstado.setEnabled(false);
                    
                    Editar();
                } else {
                    if (!camposEstanLlenos()) {
                        return;
                    }
                    String razaSeleccionada = cbxRazita.getSelectedItem() != null ? cbxRazita.getSelectedItem().toString() : null;
                    if (razaSeleccionada == null || razaSeleccionada.equals("-Sin Razas-")) {
                        JOptionPane.showMessageDialog(this, "Debe seleccionar una raza válida antes de modificar.");
                        return;
                    }

                    int idRaza = objRaza.obtenerIdRaza(razaSeleccionada);
                    if (idRaza == 0) {
                        JOptionPane.showMessageDialog(this, "La raza seleccionada no es válida.");
                        return;
                    }
                    int idMascota = Integer.parseInt(txtId.getText().trim());
                    double altura = Double.parseDouble(txtAltura.getText().trim());
                    double peso = Double.parseDouble(txtPeso.getText().trim());
                    try {
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
                                idRaza,
                                chkEstado.isSelected());
                        JOptionPane.showMessageDialog(this, "Información de mascota modificada exitosamente.");
                        limpiarCampos();
                        listarMascotas();
                        inicializarComboSexo();
                        listarNombreEspecie();
                        listarNombreRaza();
                        inicializarComboEstado();
                        sinEditarP();
                        btnModificar.setText("Modificar");
                        btnBuscar.setEnabled(true);
                        txtId.setEditable(true);
                        btnLimpiar.setEnabled(true);
                        btnRegistrar.setEnabled(true);
btnEliminar.setEnabled(true);
                    btnVigencia.setEnabled(true);
                        btnAgregarD.setEnabled(true);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Error al modificar la mascota: " + e.getMessage());
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al modificar la mascota: " + e.getMessage());
            }
        }

    }//GEN-LAST:event_btnModificarActionPerformed

    private void chkCastradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCastradoActionPerformed
    }//GEN-LAST:event_chkCastradoActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        try {
            limpiarCampos();
            listarMascotas();
            inicializarComboSexo();
            listarNombreEspecie();
            listarNombreRaza();
            inicializarComboEstado();
        } catch (Exception ex) {
        }

    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnAgregarDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDActionPerformed
        jdMantDuenioNOVALE objForm = new jdMantDuenioNOVALE(null, true);
        objForm.setLocationRelativeTo(this);
        objForm.setVisible(true);

    }//GEN-LAST:event_btnAgregarDActionPerformed

    private void btnRazaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRazaActionPerformed
        jdMantDuenioNOVALE objForm = new jdMantDuenioNOVALE(null, true);
        objForm.setLocationRelativeTo(this);
        objForm.setVisible(true);

    }//GEN-LAST:event_btnRazaActionPerformed

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        ResultSet rsMas = null;
        DefaultTableModel modeloM = new DefaultTableModel();
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
            if (txtNombre.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe completar el campo de búsqueda.");
                listarMascotas();
                return;
            }
            rsMas = objMasco.filtrar(txtNombre.getText().trim());
            boolean hayResultados = false;
            mostrarCampos();
            while (rsMas.next()) {
                hayResultados = true;
                String sexoTexto = rsMas.getBoolean("sexo") ? "Macho" : "Hembra";
                String despaTexto = rsMas.getBoolean("desparasitado") ? "SI" : "NO";
                String castTexto = rsMas.getBoolean("esterilizado") ? "SI" : "NO";
                String estado = rsMas.getBoolean("vigencia") ? "VIGENTE" : "NO VIGENTE";
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
                    mostrarEstado(rsMas.getString("estado_salud")),
                    estado,
                    rsMas.getString("raza_nombre"),});
            }

            if (!hayResultados) {
                JOptionPane.showMessageDialog(this, "No se encontraron mascotas con ese nombre.");
                listarMascotas();
                inicializarComboSexo();
                listarNombreEspecie();
                listarNombreRaza();
                inicializarComboEstado();
            }
        } catch (Exception e) {
            try {
                JOptionPane.showMessageDialog(this, "Error al listar Mascotas: " + e.getMessage());
                listarMascotas();
            } catch (SQLException ex) {
                Logger.getLogger(jdMantMascotaNOVALE.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(jdMantMascotaNOVALE.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isLetter(c) && c != ' ') {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtIdKeyTyped

    private void txtPesoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoKeyTyped
        char c = evt.getKeyChar();
        String texto = ((javax.swing.JTextField) evt.getSource()).getText();
        if (!Character.isDigit(c) && c != '.') {
            evt.consume(); // Ignorar el carácter
        }
        if (c == '.' && texto.contains(".")) {
            evt.consume(); // Ignorar si ya hay un punto
        }
        if (texto.contains(".")) {
            int index = texto.indexOf('.');
            if (texto.length() - index > 2 && Character.isDigit(c)) {
                evt.consume();
            }
        }
    }//GEN-LAST:event_txtPesoKeyTyped

    private void txtAlturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlturaKeyTyped
        char c = evt.getKeyChar();
        String texto = ((javax.swing.JTextField) evt.getSource()).getText();
        if (!Character.isDigit(c) && c != '.') {
            evt.consume(); // Ignorar el carácter
        }
        if (c == '.' && texto.contains(".")) {
            evt.consume(); // Ignorar si ya hay un punto
        }
        if (texto.contains(".")) {
            int index = texto.indexOf('.');
            if (texto.length() - index > 2 && Character.isDigit(c)) {
                evt.consume();
            }
        }
    }//GEN-LAST:event_txtAlturaKeyTyped

    private void cbxEspecieeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxEspecieeActionPerformed
        try {
            if (cbxEspeciee.getSelectedItem() != null && !cbxEspeciee.getSelectedItem().toString().equals("-Ninguno-")) {
                IdEspecie = objEsp.obtenerIdEspecie(cbxEspeciee.getSelectedItem().toString());
                listarNombreRaza();
            } else {
                DefaultComboBoxModel modeloVacio = new DefaultComboBoxModel();
                modeloVacio.addElement("-Sin Razas-");
                cbxRazita.setModel(modeloVacio);
                cbxRazita.setSelectedIndex(0);
            }
        } catch (Exception ex) {
            Logger.getLogger(jdMantMascotaNOVALE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cbxEspecieeActionPerformed

    private void cbxRazitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxRazitaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxRazitaActionPerformed

    private void dtcFechaNacimientoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtcFechaNacimientoMouseClicked

    }//GEN-LAST:event_dtcFechaNacimientoMouseClicked

    private void dtcFechaNacimientoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtcFechaNacimientoPropertyChange
        if ("date".equals(evt.getPropertyName())) {
            if (dtcFechaNacimiento.getDate() == null) {
                return;
            }
            Date fechaSeleccionada = dtcFechaNacimiento.getDate();
            Date fechaActual = new Date();

            if (fechaSeleccionada.after(fechaActual)) {
                JOptionPane.showMessageDialog(this, "En este campo no es permitido ingresar fechas futuras");
                dtcFechaNacimiento.setDate(null);
            }
        }
    }//GEN-LAST:event_dtcFechaNacimientoPropertyChange

    private void dtcFechaNacimientoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dtcFechaNacimientoFocusLost

    }//GEN-LAST:event_dtcFechaNacimientoFocusLost


    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        ResultSet rsMas = null;
        DefaultTableModel modeloM = new DefaultTableModel();
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
            if (txtId.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe completar el campo de búsqueda.");
                listarMascotas();
                return;
            }
            rsMas = objMasco.filtrarID(Integer.parseInt(txtId.getText()));
            boolean hayResultados = false;
            mostrarCampos();
            while (rsMas.next()) {
                hayResultados = true;
                String sexoTexto = rsMas.getBoolean("sexo") ? "Macho" : "Hembra";
                String despaTexto = rsMas.getBoolean("desparasitado") ? "SI" : "NO";
                String castTexto = rsMas.getBoolean("esterilizado") ? "SI" : "NO";
                String estado = rsMas.getBoolean("vigencia") ? "VIGENTE" : "NO VIGENTE";
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
                    mostrarEstado(rsMas.getString("estado_salud")),
                    estado,
                    rsMas.getString("raza_nombre"),}
                );
            }

            if (!hayResultados) {
                JOptionPane.showMessageDialog(this, "No se encontraron mascotas con ese id.");
                listarMascotas();
                inicializarComboSexo();
                listarNombreEspecie();
                listarNombreRaza();
                inicializarComboEstado();
            }
        } catch (Exception e) {
            try {
                JOptionPane.showMessageDialog(this, "Error al listar Mascotas: " + e.getMessage());
                listarMascotas();
            } catch (SQLException ex) {
                Logger.getLogger(jdMantMascotaNOVALE.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(jdMantMascotaNOVALE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnVigenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVigenciaActionPerformed
        // TODO add your handling code here:
        darBaja();
        if (tblMascota.getValueAt(tblMascota.getSelectedRow(), 10).equals("Vigente")) {
            darBaja();
        } else {
            darAlta();
        }


    }//GEN-LAST:event_btnVigenciaActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
   if (btnRegistrar.getText().equals(Utilidad.BTN_GUARDAR) || btnModificar.getText().equals(Utilidad.BTN_GUARDAR)) {
        try {
            cancelarAccion();
            tblMascota.setEnabled(true);
            Utilidad.activarBotones(btnRegistrar, btnBuscar, btnLimpiar, btnModificar, btnVigencia);
        } catch (Exception ex) {
            Logger.getLogger(jdMantMascotaNOVALE.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else {
        eliminarMascota();
    }
    }//GEN-LAST:event_btnEliminarActionPerformed

private void eliminarMascota() {
    try {
        int selectedRow = tblMascota.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona una fila.");
            return;
        }
                int idMascota = Integer.parseInt(tblMascota.getValueAt(selectedRow, 0).toString()); // Suponiendo que el ID está en la primera columna

        if (txtId.getText().isBlank()) {
            Utilidad.mensajeErrorFaltaID(this);
        } else if (Utilidad.validarEliminacionForanea(clsServicio.TABLA, idMascota)) {
            JOptionPane.showMessageDialog(this, 
                    "Hay datos externos asociados a la mascota \"" + txtNombre.getText() + "\".\n"
                    + "Considere cambiar su vigencia para que ya no pueda ser usado.");
        } else {
            int valor = JOptionPane.showConfirmDialog(null, "Deseas continuar?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if (valor == JOptionPane.YES_OPTION) {
                objMasco.eliminarMascota(idMascota);
                listarMascotas();
                inicializarComboSexo();
                listarNombreEspecie();
                listarNombreRaza();
                inicializarComboEstado();
                limpiarCampos();
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
}

    private void cancelarAccion() throws Exception {
        btnRegistrar.setText(Utilidad.BTN_NUEVO);
        btnModificar.setText(Utilidad.BTN_MODIFICAR);
        btnEliminar.setText(Utilidad.BTN_ELIMINAR);
        limpiarCampos();
        listarMascotas();
    }

    private void darBaja() {
        try {
            Integer id = Integer.parseInt(txtId.getText());
            ResultSet rsUsuario = null;
            if (id.equals("")) {
                JOptionPane.showConfirmDialog(this, "Debe ingresar un codigo");
            } else {
                int valor = Utilidad.mensajeConfirmarVigencia("Usuario", Integer.parseInt(txtId.getText()), txtNombre.getText());
                if (valor == JOptionPane.YES_OPTION) {
                    rsUsuario = objMasco.buscarMascota(id);
                    if (rsUsuario.next()) {
                        if (rsUsuario.getBoolean("vigencia")) {
                            objMasco.darBaja(id);
                            limpiarCampos();
                            listarMascotas();
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
                int valor = Utilidad.mensajeConfirmarVigencia("Usuario", Integer.parseInt(txtId.getText()), txtNombre.getText());
                if (valor == JOptionPane.YES_OPTION) {
                    rsUsuario = objMasco.buscarMascota(id);
                    if (rsUsuario.next()) {
                        if (!rsUsuario.getBoolean("vigencia")) {
                            objMasco.darAlta(id);
                            limpiarCampos();
                            listarMascotas();
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

    private void listarMascotas() throws Exception {
        ResultSet rsMas = null;
        DefaultTableModel modeloM = new DefaultTableModel();
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
            rsMas = objMasco.listarMascotas();
            while (rsMas.next()) {
                String sexoTexto = rsMas.getBoolean("sexo") ? "Macho" : "Hembra";
                String despaTexto = rsMas.getBoolean("desparasitado") ? "SI" : "NO";
                String castTexto = rsMas.getBoolean("esterilizado") ? "SI" : "NO";
                String estado = rsMas.getBoolean("vigencia") ? "VIGENTE" : "NO VIGENTE";
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
                    mostrarEstado(rsMas.getString("estado_salud")),
                    estado,
                    rsMas.getString("raza_nombre"),});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al listar Mascotas: " + e.getMessage());
            listarMascotas();
        }
    }

    private void listarNombreRaza() {
        ResultSet rsRazas = null;
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        cbxRazita.setModel(modelo);
        try {
            if (!cbxEspeciee.getSelectedItem().toString().equals("-Ninguno-")) {
                int idEspecie = objEsp.obtenerIdEspecie(cbxEspeciee.getSelectedItem().toString());
                rsRazas = objRaza.listarRazasE(idEspecie);

                while (rsRazas.next()) {
                    modelo.addElement(rsRazas.getString("nombre"));
                }

                if (modelo.getSize() > 0) {
                    cbxRazita.setSelectedIndex(0);
                } else {
                    modelo.addElement("-Sin Razas-");
                    cbxRazita.setSelectedIndex(0);
                }
            } else {
                modelo.addElement("-Sin Razas-");
                cbxRazita.setSelectedIndex(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al listar las razas--->" + e.getMessage());
            e.printStackTrace();
        }
    }

    private void listarNombreEspecie() {
        ResultSet rss = null;
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        modelo.addElement("-Seleccionar-");
        try {

            rss = objEsp.listarEspecies();
            while (rss.next()) {
                modelo.addElement(rss.getString("nombre"));
                cbxEspeciee.setModel(modelo);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al llenar las especie--->" + e.getMessage());
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
        if (cbxSexo.getSelectedItem() == null || cbxSexo.getSelectedItem().toString().equals("-Selecciona-")) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un sexo válido.");
            return false;
        }
        if (cbxRazita.getSelectedItem() == null || cbxRazita.getSelectedItem().toString().equals("-Selecciona-")) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una raza válida.");
            return false;
        }
        if (cbxEspeciee.getSelectedItem() == null || cbxEspeciee.getSelectedItem().toString().equals("-Selecciona-")) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una especie válida.");
            return false;
        }
        if (cmbEstadoSalud.getSelectedItem() == null || cmbEstadoSalud.getSelectedItem().toString().equals("-Selecciona-")) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un estado de salud.");
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

    private void limpiarCampos() throws SQLException, Exception {
        txtId.setText("");
        txtNombre.setText("");
        txtAltura.setText("");
        txtPeso.setText("");
        txtNotaAdicional.setText("");
        dtcFechaNacimiento.setDate(null);
        cbxSexo.setEditable(false);
        cbxEspeciee.setEditable(false);
        cbxRazita.setEditable(false);
        cmbEstadoSalud.setEditable(false);
        chkCastrado.setEnabled(false);
        chkEstado.setSelected(false);
        chkDesparasitado.setSelected(false);
        chkCastrado.setSelected(false);
        chkDesparasitado.setEnabled(false);
        listarMascotas();
        inicializarComboSexo();
        listarNombreEspecie();
        listarNombreRaza();
        inicializarComboEstado();
    }

    private void mostrarCampos() throws Exception {

        try {

            ResultSet rsBus = objMasco.buscarMascota(Integer.parseInt(txtId.getText()));

            if (rsBus.next()) {
                txtNombre.setText(rsBus.getString("nombre"));
                txtId.setText(rsBus.getString("id"));
                cbxSexo.getSelectedItem().toString();
                cbxSexo.setSelectedItem(getSexoString(rsBus.getBoolean("sexo")));
                txtAltura.setText(String.valueOf(rsBus.getDouble("altura")));
                txtPeso.setText(String.valueOf(rsBus.getDouble("peso")));
                cbxRazita.setSelectedItem(rsBus.getString("raza_nombre"));
                cbxEspeciee.setSelectedItem(rsBus.getString("especie_nombre"));
                txtNotaAdicional.setText(rsBus.getString("notaAdicional"));
                chkCastrado.setSelected(rsBus.getBoolean("esterilizado"));
                chkDesparasitado.setSelected(rsBus.getBoolean("desparasitado"));
                cmbEstadoSalud.setSelectedItem(mostrarEstado(rsBus.getString("estado_salud")));
                chkEstado.setSelected(rsBus.getBoolean("vigencia"));
                dtcFechaNacimiento.setDate(rsBus.getDate("fecha_nacimiento"));
                sinEditarP();
            } else {
                limpiarCampos();
                sinEditarP();
                listarMascotas();
            }

        } catch (Exception e) {
        }
    }

    private void sinEditar() {
        txtId.setText("");
        txtNombre.setEditable(false);
        dtcFechaNacimiento.setEnabled(false);
        txtAltura.setEditable(false);
        txtPeso.setEditable(false);
        txtNotaAdicional.setEditable(false);
        cbxSexo.setEditable(false);
        cbxEspeciee.setEnabled(false);
        cbxRazita.setEnabled(false);
        chkCastrado.setEnabled(false);
        chkEstado.setEnabled(false);
        chkDesparasitado.setEnabled(false);
        cmbEstadoSalud.setEnabled(false);
    }

    private void sinEditarP() {
        dtcFechaNacimiento.setEnabled(false);
        txtAltura.setEditable(false);
        txtPeso.setEditable(false);
        txtNotaAdicional.setEditable(false);
        cbxSexo.setEnabled(false);
        cbxEspeciee.setEnabled(false);
        cbxRazita.setEnabled(false);
        chkCastrado.setEnabled(false);
        chkEstado.setEnabled(false);
        chkDesparasitado.setEnabled(false);
        cmbEstadoSalud.setEnabled(false);
    }

    private void Editar() {
        txtNombre.setEditable(true);
        dtcFechaNacimiento.setEnabled(true);
        txtAltura.setEditable(true);
        txtPeso.setEditable(true);
        txtNotaAdicional.setEditable(true);
        cbxSexo.setEnabled(true);
        cbxEspeciee.setEnabled(true);
        cbxRazita.setEnabled(true);
        chkCastrado.setEnabled(true);
        chkDesparasitado.setEnabled(true);
        cmbEstadoSalud.setEnabled(true);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarD;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnVigencia;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbxEspeciee;
    private javax.swing.JComboBox<String> cbxRazita;
    private javax.swing.JComboBox<String> cbxSexo;
    private javax.swing.JCheckBox chkCastrado;
    private javax.swing.JCheckBox chkDesparasitado;
    private javax.swing.JCheckBox chkEstado;
    private javax.swing.JComboBox<String> cmbEstadoSalud;
    private com.toedter.calendar.JDateChooser dtcFechaNacimiento;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JPanel jPanel7;
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

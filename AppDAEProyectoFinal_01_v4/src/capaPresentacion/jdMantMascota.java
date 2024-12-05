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

public class jdMantMascota extends javax.swing.JDialog {

    private static jdMantMascota instanciaUnica;

    clsMascota objMasco = new clsMascota();
    clsRaza objRaza = new clsRaza();
    clsEspecie objEsp = new clsEspecie();
    Integer IdEspecie = -1;

    public jdMantMascota(java.awt.Frame parent, boolean modal) throws SQLException, Exception {
        super(parent, modal);
        initComponents();
        sinEditarP();
        txtID.requestFocus();
        listarMascotas();

    }

    public static jdMantMascota getInstance(Frame parent, boolean modal) throws Exception {
        if (instanciaUnica == null) {
            instanciaUnica = new jdMantMascota(parent, modal);
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
        btnBuscarNombre = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        cbxRazita = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtID = new javax.swing.JTextField();
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(392, 392, 392))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        dtcFechaNacimiento.setDateFormatString("dd/MM/yyyy");
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

        btnBuscarNombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/buscar.png"))); // NOI18N
        btnBuscarNombre.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnBuscarNombre.setBorderPainted(false);
        btnBuscarNombre.setContentAreaFilled(false);
        btnBuscarNombre.setName(""); // NOI18N
        btnBuscarNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarNombreActionPerformed(evt);
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
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLimpiar)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbxEspeciee, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtAltura, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                                    .addComponent(txtPeso)
                                    .addComponent(txtNombre))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addGap(18, 18, 18)
                                            .addComponent(btnBuscarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                            .addGap(36, 36, 36)
                                            .addComponent(jLabel17)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(jLabel16)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbEstadoSalud, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxRazita, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dtcFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(dtcFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addContainerGap(11, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6))
                                        .addGap(15, 15, 15))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(btnBuscarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel11)
                            .addComponent(jLabel15)
                            .addComponent(cbxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel10)
                            .addComponent(jLabel18)
                            .addComponent(cmbEstadoSalud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxEspeciee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(cbxRazita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/refugio-de-animales.png"))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(138, 238, 238));

        txtID.setBorder(null);
        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });
        txtID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIDKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel4.setText("ID:");

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(138, 238, 238));

        btnModificar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/editar.png"))); // NOI18N
        btnModificar.setText("Modificar Mascota");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnAgregarD.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnAgregarD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/cuidado-de-mascotas.png"))); // NOI18N
        btnAgregarD.setText("Asignar Dueño");
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
                .addGap(17, 17, 17)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAgregarD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVigencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(9, 9, 9)
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
        chkEstado.setText("Vigente");

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
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(48, 48, 48)
                .addComponent(chkEstado)
                .addGap(86, 86, 86)
                .addComponent(chkCastrado)
                .addGap(62, 62, 62)
                .addComponent(chkDesparasitado)
                .addGap(215, 215, 215))
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
                .addGap(13, 13, 13)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbEstadoSaludActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEstadoSaludActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbEstadoSaludActionPerformed

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        try {
            listarMascotas();
            inicializarComboSexo();
            listarNombreEspecie();
            listarNombreRaza();
            inicializarComboEstado();
        } catch (SQLException ex) {
            Logger.getLogger(jdMantMascota.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(jdMantMascota.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowOpened

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        try {
            if (btnRegistrar.getText().equals("Registrar Mascota")) {
                btnRegistrar.setText("Guardar Datos");
                btnEliminar.setText("Cancelar");
                limpiarCampos();
                txtID.setText(objMasco.generarCodigoMascota().toString());
                txtNombre.requestFocus();
                btnBuscar.setEnabled(false);
                btnModificar.setEnabled(false);
                btnLimpiar.setEnabled(false);
                btnAgregarD.setEnabled(false);
                btnBuscarNombre.setEnabled(false);
                txtID.setEditable(false);
                //  btnEliminar.setEnabled(false);
                btnVigencia.setEnabled(false);
                chkEstado.setEnabled(false);
                chkEstado.setSelected(true);
                txtNotaAdicional.setEditable(true);
                tblMascota.setEnabled(false);
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
                            Integer.parseInt(txtID.getText()),
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
                    tblMascota.setEnabled(true);
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
                btnEliminar.setText("Eliminar");
                btnBuscar.setEnabled(true);
                btnModificar.setEnabled(true);
                btnBuscarNombre.setEnabled(true);
                // btnEliminar.setEnabled(true);
                btnVigencia.setEnabled(true);
                btnLimpiar.setEnabled(true);
                btnAgregarD.setEnabled(true);
                txtID.setEditable(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error interno al registrar la mascota: " + e.getMessage());
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void tblMascotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMascotaMouseClicked
        // TODO add your handling code here:
        try {
            txtID.setText(String.valueOf(tblMascota.getValueAt(tblMascota.getSelectedRow(), 0)));
            mostrarCampos();
            if (btnModificar.getText().equalsIgnoreCase("Guardar Datos")) {
                Editar();
            }

//            if (tblMascota.getValueAt(tblMascota.getSelectedRow(), 10).equals(Utilidad.VIGENCIA_SI)) {
//                btnVigencia.setText("Dar baja");
//                btnVigencia.setIcon(new ImageIcon(getClass().getResource("/conector/Recursos/darBaja.png")));
//            }
//            else {
//                btnVigencia.setText("Dar alta");
//                btnVigencia.setIcon(new ImageIcon(getClass().getResource("/conector/Recursos/darAlta.png")));
//            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Debe finalizar la operacion actual primero");
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
//        Object[] opciones = {"Sí", "No"};
//        int respuesta = JOptionPane.showOptionDialog(null,
//                "¿Estás seguro que deseas modificar la información de la mascota?",
//                "Confirmar",
//                JOptionPane.YES_NO_OPTION,
//                JOptionPane.QUESTION_MESSAGE,
//                null,
//                opciones,
//                opciones[0]);
//        int respuesta = Utilidad.mensajeConfirmarModificar("mascota", Integer.parseInt(txtId.getText()), txtNombre.getText());
//        if (respuesta == 0) {
        try {
            if (txtID.getText().isBlank()) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un elemento a modificar.");
//                return;
            } else {
                if (Utilidad.verificarElementoParaUso("mascota", "vigencia", Integer.parseInt(txtID.getText()))) {
                    Utilidad.mensajeElementoNoVigente("mascota", txtNombre.getText());
                } else {
                    if (btnModificar.getText().equals("Modificar Mascota")) {
                        btnModificar.setText("Guardar Datos");
                        btnEliminar.setText("Cancelar");

                        btnBuscar.setEnabled(false);
                        btnLimpiar.setEnabled(false);
                        btnRegistrar.setEnabled(false);
                        btnAgregarD.setEnabled(false);
                        txtID.setEditable(false);
                        btnBuscarNombre.setEnabled(false);
                        //  btnEliminar.setEnabled(false);
                        btnVigencia.setEnabled(false);
                        chkEstado.setEnabled(false);
                        tblMascota.setEnabled(false);
                        Editar();
                    } else {
                        int respuesta = Utilidad.mensajeConfirmarModificar("mascota", Integer.parseInt(txtID.getText()), txtNombre.getText());
                        if (respuesta == 0) {
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
                            int idMascota = Integer.parseInt(txtID.getText().trim());
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
                                tblMascota.setEnabled(true);
                                btnModificar.setText("Modificar Mascota");
                                btnEliminar.setText("Eliminar");
                                btnBuscar.setEnabled(true);
                                txtID.setEditable(true);
                                btnLimpiar.setEnabled(true);
                                btnBuscarNombre.setEnabled(true);
                                btnRegistrar.setEnabled(true);
                                btnEliminar.setEnabled(true);
                                btnVigencia.setEnabled(true);
                                btnAgregarD.setEnabled(true);
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(this, "Error al modificar la mascota: " + e.getMessage());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al modificar la mascota: " + e.getMessage());
        }
//        }

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
//        jdMantDuenio objForm = new jdMantDuenio(null, true);
//        objForm.setLocationRelativeTo(this);
//        objForm.setVisible(true);
//        this.dispose();
        jdCustodia1 objForm;
        try {
            objForm = new jdCustodia1(null, true);
            objForm.setLocationRelativeTo(this);
            objForm.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al mostrar interfaz de Custodia: " + ex);
        }
    }//GEN-LAST:event_btnAgregarDActionPerformed

    private void btnRazaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRazaActionPerformed
        jdMantDuenio objForm = new jdMantDuenio(null, true);
        objForm.setLocationRelativeTo(this);
        objForm.setVisible(true);
    }//GEN-LAST:event_btnRazaActionPerformed

    private void btnBuscarNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarNombreActionPerformed
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
                        ? rsMas.getDate("fecha_nacimiento").toString() : "";

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
                Logger.getLogger(jdMantMascota.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(jdMantMascota.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_btnBuscarNombreActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isLetter(c) && c != ' ') {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtIDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtIDKeyTyped

    private void txtPesoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoKeyTyped
        char c = evt.getKeyChar();
        String texto = ((javax.swing.JTextField) evt.getSource()).getText();
        if (!Character.isDigit(c) && c != '.') {
            evt.consume();
        }
        if (c == '.' && texto.contains(".")) {
            evt.consume();
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
            evt.consume();
        }
        if (c == '.' && texto.contains(".")) {
            evt.consume();
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
            Logger.getLogger(jdMantMascota.class.getName()).log(Level.SEVERE, null, ex);
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
//        DefaultTableModel modeloM = new DefaultTableModel();
//        modeloM.addColumn("Id");
//        modeloM.addColumn("Nombre");
//        modeloM.addColumn("Fecha Nacimiento");
//        modeloM.addColumn("Altura");
//        modeloM.addColumn("Peso");
//        modeloM.addColumn("Sexo");
//        modeloM.addColumn("Nota Adicional");
//        modeloM.addColumn("Esterilizado");
//        modeloM.addColumn("Desparasitado");
//        modeloM.addColumn("Condición");
//        modeloM.addColumn("Estado");
//        modeloM.addColumn("Raza");
//        tblMascota.setModel(modeloM);
        try {
            if (txtID.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe completar el campo de búsqueda.");
                listarMascotas();
                return;
            } else {
                for (int i = 0; i < tblMascota.getRowCount(); i++) {
                    String valorCodigo = tblMascota.getValueAt(i, 0).toString();
                    if (valorCodigo.equals(txtID.getText())) {
                        tblMascota.setRowSelectionInterval(i, i);
                        tblMascota.scrollRectToVisible(tblMascota.getCellRect(i, 0, true));
                        break;
                    }
                }
                rsMas = objMasco.buscarMascota(Integer.parseInt(txtID.getText()));
                boolean hayResultados = false;
                if (rsMas.next()) {
                    mostrarCampos();
                    rsMas.close();
                } else {
                    JOptionPane.showMessageDialog(this, "Esta ID de mascota no existe");
                    limpiarCampos();
                }
//                while (rsMas.next()) {
//                    hayResultados = true;
//                    String sexoTexto = rsMas.getBoolean("sexo") ? "Macho" : "Hembra";
//                    String despaTexto = rsMas.getBoolean("desparasitado") ? "SI" : "NO";
//                    String castTexto = rsMas.getBoolean("esterilizado") ? "SI" : "NO";
//                    String estado = rsMas.getBoolean("vigencia") ? "VIGENTE" : "NO VIGENTE";
//                    String fechaNacimiento = rsMas.getDate("fecha_nacimiento") != null
//                            ? rsMas.getDate("fecha_nacimiento").toString() : "Sin fecha";
//
//                    modeloM.addRow(new Object[]{
//                        rsMas.getInt("id"),
//                        rsMas.getString("nombre"),
//                        fechaNacimiento,
//                        rsMas.getString("altura"),
//                        rsMas.getString("peso"),
//                        sexoTexto,
//                        rsMas.getString("notaAdicional"),
//                        castTexto,
//                        despaTexto,
//                        mostrarEstado(rsMas.getString("estado_salud")),
//                        estado,
//                        rsMas.getString("raza_nombre"),}
//                    );
//                }

//                if (!hayResultados) {
//                    JOptionPane.showMessageDialog(this, "No se encontraron mascotas con ese id.");
//                    listarMascotas();
//                    inicializarComboSexo();
//                    listarNombreEspecie();
//                    listarNombreRaza();
//                    inicializarComboEstado();
//                }
            }

        } catch (Exception e) {
            try {
                JOptionPane.showMessageDialog(this, "Error al listar Mascotas: " + e.getMessage());
                listarMascotas();
            } catch (SQLException ex) {
                Logger.getLogger(jdMantMascota.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(jdMantMascota.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnVigenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVigenciaActionPerformed
        // TODO add your handling code here:
        if (txtID.getText().isBlank()) {
            Utilidad.mensajeErrorFaltaID(this);
        } else {
            try {
                if (Utilidad.verificarElementoParaUso("mascota", "vigencia", Integer.parseInt(txtID.getText()))) {
                    Utilidad.mensajeElementoNoVigente("mascota", txtNombre.getText());
                } else {
                    darBaja();
                    if (tblMascota.getValueAt(tblMascota.getSelectedRow(), 10).equals("Vigente")) {
                        darBaja();
                    } else {
                        darAlta();
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(jdMantMascota.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnVigenciaActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        if (btnRegistrar.getText().equals("Guardar Datos") || btnModificar.getText().equals("Guardar Datos")) {
            try {
                limpiarCampos();
                listarMascotas();
                btnBuscar.setEnabled(true);
                btnModificar.setEnabled(true);
                // btnEliminar.setEnabled(true);
                btnVigencia.setEnabled(true);
                btnLimpiar.setEnabled(true);
                btnAgregarD.setEnabled(true);
                btnRegistrar.setEnabled(true);
                txtID.setEditable(true);
                btnRegistrar.setText("Registrar Mascota");
                tblMascota.setEnabled(true);
                btnModificar.setText("Modificar Mascota");
                sinEditarP();
                btnEliminar.setText("Eliminar");

            } catch (Exception ex) {
                Logger.getLogger(jdMantMascota.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            eliminarMascota();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void eliminarMascota() {
        try {
            int selectedRow = tblMascota.getSelectedRow();
            if (selectedRow == -1) {
                Utilidad.mensajeErrorFaltaID(this);
                return;
            }
            int idMascota = Integer.parseInt(tblMascota.getValueAt(selectedRow, 0).toString()); // Suponiendo que el ID está en la primera columna

            if (txtID.getText().isBlank()) {
                Utilidad.mensajeErrorFaltaID(this);
            } else if (Utilidad.verificarElementoParaUso("mascota", "vigencia", Integer.parseInt(txtID.getText()))) {
                Utilidad.mensajeElementoNoVigente("mascota", txtNombre.getText());
            } else if (Utilidad.validarEliminacionForanea("mascota", idMascota)) {
                JOptionPane.showMessageDialog(this,
                        "Hay datos externos asociados a la mascota \"" + txtNombre.getText() + "\".\n"
                        + "Considere cambiar su vigencia para que ya no pueda ser usado.");
            } else {
                int valor = Utilidad.mensajeConfirmarEliminar("mascota", Integer.parseInt(txtID.getText()), txtNombre.getText());
                if (valor == 0) {
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

    private void darBaja() {
        try {
            Integer id = Integer.parseInt(txtID.getText());
            ResultSet rsUsuario = null;
            if (txtID.getText().isBlank()) {
                Utilidad.mensajeErrorFaltaID(this);
            } else {
                int valor = Utilidad.mensajeConfirmarVigencia("mascota", Integer.parseInt(txtID.getText()), txtNombre.getText());
                if (valor == 0) {
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
            Integer id = Integer.parseInt(txtID.getText());
            ResultSet rsUsuario = null;
            if (id.equals("")) {
                JOptionPane.showConfirmDialog(this, "Debe ingresar un codigo");
            } else {
                int valor = Utilidad.mensajeConfirmarVigencia("Usuario", Integer.parseInt(txtID.getText()), txtNombre.getText());
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

        try {
            rsMas = objMasco.listarMascotas();
            while (rsMas.next()) {
                String sexoTexto = rsMas.getBoolean("sexo") ? "Macho" : "Hembra";
                String despaTexto = rsMas.getBoolean("desparasitado") ? "SI" : "NO";
                String castTexto = rsMas.getBoolean("esterilizado") ? "SI" : "NO";
                String estado = rsMas.getBoolean("vigencia") ? Utilidad.VIGENCIA_SI : Utilidad.VIGENCIA_NO;
                String fechaNacimiento = rsMas.getDate("fecha_nacimiento") != null
                        ? rsMas.getDate("fecha_nacimiento").toString() : "";

                modeloM.addRow(new Object[]{
                    rsMas.getInt("id"),
                    rsMas.getString("nombre"),
                    Utilidad.textoFormatoFecha(fechaNacimiento),
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

        tblMascota.setModel(modeloM);
        Utilidad.alineacionDerechaColumnaTabla(tblMascota, 0, 50);
        Utilidad.alineacionDerechaColumnaTabla(tblMascota, 3, 50);
        Utilidad.alineacionDerechaColumnaTabla(tblMascota, 4, 50);
        Utilidad.tamañoColumnaTablaxPos(tblMascota, 0, 50);
        Utilidad.tamañoColumnaTablaxPos(tblMascota, 3, 50);
        Utilidad.tamañoColumnaTablaxPos(tblMascota, 4, 50);
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

        if (txtID.getText().isEmpty() || txtNombre.getText().isEmpty() || txtPeso.getText().isEmpty() || txtAltura.getText().isEmpty()) {
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
            JOptionPane.showMessageDialog(null, "Debe seleccionar una condición de salud.");
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
        txtID.setText("");
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
        tblMascota.setEnabled(true);
        listarMascotas();
        inicializarComboSexo();
        listarNombreEspecie();
        listarNombreRaza();
        inicializarComboEstado();
    }

    private void mostrarCampos() throws Exception {

        try {
            ResultSet rsBus = objMasco.buscarMascota(Integer.parseInt(txtID.getText()));
            if (rsBus.next()) {
                txtNombre.setText(rsBus.getString("nombre"));
                txtID.setText(rsBus.getString("id"));
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
    private javax.swing.JButton btnBuscarNombre;
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
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextArea txtNotaAdicional;
    private javax.swing.JTextField txtPeso;
    // End of variables declaration//GEN-END:variables
}

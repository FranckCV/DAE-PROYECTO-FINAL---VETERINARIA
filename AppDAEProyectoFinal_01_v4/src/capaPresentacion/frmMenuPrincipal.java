/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package capaPresentacion;

import capaNegocio.*;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author franc
 */
public class frmMenuPrincipal extends javax.swing.JFrame {

    private String IMG_ICON = "logo_marmota.png";
    clsUsuario objUsuario = new clsUsuario();

    /**
     * Creates new form frmMenuPrincipal
     */
    public frmMenuPrincipal() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        setIconImage(getIconImage());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @Override

    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("conector/Recursos/" + IMG_ICON));
        return retValue;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenuItem26 = new javax.swing.JMenuItem();
        panel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCargo = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnMascota = new javax.swing.JButton();
        btnDueño = new javax.swing.JButton();
        btnVacunacion = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        btnCita = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        mnuCambiarUsuario = new javax.swing.JMenuItem();
        mnuMantenimiento = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        mnuMascota = new javax.swing.JMenuItem();
        mnuDueño = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        mnuMedico = new javax.swing.JMenuItem();
        mnuMedicamento = new javax.swing.JMenuItem();
        mnuServicio = new javax.swing.JMenuItem();
        mnuVacuna = new javax.swing.JMenuItem();
        mnuUsuarios = new javax.swing.JMenuItem();
        mnuInfoAdicional = new javax.swing.JMenu();
        mnuEstadoCita = new javax.swing.JMenuItem();
        mnuTipoMedicamento = new javax.swing.JMenuItem();
        mnuEspecialidad = new javax.swing.JMenuItem();
        mnuEspecie = new javax.swing.JMenuItem();
        mnuRazas = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        mnuCita = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenuItem29 = new javax.swing.JMenuItem();
        jMenuItem30 = new javax.swing.JMenuItem();
        jMenuItem31 = new javax.swing.JMenuItem();
        jMenuItem33 = new javax.swing.JMenuItem();
        jMenuItem34 = new javax.swing.JMenuItem();
        jMenuItem35 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        mnuCustodia = new javax.swing.JMenuItem();

        jMenuItem19.setText("jMenuItem19");

        jMenuItem21.setText("jMenuItem21");

        jMenuItem23.setText("jMenuItem23");

        jMenuItem26.setText("jMenuItem26");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panel.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/marmota.png"))); // NOI18N

        txtCargo.setEditable(false);
        txtCargo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCargo.setBorder(null);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(97, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        btnMascota.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnMascota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/mascota_inicio.png"))); // NOI18N
        btnMascota.setText("Mascota");
        btnMascota.setBorder(null);
        btnMascota.setContentAreaFilled(false);
        btnMascota.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnMascota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMascotaActionPerformed(evt);
            }
        });

        btnDueño.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnDueño.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/cuidado-de-mascotas.png"))); // NOI18N
        btnDueño.setText("Dueño");
        btnDueño.setBorder(null);
        btnDueño.setContentAreaFilled(false);
        btnDueño.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnDueño.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDueñoActionPerformed(evt);
            }
        });

        btnVacunacion.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnVacunacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/vacunacion.png"))); // NOI18N
        btnVacunacion.setText("Vacunación");
        btnVacunacion.setBorder(null);
        btnVacunacion.setContentAreaFilled(false);
        btnVacunacion.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        btnCita.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnCita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/cita.png"))); // NOI18N
        btnCita.setText("Agenda tu cita");
        btnCita.setBorder(null);
        btnCita.setContentAreaFilled(false);
        btnCita.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCitaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel2.setText("Usuario:");

        txtUsuario.setEditable(false);
        txtUsuario.setBackground(new java.awt.Color(204, 204, 204));
        txtUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUsuario.setBorder(null);
        txtUsuario.setDisabledTextColor(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(btnMascota, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnDueño, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnVacunacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
            .addComponent(btnCita, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(btnMascota, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDueño)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnVacunacion)
                .addGap(0, 0, 0)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnCita)
                .addGap(47, 47, 47)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(204, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 48)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 102));
        jLabel7.setText("Veterinaria Marmota");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(229, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(41, 41, 41))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel7)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/vet.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jMenuBar1.setBorderPainted(false);
        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jMenuBar1.setDoubleBuffered(true);
        jMenuBar1.setEnabled(false);

        jMenu4.setText("Login");

        mnuCambiarUsuario.setText("Cambiar usuario");
        mnuCambiarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuCambiarUsuarioActionPerformed(evt);
            }
        });
        jMenu4.add(mnuCambiarUsuario);

        jMenuBar1.add(jMenu4);

        mnuMantenimiento.setText("Mantenimiento");

        jMenu6.setText("Mascotas Pacientes");

        mnuMascota.setText("Mascotas");
        mnuMascota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuMascotaActionPerformed(evt);
            }
        });
        jMenu6.add(mnuMascota);

        mnuDueño.setText("Dueños");
        mnuDueño.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuDueñoActionPerformed(evt);
            }
        });
        jMenu6.add(mnuDueño);

        mnuMantenimiento.add(jMenu6);

        jMenu5.setText("Informacion de la Clinica");

        mnuMedico.setText("Medicos");
        mnuMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuMedicoActionPerformed(evt);
            }
        });
        jMenu5.add(mnuMedico);

        mnuMedicamento.setText("Medicamentos");
        mnuMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuMedicamentoActionPerformed(evt);
            }
        });
        jMenu5.add(mnuMedicamento);

        mnuServicio.setText("Servicios");
        mnuServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuServicioActionPerformed(evt);
            }
        });
        jMenu5.add(mnuServicio);

        mnuVacuna.setText("Vacunas");
        mnuVacuna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuVacunaActionPerformed(evt);
            }
        });
        jMenu5.add(mnuVacuna);

        mnuUsuarios.setText("Usuarios");
        mnuUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuUsuariosActionPerformed(evt);
            }
        });
        jMenu5.add(mnuUsuarios);

        mnuMantenimiento.add(jMenu5);

        mnuInfoAdicional.setText("Información Adicional");

        mnuEstadoCita.setText("Estados de Citas");
        mnuEstadoCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuEstadoCitaActionPerformed(evt);
            }
        });
        mnuInfoAdicional.add(mnuEstadoCita);

        mnuTipoMedicamento.setText("Tipo de medicamento");
        mnuTipoMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuTipoMedicamentoActionPerformed(evt);
            }
        });
        mnuInfoAdicional.add(mnuTipoMedicamento);

        mnuEspecialidad.setText("Especialidades");
        mnuEspecialidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuEspecialidadActionPerformed(evt);
            }
        });
        mnuInfoAdicional.add(mnuEspecialidad);

        mnuEspecie.setText("Especies");
        mnuEspecie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuEspecieActionPerformed(evt);
            }
        });
        mnuInfoAdicional.add(mnuEspecie);

        mnuRazas.setText("Razas");
        mnuRazas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRazasActionPerformed(evt);
            }
        });
        mnuInfoAdicional.add(mnuRazas);

        mnuMantenimiento.add(mnuInfoAdicional);

        jMenuBar1.add(mnuMantenimiento);

        jMenu1.setText("Citas");

        mnuCita.setText("Programación de Citas");
        mnuCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuCitaActionPerformed(evt);
            }
        });
        jMenu1.add(mnuCita);

        jMenuItem4.setText("Atención de Citas");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setText("Comprobantes de pago");
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu11.setText("Reportes");

        jMenuItem7.setText("Historial Clinico");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem7);

        jMenuItem18.setText("Rp2");
        jMenu11.add(jMenuItem18);

        jMenuItem24.setText("Rp3");
        jMenu11.add(jMenuItem24);

        jMenuItem29.setText("Rp4");
        jMenu11.add(jMenuItem29);

        jMenuItem30.setText("Rp5");
        jMenu11.add(jMenuItem30);

        jMenuItem31.setText("Rp6");
        jMenu11.add(jMenuItem31);

        jMenuItem33.setText("Rp7");
        jMenu11.add(jMenuItem33);

        jMenuItem34.setText("Rp8");
        jMenu11.add(jMenuItem34);

        jMenuItem35.setText("Rp9");
        jMenu11.add(jMenuItem35);

        jMenuBar1.add(jMenu11);

        jMenu2.setText("Operaciones");

        jMenuItem2.setText("Asignación de Vacunación");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        mnuCustodia.setText("Custodia de mascotas");
        mnuCustodia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuCustodiaActionPerformed(evt);
            }
        });
        jMenu2.add(mnuCustodia);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
//         TODO add your handling code here:
//        login();

//        jdInicioSesionVet objForm= new jdInicioSesionVet(this, true);
//        objForm.setLocationRelativeTo(this);
//        objForm.setVisible(true);
//        txtUsuario.setText(objForm.nombreUsuario);
//        txtCargo.setText(objForm.cargo);
    }//GEN-LAST:event_formWindowOpened

    private void btnCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCitaActionPerformed
        jdProgramacionCita objProgramacionCita = new jdProgramacionCita(this, true);
        objProgramacionCita.setLocationRelativeTo(this);
        objProgramacionCita.setVisible(true);
    }//GEN-LAST:event_btnCitaActionPerformed

    private void mnuMascotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuMascotaActionPerformed
        // TODO add your handling code here:
        try {
            // Llamada al constructor que puede lanzar una excepción
            jdMantMascota objForm = new jdMantMascota(null, true);
            objForm.setLocationRelativeTo(this);
            objForm.setVisible(true);
        } catch (Exception e) {
            // Manejo de la excepción
            JOptionPane.showMessageDialog(this, "Error al abrir el formulario de mascota: " + e.getMessage());
        }
        // try {
        //     jdMantMascota objFormq = new jdMantMascota(this, true);
        //     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //     int x = (screenSize.width - objFormq.getWidth()) / 2;
        //     int y = (screenSize.height - objFormq.getHeight()) / 2;
        //     objFormq.setLocation(x, y);
        //     objFormq.setVisible(true);
        // } catch (SQLException ex) {
        //     Logger.getLogger(frmMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        // }
    }//GEN-LAST:event_mnuMascotaActionPerformed

    private void mnuEspecieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuEspecieActionPerformed
        jdMntEspecie objForm = new jdMntEspecie(this, true);
        objForm.setLocationRelativeTo(this);
        objForm.setVisible(true);
    }//GEN-LAST:event_mnuEspecieActionPerformed

    private void mnuRazasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRazasActionPerformed
        // TODO add your handling code here:
        jdMntRaza objForm = new jdMntRaza(this, true);
        objForm.setLocationRelativeTo(this);
    }//GEN-LAST:event_mnuRazasActionPerformed

    private void mnuDueñoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuDueñoActionPerformed
        // TODO add your handling code here:
        jdMantDuenio objForm = new jdMantDuenio(this, true);
        objForm.setLocationRelativeTo(this);
        objForm.setVisible(true);
    }//GEN-LAST:event_mnuDueñoActionPerformed

    private void mnuEspecialidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuEspecialidadActionPerformed
        // TODO add your handling code here:
        jdMantEspecialidad objForm = new jdMantEspecialidad(this, true);
        objForm.setLocationRelativeTo(this);
        objForm.setVisible(true);
    }//GEN-LAST:event_mnuEspecialidadActionPerformed

    private void mnuMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuMedicamentoActionPerformed
        // TODO add your handling code here:
        jdMntMedicamento objForm = new jdMntMedicamento(this, true);
        objForm.setLocationRelativeTo(this);
        objForm.setVisible(true);
    }//GEN-LAST:event_mnuMedicamentoActionPerformed

    private void mnuServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuServicioActionPerformed
        // TODO add your handling code here:
        jdMantServicio objForm = new jdMantServicio(this, true);
        objForm.setLocationRelativeTo(this);
        objForm.setVisible(true);
    }//GEN-LAST:event_mnuServicioActionPerformed

    private void mnuTipoMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuTipoMedicamentoActionPerformed
        // TODO add your handling code here:
        jdMntTipoMedicamento objForm = new jdMntTipoMedicamento(this, true);
        objForm.setLocationRelativeTo(this);
        objForm.setVisible(true);
    }//GEN-LAST:event_mnuTipoMedicamentoActionPerformed

    private void mnuEstadoCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuEstadoCitaActionPerformed
        // TODO add your handling code here:
        jdMntEstadoCita objForm = new jdMntEstadoCita(this, true);

        objForm.setLocationRelativeTo(this);
        objForm.setVisible(true);
    }//GEN-LAST:event_mnuEstadoCitaActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        jdMntVacuna objForm = new jdMntVacuna(this, true);
        objForm.setLocationRelativeTo(this);
        objForm.setVisible(true);
    }

    private void mnuVacunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuVacunaActionPerformed
        // TODO add your handling code here:
        jdMntVacuna objForm = new jdMntVacuna(this, true);
        objForm.setLocationRelativeTo(this);
        objForm.setVisible(true);
    }//GEN-LAST:event_mnuVacunaActionPerformed

    private void btnMascotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMascotaActionPerformed
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
    }//GEN-LAST:event_btnMascotaActionPerformed

    private void btnDueñoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDueñoActionPerformed
        // TODO add your handling code here:
        jdMantDuenio objForm = new jdMantDuenio(this, true);
        objForm.setLocationRelativeTo(this);
        objForm.setVisible(true);
    }//GEN-LAST:event_btnDueñoActionPerformed

    private void mnuCambiarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        txtUsuario.setText("");
        txtCargo.setText("");
        login();
    }                                          

    private void mnuUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuUsuariosActionPerformed
        // TODO add your handling code here:
        jdMantUsuario objForm = new jdMantUsuario(this, true);
        objForm.setLocationRelativeTo(this);
        objForm.setVisible(true);
    }//GEN-LAST:event_mnuUsuariosActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        jdCitaFirme objCita1 = new jdCitaFirme(this, true);
        objCita1.setLocationRelativeTo(this);
        objCita1.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void mnuCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuCitaActionPerformed
        jdProgramacionCita objCita = new jdProgramacionCita(this, true);
        objCita.setLocationRelativeTo(this);
        objCita.setVisible(true);
    }//GEN-LAST:event_mnuCitaActionPerformed

    private void mnuCustodiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuCustodiaActionPerformed
        // TODO add your handling code here:
        jdCustodia objCus = null;
        try {
            objCus = new jdCustodia(this, true);
        } catch (SQLException ex) {
            Logger.getLogger(frmMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        objCus.setLocationRelativeTo(this);
        objCus.setVisible(true);
    }//GEN-LAST:event_mnuCustodiaActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        jsDetalleVacunacion objCita = null;
        try {
            objCita = new jsDetalleVacunacion(this, true);
        } catch (SQLException ex) {
            Logger.getLogger(frmMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        objCita.setLocationRelativeTo(this);
        objCita.setVisible(true);

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        jdRepHistorialClinico frm = new jdRepHistorialClinico(this, true);
        frm.setLocationRelativeTo(this);
        frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void mnuMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuMedicoActionPerformed
        // TODO add your handling code here:
        jdMantMedico objForm = new jdMantMedico(this, true);
        objForm.setLocationRelativeTo(this);
        objForm.setVisible(true); 
    }//GEN-LAST:event_mnuMedicoActionPerformed
                                                                        

    private void login(){ 
        jdInicioSesionVet objForm = new jdInicioSesionVet(this, true);
        objForm.setLocationRelativeTo(this);
        objForm.setVisible(true);
        
        txtUsuario.setText(objForm.nombreUsuario);
        switch (objForm.cargo) {
            case "V":
                txtCargo.setText("Veterinario");
                mnuMantenimiento.setVisible(false);
                mnuUsuarios.setVisible(false);
                mnuInfoAdicional.setVisible(false);
                break;
            case "E":
                txtCargo.setText("Empleado");
                mnuMantenimiento.setVisible(true);
                mnuUsuarios.setVisible(false);
                mnuInfoAdicional.setVisible(false);
                break;
            case "A":
                txtCargo.setText("Administrador");
                mnuMantenimiento.setVisible(true);
                mnuUsuarios.setVisible(true);
                mnuInfoAdicional.setVisible(true);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Error al obtener puesto");
                break;
        }
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCita;
    private javax.swing.JButton btnDueño;
    private javax.swing.JButton btnMascota;
    private javax.swing.JButton btnVacunacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem29;
    private javax.swing.JMenuItem jMenuItem30;
    private javax.swing.JMenuItem jMenuItem31;
    private javax.swing.JMenuItem jMenuItem33;
    private javax.swing.JMenuItem jMenuItem34;
    private javax.swing.JMenuItem jMenuItem35;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JMenuItem mnuCambiarUsuario;
    private javax.swing.JMenuItem mnuCita;
    private javax.swing.JMenuItem mnuCustodia;
    private javax.swing.JMenuItem mnuDueño;
    private javax.swing.JMenuItem mnuEspecialidad;
    private javax.swing.JMenuItem mnuEspecie;
    private javax.swing.JMenuItem mnuEstadoCita;
    private javax.swing.JMenu mnuInfoAdicional;
    private javax.swing.JMenu mnuMantenimiento;
    private javax.swing.JMenuItem mnuMascota;
    private javax.swing.JMenuItem mnuMedicamento;
    private javax.swing.JMenuItem mnuMedico;
    private javax.swing.JMenuItem mnuRazas;
    private javax.swing.JMenuItem mnuServicio;
    private javax.swing.JMenuItem mnuTipoMedicamento;
    private javax.swing.JMenuItem mnuUsuarios;
    private javax.swing.JMenuItem mnuVacuna;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField txtCargo;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}

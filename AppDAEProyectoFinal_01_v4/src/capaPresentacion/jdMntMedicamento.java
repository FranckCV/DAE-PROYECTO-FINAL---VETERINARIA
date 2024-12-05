/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package capaPresentacion;

import soporte.*;
import capaNegocio.clsMedicamento;
import capaNegocio.clsTipoMedicamento;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author Leonardo Guzmán
 */
public class jdMntMedicamento extends javax.swing.JDialog {

    clsMedicamento objMedicamento = new clsMedicamento();
    clsTipoMedicamento objTipoMedicamento = new clsTipoMedicamento();

    /**
     * Creates new form jdMntMedicamento
     */
    public jdMntMedicamento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Utilidad.validarSpinnerNumerosPositivos(spnStock);
        Utilidad.validacionTabla(tblMedicamento, modal, rootPaneCheckingEnabled, modal);
        tblMedicamento.revalidate();
        tblMedicamento.repaint();
        listarMedicamentos();
        listarTipoMedicamento();
        btnRegistrar.setText(Utilidad.BTN_NUEVO);
        btnModificar.setText(Utilidad.BTN_MODIFICAR);
        btnEliminar.setText(Utilidad.BTN_ELIMINAR);
        chkVigencia.setSelected(true);

    }

    public void listarMedicamentos() {
        ResultSet rsMed = null;
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Costo");
        modelo.addColumn("Stock");
        modelo.addColumn("Presentación");
        modelo.addColumn("Disponible");
        modelo.addColumn("Tipo Medicamento");
        tblMedicamento.setModel(modelo);
        Utilidad.alineacionDerechaColumnaTabla(tblMedicamento, 0 );
        Utilidad.alineacionDerechaColumnaTabla(tblMedicamento, 2 );
        Utilidad.alineacionDerechaColumnaTabla(tblMedicamento, 3 );
        Utilidad.tamañoColumnaTablaxPos(tblMedicamento, 0, 20);
        Utilidad.tamañoColumnaTablaxPos(tblMedicamento, 2, 35);
        Utilidad.tamañoColumnaTablaxPos(tblMedicamento, 3, 35);
        try {
            rsMed = objMedicamento.listarMedicamentos();
            while (rsMed.next()) {
                boolean vigencia = rsMed.getBoolean("vigencia");
                String disponibilidad = vigencia ? "Disponible" : "No disponible";
                modelo.addRow(new Object[]{
                    rsMed.getInt("id"),
                    rsMed.getString("nombre"),
                    rsMed.getDouble("costo"),
                    rsMed.getInt("stock"),
                    rsMed.getString("presentacion"),
                    disponibilidad,
                    rsMed.getString("tipo_medicamento")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al listar medicamentos: " + e.getMessage());
        }
    }

    public void listarTipoMedicamento() {
        ResultSet rsTipoMed = null;
        DefaultComboBoxModel modeloTipoMed = new DefaultComboBoxModel();
        cbxTipoMedicamento.setModel(modeloTipoMed);
        try {
            rsTipoMed = objTipoMedicamento.listarTiposMedicamentos();
            while (rsTipoMed.next()) {

                modeloTipoMed.addElement(rsTipoMed.getString("nomtipo"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al listar tipos de medicamento: " + e.getMessage());
        }
    }

    private void usarBotonesMedicamento(boolean buscar, boolean registrar, boolean modificar, boolean eliminar, boolean limpiar, boolean disponibilidad) {
        btnBuscar.setEnabled(buscar);
        btnRegistrar.setEnabled(registrar);
        btnModificar.setEnabled(modificar);
        btnEliminar.setEnabled(eliminar);
        btnLimpiar.setEnabled(limpiar);
        btnDisponibilidad.setEnabled(disponibilidad);
    }

    private void editableControlesMedicamento(boolean id, boolean nombre, boolean costo, boolean tipo, boolean stock, boolean vigencia, boolean presentacion) {
        txtId.setEditable(id);
        txtNombre.setEditable(nombre);
        txtCosto.setEditable(costo);
        cbxTipoMedicamento.setEnabled(tipo);
        spnStock.setEnabled(stock);
        chkVigencia.setEnabled(vigencia);
        txtPresentacion.setEditable(presentacion);
    }

    private void cancelarAccionMedicamento() {
        btnRegistrar.setText(Utilidad.BTN_NUEVO);
        btnModificar.setText(Utilidad.BTN_MODIFICAR);
        btnEliminar.setText(Utilidad.BTN_ELIMINAR);
        usarBotonesMedicamento(true, true, true, true, true, true);
        limpiarControles();
        editableControlesMedicamento(true, false, false, false, false, false, false);
        listarMedicamentos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnRegistrar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMedicamento = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cbxTipoMedicamento = new javax.swing.JComboBox<>();
        spnStock = new javax.swing.JSpinner();
        txtCosto = new javax.swing.JTextField();
        txtPresentacion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        chkVigencia = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnDisponibilidad = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panel.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(204, 255, 255));

        btnRegistrar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/veterinario.png"))); // NOI18N
        btnRegistrar.setText("Registrar");
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

        btnEliminar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/eliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        tblMedicamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMedicamentoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMedicamento);

        jPanel4.setBackground(new java.awt.Color(138, 238, 238));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel6.setText("Nombre:");

        txtNombre.setBorder(null);
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/opacado.png"))); // NOI18N
        btnLimpiar.setBorder(null);
        btnLimpiar.setContentAreaFilled(false);
        btnLimpiar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/opacado.png"))); // NOI18N
        btnLimpiar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/escoba-mascota.png"))); // NOI18N
        btnLimpiar.setVerifyInputWhenFocusTarget(false);
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel7.setText("Costo(s/.) :");

        jLabel11.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel11.setText("Stock:");

        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel13.setText("Presentación:");

        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel14.setText("Tipo de medicamento:");

        cbxTipoMedicamento.setBorder(null);

        spnStock.setBorder(null);
        spnStock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                spnStockKeyTyped(evt);
            }
        });

        txtCosto.setBorder(null);
        txtCosto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCostoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCostoKeyTyped(evt);
            }
        });

        txtPresentacion.setBorder(null);
        txtPresentacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPresentacionKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel3.setText("Disponibilidad:");

        chkVigencia.setText("(Disponible)");
        chkVigencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkVigenciaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(chkVigencia)
                        .addGap(59, 59, 59)
                        .addComponent(btnLimpiar))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbxTipoMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(spnStock, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPresentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cbxTipoMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(spnStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPresentacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(chkVigencia))
                    .addComponent(btnLimpiar))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/medicamento.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        jPanel2.setBackground(new java.awt.Color(138, 238, 238));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel4.setText("ID:");

        txtId.setBorder(null);
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdKeyPressed(evt);
            }
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addComponent(btnBuscar))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        btnDisponibilidad.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnDisponibilidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/disponible.png"))); // NOI18N
        btnDisponibilidad.setText("Disponibilidad ");
        btnDisponibilidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisponibilidadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnDisponibilidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRegistrar)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDisponibilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 102));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Medicamento");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(261, 261, 261)
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreKeyTyped

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
        try {
            if (btnRegistrar.getText().equals(Utilidad.BTN_NUEVO)) {
                btnRegistrar.setText(Utilidad.BTN_GUARDAR);
                btnEliminar.setText(Utilidad.BTN_CANCELAR);
                limpiarControles();
                editableControlesMedicamento(true, true, true, true, true, false, true);
                txtId.setText(objMedicamento.generarCodigoMedicamento().toString());
                usarBotonesMedicamento(false, true, false, true, false, false);
                txtNombre.requestFocus();

            } else {
                // Validación de campos
                if (txtNombre.getText().trim().isEmpty() || txtCosto.getText().trim().isEmpty() || txtPresentacion.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe llenar todos los campos");
                    return;
                }

                if (objMedicamento.existeNombreMedicamento(txtNombre.getText())) {
                    JOptionPane.showMessageDialog(this, "El nombre del medicamento ya está registrado. Elija un nombre diferente.");
                    return;
                } else {
                    btnRegistrar.setText(Utilidad.BTN_NUEVO);
                    btnEliminar.setText(Utilidad.BTN_ELIMINAR);

                    // Registro del medicamento
                    objMedicamento.registrarMedicamento(
                            Integer.parseInt(txtId.getText()),
                            txtNombre.getText(),
                            Double.parseDouble(txtCosto.getText()),
                            (int) spnStock.getValue(),
                            txtPresentacion.getText(),
                            chkVigencia.isSelected(),
                            objTipoMedicamento.obtenerCodigoTipoMedicamento(cbxTipoMedicamento.getSelectedItem().toString())
                    );

                    editableControlesMedicamento(true, true, false, false, false, false, false);
                    usarBotonesMedicamento(true, true, true, true, true, true);
                    limpiarControles();
                    listarMedicamentos();

                    JOptionPane.showMessageDialog(this, "Medicamento registrado con éxito");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        try {
            if (txtId.getText().isBlank()) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un medicamento para modificar");
                return;
            }

            if (btnModificar.getText().equals(Utilidad.BTN_MODIFICAR)) {
                // Preparar los controles para la modificación
                buscarMedicamento();
                btnModificar.setText(Utilidad.BTN_GUARDAR);
                btnEliminar.setText(Utilidad.BTN_CANCELAR);
                editableControlesMedicamento(false, true, true, true, true, false, true);
                usarBotonesMedicamento(false, false, true, true, true, false);
                chkVigencia.setEnabled(false);
                return;
            }

            if (txtNombre.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe llenar todos los campos antes de modificar.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (objTipoMedicamento.existeNombreTipoMedicamento(txtNombre.getText())) {
                JOptionPane.showMessageDialog(this, "El nombre del tipo de medicamento ya está registrado. Elija un nombre diferente.");
                return;
            }

            int confirmacion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Está seguro de que desea guardar los cambios realizados?",
                    "Confirmar modificación",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmacion != JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, "No se realizo ningún cambio");
                return;
            }

            objMedicamento.modificarMedicamento(
                    Integer.parseInt(txtId.getText()),
                    txtNombre.getText(),
                    Double.parseDouble(txtCosto.getText()),
                    (int) spnStock.getValue(),
                    txtPresentacion.getText(),
                    objTipoMedicamento.obtenerCodigoTipoMedicamento(cbxTipoMedicamento.getSelectedItem().toString())
            );

            btnModificar.setText(Utilidad.BTN_MODIFICAR);
            btnEliminar.setText(Utilidad.BTN_ELIMINAR);
            editableControlesMedicamento(true, false, false, false, false, false, false);
            usarBotonesMedicamento(true, true, true, true, true, true);
            limpiarControles();
            listarMedicamentos();
            JOptionPane.showMessageDialog(this, "Medicamento modificado con éxito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        if (btnRegistrar.getText().equals(Utilidad.BTN_GUARDAR) || btnModificar.getText().equals(Utilidad.BTN_GUARDAR)) {
            cancelarAccionMedicamento();
            tblMedicamento.setEnabled(true);
        } else {
            eliminarMedicamento();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtPresentacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPresentacionKeyTyped
        // TODO add your handling code here:
        Utilidad.validarCampoTextoSoloLetras(evt);
    }//GEN-LAST:event_txtPresentacionKeyTyped

    private void txtIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyTyped
        // TODO add your handling code here:
        Utilidad.validarCampoTextoSoloNumero(evt);
    }//GEN-LAST:event_txtIdKeyTyped

    private void txtCostoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoKeyTyped
        // TODO add your handling code here:
        Utilidad.validarCampoTextoSoloNumeroDecimal(evt);
    }//GEN-LAST:event_txtCostoKeyTyped

    private void tblMedicamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMedicamentoMouseClicked
        // TODO add your handling code here:
        if (btnModificar.getText().equals(Utilidad.BTN_GUARDAR) || btnRegistrar.getText().equals(Utilidad.BTN_GUARDAR)) {
            JOptionPane.showMessageDialog(this, "Por favor, antes de realizar otra operación complete el proceso actual.");
            return;
        }
        txtId.setText(String.valueOf(tblMedicamento.getValueAt(tblMedicamento.getSelectedRow(), 0)));
        btnBuscarActionPerformed(null);
        usarBotonesMedicamento(true, true, true, true, true, true); // Habilita todos los botones
    }//GEN-LAST:event_tblMedicamentoMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        buscarMedicamento();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        limpiarControles();
        usarBotonesMedicamento(true, true, true, true, true, true); // Habilita todos los botones
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void spnStockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spnStockKeyTyped
        // TODO add your handling code here:
        Utilidad.validarCampoTextoSoloNumero(evt);
    }//GEN-LAST:event_spnStockKeyTyped

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        Utilidad.atajoTecladoBoton(this, btnRegistrar, 'R', "registrar");   // CTRL + R
        Utilidad.atajoTecladoBoton(this, btnModificar, 'M', "modificar");   // CTRL + M
        Utilidad.atajoTecladoBoton(this, btnEliminar, 'E', "eliminar");    // CTRL + E
        Utilidad.atajoTecladoBoton(this, btnLimpiar, 'L', "limpiar");    // CTRL + L
        listarMedicamentos();
        listarTipoMedicamento();
        limpiarControles();
        editableControlesMedicamento(true, false, false, false, false, false, false);
        usarBotonesMedicamento(true, true, true, true, true, true); // Habilita "Buscar" y "Registrar"

    }//GEN-LAST:event_formWindowOpened

    private void chkVigenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkVigenciaActionPerformed
        // TODO add your handling code here:
        boolean valor = chkVigencia.isSelected();
        chkVigencia.setSelected(valor);
    }//GEN-LAST:event_chkVigenciaActionPerformed

    private void btnDisponibilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisponibilidadActionPerformed
        // TODO add your handling code here:
        try {
            if (txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seleccione un medicamento para cambiar su disponibilidad.");
                return;
            }

            boolean disponibilidadActual = chkVigencia.isSelected();
            String estadoActual = disponibilidadActual ? "Disponible" : "No Disponible";
            String nuevoEstado = disponibilidadActual ? "No Disponible" : "Disponible";

            int confirmacion = JOptionPane.showConfirmDialog(
                    this,
                    "El medicamento \"" + txtNombre.getText() + "\" actualmente está " + estadoActual + ".\n"
                    + "¿Desea cambiar su estado a \"" + nuevoEstado + "\"?",
                    "Confirmación de cambio de disponibilidad",
                    JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                objMedicamento.actualizarDisponibilidad(Integer.parseInt(txtId.getText()), !disponibilidadActual);

                chkVigencia.setSelected(!disponibilidadActual);
                listarMedicamentos();

                JOptionPane.showMessageDialog(this, "Disponibilidad actualizada con éxito.");
            } else {
                JOptionPane.showMessageDialog(this, "No se realizaron cambios.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar disponibilidad: " + e.getMessage());
        }
    }//GEN-LAST:event_btnDisponibilidadActionPerformed

    private void txtIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) { 
            try {
                if (txtId.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Ingrese un ID válido.", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int id = Integer.parseInt(txtId.getText());
                ResultSet rsEsp = objMedicamento.buscarMedicamento(id);

                if (rsEsp.next()) {
                    txtNombre.setText(rsEsp.getString("nombre"));
                    txtCosto.setText(String.valueOf(rsEsp.getDouble("costo")));
                    cbxTipoMedicamento.setSelectedItem(rsEsp.getString("tipo_medicamento"));
                    spnStock.setValue(rsEsp.getInt("stock"));
                    txtPresentacion.setText(rsEsp.getString("presentacion"));
                    chkVigencia.setSelected(rsEsp.getBoolean("vigencia"));
                    rsEsp.close();

                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró información para el ID ingresado.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    limpiarControles();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al buscar información: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtIdKeyPressed

    private void txtCostoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCostoKeyReleased

    public void limpiarControles() {
        txtId.setText("");
        txtNombre.setText("");
        txtCosto.setText("");
        spnStock.setValue(0);
        txtPresentacion.setText("");
        cbxTipoMedicamento.setSelectedIndex(0);
        listarMedicamentos();
        txtId.requestFocus();
    }

    private void buscarMedicamento() {
        ResultSet rsEsp = null;
        try {
            if (txtId.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar una ID para buscar");
            } else {

                for (int i = 0; i < tblMedicamento.getRowCount(); i++) {
                    String valorCodigo = tblMedicamento.getValueAt(i, 0).toString();
                    if (valorCodigo.equals(txtId.getText())) {
                        tblMedicamento.setRowSelectionInterval(i, i);
                        tblMedicamento.scrollRectToVisible(tblMedicamento.getCellRect(i, 0, true));
                        break;
                    }
                }

                rsEsp = objMedicamento.buscarMedicamento(Integer.parseInt(txtId.getText()));
                if (rsEsp.next()) {
                    txtNombre.setText(rsEsp.getString("nombre"));
                    txtCosto.setText(rsEsp.getString("costo"));
                    spnStock.setValue(rsEsp.getInt("stock"));
                    txtPresentacion.setText(rsEsp.getString("presentacion"));
                    chkVigencia.setSelected(rsEsp.getBoolean("vigencia"));

                    rsEsp.close();
                } else {
                    JOptionPane.showMessageDialog(this, "Este codigo en medicamento no existe");
                    limpiarControles();
                    listarMedicamentos();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error:" + e.getMessage());
            listarMedicamentos();
            limpiarControles();
        }
    }

    private void eliminarMedicamento() {
        try {
            if (txtId.getText().equals("")) {
                Utilidad.mensajeErrorFaltaID(this);
            } else if (Utilidad.validarEliminacionForanea("medicamento", Integer.parseInt(txtId.getText()))) {
                JOptionPane.showMessageDialog(this, "Hay datos externos asociados a" + "medicamento" + "\" " + txtNombre.getText() + "\".\n"
                        + "Considere cambiar su disponibilidad o vigencia para que ya no pueda ser usado");
            } else {
                int valor = JOptionPane.showConfirmDialog(null, "¿Deseas eliminarlo?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (valor == JOptionPane.YES_OPTION) {
                    objMedicamento.eliminarMedicamento(Integer.parseInt(txtId.getText()));
                    limpiarControles();
                    listarMedicamentos();
                    JOptionPane.showMessageDialog(this, "Medicamento eliminado con éxito");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDisponibilidad;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox<String> cbxTipoMedicamento;
    private javax.swing.JCheckBox chkVigencia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel panel;
    private javax.swing.JSpinner spnStock;
    private javax.swing.JTable tblMedicamento;
    private javax.swing.JTextField txtCosto;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPresentacion;
    // End of variables declaration//GEN-END:variables
}

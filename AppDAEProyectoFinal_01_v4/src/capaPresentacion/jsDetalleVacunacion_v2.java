package capaPresentacion;

import capaNegocio.clsCustodia;
import capaNegocio.clsDetalleVacunacion;
import capaNegocio.clsDuenio;
import capaNegocio.clsMascota;
import capaNegocio.clsVacuna;
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
public class jsDetalleVacunacion_v2 extends javax.swing.JDialog {

    /**
     * Creates new form jsDetalleVacunacion
     */
    clsCustodia objCus = new clsCustodia();
    clsVacuna objVac = new clsVacuna();
    clsMascota objMas = new clsMascota();
    clsDetalleVacunacion objDvac = new clsDetalleVacunacion();

    public jsDetalleVacunacion_v2(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
        initComponents();
        listarMascotas();
        listarVacunas();
        listarDetalle_V();
        buttonGroup1.add(jRBCod);
        buttonGroup1.add(jRBNomM);
        buttonGroup2.add(jRBCodD);
        buttonGroup2.add(jRBNOmb);

    }

    private void eliminarAsignacion() {
        int fila = tblDetVac.getSelectedRow();
        int masId = Integer.parseInt(txtMas.getText());
        int vacId = Integer.parseInt(txtVacuna.getText());
        try {
            objDvac.eliminarDetalleVac(vacId, masId);
        } catch (Exception e) {

        }
    }

    private boolean camposEstanLlenos() {
        // Verificar si los campos de texto están llenos
        if (txtMas.getText().isEmpty() || txtVacuna.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Los campos más importantes deben estar llenos.");
            return false; // Retorna false si falta algún campo importante
        }
        if (dtcFechaNacimiento.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar las fecha de vacunación obligatoriamente");
            return false;
        }
        return true; // Retorna true solo si todos los campos están llenos
    }

    private void listarMascotas() throws SQLException {
        DefaultTableModel modeloM = new DefaultTableModel();
        modeloM.addColumn("Código");
        modeloM.addColumn("Nombre");
        modeloM.addColumn("Edad");
        modeloM.addColumn("Dueño");
        modeloM.addColumn("Sexo");
        modeloM.addColumn("Raza");
        tblMascotas.setModel(modeloM);

        ResultSet rsMas = null;
        try {
            rsMas = objCus.listarMascotasV();  // Obtener el ResultSet de mascotas
            while (rsMas.next()) {
                String sexoTexto = rsMas.getBoolean("sexo") ? "Macho" : "Hembra";
                String fechaNacimiento = rsMas.getDate("fecha_nacimiento") != null
                        ? rsMas.getDate("fecha_nacimiento").toString() : null;

                // Obtener la edad utilizando el método calcularEdadMascota
                String edad = objMas.calcularEdadMascota(rsMas.getInt("id"));

                // Agregar la fila a la tabla
                modeloM.addRow(new Object[]{
                    rsMas.getInt("id"),
                    rsMas.getString("nombre"),
                    edad, // Usar la edad calculada
                    rsMas.getString("d_nom"), // Asumiendo que tienes la columna 'dueño'
                    sexoTexto,
                    rsMas.getString("raza_nombre")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al listar Mascotas: " + e.getMessage());
        } finally {
            if (rsMas != null) {
                rsMas.close();
            }
        }

    }

    private boolean asignarVacunacionJ(int vacId, int masId) {
        try {
            if (objDvac.existeDetalleVacunacion(vacId, masId)) {
                JOptionPane.showMessageDialog(this, "Error: La combinación de vacuna y mascota ya existe en el registro.");
                return false; // Si ya existe, devolvemos false
            }
            return true; // Si no existe, devolvemos true
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al realizar la acción: " + e.getMessage());
            return false; // Si hay un error, devolvemos false para detener el proceso
        }
    }

    public void listarVacunas() throws SQLException {
        ResultSet rsVac = null;
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        modelo.addColumn("Dosis por Kg");
        modelo.addColumn("Especie");
        modelo.addColumn("Disponible");
        tblVacuna.setModel(modelo);

        try {
            rsVac = objDvac.listarVacunas();
            while (rsVac.next()) {
                boolean disponibilidad = rsVac.getBoolean("v_dis");
                String disponibleText = disponibilidad ? "Disponible" : "No disponible";
                modelo.addRow(new Object[]{
                    rsVac.getInt("id"),
                    rsVac.getString("nombre"),
                    rsVac.getDouble("dosis_x_kgpeso"),
                    rsVac.getString("es_nom"),
                    disponibleText
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al listar vacunas: " + e.getMessage());
        }
    }

    private void limpiarControles() throws SQLException {

        // Limpiar los campos de texto
        txtVacuna.setText("");
        txtMas.setText("");
        dtcFechaNacimiento.setDate(null);
        txtObser.setText("");
        // Desmarcar botones de radio
        buttonGroup2.clearSelection();
        buttonGroup1.clearSelection();
        // Restablecer selección en tablas
        tblMascotas.clearSelection();
        tblVacuna.clearSelection();
        tblDetVac.clearSelection();

    }

    public void listarDetalle_V() throws SQLException {
        ResultSet rsVac = null;
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Código Vacuna");
        modelo.addColumn("Código Mascota");
        modelo.addColumn("Fecha Vacuna");
        modelo.addColumn("Observación");
        tblDetVac.setModel(modelo);

        try {
            rsVac = objDvac.listarDetalleV();
            while (rsVac.next()) {
                modelo.addRow(new Object[]{
                    rsVac.getInt("vacuna_id"),
                    rsVac.getInt("mascota_id"),
                    rsVac.getString("fecha_vacuna"),
                    rsVac.getString("observacion")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al listar vacunas: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDetVac = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        dtcFechaNacimiento = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jRBCod = new javax.swing.JRadioButton();
        txtMas = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMascotas = new javax.swing.JTable();
        jRBNomM = new javax.swing.JRadioButton();
        btnBuscar1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtObser = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jRBNOmb = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        txtVacuna = new javax.swing.JTextField();
        jRBCodD = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblVacuna = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        btnLimpiar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnRe = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detalle Vacunación");

        jPanel5.setBackground(new java.awt.Color(0, 0, 102));

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("DETALLE VACUNACIÓN");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(454, 454, 454))
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
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

        jPanel4.setBackground(new java.awt.Color(204, 255, 204));

        tblDetVac.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDetVac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetVacMouseClicked(evt);
            }
        });
        tblDetVac.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblDetVacKeyTyped(evt);
            }
        });
        jScrollPane4.setViewportView(tblDetVac);

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel5.setText("Lista de Detalle vacunación:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel6.setBackground(new java.awt.Color(204, 204, 255));
        jPanel6.setForeground(new java.awt.Color(204, 204, 255));

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel3.setText("Fecha Vacunación:");

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
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMascotas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMascotasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMascotas);
        if (tblMascotas.getColumnModel().getColumnCount() > 0) {
            tblMascotas.getColumnModel().getColumn(0).setResizable(false);
            tblMascotas.getColumnModel().getColumn(1).setResizable(false);
            tblMascotas.getColumnModel().getColumn(2).setResizable(false);
            tblMascotas.getColumnModel().getColumn(3).setResizable(false);
        }

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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
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

        txtObser.setColumns(20);
        txtObser.setRows(5);
        jScrollPane3.setViewportView(txtObser);

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel4.setText("Observación:");

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));

        jRBNOmb.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jRBNOmb.setText("Nombre");

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel1.setText("Buscar vacuna:");

        txtVacuna.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtVacunaKeyTyped(evt);
            }
        });

        jRBCodD.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jRBCodD.setText("Código");

        tblVacuna.setModel(new javax.swing.table.DefaultTableModel(
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
        tblVacuna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVacunaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblVacuna);

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
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jRBCodD)
                        .addGap(28, 28, 28)
                        .addComponent(jRBNOmb))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtVacuna, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRBCodD)
                    .addComponent(jRBNOmb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtVacuna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        jPanel10.setBackground(new java.awt.Color(255, 204, 204));

        btnLimpiar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/escoba-mascota.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/eliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar Asignación de Vacuna");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnRe.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnRe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/flecha derecha.png"))); // NOI18N
        btnRe.setText("Asignar Vacunación");
        btnRe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(btnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRe, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar)
                    .addComponent(btnRe, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtVacunaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVacunaKeyTyped
        // TODO add your handling code here:
        if (jRBCodD.isSelected()) {
            Utilidad.validarCampoTextoSoloNumero(evt);
        } else if (jRBNOmb.isSelected()) {
            Utilidad.validarCampoTextoSoloLetras(evt);
        }
    }//GEN-LAST:event_txtVacunaKeyTyped

    private void tblVacunaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVacunaMouseClicked
        // TODO add your handling code here:
        txtVacuna.setText(String.valueOf(tblVacuna.getValueAt(tblVacuna.getSelectedRow(), 0)));
        jRBCodD.setSelected(true);
    }//GEN-LAST:event_tblVacunaMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        ResultSet rsDuenio = null;
        DefaultTableModel modeloD = new DefaultTableModel();
        modeloD.addColumn("Código");
        modeloD.addColumn("Nombre");
        modeloD.addColumn("Dosis x Peso");
        modeloD.addColumn("Especie");

        tblVacuna.setModel(modeloD);

        try {
            // Verificar si se ha seleccionado al menos una opción
            if (!jRBCodD.isSelected() && !jRBNOmb.isSelected()) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un botón para realizar la búsqueda");
            }

            // Verificar si el campo de texto está vacío
            if (txtVacuna.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe llenar el campo vacío para realizar la búsqueda");
                listarVacunas();
                txtVacuna.setText("");
            }

            // Realizar la búsqueda dependiendo de la opción seleccionada
            if (jRBCodD.isSelected()) {
                try {
                    int codigo = Integer.parseInt(txtVacuna.getText());
                    rsDuenio = objDvac.filtrarVacunas(codigo);

                    // Procesar resultados
                    while (rsDuenio != null && rsDuenio.next()) {
                        modeloD.addRow(new Object[]{
                            rsDuenio.getString("id"),
                            rsDuenio.getString("nombre"),
                            rsDuenio.getString("dosis_x_kgpeso"),
                            rsDuenio.getString("es_nom")
                        });
                    }
                    if (modeloD.getRowCount() == 0) {
                        JOptionPane.showMessageDialog(this, "Código de Vacuna no existe");
                        listarVacunas();
                        txtVacuna.setText("");
                    }
                } catch (NumberFormatException e) {
                    listarVacunas();
                    txtVacuna.setText("");
                }
            } else if (jRBNOmb.isSelected()) {
                String nom = txtVacuna.getText();
                rsDuenio = objDvac.filtrarVacunasN(nom);

                // Procesar resultados
                while (rsDuenio != null && rsDuenio.next()) {
                    modeloD.addRow(new Object[]{
                        rsDuenio.getString("id"),
                        rsDuenio.getString("nombre"),
                        rsDuenio.getString("dosis_x_kgpeso"),
                        rsDuenio.getString("es_nom")
                    });
                }

                if (modeloD.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(this, "No se encontraron resultados para el nombre ingresado");
                    listarVacunas();
                    txtVacuna.setText("");
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
                JOptionPane.showMessageDialog(txtVacuna, "Solo se permiten números en esta opción");
            }
        } else if (jRBNomM.isSelected()) {
            // Si la opción de nombre está seleccionada, permitir solo letras
            char c = evt.getKeyChar();
            if (!Character.isLetter(c) && c != ' ') {
                evt.consume(); // Ignora la entrada si no es una letra o espacio
                JOptionPane.showMessageDialog(txtVacuna, "Solo se permiten letras en esta opción");
            }
        }
    }//GEN-LAST:event_txtMasKeyTyped

    private void tblMascotasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMascotasMouseClicked
        // TODO add your handling code here:
        txtMas.setText(String.valueOf(tblMascotas.getValueAt(tblMascotas.getSelectedRow(), 0)));
        jRBCod.setSelected(true);
        //  btnBuscaMActionPerformed(null);
        try {
            listarMascotas();
        } catch (SQLException ex) {
            Logger.getLogger(jdCustodia_v2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblMascotasMouseClicked

    private void jRBNomMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBNomMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBNomMActionPerformed

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        ResultSet rsDuenio = null;
        DefaultTableModel modeloM = new DefaultTableModel();
        modeloM.addColumn("Código");
        modeloM.addColumn("Nombre");
        modeloM.addColumn("Edad");
        modeloM.addColumn("Dueño");
        modeloM.addColumn("Sexo");
        modeloM.addColumn("Raza");
        tblMascotas.setModel(modeloM);

        try {
            if (!jRBCod.isSelected() && !jRBNomM.isSelected()) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un botón para realizar la búsqueda");
            }
            txtMas.setEnabled(true);
            if (txtMas.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe llenar el campo vacío para realizar la búsqueda");
                listarMascotas();
                txtMas.setText("");
            }
            if (jRBCod.isSelected()) {
                try {
                    int codigo = Integer.parseInt(txtMas.getText());
                    rsDuenio = objCus.filtrarMascota(codigo);
                    while (rsDuenio.next()) {
                        String sexoTexto = rsDuenio.getBoolean("sexo") ? "Macho" : "Hembra";
                        String fechaNacimiento = rsDuenio.getDate("fecha_nacimiento") != null
                                ? rsDuenio.getDate("fecha_nacimiento").toString() : null;
                        String edad = objMas.calcularEdadMascota(rsDuenio.getInt("id"));
                        modeloM.addRow(new Object[]{
                            rsDuenio.getInt("id"),
                            rsDuenio.getString("nombre"),
                            edad, // Usar la edad calculada
                            rsDuenio.getString("d_nom"), // Asumiendo que tienes la columna 'dueño'
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
                    return;
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
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed

        try {
            listarMascotas();
            limpiarControles();
            listarVacunas();
            listarDetalle_V();
        } catch (SQLException ex) {
            Logger.getLogger(jsDetalleVacunacion_v2.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnLimpiarActionPerformed

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
            if (tblDetVac.getSelectedRow() != -1) {
                try {
                    eliminarAsignacion();
                    listarDetalle_V();
                    limpiarControles();
                    listarMascotas();
                } catch (SQLException ex) {
                    Logger.getLogger(jsDetalleVacunacion_v2.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Error al realizar la operación: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una fila de la 3ra tabla para hacer esta operación", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }


    }//GEN-LAST:event_btnEliminarActionPerformed


    private void btnReActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReActionPerformed
        try {
            if (btnRe.getText().equals("Asignar Vacunación")) {
                btnRe.setText("Guardar Datos");
                JOptionPane.showMessageDialog(this, "TE RECUERDO QUE DEBES GUARDAR LA ASIGNACIÓN DE VACUNA POR CÓDIGO EN AMBOS CASOS");
                btnEliminar.setEnabled(false);
                btnLimpiar.setEnabled(false);
                limpiarControles();
                dtcFechaNacimiento.requestFocus();
            } else {
                int masId = Integer.parseInt(txtMas.getText());
                int vacId = Integer.parseInt(txtVacuna.getText());
                Date fechaNacimiento = dtcFechaNacimiento.getDate();

                if (camposEstanLlenos()) {
                    btnRe.setText("Asignar Vacunación");

                    try {
                        // Verifica la existencia antes de asignar
                        if (objDvac.existeDetalleVacunacion(vacId, masId)) {
                            JOptionPane.showMessageDialog(this, "Error: La combinación de vacuna y mascota ya existe en el registro.");
                        } else {
                            String obs = txtObser.getText().isEmpty() ? null : txtObser.getText();

                            objDvac.asignarVacuna(vacId, masId, fechaNacimiento, obs);
                            JOptionPane.showMessageDialog(this, "Vacunación asignada con éxito");

                            // Cambiar el texto del botón y actualizar la vista
                            btnRe.setText("Asignar Vacunación");
                            btnBuscar.setEnabled(true);
                            btnEliminar.setEnabled(true);
                            btnLimpiar.setEnabled(true);
                            listarDetalle_V();
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(jsDetalleVacunacion_v2.class.getName()).log(Level.SEVERE, null, ex);
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

    private void tblDetVacKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblDetVacKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_tblDetVacKeyTyped

    private void tblDetVacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetVacMouseClicked
        // TODO add your handling code here:

        txtMas.setText(String.valueOf(tblDetVac.getValueAt(tblDetVac.getSelectedRow(), 1)));
        txtVacuna.setText(String.valueOf(tblDetVac.getValueAt(tblDetVac.getSelectedRow(), 0)));
        txtObser.setText(String.valueOf(tblDetVac.getValueAt(tblDetVac.getSelectedRow(), 3)));
        String fechaAdopcionStr = String.valueOf(tblDetVac.getValueAt(tblDetVac.getSelectedRow(), 2));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaAdopcion;
        jRBCod.setSelected(true);
        jRBCodD.setSelected(true);
        try {
            fechaAdopcion = dateFormat.parse(fechaAdopcionStr);
            // Asigna la fecha al campo dtcFechaNacimiento
            dtcFechaNacimiento.setDate(fechaAdopcion);
        } catch (ParseException ex) {
            Logger.getLogger(jsDetalleVacunacion_v2.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_tblDetVacMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRe;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.toedter.calendar.JDateChooser dtcFechaNacimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRBCod;
    private javax.swing.JRadioButton jRBCodD;
    private javax.swing.JRadioButton jRBNOmb;
    private javax.swing.JRadioButton jRBNomM;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tblDetVac;
    private javax.swing.JTable tblMascotas;
    private javax.swing.JTable tblVacuna;
    private javax.swing.JTextField txtMas;
    private javax.swing.JTextArea txtObser;
    private javax.swing.JTextField txtVacuna;
    // End of variables declaration//GEN-END:variables
}

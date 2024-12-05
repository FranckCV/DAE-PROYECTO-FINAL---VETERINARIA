package capaPresentacion;

import capaNegocio.clsCustodia;
import capaNegocio.clsMascota;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import soporte.Utilidad;

/**
 *
 * @author Windows10
 */
public class jdCustodiaMasc extends javax.swing.JDialog {

    private int mascota = 0;
    private int codigoEspecie = 0;

    clsMascota objMas = new clsMascota();
    clsCustodia objCus = new clsCustodia();

    public jdCustodiaMasc(java.awt.Frame parent, boolean modal) throws Exception {
        super(parent, modal);
        initComponents();
        listarMascotas();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMas = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMascotas = new javax.swing.JTable();
        btnBuscar1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel2.setText("Buscar por nombre:");

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

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/escoba-mascota.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMas, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(txtMas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnBuscar1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 464, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 264, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMasKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isLetter(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
            evt.consume();
            JOptionPane.showMessageDialog(txtMas, "Solo se permiten letras en esta opción");
        }
    }//GEN-LAST:event_txtMasKeyTyped

    private void tblMascotasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMascotasMouseClicked
        // TODO add your handling code here
        int selectedRow = tblMascotas.getSelectedRow();
        txtMas.setText(String.valueOf(tblMascotas.getValueAt(tblMascotas.getSelectedRow(), 1)));
        if (selectedRow != -1) {
            int cod = Integer.parseInt(String.valueOf(tblMascotas.getValueAt(selectedRow, 0)));

            codigoEspecie = Integer.parseInt(String.valueOf(tblMascotas.getValueAt(selectedRow, 5))); // Código de especie

            //      pasarMascotaYEspecie(cod, especieId); // Método modificado para pasar ambos valores
            pasarM(cod);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una mascota válido.");
        }
    }//GEN-LAST:event_tblMascotasMouseClicked

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        ResultSet rsDuenio = null;
        DefaultTableModel modeloM = new DefaultTableModel();
        modeloM.addColumn("Id");
        modeloM.addColumn("Nombre");
        modeloM.addColumn("Fecha Nacimiento");
        modeloM.addColumn("Sexo");
        modeloM.addColumn("Raza");
        tblMascotas.setModel(modeloM);
        Utilidad.alineacionDerechaColumnaTabla(tblMascotas, 0);
        Utilidad.alineacionDerechaColumnaTabla(tblMascotas, 2);

        try {
            if (txtMas.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe llenar el campo vacío para realizar la búsqueda");
                listarMascotas();  // Recargar la lista completa
                txtMas.setText("");
                return;
            }
            rsDuenio = objCus.filtrarMascotaVig(txtMas.getText());
            while (rsDuenio.next() && rsDuenio != null) {
                String sexoTexto = rsDuenio.getBoolean("sexo") ? "Macho" : "Hembra";
                String fechaNacimiento = rsDuenio.getString("fecha_nacimiento");
                modeloM.addRow(new Object[]{
                    rsDuenio.getString("ma_id"),
                    rsDuenio.getString("nom_mas"),
                    fechaNacimiento != null ? Utilidad.textoFormatoFecha(fechaNacimiento) : "",
                    sexoTexto,
                    rsDuenio.getString("raza_nombre")
                });
                if (modeloM.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(this, "No se encontraron resultados para la mascota seleccionada");
                    listarMascotas();
                    txtMas.setText("");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error del sistema en la búsqueda: " + e.getMessage());
        }
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            txtMas.setText("");
            listarMascotas();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    private void listarMascotas() throws Exception {
        ResultSet rsMas = null;
        DefaultTableModel modeloM = new DefaultTableModel();
        modeloM.addColumn("Id");
        modeloM.addColumn("Nombre");
        modeloM.addColumn("Fecha Nacimiento");
        modeloM.addColumn("Sexo");
        modeloM.addColumn("Raza");
        modeloM.addColumn("Especie ID"); // Columna oculta para el código de especie
        tblMascotas.setModel(modeloM);
        Utilidad.alineacionDerechaColumnaTabla(tblMascotas, 0);
        Utilidad.alineacionDerechaColumnaTabla(tblMascotas, 2);
        try {
            rsMas = objCus.listarMascotasVig();
            while (rsMas.next()) {
                String sexoTexto = rsMas.getBoolean("sexo") ? "Macho" : "Hembra";
                String fechaNacimiento = rsMas.getString("fecha_nacimiento");
                modeloM.addRow(new Object[]{
                    rsMas.getString("id"),
                    rsMas.getString("nombre"),
                    fechaNacimiento != null ? Utilidad.textoFormatoFecha(fechaNacimiento) : "",
                    sexoTexto,
                    rsMas.getString("raza_nombre"),
                    rsMas.getInt("especie_id") // Agregando el código de especie
                });
            }
            // Ocultando la columna de Especie ID
            tblMascotas.getColumnModel().getColumn(5).setMinWidth(0);
            tblMascotas.getColumnModel().getColumn(5).setMaxWidth(0);
            tblMascotas.getColumnModel().getColumn(5).setWidth(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al listar Mascotas: " + e.getMessage());
        }
    }

    public int getCod() {
        return mascota;
    }

    private void pasarM(int cod) {
        try {
            mascota = cod;
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ERROR AL PASAR LOS DATOS DE LA MASCOTA--->" + e.getMessage());
        }

    }

    private void pasarMascotaYEspecie(int codMascota, int codEspecie) {
        try {
            mascota = codMascota;
            codigoEspecie = codEspecie;
            dispose(); // Cierra el diálogo
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ERROR AL PASAR LOS DATOS DE LA MASCOTA Y ESPECIE: " + e.getMessage());
        }
    }

    public int getCodigoEspecie() {
        return this.codigoEspecie; // Asegúrate de que `codigoEspecie` esté correctamente definido y asignado
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblMascotas;
    private javax.swing.JTextField txtMas;
    // End of variables declaration//GEN-END:variables
}

package capaPresentacion;

import capaNegocio.clsCustodia;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Windows10
 */
public class jdCustodiaDue_v2 extends javax.swing.JDialog {

    private int duenio = 0;
    clsCustodia objCus = new clsCustodia();

    public jdCustodiaDue_v2(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        listarDuenios();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDuenio = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDuenos = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel1.setText("Buscar por número documento:");

        txtDuenio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDuenioKeyTyped(evt);
            }
        });

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

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/escoba-mascota.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDuenio, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtDuenio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDuenioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDuenioKeyTyped
        char c = evt.getKeyChar();
    if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
        evt.consume();
        JOptionPane.showMessageDialog(txtDuenio, "Solo se permiten números en esta opción");
    }
    }//GEN-LAST:event_txtDuenioKeyTyped

    private void tblDuenosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDuenosMouseClicked
           int selectedRow = tblDuenos.getSelectedRow();
            txtDuenio.setText(String.valueOf(tblDuenos.getValueAt(selectedRow, 1)));
    if (selectedRow != -1) { 
        int cod = Integer.parseInt(String.valueOf(tblDuenos.getValueAt(selectedRow, 0)));
        pasar(cod);
    } else {
        JOptionPane.showMessageDialog(this, "Seleccione un dueño válido.");
    }
    }//GEN-LAST:event_tblDuenosMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        ResultSet rsDuenio = null;
        DefaultTableModel modeloD = new DefaultTableModel();
modeloD.addColumn("Id");
        modeloD.addColumn("Num. Doc.");
        modeloD.addColumn("Nombres");
        modeloD.addColumn("Ap. Paterno");
        modeloD.addColumn("Ap. Materno");
        modeloD.addColumn("Teléfono");
        tblDuenos.setModel(modeloD);

        try {
            if (txtDuenio.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe llenar el campo vacío para realizar la búsqueda");
                listarDuenios();
                txtDuenio.setText("");
                return;  // Terminar ejecución si el campo está vacío
            }
            rsDuenio = objCus.filtrarDuenioNV(txtDuenio.getText());
            while (rsDuenio != null && rsDuenio.next()) {
                modeloD.addRow(new Object[]{
                    rsDuenio.getString("id"),
                    rsDuenio.getString("doc_identidad"),
                    rsDuenio.getString("nombres"),
                    rsDuenio.getString("apepaterno"),
                    rsDuenio.getString("apematerno"),
                    rsDuenio.getString("telefono")
                });
                if (modeloD.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(this, "No se encontraron resultados para el número de documento ingresado");
                    listarDuenios();
                    txtDuenio.setText("");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error del sistema en la búsqueda: " + e.getMessage());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            txtDuenio.setText("");
            listarDuenios();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    private void listarDuenios() {
        ResultSet rsDue = null;
        DefaultTableModel modeloD = new DefaultTableModel();
        modeloD.addColumn("Id");
        modeloD.addColumn("Num. Doc.");
        modeloD.addColumn("Nombres");
        modeloD.addColumn("Ap. Paterno");
        modeloD.addColumn("Ap. Materno");
        modeloD.addColumn("Teléfono");

        tblDuenos.setModel(modeloD);
        try {
            rsDue = objCus.listarDueniosVIg();
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
     public int getCod() {
        return duenio;
    }    
     private void pasar(int cod) {
        try {
            duenio = cod; 
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ERROR AL PASAR LOS DATOS DEL DUENIO--->" + e.getMessage());
        }
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblDuenos;
    private javax.swing.JTextField txtDuenio;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package capaPresentacion;

import capaNegocio.clsMedicamento;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import soporte.Utilidad;

/**
 *
 * @author Junior
 */
public class jdAniadirMedicamento extends javax.swing.JDialog {

    clsMedicamento objMedicamento = new clsMedicamento();

    private int med = 0;
    private int cant = 0;
    private int dosis = 0;
    private String indic = "";

    public jdAniadirMedicamento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        listarMedicamentos();
        Utilidad.validacionTabla(tblMedicamentos, modal, rootPaneCheckingEnabled, modal);
    }

    private void listarMedicamentos() {
        ResultSet rsMedicamentos = null;
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("COSTO");
        modelo.addColumn("STOCK");
        modelo.addColumn("PRESENTACIÓN");
        modelo.addColumn("VIGENCIA");
        modelo.addColumn("TIPO");

        try {
            rsMedicamentos = objMedicamento.filtrar(txtNombreMedicamento.getText());
            while (rsMedicamentos.next()) {
                String vigencia;

                if (rsMedicamentos.getBoolean("vigencia")) {
                    vigencia = "VIGENTE";
                } else {
                    vigencia = "NO VIGENTE";
                }

                modelo.addRow(new Object[]{rsMedicamentos.getInt("ID"),
                    rsMedicamentos.getString("nombre"),
                    rsMedicamentos.getString("costo"),
                    rsMedicamentos.getString("stock"),
                    rsMedicamentos.getString("presentacion"),
                    vigencia,
                    rsMedicamentos.getString("nomtipo")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage() + " uuu");
        }
        tblMedicamentos.setModel(modelo);

        ocultarColumna(tblMedicamentos, 0);
        ocultarColumna(tblMedicamentos, 4);
        ocultarColumna(tblMedicamentos, 6);
        tblMedicamentos.getColumnModel().getColumn(1).setPreferredWidth(250);

        
        alinearDerecha(2); // Alinear COSTO a la derecha
        alinearDerecha(3); // Alinear STOCK a la derecha
        alinearIzquierda(1);  // Alinear NOMBRE a la izquierda
        alinearIzquierda(4); 
        alinearIzquierda(5); 
        alinearCentro(6); 
    }
    
    private void alinearIzquierda(int columna) {
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        tblMedicamentos.getColumnModel().getColumn(columna).setCellRenderer(leftRenderer);
    }

    private void alinearDerecha(int columna) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        tblMedicamentos.getColumnModel().getColumn(columna).setCellRenderer(rightRenderer);
    }

    private void alinearCentro(int columna) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tblMedicamentos.getColumnModel().getColumn(columna).setCellRenderer(centerRenderer);
    }

    private void ocultarColumna(JTable tabla, int indiceColumna) {
        tabla.getColumnModel().getColumn(indiceColumna).setMaxWidth(0);
        tabla.getColumnModel().getColumn(indiceColumna).setMinWidth(0);
        tabla.getColumnModel().getColumn(indiceColumna).setWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(indiceColumna).setMaxWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(indiceColumna).setMinWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(indiceColumna).setWidth(0);
    }

    private void pasarDatos(int cod, int ctd) {
        try {
            int stock = objMedicamento.getStock(cod);

            if (ctd <= stock) {
                med = cod;
                cant = ctd;
                String dosificacion = String.valueOf(JOptionPane.showInputDialog(rootPane,
                        "Ingrese la dosis:", "0"));

                indic = String.valueOf(JOptionPane.showInputDialog(rootPane,
                        "Ingrese alguna indicacion adicional", ""));
                try {
                    dosis = Integer.parseInt(dosificacion);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(rootPane, e.getMessage());
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Stock Insuficiente");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }

    public int getCodMed() {
        return med;
    }

    public int getCant() {
        return cant;
    }

    public float getDosis() {
        return dosis;
    }

    public String getIndic() {
        return indic;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtNombreMedicamento = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMedicamentos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Añadir Medicamento");

        txtNombreMedicamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreMedicamentoKeyReleased(evt);
            }
        });

        tblMedicamentos.setModel(new javax.swing.table.DefaultTableModel(
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
        tblMedicamentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMedicamentosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMedicamentos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombreMedicamento)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNombreMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreMedicamentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreMedicamentoKeyReleased
        listarMedicamentos();
    }//GEN-LAST:event_txtNombreMedicamentoKeyReleased

    private void tblMedicamentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMedicamentosMouseClicked
        int cod = Integer.parseInt(String.valueOf(tblMedicamentos.getValueAt(tblMedicamentos.getSelectedRow(), 0)));
        String ctd = String.valueOf(JOptionPane.showInputDialog(rootPane, "Ingrese la cantidad:"));

        if (ctd != "null") {
            try {
                pasarDatos(cod, Integer.parseInt(ctd));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Cantidad no válida");
            }
        }
    }//GEN-LAST:event_tblMedicamentosMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblMedicamentos;
    private javax.swing.JTextField txtNombreMedicamento;
    // End of variables declaration//GEN-END:variables
}

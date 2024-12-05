/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package capaPresentacion;

import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import soporte.Utilidad;

/**
 *
 * @author Junior
 */
public class jdColocarHora extends javax.swing.JDialog {

    private String horaEntradaPasar = "";
    private String horaSalidaPasar = "";
    private String notaAdicionalPasar = "";

    public jdColocarHora(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        llenarComboHorasMinutosYAMPM();
    }

    private void pasarDatos(String horaE, String horaS, String notita) {
        try {

            horaSalidaPasar = horaS;
            horaEntradaPasar = horaE;

            notaAdicionalPasar = notita;
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }

    private Date convertirHoraAFecha(String hora) {
        // Detectamos si es AM o PM
        String amPm = hora.substring(hora.length() - 2).trim();
        int horas = Integer.parseInt(hora.substring(0, 2).trim());
        int minutos = Integer.parseInt(hora.substring(3, 5).trim());

        if (amPm.equalsIgnoreCase("PM") && horas != 12) {
            horas += 12;
        } else if (amPm.equalsIgnoreCase("AM") && horas == 12) {
            horas = 0;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, horas);
        calendar.set(Calendar.MINUTE, minutos);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime();
    }

    private void llenarComboHorasMinutosYAMPM() {

        cboHoraEntrada.removeAllItems();
        cboMinutosEntrada.removeAllItems();
        cboAMPMEntrada.removeAllItems();

        cboHoraSalida.removeAllItems();
        cboAMPMSalida.removeAllItems();
        cboMinutosSalida.removeAllItems();

        for (int i = 0; i <= 12; i++) {
            cboHoraSalida.addItem(String.format("%02d", i)); // Formato para mostrar siempre dos dígitos (ej. "00", "01", ..., "12")
            cboHoraEntrada.addItem(String.format("%02d", i));
        }

        // Llenar el JComboBox para minutos (00, 15, 30, 45)
        int[] minutos = {0, 15, 30, 45};
        for (int minuto : minutos) {
            cboMinutosSalida.addItem(String.format("%02d", minuto)); // Formato para mostrar siempre dos dígitos (ej. "00", "15", "30", "45")
            cboMinutosEntrada.addItem(String.format("%02d", minuto));
        }

        // Llenar el JComboBox para AM/PM
        String[] amPm = {"AM", "PM"};
        for (String periodo : amPm) {
            cboAMPMEntrada.addItem(periodo);  // Llenamos el combo con "AM" y "PM"
            cboAMPMSalida.addItem(periodo);
        }
    }

    public String getHoraEntrada() {
        return horaEntradaPasar;
    }

    public String getHoraSalida() {
        return horaSalidaPasar;
    }

    public String getNota() {
        return notaAdicionalPasar;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cboMinutosSalida = new javax.swing.JComboBox<>();
        cboAMPMSalida = new javax.swing.JComboBox<>();
        cboHoraSalida = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cboHoraEntrada = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cboMinutosEntrada = new javax.swing.JComboBox<>();
        cboAMPMEntrada = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtNota = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Colocar Hora");

        cboMinutosSalida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboAMPMSalida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboHoraSalida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("Hora Salida:");

        cboHoraEntrada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboHoraEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboHoraEntradaActionPerformed(evt);
            }
        });

        jLabel3.setText("Hora Entrada:");

        cboMinutosEntrada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboMinutosEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMinutosEntradaActionPerformed(evt);
            }
        });

        cboAMPMEntrada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Nota Adicional:");

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cboHoraEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboMinutosEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboAMPMEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cboHoraSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboMinutosSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboAMPMSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboHoraEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboMinutosEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboAMPMEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboHoraSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboMinutosSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboAMPMSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboHoraEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboHoraEntradaActionPerformed
        if (cboHoraEntrada.getSelectedIndex() != -1) {
            cboHoraSalida.setSelectedIndex(cboHoraEntrada.getSelectedIndex());
        }
    }//GEN-LAST:event_cboHoraEntradaActionPerformed

    private void cboMinutosEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMinutosEntradaActionPerformed
        if (cboHoraEntrada.getSelectedIndex() != -1 && cboMinutosEntrada.getSelectedIndex() != -1) {
            if (cboMinutosEntrada.getSelectedIndex() == 3) {
                int horaEntradaIndex = cboHoraEntrada.getSelectedIndex();
                if (horaEntradaIndex + 1 < cboHoraSalida.getItemCount()) {
                    cboHoraSalida.setSelectedIndex(horaEntradaIndex + 1);
                }
            } else {
                int minutoEntradaIndex = cboMinutosEntrada.getSelectedIndex();
                if (minutoEntradaIndex + 1 < cboMinutosSalida.getItemCount()) {
                    cboMinutosSalida.setSelectedIndex(minutoEntradaIndex + 1);
                }
            }
        }
    }//GEN-LAST:event_cboMinutosEntradaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        Date hora1 = convertirHoraAFecha(cboHoraEntrada.getSelectedItem().toString() + ":"
                + cboMinutosEntrada.getSelectedItem().toString()
                + cboAMPMEntrada.getSelectedItem().toString());

        Date hora2 = convertirHoraAFecha(cboHoraSalida.getSelectedItem().toString() + ":"
                + cboMinutosSalida.getSelectedItem().toString()
                + cboAMPMSalida.getSelectedItem().toString());

        Calendar inicioRango = Calendar.getInstance();
        inicioRango.set(Calendar.HOUR_OF_DAY, 8);
        inicioRango.set(Calendar.MINUTE, 0);
        inicioRango.set(Calendar.SECOND, 0);

        Calendar finRango = Calendar.getInstance();
        finRango.set(Calendar.HOUR_OF_DAY, 20);
        finRango.set(Calendar.MINUTE, 0);
        finRango.set(Calendar.SECOND, 0);

        if (hora1.before(inicioRango.getTime()) || hora1.after(finRango.getTime())) {
            JOptionPane.showMessageDialog(this, "La hora de entrada debe estar entre las 8 AM y las 8 PM.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } else if (hora2.before(hora1) || (hora2.getTime() - hora1.getTime()) < 15 * 60 * 1000) {

            JOptionPane.showMessageDialog(this, "La hora de salida debe ser al menos 15 minutos después de la de entrada.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } else {
            int valor = Utilidad.mensajeConfirmarAgregarServicio("Servicio");

            if (valor == 0) {
                String horaEntrada = cboHoraEntrada.getSelectedItem().toString() + ":" + cboMinutosEntrada.getSelectedItem().toString() + ":00 " + cboAMPMEntrada.getSelectedItem().toString();
                String horaSalida = cboHoraSalida.getSelectedItem().toString() + ":" + cboMinutosSalida.getSelectedItem().toString() + ":00 " + cboAMPMSalida.getSelectedItem().toString();
                String notaAdicional = txtNota.getText();
                try {
                    pasarDatos(horaEntrada, horaSalida, notaAdicional);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(rootPane, "Cantidad no válida");
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboAMPMEntrada;
    private javax.swing.JComboBox<String> cboAMPMSalida;
    private javax.swing.JComboBox<String> cboHoraEntrada;
    private javax.swing.JComboBox<String> cboHoraSalida;
    private javax.swing.JComboBox<String> cboMinutosEntrada;
    private javax.swing.JComboBox<String> cboMinutosSalida;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txtNota;
    // End of variables declaration//GEN-END:variables
}

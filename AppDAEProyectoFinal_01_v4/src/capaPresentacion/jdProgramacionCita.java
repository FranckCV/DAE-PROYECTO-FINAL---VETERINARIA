/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package capaPresentacion;

import capaNegocio.clsCita;
import capaNegocio.clsDetalleCita;
import capaNegocio.clsDuenio;
import capaNegocio.clsMascota;
import capaNegocio.clsMedicamento;
import capaNegocio.clsMedico;
import capaNegocio.clsServicio;
import java.awt.Frame;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import soporte.*;

/**
 *
 * @author Junior
 */
public class jdProgramacionCita extends javax.swing.JDialog {

    clsMedicamento objMedicamento = new clsMedicamento();
    clsCita objCita = new clsCita();
    clsDuenio objDuenio = new clsDuenio();
    clsMedico objMedico = new clsMedico();
    clsServicio objServicio = new clsServicio();
    clsMascota objMascota = new clsMascota();
    clsDetalleCita objDetalleCita = new clsDetalleCita();

//    Integer servicioCod = -1;
//    Integer medicoCod = -1;
    public jdProgramacionCita(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Programación de cita");

        txtNumero.setEditable(false);
        generarCodigo();
        llenarCboServicios();
        llenarTablaInicial();
    }

    private void generarCodigo() {
        try {
            int numero = objCita.generarCodigoCita();
            txtNumero.setText(String.valueOf(numero));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }

    }

    private void llenarTablaInicial() {
        ResultSet rsServicios = null;
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID SERV_MED");
        modelo.addColumn("SERVICIO");
        modelo.addColumn("MEDICO");
        modelo.addColumn("HORA ENTRADA");
        modelo.addColumn("HORA SALIDA");
        modelo.addColumn("NOTA ADICIONAL");
        modelo.addColumn("COSTO");

        tblDetalleServicio.setModel(modelo);

        tblDetalleServicio.getColumnModel().getColumn(0).setMaxWidth(0);
        tblDetalleServicio.getColumnModel().getColumn(0).setMinWidth(0);
        tblDetalleServicio.getColumnModel().getColumn(0).setWidth(0);
        tblDetalleServicio.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tblDetalleServicio.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        tblDetalleServicio.getTableHeader().getColumnModel().getColumn(0).setWidth(0);

        tblDetalleServicio.getTableHeader().setReorderingAllowed(false); //no mover los headers
    }

    private void agregarServicio(int codServ, int codMed, String horaEntrada, String horaSalida, String notita) {

        ResultSet rsSer = null;
        ResultSet rsMed = null;

        if (codServ != -1 && codMed != -1) {
            try {
                DefaultTableModel modelo = (DefaultTableModel) tblDetalleServicio.getModel();
                boolean repetido = false;

                // Verificar si el servicio ya está en la tabla
                for (int i = 0; i < tblDetalleServicio.getRowCount(); i++) {
                    String cadena = String.valueOf(tblDetalleServicio.getValueAt(i, 0));
                    String[] codigos = cadena.split(" - ");
                    int codigoTablaSer = Integer.parseInt(codigos[0].trim());
                    int codTablaMed = Integer.parseInt(codigos[1].trim());

                    if (codigoTablaSer == codServ && codTablaMed == codMed) {
                        repetido = true;
                        break;
                    } else {
                        if (codigoTablaSer == codServ) {
                            if (JOptionPane.showConfirmDialog(this, "Agregar servicio repetido?") == JOptionPane.YES_OPTION) {
                                repetido = false;
                            } else {
                                repetido = true;
                                break;
                            }

                        }
                    }
                }
                // Mostrar mensaje si el servicio ya está en la tabla
                if (repetido) {
                    JOptionPane.showMessageDialog(rootPane, "Este servicio ya se encuentra agregado.");
                    return;
                }
//
//                System.out.println(codServ + " --- " + codMed);
                rsSer = objServicio.buscarServicio(codServ);
                rsMed = objMedico.buscarMedico(codMed);
                rsSer.next();
                rsMed.next();

                Date entradaDate = convertirHoraAFecha(horaEntrada);
                Date salidaDate = convertirHoraAFecha(horaSalida);

                // Formatear las horas
                String horaEntradaFormateada = formatearHora(entradaDate);
                String horaSalidaFormateada = formatearHora(salidaDate);

//                if (!objMedico.verificarDisponibilidad(codMed, horaEntrada, horaSalida)) {
//                    JOptionPane.showMessageDialog(rootPane, "Este médico ya tiene un servicio en el horario indicado.");
//                    return;  // No agregar el servicio si el médico no está disponible
//                }
//                
                Object[] fila = new Object[7];
                fila[0] = codServ + " - " + codMed;
                fila[1] = rsSer.getString("nom_servicio"); // Aquí va el nombre del servicio (ajustar según tu lógica)
                fila[2] = rsMed.getString("nombres") + " " + rsMed.getString("apepaterno") + " " + rsMed.getString("apematerno");
                fila[3] = horaEntradaFormateada;
                fila[4] = horaSalidaFormateada;
                fila[5] = notita;
                fila[6] = rsSer.getDouble("costo");

                modelo.addRow(fila);
                tblDetalleServicio.setModel(modelo);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Error al agregar el servicio: " + e.getMessage());
            }
        }
    }

//    public void agregarServicio2(int codServ, int codMed, String horaEntrada, String horaSalida) {
//        try {
//            // Verificar disponibilidad del médico
//            if (!objMedico.verificarDisponibilidad(codMed, horaEntrada, horaSalida)) {
//                JOptionPane.showMessageDialog(rootPane, "Este médico ya tiene un servicio en el horario indicado.");
//                return;  // No agregar el servicio si el médico no está disponible
//            }
//
//            // Si el médico está disponible, continuar con la inserción del servicio
//            DefaultTableModel modelo = (DefaultTableModel) tblDetalleServicio.getModel();
//            boolean repetido = false;
//
//            // Verificar si el servicio ya está en la tabla
//            for (int i = 0; i < tblDetalleServicio.getRowCount(); i++) {
//                String cadena = String.valueOf(tblDetalleServicio.getValueAt(i, 0));
//                String[] codigos = cadena.split(" - ");
//                int codigoTablaSer = Integer.parseInt(codigos[0].trim());
//                int codTablaMed = Integer.parseInt(codigos[1].trim());
//
//                if (codigoTablaSer == codServ && codTablaMed == codMed) {
//                    repetido = true;
//                    break;
//                }
//            }
//
//            if (repetido) {
//                JOptionPane.showMessageDialog(rootPane, "Este servicio ya se encuentra agregado.");
//            } else {
//                // Realizar la inserción en la tabla
//                ResultSet rsSer = objServicio.buscarServicio(codServ);
//                ResultSet rsMed = objMedico.buscarMedico(codMed);
//
//                // Asegúrate de que rsSer y rsMed no sean null antes de continuar
//                if (rsSer != null && rsMed != null) {
//                    // Aquí agregas el servicio a la tabla
//                    modelo.addRow(new Object[]{codServ + " - " + codMed, rsSer.getString("nom_servicio"), rsMed.getString("nombres"), horaEntrada, horaSalida});
//                } else {
//                    JOptionPane.showMessageDialog(rootPane, "Error al obtener los datos del servicio o médico.");
//                }
//            }
//
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(rootPane, "Error al agregar servicio: " + e.getMessage());
//        }
//    }
    private Date convertirHoraAFecha(String hora) throws Exception {
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

    private String formatearHora(Date fecha) {
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        return formatoHora.format(fecha);
    }

//    private void agregarServicio(int codServ, int codMed, String horaEntrada, String horaSalida) {
//        if (codServ != -1 && codMed != -1) {
//            ResultSet rs = null;
//
//            try {
//                DefaultTableModel modelo = (DefaultTableModel) tblDetalleServicio.getModel();
//                boolean repetido = false;
//
//                // Verificar si el servicio ya está en la tabla
//                for (int i = 0; i < tblDetalleServicio.getRowCount(); i++) {
//                    String cadena = String.valueOf(tblDetalleServicio.getValueAt(i, 0));
//                    String[] codigos = cadena.split(" - ");
//                    int codigoTablaSer = Integer.parseInt(codigos[0].trim());
//                    int codTablaMed = Integer.parseInt(codigos[1].trim());
//
//                    if (codigoTablaSer == codServ && codTablaMed == codMed) {
//                        repetido = true;
//                        break;
//                    }
//                }
//
//                // Mostrar mensaje si el servicio ya está en la tabla
//                if (repetido) {
//                    JOptionPane.showMessageDialog(rootPane, "Este servicio ya se encuentra agregado");
//                } else {
//                    // Obtener datos del servicio y agregarlos a la tabla si no está repetido
//                    rs = objServicio.obtenerDatosDetalleServicio(codServ, codMed);
//
////                    medicoCod = codMed;
////                    servicioCod = codServ;
//                    if (rs == null) {
//                        JOptionPane.showMessageDialog(this, "Error: El ResultSet está vacío.");
//                        return;
//                    }
//
//                    boolean hasData = false; // Para verificar si hay datos en el ResultSet
//                    while (rs.next()) {
//                        hasData = true;
//                        modelo.addRow(new Object[]{
//                            rs.getInt("ID") + " - " + rs.getInt("medico_id"),
//                            rs.getString("nom_servicio"),
//                            rs.getString("nombres") + " " + rs.getString("apepaterno"),
//                            obtenerHoraEntrada(), obtenerHoraSalida(), txtNotaDetalleCita.getText()
//                        });
//                    }
//
//                    if (!hasData) {
//                        JOptionPane.showMessageDialog(this, "No se encontraron datos para el servicio y médico especificados.");
//                    }
//
//                    tblDetalleServicio.setModel(modelo); // Asegurar que el modelo actualizado esté en la tabla
//                }
//
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(this, "Error al llenar tabla detalle servicio: " + e.getMessage());
//            }
//        }
//    }
    private void limpiarControles() {
        txtAObservacion.setText("");
        txtCodDuenio.setText("");
        txtCodMascota.setText("");
        txtCodServicio.setText("");
        txtDocDuenio.setText("");
        txtDocMedico.setText("");
        txtEdadMascota.setText("");
        txtNombreCliente.setText("");
        txtNombreMascota.setText("");
        txtNombreMedico.setText("");
//        txtNotaDetalleCita.setText("");
        txtNotaMascota.setText("");
        txtNumero.setText("");
        txtTelefono.setText("");

        cboServicios.setSelectedIndex(0);
    }

    private void limpiarControlesDespuesDeAgregarServicio() {

//        txtNotaDetalleCita.setText("");
        Calendar calendario = Calendar.getInstance();
        calendario.set(Calendar.HOUR_OF_DAY, 12);
        calendario.set(Calendar.MINUTE, 0);
        calendario.set(Calendar.SECOND, 0);
        calendario.set(Calendar.MILLISECOND, 0);

        Date horaCero = calendario.getTime();

        cboServicios.setSelectedIndex(0);
        txtDocMedico.setText("");
        txtCodServicio.setText("");
        txtNombreMedico.setText("");
    }

    private void llenarCboServicios() {
        ResultSet rsServicios = null;
        DefaultComboBoxModel modeloSer = new DefaultComboBoxModel();
        cboServicios.setModel(modeloSer);

        try {
            rsServicios = objServicio.listarServicios();

            while (rsServicios.next()) {
                modeloSer.addElement(rsServicios.getString("nom_servicio"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Error al listar en interfaz los servicios");
        }
    }

    private void llenarServicioMedico() {
        ResultSet rsSer;
        ResultSet rsMed;
        String cadena = String.valueOf(tblDetalleServicio.getValueAt(tblDetalleServicio.getSelectedRow(), 0));
        String[] codigos = cadena.split(" - ");
        int codigoTablaSer = Integer.parseInt(codigos[0].trim());
        int codTablaMed = Integer.parseInt(codigos[1].trim());

        try {
            rsSer = objServicio.buscarServicio(codigoTablaSer);
            rsMed = objMedico.buscarMedico(codTablaMed);

            if (rsSer.next()) {
                cboServicios.setSelectedItem(rsSer.getString("nom_servicio"));
            }

            if (rsMed.next()) {
                txtDocMedico.setText(rsMed.getString("doc_identidad"));
                txtNombreMedico.setText(rsMed.getString("nombres") + " " + rsMed.getString("apepaterno") + " " + rsMed.getString("apematerno"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Error al llenar campos SER-MED " + e.getMessage());
        }

    }

    private void buscarServicio() {
        ResultSet rsServicio = null;

        if (txtDocMedico.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Debe ingresar un DNI de médico");
        } else {
            try {

                rsServicio = objServicio.obtenerDatosDetalleServicio(Integer.parseInt(txtCodServicio.getText()), txtDocMedico.getText());

                if (rsServicio.next()) {
                    txtNombreMedico.setText(String.valueOf(rsServicio.getString("nombres") + " " + rsServicio.getString("apepaterno")
                            + " " + rsServicio.getString("apematerno")));

                } else {
                    JOptionPane.showMessageDialog(this, "Este veterinario no brinda dicho servicio");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnDarBaja = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtCodServicio = new javax.swing.JTextField();
        btnAgregarServicio = new javax.swing.JButton();
        btnEliminarServicio = new javax.swing.JButton();
        txtDocMedico = new javax.swing.JTextField();
        txtNombreMedico = new javax.swing.JTextField();
        cboServicios = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAObservacion = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDocDuenio = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnBuscarDuenio = new javax.swing.JButton();
        txtCodDuenio = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalleServicio = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtNombreMascota = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtEdadMascota = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtNotaMascota = new javax.swing.JTextField();
        btnBuscarMascota = new javax.swing.JButton();
        txtCodMascota = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        btnNuevo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/veterinario.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/editar.png"))); // NOI18N
        btnModificar.setText("Modificar");

        btnDarBaja.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnDarBaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/darBaja.png"))); // NOI18N
        btnDarBaja.setText("Dar baja");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDarBaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDarBaja)
                .addGap(63, 63, 63))
        );

        jPanel10.setBackground(new java.awt.Color(204, 255, 204));

        jLabel21.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel21.setText("Servicio:");

        jLabel22.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel22.setText("Médico:");

        txtCodServicio.setMinimumSize(new java.awt.Dimension(61, 22));
        txtCodServicio.setPreferredSize(new java.awt.Dimension(61, 22));
        txtCodServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodServicioActionPerformed(evt);
            }
        });

        btnAgregarServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/veterinario.png"))); // NOI18N
        btnAgregarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarServicioActionPerformed(evt);
            }
        });

        btnEliminarServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/eliminar.png"))); // NOI18N
        btnEliminarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarServicioActionPerformed(evt);
            }
        });

        txtDocMedico.setMinimumSize(new java.awt.Dimension(61, 22));
        txtDocMedico.setPreferredSize(new java.awt.Dimension(61, 22));
        txtDocMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDocMedicoActionPerformed(evt);
            }
        });

        txtNombreMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreMedicoActionPerformed(evt);
            }
        });

        cboServicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboServiciosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel21))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCodServicio, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                    .addComponent(txtDocMedico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombreMedico)
                    .addComponent(cboServicios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnEliminarServicio)
                .addGap(18, 18, 18)
                .addComponent(btnAgregarServicio)
                .addGap(14, 14, 14))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregarServicio)
                    .addComponent(btnEliminarServicio)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtCodServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboServicios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txtDocMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(204, 255, 255));

        jLabel16.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel16.setText("Observación:");

        txtAObservacion.setColumns(20);
        txtAObservacion.setRows(5);
        jScrollPane3.setViewportView(txtAObservacion);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel1.setText("Doc. Ide:");

        txtDocDuenio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDocDuenioKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel2.setText("Dueño:");

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel8.setText("Teléfono:");

        btnBuscarDuenio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/buscar-pequeño.png"))); // NOI18N
        btnBuscarDuenio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarDuenioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtDocDuenio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscarDuenio))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtCodDuenio, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombreCliente))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTelefono)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscarDuenio)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDocDuenio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtCodDuenio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(51, 0, 51));

        tblDetalleServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetalleServicioMouseClicked(evt);
            }
        });
        tblDetalleServicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblDetalleServicioKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblDetalleServicio);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(4, 222, 222));

        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel10.setText("Día de la cita:");

        jLabel11.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel11.setText("Número de la cita:");

        txtNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jLabel11)
                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(204, 255, 204));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel7.setText("Nombre:");

        txtNombreMascota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreMascotaKeyPressed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel12.setText("Mascota:");

        jLabel19.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel19.setText("Nota:");

        btnBuscarMascota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conector/Recursos/buscar-pequeño.png"))); // NOI18N
        btnBuscarMascota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarMascotaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel12)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txtNombreMascota, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarMascota))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txtCodMascota, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEdadMascota))
                    .addComponent(txtNotaMascota))
                .addGap(0, 32, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscarMascota)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNombreMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEdadMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtCodMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtNotaMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(0, 153, 153));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Datos de la mascota");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(0, 153, 153));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Elegir servicio");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(213, 213, 213)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(0, 153, 153));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Datos del dueño");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(62, 62, 62))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // Guardar la cita y el detalle de la cita, para luego updatear simplemente el estado de la cita y el contenido del detalle

        Date fechaSeleccionada = jDateChooser1.getDate();
        if (fechaSeleccionada != null) {
            // Convertir la fecha a java.sql.Date
            java.sql.Date fechaCita = new java.sql.Date(fechaSeleccionada.getTime());

            // Obtener otros datos necesarios
            int numero = Integer.parseInt(txtNumero.getText());
            String observacion = txtAObservacion.getText();

            if (observacion.isEmpty()) {
                observacion = "Sin observación";
            }

//            System.out.println("Datos de Cita:");
//            System.out.println("Número: " + numero);
//            System.out.println("Estado Cita ID: " + 1);
//            System.out.println("Fecha Cita: " + fechaCita);
//            System.out.println("Observación: " + observacion);
//            System.out.println("Código Dueño: " + Integer.parseInt(txtCodDuenio.getText()));
//            System.out.println("Código Mascota: " + Integer.parseInt(txtCodMascota.getText()));
//
//            System.out.println("Datos de Detalle de Cita:");
//            System.out.println("Hora Entrada: " + horaEntrada);
//            System.out.println("Hora Salida: " + horaSalida);
//            System.out.println("Código Servicio: " + servicioCod);
//            System.out.println("Código Médico: " + medicoCod);
//            System.out.println("Nota detalle: " + notaDetalle);
            try {
//                objCita.insertarCita(numero, 1, fechaCita, observacion, Integer.parseInt(txtCodDuenio.getText()),
//                        Integer.parseInt(txtCodMascota.getText()));
//
//                for (int i = 0; i < tblDetalleServicio.getRowCount(); i++) {
//                    String cadena = String.valueOf(tblDetalleServicio.getValueAt(i, 0));
//                    String[] codigos = cadena.split(" - ");
//                    int codSer = Integer.parseInt(codigos[0].trim());
//                    int codMed = Integer.parseInt(codigos[1].trim());
//
//                    objDetalleCita.insertarDetalleCita(numero, codSer, codMed,
//                            horaEntrada, horaSalida, notaDetalle);
//                }

                if (fechaSeleccionada != null) {
                    try {
                        // Llamar al método registrarCita pasándole la fecha
                        objCita.registrarCita(1, Integer.parseInt(txtCodMascota.getText()), Integer.parseInt(txtCodDuenio.getText()), tblDetalleServicio, fechaCita, observacion);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Error al registrar cita: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Por favor, seleccione una fecha.");
                }

                JOptionPane.showMessageDialog(this, "Registrado exitosamente");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e.getMessage() + " AL CREAR CITA");
            }
            limpiarControles();
            generarCodigo();
            llenarTablaInicial();
//            medicoCod = -1;
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una fecha válida");
        }

    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtCodServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodServicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodServicioActionPerformed

    private void btnAgregarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarServicioActionPerformed

        jdAniadirServicio objAniadirServicio
                = new jdAniadirServicio((Frame) SwingUtilities.getWindowAncestor(this), true);
        objAniadirServicio.setLocationRelativeTo(this);
        objAniadirServicio.setVisible(true);

        int codServicio = objAniadirServicio.getCodServicio();
        int codMedico = objAniadirServicio.getCodMedico();
        String horaEntrada = objAniadirServicio.getHoraEntrada();
        String horaSalida = objAniadirServicio.getHoraSalida();
        String nota = objAniadirServicio.getNota();

        try {
            System.out.println(codServicio + " - " + codMedico);
            agregarServicio(codServicio, codMedico, horaEntrada, horaSalida, nota);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

        limpiarControlesDespuesDeAgregarServicio();

    }//GEN-LAST:event_btnAgregarServicioActionPerformed

    private void btnEliminarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarServicioActionPerformed
//        String cadena = String.valueOf(tblDetalleServicio.getValueAt(tblDetalleServicio.getSelectedRow(), 0));
//        String[] codigos = cadena.split(" - ");
//        int codigoSer = Integer.parseInt(txtCodServicio.getText());
//        int codTablaMed = Integer.parseInt(codigos[1].trim());

        int valor = Utilidad.mensajeConfirmarEliminarDetalleServicio(cboServicios.getSelectedItem().toString() + "\"");

        if (valor == 0) {
            DefaultTableModel modelito = (DefaultTableModel) tblDetalleServicio.getModel();
            modelito.removeRow(tblDetalleServicio.getSelectedRow());
        }

    }//GEN-LAST:event_btnEliminarServicioActionPerformed

    private void txtDocMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDocMedicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDocMedicoActionPerformed

    private void txtNombreMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreMedicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreMedicoActionPerformed

    private void cboServiciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboServiciosActionPerformed
        String nom_servicio = cboServicios.getSelectedItem().toString();

        try {
            Integer codServicio = objServicio.obtenerID(nom_servicio);
            txtCodServicio.setText(codServicio.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_cboServiciosActionPerformed

    private void txtDocDuenioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDocDuenioKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            if (txtDocDuenio.getText().length() == 8 || txtDocDuenio.getText().length() == 11) {
                btnBuscarDuenio.requestFocus();
                btnBuscarDuenioActionPerformed(null);

            } else {
                JOptionPane.showMessageDialog(this, "Ingresar DNI (8 dígitos) / RUC (11 dígitos)");
            }
        } else {
        }
    }//GEN-LAST:event_txtDocDuenioKeyPressed

    private void btnBuscarDuenioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarDuenioActionPerformed
        ResultSet rsCliente = null;

        try {

            rsCliente = objDuenio.buscarDuenioN(txtDocDuenio.getText());

            if (rsCliente.next()) {
                txtCodDuenio.setText(String.valueOf(rsCliente.getString("id")));
                txtNombreCliente.setText(String.valueOf(rsCliente.getString("nombres") + " " + rsCliente.getString("apepaterno")
                        + " " + rsCliente.getString("apematerno")));

                txtTelefono.setText(rsCliente.getString("telefono"));

            } else {
                if (JOptionPane.showConfirmDialog(this, "Dueño no existe ¿Desea registrarlo?", "Alerta!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    //jdMantDuenio_v2 objMantDuenio = new jdMantDuenio_v2(null, true);
                    jdMantDuenio objMantDuenio = new jdMantDuenio(null, true);
                    objMantDuenio.setLocationRelativeTo(this);
                    objMantDuenio.setVisible(true);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnBuscarDuenioActionPerformed

    private void txtNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroActionPerformed

    private void txtNombreMascotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreMascotaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreMascotaKeyPressed

    private void btnBuscarMascotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarMascotaActionPerformed
        ResultSet rsMascota = null;

        try {

            if (txtCodDuenio.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un dueño para buscar mascotas");
            } else if (txtNombreMascota.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar el nombre de la mascota");
            } else {
                rsMascota = objMascota.filtrarMascotaPorDuenioYNombre(Integer.valueOf(txtCodDuenio.getText()),
                        txtNombreMascota.getText());

                if (rsMascota.next()) {
                    txtCodMascota.setText(String.valueOf(rsMascota.getString("id")));
                    txtNotaMascota.setText(String.valueOf(rsMascota.getString("notaAdicional")));
                    txtEdadMascota.setText(String.valueOf(objMascota.calcularEdadMascota(rsMascota.getInt("id"))));

//                    if (rsMascota.getBoolean("esterilizado")) {
//                        rdbCastrado.setSelected(true);
//                    } else {
//                        rdbNoCastrado.setSelected(true);
//                    }
                } else {
                    if (JOptionPane.showConfirmDialog(this, "Mascota no existe ¿Desea registrar?", "Alerta!",
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        jdMantMascota objMantMascota = new jdMantMascota(null, true);

                        //jdMantMascotaNOVALE objMantMascota = new jdMantMascotaNOVALE(null, true);
                        objMantMascota.setLocationRelativeTo(this);
                        objMantMascota.setVisible(true);
                    }
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnBuscarMascotaActionPerformed

    private void tblDetalleServicioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblDetalleServicioKeyPressed

//        ResultSet rsMed;
//                
//        String cadena = String.valueOf(tblDetalleServicio.getValueAt(tblDetalleServicio.getSelectedRow(), 0));
//        String[] codigos = cadena.split(" - ");
//        int codigoTablaSer = Integer.parseInt(codigos[0].trim());
//        int codTablaMed = Integer.parseInt(codigos[1].trim());
//        
//        try {
//            rsMed = objMedico.buscarMedico(codTablaMed);
//            txtDocMedico.setText(rsMed.getString("doc_medico"));
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(this, ex.getMessage());
//        }
//        
//
//        buscarServicio();
    }//GEN-LAST:event_tblDetalleServicioKeyPressed

    private void tblDetalleServicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetalleServicioMouseClicked
        llenarServicioMedico();
    }//GEN-LAST:event_tblDetalleServicioMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarServicio;
    private javax.swing.JButton btnBuscarDuenio;
    private javax.swing.JButton btnBuscarMascota;
    private javax.swing.JButton btnDarBaja;
    private javax.swing.JButton btnEliminarServicio;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboServicios;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblDetalleServicio;
    private javax.swing.JTextArea txtAObservacion;
    private javax.swing.JTextField txtCodDuenio;
    private javax.swing.JTextField txtCodMascota;
    private javax.swing.JTextField txtCodServicio;
    private javax.swing.JTextField txtDocDuenio;
    private javax.swing.JTextField txtDocMedico;
    private javax.swing.JTextField txtEdadMascota;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombreMascota;
    private javax.swing.JTextField txtNombreMedico;
    private javax.swing.JTextField txtNotaMascota;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}

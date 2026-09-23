package examen.actividades.view;

import examen.actividades.controller.ActividadController;
import examen.actividades.model.TipoActividad;
import javax.swing.*;
import java.awt.*;

public class VentanaActividades extends JFrame {
    public JTextField txtCodigo = new JTextField(10);
    public JTextField txtNombre = new JTextField(20);
    public JTextField txtTarifaBase = new JTextField(10);
    public JTextField txtCupoTotal = new JTextField(5);
    public JComboBox<TipoActividad> cmbTipo = new JComboBox<>(TipoActividad.values());

    public JTextField txtCodigoConsulta = new JTextField(10);
    public JTextArea txaResultados = new JTextArea(15, 60);
    public JLabel lblMensaje = new JLabel(" ");

    public JButton btnRegistrar = new JButton("Registrar");
    public JButton btnBuscar = new JButton("Buscar");
    public JButton btnInscribir = new JButton("Inscribir");
    public JButton btnMostrarTodas = new JButton("Mostrar Todas");
    public JButton btnLimpiar = new JButton("Limpiar");
    public JButton btnGuardar = new JButton("Guardar");

    public VentanaActividades() {
        setTitle("Gestión de Actividades y Cupos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        txaResultados.setEditable(false);
        lblMensaje.setForeground(Color.RED);

        JPanel pnlForm = new JPanel(new GridLayout(6, 2, 5, 5));
        pnlForm.setBorder(BorderFactory.createTitledBorder("Registro de Actividad"));
        pnlForm.add(new JLabel("Código:")); pnlForm.add(txtCodigo);
        pnlForm.add(new JLabel("Nombre:")); pnlForm.add(txtNombre);
        pnlForm.add(new JLabel("Tarifa Base:")); pnlForm.add(txtTarifaBase);
        pnlForm.add(new JLabel("Cupo Total:")); pnlForm.add(txtCupoTotal);
        pnlForm.add(new JLabel("Tipo:")); pnlForm.add(cmbTipo);
        pnlForm.add(btnRegistrar); pnlForm.add(btnLimpiar);

        JPanel pnlAcciones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlAcciones.setBorder(BorderFactory.createTitledBorder("Consultas y Operaciones"));
        pnlAcciones.add(new JLabel("Código Consulta:"));
        pnlAcciones.add(txtCodigoConsulta);
        pnlAcciones.add(btnBuscar);
        pnlAcciones.add(btnInscribir);
        pnlAcciones.add(btnMostrarTodas);
        pnlAcciones.add(btnGuardar);

        JPanel pnlNorte = new JPanel(new BorderLayout());
        pnlNorte.add(pnlForm, BorderLayout.NORTH);
        pnlNorte.add(pnlAcciones, BorderLayout.SOUTH);

        add(pnlNorte, BorderLayout.NORTH);
        add(new JScrollPane(txaResultados), BorderLayout.CENTER);
        add(lblMensaje, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }
}


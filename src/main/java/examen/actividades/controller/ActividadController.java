package examen.actividades.controller;

import examen.actividades.model.Actividad;
import examen.actividades.model.TipoActividad;
import examen.actividades.repository.RepositorioActividadTxt;
import examen.actividades.service.ActividadService;
import examen.actividades.view.VentanaActividades;

import java.util.List;

public class ActividadController {
    private VentanaActividades vista;
    private ActividadService servicio;

    public ActividadController(VentanaActividades vista) {
        this.vista = vista;
        this.servicio = new ActividadService(new RepositorioActividadTxt("actividades.txt"));
        conectarEventos();
        iniciar();
    }

    private void conectarEventos() {
        vista.btnRegistrar.addActionListener(e -> registrar());
        vista.btnBuscar.addActionListener(e -> buscar());
        vista.btnInscribir.addActionListener(e -> inscribir());
        vista.btnMostrarTodas.addActionListener(e -> mostrarTodas());
        vista.btnLimpiar.addActionListener(e -> limpiar());
        vista.btnGuardar.addActionListener(e -> guardarDatos());
        vista.txtCodigoConsulta.addActionListener(e -> buscar());
    }

    public void iniciar() {
        vista.cmbTipo.setSelectedItem(null);
        try {
            servicio.cargarDatos();
            mostrarTodas();
            vista.lblMensaje.setText("Datos cargados correctamente.");
        } catch (Exception e) {
            vista.lblMensaje.setText("Error al cargar datos: " + e.getMessage());
        }
    }

    public void registrar() {
        vista.lblMensaje.setText(" ");
        try {
            String codigo = vista.txtCodigo.getText();
            String nombre = vista.txtNombre.getText();
            TipoActividad tipo = (TipoActividad) vista.cmbTipo.getSelectedItem();
            double tarifaBase = Double.parseDouble(vista.txtTarifaBase.getText());
            int cupoTotal = Integer.parseInt(vista.txtCupoTotal.getText());

            servicio.registrarActividad(codigo, nombre, tipo, tarifaBase, cupoTotal);
            mostrarTodas();
            vista.lblMensaje.setText("Actividad registrada con éxito.");
        } catch (NumberFormatException ex) {
            vista.lblMensaje.setText("Error numérico: Tarifa y cupo deben ser valores válidos.");
        } catch (Exception ex) {
            vista.lblMensaje.setText(ex.getMessage());
        }
    }

    public void buscar() {
        vista.lblMensaje.setText(" ");
        vista.txaResultados.setText("");
        try {
            Actividad act = servicio.buscarPorCodigo(vista.txtCodigoConsulta.getText());
            if (act != null) {
                mostrarActividad(act);
            } else {
                vista.txaResultados.setText("Actividad no encontrada.");
            }
        } catch (Exception ex) {
            vista.lblMensaje.setText(ex.getMessage());
        }
    }

    public void inscribir() {
        vista.lblMensaje.setText(" ");
        try {
            servicio.inscribir(vista.txtCodigoConsulta.getText());
            buscar();
            vista.lblMensaje.setText("Inscripción realizada con éxito.");
        } catch (Exception ex) {
            vista.lblMensaje.setText(ex.getMessage());
        }
    }

    public void mostrarTodas() {
        vista.lblMensaje.setText(" ");
        vista.txaResultados.setText("");
        List<Actividad> lista = servicio.listarActividades();
        if (lista.isEmpty()) {
            vista.txaResultados.setText("No hay actividades registradas.");
            return;
        }
        for (Actividad act : lista) {
            mostrarActividad(act);
            vista.txaResultados.append("\n-----------------------------------\n");
        }
    }

    public void limpiar() {
        vista.txtCodigo.setText("");
        vista.txtNombre.setText("");
        vista.txtTarifaBase.setText("");
        vista.txtCupoTotal.setText("");
        vista.cmbTipo.setSelectedItem(null);
        vista.lblMensaje.setText("Campos limpiados.");
    }

    public void guardarDatos() {
        try {
            servicio.guardarDatos();
            vista.lblMensaje.setText("Datos guardados correctamente.");
        } catch (Exception e) {
            vista.lblMensaje.setText("Error al guardar: " + e.getMessage());
        }
    }

    private void mostrarActividad(Actividad act) {
        vista.txaResultados.append(String.format(
                "Código: %s | Nombre: %s | Tipo: %s\nTarifa Final: %.2f | Cupo Total: %d | Inscritos: %d | Disponibles: %d",
                act.getCodigo(), act.getNombre(), act.getTipo(),
                act.calcularTarifaFinal(), act.getCupoTotal(), act.getInscritos(), act.getCuposDisponibles()
        ));
    }
}


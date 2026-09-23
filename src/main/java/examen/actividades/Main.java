package examen.actividades;

import examen.actividades.controller.ActividadController;
import examen.actividades.view.VentanaActividades;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaActividades ventana = new VentanaActividades();
            new ActividadController(ventana);
            ventana.setVisible(true);
        });
    }
}
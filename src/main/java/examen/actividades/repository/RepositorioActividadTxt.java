package examen.actividades.repository;

import examen.actividades.repository.Repositorio;
import examen.actividades.model.Actividad;
import examen.actividades.model.ActividadPresencial;
import examen.actividades.model.ActividadVirtual;
import examen.actividades.model.TipoActividad;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioActividadTxt implements Repositorio<Actividad> {
    private String rutaArchivo;

    public RepositorioActividadTxt(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    public List<Actividad> cargarTodos() throws IOException {
        List<Actividad> lista = new ArrayList<>();
        File archivo = new File(rutaArchivo);
        if (!archivo.exists() || archivo.length() == 0) {
            return lista;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    lista.add(convertirDesdeLinea(linea));
                }
            }
        }
        return lista;
    }

    @Override
    public void guardarTodos(List<Actividad> elementos) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, false))) {
            for (Actividad act : elementos) {
                bw.write(convertirALinea(act));
                bw.newLine();
            }
        }
    }

    private String convertirALinea(Actividad actividad) {
        return actividad.getTipo().name() + ";" +
                actividad.getCodigo() + ";" +
                actividad.getNombre() + ";" +
                actividad.getTarifaBase() + ";" +
                actividad.getCupoTotal() + ";" +
                actividad.getInscritos();
    }

    private Actividad convertirDesdeLinea(String linea) {
        String[] partes = linea.split(";");
        TipoActividad tipo = TipoActividad.valueOf(partes[0].trim());
        String codigo = partes[1].trim();
        String nombre = partes[2].trim();
        double tarifaBase = Double.parseDouble(partes[3].trim());
        int cupoTotal = Integer.parseInt(partes[4].trim());
        int inscritos = Integer.parseInt(partes[5].trim());

        if (tipo == TipoActividad.PRESENCIAL) {
            return new ActividadPresencial(codigo, nombre, tarifaBase, cupoTotal, inscritos);
        } else {
            return new ActividadVirtual(codigo, nombre, tarifaBase, cupoTotal, inscritos);
        }
    }
}
package examen.actividades.service;

import examen.actividades.model.Actividad;
import examen.actividades.model.ActividadPresencial;
import examen.actividades.model.ActividadVirtual;
import examen.actividades.model.TipoActividad;
import examen.actividades.repository.Repositorio;

import java.util.ArrayList;
import java.util.List;

public class ActividadService {

    private List<Actividad> actividades;
    private Repositorio<Actividad> repositorio;

    public ActividadService(Repositorio<Actividad> repositorio) {

        this.repositorio = repositorio;
        this.actividades = new ArrayList<>();
    }

    public void registrarActividad(String codigo, String nombre, TipoActividad tipo, double tarifaBase, int cupoTotal) {

        if (tipo == null) {
            throw new IllegalArgumentException("Debe seleccionar un tipo de actividad válido.");
        }


        if (codigo != null && !codigo.trim().isEmpty()) {

            String codLimpio = codigo.trim();
            for (Actividad act : actividades) {
                if (act.getCodigo().equalsIgnoreCase(codLimpio)) {
                    throw new IllegalArgumentException("El código de la actividad ya se encuentra registrado.");
                }
            }
        }

        Actividad nuevaActividad;

        if (tipo == TipoActividad.PRESENCIAL) {
            nuevaActividad = new ActividadPresencial(codigo, nombre, tarifaBase, cupoTotal, 0);
        } else {
            nuevaActividad = new ActividadVirtual(codigo, nombre, tarifaBase, cupoTotal, 0);
        }

        this.actividades.add(nuevaActividad);
    }

    public Actividad buscarPorCodigo(String codigo) {

        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código de búsqueda no puede estar vacío.");
        }

        String codLimpio = codigo.trim();

        for (Actividad act : actividades) {

            if (act.getCodigo().equalsIgnoreCase(codLimpio)) {
                return act;
            }
        }
        return null;
    }

    public List<Actividad> listarActividades() {

        return new ArrayList<>(this.actividades);
    }

    public void inscribir(String codigo) {

        Actividad act = buscarPorCodigo(codigo);


        if (act == null) {
            throw new IllegalArgumentException("La actividad indicada no existe.");
        }


        act.inscribir();
    }

    public void cargarDatos() throws Exception {

        List<Actividad> datosCargados = repositorio.cargarTodos();


        this.actividades = datosCargados;
    }

    public void guardarDatos() throws Exception {

        repositorio.guardarTodos(this.actividades);
    }
}

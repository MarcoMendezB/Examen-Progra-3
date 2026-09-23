package examen.actividades.repository;

import java.util.List;

public interface Repositorio<T> {
    List<T> cargarTodos() throws Exception;
    void guardarTodos(List<T> elementos) throws Exception;
}
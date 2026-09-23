package examen.actividades.model;

public abstract class Actividad {
    private String codigo;
    private String nombre;
    private double tarifaBase;
    private int cupoTotal;
    private int inscritos;

    public Actividad(String codigo, String nombre, double tarifaBase, int cupoTotal, int inscritos) {
        if (codigo == null || codigo.trim().isEmpty() || codigo.contains(";") || codigo.contains("\n")) {
            throw new IllegalArgumentException("Código inválido.");
        }
        if (nombre == null || nombre.trim().isEmpty() || nombre.contains(";") || nombre.contains("\n")) {
            throw new IllegalArgumentException("Nombre inválido.");
        }
        if (tarifaBase <= 0 || !Double.isFinite(tarifaBase)) {
            throw new IllegalArgumentException("La tarifa base debe ser mayor a cero.");
        }
        if (cupoTotal <= 0) {
            throw new IllegalArgumentException("El cupo total debe ser mayor a cero.");
        }
        if (inscritos < 0 || inscritos > cupoTotal) {
            throw new IllegalArgumentException("Cantidad de inscritos inválida.");
        }

        this.codigo = codigo.trim().toUpperCase();
        this.nombre = nombre.trim();
        this.tarifaBase = tarifaBase;
        this.cupoTotal = cupoTotal;
        this.inscritos = inscritos;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getTarifaBase() { return tarifaBase; }
    public int getCupoTotal() { return cupoTotal; }
    public int getInscritos() { return inscritos; }

    public int getCuposDisponibles() {
        return cupoTotal - inscritos;
    }

    public void inscribir() {
        if (inscritos >= cupoTotal) {
            throw new IllegalStateException("No hay cupos disponibles para esta actividad.");
        }
        inscritos++;
    }

    public abstract double calcularTarifaFinal();
    public abstract TipoActividad getTipo();
}


package hotel;

public class TipoHabitacion {

    // Encapsulamiento
    private String nombreTipo;
    private double precioBase;

    // Constructor
    public TipoHabitacion(String nombreTipo, double precioBase) {
        this.nombreTipo = nombreTipo;
        this.precioBase = precioBase;
    }

    // Getters
    public String getNombreTipo() {
        return nombreTipo;
    }

    public double getPrecioBase() {
        return precioBase;
    }
}
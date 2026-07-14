package hotel;

public abstract class Habitacion {

    // Encapsulamiento protegido para acceso directo
    private int numero;
    private boolean disponible;
    private int capacidad;
    private boolean aireAcondicionado;
    protected TipoHabitacion tipo;

    // constructor
    public Habitacion(int numero, int capacidad, boolean aireAcondicionado, TipoHabitacion tipo) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.aireAcondicionado = aireAcondicionado;
        this.tipo = tipo;
        disponible = true;
    }

    // Método abstracto
    public abstract double calcularPago(int dias);

    // getters
    public int getNumero() {
        return numero;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public boolean isAireAcondicionado() {
        return aireAcondicionado;
    }

    // Setter
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    // sobreescritura de object modificada para mostrar el tipo de habitacion limpio en la interfaz grafica
    @Override
    public String toString() {
        return "Hab. " + numero + " - " + tipo.getNombreTipo();
    }

    // Metodo
    public void mostrarHabitacion() {
        System.out.println("Habitación: " + numero);
        System.out.println("Tipo: " + tipo.getNombreTipo());
        System.out.println("Capacidad: " + capacidad);
        
        if (aireAcondicionado) {
            System.out.println("Tiene aire acondicionado");
        } else {
            System.out.println("Tiene ventilador");
        }
    }
}

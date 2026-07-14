package hotel;

public class HabitacionSimple extends Habitacion {

    // Constructor
    public HabitacionSimple(int numero, int capacidad, boolean aireAcondicionado) {
        super(numero, capacidad, aireAcondicionado, new TipoHabitacion("Simple", 85.0));
    }

    // POLIMORFISMO
    @Override
    public double calcularPago(int dias) {
        return dias * tipo.getPrecioBase();
    }
}
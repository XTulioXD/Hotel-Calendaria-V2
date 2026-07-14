package Hotel;

public class HabitacionVIP extends Habitacion {

    // Constructor
    public HabitacionVIP(int numero, int capacidad, boolean aireAcondicionado) {
        super(numero, capacidad, aireAcondicionado, new TipoHabitacion("VIP", 195.0));
    }

    // POLIMORFISMO
    @Override
    public double calcularPago(int dias) {
        return dias * tipo.getPrecioBase();
    }
}
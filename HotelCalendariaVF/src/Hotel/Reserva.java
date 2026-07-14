package hotel;

public class Reserva {

    // NUEVO: Atributo para el ID de la reserva
    private int idReserva;
    private Cliente cliente;
    private Empleado empleado;
    private Habitacion habitacion;
    private int dias;
    private Pago pago;

    public Reserva(Cliente cliente, Empleado empleado, Habitacion habitacion, int dias) {
        this.cliente = cliente;
        this.empleado = empleado;
        this.habitacion = habitacion;
        this.dias = dias;
        
        this.habitacion.setDisponible(false); 
        
        double montoTotal = habitacion.calcularPago(dias);
        this.pago = new Pago(montoTotal);
    }

    // NUEVOS: Getter y Setter para controlar el ID
    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public void modificarEstadia(int nuevosDias) {
        this.dias = nuevosDias;
        double nuevoMonto = habitacion.calcularPago(nuevosDias);
        this.pago = new Pago(nuevoMonto);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public int getDias() {
        return dias;
    }

    public Pago getPago() {
        return pago;
    }

    public void mostrarReserva() {
        System.out.println("DETALLE DE OPERACIÓN DE RESERVA:");
        System.out.println("ID Reserva: " + idReserva); // Agregado en el texto
        cliente.mostrarCliente();
        System.out.println("Atendido por: " + empleado.nombre); 
        System.out.println("..................................");
        habitacion.mostrarHabitacion();
        System.out.println("Tiempo de estadía: " + dias + " días");
        pago.mostrarPago();
        System.out.println("Estado de Reserva: Registrada");
    }
}

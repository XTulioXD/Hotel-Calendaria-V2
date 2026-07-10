package hotel;

public class EliminarReservaCommand implements Command {
    private GestionReservas control;
    private int numeroHabitacion;

    public EliminarReservaCommand(GestionReservas control, int numeroHabitacion) {
        this.control = control;
        this.numeroHabitacion = numeroHabitacion;
    }

    @Override
    public void ejecutar() {
        control.eliminarReserva(numeroHabitacion);
    }
}

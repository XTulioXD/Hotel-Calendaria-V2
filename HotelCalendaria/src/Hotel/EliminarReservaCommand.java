package Hotel;

public class EliminarReservaCommand implements Command {
    private GestionReservas control;
    private Reserva reserva;

    public EliminarReservaCommand(GestionReservas control, Reserva reserva) {
        this.control = control;
        this.reserva = reserva;
    }

    @Override
    public void ejecutar() {
        control.eliminarReserva(reserva);
    }
}

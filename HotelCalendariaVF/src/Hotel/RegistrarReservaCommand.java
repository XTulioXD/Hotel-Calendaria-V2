package hotel;

public class RegistrarReservaCommand implements Command {
    private GestionReservas control;
    private Reserva reserva;

    public RegistrarReservaCommand(GestionReservas control, Reserva reserva) {
        this.control = control;
        this.reserva = reserva;
    }

    @Override
    public void ejecutar() {
        control.registrarReserva(reserva);
    }
}

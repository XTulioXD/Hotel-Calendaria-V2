package Hotel;

public class ModificarReservaCommand implements Command {
    private GestionReservas control;
    private Reserva reserva;
    // private int nuevosDias;
    private int nuevasPersonas;

    public ModificarReservaCommand(GestionReservas control, Reserva reserva, int nuevasPersonas) {
        this.control = control;
        this.reserva = reserva;
        this.nuevasPersonas = nuevasPersonas;
    }

    @Override
    public void ejecutar() {
        control.modificarReserva(reserva, nuevasPersonas);
    }
}

package hotel;

public class ModificarReservaCommand implements Command {
    private GestionReservas control;
    private int numeroHabitacion;
    private int nuevosDias;
    private int nuevasPersonas;

    public ModificarReservaCommand(GestionReservas control, int numeroHabitacion, int nuevosDias, int nuevasPersonas) {
        this.control = control;
        this.numeroHabitacion = numeroHabitacion;
        this.nuevosDias = nuevosDias;
        this.nuevasPersonas = nuevasPersonas;
    }

    @Override
    public void ejecutar() {
        control.modificarReserva(numeroHabitacion, nuevosDias, nuevasPersonas);
    }
}

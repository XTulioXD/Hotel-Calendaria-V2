package hotel;

import java.util.*;

public class GestionReservas {

    private ArrayList<Reserva> listaReservas;
    private LinkedList<Cliente> listaClientes;
    private LinkedList<Empleado> listaEmpleados;
    private LinkedList<Habitacion> listaHabitaciones;

    private int contadorId;

    public GestionReservas() {
        listaReservas = new ArrayList<>();
        listaClientes = new LinkedList<>();
        listaEmpleados = new LinkedList<>();
        listaHabitaciones = new LinkedList<>();
        this.contadorId = 1;
    }

    public void registrarReserva(Reserva nuevaReserva) {
        nuevaReserva.setIdReserva(contadorId);
        contadorId++;

        listaReservas.add(nuevaReserva);
        listaClientes.add(nuevaReserva.getCliente());

        Empleado empleadoEncontrado = listaEmpleados.stream()
                .filter(emp -> emp.getNombre().equalsIgnoreCase(nuevaReserva.getEmpleado().getNombre()))
                .findFirst()
                .orElse(null);

        if (empleadoEncontrado == null) {
            listaEmpleados.add(nuevaReserva.getEmpleado());
        }
    }

    public Reserva buscarReserva(int numeroHabitacion) {
        return listaReservas.stream()
                .filter(r -> r.getHabitacion().getNumero() == numeroHabitacion)
                .findFirst()
                .orElse(null);
    }

    public boolean modificarReserva(int numeroHabitacion, int nuevosDias, int nuevasPersonas) {
        Reserva reservaEncontrada = buscarReserva(numeroHabitacion);
        if (reservaEncontrada != null) {
            reservaEncontrada.modificarEstadia(nuevosDias);
            reservaEncontrada.getCliente().setCantidadPersonas(nuevasPersonas);
            return true;
        }
        return false;
    }

    public boolean eliminarReserva(int numeroHabitacion) {
        Reserva reservaEncontrada = buscarReserva(numeroHabitacion);
        if (reservaEncontrada != null) {
            reservaEncontrada.getHabitacion().setDisponible(true);
            listaClientes.remove(reservaEncontrada.getCliente());
            listaReservas.remove(reservaEncontrada);
            return true;
        }
        return false;
    }

    public ArrayList<Reserva> getListaReservas() {
        return listaReservas;
    }

    public LinkedList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public LinkedList<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public LinkedList<Habitacion> getListaHabitaciones() {
        return listaHabitaciones;
    }
}

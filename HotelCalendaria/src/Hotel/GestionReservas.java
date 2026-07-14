package Hotel;

import Dao.ClienteDao;
import Dao.HabitacionDao;
import Dao.PagoDao;
import Dao.ReservaDao;
import java.util.*;

public class GestionReservas {

    private ArrayList<Reserva> listaReservas;
    private LinkedList<Cliente> listaClientes;
    private LinkedList<Empleado> listaEmpleados;
    private LinkedList<Habitacion> listaHabitaciones;

    // private int contadorId; // GIAN MODIFICO AQUI

    public GestionReservas() {
        listaReservas = new ArrayList<>();
        listaClientes = new LinkedList<>();
        listaEmpleados = new LinkedList<>();
        listaHabitaciones = new LinkedList<>();
        //this.contadorId = 1; // GIAN MODIFICO AQUI
    }

    public void registrarReserva(Reserva nuevaReserva) {
        // GIAN MODIFICO DESDE AQUI
        /*
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
        */
        Pago pagoCreado = PagoDao.insertar(nuevaReserva.getPago());
        if (pagoCreado != null) {
            nuevaReserva.setPago(pagoCreado);
            Reserva reservaCreada = ReservaDao.insertar(nuevaReserva);
            if (reservaCreada != null) {
                System.out.println("Se creo la reserva");
                Habitacion habitacion = reservaCreada.getHabitacion();
                habitacion.setDisponible(false);
                boolean habitacionActualizada = HabitacionDao.actualizar(habitacion, habitacion.getNumero());
                if (habitacionActualizada) {
                    System.out.println("Se actualizo la disponibilidad de la habitacion a false");
                }else{
                    System.out.println("Sucedio un error al actualizar la disponibilidad de la habitacion.");
                }
            }else{
                System.out.println("Hubo error al crear la reserva");
            }
        }else{
            System.out.println("Hubo un error al crear el pago.");
        }
        // GIAN MODIFICO HASTA AQUI
    }

    public Reserva buscarReserva(int numeroHabitacion) {
        return listaReservas.stream()
                .filter(r -> r.getHabitacion().getNumero() == numeroHabitacion)
                .findFirst()
                .orElse(null);
    }

    // GIAN MODIFICO DESDE AQUI
    public boolean modificarReserva(Reserva reserva, int nuevasPersonas) {
        /*
        Reserva reservaEncontrada = buscarReserva(numeroHabitacion);
        if (reservaEncontrada != null) {
            reservaEncontrada.modificarEstadia(nuevosDias);
            reservaEncontrada.getCliente().setCantidadPersonas(nuevasPersonas);
            return true;
        }
        return false;
        */
        if (reserva != null) {
            boolean cantidadPersonasActualizada = ClienteDao.actualizarCantidadPersonas(
                    nuevasPersonas, 
                    reserva.getCliente().getDni()
            );
            if (cantidadPersonasActualizada) {
                System.out.println("Se actualizó la cantidad de personas del Cliente");
            }

            boolean diasActualizado = ReservaDao.actualizarDias(
                    reserva.getDias(),
                    reserva.getIdReserva()
            );
            if (diasActualizado) {
                System.out.println("Se actualizo la cantidad de dias de la reserva");
                double nuevoMontoPago = reserva.getHabitacion().calcularPago(reserva.getDias());
                reserva.getPago().setMonto(nuevoMontoPago);
                boolean pagoActualizado = PagoDao.actualizar(reserva.getPago());
                if (pagoActualizado) {
                    System.out.println("Monto del Pago actualizado");
                }
            }
            return true;
        }
        return false;
    }
    // GIAN MODIFICO HASTA AQUI

    // GIAN MODIFICO DESDE AQUI
    public boolean eliminarReserva(Reserva reserva) {
        /*
        Reserva reservaEncontrada = buscarReserva(numeroHabitacion);
        if (reservaEncontrada != null) {
            reservaEncontrada.getHabitacion().setDisponible(true);
            listaClientes.remove(reservaEncontrada.getCliente());
            listaReservas.remove(reservaEncontrada);
            return true;
        }
        return false;
        */
        if (reserva != null) {
            boolean reservaEliminada = ReservaDao.eliminar(reserva.getIdReserva());
            if (reservaEliminada) {
                Habitacion habitacion = reserva.getHabitacion();
                habitacion.setDisponible(true);
                boolean habitacionActualizada = HabitacionDao.actualizar(habitacion, habitacion.getNumero());
                if (habitacionActualizada) {
                    System.out.println("Se actualizo la disponibilidad de la habitacion a true");
                }else{
                    System.out.println("Sucedio un error al actualizar la disponibilidad de la habitacion.");
                }
            }
            return true;
        }
        return false;
    }
    // GIAN MODIFICO HASTA AQUI

    // GIAN MODIFICO DESDE AQUI
    public List<Reserva> getListaReservas() {
        // return listaReservas;
        return ReservaDao.listarTodos();
    }
    // GIAN MODIFICO HASTA AQUI

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

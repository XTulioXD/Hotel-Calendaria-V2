package Dao;

import java.util.List;
import Hotel.Reserva;
import Hotel.Habitacion;
import Hotel.Empleado;
import Conexion.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ReservaDao {
    public static List<Reserva> listarTodos(){
        String sql = "SELECT * FROM reserva";

        try {
            // CREA LA CONEXION A LA BD
            Connection con = Conexion.getConexion();

            // PREPARA EL SCRIPT SQL
            PreparedStatement ps = con.prepareStatement(sql);

            // EJECUTA EL SCRIPT SQL Y RETORNA LOS DATOS 
            ResultSet rs = ps.executeQuery();
            
            List<Reserva> listaReserva = new ArrayList<>();
            while (rs.next()) {
                Reserva reserva = new Reserva(
                        rs.getInt("id_reserva"),
                        ClienteDao.obtenerCliente(rs.getString("cliente_dni")),
                        EmpleadoDao.obtenerEmpleado(rs.getString("empleado_dni")),
                        HabitacionDao.obtenerHabitacion(rs.getInt("habitacion_numero")),
                        rs.getInt("dias"),
                        PagoDao.obtenerPago(rs.getInt("pago_id"))
                );
                listaReserva.add(reserva);
            }
            
            // CERRAR EL RESULTSET
            rs.close();
            
            // CERRAR EL SCRIPT SQL PARA QUE NO CONSUMA MEMORIA
            ps.close();
            
            // CERRAR LA CONEXION A LA BD
            Conexion.cerrarConexion();

            return listaReserva;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static List<Reserva> listarReservasEmpleado(Empleado empleado){
        String sql = "SELECT * FROM reserva where empleado_dni = ?";

        try {
            // CREA LA CONEXION A LA BD
            Connection con = Conexion.getConexion();

            // PREPARA EL SCRIPT SQL
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, empleado.getDni());

            // EJECUTA EL SCRIPT SQL Y RETORNA LOS DATOS 
            ResultSet rs = ps.executeQuery();
            
            List<Reserva> listaReserva = new ArrayList<>();
            while (rs.next()) {
                Reserva reserva = new Reserva(
                        rs.getInt("id_reserva"),
                        ClienteDao.obtenerCliente(rs.getString("cliente_dni")),
                        EmpleadoDao.obtenerEmpleado(rs.getString("empleado_dni")),
                        HabitacionDao.obtenerHabitacion(rs.getInt("habitacion_numero")),
                        rs.getInt("dias"),
                        PagoDao.obtenerPago(rs.getInt("pago_id"))
                );
                listaReserva.add(reserva);
            }
            
            // CERRAR EL RESULTSET
            rs.close();
            
            // CERRAR EL SCRIPT SQL PARA QUE NO CONSUMA MEMORIA
            ps.close();
            
            // CERRAR LA CONEXION A LA BD
            Conexion.cerrarConexion();

            return listaReserva;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static List<Reserva> listarReservasHabitacion(Habitacion habitacion){
        String sql = "SELECT * FROM reserva where habitacion_numero = ?";

        try {
            // CREA LA CONEXION A LA BD
            Connection con = Conexion.getConexion();

            // PREPARA EL SCRIPT SQL
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, habitacion.getNumero());

            // EJECUTA EL SCRIPT SQL Y RETORNA LOS DATOS 
            ResultSet rs = ps.executeQuery();
            
            List<Reserva> listaReserva = new ArrayList<>();
            while (rs.next()) {
                Reserva reserva = new Reserva(
                        rs.getInt("id_reserva"),
                        ClienteDao.obtenerCliente(rs.getString("cliente_dni")),
                        EmpleadoDao.obtenerEmpleado(rs.getString("empleado_dni")),
                        HabitacionDao.obtenerHabitacion(rs.getInt("habitacion_numero")),
                        rs.getInt("dias"),
                        PagoDao.obtenerPago(rs.getInt("pago_id"))
                );
                listaReserva.add(reserva);
            }
            
            // CERRAR EL RESULTSET
            rs.close();
            
            // CERRAR EL SCRIPT SQL PARA QUE NO CONSUMA MEMORIA
            ps.close();
            
            // CERRAR LA CONEXION A LA BD
            Conexion.cerrarConexion();

            return listaReserva;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Reserva obtenerReserva(int idReserva) {

        String sql = "SELECT * FROM reserva WHERE id_reserva = ?";

        try {
            Connection con = Conexion.getConexion();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, idReserva);

            ResultSet rs = ps.executeQuery();

            Reserva reserva = null;

            if (rs.next()) {
                reserva = new Reserva(
                        rs.getInt("id_reserva"),
                        ClienteDao.obtenerCliente(rs.getString("cliente_dni")),
                        EmpleadoDao.obtenerEmpleado(rs.getString("empleado_dni")),
                        HabitacionDao.obtenerHabitacion(rs.getInt("habitacion_numero")),
                        rs.getInt("dias"),
                        PagoDao.obtenerPago(rs.getInt("pago_id"))
                );
            }

            rs.close();
            ps.close();
            Conexion.cerrarConexion();

            return reserva;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Reserva insertar(Reserva reserva) {

        String sql = "INSERT INTO reserva(cliente_dni,empleado_dni,habitacion_numero,dias,pago_id) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection con = Conexion.getConexion();

            PreparedStatement ps = con.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );

            ps.setString(1, reserva.getCliente().getDni());
            ps.setString(2, reserva.getEmpleado().getDni());
            ps.setInt(3, reserva.getHabitacion().getNumero());
            ps.setInt(4, reserva.getDias());
            ps.setInt(5, reserva.getPago().getIdPago());

            int filas = ps.executeUpdate();
            
            if (filas > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                
                if (rs.next()) {
                    reserva.setIdReserva(rs.getInt("id_reserva"));
                }

                rs.close();
            }

            ps.close();
            
            Conexion.cerrarConexion();

            return reserva;

        } catch (SQLException e) {
            System.out.println("Dio error al crear");
            e.printStackTrace();
            return null;
        }
    }
    
    public static boolean actualizarDias(int nuevosDias, int idReserva) {

        String sql = "UPDATE reserva SET dias = ? WHERE id_reserva = ?";

        try {
            Connection con = Conexion.getConexion();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, nuevosDias);
            ps.setInt(2, idReserva);

            ps.executeUpdate();

            ps.close();
            
            Conexion.cerrarConexion();

            return true;

        } catch (SQLException e) {
            System.out.println("Dio error al actualizar");
            e.printStackTrace();
            return false;
        }
    }

    public static boolean eliminar(int idReserva) {

        String sql = "DELETE FROM reserva WHERE id_reserva = ?";

        try {
            Connection con = Conexion.getConexion();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, idReserva);

            ps.executeUpdate();

            ps.close();
            
            Conexion.cerrarConexion();

            return true;

        } catch (SQLException e) {
            System.out.println("Dio error al eliminar");
            e.printStackTrace();
            return false;
        }
    }
}

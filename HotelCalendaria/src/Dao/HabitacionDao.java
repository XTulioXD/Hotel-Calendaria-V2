package Dao;

import Conexion.Conexion;
import Hotel.Habitacion;
import Hotel.HabitacionSimple;
import Hotel.HabitacionVIP;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDao {
    public static List<Habitacion> listarTodos(){
        String sql = "SELECT * FROM habitacion";

        try {
            // CREA LA CONEXION A LA BD
            Connection con = Conexion.getConexion();

            // PREPARA EL SCRIPT SQL
            PreparedStatement ps = con.prepareStatement(sql);

            // EJECUTA EL SCRIPT SQL Y RETORNA LOS DATOS 
            ResultSet rs = ps.executeQuery();
            
            List<Habitacion> lista = new ArrayList<>();
            while (rs.next()) {
                Habitacion habitacion  = null;
                if(rs.getString("tipo").equalsIgnoreCase("Simple")){
                    habitacion = new HabitacionSimple(
                            rs.getInt("numero"),
                            rs.getInt("capacidad"),
                            rs.getBoolean("aire_acondicionado")
                    );
                }else{
                    habitacion = new HabitacionVIP(
                            rs.getInt("numero"),
                            rs.getInt("capacidad"),
                            rs.getBoolean("aire_acondicionado")
                    );
                }
                habitacion.setDisponible(rs.getBoolean("disponible"));
                lista.add(habitacion);
            }
            
            // CERRAR EL RESULTSET
            rs.close();
            
            // CERRAR EL SCRIPT SQL PARA QUE NO CONSUMA MEMORIA
            ps.close();
            
            // CERRAR LA CONEXION A LA BD
            Conexion.cerrarConexion();

            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Habitacion obtenerHabitacion(int numero) {

        String sql = "SELECT * FROM habitacion WHERE numero = ?";

        try {
            Connection con = Conexion.getConexion();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, numero);

            ResultSet rs = ps.executeQuery();

            Habitacion habitacion = null;

            if (rs.next()) {
                if(rs.getString("tipo").equalsIgnoreCase("Simple")){
                    habitacion = new HabitacionSimple(
                            rs.getInt("numero"),
                            rs.getInt("capacidad"),
                            rs.getBoolean("aire_acondicionado")
                    );
                }else{
                    habitacion = new HabitacionVIP(
                            rs.getInt("numero"),
                            rs.getInt("capacidad"),
                            rs.getBoolean("aire_acondicionado")
                    );
                }
                habitacion.setDisponible(rs.getBoolean("disponible"));
            }

            rs.close();
            ps.close();
            Conexion.cerrarConexion();

            return habitacion;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static boolean validarSiExiste(int numero){
        String sql = "SELECT * FROM habitacion WHERE numero = ?";

        try {
            // CREA LA CONEXION A LA BD
            Connection con = Conexion.getConexion();

            // PREPARA EL SCRIPT SQL
            PreparedStatement ps = con.prepareStatement(sql);

            // ACA SE INGRESA LOS VALUES EN LAS POSICIONES CORRESPONDIENTES
            ps.setInt(1, numero);
            
            // EJECUTA EL SCRIPT SQL Y RETORNA LOS DATOS 
            ResultSet rs = ps.executeQuery();
            
            // VALIDAMOS SI EXISTE LA PRIMERA FILA, TRUE SI EXISTE, FALSE SI NO EXISTE
            boolean existe = rs.next();
            
            // CERRAR EL RESULTSET
            rs.close();
            
            // CERRAR EL SCRIPT SQL PARA QUE NO CONSUMA MEMORIA
            ps.close();
            
            // CERRAR LA CONEXION A LA BD
            Conexion.cerrarConexion();

        return existe;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
    }

    public static boolean insertar(Habitacion habitacion) {

        String sql = "INSERT INTO habitacion(numero,capacidad,disponible,aire_acondicionado,tipo) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection con = Conexion.getConexion();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, habitacion.getNumero());
            ps.setInt(2, habitacion.getCapacidad());
            ps.setBoolean(3, habitacion.isDisponible());
            ps.setBoolean(4, habitacion.isAireAcondicionado());
            if (habitacion instanceof HabitacionSimple) {
                ps.setString(5, "Simple");
            } else {
                ps.setString(5, "VIP");
            }

            ps.executeUpdate();

            ps.close();
            
            Conexion.cerrarConexion();

            return true;

        } catch (SQLException e) {
            System.out.println("Dio error al crear");
            e.printStackTrace();
            return false;
        }
    }

    public static boolean actualizar(Habitacion habitacion, int numeroAnterior) {

        String sql = "UPDATE habitacion SET numero = ?, capacidad = ?, disponible = ?, aire_acondicionado = ?, tipo = ? WHERE numero = ?";

        try {
            Connection con = Conexion.getConexion();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, habitacion.getNumero());
            ps.setInt(2, habitacion.getCapacidad());
            ps.setBoolean(3, habitacion.isDisponible());
            ps.setBoolean(4, habitacion.isAireAcondicionado());
            if (habitacion instanceof HabitacionSimple) {
                ps.setString(5, "Simple");
            } else {
                ps.setString(5, "VIP");
            }
            ps.setInt(6, numeroAnterior);
            
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
    
    public static boolean eliminar(int numero) {

        String sql = "DELETE FROM habitacion WHERE numero = ?";

        try {
            Connection con = Conexion.getConexion();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, numero);

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

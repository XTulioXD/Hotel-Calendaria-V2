package Dao;

import Conexion.Conexion;
import Hotel.Empleado;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDao {
    public static List<Empleado> listarTodos(){
        String sql = "SELECT * FROM empleado";

        try {
            // CREA LA CONEXION A LA BD
            Connection con = Conexion.getConexion();

            // PREPARA EL SCRIPT SQL
            PreparedStatement ps = con.prepareStatement(sql);

            // EJECUTA EL SCRIPT SQL Y RETORNA LOS DATOS 
            ResultSet rs = ps.executeQuery();
            
            List<Empleado> listaEmpleado = new ArrayList<>();
            while (rs.next()) {
                Empleado empleado = new Empleado(rs.getString("nombre"), rs.getString("dni"), rs.getString("cargo"));
                listaEmpleado.add(empleado);
            }
            
            // CERRAR EL RESULTSET
            rs.close();
            
            // CERRAR EL SCRIPT SQL PARA QUE NO CONSUMA MEMORIA
            ps.close();
            
            // CERRAR LA CONEXION A LA BD
            Conexion.cerrarConexion();

            return listaEmpleado;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Empleado obtenerEmpleado(String dni) {

        String sql = "SELECT * FROM empleado WHERE dni = ?";

        try {
            Connection con = Conexion.getConexion();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, dni);

            ResultSet rs = ps.executeQuery();

            Empleado empleado = null;

            if (rs.next()) {
                empleado = new Empleado(
                        rs.getString("nombre"),
                        rs.getString("dni"),
                        rs.getString("cargo")
                );
            }

            rs.close();
            ps.close();
            Conexion.cerrarConexion();

            return empleado;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static boolean validarSiExiste(String dni){
        String sql = "SELECT * FROM empleado WHERE dni = ?";

        try {
            // CREA LA CONEXION A LA BD
            Connection con = Conexion.getConexion();

            // PREPARA EL SCRIPT SQL
            PreparedStatement ps = con.prepareStatement(sql);

            // ACA SE INGRESA LOS VALUES EN LAS POSICIONES CORRESPONDIENTES
            ps.setString(1, dni);
            
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

    public static boolean insertar(Empleado empleado) {

        String sql = "INSERT INTO empleado(dni,nombre,cargo) VALUES (?, ?, ?)";

        try {
            Connection con = Conexion.getConexion();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, empleado.getDni());
            ps.setString(2, empleado.getNombre());
            ps.setString(3, empleado.getCargo());

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

    public static boolean actualizar(Empleado empleado, String dniAnterior) {

        String sql = "UPDATE empleado SET dni = ?, nombre = ?, cargo = ? WHERE dni = ?";

        try {
            Connection con = Conexion.getConexion();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, empleado.getDni());
            ps.setString(2, empleado.getNombre());
            ps.setString(3, empleado.getCargo());
            ps.setString(4, dniAnterior);

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
    
    public static boolean eliminar(String dni) {

        String sql = "DELETE FROM empleado WHERE dni = ?";

        try {
            Connection con = Conexion.getConexion();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, dni);

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
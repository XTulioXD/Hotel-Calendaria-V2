package Dao;

import Conexion.Conexion;
import Hotel.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDao {

    public static Cliente obtenerCliente(String dni) {

        String sql = "SELECT * FROM cliente WHERE dni = ?";

        try {
            Connection con = Conexion.getConexion();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, dni);

            ResultSet rs = ps.executeQuery();

            Cliente cliente = null;

            if (rs.next()) {
                cliente = new Cliente(
                        rs.getString("nombre"),
                        rs.getString("dni"),
                        rs.getInt("cantidad_personas")
                );
            }

            rs.close();
            ps.close();
            Conexion.cerrarConexion();

            return cliente;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static boolean validarSiExiste(String dni){
        String sql = "SELECT * FROM cliente WHERE dni = ?";

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

    public static boolean insertar(Cliente cliente) {

        String sql = "INSERT INTO cliente(nombre,dni,cantidad_personas) VALUES (?, ?, ?)";

        try {
            // CREA LA CONEXION A LA BD
            Connection con = Conexion.getConexion();

            // PREPARA EL SCRIPT SQL
            PreparedStatement ps = con.prepareStatement(sql);

            // ACA SE INGRESA LOS VALUES EN LAS POSICIONES CORRESPONDIENTES
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getDni());
            ps.setInt(3, cliente.getCantidadPersonas());

            // EJECUTA EL SCRIPT SQL
            ps.executeUpdate();

            // CERRAR EL SCRIPT SQL PARA QUE NO CONSUMA MEMORIA
            ps.close();
            
            // CERRAR LA CONEXION A LA BD
            Conexion.cerrarConexion();

            return true;

        } catch (SQLException e) {
            System.out.println("Dio error al crear");
            e.printStackTrace();
            return false;
        }
    }

    public static boolean actualizarCantidadPersonas(int cantidadPersonas, String dni) {

        String sql = "UPDATE cliente SET cantidad_personas = ? WHERE dni = ?";

        try {
            Connection con = Conexion.getConexion();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, cantidadPersonas);
            ps.setString(2, dni);

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
    
}
package Dao;

import Conexion.Conexion;
import Hotel.Pago;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PagoDao {

    public static Pago obtenerPago(int idPago) {

        String sql = "SELECT * FROM pago WHERE id_pago = ?";

        try {
            Connection con = Conexion.getConexion();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, idPago);

            ResultSet rs = ps.executeQuery();

            Pago pago = null;

            if (rs.next()) {
                pago = new Pago(
                        rs.getInt("id_pago"),
                        rs.getDouble("monto")
                );
            }

            rs.close();
            ps.close();
            Conexion.cerrarConexion();

            return pago;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Pago insertar(Pago pago) {

        String sql = "INSERT INTO pago(monto) VALUES (?)";

        try {
            // CREA LA CONEXION A LA BD
            Connection con = Conexion.getConexion();

            // PREPARA EL SCRIPT SQL
            PreparedStatement ps = con.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );

            // ACA SE INGRESA LOS VALUES EN LAS POSICIONES CORRESPONDIENTES
            ps.setDouble(1, pago.getMonto());
            
            // RETORNA LA CANTIDAD DE FILAS INSERTADAS
            int filas = ps.executeUpdate();
            
            // SI AL MENOS HUBO UNA INSERCCION
            if (filas > 0) {
                // OBTENGO LOS DATOS DE LA FILA YA INSERTADA
                ResultSet rs = ps.getGeneratedKeys();
                
                // SI HAY AL MENOS UNA FILA
                if (rs.next()) {
                    // ESTABLECEMOS EL VALOR DEL ID GENERADO AUTOMATICAMENTE POR LA BD AL OBJETO PAGO
                    pago.setIdPago(rs.getInt("id_pago"));
                }

                // CERRAMOS EL RESULTSET
                rs.close();
            }

            // CERRAR EL SCRIPT SQL PARA QUE NO CONSUMA MEMORIA
            ps.close();
            
            // CERRAR LA CONEXION A LA BD
            Conexion.cerrarConexion();

            return pago;

        } catch (SQLException e) {
            System.out.println("Dio error al crear");
            e.printStackTrace();
            return null;
        }
    }
    
    public static boolean actualizar(Pago pago) {

        String sql = "UPDATE pago SET monto = ? WHERE id_pago = ?";

        try {
            Connection con = Conexion.getConexion();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDouble(1, pago.getMonto());
            ps.setInt(2, pago.getIdPago());

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
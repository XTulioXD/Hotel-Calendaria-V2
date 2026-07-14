package Hotel;

public class Pago {
    
    // Encapsulamiento
    private int idPago; // GIAN MODIFICO AQUI
    private double monto;

    // Constructor PARA CREAR
    public Pago(double monto) {
        this.monto = monto;
    }
    // GIAN MODIFICO DESDE AQUI
    // CONSTRUCTOR PARA LECTURA
    public Pago(int idPago, double monto) {
        this.idPago = idPago;
        this.monto = monto;
    }
    // GIAN MODIFICO HASTA AQUI
    
    // Getter
    public double getMonto() {
        return monto;
    }

    // GIAN MODIFICO DESDE AQUI
    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }
    // GIAN MODIFICO HASTA AQUI

    // Método para mostrar el pago
    public void mostrarPago() {
        System.out.println("Monto a pagar: S/ " + monto);
    }
}
package hotel;

public class Pago {
    
    // Encapsulamiento
    private double monto;

    // Constructor
    public Pago(double monto) {
        this.monto = monto;
    }

    // Getter
    public double getMonto() {
        return monto;
    }

    // Método para mostrar el pago
    public void mostrarPago() {
        System.out.println("Monto a pagar: S/ " + monto);
    }
}
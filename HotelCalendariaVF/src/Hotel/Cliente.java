package hotel;

// Aplicamos la herencia: Cliente extiende de Persona
public class Cliente extends Persona {

    // Encapsulamiento
    private int cantidadPersonas;

    // Constructor
    public Cliente(String nombre, String dni, int cantidadPersonas) {
        // Hereda nombre y dni de Persona
        super(nombre, dni);
        this.cantidadPersonas = cantidadPersonas;
    }

    // Getter
    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    // Setter (NUEVO: Permite modificar el dato en caliente)
    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    // Método propio
    public void mostrarCliente() {
        // Método heredado de Persona
        mostrarPersona();
        System.out.println("Cantidad de personas: " + cantidadPersonas);
    }
}

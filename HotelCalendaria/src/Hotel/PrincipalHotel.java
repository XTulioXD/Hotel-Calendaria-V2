package Hotel;

import Dao.*;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import java.util.List;

public class PrincipalHotel extends JFrame {

    private GestionReservas control;

    private Habitacion h1 = new HabitacionSimple(101, 2, false);
    private Habitacion h2 = new HabitacionSimple(102, 2, false);
    private Habitacion h3 = new HabitacionSimple(103, 2, false);
    private Habitacion h4 = new HabitacionSimple(104, 2, false);
    private Habitacion h5 = new HabitacionSimple(105, 2, false);
    private Habitacion h6 = new HabitacionVIP(201, 4, true);
    private Habitacion h7 = new HabitacionVIP(202, 4, true);
    private Habitacion h8 = new HabitacionVIP(203, 4, true);
    private Habitacion h9 = new HabitacionVIP(204, 4, true);
    private Habitacion h10 = new HabitacionVIP(205, 4, true);

    private JTextField txtNombre, txtDni, txtPersonas, txtDias, txtIdConsulta;
    private JComboBox<Object> cbHabitacion;
    private JComboBox<Object> cboEmpleado;
    private JTextArea txtArea;
    private JButton btnRegistrar, btnBuscar, btnEliminar, btnModificar, btnMostrar;
    private JButton btnAgregarHabitacion, btnEditarHabitacion, btnEliminarHabitacion, btnListarHabitaciones;
    private JButton btnAgregarEmpleado, btnEditarEmpleado, btnEliminarEmpleado, btnListarEmpleados;

    public PrincipalHotel() {
        control = new GestionReservas();
        configurarVentana();
        inicializarComponentes();
        mapearEventos();
    }

    private void configurarVentana() {
        setTitle("HOTEL CANDELARIA");
        setSize(820, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        JLabel lblTitulo = new JLabel("HOTEL CANDELARIA");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setBounds(250, 20, 300, 30);
        add(lblTitulo);

        addLabel("Nombre:", 40, 90);
        txtNombre = addField(120, 90);

        addLabel("DNI:", 40, 130);
        txtDni = addField(120, 130);

        addLabel("N° Personas:", 40, 170);
        txtPersonas = addField(120, 170);

        addLabel("Tipo de Habitación:", 350, 90);
        cbHabitacion = new JComboBox<>();
        cbHabitacion.setBounds(460, 90, 170, 25);
        add(cbHabitacion);

        cargarHabitacionesIniciales();
        actualizarComboHabitaciones();

        btnAgregarHabitacion = new JButton("+");
        btnAgregarHabitacion.setBounds(640, 90, 45, 25);
        add(btnAgregarHabitacion);

        addLabel("Días:", 350, 130);
        txtDias = addField(460, 130);

        addLabel("Empleado:", 350, 170);
        cboEmpleado = new JComboBox<>();
        cboEmpleado.setEditable(false);
        cboEmpleado.addItem("Seleccione recepcionista");
        agregarEmpleadoInicial("Carlos Trujillo", "71223344", "Recepcionista");
        agregarEmpleadoInicial("Diego Asencios", "73445566", "Recepcionista");
        agregarEmpleadoInicial("Gian Salinas", "75667788", "Recepcionista");
        cboEmpleado.setBounds(460, 170, 170, 25);
        add(cboEmpleado);
        actualizarComboEmpleados();

        btnAgregarEmpleado = new JButton("+");
        btnAgregarEmpleado.setBounds(640, 170, 45, 25);
        add(btnAgregarEmpleado);

        btnRegistrar = new JButton("Registrar Reserva");
        btnRegistrar.setBounds(250, 210, 180, 35);
        add(btnRegistrar);

        JLabel lblConsultas = new JLabel("CONSULTAS");
        lblConsultas.setFont(new Font("Arial", Font.BOLD, 16));
        lblConsultas.setBounds(300, 260, 150, 25);
        add(lblConsultas);

        addLabel("ID Reserva:", 40, 300);
        txtIdConsulta = addField(120, 300);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(300, 300, 100, 30);
        add(btnBuscar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(420, 300, 100, 30);
        add(btnEliminar);

        btnModificar = new JButton("Modificar Reserva");
        btnModificar.setBounds(540, 300, 140, 30);
        add(btnModificar);

        btnMostrar = new JButton("Mostrar Reservas");
        btnMostrar.setBounds(250, 340, 180, 30);
        add(btnMostrar);

        JLabel lblMantenimiento = new JLabel("MANTENIMIENTO");
        lblMantenimiento.setFont(new Font("Arial", Font.BOLD, 16));
        lblMantenimiento.setBounds(300, 380, 180, 25);
        add(lblMantenimiento);

        addLabel("Habitaciones:", 40, 420);
        btnListarHabitaciones = new JButton("Listar");
        btnListarHabitaciones.setBounds(150, 420, 95, 30);
        add(btnListarHabitaciones);

        btnEditarHabitacion = new JButton("Editar");
        btnEditarHabitacion.setBounds(255, 420, 95, 30);
        add(btnEditarHabitacion);

        btnEliminarHabitacion = new JButton("Eliminar");
        btnEliminarHabitacion.setBounds(360, 420, 110, 30);
        add(btnEliminarHabitacion);

        addLabel("Empleados:", 40, 460);
        btnListarEmpleados = new JButton("Listar");
        btnListarEmpleados.setBounds(150, 460, 95, 30);
        add(btnListarEmpleados);

        btnEditarEmpleado = new JButton("Editar");
        btnEditarEmpleado.setBounds(255, 460, 95, 30);
        add(btnEditarEmpleado);

        btnEliminarEmpleado = new JButton("Eliminar");
        btnEliminarEmpleado.setBounds(360, 460, 110, 30);
        add(btnEliminarEmpleado);

        txtArea = new JTextArea();
        txtArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtArea);
        scroll.setBounds(40, 510, 720, 120);
        add(scroll);
    }

    private void addLabel(String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setBounds(x, y, 120, 25);
        add(lbl);
    }

    private JTextField addField(int x, int y) {
        JTextField txt = new JTextField();
        txt.setBounds(x, y, 150, 25);
        add(txt);
        return txt;
    }

    private void mapearEventos() {
        Command registrar = () -> registrarReserva();
        Command buscar = () -> buscarReserva();
        Command eliminar = () -> eliminarReserva();
        Command modificar = () -> modificarReserva();
        Command mostrar = () -> mostrarReservas();
        Command agregarHabitacion = () -> agregarHabitacion();
        Command editarHabitacion = () -> editarHabitacion();
        Command eliminarHabitacion = () -> eliminarHabitacion();
        Command listarHabitaciones = () -> listarHabitaciones();
        Command agregarEmpleado = () -> agregarEmpleado();
        Command editarEmpleado = () -> editarEmpleado();
        Command eliminarEmpleado = () -> eliminarEmpleado();
        Command listarEmpleados = () -> listarEmpleados();

        btnRegistrar.addActionListener(e -> registrar.ejecutar());
        btnBuscar.addActionListener(e -> buscar.ejecutar());
        btnEliminar.addActionListener(e -> eliminar.ejecutar());
        btnModificar.addActionListener(e -> modificar.ejecutar());
        btnMostrar.addActionListener(e -> mostrar.ejecutar());
        btnAgregarHabitacion.addActionListener(e -> agregarHabitacion.ejecutar());
        btnEditarHabitacion.addActionListener(e -> editarHabitacion.ejecutar());
        btnEliminarHabitacion.addActionListener(e -> eliminarHabitacion.ejecutar());
        btnListarHabitaciones.addActionListener(e -> listarHabitaciones.ejecutar());
        btnAgregarEmpleado.addActionListener(e -> agregarEmpleado.ejecutar());
        btnEditarEmpleado.addActionListener(e -> editarEmpleado.ejecutar());
        btnEliminarEmpleado.addActionListener(e -> eliminarEmpleado.ejecutar());
        btnListarEmpleados.addActionListener(e -> listarEmpleados.ejecutar());
    }

    private void cargarHabitacionesIniciales() {
        // GIAN MODIFICO DESDE AQUI
        control.getListaHabitaciones().clear();
        control.getListaHabitaciones().add(h1);
        control.getListaHabitaciones().add(h2);
        control.getListaHabitaciones().add(h3);
        control.getListaHabitaciones().add(h4);
        control.getListaHabitaciones().add(h5);
        control.getListaHabitaciones().add(h6);
        control.getListaHabitaciones().add(h7);
        control.getListaHabitaciones().add(h8);
        control.getListaHabitaciones().add(h9);
        control.getListaHabitaciones().add(h10);
        
        for (Habitacion habitacion : control.getListaHabitaciones()) {
            if (!existeHabitacion(habitacion.getNumero())) {
                boolean seCreo = HabitacionDao.insertar(habitacion);
                if (seCreo) {
                    System.out.println("Se creo la habitacion");
                }
            }
        }
        // GIAN MODIFICO HASTA AQUI
    }

    private void actualizarComboHabitaciones() {
        cbHabitacion.removeAllItems();
        cbHabitacion.addItem("Elegir Habitación");

        // GIAN MODIFICO DESDE AQUI
        List<Habitacion> listaHabitacion = HabitacionDao.listarTodos();
        for (Habitacion h : listaHabitacion) {
            cbHabitacion.addItem(h);
        }
        // GIAN MODIFICO HASTA AQUI
    }

    public void agregarHabitacion() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));

        JComboBox<String> cboTipo = new JComboBox<>();
        cboTipo.addItem("Simple");
        cboTipo.addItem("VIP");

        JTextField txtNumeroHabitacion = new JTextField();

        panel.add(new JLabel("Seleccione tipo de habitación:"));
        panel.add(cboTipo);
        panel.add(new JLabel("Ingrese número de habitación:"));
        panel.add(txtNumeroHabitacion);

        int opcion = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Agregar nueva habitación",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (opcion == JOptionPane.OK_OPTION) {
            try {
                String textoNumero = txtNumeroHabitacion.getText().trim();

                if (textoNumero.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar el número de la habitación.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                textoNumero = textoNumero.replaceAll("[^0-9]", "");

                if (textoNumero.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número válido. Ejemplo: 106 o Hab 106.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int numero = Integer.parseInt(textoNumero);

                if (existeHabitacion(numero)) {
                    JOptionPane.showMessageDialog(null, "La habitación Nro " + numero + " ya existe.", "Habitación duplicada", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String tipo = cboTipo.getSelectedItem().toString();
                Habitacion nuevaHabitacion;

                if (tipo.equals("Simple")) {
                    nuevaHabitacion = new HabitacionSimple(numero, 2, false);
                } else {
                    nuevaHabitacion = new HabitacionVIP(numero, 4, true);
                }
                
                // GIAN MODIFICO DESDE AQUI
                boolean seCreo = HabitacionDao.insertar(nuevaHabitacion);
                if (seCreo) {
                    System.out.println("Se creo la habitacion");
                }
                // GIAN MODIFICO HASTA AQUI

                control.getListaHabitaciones().add(nuevaHabitacion);
                cbHabitacion.addItem(nuevaHabitacion);
                cbHabitacion.setSelectedItem(nuevaHabitacion);

                JOptionPane.showMessageDialog(null, "Habitación " + numero + " agregada correctamente como " + tipo + ".", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error: Ingrese un número de habitación válido.");
            }
        }
    }

    public void editarHabitacion() {
        Habitacion habitacion = seleccionarHabitacion("Editar habitacion");
        if (habitacion == null) {
            return;
        }

        if (!habitacion.isDisponible()) {
            JOptionPane.showMessageDialog(null, "No se puede editar una habitacion ocupada.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // GIAN MODIFICO DESDE AQUI
        int numeroOriginal = habitacion.getNumero();
        // GIAN MODIFICO HASTA AQUI

        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        JComboBox<String> cboTipo = new JComboBox<>(new String[]{"Simple", "VIP"});
        JTextField txtNumeroHabitacion = new JTextField(String.valueOf(habitacion.getNumero()));
        cboTipo.setSelectedItem(obtenerTipoHabitacion(habitacion));

        panel.add(new JLabel("Tipo de habitacion:"));
        panel.add(cboTipo);
        panel.add(new JLabel("Numero de habitacion:"));
        panel.add(txtNumeroHabitacion);

        int opcion = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Editar habitacion",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (opcion == JOptionPane.OK_OPTION) {
            try {
                int nuevoNumero = obtenerNumeroHabitacion(txtNumeroHabitacion.getText());

                if (nuevoNumero != habitacion.getNumero() && existeHabitacion(nuevoNumero)) {
                    JOptionPane.showMessageDialog(null, "La habitacion Nro " + nuevoNumero + " ya existe.", "Habitacion duplicada", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                //int indice = control.getListaHabitaciones().indexOf(habitacion);
                Habitacion habitacionActualizada = crearHabitacion(nuevoNumero, cboTipo.getSelectedItem().toString());
                //control.getListaHabitaciones().set(indice, habitacionActualizada);
                // GIAN MODIFICO DESDE AQUI
                boolean actualizo = HabitacionDao.actualizar(habitacionActualizada, numeroOriginal);
                // GIAN MODIFICO HASTA AQUI
                actualizarComboHabitaciones();
                cbHabitacion.setSelectedItem(habitacionActualizada);
                txtArea.setText(generarReporteHabitaciones());

                JOptionPane.showMessageDialog(null, "Habitacion actualizada correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingrese un numero de habitacion valido.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public void eliminarHabitacion() {
        Habitacion habitacion = seleccionarHabitacion("Eliminar habitacion");
        if (habitacion == null) {
            return;
        }

        if (!habitacion.isDisponible()) {
            JOptionPane.showMessageDialog(null, "No se puede eliminar una habitacion ocupada.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // GIAN MODIFICO DESDE AQUI
        if (habitacionTieneReservas(habitacion)) {
            JOptionPane.showMessageDialog(null, "No se puede eliminar una habitacion con reservas registradas.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // GIAN MODIFICO HASTA AQUI
        int confirmacion = JOptionPane.showConfirmDialog(
                null,
                "Desea eliminar la habitacion Nro " + habitacion.getNumero() + "?",
                "Confirmar eliminacion",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            control.getListaHabitaciones().remove(habitacion);
            // GIAN MODIFICO DESDE AQUI
            boolean elimino = HabitacionDao.eliminar(habitacion.getNumero());
            if (elimino) {
                System.out.println("Se elimino habitacion.");
            }
            // GIAN MODIFICO HASTA AQUI
            actualizarComboHabitaciones();
            txtArea.setText(generarReporteHabitaciones());
            JOptionPane.showMessageDialog(null, "Habitacion eliminada correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void listarHabitaciones() {
        txtArea.setText(generarReporteHabitaciones());
    }

    private Habitacion seleccionarHabitacion(String titulo) {
        // GIAN MODIFICO DESDE AQUI
        List<Habitacion> listaHabitacion = HabitacionDao.listarTodos();
        if(listaHabitacion == null || listaHabitacion.isEmpty()){
            JOptionPane.showMessageDialog(null, "No hay habitaciones registradas.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        return (Habitacion) JOptionPane.showInputDialog(
                null,
                "Seleccione una habitacion:",
                titulo,
                JOptionPane.PLAIN_MESSAGE,
                null,
                listaHabitacion.toArray(),
                listaHabitacion.get(0)
        );
        /*
        if (control.getListaHabitaciones().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay habitaciones registradas.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        return (Habitacion) JOptionPane.showInputDialog(
                null,
                "Seleccione una habitacion:",
                titulo,
                JOptionPane.PLAIN_MESSAGE,
                null,
                control.getListaHabitaciones().toArray(),
                control.getListaHabitaciones().getFirst()
        );
        */
    }

    private Habitacion crearHabitacion(int numero, String tipo) {
        if (tipo.equals("Simple")) {
            return new HabitacionSimple(numero, 2, false);
        }

        return new HabitacionVIP(numero, 4, true);
    }

    private int obtenerNumeroHabitacion(String textoNumero) {
        String numeroLimpio = textoNumero.trim().replaceAll("[^0-9]", "");
        if (numeroLimpio.isEmpty()) {
            throw new NumberFormatException();
        }

        return Integer.parseInt(numeroLimpio);
    }

    private String obtenerTipoHabitacion(Habitacion habitacion) {
        return (habitacion instanceof HabitacionSimple) ? "Simple" : "VIP";
    }

    private String generarReporteHabitaciones() {
        // GIAN MODIFICO DESDE AQUI
        List<Habitacion> listaHabitacion = HabitacionDao.listarTodos();
        if(listaHabitacion == null || listaHabitacion.isEmpty()){
            return "No hay habitaciones registradas.";
        }
        
        StringBuilder sb = new StringBuilder("HABITACIONES REGISTRADAS\n\n");
        for (Habitacion h : listaHabitacion) {
            sb.append("Nro ").append(h.getNumero())
                    .append(" | Tipo: ").append(obtenerTipoHabitacion(h))
                    .append(" | Capacidad: ").append(h.getCapacidad())
                    .append(" | Estado: ").append(h.isDisponible() ? "Disponible" : "Ocupada")
                    .append("\n");
        }
        return sb.toString();
        /*
        if (control.getListaHabitaciones().isEmpty()) {
            return "No hay habitaciones registradas.";
        }

        StringBuilder sb = new StringBuilder("HABITACIONES REGISTRADAS\n\n");
        for (Habitacion h : control.getListaHabitaciones()) {
            sb.append("Nro ").append(h.getNumero())
                    .append(" | Tipo: ").append(obtenerTipoHabitacion(h))
                    .append(" | Capacidad: ").append(h.getCapacidad())
                    .append(" | Estado: ").append(h.isDisponible() ? "Disponible" : "Ocupada")
                    .append("\n");
        }

        return sb.toString();
        */
        // GIAN MODIFICO HASTA AQUI
    }

    private boolean existeHabitacion(int numero) {
        // GIAN MODIFICO DESDE AQUI
        /*
        Habitacion habitacionEncontrada = control.getListaHabitaciones().stream()
                .filter(h -> h.getNumero() == numero)
                .findFirst()
                .orElse(null);

        return habitacionEncontrada != null;
        */
        boolean existe = HabitacionDao.validarSiExiste(numero);
        return existe;
        // GIAN MODIFICO HASTA AQUI
    }

    private void agregarEmpleadoInicial(String nombre, String dni, String cargo) {
        Empleado empleado = new Empleado(nombre, dni, cargo);
        // GIAN MODIFICO DESDE AQUI
        boolean existe = EmpleadoDao.validarSiExiste(empleado.getDni());
        if (existe){
            System.out.println("Este DNI ya esta en la BD");
        }else{
            boolean resultado = EmpleadoDao.insertar(empleado);
            if(resultado == true){
                System.out.println("Se inserto el dato del empleado en la base de datos");
            }            
        }
        // GIAN MODIFICO HASTA AQUI
        control.getListaEmpleados().add(empleado);
        cboEmpleado.addItem(nombre);
    }

    public void agregarEmpleado() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));

        JTextField txtNombreEmpleado = new JTextField();
        JTextField txtDniEmpleado = new JTextField();
        JTextField txtCargoEmpleado = new JTextField("Recepcionista");

        panel.add(new JLabel("Ingrese nombre del empleado:"));
        panel.add(txtNombreEmpleado);
        panel.add(new JLabel("Ingrese DNI del empleado:"));
        panel.add(txtDniEmpleado);
        panel.add(new JLabel("Ingrese cargo del empleado:"));
        panel.add(txtCargoEmpleado);

        int opcion = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Agregar nuevo empleado",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (opcion == JOptionPane.OK_OPTION) {
            String nombre = txtNombreEmpleado.getText().trim();
            String dni = txtDniEmpleado.getText().trim();
            String cargo = txtCargoEmpleado.getText().trim();

            if (nombre.isEmpty() || dni.isEmpty() || cargo.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Complete todos los datos del empleado.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            // GIAN MODIFICO DESDE AQUI - SOLO MODIFIQUE QUE AHORA SE VALIDARA POR DNI YA QUE ESTE ES PK
            if (existeEmpleado(dni)) {
                JOptionPane.showMessageDialog(null, "El empleado con dni " + dni + " ya existe en la lista.", "Empleado duplicado", JOptionPane.WARNING_MESSAGE);
                return;
            }
            // GIAN MODIFICO HASTA AQUI

            Empleado nuevoEmpleado = new Empleado(nombre, dni, cargo);
            // GIAN MODIFICO DESDE AQUI
            boolean resultado = EmpleadoDao.insertar(nuevoEmpleado);
            if(resultado == true){
                System.out.println("Se inserto el dato del empleado en la base de datos");
            }
            // GIAN MODIFICO HASTA AQUI
            control.getListaEmpleados().add(nuevoEmpleado);
            cboEmpleado.addItem(nombre);
            cboEmpleado.setSelectedItem(nombre);

            JOptionPane.showMessageDialog(null, "Empleado " + nombre + " agregado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void editarEmpleado() {
        Empleado empleado = seleccionarEmpleado("Editar empleado");
        if (empleado == null) {
            return;
        }
        
        // GIAN MODIFICO DESDE AQUI
        String dniOriginal = empleado.getDni();
        // GIAN MODIFICO HASTA AQUI
        
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        JTextField txtNombreEmpleado = new JTextField(empleado.getNombre());
        JTextField txtDniEmpleado = new JTextField(empleado.getDni());
        JTextField txtCargoEmpleado = new JTextField(empleado.getCargo());

        panel.add(new JLabel("Nombre del empleado:"));
        panel.add(txtNombreEmpleado);
        panel.add(new JLabel("DNI del empleado:"));
        panel.add(txtDniEmpleado);
        panel.add(new JLabel("Cargo del empleado:"));
        panel.add(txtCargoEmpleado);

        int opcion = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Editar empleado",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (opcion == JOptionPane.OK_OPTION) {
            String nombre = txtNombreEmpleado.getText().trim();
            String dni = txtDniEmpleado.getText().trim();
            String cargo = txtCargoEmpleado.getText().trim();

            if (nombre.isEmpty() || dni.isEmpty() || cargo.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Complete todos los datos del empleado.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!nombre.equalsIgnoreCase(empleado.getNombre()) && existeEmpleado(nombre)) {
                JOptionPane.showMessageDialog(null, "El empleado " + nombre + " ya existe en la lista.", "Empleado duplicado", JOptionPane.WARNING_MESSAGE);
                return;
            }

            empleado.setNombre(nombre);
            empleado.setDni(dni);
            empleado.setCargo(cargo);
            // GIAN MODIFICO DESDE AQUI
            boolean actualizo = EmpleadoDao.actualizar(empleado, dniOriginal);
            if (actualizo) {
                System.out.println("Se actualizo el emplado.");
            }
            // GIAN MODIFICO HASTA AQUI
            actualizarComboEmpleados();
            cboEmpleado.setSelectedItem(nombre);
            txtArea.setText(generarReporteEmpleados());

            JOptionPane.showMessageDialog(null, "Empleado actualizado correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void eliminarEmpleado() {
        Empleado empleado = seleccionarEmpleado("Eliminar empleado");
        if (empleado == null) {
            return;
        }

        if (empleadoTieneReservas(empleado)) {
            JOptionPane.showMessageDialog(null, "No se puede eliminar un empleado con reservas registradas.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(
                null,
                "Desea eliminar al empleado " + empleado.getNombre() + "?",
                "Confirmar eliminacion",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            control.getListaEmpleados().remove(empleado);
            
            // GIAN MODIFICO DESDE AQUI
            boolean elimino = EmpleadoDao.eliminar(empleado.getDni());
            if (elimino) {
                System.out.println("Se eliminó el empleado.");
            }
            // GIAN MODIFICO HASTA AQUI
            
            actualizarComboEmpleados();
            txtArea.setText(generarReporteEmpleados());
            JOptionPane.showMessageDialog(null, "Empleado eliminado correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void listarEmpleados() {
        txtArea.setText(generarReporteEmpleados());
    }

    private Empleado seleccionarEmpleado(String titulo) {
        // GIAN MODIFICO DESDE AQUI
        List<Empleado> listaEmpleado = EmpleadoDao.listarTodos();
        if(listaEmpleado == null || listaEmpleado.isEmpty()){
            JOptionPane.showMessageDialog(null, "No hay empleados registrados.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        
        return (Empleado) JOptionPane.showInputDialog(
                null,
                "Seleccione un empleado:",
                titulo,
                JOptionPane.PLAIN_MESSAGE,
                null,
                listaEmpleado.toArray(),
                listaEmpleado.get(0)
        );
        /*
        if (control.getListaEmpleados().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay empleados registrados.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        String[] nombres = new String[control.getListaEmpleados().size()];
        for (int i = 0; i < control.getListaEmpleados().size(); i++) {
            nombres[i] = control.getListaEmpleados().get(i).getNombre();
        }

        String nombreSeleccionado = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione un empleado:",
                titulo,
                JOptionPane.PLAIN_MESSAGE,
                null,
                nombres,
                nombres[0]
        );

        if (nombreSeleccionado == null) {
            return null;
        }

        return obtenerEmpleadoSeleccionado(nombreSeleccionado);
        */
        // GIAN MODIFICO HASTA AQUI
    }

    private boolean empleadoTieneReservas(Empleado empleado) {
        // GIAN MODIFICO DESDE AQUI
        List<Reserva> reservasEmpleado = ReservaDao.listarReservasEmpleado(empleado);
        if (reservasEmpleado == null || reservasEmpleado.isEmpty()) {
            return false;
        }
        return true;
        /*
        return control.getListaReservas().stream()
                .anyMatch(reserva -> reserva.getEmpleado().getNombre().equalsIgnoreCase(empleado.getNombre()));
        */
        // GIAN MODIFICO HASTA AQUI
    }
    
    // GIAN MODIFICO DESDE AQUI
    private boolean habitacionTieneReservas(Habitacion habitacion) {
        List<Reserva> reservasHabitacion = ReservaDao.listarReservasHabitacion(habitacion);
        if (reservasHabitacion == null || reservasHabitacion.isEmpty()) {
            return false;
        }
        return true;
    }
    // GIAN MODIFICO HASTA AQUI

    private void actualizarComboEmpleados() {
        cboEmpleado.removeAllItems();
        cboEmpleado.addItem("Seleccione recepcionista");

        // GIAN MODIFICO DESDE AQUI
        List<Empleado> listaEmpleado = EmpleadoDao.listarTodos();
        for (Empleado empleado : listaEmpleado) {
            cboEmpleado.addItem(empleado);
        }
        // GIAN MODIFICO HASTA AQUI
    }

    private String generarReporteEmpleados() {
        // GIAN MODIFICO DESDE AQUI
        List<Empleado> listaEmpleado = EmpleadoDao.listarTodos();
        if(listaEmpleado == null || listaEmpleado.isEmpty()){
            return "No hay empleados registrados.";
        }
        
        StringBuilder sb = new StringBuilder("EMPLEADOS REGISTRADOS\n\n");
        for (Empleado empleado : listaEmpleado) {
            sb.append("Nombre: ").append(empleado.getNombre())
                    .append(" | DNI: ").append(empleado.getDni())
                    .append(" | Cargo: ").append(empleado.getCargo())
                    .append("\n");
        }
        return sb.toString();
        /*
        if (control.getListaEmpleados().isEmpty()) {
            return "No hay empleados registrados.";
        }

        StringBuilder sb = new StringBuilder("EMPLEADOS REGISTRADOS\n\n");
        for (Empleado empleado : control.getListaEmpleados()) {
            sb.append("Nombre: ").append(empleado.getNombre())
                    .append(" | DNI: ").append(empleado.getDni())
                    .append(" | Cargo: ").append(empleado.getCargo())
                    .append("\n");
        }

        return sb.toString();
        */
        // GIAN MODIFICO HASTA AQUI
    }

    private boolean existeEmpleado(String dni) {
        // GIAN MODIFICO DESDE AQUI
        /*
        Empleado empleadoEncontrado = control.getListaEmpleados().stream()
                .filter(emp -> emp.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);

        return empleadoEncontrado != null;
        */
        boolean existe = EmpleadoDao.validarSiExiste(dni);
        return existe;
        // GIAN MODIFICO HASTA AQUI
    }

    private Empleado obtenerEmpleadoSeleccionado(String nombre) {
        return control.getListaEmpleados().stream()
                .filter(emp -> emp.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    public void registrarReserva() {
        try {
            if (cbHabitacion.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una Habitación válida.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Habitacion habSeleccionada = (Habitacion) cbHabitacion.getSelectedItem();

            if (!habSeleccionada.isDisponible()) {
                JOptionPane.showMessageDialog(null, "Error: La habitación Nro " + habSeleccionada.getNumero() + " ya está ocupada por otro cliente.", "Ocupada", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String nombre = txtNombre.getText().trim();
            String dni = txtDni.getText().trim();
            int personas = Integer.parseInt(txtPersonas.getText().trim());
            int dias = Integer.parseInt(txtDias.getText().trim());

            // GIAN MODIFICO DESDE AQUI
            /*
            String nombreEmpleado = "";
            if (cboEmpleado.getSelectedItem() != null) {
                nombreEmpleado = cboEmpleado.getSelectedItem().toString().trim();
            }
            */
            String nombreEmpleado = cboEmpleado.getSelectedItem().toString().trim();
            if (nombre.isEmpty() || dni.isEmpty() || nombreEmpleado.isEmpty() || nombreEmpleado.equals("Seleccione recepcionista")) {
                JOptionPane.showMessageDialog(null, "Complete todos los campos del formulario.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Empleado empleado = null;
            if (cboEmpleado.getSelectedItem() != null) {
                empleado = (Empleado) cboEmpleado.getSelectedItem();
            }
            // GIAN MODIFICO HASTA AQUI

            if (personas > habSeleccionada.getCapacidad()) {
                JOptionPane.showMessageDialog(null, "Error: El número de personas supera la capacidad máxima de esta habitación (" + habSeleccionada.getCapacidad() + ").", "Capacidad Superada", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // GIAN MODIFICO DESDE AQUI
            Cliente cliente = ClienteDao.obtenerCliente(dni);
            if (cliente == null) {
                cliente = new Cliente(nombre, dni, personas);
                boolean resultado = ClienteDao.insertar(cliente);
                if(resultado){
                    System.out.println("Se inserto el dato del cliente en la base de datos");
                }
            }else{
                JOptionPane.showMessageDialog(null, "El cliente con dni " + dni + " ya existe en la lista. Se crea la reserva a nombre de la persona del dni.", "Empleado duplicado", JOptionPane.WARNING_MESSAGE);
            }
            // Empleado empleado = obtenerEmpleadoSeleccionado(nombreEmpleado);
            // GIAN MODIFICO HASTA AQUI

            if (empleado == null) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un empleado válido.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Reserva nuevaReserva = new Reserva(cliente, empleado, habSeleccionada, dias);

            control.registrarReserva(nuevaReserva);
            cbHabitacion.repaint();
            txtArea.setText(ejecutarReporteGeneral());
            limpiarCampos();

            JOptionPane.showMessageDialog(null, "Reserva registrada correctamente en la habitación " + habSeleccionada.getNumero() + ".", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error: Ingrese valores numéricos válidos en personas y días.");
        }
    }

    public void buscarReserva() {
        String idTexto = txtIdConsulta.getText().trim();

        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese un ID de Reserva en la casilla para poder buscar.");
            return;
        }

        try {
            int idBuscado = Integer.parseInt(idTexto);

            // GIAN MODIFICO DESDE AQUI
            /*
            Reserva r = control.getListaReservas().stream()
                    .filter(res -> res.getIdReserva() == idBuscado)
                    .findFirst()
                    .orElse(null);
            */
            Reserva r = ReservaDao.obtenerReserva(idBuscado);
            // GIAN MODIFICO HASTA AQUI

            if (r != null) {
                txtNombre.setText(r.getCliente().getNombre());
                txtDni.setText(r.getCliente().getDni());
                txtDias.setText(String.valueOf(r.getDias()));
                txtPersonas.setText(String.valueOf(r.getCliente().getCantidadPersonas()));

                for (int i = 1; i < cbHabitacion.getItemCount(); i++) {
                    Habitacion hCombo = (Habitacion) cbHabitacion.getItemAt(i);
                    if (hCombo.getNumero() == r.getHabitacion().getNumero()) {
                        cbHabitacion.setSelectedIndex(i);
                        break;
                    }
                }

                cboEmpleado.setSelectedItem(r.getEmpleado().getNombre());

                txtArea.setText("REGISTRO ENCONTRADO:\n\n" + generarBoletaString(r));
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ninguna reserva asociada a ese ID.");
                txtArea.setText("");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error: El ID debe ser un número entero válido.");
        }
    }

    public void eliminarReserva() {
        String idTexto = txtIdConsulta.getText().trim();

        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese un ID de Reserva en la casilla para eliminar.");
            return;
        }

        try {
            int idBuscado = Integer.parseInt(idTexto);

            // GIAN MODIFICO DESDE AQUI
            /*
            Reserva r = control.getListaReservas().stream()
                    .filter(res -> res.getIdReserva() == idBuscado)
                    .findFirst()
                    .orElse(null);
            */
            Reserva r = ReservaDao.obtenerReserva(idBuscado);
            // GIAN MODIFICO HASTA AQUI

            if (r != null) {
                control.eliminarReserva(r);
                cbHabitacion.repaint();

                JOptionPane.showMessageDialog(null, "Reserva eliminada. La habitación Nro " + r.getHabitacion().getNumero() + " ahora está libre.");
                txtArea.setText(ejecutarReporteGeneral());
                txtIdConsulta.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ninguna reserva asociada a ese ID.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error: El ID debe ser un número entero válido.");
        }
    }

    public void modificarReserva() {
        String idTexto = txtIdConsulta.getText().trim();

        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese un ID de Reserva en la casilla para modificar.");
            return;
        }

        try {
            int idBuscado = Integer.parseInt(idTexto);

            // GIAN MODIFICO DESDE AQUI
            /*
            Reserva r = control.getListaReservas().stream()
                    .filter(res -> res.getIdReserva() == idBuscado)
                    .findFirst()
                    .orElse(null);
            */
            Reserva r = ReservaDao.obtenerReserva(idBuscado);
            // GIAN M|ODIFICO HASTA AQUI

            if (r == null) {
                JOptionPane.showMessageDialog(null, "No se encontró ninguna reserva asociada a ese ID.");
                return;
            }

            JPanel panelModificar = new JPanel(new GridLayout(2, 2, 5, 5));
            JTextField txtNuevosDias = new JTextField(String.valueOf(r.getDias()));
            JTextField txtNuevasPersonas = new JTextField(String.valueOf(r.getCliente().getCantidadPersonas()));

            panelModificar.add(new JLabel("Nuevos días de estadía:"));
            panelModificar.add(txtNuevosDias);
            panelModificar.add(new JLabel("Nuevo número de personas:"));
            panelModificar.add(txtNuevasPersonas);

            int opcion = JOptionPane.showConfirmDialog(
                    null,
                    panelModificar,
                    "Actualizar Datos de Hospedaje - ID Reserva: " + idBuscado,
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            if (opcion == JOptionPane.OK_OPTION) {
                try {
                    int nuevosDias = Integer.parseInt(txtNuevosDias.getText().trim());
                    int nuevasPersonas = Integer.parseInt(txtNuevasPersonas.getText().trim());

                    if (nuevasPersonas > r.getHabitacion().getCapacidad()) {
                        JOptionPane.showMessageDialog(null, "Error: La cantidad de personas excede el límite permitido para esta habitación (" + r.getHabitacion().getCapacidad() + ").", "Error de Modificación", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    // GIAN MODIFICO DESDE AQUI
                    // r.modificarEstadia(nuevosDias);
                    r.setDias(nuevosDias);
                    control.modificarReserva(r, nuevasPersonas);
                    // GIAN MODIFICO HASTA AQUI
                    cbHabitacion.repaint();

                    JOptionPane.showMessageDialog(null, "Reserva modificada correctamente.");
                    txtArea.setText(ejecutarReporteGeneral());
                    limpiarCampos();
                    txtIdConsulta.setText("");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese valores numéricos válidos.");
                }
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error: El ID debe ser un número entero válido.");
        }
    }

    public void mostrarReservas() {
        txtArea.setText(ejecutarReporteGeneral());
    }

    private String generarBoletaString(Reserva r) {
        StringBuilder sb = new StringBuilder();

        sb.append("ID RESERVA: ").append(r.getIdReserva()).append("\n");
        sb.append("CLIENTE: ").append(r.getCliente().getNombre()).append(" | DNI: ").append(r.getCliente().getDni()).append("\n");
        sb.append("Huéspedes: ").append(r.getCliente().getCantidadPersonas()).append(" personas\n");
        sb.append("Atendido por: ").append(r.getEmpleado().getNombre()).append("\n");
        sb.append("............................................................\n");

        String tipoHab = (r.getHabitacion() instanceof HabitacionSimple) ? "Simple" : "VIP";

        sb.append("Habitación: Nro ").append(r.getHabitacion().getNumero()).append(" (").append(tipoHab).append(")\n");
        sb.append("Estadía: ").append(r.getDias()).append(" días | Total a pagar: S/. ").append(r.getPago().getMonto()).append("\n");

        return sb.toString();
    }

    private String ejecutarReporteGeneral() {
        if (control.getListaReservas().isEmpty()) {
            return "No hay reservas registradas.";
        }

        StringBuilder sb = new StringBuilder();

        for (Reserva r : control.getListaReservas()) {
            sb.append("===== RESERVA =====\n")
                    .append(generarBoletaString(r))
                    .append("------------------------------------------------------------\n");
        }

        return sb.toString();
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtDni.setText("");
        txtPersonas.setText("");
        txtDias.setText("");
        cbHabitacion.setSelectedIndex(0);
        cboEmpleado.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        /*
        Connection con = Conexion.getConexion();

        if (con != null) {
            System.out.println("La conexión funciona correctamente.");
        }
        Cliente cliente = new Cliente("Gian", "75440324", 20);
        
        ClienteDao cdao = new ClienteDao();
        boolean resultado = cdao.insertar(cliente);
        
        if(resultado == true){
            System.out.println("Se inserto el dato correcto");
        }

        Conexion.cerrarConexion();
        */
        SwingUtilities.invokeLater(() -> new PrincipalHotel().setVisible(true));
    }
}

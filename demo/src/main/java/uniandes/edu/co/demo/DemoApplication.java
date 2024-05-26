package  uniandes.edu.co.demo;

import java.util.List;
import java.util.Scanner;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import uniandes.edu.co.demo.modelo.Cuenta;
import uniandes.edu.co.demo.modelo.Usuario;
import uniandes.edu.co.demo.repository.CuentaRepository;
import uniandes.edu.co.demo.repository.UsuarioRepository;

@ComponentScan({"uniandes.edu.co.demo.repository"})
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menu:");
            System.out.println("1. Consultar todas las cuentas");
            System.out.println("2. Buscar cuenta por ID");
            System.out.println("3. Crear nueva cuenta");
            System.out.println("4. Cambiar estado de cuenta a cerrado");
            System.out.println("0. Salir");
            System.out.println("Seleccione una opción:");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    consultarTodasLasCuentas(scanner);
                    break;
                case 2:
                    buscarCuentaPorId(scanner);
                    break;
                case 3:
                    crearCuenta(scanner);
                    break;
                case 4:
                    cambiarEstadoACerrado(scanner);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    public void consultarTodasLasCuentas(Scanner scanner) {
        List<Cuenta> todasLasCuentas = cuentaRepository.findAll();

        if (todasLasCuentas.isEmpty()) {
            System.out.println("No se encontraron cuentas.");
        } else {
            System.out.println("Todas las cuentas:");
            for (Cuenta cuenta : todasLasCuentas) {
                System.out.println(cuenta);
            }
        }
    }

    public void buscarCuentaPorId(Scanner scanner) {
        System.out.println("Ingrese el ID de la cuenta:");
        String idCuentaString = scanner.next();
        ObjectId idCuenta = new ObjectId(idCuentaString);

        Cuenta cuenta = cuentaRepository.findById(idCuenta).orElse(null);

        if (cuenta == null) {
            System.out.println("No se encontraron cuentas con el ID proporcionado.");
        } else {
            System.out.println("Cuenta encontrada con el ID " + idCuenta + ":");
            System.out.println(cuenta);
        }
    }

    public void crearCuenta(Scanner scanner) {
        System.out.println("Ingrese el saldo inicial:");
        int saldoInicial = scanner.nextInt();
    
        List<Usuario> usuarios = usuarioRepository.findAll();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios disponibles.");
            return;
        }
    
        System.out.println("Seleccione el ID del usuario asociado:");
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.getId() + ": " + usuario.getNombre());
        }
    
        String idUsuarioString = scanner.next();
        ObjectId idUsuario = new ObjectId(idUsuarioString);
    
        Usuario usuarioSeleccionado = usuarioRepository.findById(idUsuario).orElse(null);
        if (usuarioSeleccionado == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }
    
        // Crear la nueva cuenta
        Cuenta nuevaCuenta = new Cuenta(saldoInicial, usuarioSeleccionado);
        cuentaRepository.save(nuevaCuenta);
        System.out.println("Cuenta creada: " + nuevaCuenta);
    
        // Agregar la nueva cuenta al array de cuentas del usuario seleccionado
        usuarioSeleccionado.getCuentas().add(nuevaCuenta.getId());
        usuarioRepository.save(usuarioSeleccionado);
        System.out.println("Cuenta añadida al usuario: " + usuarioSeleccionado);
    }
    

    public void cambiarEstadoACerrado(Scanner scanner) {
        System.out.println("Ingrese el ID de la cuenta que desea cerrar:");
        String idCuentaString = scanner.next();
        ObjectId idCuenta = new ObjectId(idCuentaString);

        cuentaRepository.cambiarEstadoACerrado(idCuenta);
        System.out.println("El estado de la cuenta con ID " + idCuenta + " se ha cambiado a 'cerrado'.");
    }
}

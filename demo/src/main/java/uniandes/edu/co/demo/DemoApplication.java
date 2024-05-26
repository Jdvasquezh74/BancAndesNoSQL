package uniandes.edu.co.demo;


import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import uniandes.edu.co.demo.modelo.Cuenta;
import uniandes.edu.co.demo.repository.CuentaRepository;

@ComponentScan({"uniandes.edu.co.demo.repository"})
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private CuentaRepository cuentaRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
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

        // Cerrar el Scanner después de salir del bucle
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
        Integer idCuenta = scanner.nextInt();

        List<Cuenta> res = cuentaRepository.buscarPorId(idCuenta);

        if (res.isEmpty()) {
            System.out.println("No se encontraron cuentas con el ID proporcionado.");
        } else {
            System.out.println("Cuenta(s) encontrada(s) con el ID " + idCuenta + ":");
            for (Cuenta cuenta : res) {
                System.out.println(cuenta);
            }
        }
    }

    public void cambiarEstadoACerrado(Scanner scanner) {
        System.out.println("Ingrese el ID de la cuenta que desea cerrar:");
        Integer idCuenta = scanner.nextInt();

        cuentaRepository.cambiarEstadoACerrado(idCuenta);
        System.out.println("El estado de la cuenta con ID " + idCuenta + " se ha cambiado a 'cerrado'.");
    }

    public void crearCuenta(Scanner scanner) {
        System.out.println("Ingrese el saldo inicial:");
        int saldoInicial = scanner.nextInt();

        Cuenta nuevaCuenta = new Cuenta(
            saldoInicial
        );

        cuentaRepository.save(nuevaCuenta);
        System.out.println("Cuenta creada: " + nuevaCuenta);
    }
}

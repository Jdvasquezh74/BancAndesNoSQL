package uniandes.edu.co.demo;

import java.util.Arrays;
import java.util.List;

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

        // QUERIES
        List<Cuenta> res = cuentaRepository.buscarPorId(1);

        for (Cuenta b : res) {
            System.out.println(b);
        }

        // Obtener todas las cuentas
        List<Cuenta> todasLasCuentas = cuentaRepository.findAll();

        System.out.println("Todas las cuentas:");
        for (Cuenta cuenta : todasLasCuentas) {
            System.out.println(cuenta);
        }

        // Crear y guardar una nueva cuenta
        Cuenta nuevaCuenta = new Cuenta(
            5000,  // Saldo inicial
            "2024-05-25T10:00:00Z",  // Fecha de la última operación en formato ISO 8601
            "activa",   // Estado de la cuenta
            Arrays.asList(1L, 2L, 3L)  // Lista de operaciones
        );
        
        cuentaRepository.save(nuevaCuenta);
        System.out.println("Cuenta creada: " + nuevaCuenta);

        // Update
        cuentaRepository.cambiarEstadoACerrado(4);
    }
}

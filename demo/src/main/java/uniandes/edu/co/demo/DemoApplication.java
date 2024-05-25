package uniandes.edu.co.demo;

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
public class DemoApplication  implements CommandLineRunner{

	@Autowired
	private CuentaRepository cuentaRepository;


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception{

		//QUERIES
		List<Cuenta> res = cuentaRepository.buscarPorId(1);

		for(Cuenta b: res){
			System.out.println(b);
		}
		


		//INSERT / UPDATE
		//cuentaRepository.save(new Bar(101, "Bar de prueba","Cali","Alto",2));

		//Update
		cuentaRepository.cambiarEstadoACerrado(4);
	}

}

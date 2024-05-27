package uniandes.edu.co.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.demo.modelo.Cuenta;
import uniandes.edu.co.demo.modelo.OperacionBancaria;
import uniandes.edu.co.demo.repository.CuentaRepository;
import uniandes.edu.co.demo.repository.OperacionBancariaRepository;
import uniandes.edu.co.demo.repository.UsuarioRepository;


@Controller
public class OperacionBancariaController {

    @Autowired
    OperacionBancariaRepository operacionBancariaRepository;
    @Autowired
    CuentaRepository cuentaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    

    @GetMapping("/operacionesbancarias/new")
    public String operacionBancariaForm(Model model) {
        
        model.addAttribute("cuentas", cuentaRepository.findAll());
        
        
        return "operacionBancariaFormulario";
    }

    @GetMapping("/new/consignacion")
public String consignacionForm(Model model) {
    model.addAttribute("cuentas", cuentaRepository.findAll());
    OperacionBancaria operacionBancaria = new OperacionBancaria();
    
    operacionBancaria.setTipooperacion("consignacion");
    System.out.println(operacionBancaria.getTipooperacion());
    model.addAttribute("operacionBancaria",operacionBancaria);
    return "consignacion";
}

@GetMapping("/new/retiro")
public String retiroForm(Model model) {
    model.addAttribute("cuentas", cuentaRepository.findAll());
    OperacionBancaria operacionBancaria = new OperacionBancaria();
    operacionBancaria.setTipooperacion("retiro");
    model.addAttribute("operacionBancaria", operacionBancaria);
    return "retiro";
}

@GetMapping("/new/transferencia")
public String transferenciaForm(Model model) {
    model.addAttribute("cuentas", cuentaRepository.findAll());
    OperacionBancaria operacionBancaria = new OperacionBancaria();
    operacionBancaria.setTipooperacion("transferencia");

    model.addAttribute("operacionBancaria", operacionBancaria);

    return "transferencia";
}



    @PostMapping("/operacionesbancarias/new/save")
    public String operacionBancariaGuardar(@ModelAttribute OperacionBancaria operacionBancaria) {
        // Obtener la fecha y hora actual
        LocalDateTime now = LocalDateTime.now();
        
        // Definir el formato deseado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        
        // Formatear la fecha y hora
        String formattedDateTime = now.format(formatter);
        
        operacionBancaria.setFechaOperacion(formattedDateTime.toString());
        
        System.out.println("ID de cuentaafectada: " + operacionBancaria.getCuentaafectada());
        
        System.out.println("ID de destino: " + operacionBancaria.getCuentadestino());
        System.out.println("Monto: " + operacionBancaria.getMonto());
        System.out.println("Fecha y hora: " + operacionBancaria.getFechaOperacion());
        System.out.println("Tipo de operación: " + operacionBancaria.getTipooperacion());

        System.out.println(operacionBancaria.getCuentaafectada().getClass());

        if(operacionBancaria.getTipooperacion().equals("retiro")||operacionBancaria.getTipooperacion().equals("consignacion")){
            operacionBancaria.setCuentadestino(operacionBancaria.getCuentaafectada());
        }
       
        
        Optional<Cuenta> cuentaAfectadaOptional = cuentaRepository.findById(operacionBancaria.getCuentaafectada());
        Optional<Cuenta> cuentaDestinoOptional = cuentaRepository.findById(operacionBancaria.getCuentadestino());

        
        Cuenta cuentaAfectada = cuentaAfectadaOptional.get();
        Cuenta cuentaDestino = cuentaDestinoOptional.get();
       

       
       
        
        
       

        if(!(cuentaAfectada.getEstado().equals("activada")) ){
            
            return "cuentanoactiva";
        }
        else{
            if(operacionBancaria.getTipooperacion().equals("transferencia") &&  !(cuentaDestino.getEstado().equals("activada")) ){
                return "cuentanoactiva";
            }
            if(operacionBancaria.getTipooperacion().equals("transferencia") &&  operacionBancaria.getCuentaafectada().equals(operacionBancaria.getCuentadestino()) ){
                return "montoequivocado";
            }
            
        }
        

        if(cuentaAfectada.getSaldo() < 0 ){
            return "montoequivocado";
        }
        System.out.println(operacionBancaria.getMonto());
        System.out.println(cuentaAfectada.getSaldo());
        System.out.println(cuentaAfectada.getSaldo() < operacionBancaria.getMonto());

        if((operacionBancaria.getTipooperacion().equals("retiro") || operacionBancaria.getTipooperacion().equals("transferencia")) && cuentaAfectada.getSaldo() < operacionBancaria.getMonto()){
            
            return "montoequivocado";
        }
        

        if(operacionBancaria.getMonto() <= 0 ){
            return "montoequivocado";
        }

        
        operacionBancariaRepository.save(operacionBancaria);
        
      
        if(operacionBancaria.getTipooperacion().equals("retiro")){
           float nuevoSaldo = cuentaAfectada.getSaldo()  -  operacionBancaria.getMonto();
           cuentaRepository.actualizarSaldo(operacionBancaria.getCuentaafectada(), nuevoSaldo);
        }
        if(operacionBancaria.getTipooperacion().equals("consignacion")){
            float nuevoSaldo = cuentaAfectada.getSaldo()  +  operacionBancaria.getMonto();
           cuentaRepository.actualizarSaldo(operacionBancaria.getCuentaafectada(), nuevoSaldo);

        }
        if (operacionBancaria.getTipooperacion().equals("transferencia")) {
            float nuevoSaldoafectada = cuentaAfectada.getSaldo()  -  operacionBancaria.getMonto();
            float nuevoSaldodestino = cuentaDestino.getSaldo()  +  operacionBancaria.getMonto();

            cuentaRepository.actualizarSaldo(operacionBancaria.getCuentaafectada(), nuevoSaldoafectada);
            cuentaRepository.actualizarSaldo(operacionBancaria.getCuentadestino(), nuevoSaldodestino);
        }
       
        return "redirect:/";
    }

}
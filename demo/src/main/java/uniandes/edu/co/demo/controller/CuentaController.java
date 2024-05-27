package uniandes.edu.co.demo.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import uniandes.edu.co.demo.modelo.Cuenta;
import uniandes.edu.co.demo.modelo.OperacionBancaria;
import uniandes.edu.co.demo.modelo.Usuario;
import uniandes.edu.co.demo.repository.CuentaRepository;
import uniandes.edu.co.demo.repository.OficinaRepository;
import uniandes.edu.co.demo.repository.OperacionBancariaRepository;
import uniandes.edu.co.demo.repository.UsuarioRepository;

@Controller
public class CuentaController {

    @Autowired
    CuentaRepository cuentaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    OficinaRepository oficinaRepository;
    @Autowired
    OperacionBancariaRepository operacionBancariaRepository;


    @GetMapping("/cuentas")
    public String cuentas(Model model) {
    model.addAttribute("cuenta", new Cuenta());
    
    model.addAttribute("cuentas", cuentaRepository.findAll());
    
    return "cuentas";
}




    @GetMapping("/cuentas/new")
    public String cuentaForm(Model model) {
        model.addAttribute("cuenta", new Cuenta());
        model.addAttribute("usuarios", usuarioRepository.findAll());
        model.addAttribute("oficinas", oficinaRepository.findAll());
        return "cuentaFormulario";
    }




    @PostMapping("/cuentas/new/save")
    public String cuentaGuardar(@ModelAttribute Cuenta cuenta) {
        cuenta.setId(new ObjectId());
        cuenta.setOperaciones(new ArrayList<ObjectId>());
        usuarioRepository.anadirCuenta(cuenta.getClienteasociado().getId(), cuenta.getId());
        
         // Obtener la fecha y hora actual
        LocalDateTime now = LocalDateTime.now();
        // Definir el formato deseado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        // Formatear la fecha y hora
        String formattedDateTime = now.format(formatter);
        cuenta.setFechaultimaoperacion(formattedDateTime.toString());

        cuenta.setFechacreacioncuenta(formattedDateTime);

        cuenta.setEstado("activada");

        ObjectId clienteAsociadoId = cuenta.getClienteasociado().getId();

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(clienteAsociadoId);

       
    if (usuarioOptional.isPresent()) {
        Usuario usuario = usuarioOptional.get();
        cuenta.setClienteasociado(usuario);
        cuentaRepository.save(cuenta);
    } else {
        return "redirect:/error?message=UserNotFound";
    }

        
        cuentaRepository.save(
                cuenta);
        return "redirect:/";
    }

    @GetMapping("/cuentas/edit")
    public String mostrarFormulario(Model model) {
        Iterable<Cuenta> cuentas = cuentaRepository.findAll();
        model.addAttribute("cuentas", cuentas);
        model.addAttribute("cuenta", new Cuenta());
        return "cuentaEditar";
    }

    @PostMapping("/cuentas/edit/save")
    public String guardarCuenta(@ModelAttribute Cuenta cuenta) {
    Cuenta cuentaExistente = new Cuenta();
    Optional<Cuenta> cuentaExistenteOptional = cuentaRepository.findById(cuenta.getId());
    if (cuentaExistenteOptional.isPresent()) {
    cuentaExistente = cuentaExistenteOptional.get();
    
    } else {
      return "error";
    }

        if (cuentaExistente != null) {
            cuentaExistente.setEstado(cuenta.getEstado());
            cuentaRepository.save(cuentaExistente);
        }
        return "redirect:/";
    }

    @GetMapping("/cuentas/extracto/{id}/{fecha}")
    public String generarExtracto(Model model, @PathVariable("id") String id,@PathVariable("fecha") String fecha ) {
     
        System.out.println(id);
        System.out.println(fecha);
        // Convertir la cadena a objeto Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date fechaInicial;
            try {
                fechaInicial = dateFormat.parse(fecha);
                 // Sumar 30 días
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fechaInicial);
            calendar.add(Calendar.DAY_OF_MONTH, 30);
            Date fechaFinal = calendar.getTime();

            // Formatear la fecha final como string
            String fechaFinalStr = dateFormat.format(fechaFinal);
            
            String conxeros1 = fecha + " 00:00:00";
            String conxeros = fechaFinalStr + " 00:00:00";
            
            System.out.println(conxeros1);
            System.out.println(conxeros);


            List<OperacionBancaria> operaciones =  operacionBancariaRepository.findOperacionesByCuentaAndFecha(new ObjectId(id), conxeros1, conxeros);
           
            for(OperacionBancaria op : operaciones){
                System.out.println(op.getId());
                System.out.println("a");
            }
            System.out.println(operaciones);
            model.addAttribute("operaciones", operaciones);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            /*
             db.operaciones.aggregate([
  {
    "$match": {
      "cuentaafectada": ObjectId("6653bb88b738e51b7695dbf0")
    }
  },
  {
    "$addFields": {
      "fechaoperacionDate": {
        "$dateFromString": {
          "dateString": "$fechaoperacion",
          "format": "%d/%m/%Y %H:%M:%S"
        }
      }
    }
  },
  {
    "$match": {
      "fechaoperacionDate": {
        "$gte": ISODate("2024-05-01T00:00:00Z"),
        "$lt": ISODate("2024-05-31T00:00:00Z")
      }
    }
  }
]) 
             */

           

       
    
    return "consultacuenta";
    }

   
}

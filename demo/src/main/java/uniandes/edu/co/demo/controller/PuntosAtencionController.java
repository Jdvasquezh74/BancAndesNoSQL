package uniandes.edu.co.demo.controller;

import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.demo.modelo.Oficina;
import uniandes.edu.co.demo.modelo.PuntoAtencion;
import uniandes.edu.co.demo.repository.OficinaRepository;

@Controller
public class PuntosAtencionController {
    @Autowired
    private OficinaRepository oficinaRepository;
    
    
    

    @GetMapping("puntosAtencion/new")
    public String puntosAtencionForm(Model model){
        Collection<Oficina> oficinas = oficinaRepository.findAll();
        model.addAttribute("oficinas", oficinas);
        model.addAttribute("puntoAtencion", new PuntoAtencion());
       
        
        
        return "puntoAtencionFormulario";

    }

    @PostMapping("/puntosAtencion/new/save")
    public String guardarPuntoAtencion(@ModelAttribute PuntoAtencion puntoAtencion){
        List<String> operaciones = new ArrayList<>();
        puntoAtencion.setOperaciones(operaciones);
        if(puntoAtencion.getTipo().equals("canalDigital")){
            
            puntoAtencion.getOperaciones().add("CONSULTA");
        }

        if(puntoAtencion.getTipo().equals("cajeroAutomatico")){
            
            puntoAtencion.getOperaciones().add("CONSULTA");
            puntoAtencion.getOperaciones().add("RETIRO");
        }
        if(puntoAtencion.getTipo().equals("atencionPersonalizada")){
            
            puntoAtencion.getOperaciones().add("CONSULTA");
            puntoAtencion.getOperaciones().add("RETIRO");
            puntoAtencion.getOperaciones().add("CONSIGNACION");
            puntoAtencion.getOperaciones().add("TRANSFERENCIA");

        }

        puntoAtencion.setId(new ObjectId());
        
        oficinaRepository.anadirPuntoAtencion(puntoAtencion.getOficinaAsociada(),puntoAtencion.getId(), puntoAtencion.getTipo(),puntoAtencion.getLatitud(),puntoAtencion.getLongitud(),puntoAtencion.getOperaciones());
        return "redirect:/";


    }

    @GetMapping("puntosAtencion/delete")
    public String borrarpuntosAtencionForm(Model model){
        model.addAttribute("puntosAtencionIds", oficinaRepository.findAll());
    
        return "borrarpuntoAtencionFormulario";

    }

    @GetMapping("puntosAtencion/{id}/delete")
    public String bebidaBorrar(@PathVariable("id") String id) {
        ObjectId ids = new ObjectId(id);
        System.out.println(ids.toString());
        oficinaRepository.deletePuntoAtencionById(ids);
        return "redirect:/";
    }

    
}

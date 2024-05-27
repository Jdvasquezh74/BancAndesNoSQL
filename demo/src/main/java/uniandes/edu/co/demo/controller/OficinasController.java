package uniandes.edu.co.demo.controller;



import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.demo.modelo.Oficina;
import uniandes.edu.co.demo.modelo.PuntoAtencion;
import uniandes.edu.co.demo.modelo.Usuario;
import uniandes.edu.co.demo.repository.OficinaRepository;
import uniandes.edu.co.demo.repository.UsuarioRepository;





@Controller
public class OficinasController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private OficinaRepository oficinaRepository;



     @GetMapping("/oficinas/new")
    public String oficinasForm(Model model) {
        Collection<Usuario> usuarios = usuarioRepository.findAll();
       
        Iterator<Usuario> iterator = usuarios.iterator();
        while (iterator.hasNext()) {
            Usuario user = iterator.next();
            if (!user.getRol().equals("gerenteoficina")) {
            iterator.remove(); 
            }
        }
        
        model.addAttribute("usuarios",usuarios);
        
        model.addAttribute("oficina", new Oficina() );
        return "oficinaFormulario";
    }

    @PostMapping("/oficinas/new/save")
    public String guardarOficina(@ModelAttribute Oficina oficina) {
    System.out.println("id: " + oficina.getId());
    System.out.println("Nombre: " + oficina.getNombre());
    System.out.println("Direccion: " + oficina.getDireccion());
    System.out.println("Cantidad de puntos de atención: " + oficina.getCantidadpuntosatencion());
    System.out.println("ID del gerente de oficina: " + oficina.getIdgerenteoficina());
    System.out.println("Hora de apertura: " + oficina.getHoraapertura());
    System.out.println("Hora de cierre: " + oficina.getHoracierre());
    
    List<PuntoAtencion> puntos = new ArrayList<>();
    oficina.setPuntosatencion(puntos);
    oficinaRepository.save(
        oficina
    );

   
    return "redirect:/"; 
}



}


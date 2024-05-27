package uniandes.edu.co.demo.controller;


import java.util.ArrayList;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.demo.modelo.Usuario;
import uniandes.edu.co.demo.repository.UsuarioRepository;





@Controller
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;


    @GetMapping("usuarios/new")
    public String usuarioForm(Model model) {
        model.addAttribute("usuario", new Usuario() );
        return "usuarioFormulario";
    }

    @PostMapping("/usuarios/new/save")
    public String usuarioGuardar(@ModelAttribute Usuario usuario){
        System.out.println("Login: " + usuario.getLogin());
System.out.println("Clave: " + usuario.getClave());
System.out.println("Rol: " + usuario.getRol());
System.out.println("Número de Identificación: " + usuario.getNumeroidentificacion());
System.out.println("Tipo de Identificación: " + usuario.getTipoidentificacion());
System.out.println("Nombre: " + usuario.getNombre());
System.out.println("Nacionalidad: " + usuario.getNacionalidad());
System.out.println("Dirección Física: " + usuario.getDireccionfisica());
System.out.println("Dirección Electrónica: " + usuario.getDireccionelectronica());
System.out.println("Teléfono: " + usuario.getTelefono());
System.out.println("Ciudad: " + usuario.getCiudad());
System.out.println("Departamento: " + usuario.getDepartamento());
System.out.println("Código Postal: " + usuario.getCodigopostal());
usuario.setCuentas(new ArrayList<ObjectId>());

usuarioRepository.save(usuario);


        
           
        return "redirect:/";
    }

    
    
    
    
    
}

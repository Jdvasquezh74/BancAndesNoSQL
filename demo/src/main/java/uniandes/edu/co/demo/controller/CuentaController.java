package uniandes.edu.co.demo.controller;


import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import uniandes.edu.co.demo.modelo.Cuenta;
import uniandes.edu.co.demo.modelo.Usuario;
import uniandes.edu.co.demo.repository.CuentaRepository;
import uniandes.edu.co.demo.repository.OficinaRepository;
import uniandes.edu.co.demo.repository.UsuarioRepository;

@Controller
public class CuentaController {

    @Autowired
    CuentaRepository cuentaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    OficinaRepository oficinaRepository;



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

   
}

package uniandes.edu.co.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BancAndesController {
 
    @GetMapping("/")
    public String index() {
        return "index";
    }

}

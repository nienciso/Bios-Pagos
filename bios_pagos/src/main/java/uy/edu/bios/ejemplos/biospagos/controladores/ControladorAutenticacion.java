package uy.edu.bios.ejemplos.biospagos.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorAutenticacion {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
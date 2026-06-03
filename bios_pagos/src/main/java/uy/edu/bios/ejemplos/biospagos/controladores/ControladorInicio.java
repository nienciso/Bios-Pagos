package uy.edu.bios.ejemplos.biospagos.controladores;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uy.edu.bios.ejemplos.biospagos.servicios.IServicioSucursales;

@Controller
public class ControladorInicio {

    private final IServicioSucursales servicioSucursales;

    public ControladorInicio(IServicioSucursales servicioSucursales) {
        this.servicioSucursales = servicioSucursales;
    }

    @GetMapping("/")
    public String inicio(Model model, Authentication authentication) {

        model.addAttribute("sucursales", servicioSucursales.listar());

        boolean autenticado = authentication != null && authentication.isAuthenticated()
                && !"anonymousUser".equals(authentication.getName());

        model.addAttribute("autenticado", autenticado);

        if (autenticado) {
            model.addAttribute("usuario", authentication.getName());
            model.addAttribute("esEmpleado", authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_EMPLEADO")));
            model.addAttribute("esCliente", authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_CLIENTE")));
        } else {
            model.addAttribute("esEmpleado", false);
            model.addAttribute("esCliente", false);
        }

        return "inicio";
    }
}
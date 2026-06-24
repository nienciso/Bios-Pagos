package uy.edu.bios.ejemplos.biospagos.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uy.edu.bios.ejemplos.biospagos.dominio.Cliente;
import uy.edu.bios.ejemplos.biospagos.excepciones.ExcepcionBiosPagos;
import uy.edu.bios.ejemplos.biospagos.servicios.IServicioClientes;

@Controller
public class ControladorClientes {

    private final IServicioClientes servicioClientes;

    public ControladorClientes(IServicioClientes servicioClientes) {
        this.servicioClientes = servicioClientes;
    }

    @GetMapping("/clientes")
    public String listar(Model model) {
        model.addAttribute("clientes", servicioClientes.listar());
        return "clientes/listado";
    }

    @GetMapping("/clientes/nuevo")
    public String mostrarFormularioAlta(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/formulario";
    }

    @PostMapping("/clientes/guardar")
    public String guardar(@Valid Cliente cliente, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "clientes/formulario";
        }

        try {
            servicioClientes.guardar(cliente);
            return "redirect:/clientes";

        } catch (ExcepcionBiosPagos ex) {
            model.addAttribute("error", ex.getMessage());
            return "clientes/formulario";
        }
    }

    @GetMapping("/clientes/editar/{correoElectronico}")
    public String mostrarFormularioEdicion(@PathVariable String correoElectronico, Model model) {

        Cliente cliente = servicioClientes.buscar(correoElectronico).orElse(null);

        if (cliente == null) {
            return "redirect:/clientes";
        }

        model.addAttribute("cliente", cliente);
        return "clientes/formulario";
    }

    @GetMapping("/clientes/eliminar/{correoElectronico}")
public String eliminar(@PathVariable String correoElectronico, Model model) {

    try {
        servicioClientes.eliminar(correoElectronico);
        return "redirect:/clientes";

    } catch (Exception e) {

        model.addAttribute("clientes", servicioClientes.listar());
        model.addAttribute("error",
                "No es posible eliminar clientes con envíos asociados.");

        return "clientes/listado";
    }
}
    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/registro";
    }

    @PostMapping("/registro")
    public String registrar(@Valid Cliente cliente, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "clientes/registro";
        }

        try {
            servicioClientes.guardar(cliente);
            return "redirect:/login";

        } catch (ExcepcionBiosPagos ex) {
            model.addAttribute("error", ex.getMessage());
            return "clientes/registro";
        }
    }
}
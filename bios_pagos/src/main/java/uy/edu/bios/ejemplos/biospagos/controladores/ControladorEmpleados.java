package uy.edu.bios.ejemplos.biospagos.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uy.edu.bios.ejemplos.biospagos.dominio.Empleado;
import uy.edu.bios.ejemplos.biospagos.servicios.IServicioEmpleados;

@Controller
public class ControladorEmpleados {

    private final IServicioEmpleados servicioEmpleados;

    public ControladorEmpleados(IServicioEmpleados servicioEmpleados) {
        this.servicioEmpleados = servicioEmpleados;
    }

    @GetMapping("/empleados")
    public String listar(String busqueda, String tipo, Model model) {

        if (busqueda != null && !busqueda.isBlank()) {

            if ("correo".equals(tipo)) {
                model.addAttribute("empleados", servicioEmpleados.buscarPorCorreo(busqueda));
            } else {
                model.addAttribute("empleados", servicioEmpleados.buscarPorNombre(busqueda));
            }

        } else {
            model.addAttribute("empleados", servicioEmpleados.listar());
        }

        model.addAttribute("busqueda", busqueda);
        model.addAttribute("tipo", tipo);

        return "empleados/listado";
    }

    @GetMapping("/empleados/nuevo")
    public String mostrarFormularioAlta(Model model) {
        model.addAttribute("empleado", new Empleado());
        return "empleados/formulario";
    }

    @PostMapping("/empleados/guardar")
    public String guardar(@Valid Empleado empleado, BindingResult result) {

        Empleado empleadoAnterior = servicioEmpleados.buscar(empleado.getCorreoElectronico()).orElse(null);

        if (empleadoAnterior != null) {

            if (empleado.getClaveAcceso() == null || empleado.getClaveAcceso().isBlank()) {
                empleado.setClaveAcceso(empleadoAnterior.getClaveAcceso());
            }

            if (empleado.getFechaIngreso() == null) {
                empleado.setFechaIngreso(empleadoAnterior.getFechaIngreso());
            }
        }

        if (result.hasErrors()) {
            return "empleados/formulario";
        }

        servicioEmpleados.guardar(empleado);
        return "redirect:/empleados";
    }

@GetMapping("/empleados/editar/{correoElectronico}")
public String mostrarFormularioEdicion(@PathVariable String correoElectronico, Model model) {

    Empleado empleado = servicioEmpleados.buscar(correoElectronico).orElse(null);

    if (empleado == null) {
        return "redirect:/empleados";
    }

    model.addAttribute("empleado", empleado);
    return "empleados/formulario";
}

    @GetMapping("/empleados/eliminar/{correoElectronico}")
    public String eliminar(@PathVariable String correoElectronico) {
        servicioEmpleados.eliminar(correoElectronico);
        return "redirect:/empleados";
    }

    @GetMapping("/empleados/ver/{correoElectronico}")
    public String ver(@PathVariable String correoElectronico, Model model) {

        Empleado empleado = servicioEmpleados.buscar(correoElectronico).orElse(null);

        if (empleado == null) {
            return "redirect:/empleados";
        }

        model.addAttribute("empleado", empleado);
        return "empleados/ver";
    }
}
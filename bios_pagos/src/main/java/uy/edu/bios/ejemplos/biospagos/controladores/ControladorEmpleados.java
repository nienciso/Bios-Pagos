package uy.edu.bios.ejemplos.biospagos.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uy.edu.bios.ejemplos.biospagos.dominio.Empleado;
import uy.edu.bios.ejemplos.biospagos.excepciones.ExcepcionBiosPagos;
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
        Empleado empleado = new Empleado();
        empleado.setActivo(true);

        model.addAttribute("empleado", empleado);
        return "empleados/formulario";
    }

    @PostMapping("/empleados/guardar")
    public String guardar(@Valid Empleado empleado,
            BindingResult result,
            @RequestParam(required = false) String activo,
            Model model) {

        empleado.setActivo(activo != null);

        if (result.hasErrors()) {
            return "empleados/formulario";
        }

        try {
            servicioEmpleados.guardar(empleado);
            return "redirect:/empleados";

        } catch (ExcepcionBiosPagos ex) {
            model.addAttribute("error", ex.getMessage());
            return "empleados/formulario";
        }
    }

    @GetMapping("/empleados/eliminar/{correoElectronico}")
    public String eliminar(@PathVariable String correoElectronico, Model model) {

        try {
            servicioEmpleados.eliminar(correoElectronico);
            model.addAttribute("mensaje", "El empleado fue eliminado o dado de baja correctamente.");

        } catch (Exception e) {
            model.addAttribute("error", "No fue posible eliminar el empleado.");
        }

        model.addAttribute("empleados", servicioEmpleados.listar());

        return "empleados/listado";
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
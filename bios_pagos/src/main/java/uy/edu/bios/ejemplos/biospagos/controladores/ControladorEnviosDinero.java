package uy.edu.bios.ejemplos.biospagos.controladores;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uy.edu.bios.ejemplos.biospagos.dominio.Cliente;
import uy.edu.bios.ejemplos.biospagos.dominio.EnvioDinero;
import uy.edu.bios.ejemplos.biospagos.servicios.IServicioClientes;
import uy.edu.bios.ejemplos.biospagos.servicios.IServicioEmpleados;
import uy.edu.bios.ejemplos.biospagos.servicios.IServicioEnviosDinero;
import uy.edu.bios.ejemplos.biospagos.servicios.IServicioSucursales;

@Controller
public class ControladorEnviosDinero {

    private final IServicioEnviosDinero servicioEnviosDinero;
    private final IServicioClientes servicioClientes;
    private final IServicioEmpleados servicioEmpleados;
    private final IServicioSucursales servicioSucursales;

    public ControladorEnviosDinero(
            IServicioEnviosDinero servicioEnviosDinero,
            IServicioClientes servicioClientes,
            IServicioEmpleados servicioEmpleados,
            IServicioSucursales servicioSucursales) {

        this.servicioEnviosDinero = servicioEnviosDinero;
        this.servicioClientes = servicioClientes;
        this.servicioEmpleados = servicioEmpleados;
        this.servicioSucursales = servicioSucursales;
    }

    @GetMapping("/envios")
    public String listar(Model model) {
        model.addAttribute("envios", servicioEnviosDinero.listar());
        return "envios/listado";
    }

    @GetMapping("/envios/nuevo")
    public String mostrarFormularioAlta(Model model) {
        model.addAttribute("envio", new EnvioDinero());
        cargarDatosFormulario(model);
        return "envios/formulario";
    }

    @PostMapping("/envios/guardar")
    public String guardar(@Valid EnvioDinero envio, BindingResult result, Model model) {

        if (envio.getFechaHoraRegistro() == null) {
            envio.setFechaHoraRegistro(LocalDateTime.now());
        }

        if (result.hasErrors()) {
            cargarDatosFormulario(model);
            return "envios/formulario";
        }

        servicioEnviosDinero.guardar(envio);
        return "redirect:/envios";
    }

    @GetMapping("/envios/ver/{id}")
    public String ver(@PathVariable Long id, Model model) {

        EnvioDinero envio = servicioEnviosDinero.buscar(id).orElse(null);

        if (envio == null) {
            return "redirect:/envios";
        }

        model.addAttribute("envio", envio);
        return "envios/ver";
    }

    @GetMapping("/envios/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        servicioEnviosDinero.eliminar(id);
        return "redirect:/envios";
    }

    private void cargarDatosFormulario(Model model) {
        model.addAttribute("clientes", servicioClientes.listar());
        model.addAttribute("empleados", servicioEmpleados.listar());
        model.addAttribute("sucursales", servicioSucursales.listar());
    }

    @GetMapping("/envios/pagar/{id}")
public String pagar(@PathVariable Long id) {

    EnvioDinero envio = servicioEnviosDinero.buscar(id).orElse(null);

    if (envio != null && envio.getFechaHoraPago() == null) {
        envio.setFechaHoraPago(LocalDateTime.now());
        servicioEnviosDinero.guardar(envio);
    }

    return "redirect:/envios";
}

@GetMapping("/mis-envios")
public String misEnvios(String estado, Model model, org.springframework.security.core.Authentication authentication) {

    String correoElectronico = authentication.getName();

    Cliente cliente = servicioClientes.buscar(correoElectronico).orElse(null);

    if (cliente == null) {
        return "redirect:/";
    }

    if ("cobrados".equals(estado)) {
        model.addAttribute("envios", servicioEnviosDinero.listarPorClienteCobrados(cliente));
    } else if ("pendientes".equals(estado)) {
        model.addAttribute("envios", servicioEnviosDinero.listarPorClientePendientes(cliente));
    } else {
        model.addAttribute("envios", servicioEnviosDinero.listarPorCliente(cliente));
    }

    model.addAttribute("estado", estado);

    return "envios/misenvios";
}
}
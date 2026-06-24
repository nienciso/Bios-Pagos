package uy.edu.bios.ejemplos.biospagos.controladores;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;

import uy.edu.bios.ejemplos.biospagos.dominio.Sucursal;
import uy.edu.bios.ejemplos.biospagos.excepciones.ExcepcionBiosPagos;
import uy.edu.bios.ejemplos.biospagos.servicios.IServicioSucursales;

@Controller
public class ControladorSucursales {

    private final IServicioSucursales servicioSucursales;

    public ControladorSucursales(IServicioSucursales servicioSucursales) {
        this.servicioSucursales = servicioSucursales;
    }

    @GetMapping("/sucursales")
    public String listar(Model model) {
        model.addAttribute("sucursales", servicioSucursales.listar());
        return "sucursales/listado";
    }

    @GetMapping("/sucursales/nueva")
    public String mostrarFormularioAlta(Model model) {
        model.addAttribute("sucursal", new Sucursal());
        return "sucursales/formulario";
    }

    @PostMapping("/sucursales/guardar")
    public String guardar(@Valid Sucursal sucursal,
            BindingResult result,
            MultipartFile archivo,
            Model model) {

        if (result.hasErrors()) {
            return "sucursales/formulario";
        }

        if (archivo != null && !archivo.isEmpty()) {
            try {
                String nombreArchivo = archivo.getOriginalFilename();

                String rutaCarpeta = "src/main/resources/static/imagenes";
                File carpeta = new File(rutaCarpeta);

                if (!carpeta.exists()) {
                    carpeta.mkdirs();
                }

                Path rutaArchivo = Paths.get(rutaCarpeta, nombreArchivo);
                Files.write(rutaArchivo, archivo.getBytes());

                sucursal.setFotografiaFachada(nombreArchivo);

            } catch (IOException e) {
                model.addAttribute("error", "No se pudo guardar la imagen.");
                return "sucursales/formulario";
            }
        } else {
            Sucursal sucursalAnterior = servicioSucursales.buscar(sucursal.getNumero()).orElse(null);

            if (sucursalAnterior != null) {
                sucursal.setFotografiaFachada(sucursalAnterior.getFotografiaFachada());
            }
        }

        try {
            servicioSucursales.guardar(sucursal);
            return "redirect:/sucursales";

        } catch (ExcepcionBiosPagos ex) {
            model.addAttribute("error", ex.getMessage());
            return "sucursales/formulario";
        }
    }

    @GetMapping("/sucursales/editar/{numero}")
    public String mostrarFormularioEdicion(@PathVariable Integer numero, Model model) {

        Sucursal sucursal = servicioSucursales.buscar(numero).orElse(null);

        if (sucursal == null) {
            return "redirect:/sucursales";
        }

        model.addAttribute("sucursal", sucursal);
        return "sucursales/formulario";
    }

@GetMapping("/sucursales/eliminar/{numero}")
public String eliminar(@PathVariable Integer numero, Model model) {

    try {
        servicioSucursales.eliminar(numero);
        return "redirect:/sucursales";

    } catch (Exception e) {
        model.addAttribute("sucursales", servicioSucursales.listar());
        model.addAttribute("error", "No es posible eliminar sucursales con envíos asociados.");
        return "sucursales/listado";
    }
}
}
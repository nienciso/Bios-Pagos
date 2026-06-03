package uy.edu.bios.ejemplos.biospagos.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import uy.edu.bios.ejemplos.biospagos.dominio.Empleado;
import uy.edu.bios.ejemplos.biospagos.repositorios.RepositorioEmpleado;
import uy.edu.bios.ejemplos.biospagos.repositorios.RepositorioEnvioDinero;

@Service
public class ServicioEmpleados implements IServicioEmpleados {

    private final RepositorioEmpleado repositorioEmpleado;
    private final RepositorioEnvioDinero repositorioEnvioDinero;

    public ServicioEmpleados(RepositorioEmpleado repositorioEmpleado,
            RepositorioEnvioDinero repositorioEnvioDinero) {

        this.repositorioEmpleado = repositorioEmpleado;
        this.repositorioEnvioDinero = repositorioEnvioDinero;
    }

    @Override
    public List<Empleado> listar() {
        return repositorioEmpleado.findAll();
    }

    @Override
    public Optional<Empleado> buscar(String correoElectronico) {
        return repositorioEmpleado.findById(correoElectronico);
    }

    @Override
    public Empleado guardar(Empleado empleado) {
        return repositorioEmpleado.save(empleado);
    }

    @Override
    public void eliminar(String correoElectronico) {

        Empleado empleado = repositorioEmpleado.findById(correoElectronico).orElse(null);

        if (empleado == null) {
            return;
        }

        if (repositorioEnvioDinero.existsByEmpleado(empleado)) {
            empleado.setActivo(false);
            repositorioEmpleado.save(empleado);
        } else {
            repositorioEmpleado.deleteById(correoElectronico);
        }
    }

    @Override
    public List<Empleado> buscarPorNombre(String nombre) {
        return repositorioEmpleado.findByNombreCompletoContainingIgnoreCase(nombre);
    }

    @Override
    public List<Empleado> buscarPorCorreo(String correo) {
        return repositorioEmpleado.findByCorreoElectronicoContainingIgnoreCase(correo);
    }
}
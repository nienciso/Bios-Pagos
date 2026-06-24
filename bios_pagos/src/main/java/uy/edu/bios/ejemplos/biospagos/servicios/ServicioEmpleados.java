package uy.edu.bios.ejemplos.biospagos.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import uy.edu.bios.ejemplos.biospagos.dominio.Empleado;
import uy.edu.bios.ejemplos.biospagos.excepciones.ExcepcionBiosPagos;
import uy.edu.bios.ejemplos.biospagos.excepciones.ExcepcionNoExiste;
import uy.edu.bios.ejemplos.biospagos.repositorios.IRepositorioEmpleados;
import uy.edu.bios.ejemplos.biospagos.repositorios.IRepositorioEnviosDinero;

@Service
public class ServicioEmpleados implements IServicioEmpleados {

    private final IRepositorioEmpleados repositorioEmpleado;
    private final IRepositorioEnviosDinero repositorioEnvioDinero;

    public ServicioEmpleados(IRepositorioEmpleados repositorioEmpleado,
            IRepositorioEnviosDinero repositorioEnvioDinero) {

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
public Empleado guardar(Empleado empleado) throws ExcepcionBiosPagos {

    Empleado empleadoAnterior = repositorioEmpleado.findById(empleado.getCorreoElectronico()).orElse(null);

    if (empleadoAnterior != null) {

        if (empleado.getClaveAcceso() == null || empleado.getClaveAcceso().isBlank()) {
            empleado.setClaveAcceso(empleadoAnterior.getClaveAcceso());
        }

    } else {
        empleado.setActivo(true);
    }

    return repositorioEmpleado.save(empleado);
}

    @Override
    public void eliminar(String correoElectronico) throws ExcepcionBiosPagos {

        Empleado empleado = repositorioEmpleado.findById(correoElectronico).orElse(null);

        if (empleado == null) {
            throw new ExcepcionNoExiste("No existe el empleado indicado.");
        }

        if (repositorioEnvioDinero.existsByEmpleado(empleado)) {
            empleado.setActivo(false);
            repositorioEmpleado.save(empleado);
        } else {
            repositorioEmpleado.deleteById(correoElectronico);
        }
    }

    @Override
    public List<Empleado> buscarPorNombre(String nombreCompleto) {
        return repositorioEmpleado.findByNombreCompletoContainingIgnoreCase(nombreCompleto);
    }

    @Override
    public List<Empleado> buscarPorCorreo(String correoElectronico) {
        return repositorioEmpleado.findByCorreoElectronicoContainingIgnoreCase(correoElectronico);
    }
}
package uy.edu.bios.ejemplos.biospagos.servicios;

import java.util.List;
import java.util.Optional;

import uy.edu.bios.ejemplos.biospagos.dominio.Empleado;

public interface IServicioEmpleados {

    List<Empleado> listar();

    Optional<Empleado> buscar(String correoElectronico);

    Empleado guardar(Empleado empleado);

    void eliminar(String correoElectronico);

    List<Empleado> buscarPorNombre(String nombre);

    List<Empleado> buscarPorCorreo(String correo);

    
}
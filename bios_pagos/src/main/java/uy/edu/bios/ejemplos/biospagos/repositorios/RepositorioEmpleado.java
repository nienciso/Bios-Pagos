package uy.edu.bios.ejemplos.biospagos.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import uy.edu.bios.ejemplos.biospagos.dominio.Empleado;

public interface RepositorioEmpleado extends JpaRepository<Empleado, String> {

    List<Empleado> findByNombreCompletoContainingIgnoreCase(String nombreCompleto);

    List<Empleado> findByCorreoElectronicoContainingIgnoreCase(String correoElectronico);
}
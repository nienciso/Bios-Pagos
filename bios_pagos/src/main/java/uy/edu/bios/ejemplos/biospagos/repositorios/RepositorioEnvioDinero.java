package uy.edu.bios.ejemplos.biospagos.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import uy.edu.bios.ejemplos.biospagos.dominio.Cliente;
import uy.edu.bios.ejemplos.biospagos.dominio.Empleado;
import uy.edu.bios.ejemplos.biospagos.dominio.EnvioDinero;

public interface RepositorioEnvioDinero extends JpaRepository<EnvioDinero, Long> {

    List<EnvioDinero> findByCliente(Cliente cliente);

    List<EnvioDinero> findByClienteAndFechaHoraPagoIsNull(Cliente cliente);

    List<EnvioDinero> findByClienteAndFechaHoraPagoIsNotNull(Cliente cliente);

    boolean existsByEmpleado(Empleado empleado);    
}
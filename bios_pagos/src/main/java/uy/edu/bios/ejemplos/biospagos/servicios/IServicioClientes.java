package uy.edu.bios.ejemplos.biospagos.servicios;

import java.util.List;
import java.util.Optional;

import uy.edu.bios.ejemplos.biospagos.dominio.Cliente;

public interface IServicioClientes {

    List<Cliente> listar();

    Optional<Cliente> buscar(String correoElectronico);

    Cliente guardar(Cliente cliente);

    void eliminar(String correoElectronico);
}
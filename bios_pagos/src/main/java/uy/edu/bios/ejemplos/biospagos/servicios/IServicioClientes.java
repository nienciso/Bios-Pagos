package uy.edu.bios.ejemplos.biospagos.servicios;

import java.util.List;
import java.util.Optional;

import uy.edu.bios.ejemplos.biospagos.dominio.Cliente;
import uy.edu.bios.ejemplos.biospagos.excepciones.ExcepcionBiosPagos;

public interface IServicioClientes {

    List<Cliente> listar();

    Optional<Cliente> buscar(String correoElectronico);

    Cliente guardar(Cliente cliente) throws ExcepcionBiosPagos;

    void eliminar(String correoElectronico) throws ExcepcionBiosPagos;
}
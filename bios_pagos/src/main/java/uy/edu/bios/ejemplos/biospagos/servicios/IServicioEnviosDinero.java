package uy.edu.bios.ejemplos.biospagos.servicios;

import java.util.List;
import java.util.Optional;

import uy.edu.bios.ejemplos.biospagos.dominio.Cliente;
import uy.edu.bios.ejemplos.biospagos.dominio.EnvioDinero;

public interface IServicioEnviosDinero {

    List<EnvioDinero> listar();

    Optional<EnvioDinero> buscar(Long id);

    EnvioDinero guardar(EnvioDinero envio);

    void eliminar(Long id);

    List<EnvioDinero> listarPorCliente(Cliente cliente);

List<EnvioDinero> listarPorClientePendientes(Cliente cliente);

List<EnvioDinero> listarPorClienteCobrados(Cliente cliente);
}
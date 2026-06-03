package uy.edu.bios.ejemplos.biospagos.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import uy.edu.bios.ejemplos.biospagos.dominio.Cliente;
import uy.edu.bios.ejemplos.biospagos.repositorios.RepositorioCliente;

@Service
public class ServicioClientes implements IServicioClientes {

    private final RepositorioCliente repositorioCliente;

    public ServicioClientes(RepositorioCliente repositorioCliente) {
        this.repositorioCliente = repositorioCliente;
    }

    @Override
    public List<Cliente> listar() {
        return repositorioCliente.findAll();
    }

    @Override
    public Optional<Cliente> buscar(String correoElectronico) {
        return repositorioCliente.findById(correoElectronico);
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        return repositorioCliente.save(cliente);
    }

    @Override
    public void eliminar(String correoElectronico) {
        repositorioCliente.deleteById(correoElectronico);
    }
}
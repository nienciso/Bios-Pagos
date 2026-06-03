package uy.edu.bios.ejemplos.biospagos.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import uy.edu.bios.ejemplos.biospagos.dominio.Cliente;
import uy.edu.bios.ejemplos.biospagos.dominio.EnvioDinero;
import uy.edu.bios.ejemplos.biospagos.repositorios.RepositorioEnvioDinero;



@Service
public class ServicioEnviosDinero implements IServicioEnviosDinero {

    private final RepositorioEnvioDinero repositorioEnvioDinero;

    public ServicioEnviosDinero(RepositorioEnvioDinero repositorioEnvioDinero) {
        this.repositorioEnvioDinero = repositorioEnvioDinero;
    }

    @Override
    public List<EnvioDinero> listar() {
        return repositorioEnvioDinero.findAll();
    }

    @Override
    public Optional<EnvioDinero> buscar(Long id) {
        return repositorioEnvioDinero.findById(id);
    }

    @Override
    public EnvioDinero guardar(EnvioDinero envio) {
        return repositorioEnvioDinero.save(envio);
    }

    @Override
    public void eliminar(Long id) {
        repositorioEnvioDinero.deleteById(id);
    }

    @Override
    public List<EnvioDinero> listarPorCliente(Cliente cliente) {
        return repositorioEnvioDinero.findByCliente(cliente);
    }

    @Override
    public List<EnvioDinero> listarPorClientePendientes(Cliente cliente) {
        return repositorioEnvioDinero.findByClienteAndFechaHoraPagoIsNull(cliente);
    }

    @Override
    public List<EnvioDinero> listarPorClienteCobrados(Cliente cliente) {
        return repositorioEnvioDinero.findByClienteAndFechaHoraPagoIsNotNull(cliente);
    }
}
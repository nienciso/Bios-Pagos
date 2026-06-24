package uy.edu.bios.ejemplos.biospagos.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import uy.edu.bios.ejemplos.biospagos.dominio.Cliente;
import uy.edu.bios.ejemplos.biospagos.excepciones.ExcepcionBiosPagos;
import uy.edu.bios.ejemplos.biospagos.excepciones.ExcepcionNoExiste;
import uy.edu.bios.ejemplos.biospagos.repositorios.IRepositorioClientes;
import uy.edu.bios.ejemplos.biospagos.repositorios.IRepositorioEnviosDinero;

@Service
public class ServicioClientes implements IServicioClientes {

    private final IRepositorioClientes repositorioCliente;
    private final IRepositorioEnviosDinero repositorioEnvioDinero;

    public ServicioClientes(IRepositorioClientes repositorioCliente,
            IRepositorioEnviosDinero repositorioEnvioDinero) {

        this.repositorioCliente = repositorioCliente;
        this.repositorioEnvioDinero = repositorioEnvioDinero;
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
    public Cliente guardar(Cliente cliente) throws ExcepcionBiosPagos {

        Cliente clienteAnterior = repositorioCliente.findById(cliente.getCorreoElectronico()).orElse(null);

        if (clienteAnterior != null) {
            if (cliente.getClaveAcceso() == null || cliente.getClaveAcceso().isBlank()) {
                cliente.setClaveAcceso(clienteAnterior.getClaveAcceso());
            }
        }

        return repositorioCliente.save(cliente);
    }

    @Override
    public void eliminar(String correoElectronico) throws ExcepcionBiosPagos {

        Cliente cliente = repositorioCliente.findById(correoElectronico).orElse(null);

        if (cliente == null) {
            throw new ExcepcionNoExiste("No existe el cliente indicado.");
        }

        if (repositorioEnvioDinero.existsByCliente(cliente)) {
            throw new ExcepcionBiosPagos("No es posible eliminar el cliente porque tiene envíos asociados.");
        }

        repositorioCliente.deleteById(correoElectronico);
    }
}
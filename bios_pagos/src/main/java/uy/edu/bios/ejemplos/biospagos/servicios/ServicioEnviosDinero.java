package uy.edu.bios.ejemplos.biospagos.servicios;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import uy.edu.bios.ejemplos.biospagos.dominio.Cliente;
import uy.edu.bios.ejemplos.biospagos.dominio.EnvioDinero;
import uy.edu.bios.ejemplos.biospagos.excepciones.ExcepcionBiosPagos;
import uy.edu.bios.ejemplos.biospagos.excepciones.ExcepcionNoExiste;
import uy.edu.bios.ejemplos.biospagos.repositorios.IRepositorioEnviosDinero;

@Service
public class ServicioEnviosDinero implements IServicioEnviosDinero {

    private final IRepositorioEnviosDinero repositorioEnvioDinero;

    public ServicioEnviosDinero(IRepositorioEnviosDinero repositorioEnvioDinero) {
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
    public EnvioDinero guardar(EnvioDinero envio) throws ExcepcionBiosPagos {

        if (envio.getId() == null) {
            envio.setFechaHoraRegistro(LocalDateTime.now());
        } else {
            EnvioDinero envioAnterior = repositorioEnvioDinero.findById(envio.getId()).orElse(null);

            if (envioAnterior == null) {
                throw new ExcepcionNoExiste("No existe el envío indicado.");
            }

            envio.setFechaHoraRegistro(envioAnterior.getFechaHoraRegistro());
        }

        return repositorioEnvioDinero.save(envio);
    }

    @Override
    public void eliminar(Long id) throws ExcepcionBiosPagos {

        if (!repositorioEnvioDinero.existsById(id)) {
            throw new ExcepcionNoExiste("No existe el envío indicado.");
        }

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
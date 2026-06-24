package uy.edu.bios.ejemplos.biospagos.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import uy.edu.bios.ejemplos.biospagos.dominio.Sucursal;
import uy.edu.bios.ejemplos.biospagos.excepciones.ExcepcionBiosPagos;
import uy.edu.bios.ejemplos.biospagos.excepciones.ExcepcionNoExiste;
import uy.edu.bios.ejemplos.biospagos.repositorios.IRepositorioEnviosDinero;
import uy.edu.bios.ejemplos.biospagos.repositorios.IRepositorioSucursales;

@Service
public class ServicioSucursales implements IServicioSucursales {

    private final IRepositorioSucursales repositorioSucursal;
    private final IRepositorioEnviosDinero repositorioEnvioDinero;

    public ServicioSucursales(IRepositorioSucursales repositorioSucursal,
            IRepositorioEnviosDinero repositorioEnvioDinero) {

        this.repositorioSucursal = repositorioSucursal;
        this.repositorioEnvioDinero = repositorioEnvioDinero;
    }

    @Override
    public List<Sucursal> listar() {
        return repositorioSucursal.findAll();
    }

    @Override
    public Optional<Sucursal> buscar(Integer numero) {
        return repositorioSucursal.findById(numero);
    }

    @Override
    public Sucursal guardar(Sucursal sucursal) throws ExcepcionBiosPagos {

        Sucursal sucursalAnterior = repositorioSucursal.findById(sucursal.getNumero()).orElse(null);

        if (sucursalAnterior != null) {
            if (sucursal.getFotografiaFachada() == null || sucursal.getFotografiaFachada().isBlank()) {
                sucursal.setFotografiaFachada(sucursalAnterior.getFotografiaFachada());
            }
        }

        return repositorioSucursal.save(sucursal);
    }

    @Override
    public void eliminar(Integer numero) throws ExcepcionBiosPagos {

        Sucursal sucursal = repositorioSucursal.findById(numero).orElse(null);

        if (sucursal == null) {
            throw new ExcepcionNoExiste("No existe la sucursal indicada.");
        }

        if (repositorioEnvioDinero.existsBySucursalOrigen(sucursal)
                || repositorioEnvioDinero.existsBySucursalCobro(sucursal)) {

            throw new ExcepcionBiosPagos("No es posible eliminar la sucursal porque tiene envíos asociados.");
        }

        repositorioSucursal.deleteById(numero);
    }
}
package uy.edu.bios.ejemplos.biospagos.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import uy.edu.bios.ejemplos.biospagos.dominio.Sucursal;
import uy.edu.bios.ejemplos.biospagos.repositorios.RepositorioSucursal;

@Service
public class ServicioSucursales implements IServicioSucursales {

    private final RepositorioSucursal repositorioSucursal;

    public ServicioSucursales(RepositorioSucursal repositorioSucursal) {
        this.repositorioSucursal = repositorioSucursal;
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
    public Sucursal guardar(Sucursal sucursal) {
        return repositorioSucursal.save(sucursal);
    }

    @Override
    public void eliminar(Integer numero) {
        repositorioSucursal.deleteById(numero);
    }
}
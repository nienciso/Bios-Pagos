package uy.edu.bios.ejemplos.biospagos.servicios;

import java.util.List;
import java.util.Optional;

import uy.edu.bios.ejemplos.biospagos.dominio.Sucursal;

public interface IServicioSucursales {

    List<Sucursal> listar();

    Optional<Sucursal> buscar(Integer numero);

    Sucursal guardar(Sucursal sucursal);

    void eliminar(Integer numero);
}
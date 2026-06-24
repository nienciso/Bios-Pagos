package uy.edu.bios.ejemplos.biospagos.servicios;

import java.util.List;
import java.util.Optional;

import uy.edu.bios.ejemplos.biospagos.dominio.Sucursal;
import uy.edu.bios.ejemplos.biospagos.excepciones.ExcepcionBiosPagos;

public interface IServicioSucursales {

    List<Sucursal> listar();

    Optional<Sucursal> buscar(Integer numero);

    Sucursal guardar(Sucursal sucursal) throws ExcepcionBiosPagos;

    void eliminar(Integer numero) throws ExcepcionBiosPagos;
}
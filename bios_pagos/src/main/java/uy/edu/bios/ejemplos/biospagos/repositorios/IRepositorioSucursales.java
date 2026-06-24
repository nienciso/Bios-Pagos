package uy.edu.bios.ejemplos.biospagos.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import uy.edu.bios.ejemplos.biospagos.dominio.Sucursal;

public interface IRepositorioSucursales extends JpaRepository<Sucursal, Integer> {

}
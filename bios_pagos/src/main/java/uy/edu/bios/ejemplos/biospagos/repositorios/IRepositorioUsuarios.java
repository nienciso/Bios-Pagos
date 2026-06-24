package uy.edu.bios.ejemplos.biospagos.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import uy.edu.bios.ejemplos.biospagos.dominio.Usuario;

public interface IRepositorioUsuarios extends JpaRepository<Usuario, String> {

}
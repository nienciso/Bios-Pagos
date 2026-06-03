package uy.edu.bios.ejemplos.biospagos.servicios;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import uy.edu.bios.ejemplos.biospagos.dominio.Cliente;
import uy.edu.bios.ejemplos.biospagos.dominio.Empleado;
import uy.edu.bios.ejemplos.biospagos.dominio.Usuario;
import uy.edu.bios.ejemplos.biospagos.repositorios.RepositorioUsuario;

@Service
public class ServicioDetallesUsuario implements UserDetailsService {

    private final RepositorioUsuario repositorioUsuario;

    public ServicioDetallesUsuario(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public UserDetails loadUserByUsername(String correoElectronico) throws UsernameNotFoundException {

        Usuario usuario = repositorioUsuario.findById(correoElectronico)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        String rol = "";

        if (usuario instanceof Empleado) {
            rol = "EMPLEADO";
        } else if (usuario instanceof Cliente) {
            rol = "CLIENTE";
        }

        return User.withUsername(usuario.getCorreoElectronico())
                .password("{noop}" + usuario.getClaveAcceso())
                .roles(rol)
                .build();
    }
}
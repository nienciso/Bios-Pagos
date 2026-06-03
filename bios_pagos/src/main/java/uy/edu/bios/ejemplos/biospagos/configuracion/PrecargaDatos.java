package uy.edu.bios.ejemplos.biospagos.configuracion;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import uy.edu.bios.ejemplos.biospagos.dominio.Cliente;
import uy.edu.bios.ejemplos.biospagos.dominio.Empleado;
import uy.edu.bios.ejemplos.biospagos.dominio.EnvioDinero;
import uy.edu.bios.ejemplos.biospagos.dominio.Sucursal;
import uy.edu.bios.ejemplos.biospagos.repositorios.RepositorioCliente;
import uy.edu.bios.ejemplos.biospagos.repositorios.RepositorioEmpleado;
import uy.edu.bios.ejemplos.biospagos.repositorios.RepositorioEnvioDinero;
import uy.edu.bios.ejemplos.biospagos.repositorios.RepositorioSucursal;

@Configuration
public class PrecargaDatos {

    @Bean
    public CommandLineRunner cargarDatos(
            RepositorioCliente repositorioCliente,
            RepositorioEmpleado repositorioEmpleado,
            RepositorioSucursal repositorioSucursal,
            RepositorioEnvioDinero repositorioEnvioDinero) {

        return args -> {

            if (repositorioEmpleado.count() == 0) {
                Empleado admin = new Empleado(
                        "admin@biospagos.com.uy",
                        "bios2026",
                        "Administrador BIOS Pagos",
                        LocalDate.of(2026, 1, 1)
                );

                repositorioEmpleado.save(admin);
            }

            if (repositorioCliente.count() == 0) {
                Cliente cliente1 = new Cliente("cliente1@biospagos.com", "cliente123", "Juan Pérez", "12345678");
                Cliente cliente2 = new Cliente("cliente2@biospagos.com", "cliente123", "María Rodríguez", "23456789");

                repositorioCliente.save(cliente1);
                repositorioCliente.save(cliente2);
            }

            if (repositorioSucursal.count() == 0) {
                Sucursal sucursal1 = new Sucursal(1, "Sucursal Centro", "18 de Julio 1234", "29000001", "sin-foto.jpg");
                Sucursal sucursal2 = new Sucursal(2, "Sucursal Cordón", "Mercedes 1500", "29000002", "sin-foto.jpg");
                Sucursal sucursal3 = new Sucursal(3, "Sucursal Pocitos", "Bulevar España 2200", "29000003", "sin-foto.jpg");

                repositorioSucursal.save(sucursal1);
                repositorioSucursal.save(sucursal2);
                repositorioSucursal.save(sucursal3);
            }

            if (repositorioEnvioDinero.count() == 0) {

                Cliente cliente1 = repositorioCliente.findById("cliente1@biospagos.com").orElse(null);
                Cliente cliente2 = repositorioCliente.findById("cliente2@biospagos.com").orElse(null);
                Empleado admin = repositorioEmpleado.findById("admin@biospagos.com.uy").orElse(null);
                Sucursal sucursal1 = repositorioSucursal.findById(1).orElse(null);
                Sucursal sucursal2 = repositorioSucursal.findById(2).orElse(null);

                if (cliente1 != null && cliente2 != null && admin != null && sucursal1 != null && sucursal2 != null) {

                    EnvioDinero envio1 = new EnvioDinero(
                            null,
                            LocalDateTime.now(),
                            new BigDecimal("1500.00"),
                            "34567890",
                            null,
                            cliente1,
                            admin,
                            sucursal1,
                            sucursal2
                    );

                    EnvioDinero envio2 = new EnvioDinero(
                            null,
                            LocalDateTime.now(),
                            new BigDecimal("2300.00"),
                            "45678901",
                            null,
                            cliente2,
                            admin,
                            sucursal2,
                            sucursal1
                    );

                    repositorioEnvioDinero.save(envio1);
                    repositorioEnvioDinero.save(envio2);
                }
            }
        };
    }
}
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
import uy.edu.bios.ejemplos.biospagos.repositorios.IRepositorioClientes;
import uy.edu.bios.ejemplos.biospagos.repositorios.IRepositorioEmpleados;
import uy.edu.bios.ejemplos.biospagos.repositorios.IRepositorioEnviosDinero;
import uy.edu.bios.ejemplos.biospagos.repositorios.IRepositorioSucursales;
import uy.edu.bios.ejemplos.biospagos.repositorios.IRepositorioUsuarios;

@Configuration
public class PrecargaDatos {

    @Bean
    public CommandLineRunner cargarDatos(
            IRepositorioUsuarios repositorioUsuarios,
            IRepositorioEmpleados repositorioEmpleados,
            IRepositorioClientes repositorioClientes,
            IRepositorioSucursales repositorioSucursales,
            IRepositorioEnviosDinero repositorioEnviosDinero) {

        return args -> {

            Empleado admin = repositorioEmpleados.findById("admin@biospagos.com.uy")
                    .orElse(new Empleado(
                            "admin@biospagos.com.uy",
                            "bios2026",
                            "Administrador BIOS Pagos",
                            LocalDate.of(2026, 1, 1)));

            repositorioEmpleados.save(admin);

            if (repositorioClientes.count() == 0) {
                repositorioClientes.save(new Cliente(
                        "cliente1@biospagos.com",
                        "cliente123",
                        "Juan Pérez",
                        "12345678"));

                repositorioClientes.save(new Cliente(
                        "cliente2@biospagos.com",
                        "cliente123",
                        "María Rodríguez",
                        "23456789"));
            }

            if (repositorioEmpleados.count() < 3) {
                if (!repositorioEmpleados.existsById("empleado1@biospagos.com")) {
                    repositorioEmpleados.save(new Empleado(
                            "empleado1@biospagos.com",
                            "empleado123",
                            "Ana Gómez",
                            LocalDate.of(2024, 3, 15)));
                }

                if (!repositorioEmpleados.existsById("empleado2@biospagos.com")) {
                    repositorioEmpleados.save(new Empleado(
                            "empleado2@biospagos.com",
                            "empleado123",
                            "Carlos Silva",
                            LocalDate.of(2023, 8, 10)));
                }
            }

            if (repositorioSucursales.count() == 0) {
                repositorioSucursales.save(new Sucursal(
                        1,
                        "Sucursal Centro",
                        "18 de Julio 1234",
                        "29000001",
                        "sin-foto.jpg"));

                repositorioSucursales.save(new Sucursal(
                        2,
                        "Sucursal Cordón",
                        "Mercedes 1500",
                        "29000002",
                        "sin-foto.jpg"));

                repositorioSucursales.save(new Sucursal(
                        3,
                        "Sucursal Pocitos",
                        "Bulevar España 2200",
                        "29000003",
                        "sin-foto.jpg"));
            }

            if (repositorioEnviosDinero.count() == 0) {

                Cliente cliente1 = repositorioClientes.findById("cliente1@biospagos.com").orElse(null);
                Cliente cliente2 = repositorioClientes.findById("cliente2@biospagos.com").orElse(null);
                Empleado empleadoAdmin = repositorioEmpleados.findById("admin@biospagos.com.uy").orElse(null);
                Empleado empleado1 = repositorioEmpleados.findById("empleado1@biospagos.com").orElse(null);
                Sucursal sucursal1 = repositorioSucursales.findById(1).orElse(null);
                Sucursal sucursal2 = repositorioSucursales.findById(2).orElse(null);
                Sucursal sucursal3 = repositorioSucursales.findById(3).orElse(null);

                if (cliente1 != null && cliente2 != null && empleadoAdmin != null
                        && empleado1 != null && sucursal1 != null && sucursal2 != null && sucursal3 != null) {

                    EnvioDinero envio1 = new EnvioDinero(
                            null,
                            LocalDateTime.now().minusDays(3),
                            new BigDecimal("1500.00"),
                            "34567890",
                            null,
                            cliente1,
                            empleadoAdmin,
                            sucursal1,
                            sucursal2);

                    EnvioDinero envio2 = new EnvioDinero(
                            null,
                            LocalDateTime.now().minusDays(2),
                            new BigDecimal("2300.00"),
                            "45678901",
                            LocalDateTime.now().minusDays(1),
                            cliente1,
                            empleado1,
                            sucursal2,
                            sucursal3);

                    EnvioDinero envio3 = new EnvioDinero(
                            null,
                            LocalDateTime.now().minusDays(1),
                            new BigDecimal("850.00"),
                            "56789012",
                            null,
                            cliente2,
                            empleadoAdmin,
                            sucursal3,
                            sucursal1);

                    repositorioEnviosDinero.save(envio1);
                    repositorioEnviosDinero.save(envio2);
                    repositorioEnviosDinero.save(envio3);
                }
            }
        };
    }
}
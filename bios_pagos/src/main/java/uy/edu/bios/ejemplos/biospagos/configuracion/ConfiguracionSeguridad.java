package uy.edu.bios.ejemplos.biospagos.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ConfiguracionSeguridad {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/registro", "/sucursales", "/css/**", "/imagenes/**").permitAll()

                .requestMatchers("/empleados/**").hasRole("EMPLEADO")
                .requestMatchers("/clientes/**").hasRole("EMPLEADO")
                .requestMatchers("/envios/**").hasRole("EMPLEADO")
                .requestMatchers("/sucursales/**").hasRole("EMPLEADO")

                .requestMatchers("/mis-envios/**").hasRole("CLIENTE")

                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/")
                .permitAll()
            );

        return http.build();
    }
}
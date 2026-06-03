package uy.edu.bios.ejemplos.biospagos.dominio;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
public class Empleado extends Usuario {

    @NotNull(message = "{empleado.fechaIngreso.requerida}")
    @PastOrPresent(message = "{empleado.fechaIngreso.pasado}")
    @Column(nullable = false)
    private LocalDate fechaIngreso;
    private Boolean activo = true;


    public Empleado() {
    }

    public Empleado(String correoElectronico, String claveAcceso, String nombreCompleto, LocalDate fechaIngreso) {
        super(correoElectronico, claveAcceso, nombreCompleto);
        this.fechaIngreso = fechaIngreso;
        this.activo = true;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

        public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    
    @Override
    public String toString() {
        return super.toString() +
                " Empleado [fechaIngreso=" + fechaIngreso + "]";
    }
}
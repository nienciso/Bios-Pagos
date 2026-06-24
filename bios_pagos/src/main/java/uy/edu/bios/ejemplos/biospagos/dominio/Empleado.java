package uy.edu.bios.ejemplos.biospagos.dominio;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "empleados")
public class Empleado extends Usuario {

    @NotNull
    @Column(nullable = false)
    private LocalDate fechaIngreso;

    @Column(nullable = false)
    private boolean activo = true;

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Empleado() {
        this(null, null, null, null);
    }

    public Empleado(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
        this.activo = true;
    }

    public Empleado(String correoElectronico, String claveAcceso, String nombreCompleto, LocalDate fechaIngreso) {
        super(correoElectronico, claveAcceso, nombreCompleto);
        this.fechaIngreso = fechaIngreso;
        this.activo = true;
    }

    @Override
    public String toString() {
        return super.toString() +
                " Empleado [fechaIngreso=" + fechaIngreso +
                ", activo=" + activo + "]";
    }
}


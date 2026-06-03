package uy.edu.bios.ejemplos.biospagos.dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Cliente extends Usuario {

    @NotBlank(message = "{cliente.cedula.requerida}")
    @Size(min = 8, max = 8, message = "{cliente.cedula.longitud}")
    @Column(nullable = false, unique = true, length = 8)
    private String cedula;

    public Cliente() {
    }

    public Cliente(String correoElectronico, String claveAcceso, String nombreCompleto, String cedula) {
        super(correoElectronico, claveAcceso, nombreCompleto);
        this.cedula = cedula;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    @Override
    public String toString() {
        return super.toString() +
                " Cliente [cedula=" + cedula + "]";
    }
}
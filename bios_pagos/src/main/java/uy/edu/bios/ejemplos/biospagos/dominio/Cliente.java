package uy.edu.bios.ejemplos.biospagos.dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "clientes")
public class Cliente extends Usuario {

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false, length = 20)
    private String cedula;

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Cliente() {
        this(null, null, null, null);
    }

    public Cliente(String correoElectronico, String claveAcceso, String nombreCompleto, String cedula) {
        super(correoElectronico, claveAcceso, nombreCompleto);
        this.cedula = cedula;
    }

    @Override
    public String toString() {
        return super.toString() +
                " Cliente [cedula=" + cedula + "]";
    }

}

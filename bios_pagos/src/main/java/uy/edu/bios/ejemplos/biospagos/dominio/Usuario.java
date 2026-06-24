package uy.edu.bios.ejemplos.biospagos.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {

    @Id
    @Email
    @NotBlank
    @Size(max = 100)
    private String correoElectronico;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false)
    private String claveAcceso;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false)
    private String nombreCompleto;

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getClaveAcceso() {
        return claveAcceso;
    }

    public void setClaveAcceso(String claveAcceso) {
        this.claveAcceso = claveAcceso;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Usuario() {
        this(null, null, null);
    }

    public Usuario(String correoElectronico, String claveAcceso, String nombreCompleto) {
        this.correoElectronico = correoElectronico;
        this.claveAcceso = claveAcceso;
        this.nombreCompleto = nombreCompleto;
    }

    @Override
    public String toString() {
        return "Usuario [correoElectronico=" + correoElectronico
                + ", nombreCompleto=" + nombreCompleto + "]";
    }

}

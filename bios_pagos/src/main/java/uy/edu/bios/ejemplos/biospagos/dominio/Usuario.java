package uy.edu.bios.ejemplos.biospagos.dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {

    @Id
    @Email(message = "{usuario.correo.formato}")
    @NotBlank(message = "{usuario.correo.requerido}")
    @Column(nullable = false, unique = true)
    private String correoElectronico;

    @NotBlank(message = "{usuario.clave.requerida}")
    @Size(min = 6, max = 20, message = "{usuario.clave.longitud}")
    @Column(nullable = false)
    private String claveAcceso;

    @NotBlank(message = "{usuario.nombre.requerido}")
    @Column(nullable = false)
    private String nombreCompleto;

    public Usuario() {
    }

    public Usuario(String correoElectronico, String claveAcceso, String nombreCompleto) {
        this.correoElectronico = correoElectronico;
        this.claveAcceso = claveAcceso;
        this.nombreCompleto = nombreCompleto;
    }

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

    @Override
    public String toString() {
        return "Usuario [correoElectronico=" + correoElectronico
                + ", nombreCompleto=" + nombreCompleto + "]";
    }
}
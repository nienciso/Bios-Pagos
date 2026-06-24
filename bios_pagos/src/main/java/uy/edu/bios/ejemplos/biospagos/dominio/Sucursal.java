package uy.edu.bios.ejemplos.biospagos.dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "sucursales")
public class Sucursal {

    @Id
    @NotNull
    @Min(1)
    private Integer numero;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank
    @Size(max = 200)
    @Column(nullable = false, length = 200)
    private String direccion;

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false, length = 20)
    private String telefono;

    @Size(max = 255)
    private String fotografiaFachada;

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFotografiaFachada() {
        return fotografiaFachada;
    }

    public void setFotografiaFachada(String fotografiaFachada) {
        this.fotografiaFachada = fotografiaFachada;
    }

    public Sucursal() {
        this(null, null, null, null, null);
    }

    public Sucursal(Integer numero, String nombre, String direccion, String telefono, String fotografiaFachada) {
        this.numero = numero;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fotografiaFachada = fotografiaFachada;
    }

    @Override
    public String toString() {
        return numero + " - " + nombre;
    }

}

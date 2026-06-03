package uy.edu.bios.ejemplos.biospagos.dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Sucursal {

    @Id
    @NotNull(message = "{sucursal.numero.requerido}")
    @Min(value = 1, message = "{sucursal.numero.minimo}")
    private Integer numero;

    @NotBlank(message = "{sucursal.nombre.requerido}")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "{sucursal.direccion.requerida}")
    @Column(nullable = false)
    private String direccion;

    @NotBlank(message = "{sucursal.telefono.requerido}")
    @Column(nullable = false)
    private String telefono;

    @Column(nullable = true)
    private String fotografiaFachada;

    public Sucursal() {
    }

    public Sucursal(Integer numero, String nombre, String direccion, String telefono, String fotografiaFachada) {
        this.numero = numero;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fotografiaFachada = fotografiaFachada;
    }

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

    @Override
    public String toString() {
        return numero + " - " + nombre;
    }
}
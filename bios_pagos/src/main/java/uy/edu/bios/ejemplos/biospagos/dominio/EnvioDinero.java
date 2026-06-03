package uy.edu.bios.ejemplos.biospagos.dominio;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class EnvioDinero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{envio.fechaRegistro.requerida}")
    @Column(nullable = false)
    private LocalDateTime fechaHoraRegistro;

    @NotNull(message = "{envio.monto.requerido}")
    @DecimalMin(value = "0.01", message = "{envio.monto.minimo}")
    @Column(nullable = false)
    private BigDecimal monto;

    @NotBlank(message = "{envio.cedulaDestinatario.requerida}")
    @Column(nullable = false)
    private String cedulaDestinatario;

    @Column(nullable = true)
    private LocalDateTime fechaHoraPago;

    @ManyToOne
    @JoinColumn(name = "correo_cliente", nullable = false)
    @NotNull(message = "{envio.cliente.requerido}")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "correo_empleado", nullable = false)
    @NotNull(message = "{envio.empleado.requerido}")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "numero_sucursal_registro", nullable = false)
    @NotNull(message = "{envio.sucursalRegistro.requerida}")
    private Sucursal sucursalRegistro;

    @ManyToOne
    @JoinColumn(name = "numero_sucursal_pago", nullable = false)
    @NotNull(message = "{envio.sucursalPago.requerida}")
    private Sucursal sucursalPago;

    public EnvioDinero() {
    }

    public EnvioDinero(Long id, LocalDateTime fechaHoraRegistro, BigDecimal monto,
            String cedulaDestinatario, LocalDateTime fechaHoraPago,
            Cliente cliente, Empleado empleado, Sucursal sucursalRegistro, Sucursal sucursalPago) {

        this.id = id;
        this.fechaHoraRegistro = fechaHoraRegistro;
        this.monto = monto;
        this.cedulaDestinatario = cedulaDestinatario;
        this.fechaHoraPago = fechaHoraPago;
        this.cliente = cliente;
        this.empleado = empleado;
        this.sucursalRegistro = sucursalRegistro;
        this.sucursalPago = sucursalPago;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getFechaHoraRegistro() {
        return fechaHoraRegistro;
    }

    public void setFechaHoraRegistro(LocalDateTime fechaHoraRegistro) {
        this.fechaHoraRegistro = fechaHoraRegistro;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getCedulaDestinatario() {
        return cedulaDestinatario;
    }

    public void setCedulaDestinatario(String cedulaDestinatario) {
        this.cedulaDestinatario = cedulaDestinatario;
    }

    public LocalDateTime getFechaHoraPago() {
        return fechaHoraPago;
    }

    public void setFechaHoraPago(LocalDateTime fechaHoraPago) {
        this.fechaHoraPago = fechaHoraPago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Sucursal getSucursalRegistro() {
        return sucursalRegistro;
    }

    public void setSucursalRegistro(Sucursal sucursalRegistro) {
        this.sucursalRegistro = sucursalRegistro;
    }

    public Sucursal getSucursalPago() {
        return sucursalPago;
    }

    public void setSucursalPago(Sucursal sucursalPago) {
        this.sucursalPago = sucursalPago;
    }

    public BigDecimal getImporteACobrar() {
        if (monto == null) {
            return BigDecimal.ZERO;
        }

        return monto.multiply(new BigDecimal("1.10"));
    }

    @Override
    public String toString() {
        return "EnvioDinero [id=" + id
                + ", fechaHoraRegistro=" + fechaHoraRegistro
                + ", monto=" + monto
                + ", cedulaDestinatario=" + cedulaDestinatario
                + ", fechaHoraPago=" + fechaHoraPago + "]";
    }
}
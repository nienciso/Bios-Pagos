package uy.edu.bios.ejemplos.biospagos.dominio;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "envios_dinero")
public class EnvioDinero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime fechaHoraRegistro;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal monto;

    @NotBlank
    private String cedulaDestinatario;

    private LocalDateTime fechaHoraPago;

    @NotNull
    @ManyToOne
    private Cliente cliente;

    @NotNull
    @ManyToOne
    private Empleado empleado;

    @NotNull
    @ManyToOne
    private Sucursal sucursalOrigen;

    @NotNull
    @ManyToOne
    private Sucursal sucursalCobro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getImporteCobrar() {
        if (monto == null) {
            return BigDecimal.ZERO;
        }

        return monto.multiply(new BigDecimal("1.10"));
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

    public Sucursal getSucursalOrigen() {
        return sucursalOrigen;
    }

    public void setSucursalOrigen(Sucursal sucursalOrigen) {
        this.sucursalOrigen = sucursalOrigen;
    }

    public Sucursal getSucursalCobro() {
        return sucursalCobro;
    }

    public void setSucursalCobro(Sucursal sucursalCobro) {
        this.sucursalCobro = sucursalCobro;
    }

    public EnvioDinero() {
        this(null, null, null, null, null, null, null, null, null);
    }

    public EnvioDinero(Long id, LocalDateTime fechaHoraRegistro, BigDecimal monto,
            String cedulaDestinatario, LocalDateTime fechaHoraPago,
            Cliente cliente, Empleado empleado,
            Sucursal sucursalOrigen, Sucursal sucursalCobro) {

        this.id = id;
        this.fechaHoraRegistro = fechaHoraRegistro;
        this.monto = monto;
        this.cedulaDestinatario = cedulaDestinatario;
        this.fechaHoraPago = fechaHoraPago;
        this.cliente = cliente;
        this.empleado = empleado;
        this.sucursalOrigen = sucursalOrigen;
        this.sucursalCobro = sucursalCobro;
    }

    @Override
    public String toString() {
        return "EnvioDinero [id=" + id +
                ", fechaHoraRegistro=" + fechaHoraRegistro +
                ", monto=" + monto +
                ", cedulaDestinatario=" + cedulaDestinatario +
                ", fechaHoraPago=" + fechaHoraPago + "]";
    }
}
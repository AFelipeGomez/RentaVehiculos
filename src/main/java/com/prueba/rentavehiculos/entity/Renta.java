package com.prueba.rentavehiculos.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Clase de entidad que representa una renta de vehículo en la aplicación.
 * Esta clase está marcada como una entidad JPA y se mapea a la tabla "renta" en la base de datos.
 */
@Entity
@Table(name = "renta")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Renta implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "valor")
    private double valor;
    @Column(name = "vehiculo", nullable = false, length = 25)
    private String vehiculo;

    @ManyToOne
    @JoinColumn(name = "id_mediopago", foreignKey = @ForeignKey(name = "FK_mediopago"))
    private MedioPago medioPago;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Column(name = "estado", nullable = false, length = 25)
    private String estado; // Abierto, Cerrado, En revisión


    @OneToOne
    @JoinColumn(name = "id_usuario", unique = true)

    private Usuario usuario;


    public Renta(Long id, double valor, String vehiculo, MedioPago medioPago, Date fechaInicio, Date fechaFin, String estado) {
        this.id = id;
        this.valor = valor;
        this.vehiculo = vehiculo;
        this.medioPago = medioPago;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
    }

    public Renta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public MedioPago getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(MedioPago medioPago) {
        this.medioPago = medioPago;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }



    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

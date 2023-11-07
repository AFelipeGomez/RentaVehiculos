package com.prueba.rentavehiculos.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

/**
 * Clase de entidad que representa un medio de pago en la aplicación.
 * Esta clase está marcada como una entidad JPA y se mapea a la tabla "medioPago" en la base de datos.
 */
@Entity
@Table(name = "medioPago")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class MedioPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;
    @Column(name = "numero_tarjeta", nullable = false, length = 50)
    private String numeroTarjeta;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    public MedioPago() {
    }

    public MedioPago(Long id, String tipo, String numeroTarjeta) {
        this.id = id;
        this.tipo = tipo;
        this.numeroTarjeta = numeroTarjeta;
    }

    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

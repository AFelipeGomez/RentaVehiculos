package com.prueba.rentavehiculos.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Clase de entidad que representa un usuario en la aplicación.
 * Esta clase está marcada como una entidad JPA y se mapea a la tabla "usuario" en la base de datos.
 */
@Entity
@Table(name = "usuario")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre_usuario", nullable = false, length = 50)
    private String nombreUsuario;
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;
    @Column(name = "clave", nullable = false, length = 250)
    private String clave;// Clave encriptada

    private LocalDateTime fechaRegistro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", orphanRemoval = true)
    private List<MedioPago> mediosPago;
    @OneToOne(mappedBy = "usuario")
    private Renta renta;

    public Long getId() {
        return id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getClave() {
        return clave;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public List<MedioPago> getMediosPago() {
        return mediosPago;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public void setMediosPago(List<MedioPago> mediosPago) {
        this.mediosPago = mediosPago;
    }
}

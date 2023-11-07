package com.prueba.rentavehiculos.service;

import com.prueba.rentavehiculos.entity.Usuario;
import com.prueba.rentavehiculos.exception.ConflictException;
import com.prueba.rentavehiculos.exception.ModelNotFoundException;

import java.util.List;

/**
 * Interfaz que define el comportamiento para los servicios de usuario,
 * las clases que implementan esta interfaz deben proporcionar una implementacion concreta.
 */
public interface IUsuarioService {


    public List<Usuario> getAll();

    public Usuario getById(Long id) throws ModelNotFoundException;

    public void save(Usuario usuario) throws ConflictException;

    public void update(Usuario usuario) throws ModelNotFoundException;

    public void delete(String nombreUsuario) throws ModelNotFoundException;

    public Usuario getByNombreUsuario(String nombreUsuario) throws ModelNotFoundException;
}

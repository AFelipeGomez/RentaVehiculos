package com.prueba.rentavehiculos.service;

import com.prueba.rentavehiculos.entity.Renta;
import com.prueba.rentavehiculos.exception.ConflictException;
import com.prueba.rentavehiculos.exception.ModelNotFoundException;

import java.util.List;

/**
 * Interfaz que define el comportamiento para los servicios de renta de vehiculos,
 * las calses que implementan esta interfaz deben proporcionar una implementacion concreta.
 */
public interface IRentaService {

    public void saveRenta(Renta renta)throws ConflictException, ModelNotFoundException;

    public List<Renta> all();

    public Renta getByUsuario(Long idUsuarioRenta);



    public void updateEstado(String estado, Long idRenta) throws ModelNotFoundException;
}

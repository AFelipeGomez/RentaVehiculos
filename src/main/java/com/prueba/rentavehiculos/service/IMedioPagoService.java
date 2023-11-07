package com.prueba.rentavehiculos.service;

import com.prueba.rentavehiculos.entity.MedioPago;
import com.prueba.rentavehiculos.exception.DataIntegrityViolationException;
import com.prueba.rentavehiculos.exception.ModelNotFoundException;

import java.util.List;

/**
 * Interfaz que define el comportamiento para los servicios de Medio de pago,
 * las calses que implementan esta interfaz deben proporcionar una implementacion concreta.
 */
public interface IMedioPagoService {

    public void save(MedioPago medioPago)throws ModelNotFoundException, DataIntegrityViolationException;

    public void update(MedioPago medioPago);

    public void delete(Long id)throws ModelNotFoundException;

    public List<MedioPago> getByUsuario(String nombreUsuario);
}

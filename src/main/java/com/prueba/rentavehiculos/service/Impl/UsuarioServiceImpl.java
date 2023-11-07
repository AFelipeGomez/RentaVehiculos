package com.prueba.rentavehiculos.service.Impl;

import com.prueba.rentavehiculos.entity.MedioPago;
import com.prueba.rentavehiculos.entity.Usuario;
import com.prueba.rentavehiculos.exception.ConflictException;
import com.prueba.rentavehiculos.exception.ModelNotFoundException;
import com.prueba.rentavehiculos.repository.IUsuarioRepo;
import com.prueba.rentavehiculos.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Implementación del servicio para operaciones relacionadas con usuarios en el sistema.
 */
@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepo repository;

    /**
     * Recupera todos los usuarios registrados en el sistema.
     *
     * @return Lista de usuarios en el sistema.
     */
    @Override
    public List<Usuario> getAll() {
        return repository.findAll();
    }

    /**
     * Recupera un usuario por su identificador único.
     *
     * @param id Identificador único del usuario.
     * @return El usuario con el ID proporcionado.
     * @throws ModelNotFoundException Si no se encuentra ningún usuario con el ID especificado.
     */
    @Override
    public Usuario getById(Long id) throws ModelNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ModelNotFoundException("User no encontrado"));
    }

    /**
     * Recupera un usuario por su nombre de usuario.
     *
     * @param nombreUsuario Nombre de usuario del usuario.
     * @return El usuario con el nombre de usuario proporcionado.
     * @throws ModelNotFoundException Si no se encuentra ningún usuario con el nombre de usuario especificado.
     */
    @Override
    public Usuario getByNombreUsuario(String nombreUsuario) throws ModelNotFoundException {
        if (!repository.existsBynombreUsuario(nombreUsuario)) {
            throw new ModelNotFoundException("Usuario no encontrado");
        }
        return repository.findByNombreUsuario(nombreUsuario);
    }

    /**
     * Guarda un nuevo usuario en el sistema.
     *
     * @param usuario Usuario a ser guardado.
     * @throws ConflictException Si el usuario ya existe en el sistema.
     */
    @Override
    public void save(Usuario usuario) throws ConflictException {
        if (repository.existsBynombreUsuario(usuario.getNombreUsuario())) {
            throw new ConflictException("Usuario ya existe");
        } else {
            if (usuario.getMediosPago() != null) {
                for (MedioPago dc : usuario.getMediosPago()) {
                    dc.setUsuario(usuario);
                }
            }

        }
        usuario.setFechaRegistro(LocalDateTime.now());
        usuario.setClave(new BCryptPasswordEncoder().encode(usuario.getClave()));

        repository.save(usuario);


    }

    /**
     * Actualiza la información de un usuario existente en el sistema.
     *
     * @param usuario Usuario con la información actualizada.
     * @throws ModelNotFoundException Si el usuario no se encuentra en el sistema.
     */
    @Override
    public void update(Usuario usuario) throws ModelNotFoundException {
        if (repository.existsById(usuario.getId())) {
            repository.save(usuario);
        } else {
            throw new ModelNotFoundException("Usuario no encontrado");
        }
    }

    /**
     * Elimina un usuario del sistema por su nombre de usuario.
     *
     * @param nombreUsuario Nombre de usuario del usuario a ser eliminado.
     * @throws ModelNotFoundException Si no se encuentra ningún usuario con el nombre de usuario especificado.
     */
    @Transactional
    @Override
    public void delete(String nombreUsuario) throws ModelNotFoundException {
        if (repository.existsBynombreUsuario(nombreUsuario)) {
            repository.deleteBynombreUsuario(nombreUsuario);
        } else {
            throw new ModelNotFoundException("Usuario no encontrado");
        }
    }
}

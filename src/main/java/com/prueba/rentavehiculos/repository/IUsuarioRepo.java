package com.prueba.rentavehiculos.repository;

import com.prueba.rentavehiculos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz de repositorio para la entidad Usuario, que extiende JpaRepository de Spring Data JPA.
 * Proporciona métodos para acceder a los datos de los usuarios en la base de datos.
 */
@Repository
public interface IUsuarioRepo extends JpaRepository<Usuario, Long> {

    /**
     * Verifica si existe un usuario en la base de datos con el nombre de usuario proporcionado.
     *
     * @param nombreUsuario El nombre de usuario que se desea verificar.
     * @return `true` si existe un usuario con el nombre de usuario proporcionado, `false` de lo contrario.
     */
    public Boolean existsBynombreUsuario(String nombreUsuario);

    /**
     * Elimina un usuario de la base de datos por su nombre de usuario.
     *
     * @param nombreUsuario El nombre de usuario del usuario que se desea eliminar.
     */
    public void deleteBynombreUsuario(String nombreUsuari);

    /**
     * Busca y devuelve un usuario por su nombre de usuario.
     *
     * @param nombreUsuario El nombre de usuario del usuario que se desea recuperar.
     * @return El objeto Usuario correspondiente al nombre de usuario proporcionado, o `null` si no se encuentra.
     */
    public Usuario findByNombreUsuario(String nombreUsuario);

}

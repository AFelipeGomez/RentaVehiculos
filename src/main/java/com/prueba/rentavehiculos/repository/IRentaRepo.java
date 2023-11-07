package com.prueba.rentavehiculos.repository;

import com.prueba.rentavehiculos.entity.Renta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Interfaz de repositorio para la entidad Renta, que extiende JpaRepository de Spring Data JPA.
 * Proporciona métodos para acceder a los datos de las rentas en la base de datos.
 */
@Repository
public interface IRentaRepo extends JpaRepository<Renta, Long> {


    /**
     * Cuenta la cantidad de rentas abiertas o en revisión asociadas a un usuario específico.
     *
     * @param usuarioId Identificador único del usuario.
     * @return El número de rentas abiertas o en revisión asociadas al usuario proporcionado.
     */
    @Query(value = "SELECT COUNT(*) FROM renta WHERE id_usuario = :usuarioId AND estado ILIKE 'abierto' OR estado ILIKE 'revision'", nativeQuery = true)
    public int countByUsuarioId(@Param("usuarioId") Long usuarioId);


    /**
     * Actualiza el estado de una renta por su ID.
     *
     * @param rentaId     Identificador único de la renta a ser actualizada.
     * @param nuevoEstado Nuevo estado de la renta (p. ej., "Abierto" o "Cerrado").
     */
    @Modifying
    @Query("UPDATE Renta r SET r.estado = :nuevoEstado WHERE r.id = :rentaId")
    public void actualizarEstadoPorId(@Param("rentaId") Long rentaId, @Param("nuevoEstado") String nuevoEstado);

    /**
     * Actualiza el estado y la fecha de finalización de una renta por su ID.
     *
     * @param rentaId     Identificador único de la renta a ser actualizada.
     * @param nuevoEstado Nuevo estado de la renta (p. ej., "Abierto" o "Cerrado").
     */
    @Modifying
    @Query("UPDATE Renta r SET r.estado = :nuevoEstado, r.fechaFin = CURRENT_TIMESTAMP WHERE r.id = :rentaId")
    void actualizarEstadoYFechaFinPorId(@Param("rentaId") Long rentaId, @Param("nuevoEstado") String nuevoEstado);

    /**
     * Verifica si existe un medio de pago asociado a un usuario específico por su ID.
     *
     * @param medioPagoId Identificador único del medio de pago.
     * @param usuarioId   Identificador único del usuario.
     * @return `true` si existe un medio de pago con el ID proporcionado para el usuario proporcionado, `false` de lo contrario.
     */
    @Query("SELECT COUNT(m) > 0 FROM MedioPago m WHERE m.id = :medioPagoId AND m.usuario.id = :usuarioId")
    boolean existsByIdAndUsuarioId(@Param("medioPagoId") Long medioPagoId, @Param("usuarioId") Long usuarioId);

    /**
     * Busca y devuelve una renta por el ID del usuario asociado.
     *
     * @param usuarioId Identificador único del usuario asociado a la renta.
     * @return El objeto Renta correspondiente al usuario proporcionado, o `null` si no se encuentra.
     */
    @Query("SELECT r FROM Renta r WHERE r.usuario.id = :usuarioId")
    Renta findByUsuarioId(@Param("usuarioId") Long usuarioId);

}

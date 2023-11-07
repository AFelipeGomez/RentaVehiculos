package com.prueba.rentavehiculos.repository;

import com.prueba.rentavehiculos.entity.MedioPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Interfaz de repositorio para la entidad MedioPago, que extiende JpaRepository de Spring Data JPA.
 * Proporciona métodos para acceder a los datos de los medios de pago en la base de datos.
 */
@Repository
public interface IMedioPagoRepo extends JpaRepository<MedioPago, Long> {

    /**
     * Cuenta la cantidad de medios de pago asociados a un usuario específico por su tipo de medio de pago.
     *
     * @param usuarioId     Identificador único del usuario.
     * @param tipoMedioPago Tipo de medio de pago que se desea contar.
     * @return El número de medios de pago asociados al usuario proporcionado y del tipo proporcionado.
     */
    @Query(value = "SELECT COUNT(*) FROM medio_pago WHERE id_usuario = :usuarioId AND tipo ILIKE :tipoMedioPago", nativeQuery = true)
    int countByUsuarioIdAndTipoIgnoreCase(@Param("usuarioId") Long usuarioId, @Param("tipoMedioPago") String tipoMedioPago);
}

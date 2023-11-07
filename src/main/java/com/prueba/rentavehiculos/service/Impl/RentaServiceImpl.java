package com.prueba.rentavehiculos.service.Impl;

import com.prueba.rentavehiculos.entity.Renta;
import com.prueba.rentavehiculos.exception.ConflictException;
import com.prueba.rentavehiculos.exception.ModelNotFoundException;
import com.prueba.rentavehiculos.repository.IRentaRepo;
import com.prueba.rentavehiculos.repository.IUsuarioRepo;
import com.prueba.rentavehiculos.service.IRentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;

/**
 * Implementación del servicio para operaciones relacionadas con rentas de vehículos en el sistema.
 */
@Service
public class RentaServiceImpl implements IRentaService {

    @Autowired
    private IRentaRepo repo;

    @Autowired
    private IUsuarioRepo repoUsuario;

    /**
     * Guarda una nueva renta de vehículo en el sistema.
     *
     * @param renta Objeto de tipo Renta que representa la renta a ser guardada.
     * @throws ConflictException      Si ya existe un proceso de renta para el usuario proporcionado
     *                                o si el medio de pago no está registrado para el usuario.
     * @throws ModelNotFoundException Si el medio de pago no está registrado o si el usuario no se encuentra en el sistema.
     */
    @Override
    public void saveRenta(Renta renta) throws ConflictException, ModelNotFoundException {
        if (!repo.existsByIdAndUsuarioId(renta.getMedioPago().getId(), renta.getUsuario().getId())) {
            throw new ModelNotFoundException("El medio de pago no existe dentro de los registrados por el usuario");
        }
        if (repoUsuario.existsById(renta.getUsuario().getId())) {
            if (repo.countByUsuarioId(renta.getUsuario().getId()) > 0) {
                throw new ConflictException("El usuario ya tiene un proceso de renta actualmente");
            }
        } else {
            throw new ModelNotFoundException("Usuario no encontrado para asociar renta de vehiculo");
        }
        renta.setEstado("Abierto");
        repo.save(renta);

    }

    /**
     * Obtiene todas las rentas de vehículos registradas en el sistema.
     *
     * @return Lista de objetos de tipo Renta que representan todas las rentas en el sistema.
     */
    @Override
    public List<Renta> all() {
        return repo.findAll();
    }

    /**
     * Obtiene una renta de vehículo por el ID del usuario asociado a la renta.
     *
     * @param idUsuarioRenta Identificador único del usuario asociado a la renta.
     * @return Objeto de tipo Renta que representa la renta asociada al usuario proporcionado.
     */
    @Override
    public Renta getByUsuario(Long idUsuarioRenta) {
        return repo.findByUsuarioId(idUsuarioRenta);
    }

    /**
     * Actualiza el estado de una renta de vehículo por su ID.
     *
     * @param estado  Nuevo estado de la renta (p. ej., "Abierto" o "Cerrado").
     * @param idRenta Identificador único de la renta a ser actualizada.
     * @throws ModelNotFoundException Si la renta con el ID especificado no se encuentra en el sistema.
     */
    @Transactional
    @Override
    public void updateEstado(String estado, Long idRenta) throws ModelNotFoundException {

        if (repo.existsById(idRenta)) {

            if (estado.toLowerCase().contains("cerrado")) {

                repo.actualizarEstadoYFechaFinPorId(idRenta, estado);
            } else {
                repo.actualizarEstadoPorId(idRenta, estado);
            }

        } else {
            throw new ModelNotFoundException("Error la renta no encontrada");
        }

    }
}

package com.prueba.rentavehiculos.service.Impl;

import com.prueba.rentavehiculos.entity.MedioPago;
import com.prueba.rentavehiculos.entity.Usuario;
import com.prueba.rentavehiculos.exception.DataIntegrityViolationException;
import com.prueba.rentavehiculos.exception.ModelNotFoundException;
import com.prueba.rentavehiculos.repository.IMedioPagoRepo;
import com.prueba.rentavehiculos.repository.IUsuarioRepo;
import com.prueba.rentavehiculos.service.IMedioPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * Implementación del servicio para operaciones relacionadas con medios de pago en el sistema.
 */
@Service
public class MedioPagoServiceImpl implements IMedioPagoService {

    @Autowired
    private IMedioPagoRepo repoMedioPago;

    @Autowired
    private IUsuarioRepo repoUsuario;

    /**
     * Guarda un nuevo medio de pago en el sistema.
     *
     * @param medioPago Objeto de tipo MedioPago que representa el medio de pago a ser guardado.
     * @throws ModelNotFoundException        Si el ID de usuario no está proporcionado o si el usuario no existe en el sistema.
     * @throws DataIntegrityViolationException Si el medio de pago ya existe para el usuario actual.
     */
    @Override
    public void save(MedioPago medioPago)throws ModelNotFoundException, DataIntegrityViolationException {

        if(medioPago.getUsuario().getId()==null){
            throw  new ModelNotFoundException("El id de usuario es requerido");

        }else {
            if(repoMedioPago.countByUsuarioIdAndTipoIgnoreCase(medioPago.getUsuario().getId(),medioPago.getTipo())>0){
                throw new DataIntegrityViolationException("Error el medio de pago ya existe para el usuario actual");
            }
            Optional<Usuario> usuarioOp = repoUsuario.findById(medioPago.getUsuario().getId());
            if(usuarioOp.isPresent()){
                Usuario usuario = usuarioOp.get();
                medioPago.setUsuario(usuario);
                usuario.getMediosPago().add(medioPago);
                repoMedioPago.save(medioPago);

            }else{
                throw new ModelNotFoundException("Error el usuario no existe valide la información");
            }

        }

    }

    /**
     * Actualiza la información de un medio de pago existente en el sistema.
     *
     * @param medioPago Objeto de tipo MedioPago con la información actualizada.
     */
    @Override
    public void update(MedioPago medioPago) {

    }

    /**
     * Elimina un medio de pago del sistema por su ID.
     *
     * @param id Identificador único del medio de pago a ser eliminado.
     * @throws ModelNotFoundException Si el medio de pago con el ID especificado no se encuentra en el sistema.
     */
    @Override
    public void delete(Long id)throws ModelNotFoundException {

        if (!repoMedioPago.existsById(id)) {

            throw new ModelNotFoundException("Medio de Pago no encontrado verifique el Id");
        } else {

            MedioPago dtMetodoPago = repoMedioPago.getById(id);
           repoMedioPago.delete(dtMetodoPago);
        }

    }

    @Override
    public List<MedioPago> getByUsuario(String nombreUsuario) {
        return null;
    }
}

package com.prueba.rentavehiculos.controller;

import com.prueba.rentavehiculos.entity.MedioPago;
import com.prueba.rentavehiculos.entity.Usuario;
import com.prueba.rentavehiculos.exception.ConflictException;
import com.prueba.rentavehiculos.exception.DataIntegrityViolationException;
import com.prueba.rentavehiculos.exception.ModelNotFoundException;
import com.prueba.rentavehiculos.service.IMedioPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para las operaciones relacionadas con medios de pago.
 * Este controlador maneja las solicitudes HTTP relacionadas con la entidad MedioPago.
 */
@RestController
@RequestMapping("/MedioPago")
public class MedioPagoController {

    @Autowired
    private IMedioPagoService service;

    /**
     * Maneja las solicitudes POST para guardar un nuevo medio de pago.
     *
     * @param medioPago Objeto MedioPago que se va a guardar en el sistema.
     * @return ResponseEntity que indica el resultado de la operación, junto con un mensaje de éxito.
     * @throws ModelNotFoundException          Si el usuario asociado al medio de pago no se encuentra en el sistema.
     * @throws DataIntegrityViolationException Si hay conflictos de integridad de datos durante la creación del medio de pago.
     */
    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody MedioPago medioPago) throws ModelNotFoundException, DataIntegrityViolationException {

        service.save(medioPago);
        return new ResponseEntity<Object>("Medio de pago creado", HttpStatus.CREATED);
    }

    /**
     * Maneja las solicitudes DELETE para eliminar un medio de pago por su ID.
     *
     * @param idMedioPago Identificador único del medio de pago que se va a eliminar.
     * @return ResponseEntity que indica el resultado de la operación.
     * @throws ModelNotFoundException Si el medio de pago con el ID proporcionado no se encuentra en el sistema.
     */
    @DeleteMapping(value = "/delete/{idMedioPago}")
    public ResponseEntity<?> delete(@PathVariable Long idMedioPago) throws ModelNotFoundException {

        service.delete(idMedioPago);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }
}

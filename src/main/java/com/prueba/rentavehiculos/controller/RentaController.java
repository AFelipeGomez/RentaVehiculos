package com.prueba.rentavehiculos.controller;

import com.prueba.rentavehiculos.entity.Renta;
import com.prueba.rentavehiculos.exception.ConflictException;
import com.prueba.rentavehiculos.exception.ModelNotFoundException;
import com.prueba.rentavehiculos.service.IRentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controlador REST para las operaciones relacionadas con rentas de vehículos.
 * Este controlador maneja las solicitudes HTTP relacionadas con la entidad Renta.
 */
@RestController
@RequestMapping("/rentaVehiculo")
public class RentaController {

    @Autowired
    private IRentaService service;

    /**
     * Maneja las solicitudes POST para guardar una nueva renta de vehículo.
     *
     * @param renta Objeto Renta que se va a guardar en el sistema.
     * @return ResponseEntity que indica el resultado de la operación, junto con la renta creada.
     * @throws ConflictException Si hay conflictos durante la creación de la renta.
     * @throws ModelNotFoundException Si no se encuentra el modelo asociado durante la creación de la renta.
     */
    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody Renta renta) throws ConflictException, ModelNotFoundException {

        service.saveRenta(renta);
        return new ResponseEntity<Object>(renta, HttpStatus.CREATED);
    }

    /**
     * Maneja las solicitudes GET para obtener una renta de vehículo por el ID del usuario asociado.
     *
     * @param idUsuarioRenta Identificador único del usuario asociado a la renta.
     * @return ResponseEntity que contiene la renta solicitada y el estado de la operación.
     * @throws ModelNotFoundException Si la renta asociada al usuario proporcionado no se encuentra en el sistema.
     */
    @GetMapping(value = "/getByUsuario/{idUsuarioRenta}", produces = "application/json")
    public ResponseEntity<?> getByUsuario(@PathVariable Long idUsuarioRenta) throws ModelNotFoundException, Exception {
        Renta renta = service.getByUsuario(idUsuarioRenta);

        return new ResponseEntity<>(renta, HttpStatus.OK);

    }

    /**
     * Maneja las solicitudes PUT para actualizar el estado de una renta por su ID.
     *
     * @param requestData Mapa que contiene los datos de la solicitud, incluyendo el ID de la renta y el nuevo estado.
     * @return ResponseEntity que indica el resultado de la operación.
     * @throws ModelNotFoundException Si la renta con el ID proporcionado no se encuentra en el sistema.
     */
    @PutMapping(value = "/update", consumes = "application/json")
    public ResponseEntity<?> update(@RequestBody Map<String, Object> requestData) throws ModelNotFoundException {
        Long idRenta = Long.parseLong(requestData.get("idRenta").toString());
        String estado = requestData.get("estado").toString();
        service.updateEstado(estado,idRenta);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}

package com.prueba.rentavehiculos.controller;

import com.prueba.rentavehiculos.entity.Usuario;
import com.prueba.rentavehiculos.exception.ConflictException;
import com.prueba.rentavehiculos.exception.ModelNotFoundException;
import com.prueba.rentavehiculos.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para las operaciones relacionadas con usuarios.
 * Este controlador maneja las solicitudes HTTP relacionadas con la entidad Usuario.
 */
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService service;

    /**
     * Maneja las solicitudes POST para guardar un nuevo usuario.
     *
     * @param usuario Objeto Usuario que se va a guardar en el sistema.
     * @return ResponseEntity que indica el resultado de la operación, junto con el usuario creado.
     * @throws ConflictException Si el usuario ya existe en el sistema.
     */
    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody Usuario usuario) throws ConflictException {

        service.save(usuario);
        return new ResponseEntity<Object>(usuario, HttpStatus.CREATED);
    }

    /**
     * Maneja las solicitudes GET para obtener todos los usuarios.
     *
     * @return ResponseEntity que contiene una lista de usuarios y el estado de la operación.
     * @throws ModelNotFoundException Si no se encuentran usuarios en el sistema.
     */
    @GetMapping(value = "/all", produces =
            "application/json")
    public ResponseEntity<?> get() throws ModelNotFoundException {

        List<Usuario> listUser = service.getAll();
        return new ResponseEntity<>(listUser, HttpStatus.OK);

    }

    /**
     * Maneja las solicitudes DELETE para eliminar un usuario por su nombre de usuario.
     *
     * @param nombreUsuario Nombre de usuario del usuario que se va a eliminar.
     * @return ResponseEntity que indica el resultado de la operación.
     * @throws ModelNotFoundException Si el usuario con el nombre de usuario proporcionado no se encuentra en el sistema.
     */
    @DeleteMapping(value = "/delete/{nombreUsuario}")
    public ResponseEntity<?> delete(@PathVariable String nombreUsuario) throws ModelNotFoundException {

        service.delete(nombreUsuario);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }


    /**
     * Maneja las solicitudes GET para obtener un usuario por su nombre de usuario.
     *
     * @param nombreUsuario Nombre de usuario del usuario que se desea recuperar.
     * @return ResponseEntity que contiene el usuario solicitado y el estado de la operación.
     * @throws ModelNotFoundException Si el usuario con el nombre de usuario proporcionado no se encuentra en el sistema.
     */
    @GetMapping(value = "/getBynombreuser/{nombreUsuario}", produces = "application/json")
    public ResponseEntity<?> getByDocument(@PathVariable String nombreUsuario) throws ModelNotFoundException, Exception {
        Usuario usuario = service.getByNombreUsuario(nombreUsuario);

        return new ResponseEntity<>(usuario, HttpStatus.OK);

    }


}

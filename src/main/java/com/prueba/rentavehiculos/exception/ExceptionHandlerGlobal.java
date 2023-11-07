package com.prueba.rentavehiculos.exception;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.Conflict;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
@RestController
public class ExceptionHandlerGlobal extends ResponseEntityExceptionHandler{

    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<ExceptionWrapper> ManejadorDataIntegrityViolationException(DataIntegrityViolationException e,
                                                                             WebRequest request) {

        System.out.print("Ingreso DataIntegrityViolationException global");
        ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.toString(),
                e.getMessage(), request.getDescription(false));
        return new ResponseEntity<ExceptionWrapper>(ew, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(ConflictException.class)
    public final ResponseEntity<ExceptionWrapper> manejadorConflictException(ConflictException e,
                                                                             WebRequest request) {

        System.out.print("Ingreso ConflicException global");
        ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString(),
                e.getMessage(), request.getDescription(false));
        return new ResponseEntity<ExceptionWrapper>(ew, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ModelNotFoundException.class)
    public final ResponseEntity<ExceptionWrapper> manejadorModelNotFoundException(ModelNotFoundException e,
                                                                                  WebRequest request) {

        System.out.print("Ingreso modelnotfound global");
        ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(),
                e.getMessage(), request.getDescription(false));
        return new ResponseEntity<ExceptionWrapper>(ew, HttpStatus.NOT_FOUND);
    }


}

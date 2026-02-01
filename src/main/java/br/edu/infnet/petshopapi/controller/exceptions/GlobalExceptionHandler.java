package br.edu.infnet.petshopapi.controller.exceptions;

import br.edu.infnet.petshopapi.model.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    Map<String, String> errors = new HashMap<>();

    @ExceptionHandler(ClienteInvalidoException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(ClienteInvalidoException ex) {
        errors = new HashMap<>();

        errors.put("Data/hora", LocalDateTime.now().toString());
        errors.put("Status", HttpStatus.BAD_REQUEST.toString());
        errors.put("message", ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(ClienteNaoEncontradoException ex) {
        errors = new HashMap<>();

        errors.put("Data/hora", LocalDateTime.now().toString());
        errors.put("Status", HttpStatus.NOT_FOUND.toString());
        errors.put("message", ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FuncionarioInvalidoException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(FuncionarioInvalidoException ex) {
        errors = new HashMap<>();

        errors.put("Data/hora", LocalDateTime.now().toString());
        errors.put("Status", HttpStatus.BAD_REQUEST.toString());
        errors.put("message", ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FuncionarioNaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(FuncionarioNaoEncontradoException ex) {
        errors = new HashMap<>();

        errors.put("Data/hora", LocalDateTime.now().toString());
        errors.put("Status", HttpStatus.NOT_FOUND.toString());
        errors.put("message", ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PetNaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(PetNaoEncontradoException ex) {
        errors = new HashMap<>();

        errors.put("Data/hora", LocalDateTime.now().toString());
        errors.put("Status", HttpStatus.NOT_FOUND.toString());
        errors.put("message", ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PetInvalidoException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(PetInvalidoException ex) {
        errors = new HashMap<>();

        errors.put("Data/hora", LocalDateTime.now().toString());
        errors.put("Status", HttpStatus.BAD_REQUEST.toString());
        errors.put("message", ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(IllegalArgumentException ex) {
        errors = new HashMap<>();

        errors.put("Data/hora", LocalDateTime.now().toString());
        errors.put("Status", HttpStatus.BAD_REQUEST.toString());
        errors.put("message", ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(Exception ex) {
        errors = new HashMap<>();

        errors.put("Data/hora", LocalDateTime.now().toString());
        errors.put("Status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
        errors.put("message", ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

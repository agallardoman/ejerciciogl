package com.globallogic.ejerciciogl.advice;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;

@Slf4j
@RestControllerAdvice
public class UserHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> entityViolations(DataIntegrityViolationException e) {
        log.error(e.getMessage(), e);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "El correo ya registrado");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    @ExceptionHandler({TransactionSystemException.class})
    public ResponseEntity<Object> validationExceptions(TransactionSystemException ex){
        Map<String, Object> response = new HashMap<>();
        Throwable cause = ex.getRootCause();
        if (cause instanceof ConstraintViolationException) {
            Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) cause).getConstraintViolations();
            List<Object> errorList = new ArrayList<>();
            constraintViolations.forEach(x -> {
                Map<String, String> error = new HashMap<>();
                error.put("field", x.getPropertyPath().toString());
                error.put("message", x.getMessage());
                errorList.add(error);
            });
            response.put("message", "Error en validaciones de usuario");
            response.put("fields", errorList);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        log.error(ex.getMessage(), ex);
        response.put("error", "No se pudo categorizar el error");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

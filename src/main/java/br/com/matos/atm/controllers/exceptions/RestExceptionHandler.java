package br.com.matos.atm.controllers.exceptions;

import br.com.matos.atm.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<RestExceptionResponse> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        String error = "Object not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        RestExceptionResponse err = new RestExceptionResponse(LocalDateTime.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}

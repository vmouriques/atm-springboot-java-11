package br.com.matos.atm.controllers.exceptions;

import br.com.matos.atm.services.exceptions.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<Object> customException(CustomException ex, HttpServletRequest request) {
        RestExceptionResponse err = new RestExceptionResponse(LocalDateTime.now(), ex.getStatus(), ex.getMessage(), request.getRequestURI());
        return  ResponseEntity.status(ex.getStatus()).body(err);
    }


}

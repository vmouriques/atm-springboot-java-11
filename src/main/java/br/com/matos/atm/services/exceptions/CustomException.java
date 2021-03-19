package br.com.matos.atm.services.exceptions;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {
    private static final long serialVersionUID = -5793602529417594377L;

    private HttpStatus status;

    public CustomException(String mensagem, HttpStatus status) {
        super(mensagem);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return this.status;
    }
}

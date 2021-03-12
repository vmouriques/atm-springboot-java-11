package br.com.matos.atm.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 7537869112677012909L;

    public ObjectNotFoundException(Object id) {
        super("Object not found. id " + id);
    }
}